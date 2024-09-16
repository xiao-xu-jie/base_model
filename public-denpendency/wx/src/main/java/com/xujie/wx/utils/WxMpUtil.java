package com.xujie.wx.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONObject;
import com.xujie.wx.config.WxMpConfig;
import com.xujie.wx.constants.WxMpApiConstant;
import com.xujie.wx.entity.WxMpQrCode;
import com.xujie.wx.entity.WxMpToken;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xujie
 * @since 2024/9/16 10:01
 * Description: 微信公众号工具类
 **/
@Slf4j
@Component
public class WxMpUtil {
    @Lazy
    @Resource
    private WebClient webClient;

    @Lazy
    @Resource
    private WxMpConfig wxMpConfig;

    private WxMpToken token;

    /**
     * 将xml文件转成易处理的map
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static Map<String, String> parseXml(final HttpServletRequest request) throws Exception {
        final Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();
        final SAXReader reader = new SAXReader();
        final Document document = reader.read(inputStream);
        final Element root = document.getRootElement();
        final List<Element> elementList = (List<Element>) root.elements();
        for (final Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 验证签名
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param token
     * @return
     */
    public static Boolean checkSignature(String signature, String timestamp, String nonce, String token) {

        String[] arr = new String[]{token, timestamp, nonce};
        // 将token、timestamp、nonce三个参数进行字典序排序
        Arrays.sort(arr);
        StringBuilder content = new StringBuilder();
        for (String s : arr) {
            content.append(s);
        }
        // 将三个参数字符串拼接成一个字符串进行sha1加密
        String result = DigestUtil.sha1Hex(content.toString());
        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        if (StringUtils.compare(signature, result) == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取Token
     *
     * @return
     */
    public WxMpToken getToken() {
        String url = WxMpApiConstant.GET_TOKEN_API;
        WxMpToken token = null;
        try {
            token = webClient.get()
                    .uri(String.format(url, wxMpConfig.getAppId(), wxMpConfig.getAppSecret()))
                    .retrieve()
                    .bodyToMono(WxMpToken.class)
                    .block();
        } catch (Exception e) {
            log.error("WxMpUtil.getToken.error: {}", e.getMessage());
        }

        if (token != null && ObjectUtils.anyNotNull(token.getErrorCode(), token.getErrorMsg())) {
            log.error("WxMpUtil.getToken.error: {}", token);
        }
        if (token != null) {
            this.token = token;
        }
        return token;
    }

    /**
     * 获取带参数二维码
     *
     * @param sceneStr
     * @param expireSeconds
     * @return
     */
    public WxMpQrCode getQrCode(String sceneStr, Long expireSeconds) {
        if (token == null) {
            token = getToken();
            if (token == null) {
                log.error("WxMpUtil.getQrCode.error: token is null");
                return null;
            }
        }
        String url = WxMpApiConstant.GET_QRCODE_API;
        WxMpQrCode qrCode = null;
        JSONObject requestBody = new JSONObject();
        requestBody.putIfAbsent("expire_seconds", expireSeconds);
        requestBody.putIfAbsent("action_name", "QR_STR_SCENE");
        requestBody.putIfAbsent("action_info", new JSONObject().putIfAbsent("scene", new JSONObject().putIfAbsent("scene_str", sceneStr)));
        try {
            qrCode = webClient.post()
                    .uri(String.format(url, token.getAccessToken()))
                    .body(BodyInserters.fromValue(requestBody.toString()))
                    .retrieve()
                    .bodyToMono(WxMpQrCode.class)
                    .block();
        } catch (Exception e) {
            log.error("WxMpUtil.getQrCode.error: {}", e.getMessage());
        }
        if (ObjectUtils.anyNull(qrCode.getTicket(), qrCode.getUrl())) {
            log.error("WxMpUtil.getQrCode.error: {}", qrCode);
        }
        return qrCode;
    }
}

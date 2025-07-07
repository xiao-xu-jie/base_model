package com.xujie.business.application.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.xujie.business.application.oss.config.OssProperties;
import com.xujie.business.application.oss.dto.OssDto;
import com.xujie.business.commom.enums.oss.OssBucketEnum;
import com.xujie.business.commom.enums.oss.OssUploadEnum;
import com.xujie.future.contract.exception.BusinessException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class OssUtil {

    @Resource
    private OssProperties ossProperties;


    /**
     * @description: 获取临时访问凭证
     * @author: AKang
     * @date: 2025/5/10 21:28
     * @param: []
     * @return: com.aliyuncs.auth.sts.AssumeRoleResponse
     **/
    public OssDto.StsResponse GetAccessCredentials(OssBucketEnum bucketEnum, String path) {
        log.debug("获取OSS临时访问凭证：{}，{}", bucketEnum.getBucketName(), path);
        // STS服务接入点
        String endpoint = ossProperties.getEndpoint();
        // 获取桶的名称
        String bucketName = bucketEnum.getBucketName();
        // 获取区域
        String region = bucketEnum.getRegion();

        // 获取RAM用户的访问密钥(AccessKeyID和AccessKeySecret)
        String accessKeyId = ossProperties.getAccessKeyId();
        String accessKeySecret = ossProperties.getAccessKeySecret();
        // 获取RAM角色的RamRoleArn
        String roleArn = ossProperties.getRamRoleArn();

        // 自定义角色会话名称,用来区分不同的令牌
        String roleSessionName = UUID.randomUUID().toString();
        // 发起STS请求所在的地域,建议保留默认值 -> 空字符串
        String regionId = "";
        // 添加endpoint
        DefaultProfile.addEndpoint(regionId, "Sts", endpoint);
        // 构造default profile
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 构造client
        DefaultAcsClient client = new DefaultAcsClient(profile);

        final AssumeRoleRequest request = new AssumeRoleRequest();

        request.setSysMethod(MethodType.POST);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);

        // 确保路径格式正确，处理前导和尾部斜杠
        String formattedPath = path;
        if (!formattedPath.startsWith("/")) {
            formattedPath = "/" + formattedPath;
        }
        if (!formattedPath.endsWith("/")) {
            formattedPath = formattedPath + "/";
        }
        // 设置policy,保证每个用户获得临时凭证后都在同一个桶中有且仅有自己的一个操作目录
        String policy = "{"
                + "\"Version\": \"1\","
                + "\"Statement\": ["
                + "    {"
                + "        \"Effect\": \"Allow\","
                + "        \"Action\": ["
                + "            \"oss:PutObject\","
                + "            \"oss:GetObject\","
                + "            \"oss:ListObjects\""
                + "        ],"
                + "\"Resource\": [\"acs:oss:*:*:" + bucketName + formattedPath + "*\"]"
                + "    }"
                + "]"
                + "}";
        log.info("授权的资源路径: {}", "acs:oss:*:*:" + bucketName + path + "/*");

        request.setPolicy(policy);

        // 临时访问凭证的有效时间,单位为秒
        Long durationSeconds = 3600L;
        request.setDurationSeconds(durationSeconds);

        // 与阿里云服务进行通信,获得临时访问凭证
        final AssumeRoleResponse acsResponse;
        try {
            acsResponse = client.getAcsResponse(request);
        } catch (ClientException | com.aliyuncs.exceptions.ClientException e) {
            throw new BusinessException("服务端初始化失败");
        }

        //设置凭证过期时间
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

        OssDto.StsResponse stsResponse = new OssDto.StsResponse();
        stsResponse.setAccessKeyId(acsResponse.getCredentials().getAccessKeyId());
        stsResponse.setAccessKeySecret(acsResponse.getCredentials().getAccessKeySecret());
        stsResponse.setSecurityToken(acsResponse.getCredentials().getSecurityToken());
        stsResponse.setBucketName(bucketName);
        stsResponse.setRegion(region);
        stsResponse.setFilePath(path);
        log.debug("获取OSS临时访问凭证：{}，{}，{}", bucketName, path, stsResponse);
        return stsResponse;
    }

    /**
     * @param uploadEnum
     * @param fileName
     * @param inputStream
     * @description: 上传文件
     */
    public void uploadFile(OssUploadEnum uploadEnum, String fileName, InputStream inputStream) {
        //桶名
        String bucketName = uploadEnum.getBucket().getBucketName();
        //桶节点
        String endpoint = uploadEnum.getBucket().getEndpoint();
        //AccessKeyId
        String accessKeyId = ossProperties.getAccessKeyId();
        //AccessKeySecret
        String accessKeySecret = ossProperties.getAccessKeySecret();

        // 上传文件到 OSS(阿里云OSS服务核心代码)
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //调用阿里云检测方法,检测文件(包括路径)是否已经存在,避免文件覆盖,存在则抛出异常
        fileName = uploadEnum.getPath() + fileName;
        if (ossClient.doesObjectExist(bucketName, fileName)) {
            throw new BusinessException("文件已存在");
        }
        ossClient.putObject(bucketName, fileName, inputStream);

        // 关闭ossClient
        ossClient.shutdown();
    }

    public String getFileUrl(OssUploadEnum uploadEnum, String fileName) {
        String template = "https://%s.%s.aliyuncs.com/%s%s";
        OssBucketEnum bucket = uploadEnum.getBucket();
        return template.formatted(bucket.getBucketName(), bucket.getRegion(), uploadEnum.getPath(), fileName);
    }
}

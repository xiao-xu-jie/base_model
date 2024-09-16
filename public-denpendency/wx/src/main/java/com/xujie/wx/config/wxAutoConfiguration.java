package com.xujie.wx.config;

import com.xujie.wx.utils.WxAppUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;

/**
 * @author Xujie
 * @since 2024/9/15 10:40
 **/

@ComponentScan(value = "com.xujie.wx")
@Import({WxAppUtils.class})
public class wxAutoConfiguration {
    Logger logger = Logger.getLogger("wxAutoConfiguration");


    @PostConstruct
    public void init() {
        System.out.println();
        String banner = """
                ____  ___         __.__          __      __        \s
                \\   \\/  /__ __   |__|__| ____   /  \\    /  \\___  ___
                 \\     /|  |  \\  |  |  |/ __ \\  \\   \\/\\/   /\\  \\/  /
                 /     \\|  |  /  |  |  \\  ___/   \\        /  >    <\s
                /___/\\  \\____/\\__|  |__|\\___  >   \\__/\\  /  /__/\\_ \\
                      \\_/    \\______|       \\/         \\/         \\/
                                                                   \s
                """;
        System.out.println(banner);
        System.out.println("\033[32m :: Wx Auto Count ::\033[0m               (v0.1)");
    }
}

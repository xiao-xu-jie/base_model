package com.xujie.wx.config;

import com.xujie.wx.utils.WxAppUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.logging.Logger;


@Configuration
@ComponentScan("com.xujie.wx")
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

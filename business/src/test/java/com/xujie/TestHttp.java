package com.xujie;

import com.xujie.business.businessApplication;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;

import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

/**
 * @author Xujie
 * @since 2024/9/15 10:24
 **/

@SpringBootTest(classes = {businessApplication.class})
public class TestHttp {
    @Resource
    private WebClient webClient;

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Consumer<String> consumer = response -> {
            System.out.println(response);
            countDownLatch.countDown();
        };
        Thread thread = new Thread(() -> {
            Disposable subscribe = webClient.get().uri("https://jsonplaceholder.typicode.com/posts")
                    .retrieve()
                    .bodyToMono(String.class)
                    .subscribe(consumer);
            System.out.println("start");

        });
        thread.start();
        countDownLatch.await();
        System.out.println("end");
    }
}

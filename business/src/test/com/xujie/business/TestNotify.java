package com.xujie.business;

import com.xujie.business.domain.service.NotifyDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Xujie
 * @since 2024/11/16 13:05
 */
@SpringBootTest(classes = businessApplication.class)
public class TestNotify {
  @Resource private NotifyDomainService notifyDomainService;

  @Test
  public void testNotify() {
    notifyDomainService.handlePaySuccess(1840249677664956416L);
  }
}

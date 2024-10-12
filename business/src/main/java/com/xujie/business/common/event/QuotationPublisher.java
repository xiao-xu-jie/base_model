package com.xujie.business.common.event;

import com.xujie.business.domain.BO.BizEggQuotationBO;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Xujie
 * @since 2024/10/12 21:00
 */
@Component
public class QuotationPublisher implements ApplicationEventPublisherAware {
  private ApplicationEventPublisher publisher;

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.publisher = applicationEventPublisher;
  }

  @Async
  public void publishEvent(Long userId, BizEggQuotationBO bizEggQuotationBO) {
    QuotationSubmitEvent event = new QuotationSubmitEvent(this, userId, bizEggQuotationBO);
    publisher.publishEvent(event);
  }
}

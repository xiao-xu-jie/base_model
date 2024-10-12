package com.xujie.business.common.event;

import com.xujie.business.domain.BO.BizEggQuotationBO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Xujie
 * @since 2024/10/12 20:58
 */
@Getter
public class QuotationSubmitEvent extends ApplicationEvent {
  private Long subUserId;
  private BizEggQuotationBO bizEggQuotationBO;

  public QuotationSubmitEvent(Object source) {
    super(source);
  }

  public QuotationSubmitEvent(Object source, Long subUserId, BizEggQuotationBO bizEggQuotationBO) {
    super(source);
    this.subUserId = subUserId;
    this.bizEggQuotationBO = bizEggQuotationBO;
  }
}

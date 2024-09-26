package com.xujie.business.common.adapters;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


public interface PlatFormAdapter<Q,QR,A,AR> {
    QR queryUserClass(Q q);
    AR submitOrder(A a);
}

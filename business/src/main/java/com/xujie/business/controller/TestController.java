package com.xujie.business.controller;

import com.xujie.future.contract.response.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xujie
 * @since 2024/9/13 17:40
 **/

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.success("hello");
    }
}

package com.example.demo.entity.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
//@Component
//@Primary
@FeignClient (name = "test", url = "http://localhost:8080",fallback = FeignCliFailed.class)
public interface FeignCli {

    @GetMapping("/")
    String feignTest();
}

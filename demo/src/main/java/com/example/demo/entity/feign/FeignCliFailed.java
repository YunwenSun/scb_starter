package com.example.demo.entity.feign;

import org.springframework.stereotype.Component;

@Component
public class FeignCliFailed implements FeignCli {

    @Override
    public String feignTest(){
        return "failed to get feign connection";
    }
}

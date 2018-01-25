package com.woowahan.codesquad001;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String hello() {
        return "Hello, World~_~!";
    }

}

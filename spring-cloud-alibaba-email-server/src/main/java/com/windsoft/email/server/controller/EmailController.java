package com.windsoft.email.server.controller;

import com.windsoft.email.server.generator.impl.EmailCodeGenerator;
import com.windsoft.email.server.service.EmailCodeService;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EmailController {

    @Resource
    private EmailCodeService emailCodeService;
    @Resource
    private EmailCodeGenerator emailCodeGenerator;

    /**
     * 邮箱验证码接口
     */
    @GetMapping("/login/email/{email}")
    public void createSmsCode(@PathVariable String email) throws ServletRequestBindingException {
        // 发送短信
        emailCodeService.send(email, emailCodeGenerator.getCode());
    }
}

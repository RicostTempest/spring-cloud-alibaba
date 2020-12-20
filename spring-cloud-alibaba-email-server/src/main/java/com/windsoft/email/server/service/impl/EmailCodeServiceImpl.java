package com.windsoft.email.server.service.impl;

import com.windsoft.email.server.service.EmailCodeService;
import com.windsoft.email.server.utils.RedisUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Setter
@Service
public class EmailCodeServiceImpl implements EmailCodeService {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 简单文本邮件
     */
    @Override
    public void send(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("kc登录验证码");
        // 邮件的内容
        message.setText(contentBild(code, 60));
        // 设置接收者邮箱
        message.setTo(to);
        // 设置发送者邮箱
        message.setFrom(from);
        // 发送
        mailSender.send(message);
        saveCode(to, code);
    }
    /**
     * 生成邮箱信息
     */
    private String contentBild(String code, int expireIn) {
        String sb = "您的验证码:" +
                code +
                ",有效时效:" +
                expireIn;
        return sb;
    }

    private void saveCode(String to, String code){
        redisUtil.set(to,code);
    }
}
package com.windsoft.email.server.generator.impl;

import com.windsoft.email.server.generator.ValidateCodeGenerator;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailCodeGenerator implements ValidateCodeGenerator {
    private Random random = new Random();
    @Override
    public String getCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}

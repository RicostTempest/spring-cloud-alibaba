package com.windsoft.oauth.service.impl;


import com.windsoft.oauth.domain.User;
import com.windsoft.oauth.mapper.UserMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class PasswordDetailsService implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",s);
        User user = userMapper.selectOneByExample(example);
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}

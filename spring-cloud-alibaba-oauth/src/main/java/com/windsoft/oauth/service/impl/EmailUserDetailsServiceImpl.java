package com.windsoft.oauth.service.impl;


import com.windsoft.oauth.domain.User;
import com.windsoft.oauth.mapper.UserMapper;
import com.windsoft.oauth.security.component.email.service.EmailUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * 根据邮箱获取验证码
 *
 * @author tangchen
 */
@Service
public class EmailUserDetailsServiceImpl implements EmailUserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String loadCodeByEmail(String email) {
        return "1234";
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email",email);
        User user = userMapper.selectOneByExample(example);
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return user;
    }
}

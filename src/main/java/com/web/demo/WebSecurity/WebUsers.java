package com.web.demo.WebSecurity;

import com.web.demo.Dao.Mapper.UserMapper;
import com.web.demo.Dao.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class WebUsers implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = new MyUser();
        MyUser myUser = userMapper.getOne(username); //需要判断是非为空
        if(myUser == null)throw new UnknownError(); // 记得自定义异常----------------------------
        user.setUsername(username);
        user.setPassword(this.passwordEncoder.encode(myUser.getPassword()));
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(username == "MrHALO") authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        else authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("user");
        return new User(username, user.getPassword(),user.isEnabled(),user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),user.isAccountNonLocked(),
                authorities);
    }

}

package com.note.swnote.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

public class UserPrincipal extends User {
    private final Long userId;

//    public UserPrincipal(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }

    public UserPrincipal(com.note.swnote.domain.User user) {
        super(user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        this.userId = user.getId();
    }

    public Long getUserId() {
        return userId;
    }
}
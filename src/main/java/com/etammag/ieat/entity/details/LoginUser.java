package com.etammag.ieat.entity.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.etammag.ieat.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class LoginUser implements UserDetails, Login{

    private User user;

    private List<String> permissions;

    private String verCode;

    public LoginUser(User user, String verCode) {
        this.user = user;
        this.verCode = verCode;
        this.permissions = new ArrayList<>();
        this.addPermission("USER");
        this.addPermission("ADDRESSBOOK");
    }

    @Override
    public Long getId() {
        return user.getId();
    }

    @Override
    public void addPermission(String permission) {
        permissions.add(permission);
    }

    @Override
    @JSONField(serialize = false)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream().
                map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return verCode;
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return user.getPhone();
    }

    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }
}

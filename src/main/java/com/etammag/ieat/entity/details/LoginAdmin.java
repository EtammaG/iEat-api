package com.etammag.ieat.entity.details;

import com.alibaba.fastjson.annotation.JSONField;
import com.etammag.ieat.entity.Admin;
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
public class LoginAdmin implements UserDetails, Login {

    private Admin admin;

    public LoginAdmin(Admin admin) {
        this.admin = admin;
        this.permissions = new ArrayList<>();
        this.addPermission("ADMIN");
    }

    private List<String> permissions;

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
        return admin.getPassword();
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return admin.getUsername();
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
        return admin.getStatus() == 1;
    }

    @Override
    public Long getId() {
        return admin.getId();
    }
}

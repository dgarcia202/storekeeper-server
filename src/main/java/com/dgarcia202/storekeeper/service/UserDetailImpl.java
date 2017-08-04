package com.dgarcia202.storekeeper.service;

import com.dgarcia202.storekeeper.entity.AccountEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailImpl implements UserDetails
{
    private Long id;

    private String username;

    private String password;

    private boolean enabled;

    public UserDetailImpl()
    {
    }

    public UserDetailImpl(AccountEntity entity)
    {
        this.id = entity.getId();
        this.username =  entity.getUsername();
        this.password = entity.getPassword();
        this.enabled = entity.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return authorities;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;
    }
}

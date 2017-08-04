package com.dgarcia202.storekeeper.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ACCOUNT")
public class AccountEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false)
    @NotNull
    private Long id;

    @Column(name = "USERNAME", unique = true)
    @NotNull
    private String username;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "ENABLED")
    @NotNull
    private boolean enabled;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }
}

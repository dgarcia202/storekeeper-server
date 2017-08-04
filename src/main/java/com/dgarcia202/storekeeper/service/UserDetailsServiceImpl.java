package com.dgarcia202.storekeeper.service;

import com.dgarcia202.storekeeper.entity.AccountEntity;
import com.dgarcia202.storekeeper.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        AccountEntity entity = this.userRepository.findOneByUsername(username);

        return entity != null ?
                new UserDetailImpl(entity) :
                null;
    }
}

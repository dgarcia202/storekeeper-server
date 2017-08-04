package com.dgarcia202.storekeeper.repository;

import com.dgarcia202.storekeeper.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<AccountEntity, Long>
{
    AccountEntity findOneByUsername(String username);
}

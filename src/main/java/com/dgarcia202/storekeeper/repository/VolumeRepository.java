package com.dgarcia202.storekeeper.repository;

import com.dgarcia202.storekeeper.entity.VolumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface VolumeRepository extends JpaRepository<VolumeEntity, UUID>
{
    VolumeEntity findByActive(Boolean active);
}

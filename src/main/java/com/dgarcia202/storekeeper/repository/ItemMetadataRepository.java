package com.dgarcia202.storekeeper.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;

@Transactional
public interface ItemMetadataRepository extends JpaRepository<ItemMetadataEntity, UUID>
{

}

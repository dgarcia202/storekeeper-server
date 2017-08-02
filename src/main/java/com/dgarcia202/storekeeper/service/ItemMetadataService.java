package com.dgarcia202.storekeeper.service;

import java.util.UUID;
import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;
import com.dgarcia202.storekeeper.exception.ItemNotFoundException;

public interface ItemMetadataService
{
	ItemMetadataEntity getById(UUID id);

	void add(ItemMetadataEntity metadata);

	ItemMetadataEntity update(ItemMetadataEntity metadata) throws ItemNotFoundException;

	void delete(UUID id);
}

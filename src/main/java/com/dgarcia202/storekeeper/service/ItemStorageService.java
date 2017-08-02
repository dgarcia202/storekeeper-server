package com.dgarcia202.storekeeper.service;

import java.util.UUID;

import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;
import com.dgarcia202.storekeeper.exception.FileSystemOperationException;
import com.dgarcia202.storekeeper.exception.ItemNotFoundException;
import com.dgarcia202.storekeeper.exception.NoActiveVolumeException;
import com.dgarcia202.storekeeper.api.dto.UpdateItemRequestDto;

public interface ItemStorageService
{
	ItemMetadataEntity getMetadataById(UUID itemId);

	ItemMetadataEntity updateMetadata(UUID itemId, UpdateItemRequestDto dto) throws ItemNotFoundException;

	UUID store(byte[] bytes, String name, String mimeType)
			throws NoActiveVolumeException, FileSystemOperationException;

	void remove(UUID itemId) throws NoActiveVolumeException;

	byte[] retrieve(UUID itemId) throws NoActiveVolumeException, FileSystemOperationException;
}

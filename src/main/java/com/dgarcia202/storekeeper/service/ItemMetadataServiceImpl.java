package com.dgarcia202.storekeeper.service;

import java.util.UUID;

import com.dgarcia202.storekeeper.exception.ItemNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;
import com.dgarcia202.storekeeper.repository.ItemMetadataRepository;

@Service
public class ItemMetadataServiceImpl implements ItemMetadataService
{
	public final 	Logger logger = LoggerFactory.getLogger(ItemMetadataServiceImpl.class);

	private ItemMetadataRepository itemMetadataRepository;

	@Autowired
	public ItemMetadataServiceImpl(ItemMetadataRepository itemMetadataRepository)
	{
		this.itemMetadataRepository = itemMetadataRepository;
	}

	public ItemMetadataEntity getById(UUID id)
	{
		this.logger.info("returning metadata for item " + id.toString());
		return this.itemMetadataRepository.findOne(id);
	}

	public void add(ItemMetadataEntity metadata)
	{
		this.itemMetadataRepository.saveAndFlush(metadata);
		this.logger.info("Added " + metadata.getId() + " with name " + metadata.getFileName());
	}

	public ItemMetadataEntity update(ItemMetadataEntity metadata) throws ItemNotFoundException {
		ItemMetadataEntity persistedObject = this.itemMetadataRepository.findOne(metadata.getId());
		if (persistedObject == null)
		{
			throw new ItemNotFoundException("id " + metadata.getId() + " not found");
		}

		persistedObject.setMimeType(metadata.getMimeType());
		persistedObject.setFileName(metadata.getFileName());
		this.itemMetadataRepository.save(persistedObject);
		return persistedObject;
	}

	public void delete(UUID id) {
		this.itemMetadataRepository.delete(id);
		this.logger.info("Deleted item " + id);
    }
}

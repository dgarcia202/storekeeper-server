package com.dgarcia202.storekeeper.api.controller;

import java.io.IOException;
import java.util.UUID;

import com.dgarcia202.storekeeper.exception.FileSystemOperationException;
import com.dgarcia202.storekeeper.exception.ItemNotFoundException;
import com.dgarcia202.storekeeper.exception.NoActiveVolumeException;
import com.dgarcia202.storekeeper.api.dto.AddItemRequestDto;
import com.dgarcia202.storekeeper.api.dto.UpdateItemRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;
import com.dgarcia202.storekeeper.service.ItemStorageService;
import com.dgarcia202.storekeeper.api.dto.AddItemResponseDto;

@RestController
@RequestMapping("api/items")
public class ItemController
{
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemStorageService itemStorageService;

    @Autowired
    public ItemController(
        ItemStorageService itemStorageService)
    {
        this.itemStorageService = itemStorageService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<byte[]> getItem(@PathVariable UUID itemId)
            throws NoActiveVolumeException, FileSystemOperationException, ItemNotFoundException {
        logger.info("retrieve item with id " + itemId);
        ItemMetadataEntity metadata = this.itemStorageService.getMetadataById(itemId);
        if (metadata == null)
        {
            throw new ItemNotFoundException("item with id " + itemId + "not found");
        }

        byte[] contents = this.itemStorageService.retrieve(itemId);

        logger.info("item has '" + metadata.getFileName() + "' as name with type " + metadata.getMimeType() + " and its physical size is " + contents.length);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(metadata.getMimeType()));
        headers.setContentLength(contents.length);
        headers.setContentDispositionFormData(metadata.getFileName(), metadata.getFileName());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{itemId}/metadata")
    public ItemMetadataEntity getItemMetadata(@PathVariable UUID itemId) throws ItemNotFoundException {
        ItemMetadataEntity entity = this.itemStorageService.getMetadataById(itemId);
        if (entity == null)
        {
            throw new ItemNotFoundException("Item " + itemId + " not found in store");
        }
        return entity;
    }

    @PostMapping
    public AddItemResponseDto addItem(
        @RequestParam("metadata") MultipartFile metadata,
        @RequestParam("file") MultipartFile file)
        throws FileSystemOperationException, NoActiveVolumeException
    {
        try {
            String json = new String(metadata.getBytes());
            AddItemRequestDto dto = new ObjectMapper().readValue(json, AddItemRequestDto.class);
            logger.info("captured metadata object with name " + dto.getFileName());

            return new AddItemResponseDto(this.itemStorageService.store(file.getBytes(), dto.getFileName(), dto.getMimeType()));
        }
        catch(IOException e)
        {
            throw new FileSystemOperationException(e);
        }
    }

    @PutMapping("/{itemId}/metadata")
    ItemMetadataEntity updateItem(@PathVariable UUID itemId, @RequestBody UpdateItemRequestDto dto)
            throws ItemNotFoundException
    {
        return this.itemStorageService.updateMetadata(itemId, dto);
    }

    @DeleteMapping("/{itemId}")
    void deleteItem(@PathVariable UUID itemId) throws ItemNotFoundException, NoActiveVolumeException
    {
        ItemMetadataEntity entity = this.itemStorageService.getMetadataById(itemId);
        if (entity == null)
        {
            throw new ItemNotFoundException("Item " + itemId + " not found in store");
        }

        this.itemStorageService.remove(itemId);
    }
}

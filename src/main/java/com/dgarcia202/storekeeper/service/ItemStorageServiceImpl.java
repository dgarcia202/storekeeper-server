package com.dgarcia202.storekeeper.service;

import java.io.*;
import java.util.Date;
import java.util.UUID;
import java.util.Calendar;
import java.nio.file.Paths;
import java.nio.file.Path;

import com.dgarcia202.storekeeper.exception.ItemNotFoundException;
import com.dgarcia202.storekeeper.api.dto.UpdateItemRequestDto;
import org.apache.commons.io.IOUtils;
import com.dgarcia202.storekeeper.entity.VolumeEntity;
import com.dgarcia202.storekeeper.exception.FileSystemOperationException;
import com.dgarcia202.storekeeper.exception.NoActiveVolumeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgarcia202.storekeeper.entity.ItemMetadataEntity;

@Service
public class ItemStorageServiceImpl implements ItemStorageService
{
    public final Logger logger = LoggerFactory.getLogger(ItemStorageServiceImpl.class);

    private final ItemMetadataService itemMetadataService;

    private final VolumeService volumeService;

    @Autowired
    public ItemStorageServiceImpl(ItemMetadataService itemMetadataService, VolumeService volumeService)
    {
        this.itemMetadataService = itemMetadataService;
        this.volumeService = volumeService;
    }

    public ItemMetadataEntity getMetadataById(UUID itemId)
    {
        return this.itemMetadataService.getById(itemId);
    }

    public ItemMetadataEntity updateMetadata(UUID itemId, UpdateItemRequestDto dto)
            throws ItemNotFoundException
    {
        ItemMetadataEntity entity = new ItemMetadataEntity();
        entity.setId(itemId);
        entity.setFileName(dto.getFileName());
        entity.setMimeType(dto.getMymeType());
        return this.itemMetadataService.update(entity);
    }

    public UUID store(byte[] bytes, String name, String mimeType) throws NoActiveVolumeException, FileSystemOperationException
    {
        ItemMetadataEntity metadata = new ItemMetadataEntity();
        metadata.setId(UUID.randomUUID());
        metadata.setFileName(name);
        metadata.setMimeType(mimeType);
        this.logger.info("Id '" + metadata.getId() + "' assigned to new item");

        VolumeEntity volume = this.volumeService.getActive();
        if (volume == null)
        {
            throw new NoActiveVolumeException();
        }

        metadata.setVolume(volume);

        Path destination = this.inferFilePathFromId(metadata.getId(), volume.getPath());
        File destFile = new File(destination.toString());
        destFile.getParentFile().mkdirs();

        try
        {
            FileOutputStream fos = new FileOutputStream(destFile);
            fos.write(bytes);
            fos.close();
        }
        catch (IOException e)
        {
            throw new FileSystemOperationException(e);
        }

        metadata.setSize(bytes.length);
        metadata.setDateCreated(new Date());
        itemMetadataService.add(metadata);

        this.logger.info("File written to " + destination);
        return metadata.getId();
    }

    public void remove(UUID itemId) throws NoActiveVolumeException
    {
        File file = this.retrieveFile(itemId);
        file.delete();
        this.logger.info("File deleted");
        itemMetadataService.delete(itemId);
    }

    public byte[] retrieve(UUID itemId) throws NoActiveVolumeException, FileSystemOperationException {
        try
        {
            File file = this.retrieveFile(itemId);
            FileReader reader = new FileReader(file);
            return IOUtils.toByteArray(reader);
        }
        catch(IOException e)
        {
            throw new FileSystemOperationException(e);
        }
    }

    private File retrieveFile(UUID itemId) throws NoActiveVolumeException
    {
        VolumeEntity volume = this.volumeService.getActive();
        if (volume == null)
        {
            throw new NoActiveVolumeException();
        }

        return new File(this.inferFilePathFromId(itemId, volume.getPath()).toString());
    }

    private Path inferFilePathFromId(UUID fileId, String storageRoot)
    {
        Calendar rightNow = Calendar.getInstance();
        String partOne = String.format("%04d%02d", rightNow.get(Calendar.YEAR), rightNow.get(Calendar.MONTH));
        String partTwo = String.format("%02d", rightNow.get(Calendar.DAY_OF_MONTH));
        String partThree = String.format("%02d", rightNow.get(Calendar.HOUR));
        return Paths.get(storageRoot, partOne, partTwo, partThree, fileId.toString());
    }
}

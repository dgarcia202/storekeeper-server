package com.dgarcia202.storekeeper.service;

import com.dgarcia202.storekeeper.entity.VolumeEntity;
import com.dgarcia202.storekeeper.exception.NoActiveVolumeException;
import com.dgarcia202.storekeeper.repository.VolumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolumeServiceImpl implements VolumeService
{
    public static final Logger logger = LoggerFactory.getLogger(VolumeServiceImpl.class);

    private final VolumeRepository volumeRepository;

    @Autowired
    public VolumeServiceImpl(VolumeRepository volumeRepository) {
        this.volumeRepository = volumeRepository;
    }

    public VolumeEntity getActive() throws NoActiveVolumeException
    {
        VolumeEntity volume = this.volumeRepository.findByActive(true);
        if (volume == null)
        {
            throw new NoActiveVolumeException();
        }

        logger.info("found active volume at " + volume.getPath() + " (" + volume.getId() + ")");
        return volume;
    }

    public List<VolumeEntity> getAll()
    {
        return this.volumeRepository.findAll();
    }
}

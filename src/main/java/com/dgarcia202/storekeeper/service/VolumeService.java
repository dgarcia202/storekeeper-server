package com.dgarcia202.storekeeper.service;

import com.dgarcia202.storekeeper.entity.VolumeEntity;
import com.dgarcia202.storekeeper.exception.NoActiveVolumeException;

import java.util.List;

public interface VolumeService
{
    VolumeEntity getActive() throws NoActiveVolumeException;

    List<VolumeEntity> getAll();
}

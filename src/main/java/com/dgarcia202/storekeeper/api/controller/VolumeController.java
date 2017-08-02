package com.dgarcia202.storekeeper.api.controller;

import com.dgarcia202.storekeeper.entity.VolumeEntity;
import com.dgarcia202.storekeeper.exception.NoActiveVolumeException;
import com.dgarcia202.storekeeper.service.VolumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/volumes")
public class VolumeController
{
    private static final Logger logger = LoggerFactory.getLogger(VolumeController.class);

    private final VolumeService volumeService;

    @Autowired
    public VolumeController(VolumeService volumeService) {
        this.volumeService = volumeService;
    }

    @GetMapping
    public List<VolumeEntity> GetVolumes()
    {
        return this.volumeService.getAll();
    }

    @GetMapping("/active")
    public VolumeEntity GetActiveVolume() throws NoActiveVolumeException
    {
        return this.volumeService.getActive();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoActiveVolumeException.class)
    public void handleNotFound()
    {
        logger.warn("Resource not found");
    }
}

package com.dgarcia202.storekeeper.api.dto;

public class AddVolumeRequestDto
{
    private String path;

    private Boolean makeActive;

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public Boolean getMakeActive()
    {
        return makeActive;
    }

    public void setMakeActive(Boolean makeActive)
    {
        this.makeActive = makeActive;
    }
}

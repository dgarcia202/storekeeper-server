package com.dgarcia202.storekeeper.exception;

public class NoActiveVolumeException extends Exception
{
    public NoActiveVolumeException()
    {
        super("No active volume found. Add and activate a storage volume in order to be able to store items.");
    }

    public NoActiveVolumeException(String message)
    {
        super(message);
    }
}

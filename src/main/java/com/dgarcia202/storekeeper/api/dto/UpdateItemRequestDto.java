package com.dgarcia202.storekeeper.api.dto;

public class UpdateItemRequestDto
{
    private String fileName;

    private String mymeType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMymeType() {
        return mymeType;
    }

    public void setMymeType(String mymeType) {
        this.mymeType = mymeType;
    }
}

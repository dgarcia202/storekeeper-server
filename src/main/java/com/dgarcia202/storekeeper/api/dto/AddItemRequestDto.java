package com.dgarcia202.storekeeper.api.dto;

public class AddItemRequestDto
{
  private String fileName;

  private String mimeType;
  
  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }

  public String getFileName()
  {
    return this.fileName;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }
}
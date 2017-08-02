package com.dgarcia202.storekeeper.api.dto;

import java.util.UUID;

public class AddItemResponseDto
{
  private UUID id;

  public AddItemResponseDto(UUID id)
  {
    this.id = id;
  }

  public void setId(UUID id)
  {
    this.id = id;
  }

  public UUID getId()
  {
    return this.id;
  }
}

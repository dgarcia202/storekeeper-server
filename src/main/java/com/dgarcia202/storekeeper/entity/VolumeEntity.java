package com.dgarcia202.storekeeper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "VOLUME")
public class VolumeEntity
{
    @Id
    @Column(name="ID")
    private UUID id;

    @Column(name="PATH", length = 1024)
    @NotNull
    private String path;

    @Column(name="ACTIVE")
    @NotNull
    private Boolean active;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

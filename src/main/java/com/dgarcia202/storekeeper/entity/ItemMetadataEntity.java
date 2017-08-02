package com.dgarcia202.storekeeper.entity;

import java.util.Date;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ITEM_METADATA")
public class ItemMetadataEntity
{
	@Id
	@Column(name="ID")
	private UUID id;

	@Column(name="FILE_NAME", length = 1024)
	@NotNull
	private String fileName;

	@Column(name="MIME_TYPE", length = 512)
	private String mimeType;

	@Column(name="FILE_SIZE")
	@NotNull
	private long size;

	@ManyToOne(optional = false)
	@JoinColumn(
			name = "VOLUME_ID",
			referencedColumnName = "ID",
			foreignKey = @ForeignKey(name = "ITEM_VOLUME_FK"))
	private VolumeEntity volume;

	@Column(name="DT_CREATED")
	@NotNull
	private Date dateCreated;

	@Column(name="DT_MODIFIED")
	private Date dateModified;

	public UUID getId()
	{
		return this.id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public String getFileName()
	{
		return this.fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public long getSize()
	{
		return this.size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	public Date getDateCreated()
	{
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated)
	{
		this.dateCreated = dateCreated;
	}

	public Date getDateModified()
	{
		return this.dateModified;
	}

	public void setDateModified(Date dateModified)
	{
		this.dateModified = dateModified;
	}

	public VolumeEntity getVolume() {
		return volume;
	}

	public void setVolume(VolumeEntity volume) {
		this.volume = volume;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}

package com.mavrick.persistance.entities;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="MVK_CAT_MEDIA")
public class Media implements MavrickEntity {

	@TableGenerator(name="MEDIA_GENERATOR",
		    table="GENERATED_KEYS",
		    pkColumnName="PK_COLUMN",
		    valueColumnName="VALUE_COLUMN",
		    pkColumnValue="MEDIA_ID",
		    allocationSize=100
			)
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator="MEDIA_GENERATOR")
	@Column(name="MEDIA_ID")
	private String mediaId;
	
	@NotEmpty
	@Column(name = "SEQ_NBR")
	private int sequenceNum;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="MEDIA_TYPE")
	private String mediaType;
	
	@Column(name="MEDIA_CONTENT")
	private Blob mediaContent;
	
	@Column(name="CREATED_DATE_TIME")
	private Date createDate;
	
	@Column(name="UPDATED_DATE_TIME")
	private Date updatedDate;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public int getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(int sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public Blob getMediaContent() {
		return mediaContent;
	}

	public void setMediaContent(Blob mediaContent) {
		this.mediaContent = mediaContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}

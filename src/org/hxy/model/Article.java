package org.hxy.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.javass.commons.mvc.util.JsonDateSerializer;

@Entity
@Table(name = "vi_Article")
@IdClass(ArticleID.class)
public class Article  implements Serializable{
	/**
	 * 主键
	 */
	@Id
	private Integer id;
	@Column(name = "Title", length = 255)
	private String title;
	@Column(name = "PostTime")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Timestamp postTime;
	@Column(name="ProvinceID")
	private Integer proviceId;
	@Column(name="SubjectID")
	private Integer subjectId;
	@Column(name="UpFilePath")
	private String filePath;
	@Column(name="UploadFiles")
	private String fileName;
	@Column(name="dsk")
	private int dsk;
	public int getDsk() {
		return dsk;
	}
	public void setDsk(int dsk) {
		this.dsk = dsk;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	@Column(name="attach")
	private String attach;
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getProviceId() {
		return proviceId;
	}
	public void setProviceId(Integer proviceId) {
		this.proviceId = proviceId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getPostTime() {
		return postTime;
	}
	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

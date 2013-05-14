package org.hxy.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.javass.commons.mvc.util.JsonDateSerializer;

@Entity
@Table(name = "T_Article")
public class Article  implements Serializable{
	/**
	 * 主键
	 */
	@Id
	@Column(name="ArticleID",length=19)
	private Integer id;
	@Column(name = "Title", length = 255)
	private String title;
	@Column(name = "PostTime")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Timestamp postTime;
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

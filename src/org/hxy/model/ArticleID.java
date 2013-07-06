package org.hxy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArticleID implements Serializable{
	@Column(name="ArticleID",length=19)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
	   final int PRIME = 31;
	   int result = 1;
	   result = PRIME * result + ((id == null) ? 0 : id.hashCode());
	   return result;
	}
	@Override
	public boolean equals(Object obj) {
	   if (this == obj)
	    return true;
	   if (obj == null)
	    return false;
	   if (getClass() != obj.getClass())
	    return false;
	   final ArticleID other = (ArticleID) obj;
	   if (id == null) {
	    if (other.id != null)
	     return false;
	   } else if (!id.equals(other.id))
	    return false;
	   return true;
	}
}

package org.hxy.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "QUE_XML_DATA03")
public class QuestionXMLData03 implements Serializable {
	private static final long serialVersionUID = 1762136433648714876L;
	@Id     
	@Column(name = "PK_QUESTION")
	private Integer questionId;     //试题ID
	
	@Column(name = "questionBody")
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String zquestionBody; //题文
	
	@Column(name = "ANSWER")
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String zanswer;       //答案
	
	@Column(name = "ANALYSIS")
	@Lob
	@Basic(fetch=FetchType.LAZY)
	private String zanalysis;     //解析

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((questionId == null) ? 0 : questionId.hashCode());
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
		final QuestionXMLData03 other = (QuestionXMLData03) obj;
		if (questionId == null) {
			if (other.questionId != null)
				return false;
		} else if (!questionId.equals(other.questionId))
			return false;
		return true;
	}
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getZquestionBody() {
		return zquestionBody;
	}
	public void setZquestionBody(String zquestionBody) {
		this.zquestionBody = zquestionBody;
	}
	public String getZanswer() {
		return zanswer;
	}
	public void setZanswer(String zanswer) {
		this.zanswer = zanswer;
	}
	public String getZanalysis() {
		return zanalysis;
	}
	public void setZanalysis(String zanalysis) {
		this.zanalysis = zanalysis;
	}
}

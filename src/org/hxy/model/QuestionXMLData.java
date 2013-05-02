package org.hxy.model;

import java.io.Serializable;

public class QuestionXMLData implements Serializable {
	private static final long serialVersionUID = -356916465400217258L;
	private Integer questionId;     //试题ID
	private String zquestionBody; //题文
	private String zanswer;       //答案
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
		final QuestionXMLData other = (QuestionXMLData) obj;
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

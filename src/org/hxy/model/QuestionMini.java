package org.hxy.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.javass.commons.mvc.util.JsonDateSerializer;


public class QuestionMini implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String answerText;
	private String bodyText;
	private Integer disciplineId;
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date regTime;
	private String queSoruce;
	private String knowledgeName;
	private String topicName;
	private Integer searchNum;
	
	public Integer getSearchNum() {
		return searchNum;
	}

	public void setSearchNum(Integer searchNum) {
		this.searchNum = searchNum;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}


    public QuestionMini(Integer id, String answerText, String bodyText,
			Integer disciplineId, Date regTime, String queSoruce,
			String knowledgeName, String topicName) {
		this.id = id;
		this.answerText = answerText;
		this.bodyText = bodyText;
		this.disciplineId = disciplineId;
		this.regTime = regTime;
		this.queSoruce = queSoruce;
		this.knowledgeName = knowledgeName;
		this.topicName = topicName;
	}

	public QuestionMini(Integer id, String answerText, String bodyText,
			Integer disciplineId, Date regTime, String queSoruce,
			String knowledgeName, String topicName, Integer searchNum) {
		super();
		this.id = id;
		this.answerText = answerText;
		this.bodyText = bodyText;
		this.disciplineId = disciplineId;
		this.regTime = regTime;
		this.queSoruce = queSoruce;
		this.knowledgeName = knowledgeName;
		this.topicName = topicName;
		this.searchNum = searchNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public Integer getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(Integer disciplineId) {
		this.disciplineId = disciplineId;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getQueSoruce() {
		return queSoruce;
	}

	public void setQueSoruce(String queSoruce) {
		this.queSoruce = queSoruce;
	}

	@Override
    public int hashCode() {
        final Integer prime = 31;
        Integer result = 1;
        result = prime * result + id;
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
        QuestionMini other = (QuestionMini) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

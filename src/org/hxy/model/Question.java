package org.hxy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import cn.javass.commons.mvc.util.JsonDateSerializer;

@Entity
@Table(name="QUESTION")
public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="PK_QUESTION",length=19)
	private Integer id;
	@Column(name="ANALYSIS_ADDER",length=19)
	private Integer analysisAddr;
	@Column(name="ANSWER_TEXT",length=4000)
	private String answerText;
	@Column(name="BODY_TEXT",length=4000)
	private String bodyText;

	@Column(name="CHECK_TIME")
	private Date checkDate;
	@Column(name="CHECKOR_ID",length=19)
	private Integer checkorId;
	@Column(name="DIFFICULTY",length=255)
	private String difficulty;
	@Column(name="PK_DISCIPLINE",length=19)
	private Integer disciplineId;
	@Column(name="FAILREASON",length=255)
	private String failReason;
	@Column(name="ISCHECKED",length=255)
	private String isChecked;
	@Column(name="ISCORRECTED",length=255)
	private String isCorrected;
	@Column(name="POINTS_CHECK")
	private float pointsCheck;
	@Column(name="POINTS_GET")
	private float pointsGet;
	@Column(name="PK_QUE_TYPE",length=19)
	private Integer queType;
	
	@Column(name="REG_TIME")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date regTime;
	@Column(name="REGISTRANT",length=19)
	private Integer  registrant;
	@Column(name="REGISTRANTNAME",length=255)
	private String  registrantName;
	@Column(name="REGISTRANTTYPE",length=255)
	private String  registrantType;
	@Column(name="REMARK",length=255)
	private String  remark;
	@Column(name="RESOURSE_TYPE",length=255)
	private String resourseType;
	@Column(name="STATUS",length=255)
	private String status;
	@Column(name="SYNTHESIS_TYPE",length=255)
	private String synthesisType;
	@Column(name="UPDATE_TIME")
	private Date updateTime;
	@Column(name="WORKS_GET")
	private float worksGet;
	@Column(name="HASNNHANDERINFO",length=255)
	private String hasnnhanderInfo;
	@Column(name="HASCORRECTED",length=19)
	private Integer  hascorrected; 
	@Column(name="WORKS_GET2")
	private float  worksGet2;
	@Column(name="JIUCUO_TIME")
	private Date jiucuoTime;
	@Column(name="ANA_ADD_TIME")
	private Date andAddTime;
	@Column(name="REAL_TIME")
	private Date realTime;
	@Column(name="KNOWLEDGE_NAME",length=255)
	private String knowledgeName;
	@Column(name="QUETYPE_NAME",length=255)
	private String queTypeName ;
	@Column(name="TOPIC_NAME",length=255)
	private String topicName ; 
	@Column(name="CHECKOR_NAME",length=255)
	private String checkorName; 
	@Column(name="ANA_CHECKOR",length=19)
	private Integer anaCheckor;
	@Column(name="INVALID_TYPE",length=255)
	private String invalidType;
	@Column(name="POINTS_CHECK2")
	private float pointsCheck2;
	@Column(name="POINTS_GET2")
	private float pointsGet2; 
	@Column(name="ANA_TEXT_LEN",length=19)
	private Integer anaTextLen;
	@Column(name="PK_KNOWLEDGE2",length=19)
	private Integer knowledge2;
	@Column(name="PK_KNOWLEDGE1",length=19)
	private Integer knowledge1;
	@Column(name="PK_KNOWLEDGE",length=19)
	private Integer knowledge;
	@Column(name="ANALYSIS_TEXT",length=255)
	private String analysisText;
	@Column(name="PK_BOOK",length=19,nullable=true)
	private Integer bookId;
	@Column(name="BOOK_NAME",length=255)
	private String bookName;
	@Column(name="PK_CHAPTER",length=19)
	private Integer chapterId;
	@Column(name="CHAPTER_NAME",length=255)
	private String chapterName;
	@Column(name="ISFREE",length=255)
	private String isFree;
	@Column(name="IS_SYNTHESIS_TYPE",length=255)
	private String isSynthesisType;
	@Column(name="PK_PARAGRAPH",length=19)
	private Integer paragraphId;
	@Column(name="PARAGRAPH_NAME",length=255)
	private String paragraphName;
	@Column(name="PK_QUE_BANK",length=19)
	private Integer queBankId;
	@Column(name="PK_TYPE_CLASS",length=19)
	private Integer typeClassId ;
	@Column(name="QUESTION_NO",length=19)
	private Integer questionNo;
	@Column(name="RESOURSE",length=255)
	private String resourse;
	@Column(name="CLASS_NAME",length=255)
	private String className;
	@Column(name="PK_VERSION",length=19)
	private Integer versionId;
	@Column(name="VERSION_NAME",length=255)
	private String versionName;
	@Column(name="QUESORUCE",length=255)
	private String queSoruce;
	@Column(name="SEARCH_NUM",length=19)
	private Integer searchNum;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAnalysisAddr() {
		return analysisAddr;
	}
	public void setAnalysisAddr(Integer analysisAddr) {
		this.analysisAddr = analysisAddr;
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
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getCheckorId() {
		return checkorId;
	}
	public void setCheckorId(Integer checkorId) {
		this.checkorId = checkorId;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public Integer getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(Integer disciplineId) {
		this.disciplineId = disciplineId;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
	public String getIsCorrected() {
		return isCorrected;
	}
	public void setIsCorrected(String isCorrected) {
		this.isCorrected = isCorrected;
	}
	public float getPointsCheck() {
		return pointsCheck;
	}
	public void setPointsCheck(float pointsCheck) {
		this.pointsCheck = pointsCheck;
	}
	public float getPointsGet() {
		return pointsGet;
	}
	public void setPointsGet(float pointsGet) {
		this.pointsGet = pointsGet;
	}
	public Integer getQueType() {
		return queType;
	}
	public void setQueType(Integer queType) {
		this.queType = queType;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public Integer getRegistrant() {
		return registrant;
	}
	public void setRegistrant(Integer registrant) {
		this.registrant = registrant;
	}
	public String getRegistrantName() {
		return registrantName;
	}
	public void setRegistrantName(String registrantName) {
		this.registrantName = registrantName;
	}
	public String getRegistrantType() {
		return registrantType;
	}
	public void setRegistrantType(String registrantType) {
		this.registrantType = registrantType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResourseType() {
		return resourseType;
	}
	public void setResourseType(String resourseType) {
		this.resourseType = resourseType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSynthesisType() {
		return synthesisType;
	}
	public void setSynthesisType(String synthesisType) {
		this.synthesisType = synthesisType;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public float getWorksGet() {
		return worksGet;
	}
	public void setWorksGet(float worksGet) {
		this.worksGet = worksGet;
	}
	public String getHasnnhanderInfo() {
		return hasnnhanderInfo;
	}
	public void setHasnnhanderInfo(String hasnnhanderInfo) {
		this.hasnnhanderInfo = hasnnhanderInfo;
	}
	public Integer getHascorrected() {
		return hascorrected;
	}
	public void setHascorrected(Integer hascorrected) {
		this.hascorrected = hascorrected;
	}
	public float getWorksGet2() {
		return worksGet2;
	}
	public void setWorksGet2(float worksGet2) {
		this.worksGet2 = worksGet2;
	}
	public Date getJiucuoTime() {
		return jiucuoTime;
	}
	public void setJiucuoTime(Date jiucuoTime) {
		this.jiucuoTime = jiucuoTime;
	}
	public Date getAndAddTime() {
		return andAddTime;
	}
	public void setAndAddTime(Date andAddTime) {
		this.andAddTime = andAddTime;
	}
	public Date getRealTime() {
		return realTime;
	}
	public void setRealTime(Date realTime) {
		this.realTime = realTime;
	}
	public String getKnowledgeName() {
		return knowledgeName;
	}
	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}
	public String getQueTypeName() {
		return queTypeName;
	}
	public void setQueTypeName(String queTypeName) {
		this.queTypeName = queTypeName;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getCheckorName() {
		return checkorName;
	}
	public void setCheckorName(String checkorName) {
		this.checkorName = checkorName;
	}
	public Integer getAnaCheckor() {
		return anaCheckor;
	}
	public void setAnaCheckor(Integer anaCheckor) {
		this.anaCheckor = anaCheckor;
	}
	public String getInvalidType() {
		return invalidType;
	}
	public void setInvalidType(String invalidType) {
		this.invalidType = invalidType;
	}

	public Integer getAnaTextLen() {
		return anaTextLen;
	}
	public void setAnaTextLen(Integer anaTextLen) {
		this.anaTextLen = anaTextLen;
	}
	public Integer getKnowledge2() {
		return knowledge2;
	}
	public void setKnowledge2(Integer knowledge2) {
		this.knowledge2 = knowledge2;
	}
	public Integer getKnowledge1() {
		return knowledge1;
	}
	public void setKnowledge1(Integer knowledge1) {
		this.knowledge1 = knowledge1;
	}
	public Integer getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(Integer knowledge) {
		this.knowledge = knowledge;
	}
	public String getAnalysisText() {
		return analysisText;
	}
	public void setAnalysisText(String analysisText) {
		this.analysisText = analysisText;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getChapterId() {
		return chapterId;
	}
	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getIsFree() {
		return isFree;
	}
	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	public String getIsSynthesisType() {
		return isSynthesisType;
	}
	public void setIsSynthesisType(String isSynthesisType) {
		this.isSynthesisType = isSynthesisType;
	}
	public Integer getParagraphId() {
		return paragraphId;
	}
	public void setParagraphId(Integer paragraphId) {
		this.paragraphId = paragraphId;
	}
	public String getParagraphName() {
		return paragraphName;
	}
	public void setParagraphName(String paragraphName) {
		this.paragraphName = paragraphName;
	}
	public Integer getQueBankId() {
		return queBankId;
	}
	public void setQueBankId(Integer queBankId) {
		this.queBankId = queBankId;
	}
	public Integer getTypeClassId() {
		return typeClassId;
	}
	public void setTypeClassId(Integer typeClassId) {
		this.typeClassId = typeClassId;
	}
	public Integer getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}
	public String getResourse() {
		return resourse;
	}
	public void setResourse(String resourse) {
		this.resourse = resourse;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public Integer getVersionId() {
		return versionId;
	}
	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public String getQueSoruce() {
		return queSoruce;
	}
	public void setQueSoruce(String queSoruce) {
		this.queSoruce = queSoruce;
	}
	public Integer getSearchNum() {
		return searchNum;
	}
	public void setSearchNum(Integer searchNum) {
		this.searchNum = searchNum;
	}
	public float getPointsCheck2() {
		return pointsCheck2;
	}
	public void setPointsCheck2(float pointsCheck2) {
		this.pointsCheck2 = pointsCheck2;
	}
	public float getPointsGet2() {
		return pointsGet2;
	}
	public void setPointsGet2(float pointsGet2) {
		this.pointsGet2 = pointsGet2;
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
        Question other = (Question) obj;
        if (id != other.id)
            return false;
        return true;
    }
}

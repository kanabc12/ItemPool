package org.hxy.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 试题实例
 * 
 * @author kanabc12@126.com
 * 
 */
@Entity
@Table(name = "DISCIPLINE")
public class Discipline implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@Column(name="PK_DISCIPLINE",length=19)
	private Integer id;
	@Column(name = "CODE", length = 255)
	private String code;
	@Column(name = "NAME", length = 255)
	private String name;
	@Column(name = "REMARK", length = 255)
	private String remark;
	@Column(name = "QUE_AMOUNT", length = 19)
	private Integer amountId;
	@Column(name = "PK_QUESTION", length = 19)
	private BigInteger questionId;
	@Column(name = "NEW_QUE_AMOUNT", length = 19)
	private Integer newQueAmount;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAmountId() {
		return amountId;
	}

	public void setAmountId(Integer amountId) {
		this.amountId = amountId;
	}

	public BigInteger getQuestionId() {
		return questionId;
	}

	public void setQuestionId(BigInteger questionId) {
		this.questionId = questionId;
	}

	public Integer getNewQueAmount() {
		return newQueAmount;
	}

	public void setNewQueAmount(Integer newQueAmount) {
		this.newQueAmount = newQueAmount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Discipline other = (Discipline) obj;
		if (id != other.id)
			return false;
		return true;
	}

}

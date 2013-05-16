package org.hxy.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import cn.javass.commons.mvc.util.JsonDateSerializer;

@Table(name="SA_USER")
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="USER_ID",length=40)
	@SequenceGenerator(name="user_seq",sequenceName="SEQ_USER_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="user_seq")
	private String userId;
	@Column(name="USER_NAME",length=40)
	private String userName;
	@Column(name="PASSWORD",length=60)
	private String password;
	@Column(name="REAL_NAME",length=200)
	private String realName;
	@Column(name="IS_LOCK",length=1)
	private String isLock;
	@Column(name="IS_DELETE",length=1)
	private String isDeleted;
	@Column(name="TELEPHONE",length=18)
	private String telephone;
	@Column(name="PASSWORD_UPDATE_TIME")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date pwdUpTime;
	@Column(name="LOCK_TIME")
	@JsonSerialize(using=JsonDateSerializer.class)
	private String lockTime;
	@Column(name="ATTR1",length=100)
	private String attr1;
	@Column(name="ATTR2",length=100)
	private String attr2;
	@Column(name="ATTR3",length=100)
	private String attr3;
	@Column(name="ATTR4",length=100)
	private String attr4;
	@Column(name="MAIL",length=100)
	private String mail;
	@Column(name="GENDER",length=1)
	private String gender;
	@Column(name="USER_IP",length=18)
	private String userIP;
	@Column(name="IS_MANAGER",length=1)
	private String isManager;
	@Column(name="USER_TYPE",length=1)
	private String userType;
	@Column(name="HIS_PASSWORD1",length=60)
	private String hisPwd1;
	@Column(name="HIS_PASSWORD2",length=60)
	private String hisPwd2;
	@Column(name="HIS_PASSWORD3",length=60)
	private String hisPwd3;
	@Column(name="HIS_PASSWORD4",length=60)
	private String hisPwd4;
	@Column(name="HIS_PASSWORD5",length=60)
	private String hisPwd5;
	@Column(name="HIS_PASSWORD6",length=60)
	private String hisPwd6;
	@Column(name="CREATE_TIME")
	@JsonSerialize(using=JsonDateSerializer.class)
	private Date createTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getPwdUpTime() {
		return pwdUpTime;
	}
	public void setPwdUpTime(Date pwdUpTime) {
		this.pwdUpTime = pwdUpTime;
	}
	public String getLockTime() {
		return lockTime;
	}
	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}
	public String getAttr4() {
		return attr4;
	}
	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public String getIsManager() {
		return isManager;
	}
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getHisPwd1() {
		return hisPwd1;
	}
	public void setHisPwd1(String hisPwd1) {
		this.hisPwd1 = hisPwd1;
	}
	public String getHisPwd2() {
		return hisPwd2;
	}
	public void setHisPwd2(String hisPwd2) {
		this.hisPwd2 = hisPwd2;
	}
	public String getHisPwd3() {
		return hisPwd3;
	}
	public void setHisPwd3(String hisPwd3) {
		this.hisPwd3 = hisPwd3;
	}
	public String getHisPwd4() {
		return hisPwd4;
	}
	public void setHisPwd4(String hisPwd4) {
		this.hisPwd4 = hisPwd4;
	}
	public String getHisPwd5() {
		return hisPwd5;
	}
	public void setHisPwd5(String hisPwd5) {
		this.hisPwd5 = hisPwd5;
	}
	public String getHisPwd6() {
		return hisPwd6;
	}
	public void setHisPwd6(String hisPwd6) {
		this.hisPwd6 = hisPwd6;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId != other.userId)
            return false;
        return true;
    }
	
}

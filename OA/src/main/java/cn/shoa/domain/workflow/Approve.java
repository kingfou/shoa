package cn.shoa.domain.workflow;

import java.io.Serializable;
import java.util.Date;

public class Approve implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long aid;
	private String approvename;
	private Date approvetime; 
	private String isapprove;//是否通过
	
	
	public Long getAid() {
		return aid;
	}


	public void setAid(Long aid) {
		this.aid = aid;
	}


	public String getApprovename() {
		return approvename;
	}


	public void setApprovename(String approvename) {
		this.approvename = approvename;
	}


	public Date getApprovetime() {
		return approvetime;
	}


	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}


	public String getIsapprove() {
		return isapprove;
	}


	public void setIsapprove(String isapprove) {
		this.isapprove = isapprove;
	}


	public Form getForm() {
		return form;
	}


	public void setForm(Form form) {
		this.form = form;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	//申请人的表单
	private Form form;

}

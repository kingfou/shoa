package cn.shoa.domain.workflow;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Form implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long fid;//表达id
	private String applicator;//表单申请人
	private String title;//表单标题
	private Date applicatetime;
	private String satate;
	private String url;//表单所在链接
	
	private Set<Approve> approves;
	private FormTemplate formTemplate;
	
	public FormTemplate getFormTemplate() {
		return formTemplate;
	}
	public void setFormTemplate(FormTemplate formTemplate) {
		this.formTemplate = formTemplate;
	}
	public Set<Approve> getApproves() {
		return approves;
	}
	public void setApproves(Set<Approve> approves) {
		this.approves = approves;
	}
	private String piid;
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public String getApplicator() {
		return applicator;
	}
	public void setApplicator(String applicator) {
		this.applicator = applicator;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getApplicatetime() {
		return applicatetime;
	}
	public void setApplicatetime(Date applicatetime) {
		this.applicatetime = applicatetime;
	}
	public String getSatate() {
		return satate;
	}
	public void setSatate(String satate) {
		this.satate = satate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}

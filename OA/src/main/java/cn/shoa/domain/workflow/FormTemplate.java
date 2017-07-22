package cn.shoa.domain.workflow;

import java.io.Serializable;
import java.util.Set;

public class FormTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//流程模板的生成
	private Long ftid;
	private String name;
	private String processkey;//流程key
	private String url;//模板下载地址
	
	private Set<Form> forms;

	public Long getFtid() {
		return ftid;
	}

	public void setFtid(Long ftid) {
		this.ftid = ftid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcesskey() {
		return processkey;
	}

	public void setProcesskey(String processkey) {
		this.processkey = processkey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Form> getForms() {
		return forms;
	}

	public void setForms(Set<Form> forms) {
		this.forms = forms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
}

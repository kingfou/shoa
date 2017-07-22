package cn.shoa.domain.menu;

public class Menuitem {
	private Long mid;
	private Long pid;
	private String name;
	private boolean parentis;
	public boolean isParentis() {
		return parentis;
	}
	public void setParentis(boolean parentis) {
		this.parentis = parentis;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String icon;
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}

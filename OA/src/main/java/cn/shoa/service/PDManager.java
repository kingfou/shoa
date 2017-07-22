package cn.shoa.service;

import java.util.Collection;

import org.jbpm.api.ProcessDefinition;

public interface PDManager {
	//获取最新版本的流程定义
	public Collection<ProcessDefinition> getLastProcessDefinitions();

}

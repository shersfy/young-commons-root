package org.young.commons.bui;

import java.util.ArrayList;
import java.util.List;

import org.young.commons.protocols.BaseProtocol;

public class BuiReleaseLog extends BaseProtocol {

	private static final long serialVersionUID = 1L;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 表单
	 */
	private List<BuiFormMeta> forms;
	
	public BuiReleaseLog() {
		super();
		this.forms = new ArrayList<>();
	}
	
	public BuiReleaseLog(Long tenantId, String projectName) {
		this();
		setTenantId(tenantId);
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<BuiFormMeta> getForms() {
		return forms;
	}

	public void setForms(List<BuiFormMeta> forms) {
		this.forms = forms;
	}


}

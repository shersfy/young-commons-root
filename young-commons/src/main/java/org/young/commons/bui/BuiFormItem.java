package org.young.commons.bui;

import org.young.commons.beans.BaseBean;

public class BuiFormItem extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 表单项ID
	 */
	private String id;
	
	/**
	 * 表单项标签
	 */
	private String label;
	
	/**
	 * 表单项参数名
	 */
	private String name;
	
	/**
	 * 表单项类型
	 */
	private String type;
	
	/**
	 * 表单项隐藏属性名称
	 */
	private String hidden;

	/**
	 * 表单项禁用属性名称
	 */
	private String disabled;

	/**
	 * 默认值
	 */
	private String defvalue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getDefvalue() {
		return defvalue;
	}

	public void setDefvalue(String defvalue) {
		this.defvalue = defvalue;
	}

}

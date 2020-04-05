package org.young.commons.bui;

import java.util.ArrayList;
import java.util.List;

import org.young.commons.beans.BaseBean;

public class BuiFormMeta extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 表单名称
	 */
	private String name;
	
	/**
	 * 表单访问路径
	 */
	private String url;
	
	/**
	 * 表单项
	 */
	private List<BuiFormItem> items;
	

	public BuiFormMeta() {
		super();
		this.items = new ArrayList<>();
	}
	
	public BuiFormMeta(String name, String url) {
		this();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<BuiFormItem> getItems() {
		return items;
	}

	public void setItems(List<BuiFormItem> items) {
		this.items = items;
	}
	
}

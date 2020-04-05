package org.young.commons.meta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据行
 * 
 */
public class RowData extends Data{

	// 一行数据的各个字段
	private List<FieldData> fields;

	public RowData() {
		fields =  new ArrayList<FieldData>();
	}

	public RowData(List<FieldData> fields) {
		this.fields = fields;
	}
	
	public RowData(FieldData[] objects) {
		fields = new ArrayList<FieldData>();
		for (FieldData object : objects) {
			fields.add(object);
		}
	}

	public List<FieldData> getFields() {
		return fields;
	}

	public void setFields(List<FieldData> fields) {
		this.fields = fields;
	}
	
	/**
	 * 行转map, key为字段名
	 * 
	 * @param headers
	 *            字段名
	 * @param row
	 *            行数据
	 * @return Map<String, Object>
	 */
	public Map<String, Object> rowToMap(List<ColumnMeta> headers, RowData row) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (headers != null && row != null) {
			List<FieldData> values = row.getFields();
			for (int i = 0; i < headers.size(); i++) {
				map.put(headers.get(i).getName(), values.get(i));
			}
		}
		return map;
	}
	
	
	public String toString(String separator) {
	    separator = separator==null?"":separator;
	    
	    StringBuilder buff = new StringBuilder();
	    for(int i=0; i<fields.size(); i++) {
	        buff.append(fields.get(i).getValue());
	        if(i==fields.size()-1) {
	            break;
	        }
	        buff.append(separator);
	    }
	    
	    return buff.toString();
	}


}

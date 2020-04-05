package org.young.commons.meta;

/**
 * excel 行信息
 */
public class RowMeta extends Data {
	
	private int index;
	private int columnSize;
	private RowData data;
	
	public RowMeta(){}
	public RowMeta(int index){
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	public int getColumnSize() {
		return columnSize;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setColumnSize(int columnSize) {
		this.columnSize = columnSize;
	}
	public RowData getData() {
		return data;
	}
	public void setData(RowData data) {
		this.data = data;
	}
	
}

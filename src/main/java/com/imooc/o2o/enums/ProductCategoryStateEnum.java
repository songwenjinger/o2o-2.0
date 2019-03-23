package com.imooc.o2o.enums;

public enum ProductCategoryStateEnum {
	NULL_SHOP(-2001, "Shop信息为空"), EMPETY_LIST(-2002, "请输入商品目录信息"), DELETE_ERROR(-2003, "商品类别删除失败"), EDIT_ERROR(-2004, "商品类别编辑失败"), SUCCESS(0, "操作成功");

	private int state;

	private String stateInfo;

	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ProductCategoryStateEnum stateOf(int index) {
		for (ProductCategoryStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}

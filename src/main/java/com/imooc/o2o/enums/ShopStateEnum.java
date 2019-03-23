package com.imooc.o2o.enums;

public enum  ShopStateEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"非法商铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOPID(-1002,"shopID为空"),
    NULL_SHOP(-1003,"shop信息为空");
    private int state;
    private String stateInfo;
    //构造器设置成私有的，是为了不让第三方的私有的程序来改变
    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }
    public String getStateInfo() {
        return stateInfo;
    }
    public static ShopStateEnum stateOf(int state)
    {
        for(ShopStateEnum stateEnum:values())
        {
            if(stateEnum.getState()==state)
            {
                return stateEnum;
            }
        }
        return null;
    }
}

package com.jkoss.util;



public class Constant {
	//放入session中用户信息的常量名
	public static final String SESSION_USER_KEY = "lgnWorker";
	
	//存放卡位查询条件用于翻页时区域的ID
    public static final String SESSION_AID_KEY = "aId";
    
    //存放客户查询条件用于分页
    public static final String SESSION_SELCSTM_KEY="selCustomerEx";
    
    //存放订单分页条件
    public static final String SESSION_SELENCOLDREG_KEY="selColdReg";
    
    //存放入库计划分页条件
    public static final String SESSION_SELENCOLDTIME_KEY="selEnCold";
    
   //存放出库计划分页条件
    public static final String SESSION_SELOUTCOLD_KEY="selOutCold";
    
    //存放调度计划分页条件
    public static final String SESSION_SELDDCOLD_KEY="selDdCold";
    
    //存放财务分页条件
    public static final String SESSION_SELFINANCEREG_KEY="selfinanceReg";
}

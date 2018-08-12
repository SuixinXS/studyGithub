package com.jkoss.biz;

import java.util.List;

import com.jkoss.pojo.Areainfo;
import com.jkoss.pojo.Cabin;
import com.jkoss.pojo.Depot;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.DepotEx;
import com.jkoss.tool.Page;

public interface IFreezerBiz {
	List<Areainfo> lsArea();
	int updateByPrimaryKeySelective(Areainfo record);
	int insertSelective(Areainfo record);
	int deleteByPrimaryKey(Integer area_id);
	Areainfo selectByPrimaryKey(Integer area_id);
	Areainfo selAreaByName(String area_name);
	
	String insertSelective(Cabin record);
    List<Cabin>lsCabin(Page<Cabin>page);
   
    String deleteCabinByPrimaryKey(Integer cab_id);
    Cabin selectCabinByPrimaryKey(Integer cab_id);
    String updateByPrimaryKeySelective(Cabin record);
    
    String insertSelective(Depot record);
    List<DepotEx>lsDepot(Page<DepotEx> page);
    
    List<Cabin>lsCabinByAid(Integer area_id);
    String deleteDepotByPrimaryKey(Integer dep_id);
    Depot selDepotByDid(Integer did);
    List<Cabin>lsAll();
    String updateByPrimaryKeySelective(Depot record);
    
    //带仓库的卡位
    List<Cabin>selAllCabADep(Page<Cabin>page);
    
    List<Depot>lsDepotCid2();
    

}

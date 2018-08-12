package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jkoss.pojo.Cabin;
import com.jkoss.pojo.Depot;
import com.jkoss.pojo.vo.DepotEx;
import com.jkoss.tool.Page;

public interface DepotMapper {
    int deleteByPrimaryKey(Integer dep_id);

	int insert(Depot record);

	int insertSelective(Depot record);

	Depot selectByPrimaryKey(Integer dep_id);

	int updateByPrimaryKeySelective(Depot record);

	int updateByPrimaryKey(Depot record);
	
	@Select("select a.*,b.cab_no from depot as a left join cabin as b on a.cab_id=b.cab_id")
	List<DepotEx>lsDepot(Page<DepotEx> page);
	
	@Select("select a.*,b.cab_no from depot as a left join cabin as b on a.cab_id=b.cab_id where b.cab_id=#{params.cid}")
	List<DepotEx>lsDepotCid(Page<DepotEx> page);
	
	@Select("select * from depot")
	List<Depot>lsDepotCid2();
	
	@Select("select * from depot where cab_id=#{cid}")
	List<Depot>lsDepotCid1(Integer cid);
	
    @Select("select * from depot where dep_id=#{did}")
	Depot selDepotByDid(Integer did);
}
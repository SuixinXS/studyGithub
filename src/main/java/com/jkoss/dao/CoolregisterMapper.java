package com.jkoss.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.jkoss.pojo.Coolregister;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.CoolregisterVoCtm;
import com.jkoss.tool.Page;

public interface CoolregisterMapper {
	int deleteByPrimaryKey(Integer regist_id);

	int insert(Coolregister record);

	int insertSelective(Coolregister record);

	Coolregister selectByPrimaryKey(Integer regist_id);

	int updateByPrimaryKeySelective(Coolregister record);

	int updateByPrimaryKey(Coolregister record);

	List<CoolregisterVo> lsEnCold(Page<CoolregisterVo> page);

	List<CoolregisterVoCtm> lsCold(Page<CoolregisterVoCtm> page);

	@Select("select a.*,b.company_name,b.ctm_name,b.ctm_phone,b.ctm_login from coolregister as a left join customer as b on a.ctm_id=b.ctm_id where a.regist_id=#{rid}")
	CoolregisterVoCtm selColdByRid(Integer rid);

	CoolregisterVo enCold1(Integer eid);

	@Update("update coolregister set area_id=null,cab_id=null,dep_id=null where regist_id=#{rid}")
	int upCrg(@Param("rid") int rid);

	CoolregisterVo enColdByRid(Integer rid);

	List<CoolregisterVo> showReg();
	
	List<CoolregisterVo> showFinEnReg();

	List<CoolregisterVo> ddSelByEmpId(Page<CoolregisterVo> page);

	@Select("select a.* from coolregister as a left join execute as b on a.regist_id=b.regist_id where b.exe_id=#{eid}")
	CoolregisterVo finshOutReg1(@Param("eid") Integer eid);
	
	//根据regist_id查goodinfo表的name和type，coolreg表的所有
	CoolregisterVo selGoodReg(Integer regid);

}
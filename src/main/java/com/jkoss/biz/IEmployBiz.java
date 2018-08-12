package com.jkoss.biz;

import java.util.List;
import java.util.Map;

import com.jkoss.bo.DTreePriNode;
import com.jkoss.pojo.Depe;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Execute;
import com.jkoss.pojo.Job;
import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.tool.Page;

public interface IEmployBiz {

	

	String addXnEp(int num);

	List<Depe> listDepe();

	List<EmployVo> listEmployByDp_id(Page<EmployVo> page);

	List<EmployVo> listEmploy(Page<EmployVo> page);

	List<Job> listJob();

	String addOneEp(Employ employ);

	Employ selectEpById(Integer wk_ep_id);

	String UpdateEpById(Employ employ);

	List<EmployVo> selectEpById(Page<EmployVo> page);

	List<EmployVo> selectEpByLkName(Page<EmployVo> page);

	Map<Integer, DTreePriNode> generatePower(EmployVo employVo);

	EmployVo selectEpByLgPw(String lgnName, String pwdword);



	


	
 
}

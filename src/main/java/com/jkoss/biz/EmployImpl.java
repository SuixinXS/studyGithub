package com.jkoss.biz;



import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.jkoss.bo.DTreePriNode;
import com.jkoss.dao.DepeMapper;
import com.jkoss.dao.EmployMapper;
import com.jkoss.dao.JobMapper;
import com.jkoss.dao.PowerMapper;
import com.jkoss.pojo.Depe;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Job;
import com.jkoss.pojo.Power;
import com.jkoss.pojo.vo.EmployVo;
import com.jkoss.tool.Page;

@Component
public class EmployImpl implements IEmployBiz {
	
	@Autowired
	private EmployMapper epDao;
	@Autowired
	private DepeMapper deDao;
	@Autowired
	private JobMapper jbDao;
	@Autowired
	private PowerMapper pwDao;
	
	@Override
	public String addXnEp(int num) {
		// TODO Auto-generated method stub
		Employ employ = new Employ();
		for(int i = 0;i<num;i++) {
			employ.setEmp_name("员工"+i);
			employ.setEmp_login("login"+i);
			employ.setEmp_pass("123");
			if(i>0.2) {
			employ.setDepe_id(2);
			employ.setJob_id(1);
			}else if(i>0.4) {
				employ.setDepe_id(1);
				employ.setJob_id(5);
			}else {
				employ.setDepe_id(3);
				employ.setJob_id(3);
			}
			epDao.insertSelective(employ);
		}
		return "添加成功";
	}

	@Override
	public List<Depe> listDepe() {
		// TODO Auto-generated method stub
		return deDao.findAll();
	}

	@Override
	public List<EmployVo> listEmployByDp_id(Page<EmployVo> page) {
		// TODO Auto-generated method stub
		return epDao.findAllByDp_id(page);
	}

	@Override
	public List<EmployVo> listEmploy(Page<EmployVo> page) {
		// TODO Auto-generated method stub
		return epDao.findAll(page);
	}

	@Override
	public List<Job> listJob() {
		// TODO Auto-generated method stub
		return jbDao.findAll();
	}

	@Override
	@Transactional
	public String addOneEp(Employ employ) {
		// TODO Auto-generated method stub
		if(employ.getEmp_name().equals("")) {
			return "增加失败";
		}
		int i = selectEpByLg(employ.getEmp_login());
		if (i==0) {
			return "增加失败";
		}
		
		return epDao.insertSelective(employ)==1?"增加成功":"增加失败";
	}

	@Override
	public Employ selectEpById(Integer wk_ep_id) {
		// TODO Auto-generated method stub
		return epDao.selectByPrimaryKey(wk_ep_id);
	}

	@Override
	public String UpdateEpById(Employ employ) {
		// TODO Auto-generated method stub
		int i = selectEpByLg(employ.getEmp_login());
		if(i==0){
			return "账号已存在";
		}else {
			return epDao.updateByPrimaryKeySelective(employ)==1?"修改成功":"修改失败";
		}
	}

	@Override
	public List<EmployVo> selectEpById(Page<EmployVo> page) {
		// TODO Auto-generated method stub
		return epDao.selectByLkName(page);
	}

	@Override
	public List<EmployVo> selectEpByLkName(Page<EmployVo> page) {
		// TODO Auto-generated method stub
		return epDao.selectByLkName(page);
	}
	
	private DTreePriNode rigth2Node(Power pw) {
		DTreePriNode node;
		node = new DTreePriNode();
		node.setIndexNo(pw.getRfloor());
		node.setPrtNo(pw.getParentid());
		node.setText(pw.getPower_name());
		node.setUri(pw.getPowerURL());
		return node;
	}

	@Override
	public EmployVo selectEpByLgPw(String lgnName, String pwdword) {
		// TODO Auto-generated method stub
		return epDao.selectByLgPw(lgnName,pwdword);
	}

	@Override
	public Map<Integer, DTreePriNode> generatePower(EmployVo employVo) {
		// TODO Auto-generated method stub
		
		TreeMap<Integer, DTreePriNode> map = new TreeMap<>();
		DTreePriNode preNode=null;
		DTreePriNode childNode=null;
		if (employVo.getJob().getPowers()!=null) {
			for(Power power : employVo.getJob().getPowers()) {
				childNode = rigth2Node(power);
				preNode = rigth2Node(pwDao.selectByRfloor(childNode.getPrtNo()));
				map.put(preNode.getIndexNo(), preNode);
				map.put(childNode.getIndexNo(), childNode);
			}
		}else {
			return null;
		}
		return map;
	}

	@Override
	public int selectEpByLg(String emp_login) {
		// TODO Auto-generated method stub
		int i = 0 ;
		if (epDao.selectByLg(emp_login)!=null) {
			return i;
		}
		return i=1;
	}


	

	

	

}

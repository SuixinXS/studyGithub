package com.jkoss.biz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jkoss.dao.CabinMapper;
import com.jkoss.dao.CoolregisterMapper;
import com.jkoss.dao.CustomerMapper;
import com.jkoss.dao.DepotMapper;
import com.jkoss.dao.EmployMapper;
import com.jkoss.dao.ExecuteMapper;
import com.jkoss.dao.GoodinfoMapper;
import com.jkoss.pojo.Cabin;
import com.jkoss.pojo.Coolregister;
import com.jkoss.pojo.Depot;
import com.jkoss.pojo.Employ;
import com.jkoss.pojo.Execute;
import com.jkoss.pojo.Goodinfo;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.CoolregisterVoCtm;
import com.jkoss.pojo.vo.CustomerEx;
import com.jkoss.tool.Page;

@Component
public class ColdRegImplBiz implements IColdRegBiz {
	@Autowired
	CoolregisterMapper coolRegDao;
	@Autowired
	GoodinfoMapper goodDao;
	@Autowired
	EmployMapper employDao;
	@Autowired
	ExecuteMapper exeDao;
	@Autowired
	DepotMapper depotDao;
	@Autowired
	CustomerMapper cstmDao;
	@Autowired
	CabinMapper cabinDao;

//订单
	@Transactional
	public List<CoolregisterVoCtm> lsCold(Page<CoolregisterVoCtm> page, CoolregisterVoCtm coolReg) {
		// TODO Auto-generated method stub

		// 对数据进行处理，日期加一天
		if (coolReg != null) {
			if (coolReg.getRegist_begin() != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(coolReg.getRegist_begin());
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date tomorrow = c.getTime();
				page.getParams().put("tomorrow", tomorrow);

				/*
				 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 * System.out.println("输入的时间加一天"+sdf.format(tomorrow));
				 */
			}
			page.getParams().put("regist_begin", coolReg.getRegist_begin());
			page.getParams().put("regist_no", coolReg.getRegist_no());
			page.getParams().put("regist_state", coolReg.getRegist_state());
		}
		return coolRegDao.lsCold(page);
	}

	@Override
	@Transactional
	public String addColdReg(Coolregister cReg, Goodinfo good) {
		coolRegDao.insertSelective(cReg);
		good.setRegist_id(cReg.getRegist_id());
		goodDao.insertSelective(good);
		return "增加成功";
	}

	@Override
	public String zf(Coolregister record) {
		if (coolRegDao.updateByPrimaryKeySelective(record) > 0) {
			return "操作成功";
		}
		return "操作失败";
	}

	@Override
	public CoolregisterVoCtm selColdByRid(Integer rid) {
		// TODO Auto-generated method stub
		return coolRegDao.selColdByRid(rid);
	}

	@Override
	public int updateByPrimaryKeySelective(Coolregister record) {
		// TODO Auto-generated method stub

		return coolRegDao.updateByPrimaryKeySelective(record);
	}

//入库

	public List<CoolregisterVo> lsEnCold(Page<CoolregisterVo> page, CoolregisterVo coolEn) {
		// TODO Auto-generated method stub
		/* page.setPageSize(1); */

		if (coolEn != null) {
			if (coolEn.getExe_begin() != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(coolEn.getExe_begin());
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date tomorrow = c.getTime();
				page.getParams().put("tomorrow", tomorrow);
			}
			page.getParams().put("exe_begin", coolEn.getExe_begin());
			page.getParams().put("jg_name", coolEn.getJg_name());
		}
		return coolRegDao.lsEnCold(page);
	}

	@Override
	public List<Employ> findAllCCS() {
		// TODO Auto-generated method stub
		return employDao.findAllCCS();
	}

	@Override
	@Transactional
	public String addEnCold(CoolregisterVo c) {
		// TODO Auto-generated method stub
		Execute e = new Execute();
		e.setRegist_id(c.getRegist_id());
		e.setExe_begin(new Date());
		e.setColdopera(0);
		// 将信息增加到出入库计划表
		Random rand = new Random();
		e.setJg_name("R" + rand.nextInt(1000) + "K" + rand.nextInt(100));

		exeDao.insertSelective(e);
		// 将信息存入计划-员工关联表（调度）
		exeDao.insertExe2Emp(c.getEmp_id(), e.getExe_id());

		Depot d = new Depot();
		d.setCab_id(c.getCab_id());
		d.setDep_address(c.getDep_address());
		d.setDep_name(c.getDep_name());

		// 仓库增加
		depotDao.insertSelective(d);
		c.setDep_id(d.getDep_id());
		// 更新订单中的信息
		coolRegDao.updateByPrimaryKeySelective(c);
		return null;
	}

	@Override
	public CoolregisterVo enCold1(Integer eid) {
		// TODO Auto-generated method stub
		return coolRegDao.enCold1(eid);
	}

	@Override
	@Transactional
	public String upEnCold2(CoolregisterVo c) {
		// TODO Auto-generated method stub
		// 删除仓库。重新添加
		coolRegDao.upCrg(c.getRegist_id());

		depotDao.deleteByPrimaryKey(c.getDep_id());

		Depot d = new Depot();
		d.setDep_address(c.getDep_address());
		d.setDep_name(c.getDep_name());
		d.setCab_id(c.getCab_id());

		depotDao.insertSelective(d);
		// 获取添加后的id
		c.setDep_id(d.getDep_id());

		// 更新订单表
		coolRegDao.updateByPrimaryKeySelective(c);
		// exe2emp表

		exeDao.upExe2Emp(c.getEmp_id(), c.getExe_id());
		return null;
	}

	@Override
	@Transactional
	public String delEnCold(Integer eid) {
		// TODO Auto-generated method stub
		// 查出关于该计划的信息
		CoolregisterVo c = new CoolregisterVo();

		c = coolRegDao.enCold1(eid);

		// 清除订单的仓库位置
		coolRegDao.upCrg(c.getRegist_id());
		// 删除仓库
		depotDao.deleteByPrimaryKey(c.getDep_id());
		// 删除exe2emp表
		exeDao.delExe2Emp(c.getExe_id());
		// 删除exe计划表
		exeDao.deleteByPrimaryKey(c.getExe_id());

		return null;
	}

	@Override
	public String checkOutByRid(Integer rid) {
		// TODO Auto-generated method stub
		CoolregisterVo c = coolRegDao.enColdByRid(rid);
		if (c == null) {
			return "无此订单或此订单还没有入库计划";
		}
		if (c.getRegist_state() == 4) {
			return "此订单已经作废";
		}

		if (c.getExe_end() == null) {
			return "此订单的入库计划还未完成";
		}

		return "通过";
	}

	@Override
	@Transactional
	public String addOutColdReg(Integer regist_id, Integer emp_id) {
		// TODO Auto-generated method stub
		Execute e = new Execute();
		e.setRegist_id(regist_id);
		e.setExe_begin(new Date());
		e.setColdopera(1);
		// 自动生成编号
		Random rand = new Random();
		e.setJg_name("C" + rand.nextInt(1000) + "K" + rand.nextInt(100));
		exeDao.insertSelective(e);

		exeDao.insertExe2Emp(emp_id, e.getExe_id());

		return null;
	}

	@Override
	@Transactional
	public String delOutColdReg(Integer exe_id) {
		// TODO Auto-generated method stub
		exeDao.delExe2Emp(exe_id);

		exeDao.deleteByPrimaryKey(exe_id);

		return null;
	}

	@Override
	public List<CoolregisterVo> showReg() {
		// TODO Auto-generated method stub
		return coolRegDao.showReg();
	}

	@Override
	public List<CoolregisterVo> ddSelByEmpId(Page<CoolregisterVo> page, CoolregisterVo ddCool) {
		// TODO Auto-generated method stub

		if (ddCool != null) {
			if (ddCool.getExe_begin() != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(ddCool.getExe_begin());
				c.add(Calendar.DAY_OF_MONTH, 1);
				Date tomorrow = c.getTime();
				page.getParams().put("tomorrow", tomorrow);
			}
			page.getParams().put("exe_begin", ddCool.getExe_begin());
			page.getParams().put("jg_name", ddCool.getJg_name());
		}

		return coolRegDao.ddSelByEmpId(page);
	}

	@Override
	public String finshEnReg(Execute e) {
		// TODO Auto-generated method stub
		exeDao.updateByPrimaryKeySelective(e);
		return "";
	}

	@Override
	@Transactional
	public String finshOutReg(Execute e) {
		// TODO Auto-generated method stub
		// 更新调度计划表
		exeDao.updateByPrimaryKeySelective(e);
		// 按exe_id找信息
		e = exeDao.selectByPrimaryKey(e.getExe_id());

		CoolregisterVo c = coolRegDao.finshOutReg1(e.getExe_id());
		// 删除仓库
		depotDao.deleteByPrimaryKey(c.getDep_id());
		// 订单表作废
		Coolregister record = new Coolregister();
		record.setRegist_id(c.getRegist_id());
		record.setRegist_state(5);
		coolRegDao.updateByPrimaryKeySelective(record);

		return null;
	}

	@Override
	public CustomerEx valiCtm(String ctm_login) {
		// TODO Auto-generated method stub
		return cstmDao.selCtmByLogin(ctm_login);
	}

	@Override
	public String valiDepAddress(int cid, String dAddress) {
		// TODO Auto-generated method stub

		Cabin cab = cabinDao.selectByPrimaryKey(cid);
		if (cab.getCab_size() < Integer.parseInt(dAddress)) {
			return "没有该地址";
		}
		if (depotDao.valiDepAddress(cid, dAddress) != null) {
			return "仓库已存在";
		}
		return "可用";
	}

	@Override
	public List<CoolregisterVo> showFinEnReg() {
		// TODO Auto-generated method stub
		return coolRegDao.showFinEnReg();
	}

	@Override
	public CoolregisterVo calculationReg(Integer regid) {
		// TODO Auto-generated method stub
		return coolRegDao.selGoodReg(regid);
	}

	@Override
	public String calculationReg1(CoolregisterVo c) {
		// TODO Auto-generated method stub
		Coolregister record = new Coolregister();
		record.setRegist_id(c.getRegist_id());
		record.setRegist_cost(c.getRegist_cost());
        System.out.println(record);
		if (coolRegDao.updateByPrimaryKeySelective(record) > 0) {
			return "修改成功";
		}
		return "修改失败";
	}

	@Override
	public String finshCalculation(Integer rid) {
		// TODO Auto-generated method stub
		Coolregister record = new Coolregister();
		record.setRegist_id(rid);
		record.setRegist_paystate(1);
		coolRegDao.updateByPrimaryKeySelective(record);
		return null;
	}

}

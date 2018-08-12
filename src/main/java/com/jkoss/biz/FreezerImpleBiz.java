package com.jkoss.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jkoss.dao.AreainfoMapper;
import com.jkoss.dao.CabinMapper;
import com.jkoss.dao.DepotMapper;
import com.jkoss.pojo.Areainfo;
import com.jkoss.pojo.Cabin;
import com.jkoss.pojo.Depot;
import com.jkoss.pojo.vo.CoolregisterVo;
import com.jkoss.pojo.vo.DepotEx;
import com.jkoss.tool.Page;

@Component
public class FreezerImpleBiz implements IFreezerBiz {
	@Autowired
	AreainfoMapper areaDao;
	@Autowired
	CabinMapper cabinDao;
	@Autowired
	DepotMapper depotDao;

	@Override
	public List<Areainfo> lsArea() {
		// TODO Auto-generated method stub
		return areaDao.lsArea();
	}

	@Override
	public int updateByPrimaryKeySelective(Areainfo record) {
		// TODO Auto-generated method stub
		return areaDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertSelective(Areainfo record) {
		// TODO Auto-generated method stub
		return areaDao.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer area_id) {
		// TODO Auto-generated method stub
		return areaDao.deleteByPrimaryKey(area_id);
	}

	@Override
	public Areainfo selectByPrimaryKey(Integer area_id) {
		// TODO Auto-generated method stub
		return areaDao.selectByPrimaryKey(area_id);
	}

	
	
	
	
	@Override
	public String insertSelective(Cabin record) {
		// TODO Auto-generated method stub
		if (cabinDao.insertSelective(record) > 0) {
			return "增加成功";
		}
		return "增加失败";
	}

	public List<Cabin> lsCabin(Page<Cabin> page) {
		// TODO Auto-generated method stub

		if (page.getParams().get("aid") != null && (int) page.getParams().get("aid") != 0) {
			return cabinDao.findAllCabinByAid(page);
		}
		return cabinDao.findAllCabin(page);
	}

	@Override
	public String deleteCabinByPrimaryKey(Integer cab_id) {
		if (cabinDao.deleteByPrimaryKey(cab_id) > 0) {
			return "删除成功";
		}
		return "删除失败";
	}

	@Override
	public Cabin selectCabinByPrimaryKey(Integer cab_id) {
		// TODO Auto-generated method stub
		return cabinDao.selectByPrimaryKey(cab_id);
	}

	@Override
	public String updateByPrimaryKeySelective(Cabin record) {
		// TODO Auto-generated method stub

		if (cabinDao.updateByPrimaryKeySelective(record) > 0) {
			return "修改成功";
		}
		return "修改失败";
	}
	
	
	
	
	

	@Override
	public String insertSelective(Depot record) {
		// TODO Auto-generated method stub
		if (depotDao.insertSelective(record) > 0) {
			return "新增成功";
		}
		return "新增失败";
	}

	@Override
	public List<DepotEx> lsDepot(Page<DepotEx> page) {
		// TODO Auto-generated method stub
		if (page.getParams().get("cid") != null) {
			return depotDao.lsDepotCid(page);
		}
		return depotDao.lsDepot(page);
	}

	@Override
	public List<Cabin> lsCabinByAid(Integer area_id) {
		// TODO Auto-generated method stub
		return cabinDao.lsCabinByAid(area_id);
	}

	@Override
	public String deleteDepotByPrimaryKey(Integer dep_id) {
		// TODO Auto-generated method stub
		if (depotDao.deleteByPrimaryKey(dep_id) > 0) {
			return "删除成功";
		}
		return "删除失败";
	}

	@Override
	public Depot selDepotByDid(Integer did) {
		// TODO Auto-generated method stub
		return depotDao.selDepotByDid(did);
	}

	@Override
	public List<Cabin> lsAll() {
		// TODO Auto-generated method stub
		return cabinDao.lsAll();
	}

	@Override
	public String updateByPrimaryKeySelective(Depot record) {
		// TODO Auto-generated method stub
		if(depotDao.updateByPrimaryKeySelective(record)>0) {			
			return "编辑成功";
		}
		return "编辑失败";
	}

	@Override
	public Areainfo selAreaByName(String area_name) {
		// TODO Auto-generated method stub
		return areaDao.selAreaByName(area_name);
	}

	@Override
	public List<Cabin> selAllCabADep(Page<Cabin>page) {
		// TODO Auto-generated method stub
		return cabinDao.selAllCabADep(page);
	}

	@Override
	public List<Depot> lsDepotCid2() {
		// TODO Auto-generated method stub
		return depotDao.lsDepotCid2();
	}



}

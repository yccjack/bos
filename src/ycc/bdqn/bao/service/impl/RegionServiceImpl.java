package ycc.bdqn.bao.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.RegionDao;
import ycc.bdqn.bao.domain.Region;
import ycc.bdqn.bao.service.RegionService;
import ycc.bdqn.bao.utils.PageBean;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

	@Resource
	private RegionDao regionDao;
	@Override
	public void saveBatch(List<Region> list) {
		for (Region region : list) {
			regionDao.saveOrUpdate(region);
		}
	}
	@Override
	public void pageQuery(PageBean pageBean) {
		regionDao.pageQuery(pageBean);
		
	}
	@Override
	public List getAll() {
		return regionDao.findAll();
		
	}

}

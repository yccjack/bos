package ycc.bdqn.bao.service;

import java.util.List;

import ycc.bdqn.bao.domain.Region;
import ycc.bdqn.bao.utils.PageBean;

public interface RegionService {

	public void saveBatch(List<Region> list);

	public void pageQuery(PageBean pageBean);

	public List<Region> getAll();

}

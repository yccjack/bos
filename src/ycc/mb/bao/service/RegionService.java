package ycc.mb.bao.service;

import java.util.List;

import ycc.mb.bao.domain.Region;
import ycc.mb.bao.utils.PageBean;

public interface RegionService {

	public void saveBatch(List<Region> list);

	public void pageQuery(PageBean pageBean);

	public List<Region> getAll();

}

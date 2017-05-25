package ycc.mb.bao.service;

import ycc.mb.bao.domain.Decidedzone;
import ycc.mb.bao.utils.PageBean;

public interface DecidedzoneService {

	public void save(Decidedzone model, String[] subareaid);

	public void pageQuery(PageBean pageBean);

	

}

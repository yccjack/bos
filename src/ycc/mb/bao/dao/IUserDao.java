package ycc.mb.bao.dao;

import ycc.mb.bao.dao.base.IBaseDao;
import ycc.mb.bao.domain.User;

public interface IUserDao extends IBaseDao<User>{
	public User findByUsernameAndPassword(String username, String password);

	public User findByUsername(String username);
}

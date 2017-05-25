package ycc.bdqn.bao.dao;

import ycc.bdqn.bao.dao.base.IBaseDao;
import ycc.bdqn.bao.domain.User;

public interface IUserDao extends IBaseDao<User>{
	public User findByUsernameAndPassword(String username, String password);

	public User findByUsername(String username);
}

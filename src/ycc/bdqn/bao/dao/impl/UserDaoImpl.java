package ycc.bdqn.bao.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import ycc.bdqn.bao.dao.IUserDao;
import ycc.bdqn.bao.dao.base.impl.BaseDaoImpl;
import ycc.bdqn.bao.domain.User;

@Repository
//@Scope("prototype")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao{
	/**
	 * 根据用户名和密码查询用户
	 */
	public User findByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<Object> list = this.getHibernateTemplate().find(hql, username,password);
		if(list != null && list.size() > 0){
			return (User) list.get(0);
		}
		return null;
	}

	@Override
	public User findByUsername(String username) {
		String hql = "FROM User u WHERE u.username = ? ";
		List<Object> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return (User) list.get(0);
		}
		return null;
	}

}

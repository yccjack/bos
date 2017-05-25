package ycc.bdqn.bao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ycc.bdqn.bao.dao.IUserDao;
import ycc.bdqn.bao.domain.User;
import ycc.bdqn.bao.service.IUserService;
import ycc.bdqn.bao.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	// 注入dao
	@Autowired
	private IUserDao userDao;

	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();// 明文
		password = MD5Utils.md5(password);// md5加密
		return userDao.findByUsernameAndPassword(username, password);
	}

	public void editPassword(String password, String id) {
		userDao.executeUpdate("editPassword", password, id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}

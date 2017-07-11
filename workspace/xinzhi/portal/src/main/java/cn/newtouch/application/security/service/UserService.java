package cn.newtouch.application.security.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.UserDao;
import cn.newtouch.application.security.dao.UserRoleDao;
import cn.newtouch.application.security.entity.User;
import cn.newtouch.application.security.entity.UserRole;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;

	public User getUserByUsername(String username) {
		String query = "from User u where u.username = ?";
		Iterator<User> it = userDao.createQuery(query, username).iterate();
		if (it.hasNext()) {
			return it.next();
		}
		return null;
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public List<UserRole> findUserRoleByUserId(Long id) {
		String hql = "from UserRole ur where ur.user.id = ?";
		List<UserRole> userRoles = userRoleDao.find(hql , id);
		return userRoles;
	}

	public UserRole saveUserRole(UserRole userRole) {
		return userRoleDao.save(userRole);
	}

}

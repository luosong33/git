package cn.newtouch.application.security.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.RoleDao;
import cn.newtouch.application.security.entity.Role;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;

	public Role getRoleByName(String name) {
		String query = "from Role r where r.name = ?";
		Iterator<?> it = roleDao.createQuery(query, "admin").iterate();
		if (it.hasNext()) {
			return (Role) it.next();
		}
		return null;
	}

	public Role save(Role role) {
		return roleDao.save(role);
	}

}

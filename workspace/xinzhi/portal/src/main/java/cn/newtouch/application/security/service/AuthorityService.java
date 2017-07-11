package cn.newtouch.application.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.AuthorityDao;
import cn.newtouch.application.security.entity.Authority;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityDao authorityDao;

	public List<String> findNameByUserId(Long userId) {
		return authorityDao.findNameByPostId(userId);
	}

	public List<Authority> findAuthorityByProjectId(String projectId) {
		String query = "from Authority a where a.project.id = ?";
		return authorityDao.createQuery(query, projectId).list();
	}

	public Authority save(Authority authority) {
		return authorityDao.save(authority);
	}

	public List<String> findAllName() {
		return authorityDao.findAllName();
	}

}

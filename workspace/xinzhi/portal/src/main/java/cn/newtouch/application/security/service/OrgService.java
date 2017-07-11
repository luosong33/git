package cn.newtouch.application.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.OrgDao;
import cn.newtouch.application.security.entity.Org;

@Service
public class OrgService {
	
	@Autowired
	private OrgDao orgDao;

	public Org save(Org org) {
		return orgDao.save(org);
	}

	public Org getOrg(String id) {
		return orgDao.get(id);
	}

}

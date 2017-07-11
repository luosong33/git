package cn.newtouch.application.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.ResourceDao;
import cn.newtouch.application.security.entity.Resource;
import cn.newtouch.framework.web.Pager;

@Service
public class ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;

	public List<Resource> getAll() {
		return resourceDao.getAll();
	}

	public List<Resource> findResourceByProjectId(String projectId) {
		String query = "from Resource r where r.authority.project.id = ?";
		return resourceDao.createQuery(query, projectId).list();
	}

	public Resource save(Resource resource) {
		return resourceDao.save(resource);
	}

	public void update(Resource resource) {
		resourceDao.update(resource);
	}

	public Pager<Resource> getPagerLinksWithoutMenu(int page, int pagesize) {
		String hql = "from Resource r join fetch r.authority a join fetch a.project p where r.type= 'html' and r.menu = null";
		List<Resource> data = resourceDao.createQuery(hql).setMaxResults(pagesize).setFirstResult(pagesize * (page -1)).list();
		hql = "select count(r) from Resource r where r.type= 'html' and r.menu = null";
		Long total = (Long) resourceDao.createQuery(hql).uniqueResult();
		return new Pager<Resource>(1L, data);
	}

}

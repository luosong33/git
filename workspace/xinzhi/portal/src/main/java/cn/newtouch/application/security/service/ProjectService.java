package cn.newtouch.application.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.ProjectDao;
import cn.newtouch.application.security.entity.Project;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectDao projectDao;

	public Project getProject(String id) {
		return projectDao.get(id);
	}

	public Project save(Project project) {
		return projectDao.save(project);
	}

}

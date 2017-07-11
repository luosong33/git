package cn.newtouch.application.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.newtouch.application.security.dao.MenuDao;
import cn.newtouch.application.security.entity.Menu;

@Service
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;

	public List<Menu> getAll() {
		String hql = "from Menu m left join fetch m.resource";
		List<Menu> menus = menuDao.find(hql);
		return menus;
	}

	public Menu create(Long pid, String name) {
		Menu menu = new Menu();
		menu.setName(name);
		if (pid != null) {
			Menu parent = menuDao.load(pid);
			menu.setParent(parent);
			String hql = "select max(m.index) from Menu m where m.parent.id = ?";
			Integer index = menuDao.findUnique(hql , pid);
			menu.setIndex(index == null ? 0 : index + 1);
		}
		return menuDao.save(menu);
	}

	public void save(Menu menu) {
		Menu parent = menuDao.load(menu.getParent().getId());
		menu.setParent(parent);
		menuDao.save(menu);
	}

	public void update(Long id, String name, String description) {
		Menu menu = menuDao.load(id);
		menu.setName(name);
		menu.setDescription(description);
		menuDao.update(menu);
	}

	public void updatePostion(Long id, Long pid, Integer index) {
		Menu menu = menuDao.load(id);
		if (pid != menu.getPid()) {
			if (pid == null) {
				menu.setParent(null);
			} else {
				Menu parent = menuDao.load(pid);
				menu.setParent(parent);
			}
			String hql = "update Menu m set m.index = m.index - 1 where m.parent.id =? and m.index >= ?";
			menuDao.batchExecute(hql , menu.getPid(), menu.getIndex());
			hql = "update Menu m set m.index = m.index + 1 where m.parent.id =? and m.index >= ?";
			menuDao.batchExecute(hql , pid, index);
		} else if(menu.getIndex() > index) {
			String hql = "update Menu m set m.index = m.index + 1 where m.parent.id =? and m.index >= ? and m.index < ?";
			menuDao.batchExecute(hql , pid, index, menu.getIndex());
		} else {
			String hql = "update Menu m set m.index = m.index - 1 where m.parent.id =? and m.index > ? and m.index <= ?";
			menuDao.batchExecute(hql , pid, menu.getIndex(), index);
		}
		menuDao.flush();
		menu.setIndex(index);
		menuDao.update(menu);
	}

	public void delete(Long id) {
		menuDao.delete(id);
	}

}

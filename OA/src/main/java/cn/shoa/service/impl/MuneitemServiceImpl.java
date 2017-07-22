package cn.shoa.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.shoa.dao.MenuitemDao;
import cn.shoa.domain.menu.Menuitem;
import cn.shoa.service.MenuitemService;
@Service("menuitemService")
public class MuneitemServiceImpl implements MenuitemService{
	@Resource(name="menuitemDao")
	private MenuitemDao menuitemDao;
	@Override
	public Collection<Menuitem> getAllMenu() {
		// TODO Auto-generated method stub
		return menuitemDao.getAllEntry();
	}

}

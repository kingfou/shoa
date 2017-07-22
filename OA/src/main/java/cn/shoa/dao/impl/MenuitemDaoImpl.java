package cn.shoa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.shoa.dao.MenuitemDao;
import cn.shoa.dao.base.impl.BaseDaoImpl;
import cn.shoa.domain.menu.Menuitem;
@Repository("menuitemDao")
public class MenuitemDaoImpl extends BaseDaoImpl<Menuitem> implements MenuitemDao{

}

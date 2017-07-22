package cn.shoa.dao.base;

import java.io.Serializable;
import java.util.Collection;

public interface BaseDao<E> {
	//保存操作
	public void saveEntry(E e);
	
	
	//删除操作
	public void deleteEntry(Serializable id);
	
	
	//更新操作
	public void updateEntry(E e);
	
	
	//得到实体
	public E getEntryById(Serializable id);
	
	
	//得到集合
	public Collection<E> getAllEntry();
	
	
	//ids[]得到Set
	public Collection<E> getEntrysByIds(Serializable[] ids);

}

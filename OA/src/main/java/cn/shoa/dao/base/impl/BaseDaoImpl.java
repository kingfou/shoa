package cn.shoa.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.shoa.dao.base.BaseDao;

public class BaseDaoImpl<E> implements BaseDao<E> {
	
	//获得泛型的参数
	/**
	 * 获得泛型的真实参数
	 */
	@SuppressWarnings("rawtypes")
	public Class classt;
	@SuppressWarnings("rawtypes")
	public BaseDaoImpl(){
		//只有当我们的BaseDaoImpl是泛型的时候，下面的语句才不会报错！
		ParameterizedType type=(ParameterizedType)this.getClass().getGenericSuperclass();
		//获得E的Class。
		this.classt=(Class) type.getActualTypeArguments()[0];
	}
	@Resource(name="hibernateTemplate")
	public HibernateTemplate hibernateTemplate;
	
	public ClassMetadata metedata;
	//该注解表示的是在构造方法执行后进行执行！！！！！！
	@PostConstruct
	public void init(){
		this.metedata = this.hibernateTemplate.getSessionFactory().getClassMetadata(this.classt);
	}
	
	@Override
	public void saveEntry(E e) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.save(e);
	}

	@Override
	public void deleteEntry(Serializable id) {
		// TODO Auto-generated method stub
		//删除之前要找到该对象，之后删除！
		E e=getEntryById(id);
		this.hibernateTemplate.delete(e);
	}

	@Override
	public void updateEntry(E e) {
		// TODO Auto-generated method stub
		this.hibernateTemplate.update(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getEntryById(Serializable id) {
		// TODO Auto-generated method stub
		
		//获得数据库的字典，获得该类的映射字典，也就是说该类映射后的所有字段名称
		/*this.metedata=this.hibernateTemplate.getSessionFactory().
				getClassMetadata(this.classt);*/
		
		return (E) this.hibernateTemplate.
				find("from "+this.classt.getName()+" where "
		              +metedata.getIdentifierPropertyName()+"=?", id).
				         get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> getAllEntry() {
		// TODO Auto-generated method stub
		return (Collection<E>) this.hibernateTemplate.find("from "+this.classt.getName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<E> getEntrysByIds(Serializable[] ids) {
		// TODO Auto-generated method stub
		
		//注意！数据字典是要自己实例化的。
		/*this.metedata=this.hibernateTemplate.getSessionFactory().
				getClassMetadata(this.classt);*/
		StringBuffer sb=new StringBuffer();
		sb.append("from "+classt.getName());
		sb.append(" where "+this.metedata.getIdentifierPropertyName());
		sb.append(" in(");
		for(int i=0;i<ids.length;i++){
			if(i==ids.length-1){//最后一个
				sb.append(ids[i]);			
			}else{
			sb.append(ids[i]+",");
			}
		}
		sb.append(")");
		return (Collection<E>) this.hibernateTemplate.find(sb.toString());
	}

}

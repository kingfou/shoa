package cn.shoa.struts2.action.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<E> extends ActionSupport implements ModelDriven<E> {

	private static final long serialVersionUID = 1L;
	//一般有E的我们都要获取到E的泛型的真正类
	@SuppressWarnings("rawtypes")
	Class classt;
	E e;//表示的是一个真实类型？？
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BaseAction(){//类实例化的时候可以直接获得E的真实类型
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		this.classt=(Class) type.getActualTypeArguments()[0];
		try {
			this.e=(E) classt.newInstance();
			//由于子类是一个多例的实例，所以这句话会打印多次。
			System.out.println(e.getClass().getSimpleName());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public E getModel() {
		// TODO Auto-generated method stub
		return e;
	}
	
public static final String LISTACTION = "listAction";
	
	/**
	 * 跳转了列表页面
	 */
	public static String listAction = LISTACTION;
	
	public static final String UPDATE_UI = "updateUI";
	
	/*
	 * 跳转到修改界面
	 */
	public static String updateUI = UPDATE_UI;
	
	public static final String ADD_UI = "addUI";
	
	/**
	 * 跳转到添加的页面
	 */
	public static String addUI = ADD_UI;
	
	
	public static final String ACTION2ACTION = "action2action";
	
	/**
	 * 由action跳转到action
	 */
	public static String action2action = ACTION2ACTION;

}

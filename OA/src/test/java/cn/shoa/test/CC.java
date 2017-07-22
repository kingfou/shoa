package cn.shoa.test;

public class CC {
	public static void main(String[] args) {
		System.out.print("1");
	}

}
interface AA{
	public void aa();
	public void ss();
}
interface BB extends AA{
	public void cc();
}
class DD implements AA{

	@Override
	public void aa() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ss() {
		// TODO Auto-generated method stub
		
	}
	
}
class FF extends DD implements BB{

	@Override
	public void cc() {
		// TODO Auto-generated method stub
		
	}}

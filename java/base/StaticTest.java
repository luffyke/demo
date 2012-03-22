package base;

public class StaticTest extends StaticTestFather{

	static{
		System.out.println("Son's static block");
	}
	
	{
		System.out.println("Son's Non-static block");
	}
	
	public StaticTest(){
		System.out.println("Son's constructor");
	}
	
	public static void main(String[] args) {
		StaticTest test = new StaticTest();
	}
}

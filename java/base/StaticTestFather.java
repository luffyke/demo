package base;

public class StaticTestFather {

	static{
		System.out.println("Father's static block");
	}
	
	{
		System.out.println("Father's Non-static block");
	}
	
	public StaticTestFather(){
		System.out.println("Father's constructor");
	}
}

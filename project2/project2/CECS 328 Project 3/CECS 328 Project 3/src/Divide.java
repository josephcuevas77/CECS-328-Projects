
public class Divide extends Operator{
	public int Operate(int num1, int num2) {
		return num1 / num2;
	}
	
	public int getOrder() {
		return 2;
	}
	
	public String toString(){
		return "/";
	}
}

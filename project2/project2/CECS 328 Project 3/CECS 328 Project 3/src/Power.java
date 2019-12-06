
public class Power extends Operator{
	public int Operate(int num1, int num2) {
		return (int) Math.pow(num1, num2);
	}
	
	public int getOrder() {
		return 3;
	}
	
	public String toString(){
		return "^";
	}
}

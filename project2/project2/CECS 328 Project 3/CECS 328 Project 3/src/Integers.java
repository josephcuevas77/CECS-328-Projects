
public class Integers extends Operator {
	private int num1;
	
	public Integers(int num) {
		num1 = num;
	}
	
	public Integers() {
		this(-1);
	}
	
	public int Operate() {
		return num1;
	}
	
	public int getOrder() {
		return -1;
	}
	
	public boolean isInteger() {
		return true;
	}
	
	public String toString(){
		return String.valueOf(num1);
	}
}

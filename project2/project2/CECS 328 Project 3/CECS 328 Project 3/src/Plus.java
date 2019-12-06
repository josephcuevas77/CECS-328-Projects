
public class Plus extends Operator{
	public int Operate(int num1, int num2) {
		return num1 + num2;
	}

	public int getOrder() {
		return 1;
	}
	
	public String toString(){
		return "+";
	}
}

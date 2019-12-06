
public class Node {
	private Node left;
	private Node right;
	private Node parent;
	private Operator content;
	
	public Node(Operator op) {
		this.content = op;
		left = null;
		right = null;
		parent = null;
	}
	
	public Node() {
		this(null);
	}
	
	public void setLeft(Node n) {
		this.left = n;
	}
	
	public void setRight(Node n) {
		this.right = n;
	}
	
	public void setParent(Node n) {
		this.parent = n;
	}
	
	public void setOperator(Operator op) {
		this.content = op;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public Operator getContent() {
		return this.content;
	}
	
	public int getOrder() {
		return this.content.getOrder();
	}
	
	public int getOperate() {
		if (this.content.isInteger()) {
			return content.Operate();
		} else {
			return content.Operate(this.left.getOperate(), this.right.getOperate());
		}
	}
	
	public String toString() {
		return this.content.toString();
	}
}

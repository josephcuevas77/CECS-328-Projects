import java.util.ArrayList;
import java.util.Stack;


public class ParseTree {
	
	private ArrayList<Node> nodeList;
	int headIndex;
	
	public ParseTree(ArrayList<Node> n) {
		this.nodeList = n;
		this.headIndex = -1;
	}
	
	public ParseTree() {
		this(null);
	}
	
	public void setNodeList(ArrayList<Node> o) {
		this.nodeList = o;
	}
	
	public ArrayList<Node> getNodeList(){
		return this.nodeList;
	}
	
	public int getHeadIndex() {
		return this.headIndex;
	}
	
	//connects all the nodes together in nodeList
	public void buildTree() {
		Stack<Node> stack = new Stack<>();
		ArrayList<Node> templist = this.nodeList;
		ArrayList<Node> newlist = new ArrayList<>();
		int n = templist.size();
		for (int i = 0; i < n; i++ ) {
			if(!templist.get(0).getContent().isInteger()) {
				
				//set right node connections
				Node r = stack.pop();
				templist.get(0).setRight(r);
				r.setParent(templist.get(0));
				newlist.add(r);
				
				//set left node connections
				Node l = stack.pop();
				templist.get(0).setLeft(l);
				l.setParent(templist.get(0));
				newlist.add(l);
			}
			stack.push(templist.remove(0));
		}
		newlist.add(stack.pop());
		
		this.nodeList = newlist;
		
		//finding head of tree and updating parameter
		for(int i = 0; i < this.nodeList.size(); i++) {
			if(nodeList.get(i).getParent() == null) {
				this.headIndex = i;
				break;
			}
		}	
	}
	
	public int Evaluate() {
		return Evaluate(this.nodeList.get(this.headIndex));
	}
	
	private int Evaluate(Node root) {
		if (root == null) {
			return 0; //empty
		}
		
		//if node is an integer
		if(root.getContent().isInteger()) {
			return root.getContent().Operate();
		}
		
		//left evaluation
		int lNum = Evaluate(root.getLeft());
		
		//right evaluation
		int rNum = Evaluate(root.getRight());
		
		//return operator
		return root.getContent().Operate(lNum, rNum);
	}
	
}

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
 * C
 * */

public class Main {

	public static void main(String[] args) {
		int userInput = 1;
		Scanner scanner = new Scanner(System.in);
		
		while (userInput == 1) {
			System.out.println("Please input an equation involving only integers[0-9] and operators[+, -, /, *, ^]:");
			String problem = scanner.nextLine();
//			String problem = "3+5*16/2^2-6";
			ArrayList<Operator> OperatorList = new ArrayList<>();
			ArrayList<Node> NodeList = new ArrayList<>();
			
			//takes the problem and converts every character into
			//an operator and then places them in the operator list
			for (int i = 0; i < problem.length(); i++)
			{
				switch(problem.charAt(i)) {
				case '+':
					OperatorList.add(new Plus());
					break;
				case '-':
					OperatorList.add(new Minus());
					break;
				case '*':
					OperatorList.add(new Multiply());
					break;
				case '/':
					OperatorList.add(new Divide());
					break;
				case '^':
					OperatorList.add(new Power());
					break;
				default:
					OperatorList.add(new Integers(getNumber(problem, i)));
					int s = String.valueOf(getNumber(problem, i)).length() - 1;
					while (s > 0){
						s--;
						i++;
					}
				}
			}
			
			OperatorList = convertPostfix(OperatorList);
			NodeList = convertNodeList(OperatorList);
			
			ParseTree tree = new ParseTree(NodeList);
			tree.buildTree();
			//prints all the nodes and their relationships
			System.out.println("=== The nodes in your equation ===");
			for(int i = 0; i < tree.getNodeList().size(); i++) {
				Node j = tree.getNodeList().get(i);
				System.out.println("Node:   " + j);
				System.out.println("Left:   " + j.getLeft());
				System.out.println("Right:  " + j.getRight());
				System.out.println("Parent: " + j.getParent());
				System.out.println();
			}
			
			System.out.println("=== Evaluation ===");
			TreeWalk(tree.getNodeList().get(tree.getHeadIndex()));
			System.out.println(" = " + tree.Evaluate());
			System.out.println();
			System.out.println("=== Would you like to try again? ===");
			System.out.println("1 -> Yes\n0 -> No");
			System.out.println("Choice: ");
			userInput = Integer.parseInt(scanner.nextLine());
		}
		System.out.println("=== Program has closed ===");
		scanner.close();
	}
	
	// ========== end of main | start of static functions ==============
	
	//gets the largest amount of char digits and places it and returns a single int
	public static int getNumber(String word, int i)
	{
		String num = "";
		for (int j = i; j < word.length(); j++) {
			if (Character.isDigit(word.charAt(j))) {
				num += word.charAt(j);
			} else {
				break;
			}
		}
		return Integer.parseInt(num);
	}
	
	//places each operator into their own node and
	//puts them into a node list
	public static ArrayList<Node> convertNodeList(ArrayList<Operator> op){
		ArrayList<Node> newList = new ArrayList<>();
		for (int i = 0; i < op.size(); i++) {
			newList.add(new Node(op.get(i)));
		}
		return newList;
	}
	
	//converts an operator list from infix to postfix order
	public static ArrayList<Operator> convertPostfix(ArrayList<Operator> opList) 
	{
		ArrayList<Operator> result = new ArrayList<>();
		Stack<Operator> opStack= new Stack<>();
		for (int i = 0; i < opList.size(); i++) {
			Operator o = opList.get(i);
			
			if(o.isInteger()){
				result.add(o);
			} else {
				while (!opStack.isEmpty() && o.getOrder() <= opStack.peek().getOrder()) {
					result.add(opStack.pop());
				}
				opStack.push(o);
			}
		}
		while (!opStack.isEmpty()) {
			result.add(opStack.pop());
		}
		return result;
	}
	
	//inorder treewalk
	public static void TreeWalk(Node root) {
		if (root != null) {
			TreeWalk(root.getLeft());
			System.out.print(root);
			TreeWalk(root.getRight());
		}
	}

}

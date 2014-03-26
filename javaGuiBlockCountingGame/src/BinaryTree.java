/*
 * name: Shaheen Ghazazani
 * class: Comp1406 
 * Assignment#: 10
 * due date: March 31st, 2014 (the last assignment, due monday). 
 */

public class BinaryTree { 
	private String data; 
	private BinaryTree leftChild; 
	private BinaryTree rightChild; 
	int counter = 0; 
	
	public BinaryTree(String d) { 
		data = d; 
		leftChild = null; 
		rightChild = null; 
	} 
	 
	public BinaryTree(String d, BinaryTree left, BinaryTree right) { 
		data = d; 
		leftChild = left; 
		rightChild = right; 
	} 
	
	public boolean hasSameStructureAs(BinaryTree tree){
		//recursively use the function below 
		return (hasSameStructureAsRecurs(this, tree));
	}
	public boolean hasSameStructureAsRecurs(BinaryTree thisTree, BinaryTree treeTree){
		if ((thisTree == null) && (treeTree == null)){
			//when there both null it means we reached the bottom and they are identical, therefore True 
			return true;
		}else if((thisTree != null) && (treeTree != null)){
			//recursively look at left and left, and right and right 
			return (hasSameStructureAsRecurs(thisTree.leftChild, treeTree.leftChild) &&
					hasSameStructureAsRecurs(thisTree.rightChild, treeTree.rightChild));
		}
		//else they have different structure 
		return false; 
	}
}


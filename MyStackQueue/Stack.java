*/
*
* Name: Sayd Mateen
* Class: CSC20
*/


package MyStackQueue;
public class Stack{
	private Node Top = null;
	private int size = 0;
	public boolean isEmpty() { return Top==null; }
	public void push(Object Element) {
		Node Tmp = new Node(Element);
		Tmp.Next = Top; Top = Tmp; size++;
	}
	public Object pop() {
		Node Tmp = Top;
		Top =  Top.Next; size--;
		return Tmp.Data;
	}
	public Object top() {
		return Top.Data;
	}

}

class Node {
	public Object Data = null;
	public Node Next = null;
	public Node(Object data){
		Data = data;
	}
}	

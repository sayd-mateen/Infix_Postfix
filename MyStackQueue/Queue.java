/* 
* 
* Name: Sayd Mateen
* Class: CSC20
*/

package MyStackQueue;
public class Queue {
	private int front = 0, rear = -1, capacity = 100;
	private Object S[] = new Object[capacity]; 
	public boolean isEmpty() { return front==(rear+1)%capacity; }
	public void enqueue(Object Element) {
		rear = (rear+1)%capacity; S[rear] = Element;
	}
	public Object dequeue() {
		Object Element =  S[front]; S[front] = null;
		front = (front+1) % capacity; 
		return Element;
	}
	public String toString(){
		String s = "";
		for(int i = front; i <= rear; i++){
			s = s + S[i].toString() + " ";
		}
		return s;
	}
}

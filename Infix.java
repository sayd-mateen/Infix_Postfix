/*
* Name:Sayd Mateen
* Class: CSC20
*/

import java.io.*;
import MyStackQueue.*;

class infix {
    static Queue infixToPostfix(String s) {
	    Tokenizer expression = new Tokenizer(s);
	    Stack theStack = new Stack();
	    Queue theQueue = new Queue();
	    theStack.push(new Operator('#'));
	    Token current = expression.nextToken();
	    Operator opr;
	    while(current != null){
	        if(current instanceof Operand){
	            theQueue.enqueue((Operand)current );
	        }else{
			    opr = (Operator)current;
			    if (opr.operator==')'){
				    while(((Operator)theStack.top()).operator != '('){
					    theQueue.enqueue(theStack.pop());
				    }
				    theStack.pop(); 	
			    }else if(opr.operator == '('){
				    theStack.push(opr);
	            }else{	
				    while(((Operator)theStack.top()).precedence() >= opr.precedence()){
					     theQueue.enqueue(theStack.pop());
				    }
				    theStack.push(opr);
	            }
	        }
		    current = expression.nextToken();		
	    }
	    while(((Operator)theStack.top()).operator != '#'){
		    Operator check = (Operator)theStack.pop();
		    theQueue.enqueue(check);
	    }
	    return theQueue;
    }

    static int evaluatePostfix(Queue Post) {
	    Stack theStack = new Stack();
	    int result = 0;
	    while(!Post.isEmpty()){
		    Token tkn = (Token)Post.dequeue();
		    if(tkn instanceof Operand){
			    theStack.push(tkn);
		    }else{
		        Operator Opr = (Operator)tkn;
			    int opnd2 = ((Operand)theStack.pop()).operand;
			    int opnd1 = ((Operand)theStack.pop()).operand;
			    switch(Opr.operator) {
				    case '+': result = opnd1 + opnd2; break;
			 	    case '-': result = opnd1 - opnd2; break;
				    case '*': result = opnd1 * opnd2; break;
				    case '/': result = opnd1 / opnd2; break;
				}
			    theStack.push(new Operand(result));
		    }
				
	    }
		
	    return result;
    }

	public static void main(String[] args) throws IOException {
		Queue Post;
		System.out.println("Sayd's test program");
		while(true) {
			try{
				System.out.print("Enter infix: ");
				System.out.flush();
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String s = br.readLine();
				if ( s.equals("") ) break;
				parentheses(s);
				Post = infixToPostfix(s);
				System.out.println("Postfix is " + Post.toString() + '\n');
				checkOpOr(s);
				int result = evaluatePostfix(Post);
				System.out.println("Result is " + result + '\n');
			}catch (Exception e) {
				System.out.println("\n***** "+e.toString()+" *****\n");
			}
    	}	
	}
	
	static void parentheses(String s) throws Exception{
		char[] arr = s.toCharArray();
		int rightP = 0, leftP = 0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i] == '('){
				leftP++;
			}else if(arr[i] == ')'){
				rightP++;
			}
		}if(rightP != leftP){
			if(rightP > leftP){
				throw new LeftParenthesesMissing();
			}else throw new RightParenthesesMissing();
		}
	}
	
	static void checkOpOr(String s) throws Exception{
		Tokenizer expression = new Tokenizer(s);
	    Token current = expression.nextToken();
	    Operator opr;
		int countOP = 0, countOR = 0;
	    while(current != null){
	        if(current instanceof Operand){
	            countOP++;
	        }else{
			    opr = (Operator)current;
			    if (opr.operator !=')' && opr.operator != '('){
				    countOR++;
				}
	        }
		    current = expression.nextToken();		
	    }
		int result = countOP - countOR;
		if(result < 1){
			throw new TooManyOperators();
		}else if( result > 1){
			throw new TooManyOperands();
		}	
	}
}

class RightParenthesesMissing extends Exception {
	public String toString () { return "Too Many (";}
}

class LeftParenthesesMissing extends Exception {
	public String toString () { return "Too many )";}
}

class TooManyOperands extends Exception {
	public String toString () { return "Too many operands";}
}

class TooManyOperators extends Exception {
	public String toString () { return "Too many operators";}
}

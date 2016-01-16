/*
* 
* Name: Sayd Mateen
* Class: CSC20
*/


class Tokenizer{
	private char[] Buf;
	private int cur;
	
	Tokenizer(String infixExpression){
		Buf = infixExpression.toCharArray();
		cur = 0;
	}
	public Token nextToken(){
		while(cur < Buf.length && Buf[cur] == ' '){
			cur++;
		}
		if(cur >= Buf.length) return null;
		if(Buf[cur] >= '0' && Buf[cur] <= '9'){
			int start = cur, len = 0;
			while(cur < Buf.length && Buf[cur] >= '0' && Buf[cur] <= '9'){
				cur++; len++;
			}
			String Digits = new String(Buf, start, len); 
			int num = Integer.valueOf(Digits).intValue();
			Token opr = new Operand(num);
			return opr;
		}else{
			Token opr = new Operator(Buf[cur]);
			cur++;
			return opr;
		}	
		
	}
}

package nju.edu.lc3.word;

import nju.edu.lc3.parser.Token;

public class Bits {
	char[] bits;
	public Bits(int length){
		bits = new char[length];
	}
	public Bits(){
		
	}
	
	public char[] extendBitsArray(char[] bits, int length){
		//扩充bits到指定长度的数组
		return null;
	}
	public static char[] OCTAL_TO_BITS(Token token,int length)throws Exception{
		if(length <1){
			throw new Exception("bit length must > 1");
		}
		String str = token.image.substring(1);
		char[] temp = OCTAL_TO_BITS(token);
		int value = Word.calValue(temp);
		int max = (int)Math.pow(2,length - 1)-1;
		int min = -(int)Math.pow(2,length-1);
		if(value < min || value > max){
			throw new Exception("Value "+value+" cannot be represented as a signed number in "+
					length+" bits at line "+token.beginLine+" column "+token.beginColumn);
		}
		return TO_BIT_ARRAY(value,length);
	}
	public static char[] HEX_TO_BITS(Token token,int length)throws Exception{
		if(length <1){
			throw new Exception("bit length must > 1");
		}
		String str = token.image.substring(1);
		//System.out.println("a:"+str);
		char[] temp = HEX_TO_BITS(token);
		System.out.println(temp);
		int value = Word.calValue(temp);
		//System.out.println("C:"+value);
		int max = (int)Math.pow(2,length - 1)-1;
		int min = -(int)Math.pow(2,length-1);
		if(value < min || value > max){
			throw new Exception("Value "+value+" cannot be represented as a signed number in "+
					length+" bits at line "+token.beginLine+" column "+token.beginColumn);
		}
		return TO_BIT_ARRAY(value,length);
	}
	public static char[] DECIMAL_TO_BITS(Token token,int length)throws Exception{
		if(length <1){
			throw new Exception("bit length must > 1");
		}
		String str = token.image.substring(1);
		int value = Integer.parseInt(str);
		int max = (int)Math.pow(2,length - 1)-1;
		int min = -(int)Math.pow(2,length-1);
		if(value < min || value > max){
			throw new Exception("Value "+value+" cannot be represented as a signed number in "+
					length+" bits at line "+token.beginLine+" column "+token.beginColumn);
		}
		return TO_BIT_ARRAY(value,length);
	}
	public static char[] TO_BIT_ARRAY(int value,int length)throws Exception{
		//System.out.println("c:"+value+" length:"+length);
		String binary = Integer.toBinaryString(value);
		//System.out.println(binary);
		char[] result;
		if(binary.length()>=length){
			//为负数
			result = binary.substring(binary.length()-length).toCharArray();
		}else{
			//正数且位数不足
			int t = length-binary.length();
			for(int i=0;i<t;i++){
				binary = '0'+binary;
			}
			result = binary.toCharArray();
		}
		//System.out.println(result);
		return result;
	}
	public static char[] OCTAL_TO_BITS(Token token){
		String str = token.image.substring(1);
		char[] bits = new char[str.length()*3];
		int index = 0;
		for(int i=0;i<str.length();i++){
			int j = Integer.valueOf(str.charAt(i)-'0');
			if(j>=4){
				bits[index] = '1';
				j = j-4;
			}else{
				bits[index] = '0';
			}
			index++;
			if(j>=2){
				bits[index] = '1';
				j = j-2;
			}else{
				bits[index] = '0';
			}
			index++;
			if(j == 1){
				bits[index] = '1';
			}else{
				bits[index] = '0';
			}
			index++;
		}
		return bits;
	}
	
	public static char[] HEX_TO_BITS(Token token){
		String str = token.image.substring(1);
		char[] bits = new char[str.length()*4];
		int index = 0;
		for(int i=0;i<str.length();i++){
			int j = Integer.parseInt(""+str.charAt(i),16);
			if(j>=8){
				bits[index] = '1';
				j = j-8;
			}else{
				bits[index] = '0';
			}
			index++;
			if(j>=4){
				bits[index] = '1';
				j = j-4;
			}else{
				bits[index] = '0';
			}
			index++;
			if(j>=2){
				bits[index] = '1';
				j = j-2;
			}else{
				bits[index] = '0';
			}
			index++;
			if(j == 1){
				bits[index] = '1';
			}else{
				bits[index] = '0';
			}
			index++;
		}
		return bits;
	}
	/*
	public static char[] DECIMAL_TO_BITS(Token token){
		String str = "";
		int value = Integer.parseInt(token.image.substring(1));
		while(value > 1){
			if(value%2 == 1){
				str = '1'+str;
			}else{
				str = '0'+str;
			}
			value = value/2;
		}
		if(value == 1){
			str = '1'+str;
		}else{
			str = '0'+str;
		}
		return str.toCharArray();
	}
	*/
}

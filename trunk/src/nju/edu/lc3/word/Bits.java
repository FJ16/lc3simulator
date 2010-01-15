package nju.edu.lc3.word;


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

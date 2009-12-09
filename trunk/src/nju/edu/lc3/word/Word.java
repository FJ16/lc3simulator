package nju.edu.lc3.word;

import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.*;

public class Word {
	public static final int WORD_LENGTH = 16;
	public static int MAX_VALUE;
	public static int MIN_VALUE;
	public char[] bits;
	
	public Word() throws Exception{
		bits = new char[16];
		for(int i = 0 ;i < WORD_LENGTH;i++){
			bits[i] = '0';
		}
		MAX_VALUE = (int)Math.pow(2,WORD_LENGTH-1)-1;
		MIN_VALUE = -(int)Math.pow(2,WORD_LENGTH-1);
		
	}
	public void setBits(char i,int index)throws Exception{
		if(index<0||index>WORD_LENGTH-1){
			throw new Exception("index out of word range");
		}
		bits[WORD_LENGTH-1-index] = i;
	}
	public void setBits(char[] i,int start,int end)throws Exception
	{
		start = WORD_LENGTH-1-start;
		end = WORD_LENGTH-1-end;
		if(start > end){
			throw new Exception("end must be greater than start");
		}
		if(start < 0 || end > WORD_LENGTH-1){
			throw new Exception("start or end out of word range");
		}
		if(i.length != end - start+1){
			//System.out.println(i.length);
			throw new Exception("value length must be equal with set range");
		}
		for(int index=start;index<=end;index++){
			bits[index] = i[index-start];
		}
	}
	
	public Word(Token token)throws Exception{
		this();
		if(token.kind != LC3ParserConstants.DECIMAL&&
				token.kind != LC3ParserConstants.OCTAL&&
				token.kind != LC3ParserConstants.HEX){
			throw new Exception("token is not hex,decimal or octal type at line "+token.beginLine+" column "+token.beginColumn);
		}
		switch(token.kind){
		case LC3ParserConstants.DECIMAL:
			Decimal_to_Word(token);break;
		case LC3ParserConstants.OCTAL:
			Octal_to_Word(token);break;
		case LC3ParserConstants.HEX:
			Hex_to_Word(token);break;
		}
	}
	
	private void Octal_to_Word(Token token) throws Exception{
		String str = token.image.substring(1);
		if(str.length()*3 > WORD_LENGTH){
			throw new Exception("Octal value out of range at line "+token.beginLine+" column "+token.beginColumn);
		}
		char[] temp = new char[str.length()*3];
		
	}

	private void Hex_to_Word(Token token) throws Exception{
		String str = token.image.substring(1);
		if(str.length()*4 > WORD_LENGTH){
			throw new Exception("Hex value out of range at line "+token.beginLine+" column "+token.beginColumn);
		}
	}

	private void Decimal_to_Word(Token token) throws Exception{
		String str = token.image.substring(1);
		int value = Integer.parseInt(str);
		if(value < MIN_VALUE || value > MAX_VALUE){
			throw new Exception("Decimal value out of range at line "+token.beginLine+" column "+token.beginColumn);
		}
	}

	public static int calValue(char[] bits){
		int value = 0;
		if(bits.length >= WORD_LENGTH){
			for(int i =0 ; i<bits.length;i++){
				if(i ==0 ){
					value+= -1*value(bits[i])*Math.pow(2,bits.length-1-i);
				}else{
					value+=value(bits[i])*Math.pow(2,bits.length-1-i);
				}
			}
		}else{
			for(int i =0 ; i<bits.length;i++){
				value+=value(bits[i])*Math.pow(2,bits.length-1-i);
			}
		}
		
		
		return value;
	}
	private static int value(char i){
		if (i=='0'){
			return 0;
		}else{
			return 1;
		}
	}
}

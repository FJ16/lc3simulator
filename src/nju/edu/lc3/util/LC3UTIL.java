package nju.edu.lc3.util;

import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.word.*;
import nju.edu.lc3.parser.*;

public class LC3UTIL {
	
	public static int getRegisterId(Token token)throws Exception{
		int id = Integer.parseInt(token.image.substring(1));
		if(id <0 || id > 7){
			throw new Exception("Register out of range at line "+token.beginLine+" column "+token.beginColumn);
		}
		return id;
	}
	
	public static Word TO_WORD(Token token)throws Exception{
		Word word = new Word(token);
		return word;
	}
	public static Exception generateException(String text,int l,int c)throws Exception{
		return new Exception(text+" at line "+l+" column "+c);
	}
	public static char[] TO_BITS(Token token,int length) throws Exception{
		//作为偏移的操作数，先转为十进制，后转为二进制代码
		switch(token.kind){
		case LC3ParserConstants.DECIMAL:
			return Bits.DECIMAL_TO_BITS(token,length);
		case LC3ParserConstants.HEX:
			return Bits.HEX_TO_BITS(token,length);
		case LC3ParserConstants.OCTAL:
			return Bits.OCTAL_TO_BITS(token,length);
		}
		return null;
	}
}

package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.*;
public class STRINGZ extends Instruction{
	String str;
	public STRINGZ(Token token,int offset){
		super(LC3ParserConstants.STRINGZ,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReadState addToken(Token token) throws Exception {
		if(token.kind != LC3ParserConstants.STRING){
			throw LC3UTIL.generateException("String expected", token.beginLine, token.beginColumn);
		}
		int length = token.image.length();
		str = token.image.substring(1,length-1);
		return ReadState.Complete;
	}

	@Override
	public int getWordLength() {
		return str.length()+1;
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[str.length()+1];
		result[str.length()]=new Word();
		for(int i=0;i<str.length();i++){
			result[i] = new Word();
			result[i].setBits(Bits.TO_BIT_ARRAY(str.charAt(i), 8), 7,0);
		}
		return result;
	}
}

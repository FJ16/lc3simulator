package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Word;
public class FILL extends Instruction{
	char[] value;
	public FILL(Token token,int offset){
		super(LC3ParserConstants.FILL,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReadState addToken(Token token) throws Exception {
		if(token.kind == LC3ParserConstants.DECIMAL||
				token.kind == LC3ParserConstants.OCTAL||
				token.kind == LC3ParserConstants.HEX){
			value = LC3UTIL.TO_BITS(token,16);
			return ReadState.Complete;
		}else{
			throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
		}
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(value, 15, 0);
		return result;
		
	}
}
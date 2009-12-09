package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class TRAP extends Instruction{
	char[] opcode={'1','1','1','1'};
	char[] offset;
	public TRAP(Token token,int offset){
		super(LC3ParserConstants.TRAP,token,offset);
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
			offset = LC3UTIL.TO_BITS(token,8);
			return ReadState.Complete;
		}else{
			throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
		}
	}
	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15,12);
		result[0].setBits(offset, 7,0);
		return result;
	}
}
package nju.edu.lc3.instruction.model;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.instruction.Instruction;
import nju.edu.lc3.instruction.ReadState;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class NOT extends Instruction{
	char[] opcode={'1','0','0','1'};
	int dr;
	int sr;
	int step = 1;
	public NOT(Token token,int offset){
		super(LC3ParserConstants.NOT,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ReadState addToken(Token token) throws Exception {
		switch(step){
		case 1:
			if(token.kind != LC3ParserConstants.REGISTER){
				throw LC3UTIL.generateException("register expected", token.beginLine, token.beginColumn);
			}
			dr = LC3UTIL.getRegisterId(token);
			step++;
			return ReadState.Continue;
		case 3:
			if(token.kind != LC3ParserConstants.REGISTER){
				throw LC3UTIL.generateException("register expected", token.beginLine, token.beginColumn);
			}
			sr = LC3UTIL.getRegisterId(token);
			step++;
			return ReadState.Complete;
		case 2:
			if(token.kind != LC3ParserConstants.COMMA){
				throw LC3UTIL.generateException("comma expected", token.beginLine, token.beginColumn);
			}
			step++;
			return ReadState.Continue;
		}
		return ReadState.Continue;
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15, 12);
		char[] drBits = Bits.TO_BIT_ARRAY(dr, 3);
		result[0].setBits(drBits, 11,9);
		char[] srBits = Bits.TO_BIT_ARRAY(sr, 3);
		result[0].setBits(srBits, 8,6);
		result[0].setBits(new char[]{'1','1','1','1','1','1'}, 5,0);
		return result;
	}
}
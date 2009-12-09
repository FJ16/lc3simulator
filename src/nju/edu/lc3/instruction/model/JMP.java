package nju.edu.lc3.instruction.model;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.instruction.Instruction;
import nju.edu.lc3.instruction.ReadState;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.*;
public class JMP extends Instruction{
	char[] opcode={'1','1','0','0'};
	int br;
	
	public JMP(Token token,int offset){
		super(LC3ParserConstants.JMP,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ReadState addToken(Token token) throws Exception {
		if(token.kind != LC3ParserConstants.REGISTER){
			throw LC3UTIL.generateException("register expected", token.beginLine, token.beginColumn);
		}
		br = LC3UTIL.getRegisterId(token);
		return ReadState.Complete;
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15, 12);
		result[0].setBits(Bits.TO_BIT_ARRAY(br, 3), 8,6);
		return result;
	}
}
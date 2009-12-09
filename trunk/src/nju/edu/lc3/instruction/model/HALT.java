package nju.edu.lc3.instruction.model;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.instruction.Instruction;
import nju.edu.lc3.instruction.ReadState;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.word.Word;

public class HALT extends Instruction{
	public HALT(Token token,int offset){
		super(LC3ParserConstants.HALT,token,offset);
	}
	@Override
	public ReadState addToken(Token token) throws Exception {
		// TODO Auto-generated method stub
		return ReadState.Optional;
	}

	@Override
	public void parseFromWord(Word word) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception {
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits("1111000000100101".toCharArray(), 15,0);
		return result;
	}

}

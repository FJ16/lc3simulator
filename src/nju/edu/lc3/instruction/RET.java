package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class RET extends Instruction{
	char[] opcode={'1','1','0','0'};
	public RET(Token token,int offset){
		super(LC3ParserConstants.RET,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReadState addToken(Token token) throws Exception {
		// TODO Auto-generated method stub
		return ReadState.Optional;
	}
	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15, 12);
		result[0].setBits(new char[]{'1','1','1'}, 8,6);
		return result;
	}
}
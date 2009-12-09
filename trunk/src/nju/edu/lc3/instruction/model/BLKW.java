package nju.edu.lc3.instruction.model;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.instruction.Instruction;
import nju.edu.lc3.instruction.ReadState;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Word;

public class BLKW extends Instruction{
	int count =0;
	char[] initValue = null;
	int step = 1;
	public BLKW(Token token,int offset){
		super(LC3ParserConstants.BLKW,token,offset);
	}

	@Override
	public ReadState addToken(Token token) throws Exception {
		switch(step){
		case 1:
			if(token.kind == LC3ParserConstants.COUNT){
				count = Integer.parseInt(token.image);
				step++;
				return ReadState.Continue;
			}else{
				throw LC3UTIL.generateException("line count expected", token.beginLine, token.beginColumn);
			}
		case 2:
			if(token.kind == LC3ParserConstants.DECIMAL||
					token.kind == LC3ParserConstants.OCTAL||
					token.kind == LC3ParserConstants.HEX){
				initValue = LC3UTIL.TO_BITS(token,16);
				return ReadState.Complete;
			}else{
				return ReadState.Optional;
			}
		}
		return ReadState.Continue;
	}

	@Override
	public void parseFromWord(Word word) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getWordLength(){
		return count;
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception {
		Word[] result = new Word[count];
		for(int i=0;i<count;i++){
			result[i] = new Word();
		}
		return result;
	}

}

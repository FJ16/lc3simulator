package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class STR extends Instruction{
	char[] opcode={'0','1','1','1'};
	int sr;
	int br;
	char[] offset;
	int step = 1;
	public STR(Token token,int offset){
		super(LC3ParserConstants.STR,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addToken(Token token) throws Exception {
				switch(step){
		case 1:
			if(token.kind != LC3ParserConstants.REGISTER){
				throw LC3UTIL.generateException("register expected", token.beginLine, token.beginColumn);
			}
			sr = LC3UTIL.getRegisterId(token);
			step++;
			return false;
		case 3:
			if(token.kind != LC3ParserConstants.REGISTER){
				throw LC3UTIL.generateException("register expected", token.beginLine, token.beginColumn);
			}
			br = LC3UTIL.getRegisterId(token);
			step++;
			return false;
		case 5:
			if(token.kind == LC3ParserConstants.DECIMAL||
					token.kind == LC3ParserConstants.OCTAL||
					token.kind == LC3ParserConstants.HEX){
				offset = LC3UTIL.TO_BITS(token,6);
				return true;
			}else{
				throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
			}
		case 2:
		case 4:
			if(token.kind != LC3ParserConstants.COMMA){
				throw LC3UTIL.generateException("comma expected", token.beginLine, token.beginColumn);
			}
			step++;
			return false;
		}
		return false;
	}


	@Override
	public Word[] toWord(CodeBase cb)throws Exception {
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15, 12);
		char[] srBits = Bits.TO_BIT_ARRAY(sr, 3);
		result[0].setBits(srBits, 11,9);
		char[] brBits = Bits.TO_BIT_ARRAY(br, 3);
		result[0].setBits(brBits, 8,6);
		result[0].setBits(offset, 5,0);
		return result;
	}
}
package nju.edu.lc3.instruction.model;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.instruction.Instruction;
import nju.edu.lc3.instruction.ReadState;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class STI extends Instruction{
	char[] opcode={'1','0','1','1'};
	int sr;
	char[] offset;
	String label;
	boolean useLabel;
	int step = 1;
	public STI(Token token,int offset){
		super(LC3ParserConstants.STI,token,offset);
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
			sr = LC3UTIL.getRegisterId(token);
			step++;
			return ReadState.Continue;
		case 3:
			if(token.kind == LC3ParserConstants.ID){
				label = token.image;
				useLabel = true;
				return ReadState.Complete;
			}else if(token.kind == LC3ParserConstants.DECIMAL||
					token.kind == LC3ParserConstants.OCTAL||
					token.kind == LC3ParserConstants.HEX){
				offset = LC3UTIL.TO_BITS(token,9);
				useLabel = false;
				return ReadState.Complete;
			}else{
				throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
			}
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
		char[] srBits = Bits.TO_BIT_ARRAY(sr, 3);
		result[0].setBits(srBits, 11,9);
		if(useLabel){
			Integer o = cb.getLabelOffset(label);
			if(o==null){
				throw new Exception("Label "+label+" not found at line "+super.opcode.beginLine);
			}
			char[] labelOffset = null;
			try{
				labelOffset = Bits.TO_BIT_ARRAY(o.intValue()-super.baseOffset-1, 9);
			}catch(Exception e){
				throw new Exception("Label "+label +" cannot be reached at line "+super.opcode.beginLine);
			}
			
			result[0].setBits(labelOffset, 8,0);
		}else{
			result[0].setBits(offset, 8,0);
		}
		return result;
	}
}
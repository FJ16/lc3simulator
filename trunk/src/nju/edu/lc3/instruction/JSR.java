package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class JSR extends Instruction{
	char[] opcode={'0','1','0','0'};
	String label;
	boolean useLabel;
	char[] offset;
	public JSR(Token token,int offset){
		super(LC3ParserConstants.JSR,token,offset);
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReadState addToken(Token token) throws Exception {
		if(token.kind == LC3ParserConstants.ID){
			label = token.image;
			useLabel = true;
			return ReadState.Complete;
		}else if(token.kind == LC3ParserConstants.DECIMAL||
				token.kind == LC3ParserConstants.OCTAL||
				token.kind == LC3ParserConstants.HEX){
			offset = LC3UTIL.TO_BITS(token,11);
			useLabel = false;
			return ReadState.Complete;
		}else{
			throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
		}	
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15, 12);
		result[0].setBits('1', 11);
		if(useLabel){
			Integer o = cb.getLabelOffset(label);
			if(o==null){
				throw new Exception("Label "+label+" not found at line "+super.opcode.beginLine);
			}
			char[] labelOffset = null;
			try{
				labelOffset = Bits.TO_BIT_ARRAY(o.intValue()-super.baseOffset-1, 11);
			}catch(Exception e){
				throw new Exception("Label "+label +" cannot be reached at line "+super.opcode.beginLine);
			}
			result[0].setBits(labelOffset, 10,0);
		}{
			result[0].setBits(offset, 10,0);
		}
		return result;
	}
}
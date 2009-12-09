package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.LC3UTIL;
import nju.edu.lc3.word.Bits;
import nju.edu.lc3.word.Word;
public class LEA extends Instruction{
	char[] opcode={'1','1','1','0'};
	int dr;
	char[] offset;
	String label;
	boolean useLabel;
	int step = 1;
	public LEA(Token token,int offset){
		super(LC3ParserConstants.LEA,token,offset);
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
			dr = LC3UTIL.getRegisterId(token);
			step++;
			return false;
		case 3:
			if(token.kind == LC3ParserConstants.ID){
				label = token.image;
				useLabel = true;
				return true;
			}else if(token.kind == LC3ParserConstants.DECIMAL||
					token.kind == LC3ParserConstants.OCTAL||
					token.kind == LC3ParserConstants.HEX){
				offset = LC3UTIL.TO_BITS(token,9);
				useLabel = false;
				return true;
			}else{
				throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
			}
		case 2:
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
		char[] drBits = Bits.TO_BIT_ARRAY(dr, 3);
		result[0].setBits(drBits, 11,9);
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
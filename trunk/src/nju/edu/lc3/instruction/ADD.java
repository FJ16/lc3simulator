package nju.edu.lc3.instruction;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3ParserConstants;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.util.*;
import nju.edu.lc3.word.*;
public class ADD extends Instruction{
	char[] opcode = {'0','0','0','1'};
	int dr;
	int sr1;
	int sr2;
	char[] offset;
	String label;
	boolean register = true;
	boolean useLabel = false;
	int step = 1;
	public ADD(Token token, int offset){
		super(LC3ParserConstants.ADD,token,offset);
		//System.out.println("Add instruction created!");
	}

	@Override
	public void parseFromWord(Word word) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Word[] toWord(CodeBase cb) throws Exception{
		Word[] result = new Word[1];
		result[0] = new Word();
		result[0].setBits(opcode, 15, 12);
		char[] drBits = Bits.TO_BIT_ARRAY(dr, 3);
		result[0].setBits(drBits, 11,9);
		char[] srBits = Bits.TO_BIT_ARRAY(sr1, 3);
		result[0].setBits(srBits, 8,6);
		if(register){
			result[0].setBits(new char[]{'0','0','0'}, 5,3);
			char[] sr2Bits = Bits.TO_BIT_ARRAY(sr2, 3);
			result[0].setBits(sr2Bits, 2,0);
		}else{
			if(useLabel){
				Integer o = cb.getLabelOffset(label);
				if(o==null){
					throw new Exception("Label "+label+" not found at line "+super.opcode.beginLine);
				}
				char[] labelOffset = null;
				try{
					labelOffset = Bits.TO_BIT_ARRAY(o.intValue()-super.baseOffset-1, 5);
				}catch(Exception e){
					throw new Exception("Label "+label +" cannot be reached at line "+super.opcode.beginLine);
				}
				result[0].setBits('1', 5);
				
				result[0].setBits(labelOffset, 4,0);
			}else{
				result[0].setBits('0', 5);
				result[0].setBits(offset, 4,0);
			}
		}
		return result;
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
			sr1 = LC3UTIL.getRegisterId(token);
			step++;
			return ReadState.Continue;
		case 5:
			if(token.kind == LC3ParserConstants.REGISTER){
				sr2 = LC3UTIL.getRegisterId(token);
				register = true;
				return ReadState.Complete;
			}else if(token.kind == LC3ParserConstants.ID){
				label = token.image;
				register = false;
				useLabel = true;
				return ReadState.Complete;
			}else if(token.kind == LC3ParserConstants.DECIMAL||
					token.kind == LC3ParserConstants.OCTAL||
					token.kind == LC3ParserConstants.HEX){
				offset = LC3UTIL.TO_BITS(token,5);
				register = false;
				useLabel = false;
				return ReadState.Complete;
			}else{
				throw LC3UTIL.generateException("register or immediate value expected", token.beginLine, token.beginColumn);
			}
		case 2:
		case 4:
			if(token.kind != LC3ParserConstants.COMMA){
				throw LC3UTIL.generateException("comma expected", token.beginLine, token.beginColumn);
			}
			step++;
			return ReadState.Continue;
		}
		return ReadState.Complete;
	}
}

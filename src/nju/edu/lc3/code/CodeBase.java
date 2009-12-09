package nju.edu.lc3.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nju.edu.lc3.instruction.*;
import nju.edu.lc3.instruction.model.ADD;
import nju.edu.lc3.instruction.model.AND;
import nju.edu.lc3.instruction.model.BLKW;
import nju.edu.lc3.instruction.model.BR;
import nju.edu.lc3.instruction.model.FILL;
import nju.edu.lc3.instruction.model.HALT;
import nju.edu.lc3.instruction.model.JMP;
import nju.edu.lc3.instruction.model.JSR;
import nju.edu.lc3.instruction.model.JSRR;
import nju.edu.lc3.instruction.model.LD;
import nju.edu.lc3.instruction.model.LDI;
import nju.edu.lc3.instruction.model.LDR;
import nju.edu.lc3.instruction.model.LEA;
import nju.edu.lc3.instruction.model.NOT;
import nju.edu.lc3.instruction.model.ORIG;
import nju.edu.lc3.instruction.model.RET;
import nju.edu.lc3.instruction.model.RTI;
import nju.edu.lc3.instruction.model.ST;
import nju.edu.lc3.instruction.model.STI;
import nju.edu.lc3.instruction.model.STR;
import nju.edu.lc3.instruction.model.STRINGZ;
import nju.edu.lc3.instruction.model.TRAP;
import nju.edu.lc3.parser.*;
import nju.edu.lc3.word.Word;

public class CodeBase {
	private static CodeBase instance = null;
	int startAddress = -1;//代码段真实地址
	int offset = 0;
	Instruction instruction = null;
	HashMap<String,Integer> labels = new HashMap<String,Integer>();
	ArrayList<Instruction> instructions = new ArrayList<Instruction>();
	
	private CodeBase(){}
	public static CodeBase getInstance(){
		if(instance == null){
			instance = new CodeBase();
		}
		return instance;
	}
	public void add(Instruction ins){
		offset += ins.getWordLength();
		instructions.add(ins);
	}
	
	public List<Instruction> getInstructions(){
		return instructions;
	}
	
	public Integer getLabelOffset(String label)throws Exception{
		Integer result = labels.get(label);
		return result;
	}
	
	public Instruction newInstruction(Token token)throws Exception{
		System.out.println("new Instruction:"+token.image+" kind:"+token.kind);
		if(startAddress < 0 && token.kind != LC3ParserConstants.ORIG){
			throw new Exception(".ORIG Excepted at line "+token.beginLine+" column "+token.beginColumn);
		}
		if(startAddress < 0){
			startAddress = 0;
		}
		if(instruction!=null){
			instruction = null;
		}
		if(token.kind == LC3ParserConstants.ID){
			System.out.println("Label:"+token.image+" Offset:"+offset);
			Integer i = labels.get(token.image);
			if(i != null){
				throw new Exception("Duplicate label \""+token.image+"\" at line "+token.beginLine +" column "+token.beginColumn);
			}
			labels.put(token.image, offset);
			return null;
		}else{
			switch(token.kind){
			case LC3ParserConstants.ADD:instruction = new ADD(token,offset);break;
			case LC3ParserConstants.AND:instruction = new AND(token,offset);break;
			case LC3ParserConstants.BR:instruction = new BR(token,offset);break;
			case LC3ParserConstants.JMP:instruction = new JMP(token,offset);break;
			case LC3ParserConstants.JSR:instruction = new JSR(token,offset);break;
			case LC3ParserConstants.JSRR:instruction = new JSRR(token,offset);break;
			case LC3ParserConstants.LD:instruction = new LD(token,offset);break;
			case LC3ParserConstants.LDI:instruction = new LDI(token,offset);break;
			case LC3ParserConstants.LDR:instruction = new LDR(token,offset);break;
			case LC3ParserConstants.LEA:instruction = new LEA(token,offset);break;
			case LC3ParserConstants.NOT:instruction = new NOT(token,offset);break;
			case LC3ParserConstants.RET:add(new RET(token,offset));return null;
			case LC3ParserConstants.RTI:add(new RTI(token,offset));return null;
			case LC3ParserConstants.ST:instruction = new ST(token,offset);break;
			case LC3ParserConstants.STI:instruction = new STI(token,offset);break;
			case LC3ParserConstants.STR:instruction = new STR(token,offset);break;
			case LC3ParserConstants.TRAP:instruction = new TRAP(token,offset);break;
			case LC3ParserConstants.FILL:instruction = new FILL(token,offset);break;
			case LC3ParserConstants.STRINGZ:instruction = new STRINGZ(token,offset);break;
			case LC3ParserConstants.ORIG:instruction = new ORIG(token,offset);break;
			case LC3ParserConstants.BLKW:instruction = new BLKW(token,offset);break;
			case LC3ParserConstants.HALT:instruction = new HALT(token,offset);break;
			}
			if(instruction == null){
				throw new Exception("Opcodes Excepted at line "+token.beginLine+" column "+token.beginColumn);
			}else{
				return instruction;
			}
		}
	}
	public void showBinaryInstructions() throws Exception{
		for(Instruction ins:instructions){
			Word[] word = ins.toWord(this);
			for(Word w:word){
				System.out.println(w.bits);
			}
		}
	}
}

package nju.edu.lc3.instruction;

import java.util.ArrayList;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.Token;
import nju.edu.lc3.word.Word;
import nju.edu.lc3.util.*;

public abstract class Instruction {
	/*
	final int REGISTER_TYPE = 0x0001;
	final int IMMEDIATE_TYPE = 0x0002;
	final int LABEL_TYPE = 0x0004;
	*/
	
	final int opType;
	Token opcode;
	int wordLength = 0;
	int baseOffset = 0;
	ArrayList<Token> list;
	public Instruction(int opType,Token opcode,int offset){
		this.opType = opType;
		this.opcode = opcode;
		this.baseOffset = offset;
		list = new ArrayList<Token>();
	}
	abstract public ReadState addToken(Token token)throws Exception;//若指令所有参数已加载完成，则返回true，否则返回false
	
	abstract public Word[] toWord(CodeBase cb)throws Exception;//生成二进制机器码
	abstract public void parseFromWord(Word word)throws Exception;//从机器码中解析指令相关信息，比如操作数，寄存器之类
	public int getWordLength(){
		return 1;
	}
	
}

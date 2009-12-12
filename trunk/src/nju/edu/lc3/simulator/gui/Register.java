package nju.edu.lc3.simulator.gui;

public enum Register {
	al,cl,dl,bl,ah,ch,dh,bh,//8 bits register
	ax,cx,dx,bx,sp,bp,si,di,	//16 bits register
	ip,	
	cs,ds,es,ss;			//segment register
	
	public static int getPos(Register type){
		return 8;
	}
	public static boolean has(String str){
		try{
			Register.valueOf(str);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	public static String getSegment(String str){
		if(str.equals("00101110")) return "cs";
		if(str.equals("00111110")) return "ds";
		if(str.equals("00100110")) return "es";
		if(str.equals("00110110")) return "ss";
		return "";
	}
	public static boolean segment(String str){
		if(str.equals("00101110")) return true;
		if(str.equals("00111110")) return true;
		if(str.equals("00100110")) return true;
		if(str.equals("00110110")) return true;
		return false;
	}
	public static boolean isSegment(String str){
		if(!has(str)) return false;
		Register type=valueOf(str);
		if(type==cs||type==ds||type==es||type==ss) return true;
		return false;
	}
	public static boolean isSegment(Register type){
		if(type==cs||type==ds||type==es||type==ss) return true;
		return false;
	}
	/*In 8086 machine code,every segment register has it's code.
	 * the function getSegmentRegister() will return it's machine code.
	 * */
	public static String getSegmentRegister(Register type){
		if(type==null) return "";
		if(type==cs) 	return ""+"00101110";
		if(type==ds)	return ""+"00111110";
		if(type==es)	return ""+"00100110";
		if(type==ss) 	return ""+"00110110";
		return "";
	}
	public static Register segRegister(int seg){
		if(seg==46)//""+"00101110";
			return cs;
		if(seg==62)//"00111110"
			return ds;
		if(seg==38)//"00100110"
			return es;
		if(seg==54)//"00110110"
			return ss;
		return null;
	}
	
	public static String getSSS(String str){
		if(str.equals("000")) return es.toString();
		if(str.equals("001")) return cs.toString();
		if(str.equals("010")) return ss.toString();
		if(str.equals("011")) return ds.toString();
		return "";
	}
	public static String getSSS(Register type){
		if(type==es)	return ""+"000";
		if(type==cs)	return ""+"001";
		if(type==ss)	return ""+"010";
		if(type==ds)	return ""+"011";
		return "";
	}
	public static boolean isRegWord(Register str){
		Register type=str;
		return type==ax||type==bx||type==si||type==di||type==bp||type==sp||type==dx||type==cx;
	}
	public static String getRegister(String str,int len){//if len=1 it will return 16bits register:word,if len=0,it will return 8bits.
		if(str.equals("000")) {
			if(len==1) return "ax"; 
			else return "al";
		}
		if(str.equals("001")) {
			if(len==1) return "cx"; 
			else return "cl";
		}
		if(str.equals("010")) {
			if(len==1) return "dx"; 
			else return "dl";
		}
		if(str.equals("011")) {
			if(len==1) return "bx"; 
			else return "bl";
		}
		if(str.equals("100")) {
			if(len==1) return "sp"; 
			else return "ah";
		}
		if(str.equals("101")) {
			if(len==1) return "bp"; 
			else return "ch";
		}
		if(str.equals("110")) {
			if(len==1) return "si"; 
			else return "dh";
		}
		if(str.equals("111")) {
			if(len==1) return "di"; 
			else return "bh";
		}
		return "";
	}
	public static String getIndexReg(Register type){
		if(type==ax||type==al) return "000";
		if(type==cx||type==cl) return ""+"001";
		if(type==dx||type==dl) return ""+"010";
		if(type==bl||type==bx) return ""+"011";
		if(type==ah||type==sp) return ""+"100";
		if(type==ch||type==bp) return ""+"101";
		if(type==dh||type==si) return ""+"110";
		if(type==bh||type==di) return ""+"111";
		return "";
	}
	public static String isByte(Register str){
		Register type=str;
		if(type==al||type==ah||type==bl||type==bh||type==ch||type==cl||type==dh||type==dl) return ""+0;
		return ""+1;
	}
}

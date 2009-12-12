package nju.edu.lc3.simulator.gui;
import javax.swing.UIManager;
public class Console {
	public static void main(String[] args){
		/*Parsing parse=new Parsing();
		parse.getTokens("txt.txt");parse.print();
		parse.parse();
		
		/*Parsing go=new Parsing();
		TreeNode temp=new TreeNode();
		Token token=new Token("near ptr 123h");
		temp.getPara(token);*/
		/*imm8,imm16,
	bxsi,bxdi,bpsi,bpdi,si,di,d16,bx,bp,		//mode=00
	bxsid8,bxdid8,bpsid8,bpdid8,sid8,did8,bpd8,bxd8,	//mod=01
	bxsid16,bxdid16,bpsid16,bpdid16,sid16,did16,bpd16,bxd16,other;	//mod=10
	
		 */
		/*Segment temp=new Segment(23,"ffff");
		LinkedList<Segment> seg=new LinkedList<Segment>();
		seg.add(temp);seg.add(new Segment(23,"gggg"));
		seg.add(new Segment(344,"ffff"));
		System.out.println(seg.size());
		System.out.println(temp.name+"    index:"+seg.indexOf((Object)temp.name));
		System.out.println(seg.get(0).equals(temp.name));*/
		//parse.generate();
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		//SetValueView.getInstance();
		//new IOConsole();
		new Simulator();
		//AddBreakPoint.getAddBreakPoint();
	}
}

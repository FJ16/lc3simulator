package nju.edu.lc3.simulator.gui;
/*There are two kind of 8086 assemble lauguage sentences:
 * idengifier:  instruction   parame,parame
 * name			pseudoinstructioin	choice,choice...
 * so the tokens read from the file can be diveded into the six kinds.
 * so it is the TreeNode.
 * */
public enum TokenType {
	identifier,
	name,
	pseudoinstruction,
	instruction,
	choice,
	parame;

}

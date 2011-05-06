package Main;


import Translator.ASTStart;
import Translator.ASTcheckCycle;
import Translator.ASTcheckIteration;
import Translator.ASTcheckLine;
import Translator.ASTcheckRandom;
import Translator.ASTcheckReturn;
import Translator.ASTcheckRoll;
import Translator.ASTcheckSelect;
import Translator.ASTStart;
import Translator.ASTtest;
import Translator.CubeExecuterVisitor;
import Translator.SimpleNode;

public class CubeVisitor implements CubeExecuterVisitor{
	private RubiksCubeInterface cubeInterface;

	public CubeVisitor(RubiksCubeInterface cubeInterface) {
		super();
		this.cubeInterface = cubeInterface;
	}

	@Override
	public Object visit(SimpleNode node, Object data) {
		System.out.println("1");
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckSelect node, Object data) {
		System.out.println("3");
		cubeInterface.select(node.getSide()); 
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckRoll node, Object data) {
		System.out.println("4");
		cubeInterface.roll(node.getStick(), node.getDir()); 
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckRandom node, Object data) {
		System.out.println("5");
		cubeInterface.random(node.getNumb1()); 
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckReturn node, Object data) {
		System.out.println("6");
		cubeInterface.returnTo(node.getNumb2()); 
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTStart node, Object data) {
		System.out.println("7");
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckLine node, Object data) {
		System.out.println("8");
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckIteration node, Object data) {
		System.out.println("9");
		node.childrenAccept(this, null);
		return null;
	}

	@Override
	public Object visit(ASTcheckCycle node, Object data) {
		System.out.println("10");
		int count = node.getCount();
		for (int i=0; i<count; i++)
			node.childrenAccept(this, null);
		return null;
	}

}

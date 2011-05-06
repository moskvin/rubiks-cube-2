package Main;

import Translator.CubeExecuter;
import Translator.ParseException;
import Translator.TokenMgrError;

public class MainPoint{
	private CubeExecuter cubeexecuter;
	
	public MainPoint(){
			CubeVisitor cubevisitor = new CubeVisitor(new TCube());
			cubeexecuter = new CubeExecuter(System.in);
			try {
				cubeexecuter.Init();
				cubeexecuter.start();
				cubeexecuter.rootNode().jjtAccept(cubevisitor, null);				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TokenMgrError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
  	public static void main(String[] args)
  	{
  		new MainPoint();
  	}


}

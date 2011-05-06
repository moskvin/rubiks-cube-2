package Main;

public class TCube implements RubiksCubeInterface{
	public void select( int side) {
		System.out.println("select!");
	}
	public void roll( int stick, int dir) {
		System.out.println("roll!");
	}
	public void random(int diff) {
		System.out.println("random!");
	}
	public void returnTo(int step) {
		System.out.println("returnTo!");
	}
}

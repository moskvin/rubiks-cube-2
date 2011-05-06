package Main;
public interface RubiksCubeInterface {
	
	public void select( int side);
	public void roll( int stick, int dir);
	public void random(int diff);
	public void returnTo(int step);
	
}

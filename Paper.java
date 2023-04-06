import javax.swing.ImageIcon;

public class Paper extends Hatsu{
	private boolean left;

	public Paper() {
		super();
	}
	public Paper(int x, int y, int dx, String animation) {
		super(x, y, 68, 46, dx, new ImageIcon(animation));
	}
	
	public void setLeft(boolean right) {
		this.left= right;
	}
	public boolean isLeft() {
		return left;
	}
	public void setImage() {
		if(left) {
			setAttack(new ImageIcon("GprojL.gif"));
		}
		if(!left) {
			setAttack(new ImageIcon("GprojR.gif"));
		}
	}

}

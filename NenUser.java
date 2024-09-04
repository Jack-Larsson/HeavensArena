import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class NenUser {

	private int x, y, w, h, dx, dy, health, range, melee, pw, ph, standY, punchW, punchY, runY, shootY, dashY;
	private ImageIcon img;
	private boolean punch, right, left, shoot, dash, run, jump;
	private String punchR, punchL, standR, standL, runR, runL, shootR, shootL, dashR, dashL;

	public NenUser() {
		x = 0;
		y = 0;
		w = 0;
		h = 0;
		dx = 0;
		dy = 0;
		health = 0;
		range = 0;
		melee = 0;
		pw = 0;
		ph = 0;
		punchW=0;
		standY=0;
		punchY=0;
		runY=0;
		shootY=0;
		dashY=0;
		img = new ImageIcon("");
		punchR="";
		punchL= "";
		standR="";
		standL="";
		runR="";
		runL="";
		shootR="";
		shootL="";
	}


	public NenUser(int posx, int posy, int width, int height, int lives, int projectile, int close, int stand, int punchrange, int punchheight, int shootheight, int runheight,int dashheight,  ImageIcon i, String punchRA,String punchLA, String shootRA, String shootLA, String standRA, String standLA, String runRA, String runLA, String DashRA, String DashLA) {

		x = posx;
		y = posy;
		w = width;
		h = height;
		health = lives;
		range = projectile;
		melee = close;
		img = i;
		punchR= punchRA;
		punchL= punchLA;
		standR= standRA;
		standL= standLA;
		runR= runRA;
		runL= runLA;
		standY= stand;
		punchW= punchrange;
		punchY= punchheight;
		runY= runheight;
		shootR= shootRA;
		shootL= shootLA;
		shootY= shootheight;
		dashY= dashheight;
		dashR= DashRA;
		dashL= DashLA;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public int getPunchW() {
		return punchW;
	}
	
	public void setPunchW(int punchW) {
		this.punchW = punchW;
	}
	
	public int getMelee() {
		return melee;
	}

	public void setMelee(int melee) {
		this.melee = melee;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	

	public String getStandR() {
		return standR;
	}


	public void setStandR(String standR) {
		this.standR = standR;
	}


	public boolean isPunch() {
		return punch;
	}

	public void setPunch(boolean punch) {
		this.punch = punch;
	}
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	public boolean isDash() {
		return dash;
	}


	public void setDash(boolean dash) {
		this.dash = dash;
	}
	public boolean isRun() {
		return run;
	}


	public void setRun(boolean run) {
		this.run = run;
	}


	public boolean isJump() {
		return jump;
	}


	public void setJump(boolean jump) {
		this.jump = jump;
	}


	public void drawUser(Graphics g2d) {
		g2d.drawImage(img.getImage(), x, y, w, h, null);
	}

	public void setAnimation() {
		if (punch) {
			if(!jump) {
			setY(punchY);
			}
				if (right) {
					img = new ImageIcon(punchR);
				}
				if (left) {
					img = new ImageIcon(punchL);
				}
		}
		if(shoot) {
			if(!jump) {
				setY(shootY);
				}
			if (right) {
				img = new ImageIcon(shootR);
			}
			if (left) {
				img = new ImageIcon(shootL);
			}
		}
		if (!punch&&dx==0&&!shoot) {
			if(!jump) {
				setY(standY);
				}
			if (right) {
				img = new ImageIcon(standR);
			}
			if (left) {
				img = new ImageIcon(standL);
			}
		}
		if(dx==3) {
			if(!jump) {
				setY(runY);
				}
			img= new ImageIcon(runR);
		}
		if(dx==-3) {
			if(!jump) {
				setY(runY);
				}
			img= new ImageIcon(runL);
		}
		if(dash) {
			if(!jump) {
				setY(dashY);
				}
		
			if(dx==10) {
				img= new ImageIcon(dashR);
		}
			if(dx==-10) {
				img= new ImageIcon(dashL);
		}
		}
		pw = getImg().getIconWidth();
		ph = getImg().getIconHeight();
		w = pw * 4;
		h = ph * 4;
	}

	// method to set boolean

	public void move() {
		x += dx;
		y += dy;
		if (x + w > 1920) {
			x = 1920 - w;
		}
		if (x < 0) {
			x = 0;
		}
		if(!dash&&!run) {
			dx=0;
		}
		if(run&&!dash) {
			if(left) {
				dx=-3;
			}
			if(right) {
				dx=3;
			}
		}
	}
	public void melee(int time) {
		if(time>67) {
			setPunch(false);
			if(isLeft()) {
				setX(getX()+punchW);
			}
		}
	}
	public void range(int time) {
		if(time>80) {
			setShoot(false);
		}
	}
	public void dash(int timer) {
		if(dash&&left) {
			dx=-10;
		}
		if(dash&&right) {
			dx=10;
		}
		if(timer>40) {
			dash=false;
		}
	}

	public boolean MCollision(NenUser Pog) {
		Rectangle Poggers = new Rectangle(Pog.getX(), Pog.getY(), Pog.getW(), Pog.getH());
		Rectangle Cringers = new Rectangle(getX(), getY(), getW(), getH());
		if (Poggers.intersects(Cringers)) {
			return true;
		
	}
	return false;
	}
	public boolean RCollision(Hatsu p) {
		Rectangle Poggers = new Rectangle(p.getX(), p.getY(), p.getW(), p.getH());
		Rectangle Cringers = new Rectangle(getX(), getY(), getW(), getH());
		if (Poggers.intersects(Cringers)) {
			return true;
		
	}
	return false;
	}
	
	public void gravity(int t) {
		Rectangle character = new Rectangle(getX(), getY(), getW(), getH());
		Rectangle ground = new Rectangle(0, 880, 1920, 200);

		if(!character.intersects(ground)) {
			if(t%5==0) {
				dy=dy+1;
			}
		}
		if(character.intersects(ground)) {
			dy=0;
			jump=false;
		}
	}

	
}

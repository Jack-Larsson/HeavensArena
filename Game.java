
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

	private BufferedImage back;
	private int key1, key2, screen, mX, mY, counter, last, last1, last2, pw, ph, Mcount, Rcount, CHcount, EMcount, ERcount, ECHcount, fatigue, StinkersL, Player1L, StinkersMaxL, Player1MaxL,dashtime, stinkerpopulation, stinkergenocide,  doublejump;
	private boolean punch, playing, KO, fight, shoot, Next, replay;
	private ArrayList<NenUser> selectList;
	private NenUser player1;
	private Hatsu hatsu;
	private Enhancer gon;
	private Emitter killua;
	private Conjurer kurapika;
	private String selected, SaveFile, check, savedplayer;
	private Color EnhancerG, EmitterB, ConjurerR;
	private ImageIcon arena;
	private Queue <NenUser> stinkers;
	private ArrayList<Hatsu> projectiles, StinkBombs;
	private File file;
	
	public Game() {
		new Thread(this).start();
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		key1 = -1;
		screen = 0;
		counter = 0;
		playing=false;
		selectList = setSelectList();
		projectiles = setProjectiles();
		StinkBombs = setStinkyProjectiles();
		gon = new Enhancer(100, 210);
		killua = new Emitter(750, 145);
		kurapika = new Conjurer(1300, 100);
		selected = "You Have Selected:";
		EnhancerG = new Color(16, 189, 8);
		EmitterB = new Color(0, 45, 122);
		ConjurerR = new Color(216, 47, 44);
		arena = new ImageIcon("Arena.png");
		stinkers = new LinkedList();
		hatsu= new Hatsu();
		Mcount = 0;
		Rcount = 0;
		StinkersL=450;
		Player1L=450;
		SaveFile= "save.txt";
		file = new File(SaveFile);
		KO=true;
		// GRR= new ImageIcon("GRR.gif");
	}
	
	public ArrayList<NenUser> setSelectList() {
		ArrayList<NenUser> tempList = new ArrayList();
		tempList.add(new Enhancer(100, 210));
		tempList.add(new Emitter(750, 145));
		tempList.add(new Conjurer(1300, 100));

		return tempList;
	}
	public ArrayList <Hatsu> setProjectiles(){
		ArrayList<Hatsu> tempList = new ArrayList();
		return tempList;
	}
	
	public ArrayList <Hatsu> setStinkyProjectiles(){
		ArrayList<Hatsu> tempList = new ArrayList();
		return tempList;
	}
	
	public void run() {
		try {
			while (true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		} catch (Exception e) {
		}
	}
	

	public void paint(Graphics g) {

		Graphics2D twoDgraph = (Graphics2D) g;
		if (back == null)
			back = (BufferedImage) ((createImage(getWidth(), getHeight())));

		Graphics g2d = back.createGraphics();

		g2d.clearRect(0, 0, getSize().width, getSize().height);
		counter++;
		CHcount++;
		ECHcount++;
		/*System.out.println("count "+EMcount);
		System.out.println("faatigue "+ fatigue);
		System.out.println("pp"+count);
		System.out.println("p1 "+ player1);*/
		System.out.println("new player "+ savedplayer);
		//System.out.println("check "+ check);
		System.out.println("stinkersize "+stinkerpopulation);
		//System.out.println("stinkerpopulation "+stinkergenocide);
		switchScreen(g2d);
		setScreen();

		twoDgraph.drawImage(back, null, 0, 0);

	}
	public void makeSave() {
		try {
			if(file.createNewFile()) {
				System.out.println("New File:"+ file.getName());
			}
			else {
				System.out.println("File already exists");
			}
		}
		catch(IOException e){
			System.out.println("Error:");
			e.printStackTrace();
		}
	}
	
	public void writeSave() {
		try {
			FileWriter scribe = new FileWriter(SaveFile);
			if(!stinkers.isEmpty()) {
				//scribe.write("level\n"+stinkers.size()+ "\n");
				scribe.write(player1.getStandR()+"\n");
				scribe.write("level\n"+ stinkers.size());
			}
			scribe.close();
		}
		catch(IOException e){
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public void readSave() {
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNext()) {
				/*if(sc.next().equals("level")) {
				stinkerpopulation = Integer.parseInt(sc.next());
				}*/
				//if(sc.next().equals("player")) {
				savedplayer = sc.next();
				if(sc.next().equals("level")) {
				stinkerpopulation = Integer.parseInt(sc.next());
				stinkergenocide= 3-stinkerpopulation;
				}
				//}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void addStinkers() {
		if(player1 instanceof Enhancer) {
			stinkers.add(new Emitter(1000, 684));
			stinkers.add(new Conjurer(1200, 680));
			stinkers.add(new Enhancer(1000, 716));
			stinkers.add(new Hisoka(1000, 658));
		}
		if(player1 instanceof Emitter) {
			stinkers.add(new Enhancer(1000, 716));
			stinkers.add(new Conjurer(1000, 696));
			stinkers.add(new Emitter(1000, 684));
			stinkers.add(new Hisoka(1000, 658));
		}
		if(player1 instanceof Conjurer) {
			stinkers.add(new Emitter(1000, 720));
			stinkers.add(new Enhancer(1000, 704));
			stinkers.add(new Conjurer(1000, 696));
			stinkers.add(new Hisoka(1000, 658));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		key1 = e.getKeyCode();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		key1 = e.getKeyCode();
		key2 = e.getKeyCode();
		System.out.println(key1);
		//press escape to return to menu
		if (key1 == 27) {
			playing=false;
			last = 0;
			last1 = 0;
			last2 = 0;
			player1 = new NenUser();
			screen = 0;
			Player1L= 450;
			StinkersL= 450;
			for(int i=0 ; i<=stinkers.size(); i++) {
				stinkers.remove();
			}
		}
		if (screen == 2) {
			//press enter on second screen to confirm selection
			if (key1 == 10) {
				playing=true;
				screen = 3;
				player1.setX(100);
				player1.setY(700);
				addStinkers();
				Player1MaxL= player1.getHealth();
				StinkersMaxL= stinkers.element().getHealth();
				if(replay) {
				for(int i=0; i<stinkergenocide; i++) {
					stinkers.remove();}
				}
			}
		}
		if (screen == 3	) {
			//press right arrow to run right
			if (key1 == 39) {
				player1.setRun(true);
				player1.setRight(true);
				player1.setLeft(false);
			}
			//presss left arrow to run left
			if (key1 == 37) {
				player1.setRun(true);
				player1.setRight(false);
				player1.setLeft(true);
			}
			//press W to do close range attack
			if (key1 == 87) {
				if(player1.isLeft()) {
					player1.setX(player1.getX()-player1.getPunchW());
					}
				player1.setDx(0);
				player1.setPunch(true);
			}
			//press E to do long range attack
			if( key1==69) {
				player1.setDx(0);
				player1.setShoot(true);
			}
			//press up arrow to jump
			if( key1==38) {
				if(player1.getDy()==0) {
					doublejump=0;
				}
				if(doublejump<2) {
					player1.setY(player1.getY()-10);
					player1.setJump(true);
					player1.setDy(-12);
				}
				doublejump++;
			}
			//press space bar to dash
			if(key1==32) {
				player1.setDash(true);
			}
			

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		key1 = e.getKeyCode();
		key2 = e.getKeyCode();
		if (key1 == 39) {
			player1.setRun(false);	
		}
		if (key1 == 37) {
			player1.setRun(false);
		}

		}

	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		mX = arg0.getX();
		mY = arg0.getY();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (screen == 1) {
			if ((mX > kurapika.getX()) && (mX < kurapika.getX() + kurapika.getW()) && (mY > kurapika.getY())
					&& (mY < kurapika.getY() + kurapika.getH())) {
				player1 = new Conjurer(1200, 10);
				player1.setW(546);
				player1.setH(1050);
				screen = 2;
			}
			if ((mX > gon.getX()) && (mX < gon.getX() + gon.getW()) && (mY > gon.getY())
					&& (mY < gon.getY() + gon.getH())) {
				player1 = new Enhancer(1200, 10);
				player1.setW(675);
				player1.setH(990);
				screen = 2;
			}
			if ((mX > killua.getX()) && (mX < killua.getX() + killua.getW()) && (mY > killua.getY())
					&& (mY < killua.getY() + killua.getH())) {
				player1 = new Emitter(1200, 10);
				player1.setW(450);
				player1.setH(1058);
				screen = 2;
			}
		}
		if(screen==0) {
			if((mX>360)&&(mX<1560)&&(mY>230)&&(mY<430)){
				player1 = new NenUser();
				screen=1;
				replay=false;
			}
			if((mX>325)&&(mX<1625)&&(mY>600)&&(mY<800)){
				if(savedplayer.equals("EstandR.gif")) {
					player1 = new Enhancer(1200, 10);
					player1.setW(675);
					player1.setH(990);
				}
				if(savedplayer.equals("KillstandR.gif")) {
					player1 = new Emitter(1200, 10);
					player1.setW(450);
					player1.setH(1058);
				}
				if(savedplayer.equals("KstandR.gif")) {
					player1 = new Conjurer(1200, 10);
					player1.setW(546);
					player1.setH(1050);
				}
				replay=true;
				screen=2;
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	public void attack() {
		if(Mcount>30&&player1.MCollision(stinkers.element())) {
			stinkers.element().setHealth(stinkers.element().getHealth()-(player1.getMelee()/10));
			Mcount=70;
		}
		if(player1.isShoot()&&Rcount==48) {
			//System.out.println(player1.getX());
			if(player1 instanceof Emitter) {
				if(player1.isRight()) {
				projectiles.add(new Electricity(player1.getX()+player1.getW(), player1.getY()+50, 15));
				}
				if(player1.isLeft()) {
				projectiles.add(new Electricity(player1.getX()-48, player1.getY()+50, -15));
				}
			}
			if(player1 instanceof Enhancer) {
				if(player1.isRight()) {
				projectiles.add(new Paper(player1.getX()+player1.getW(), player1.getY()+50, 8, "GProjR.gif"));
				}
				if(player1.isLeft()) {
				projectiles.add(new Paper(player1.getX()-34, player1.getY()+50, -8, "GProjL.gif"));
				}
			}
			if(player1 instanceof Conjurer) {
				CHcount=0;
				projectiles.add(new Chains(stinkers.element().getX()-60, 300));
			}
	
		}
		for(int h=0; h<projectiles.size(); h++) {
				if(player1 instanceof Conjurer) {
					if(CHcount>60) {
						if(stinkers.element().RCollision(projectiles.get(h))) {
							projectiles.remove(h);
							stinkers.element().setHealth(stinkers.element().getHealth()-(player1.getRange()/10));
					}
						else if(CHcount>80){
							projectiles.remove(h);
						}
				}
		}
				else {
					if(stinkers.element().RCollision(projectiles.get(h))) {
						stinkers.element().setHealth(stinkers.element().getHealth()-(player1.getRange()/10));
						projectiles.remove(h);
				}
				}
		}
	}
	public void Skynet() {
		Rectangle Poggers = new Rectangle(player1.getX(), player1.getY(), player1.getW(), player1.getH());
		Rectangle Cringers = new Rectangle((stinkers.element().getX()), stinkers.element().getY(), (stinkers.element().getW()), stinkers.element().getH());
		if(player1.getX()<stinkers.element().getX()) {
			stinkers.element().setLeft(true);
			stinkers.element().setRight(false);
		}
		if(player1.getX()>stinkers.element().getX()) {
			stinkers.element().setRight(true);
			stinkers.element().setLeft(false);
		}
		if(Poggers.intersects(Cringers)) {
			stinkers.element().setRun(false);
			int Senses =(int)(Math.random()*((10-1+1)+1));
			if(Senses==4) {
				fatigue++;
				fight=true;
			}
		}
		if(!Poggers.intersects(Cringers)) {
			fight=false;
			fatigue=0;
			if(counter%10==0) {
				int Decision =(int)(Math.random()*((3-1+1)+1));
				if(Decision==3) {
					int Action =(int)(Math.random()*((2-1+1)+1));
					if(Action==2) {
						stinkers.element().setShoot(true);
						stinkers.element().setRun(false);
					}
					if(Action==1) {
						stinkers.element().setRun(true);
						
					}
				}
			}
		}
		if(fight&&fatigue==1) {
			stinkers.element().setPunch(true);
			if(stinkers.element().isLeft()) {
				stinkers.element().setX(stinkers.element().getX()-stinkers.element().getPunchW());
			}
			fight=false;
		}
		/*System.out.println("front "+Cringers.getX());
		System.out.println("w "+ stinkers.element().getW());
		System.out.println("pog "+ Poggers.getX());
		System.out.println("direction " + stinkers.element().isLeft());
		System.out.println("img "+ stinkers.element().getImg());
		System.out.println("P1img "+ player1.getImg());*/
		//System.out.println("attacking " + stinkers.element().isPunch());
		if(EMcount>30&&stinkers.element().MCollision(player1)&&!player1.isDash()) {
			//System.out.println("pre-punch: "+player1.getHealth());
			player1.setHealth(player1.getHealth()-(stinkers.element().getMelee()/10));
			//System.out.println("post-punch: "+player1.getHealth());
			stinkers.element().setPunch(false);
		}
		if(stinkers.element().isShoot()&&ERcount==48) {
			//System.out.println(player1.getX());
			if(stinkers.element() instanceof Emitter) {
				if(stinkers.element().isRight()) {
					StinkBombs.add(new Electricity(stinkers.element().getX()+stinkers.element().getW(), stinkers.element().getY()+50, 15));
				}
				if(stinkers.element().isLeft()) {
					StinkBombs.add(new Electricity(stinkers.element().getX()-48, stinkers.element().getY()+50, -15));
				}
			}
			if(stinkers.element() instanceof Enhancer) {
				if(stinkers.element().isRight()) {
					StinkBombs.add(new Paper(stinkers.element().getX()+stinkers.element().getW(), stinkers.element().getY()+50, 8, "GProjR.gif"));
				}
				if(stinkers.element().isLeft()) {
					StinkBombs.add(new Paper(stinkers.element().getX()-34, stinkers.element().getY()+50, -8, "GProjL.gif"));
				}
			}
			if(stinkers.element() instanceof Hisoka) {
				if(stinkers.element().isRight()) {
					StinkBombs.add(new BungeeGum(stinkers.element().getX()+stinkers.element().getW(), stinkers.element().getY()+50, 12));
				}
				if(stinkers.element().isLeft()) {
					StinkBombs.add(new BungeeGum(stinkers.element().getX()-34, stinkers.element().getY()+50, -12));
				}
			}
			if(stinkers.element() instanceof Conjurer) {
				ECHcount=0;
				StinkBombs.add(new Chains(player1.getX()-60, 300));
			}
	
		}
		for(int h=0; h<StinkBombs.size(); h++) {
				if(stinkers.element() instanceof Conjurer) {
					if(ECHcount>60) {
						if(player1.RCollision(StinkBombs.get(h))&&!player1.isDash()) {
							StinkBombs.remove(h);
							player1.setHealth(player1.getHealth()-(stinkers.element().getRange()/10));
					}
						else if(ECHcount>80){
							StinkBombs.remove(h);
						}
				}
		}
				else {
					if(player1.RCollision(StinkBombs.get(h))&&!player1.isDash()) {
						player1.setHealth(player1.getHealth()-(stinkers.element().getRange()/10));
						StinkBombs.remove(h);
				}
				}
		}
		
		}
	
	public void attacktimers() {
		if(player1.isPunch()) {
			Mcount++;
		}
		if(!player1.isPunch()) {
			Mcount=0;
		}
		if(player1.isShoot()) {
			Rcount++;
		}
		if(!player1.isShoot()) {
			Rcount=0;
		}
		if(player1.isDash()) {
			dashtime++;
		}
		if(!player1.isDash()) {
			dashtime=0;
		}
		if(stinkers.element().isPunch()) {
			EMcount++;
		}
		if(!stinkers.element().isPunch()) {
			EMcount=0;
		}
		if(stinkers.element().isShoot()) {
			ERcount++;
		}
		if(!stinkers.element().isShoot()) {
			ERcount=0;
		}
	}
	
	
	public void setScreen() {
		if(playing) {
			if(counter==200&&screen==4) {
				counter=0;
				screen=3;
			}
			if(!stinkers.isEmpty()) {
				if(stinkers.element().getHealth()<=0) {
					stinkers.remove();
					player1.setX(100);
					screen=4;
					counter=0;
					Next=true;
			}
			}
			if(player1.getHealth()<=0){
				screen=5;
			}
			if(stinkers.isEmpty()){
				screen=6;
			}
		}
	}

	public void switchScreen(Graphics g2d) {
		switch (screen) {
		case 0:
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Arial", Font.BOLD, 200));
			g2d.drawString("NEW GAME", 400, 400);
			g2d.drawString("LOAD GAME", 360, 770);
			break;
		case 1:
			g2d.setFont(new Font("Arial", Font.BOLD, 100));
			for (NenUser n : selectList) {
				n.drawUser(g2d);
			}
			break;
		case 2:
			player1.drawUser(g2d);
			if (counter % 10 == 0 && last < selected.length() - 1) {
				last += 1;
			}
			g2d.setFont(new Font("Arial", Font.BOLD, 100));
			g2d.drawString(selected.substring(0, last), 100, 200);
			g2d.setFont(new Font("Arial", Font.BOLD, 250));
			if (player1 instanceof Enhancer) {
				g2d.setColor(EnhancerG);
				if (counter % 15 == 0 && last1 < "Enhancer ".length() - 1 && last == selected.length() - 1) {
					last1 += 1;
				}
				g2d.drawString("Enhancer".substring(0, last1), 10, 450);
				if (last2 == "Health: ".length() - 1) {
					g2d.setFont(new Font("Arial", Font.BOLD, 100));
					g2d.drawString(("" + player1.getHealth()), 450, 650);
					g2d.drawString(("" + player1.getRange()), 450, 750);
					g2d.drawString(("" + player1.getMelee()), 450, 850);
				}
			}
			if (player1 instanceof Conjurer) {
				g2d.setColor(ConjurerR);
				if (counter % 15 == 0 && last1 < "Conjurer ".length() - 1 && last == selected.length() - 1) {
					last1 += 1;
				}
				g2d.drawString("Conjurer".substring(0, last1), 10, 450);
				if (last2 == "Health: ".length() - 1) {
					g2d.setFont(new Font("Arial", Font.BOLD, 100));
					g2d.drawString(("" + player1.getHealth()), 450, 650);
					g2d.drawString(("" + player1.getRange()), 450, 750);
					g2d.drawString(("" + player1.getMelee()), 450, 850);
				}
			}
			if (player1 instanceof Emitter) {
				g2d.setColor(EmitterB);
				if (counter % 15 == 0 && last1 < "Emitter ".length() - 1 && last == selected.length() - 1) {
					last1 += 1;
				}
				g2d.drawString("Emitter ".substring(0, last1), 10, 450);
				if (counter % 15 == 0 && last1 == "Emitter ".length() - 1 && last2 < "Health: ".length() - 1) {
					last2 += 1;
				}

				g2d.setFont(new Font("Arial", Font.BOLD, 100));
				if (last2 == "Health: ".length() - 1) {
					g2d.drawString(("" + player1.getHealth()), 450, 650);
					g2d.drawString(("" + player1.getRange()), 450, 750);
					g2d.drawString(("" + player1.getMelee()), 450, 850);
				}
				g2d.setColor(Color.BLACK);
				g2d.drawString("Health:".substring(0, last2), 100, 650);
				g2d.drawString("Range: ".substring(0, last2), 100, 750);
				g2d.drawString("Melee: ".substring(0, last2), 100, 850);
			} else {
				if (counter % 15 == 0 && last1 == "Enhancer ".length() - 1 && last2 < "Health: ".length() - 1) {
					last2 += 1;
				}
				g2d.setFont(new Font("Arial", Font.BOLD, 100));
				g2d.setColor(Color.BLACK);
				g2d.drawString("Health:".substring(0, last2), 100, 650);
				g2d.drawString("Range: ".substring(0, last2), 100, 750);
				g2d.drawString("Melee: ".substring(0, last2), 100, 850);

			}
			break;
		case 3:
			
			g2d.drawImage(arena.getImage(), 0, 0, getWidth(), getHeight(), this);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(50, 100, 460, 60);
			g2d.fillRect(1310, 100, 460, 60);
			g2d.setColor(Color.RED);
			if(Player1L>player1.getHealth()*(450/Player1MaxL)) {
				Player1L-=5;
			}
			
			g2d.fillRect(55, 105, Player1L, 50);
			if(StinkersL>stinkers.element().getHealth()*(450/StinkersMaxL)) {
				StinkersL-=5;
			}
			g2d.fillRect(1315, 105, StinkersL, 50);
			player1.melee(Mcount);
			player1.range(Rcount);
			player1.move();
			player1.gravity(counter);
			player1.dash(dashtime);
			player1.setAnimation();
			player1.drawUser(g2d);
			if(player1 instanceof Enhancer) {
				g2d.setColor(EnhancerG);
			}
			if(player1 instanceof Emitter) {
				g2d.setColor(EmitterB);
			}
			if(player1 instanceof Conjurer) {
				g2d.setColor(ConjurerR);
			}
			g2d.fillRect(player1.getX()+(player1.getW()/3), player1.getY()-10, player1.getW()/3, 10);
			if(!stinkers.isEmpty()) {
				attacktimers();
				stinkers.element().setDash(false);
				stinkers.element().melee(EMcount);
				stinkers.element().range(ERcount);
				stinkers.element().setAnimation();
				stinkers.element().drawUser(g2d);
				stinkers.element().move(); 
				attack();
				Skynet();
				//System.out.println(stinkers.element().getW());
			}
			System.out.println("dy "+ player1.getDy());
			/*System.out.println("range:" +Rcount);
			System.out.println(player1.isShoot());
			System.out.println("punch:"+player1.isPunch());
			System.out.println("ypos:"+player1.getY());
			System.out.println("run:"+player1.getDx());
			System.out.println(projectiles.size());
			System.out.println("punching "+ stinkers.element().isPunch());
			System.out.println("meleeD "+ stinkers.element().getMelee());
			System.out.println("PLayer 1:"+(player1.getY()+player1.getH()));*/
			for(Hatsu h: projectiles) {
				h.move();
				h.drawHatsu(g2d);
				//System.out.println(h.getAttack());
			}
			for(Hatsu S: StinkBombs) {
				S.move();
				S.drawHatsu(g2d);
				//System.out.println(h.getAttack());
			}
			break;
		case 4:
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, 0, getWidth(), getHeight());
			g2d.setColor(Color.CYAN);
			g2d.setFont(new Font("Arial", Font.BOLD, 800));
			if(counter%50==0) {
				KO=!KO;
			}
			if(KO) {
				g2d.drawString("K.O", 200, 800);
			}
			if(Next) {
				StinkersMaxL= stinkers.element().getHealth();
				StinkersL=450;
				Player1L=450;
				player1.setHealth(Player1MaxL);
				Next=false;
			}
			break;
			
		case 5:
			g2d.setColor(Color.RED);
			g2d.setFont(new Font("Arial", Font.BOLD, 400));
			g2d.drawString("Get Gud", 100, 500);
			g2d.drawString("LMAO", 400, 800);
			break;
		
		case 6:
			g2d.setColor(Color.CYAN);
			g2d.setFont(new Font("Arial", Font.BOLD, 350));
			g2d.drawString("You Are", 250, 500);
			g2d.drawString("CRACKED", 50, 800);
		}
	}
}

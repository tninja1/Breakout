package breakout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import utilities.GDV5;

public class Breakout extends GDV5 {
	boolean finishOne = false;
	boolean lose = false;
	static boolean game = false;
	boolean splash = true;
	boolean allBroken = false;
	boolean powerPlay = false;
	int current = 80;
	int scorep;
	int powerIncrement;
	static int lvl = 1;
	Paddle p = new Paddle (GDV5.getMaxWindowX()/2,GDV5.getMaxWindowY()-60,100,15);
	PowerUps j = new PowerUps();
	Ball b = new Ball(GDV5.getMaxWindowX()/2,GDV5.getMaxWindowY()/2,15,15);
	Ball ab = new Ball(GDV5.getMaxWindowX()/2,GDV5.getMaxWindowY()/2,15,15);
	static Brick[] bricks;
	
	public static void main(String[] args) {
		
		bricks = new Brick[8];
	
		Color[] colors = {Color.orange,Color.cyan,Color.gray,Color.green,Color.red,Color.MAGENTA};
		int bricksPerRow = 8;
		int x = 1; int y = 1;
		int count = 1,colIndex=0;
		int width = GDV5.getMaxWindowX()/8;
			for(int i = 0; i<bricks.length; i++) {
			bricks[i] = new Brick(x,y,colors[colIndex%colors.length]);
			x+=width;
			if (count%bricksPerRow==0) {
				x=1;
				y+=bricks[i].getHeight()+2;
				colIndex++;
			}
			count++;
		}		
		Breakout b1 = new Breakout();
		b1.start();
}
	void gameStart() {
		Color[] colors = {Color.orange,Color.cyan,Color.gray,Color.green,Color.red,Color.MAGENTA};
		int bricksPerRow = 8;
		int x = 1; int y = 1;
		int count = 1,colIndex=0;
		int width = GDV5.getMaxWindowX()/8;
		bricks = new Brick[lvl*8];
		for(int i = 0; i<bricks.length; i++) {
			bricks[i] = new Brick(x,y,colors[colIndex%colors.length]);
			x+=width;
			if (count%bricksPerRow==0) {
				x=1;
				y+=bricks[i].getHeight()+2;
				colIndex++;
			}
			count++;
		}		
}
	void gameState() {
		if ((GDV5.KeysPressed[KeyEvent.VK_F])&&(powerIncrement>=100)&&(powerPlay==false)) {
			powerPlay=true;
			powerIncrement-=100;
			
		}
		if ((GDV5.KeysPressed[KeyEvent.VK_ENTER])&&(game==false)) {
			gameStart();
			b.x = GDV5.getMaxWindowX()/2;
			b.y = GDV5.getMaxWindowY()-500;
//			lvl++;
			splash = false;
			lose = false;
			allBroken = false;
			for (int i = 0; i<bricks.length;i++) {
				bricks[i].broken=false;
			}
			game=true;

			b.lives=5;
		}
		if (GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			splash = true;
			lose = false;
			game=false;
			b.lives=0;
		}
		

	}
	@Override
	public void update() {
		if (game) {
			p.move();
			b.move();
			b.bounce();
			p.Collision(b);
			if (powerPlay) {
				ab.move();
				ab.bounce2();
				p.Collision(ab);
			}
	//		newLayer();
		}
//		intersects();
		for (int i = 0; i<bricks.length;i++) {
			bricks[i].Collision(b);
			if ((bricks[i].broken==true)&&bricks[i].incremented==false) {
				scorep+=10;
				powerIncrement+=10;
				bricks[i].incremented = true;
			}
		}
		gameState();
		
		
		// TODO Auto-generated method stub
		
	}
	public void intersects() {
		if (b.intersects(p)) {
			b.dX*=-1;
			b.dY*=-1;
			b.diry *= -1;
		}
	}
	

	
	@Override
	public void draw(Graphics2D win) {
	
		if (splash) {
			win.setColor(Color.blue);
			win.setFont(new Font("Courier",Font.ITALIC,80));
			win.drawString("Purav Patel", 345, 300);
		}
		if (game) {
			win.setColor(Color.white);
			j.drawRectangles(win);
			if(powerIncrement>=100) {
			win.setColor(Color.YELLOW);
			}
			else {
				win.setColor(Color.WHITE);
			}
			if (powerIncrement>=10) {
				win.drawRect(25, 680, 15, 20);
				win.fillRect(25,680,15,20);
			}
			if (powerIncrement>=20) {
				win.drawRect(25, 660, 15, 20);
				win.fillRect(25,660,15,20);
			}
			if (powerIncrement>=30) {
				win.drawRect(25, 640,15, 20);
				win.fillRect(25,640,15,20);
			}
			if (powerIncrement>=40) {
				win.drawRect(25, 620, 15, 20);
				win.fillRect(25,620,15,20);
			}
			if (powerIncrement>=50) {
				win.drawRect(25, 600, 15, 20);
				win.fillRect(25,600,15,20);
			}
			if (powerIncrement>=60) {
				win.drawRect(25, 580, 15, 20);
				win.fillRect(25,580,15,20);
			}
			if (powerIncrement>=70) {
				win.drawRect(25, 560, 15, 20);
				win.fillRect(25,560,15,20);
			}
			if (powerIncrement>=80) {
				win.drawRect(25, 540, 15, 20);
				win.fillRect(25,540,15,20);
			}
			if (powerIncrement>=90) {
				win.drawRect(25, 520, 15, 20);
				win.fillRect(25,520,15,20);
			}
			if (powerIncrement>=100) {
				win.drawRect(25, 500, 15, 20);
				win.fillRect(25,500,15,20);
			}
			if(powerPlay) {
				win.setColor(Color.red);
				win.fill(ab);
				if(ab.powerLives==0) {
					powerPlay=false;
				}
			}
			
			win.setColor(Color.black);
			win.setFont(new Font("Courier",Font.ITALIC,80));
			win.drawString("Purav Patel", 345, 300);
			Brick.drawBricks(win,bricks);
			win.setColor(Color.white);
			win.fill(p);
			win.fill(b);
			win.setFont(new Font("Courier",Font.ITALIC,40));
			win.drawString("" + scorep, GDV5.getMaxWindowX()-100, GDV5.getMaxWindowY()-30);
			win.setFont(new Font("Courier",Font.ITALIC,40));
			win.drawString("" + b.lives, 100, GDV5.getMaxWindowY()-30);
		
		if (b.lives==0) 
		{
			game=false;
			lose = true;
			scorep=0;
			powerIncrement = 0;
			lvl = 1;
			for (int i = 0;i<bricks.length;i++) 
			{
				bricks[i].broken=false;
			}
		}
		for (int i = 0; i<bricks.length;i++) {
			if (bricks[i].broken==true) 
			{
				allBroken = true;
			}
			else if (bricks[i].broken==false) 
			{
				allBroken = false;
				break;
			}
		}
		
		if (allBroken) 
		{
			game = false;
			lvl++;
		}
		}
		if (allBroken) {
			win.setColor(Color.blue);
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString("You completed level " + lvl+ "! Press Enter to Continue.", 0, 300);
		}
		
		if (lose) 
		{
			win.setColor(Color.blue);
			win.setFont(new Font("Courier",Font.ITALIC,30));
			win.drawString("You Lose. Press Enter to Restart", 0, 300);
		}
	}

}


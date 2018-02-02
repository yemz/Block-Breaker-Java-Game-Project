import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{

	ArrayList<Block> blocks = new ArrayList<Block>(); //from the block class add block
	ArrayList<Block> ball = new ArrayList<Block>(); //from the block class add ball
	ArrayList<Block> powerup = new ArrayList<Block>();// add extra balls from block class
	Block paddle; //variable to call image paddle
	Thread thread; //call thread on animate to start in 10 milliseconds
	Animate animate; //call animate class
	int size = 25;
	BlockBreakerPanel(){
		
		paddle = new Block(175, 340, 150, 25, "paddle.png"); //make paddle appear in the middle of screen
		
		for (int i = 0; i < 8; i++){
			blocks.add(new Block ((i * 60 + 2), 0, 60, 20, "blue.png"));
		}
		for (int i = 0; i < 8; i++){
			blocks.add(new Block ((i * 60 + 2), 25, 60, 20, "red.png"));
		}
		for (int i = 0; i < 8; i++){
			blocks.add(new Block ((i * 60 + 2), 50, 60, 20, "green.png"));
		}
		for (int i = 0; i < 8; i++){
			blocks.add(new Block ((i * 60 + 2), 75, 60, 20, "yellow.png"));
		}
		Random random = new Random();
		blocks.get(random.nextInt(32)).powerup = true; 
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		ball.add(new Block (237, 300, 25, 25, "ball.png")); //add first ball from array list and place it right above the paddle
		addKeyListener(this);// needed to read the Keys to animate
		setFocusable(true);// needed to read the Keys
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);//Erases the screen
		for(Block b : blocks)//reprint the blocks or images blue, red, green, yellow blocks
			b.draw(g, this);
		for(Block b : ball)//reprint the ball
			b.draw(g, this);
		for(Block p : powerup)//reprint the ball
			p.draw(g, this);
			paddle.draw(g, this); //print paddle on the screen
	}
	
	//from animate to run application
	public void update(){
		for(Block p : powerup){//drop extra balls
			p.y += 1;
			if(p.intersects(paddle)&& !p.destroyed){//if extra powerup intersects paddle add another ball
				p.destroyed = true;
				ball.add(new Block(paddle.dx + 75, 300, 25, 25, "ball.png"));
			
	}	
	}
		for(Block ba : ball){ //update coordinates x and y of ball
			ba.x+=ba.dx;
			//allow the ball to move around
			if(ba.x > (getWidth() - size) && ba.dx > 0 || ba.x < 0)
				ba.dx *= -1; //multiply the ball -1
			if(ba.y < 0 || ba.intersects(paddle))
				ba.dy *= -1;	
				ba.y+=ba.dy;
		//for loop if ball intersects with a block destroy the block
			for (Block b : blocks){
				if((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed){//if b (block) intersects ba (ball)
					ba.dx *= -1; //ball movement by -1
					b.destroyed = true;
					if(b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
				else if(ba.intersects(b) && !b.destroyed){
					b.destroyed = true; //destroy block (b)
					ba.dy *= -1; //flip direction of ball
					if(b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
			}
		}
		repaint(); //This will update the graphics
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		//Start the game when enter is pressed
	if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			animate = new Animate(this); //call animate class
			 thread = new Thread(animate); //call thread to display blocks in 10 milliseconds
			 thread.start();
		}
		
		//to move the paddle
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0){//stay on screen limits
			paddle.x-=15;//move paddle
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth()- paddle.width)){
			paddle.x+=15; //move paddle
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

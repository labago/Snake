import java.awt.Color;
import java.util.ArrayList;

import idraw.*;
import geometry.*;

public class GameWorld extends World{
	Color background;
	Color background2 = Color.orange;
	Snake snake;
	int score = 0;
	int foodNum = 0;
	Food food;
	int tickCount = 500;
	int tickCount2 = 0;
	int mod = 12;
	int move = 0;
	int play = 0;
	int switchh = 0;
	boolean end = false;
	boolean pause = false;
	public GameWorld(Color background, Snake snake, Food food) {
		this.background = background;
		this.snake = snake;
		this.food = food;
	}

	
	public void onKeyEvent(String ke){
		if(ke.equals("r")){
			this.reset();
		}
		if(ke.equals("p")){
			if(pause){
				pause = false;
			}
			else pause = true;
		}
		if(!pause){
		if(ke.equals("up") && !this.snake.head.dir.equals("down") && this.move == 0){
			this.snake.head.dir = "up";
			this.snake.dirCheck();
			this.move = 1;
		}
		if(ke.equals("down") && !this.snake.head.dir.equals("up") && this.move == 0){
			this.snake.head.dir = "down";
			this.snake.dirCheck();
			this.move = 1;
		}
		if(ke.equals("right") && !this.snake.head.dir.equals("left") && this.move == 0){
			this.snake.head.dir = "right";
			this.snake.dirCheck();
			this.move = 1;
		}
		if(ke.equals("left") && !this.snake.head.dir.equals("right") && this.move == 0){
			this.snake.head.dir = "left";
			this.snake.dirCheck();
			this.move = 1;
		}
		}
	}
	
	public boolean checkLeftWall(){
		if(this.snake.head.loc.x < 0){
			return true;
		}
		else return false;
	}
	
	public boolean checkRightWall(){
		if(this.snake.head.loc.x > 800){
			return true;
		}
		else return false;
	}
	
	public boolean checkTopWall(){
		if(this.snake.head.loc.y < 50){
			return true;
		}
		else return false;
	}
	
	public boolean checkBottomWall(){
		if(this.snake.head.loc.y > 650){
			return true;
		}
		else return false;
	}
	
	public boolean checkWalls(){
		if(this.checkBottomWall() || this.checkTopWall()
		|| this.checkLeftWall() || this.checkRightWall()){
			return true;
		}
		else return false;
	}
	
	public boolean ateFood(){
		if(this.snake.head.loc.x == this.food.loc.x && this.snake.head.loc.y == this.food.loc.y){
			return true;
		}
		else return false;
	}
	
	public Food randomFood(){
		int pickX = (int)(Math.random() * 39);
		int pickY = (int)(Math.random() * 29);
		
		if(!this.checkSnakeBody(pickX, pickY)){
			return new Food(Color.green, new Posn((pickX * GameWorld.BLOCK_SPACE) + 10, (pickY * GameWorld.BLOCK_SPACE) + 50));
		}
		else return this.randomFood();
	}
	
	public boolean checkSnakeSelfCollision(){
		for(int i = 0;i < this.snake.body.size();i++){
			if(this.snake.head.loc.x == this.snake.body.get(i).loc.x &&
			   this.snake.head.loc.y == this.snake.body.get(i).loc.y){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkSnakeBody(int x, int y){
		for(int i = 0;i < this.snake.body.size();i++){
			if(this.snake.body.get(i).loc.x == (x * GameWorld.BLOCK_SPACE) + 10 && 
			   this.snake.body.get(i).loc.y == (y * GameWorld.BLOCK_SPACE) + 50){
				return true;
			}
		}
		if(this.snake.head.loc.x == (x * GameWorld.BLOCK_SPACE) + 10 && 
		   this.snake.head.loc.y == (y * GameWorld.BLOCK_SPACE) + 50){
				return true;
			}
	return false;
	}
	
	public void addSection(){
		if(this.snake.body.get(this.snake.body.size() - 1).dir.equals("right")){
			this.snake.body.add(new Section(Color.blue, new Posn(this.snake.body.get(this.snake.body.size() -1).loc.x - (GameWorld.SECTION_RADIUS * 2), this.snake.body.get(this.snake.body.size() -1).loc.y), this.snake.head.dir));
			}
			if(this.snake.body.get(this.snake.body.size() - 1).dir.equals("left")){
				this.snake.body.add(new Section(Color.blue, new Posn(this.snake.body.get(this.snake.body.size() -1).loc.x + (GameWorld.SECTION_RADIUS * 2), this.snake.body.get(this.snake.body.size() -1).loc.y), this.snake.head.dir));
			}
			if(this.snake.body.get(this.snake.body.size() - 1).dir.equals("up")){
				this.snake.body.add(new Section(Color.blue, new Posn(this.snake.body.get(this.snake.body.size() -1).loc.x, this.snake.body.get(this.snake.body.size() -1).loc.y + (GameWorld.SECTION_RADIUS * 2)), this.snake.head.dir));
			}
			if(this.snake.body.get(this.snake.body.size() - 1).dir.equals("down")){
				this.snake.body.add(new Section(Color.blue, new Posn(this.snake.body.get(this.snake.body.size() -1).loc.x, this.snake.body.get(this.snake.body.size() -1).loc.y - (GameWorld.SECTION_RADIUS * 2)), this.snake.head.dir));
			}	
	}
	
	public void checkAndChangeFood(){
		if(this.ateFood()){
			this.food = this.randomFood();
			this.foodNum = this.foodNum + 1;
			this.score = this.score + 50;
			this.play = 1;
			this.tickCount2 = 0;
			this.addSection();
			this.addSection();
		}
	}
	
	public void onTick(){
		if(!end){
		if(!pause){
		this.playAnimation();
		this.checkAndChangeFood();
		this.snake.dirCheck();
		if (this.tickCount % this.mod == 0){
			this.move = 0;
			this.snake.move();
		}
		if(this.tickCount2 < 40){
			this.tickCount2 = this.tickCount2 + 1;
		}
		else this.background2 = Color.orange;
		
		if(this.checkWalls() || this.checkSnakeSelfCollision()){
			end = true;
		}
		this.tickCount = this.tickCount + 1;
		
		if(this.score == 500){
			this.mod = 9;
		}
		if(this.score == 1000){
			this.mod = 7;
		}
		if(this.score == 3000){
			this.mod =5;
		}
		if(this.score == 5000){
			this.mod = 4;
		}
		if(this.score == 7000){
			this.mod = 3;
		}
		}}
	}

	
	public void draw(){
		//BACKGROUND
		this.theCanvas.drawRect(new Posn(0, 40), 800, 620, this.background);	
		
		//FOOD
		this.food.draw(theCanvas);
		
		// SNAKE
		this.snake.draw(theCanvas);
		
		
		//BOUNDARY LINES
		this.theCanvas.drawLine(new Posn(0, 40), new Posn(800, 40), Color.black);
		this.theCanvas.drawLine(new Posn(0, 39), new Posn(800, 39), Color.black);
		
		// Second Background
		this.theCanvas.drawRect(new Posn(0, 0), 800, 40, this.background2);
		
		//STATS
		this.theCanvas.drawString(new Posn(700, 15), "Score: ");
		this.theCanvas.drawString(new Posn(740, 15), new Integer(this.score).toString());
		this.theCanvas.drawString(new Posn(700, 30), "Food: ");
		this.theCanvas.drawString(new Posn(740, 30), new Integer(this.foodNum).toString());
		
	}
	
	public void playAnimation(){
		if(this.play == 1 && this.tickCount2 < 40){
			if(this.switchh > 0 && this.switchh < 20){
				this.background2 = Color.green;
			}
			if(this.switchh > 40){
				this.background2 = Color.orange;
			}
			if(this.switchh >= 0 && this.switchh <= 40){
				this.switchh = this.switchh + 1;
			}
			else
				this.switchh = 0;
		}
	}
	
	public void reset(){
		this.background = Color.gray;
		this.background2 = Color.orange;
		this.snake = new Snake(Color.red, new ArrayList<Section>(), new Section(Color.red, new Posn(210, 410), "right"), "right");
		this.score = 0;
		this.foodNum = 0;
		this.food = this.randomFood();
		this.tickCount = 500;
		this.tickCount2 = 0;
		this.mod = 15;
		this.move = 0;
		this.play = 0;
		this.switchh = 0;
		this.end = false;
		this.snake.body.add(new Section(Color.blue, new Posn(this.snake.head.loc.x - (GameWorld.SECTION_RADIUS * 2), this.snake.head.loc.y), this.snake.head.dir));
		this.snake.body.add(new Section(Color.blue, new Posn(this.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 2), this.snake.head.loc.y), this.snake.head.dir));
		this.snake.body.add(new Section(Color.blue, new Posn(this.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 3), this.snake.head.loc.y), this.snake.head.dir));
	}


	public static int SECTION_RADIUS = 10;
	public static int BLOCK_SPACE = 20;
	
	public static void main(String args[]){
		
		
		GameWorld world2 = new GameWorld(Color.cyan, new Snake(Color.red, new ArrayList<Section>(), new Section(Color.red, new Posn(210, 410), "right"), "right"), new Food(Color.green, new Posn(310, 410)));
		
		GameWorld world = new GameWorld(Color.gray, new Snake(Color.red, new ArrayList<Section>(), new Section(Color.red, new Posn(210, 410), "right"), "right"), world2.randomFood());
		
		
		world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - (GameWorld.SECTION_RADIUS * 2), world.snake.head.loc.y), world.snake.head.dir));
		world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 2), world.snake.head.loc.y), world.snake.head.dir));
		world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 3), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 4), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 5), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 6), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 7), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 8), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 9), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 10), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 11), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 12), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 13), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 14), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 15), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 16), world.snake.head.loc.y), world.snake.head.dir));
		//world.snake.body.add(new Section(Color.blue, new Posn(world.snake.head.loc.x - ((GameWorld.SECTION_RADIUS * 2) * 17), world.snake.head.loc.y), world.snake.head.dir));
		
		
		
		world.bigBang(800, 660, .015);
		
	}

}

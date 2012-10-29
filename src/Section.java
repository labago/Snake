import java.awt.Color;
import geometry.*;
import idraw.*;


public class Section {
	Color color;
	Posn loc;
	String dir;
	public Section(Color color, Posn loc, String dir) {
		this.color = color;
		this.loc = loc;
		this.dir = dir;
	}
	
	
	public boolean draw(Canvas c){
		c.drawDisk(this.loc, GameWorld.SECTION_RADIUS, this.color);
		return true;
	}
	
	public boolean isBelow(Posn p){
		if(p.x == this.loc.x && p.y == (this.loc.y - GameWorld.BLOCK_SPACE)){
			return true;
		}
		else return false;
	}
	
	public boolean isAbove(Posn p){
		if(p.x == this.loc.x && p.y == (this.loc.y + GameWorld.BLOCK_SPACE)){
			return true;
		}
		else return false;
	}
	
	public boolean isLeftOf(Posn p){
		if(p.x == this.loc.x - GameWorld.BLOCK_SPACE && p.y == this.loc.y){
			return true;
		}
		else return false;
	}
	
	public boolean isRightOf(Posn p){
		if(p.x == this.loc.x + GameWorld.BLOCK_SPACE && p.y == this.loc.y){
			return true;
		}
		else return false;
	}
	
	public void moveUp(){
		this.loc.y = this.loc.y - GameWorld.BLOCK_SPACE;
	}
	
	public void moveDown(){
		this.loc.y = this.loc.y + GameWorld.BLOCK_SPACE;
	}
	
	public void moveRight(){
		this.loc.x = this.loc.x + GameWorld.BLOCK_SPACE;
	}
	
	public void moveLeft(){
		this.loc.x = this.loc.x - GameWorld.BLOCK_SPACE;
	}
	
	public void move(String dir){
		if(dir.equals("up")){
			this.moveUp();
		}
		if(dir.equals("down")){
			this.moveDown();
		}
		if(dir.equals("left")){
			this.moveLeft();
		}
		if(dir.equals("right")){
			this.moveRight();
		}
	}
}

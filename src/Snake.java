import idraw.*;
import java.awt.Color;
import geometry.*;
import java.util.ArrayList;


public class Snake {
	Color color;
	ArrayList<Section> body;
	Section head;
	String dir;
	public Snake(Color color, ArrayList<Section> body, Section head, String dir) {
		this.color = color;
		this.body = body;
		this.head = head;
		this.dir = dir;
	}
	
	
	public boolean draw(Canvas c){
		head.draw(c);
		c.drawDisk(new Posn(this.head.loc.x + 3, this.head.loc.y), 1, Color.black);
		c.drawDisk(new Posn(this.head.loc.x - 3, this.head.loc.y), 1, Color.black);
		c.drawCircle(new Posn(this.head.loc.x + 3, this.head.loc.y), 1, Color.black);
		c.drawCircle(new Posn(this.head.loc.x - 3, this.head.loc.y), 1, Color.black);
		for (int i = 0; i < this.body.size();i++){
			this.body.get(i).draw(c);
		}
		return true;
	}
	
	public void dirCheck(){

		if(this.body.get(0).isAbove(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "down";
		}
		else 
		if(this.body.get(0).isBelow(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "up";
		}
		else 
		if(this.body.get(0).isLeftOf(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "left";
		}
		else
		if(this.body.get(0).isRightOf(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "right";
		}
		
		for(int i = 0; i < this.body.size() - 1;i++){
			if(this.body.get(i + 1).isAbove(this.body.get(i).loc)){
				this.body.get(i + 1).dir = "down";
			}
			else 
			if(this.body.get(i + 1).isBelow(this.body.get(i).loc)){
				this.body.get(i + 1).dir = "up";
			}
			else 
			if(this.body.get(i + 1).isLeftOf(this.body.get(i).loc)){
				this.body.get(i + 1).dir = "left";
			}
			else 
			if(this.body.get(i + 1).isRightOf(this.body.get(i).loc)){
				this.body.get(i + 1).dir = "right";
			}
		}
	}
	
	public void move(){
		this.head.move(this.head.dir);
		for(int i = 0; i < this.body.size();i++){
			this.body.get(i).move(this.body.get(i).dir);
		}
		
		if(this.body.get(0).isAbove(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "down";
		}
		else 
		if(this.body.get(0).isBelow(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "up";
		}
		else 
		if(this.body.get(0).isLeftOf(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "left";
		}
		else
		if(this.body.get(0).isRightOf(this.head.loc) && !(this.body.get(0).dir.equals(this.head.dir))){
			this.body.get(0).dir = "right";
		}
		
		for(int i = 0; i < this.body.size() - 1;i++){
			if(this.body.get(i + 1).isAbove(this.body.get(i).loc) && !(this.body.get(i + 1).dir.equals(this.body.get(i).dir))){
				this.body.get(i + 1).dir = "down";
			}
			else 
			if(this.body.get(i + 1).isBelow(this.body.get(i).loc) && !(this.body.get(i + 1).dir.equals(this.body.get(i).dir))){
				this.body.get(i + 1).dir = "up";
			}
			else 
			if(this.body.get(i + 1).isLeftOf(this.body.get(i).loc) && !(this.body.get(i + 1).dir.equals(this.body.get(i).dir))){
				this.body.get(i + 1).dir = "left";
			}
			else 
			if(this.body.get(i + 1).isRightOf(this.body.get(i).loc) && !(this.body.get(i + 1).dir.equals(this.body.get(i).dir))){
				this.body.get(i + 1).dir = "right";
			}
		}
	}

}

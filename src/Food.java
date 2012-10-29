import idraw.*;
import java.awt.Color;
import geometry.*;


public class Food {
	Color color;
	Posn loc;
	public Food(Color color, Posn loc) {
		super();
		this.color = color;
		this.loc = loc;
	}

	
	
	public boolean draw(Canvas c){
		c.drawDisk(new Posn(this.loc.x, this.loc.y), GameWorld.SECTION_RADIUS, this.color);
		return true;
	}
}

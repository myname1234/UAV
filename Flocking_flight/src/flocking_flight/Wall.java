package flocking_flight;

import javax.vecmath.Vector2d;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;

public class Wall {
	Vector2d pos;
	
	public Wall(double xx, double yy, Context<Object> context, ContinuousSpace<Object> space) {
		// TODO Auto-generated constructor stub
		this.pos = new Vector2d(xx, yy);
		
		context.add(this);
		space.moveTo(this, xx, yy);
	}
	
	public void go(double xx ,double yy, ContinuousSpace<Object> space){
		this.pos = new Vector2d(xx, yy);
		space.moveTo(this, xx, yy);
	}
}

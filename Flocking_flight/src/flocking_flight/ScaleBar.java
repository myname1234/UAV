package flocking_flight;

import javax.vecmath.Vector2d;

import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;

public class ScaleBar {
	Vector2d pos;
	String loc;
	int id;
	
	public ScaleBar(double xx, double yy,int id, String loc, Context<Object> context, ContinuousSpace<Object> space) {
		// TODO Auto-generated constructor stub
		this.pos = new Vector2d(xx, yy);
		this.id = id;
		this.loc = loc;
		context.add(this);
		space.moveTo(this, xx, yy);
	}
	
	
}

package flocking_flight;

import java.util.ArrayList;

import javax.vecmath.Vector2d;
import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;

public class Unit {
	Vector2d pos;
	Vector2d velocity;
	
	Vector2d lastpos;//Tdel之前的位置
	Vector2d lastvelocity;//Tdel之前的速度

	//ArrayList<Unit> neighbors;
	
	double time = 0;
	
	public Unit(double xx, double yy, Context<Object> context, ContinuousSpace<Object> space) {
		pos = new Vector2d(xx, yy);
		velocity = new Vector2d(Math.random()*2 -1, Math.random()*2 -1);
		
		lastpos = new Vector2d(xx, yy);
		lastvelocity = velocity;
				
		//neighbors = new ArrayList<>();
		
		context.add(this);
		space.moveTo(this, this.pos.x, this.pos.y);
		
	}
	
	public void wrap () {
		pos.x = (pos.x + Define.spaceWidth) % Define.spaceWidth;
		pos.y = (pos.y + Define.spaceHeight) % Define.spaceHeight;
		
	}
	
	
	public void go(ArrayList<Unit> allUnits, ArrayList<Wall> allWalls, Vector2d Xshp, Vector2d com, ContinuousSpace<Object> space) {
		
		time += ControlPanel.T;
		flock(Xshp, allUnits, com, allWalls);
		
		wrap();
		space.moveTo(this, this.pos.x, this.pos.y);
		
	}
	
	
	public void flock(Vector2d Xshp, ArrayList<Unit> allUnits, Vector2d com, ArrayList<Wall> allWalls){
//		getNeighbors(allUnits);
		
		Vector2d Vspp = Vspp();//System.out.println(Vspp);
		Vector2d Vtrack = Vtrack(Xshp, com);//System.out.println(Vtrack);
		Vector2d Apot = Apot(allUnits);//System.out.println(Apot);
		Vector2d Aslip = Aslip(allUnits);//System.out.println(Aslip);
		Vector2d AVirtualwall = AVirtualwall();//System.out.println(Awall);
		Vector2d Awall = Awall(allWalls);
		Vector2d noise = new Vector2d(Math.random() * .2 -.1, Math.random() * .2 -.1);
		
		Vector2d temp1 = new Vector2d(0, 0);
		temp1.add(Vspp);
		temp1.add(Vtrack);
		temp1.sub(velocity);
		temp1 = mult(temp1, ControlPanel.T/ControlPanel.t);
		
		
		Vector2d temp2 = new Vector2d(0, 0);
		temp2.add(Apot);
		temp2.add(Aslip);
		temp2.add(AVirtualwall);
		temp2.add(Awall);
		temp2 = mult(temp2, ControlPanel.T);
		
		velocity.add(temp1);
		velocity.add(temp2);
		
		if (time > ControlPanel.Tdel) {
			lastpos = pos;
			lastvelocity = velocity;
			time = 0;
		}
		
		velocity.add(mult(noise, ControlPanel.Noise));
		
		pos.add(mult(velocity, ControlPanel.T));
	}
	
//	public void getNeighbors(ArrayList<Unit> allUnits) {
//		for (Unit other : allUnits) {
//			if (other == this) continue;
//			if (distance(other.lastpos, this.pos) < ControlPanel.RC) neighbors.add(other);
//		}
//	}
	
	/**
	 * Unit间排斥力加速度
	 * @return
	 */
	public Vector2d Apot(ArrayList<Unit> allUnits){
		Vector2d apot = new Vector2d(0, 0);
		
		for (Unit other : allUnits) {
			double d = distance(this.pos, other.lastpos);
			if ((d > 0) && (d < ControlPanel.R0)) {
				Vector2d temp = new Vector2d(0, 0);
				temp.sub(other.lastpos, this.pos);
				//temp.normalize();
				temp = mult(temp, Math.min(ControlPanel.Rmin, ControlPanel.R0 - d)/d);
				apot.add(temp);
			}
		}
		return mult(apot, -1 * ControlPanel.D);
	}
	
	public Vector2d Awall(ArrayList<Wall> allWalls) {
		Vector2d awall = new Vector2d(0, 0);
		
		for (Wall wall : allWalls) {
			double d = distance(this.pos, wall.pos);
			if ((d > 0) && (d < ControlPanel.R0)) {
				Vector2d temp = new Vector2d(0, 0);
				temp.sub(wall.pos, this.pos);
				//temp.normalize();
				temp = mult(temp, Math.min(ControlPanel.Rmin, ControlPanel.R0 - d)/d);
				awall.add(temp);
			}
		}
		
		return mult(awall, -1 * ControlPanel.D);
	}
	
	/**
	 * Unit对齐加速度
	 * @return
	 */
	public Vector2d Aslip(ArrayList<Unit> allUnits){
		Vector2d aslip = new Vector2d(0, 0);
		
		for (Unit other : allUnits) {
			double d = distance(this.pos, other.lastpos);
			if ((d > 0 )) {
				Vector2d temp = new Vector2d(0, 0);
				temp.sub(other.lastvelocity, this.velocity);
				//temp.normalize();
				temp = divide(temp, Math.pow(Math.max(ControlPanel.Rmin, d), 2));
				aslip.add(temp);
			}
		}
		return mult(aslip, ControlPanel.Cfrict);
	}
	
	/**
	 * Unit自我推动的速度
	 * @return
	 */
	public Vector2d Vspp(){
		return mult(this.velocity, ControlPanel.Vflock/this.velocity.length());
	}
	
	
	
	/**
	 * Unit远离墙的加速度
	 * @return
	 */
	public Vector2d AVirtualwall(){
		Vector2d temp = new Vector2d(0, 0);
		double x = distance(ControlPanel.Xtrg, this.pos);
		
		temp.sub(ControlPanel.Xtrg, this.pos);
		temp = mult(temp, ControlPanel.Vflock/x);
		temp.sub(this.velocity);
		
		return mult(temp, ControlPanel.Cshill * function(x, ControlPanel.Rtrg, ControlPanel.d));
	}
	
	
	
	
	/**
	 * Unit编队飞行的速度
	 * @param Xshp
	 * @return
	 */
	public Vector2d Vtrack(Vector2d Xshp, Vector2d com){
		Vector2d Vshp = Vshp(Xshp);
		Vector2d Vtrg = Vtrg(com);
		
		Vshp.add(Vtrg);
		double len = Vshp.length();
		
		if (len > ControlPanel.V0) return mult(Vshp, ControlPanel.V0/len);
		return Vshp;
	}
	
	
	/**
	 * Unit编队的速度方向
	 * @param Xshp
	 * @return
	 */
	public Vector2d Vshp(Vector2d Xshp) {
		double x = distance(Xshp, this.pos);
		Vector2d temp = new Vector2d(0, 0);
		temp.sub(Xshp, this.pos);
		return mult(temp, ControlPanel.Bb * ControlPanel.V0 * function(x, ControlPanel.Rcom, ControlPanel.d)/x);
	}
	
	/**
	 * Unit追踪traget速度方向
	 * @return
	 */
	public Vector2d Vtrg(Vector2d com) {
		double x = distance(com, ControlPanel.Xtrg);
		Vector2d temp = new Vector2d(0, 0);
		temp.sub(ControlPanel.Xtrg, com);
		return mult(temp, ControlPanel.Aa * ControlPanel.V0 * function(x, ControlPanel.Rtrg, ControlPanel.d)/x);
	}
	
	/**
	 * 得到localUnits的质心
	 * @return
	 */
	public Vector2d getCoM(ArrayList<Unit> allUnits){
		Vector2d com = this.pos;
		double count = 1;
		for (Unit other : allUnits) {
			com.add(other.lastpos);
			count ++;
		}
		return divide(com, count);
	}
	
	/**
	 * smooth function
	 * @param x
	 * @param R
	 * @param d
	 * @return
	 */
	public double function(double x, double R, double d){
		if (x < R && x > 0) return 0;
		if (x > R + d) return 1;
		return Math.sin(Math.PI / d * (x - R) - Math.PI /2) + 1;
	}
	
	
	/**
	 * 确定两个矢量的欧几里得距离
	 * @param v1
	 * @param v2
	 * @return
	 */
	public double distance(Vector2d v1, Vector2d v2) {
		return Math.pow((v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y), 0.5);
	}
	
	/**
	 * 矢量除以n得到新矢量
	 * @param v
	 * @param n
	 * @return
	 */
	public Vector2d divide(Vector2d v, double n) {
		return new Vector2d(v.x/n, v.y/n);
	}
	
	/**
	 * 矢量乘以n得到新矢量
	 * @param v
	 * @param n
	 * @return
	 */
	public Vector2d mult(Vector2d v, double n) {
		return new Vector2d(v.x * n, v.y * n);
	}
	
	
	/**
	 * 按照指定模大小生成矢量
	 * @param v
	 * @param len
	 * @return
	 */
	public Vector2d setMagnitude(Vector2d v, double len) {
		double d = len/Math.pow(v.x * v.x + v.y * v.y, 0.5);
		return new Vector2d(v.x * d, v.y * d);
	}
	
	/**
	 * 限制矢量的模大小
	 * @param v
	 * @param limit
	 */
	public Vector2d limitMagnitude(Vector2d v, double limit) {
		double m = v.x * v.x + v.y * v.y;
		if (m > limit * limit) {
			return setMagnitude(v, limit);
		}
		else  return v;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + this.pos.x + "," + this.pos.y + ")";
	}
	
}

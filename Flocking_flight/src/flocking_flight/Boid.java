package flocking_flight;

import java.util.ArrayList;

import javax.vecmath.Vector2d;
import repast.simphony.context.Context;
import repast.simphony.space.continuous.ContinuousSpace;

public class Boid {
	Vector2d pos;
	Vector2d move;
	double shade;
	
	
	boolean colorChange;
	
	ArrayList<Boid> friends;
	
	int thinkTimer = 0;
	
	public Boid(double xx, double yy, Context<Object> context, ContinuousSpace<Object> space) {
		// TODO Auto-generated constructor stub
		move = new Vector2d(0, 0);
		pos = new Vector2d(0, 0);
	
		pos.x = xx;
		pos.y = yy;
		thinkTimer = (int)(Math.random() * 10);
		shade = Math.random() * 255;
		friends = new ArrayList<Boid>();
		colorChange = false;
		
		
		context.add(this);
		space.moveTo(this, this.pos.x, this.pos.y);
	}
	
	/**
	 * 开始形成群体
	 */
	public void go(ArrayList<Boid> boids, ArrayList<Wall> obstacles, Context<Object> context, ContinuousSpace<Object> space) {
		increment();
		
		
		if (thinkTimer == 0) {
			getFriends(boids);
		}
		flock(obstacles);
		
		pos.add(move);
		
		//System.out.println(boids.toString());
		
		wrap();
		space.moveTo(this, this.pos.x, this.pos.y);
		
	}
	

	void increment () {
		thinkTimer = (thinkTimer + 1) % 5;
	}

	void wrap () {
		pos.x = (pos.x + Define.width) % Define.width;
		pos.y = (pos.y + Define.height) % Define.height;
		
	}
	
	
	/**
	 * 聚集
	 */
	public void flock(ArrayList<Wall> obstacles) {
		Vector2d allign = getAverageDir();
		Vector2d avoidDir = getAvoidDir();
		Vector2d avoidObstacle = getAvoidObstacle(obstacles);
		Vector2d noise = new Vector2d(Math.random() * 2 -1, Math.random() * 2 - 1);
		Vector2d cohese = getCohesion();
		
		allign = mult(allign, 1);
		
		
		avoidDir = mult(avoidDir, 1);
		
		
		avoidObstacle = mult(avoidObstacle, 3);
		
		
		noise = mult(noise, 0.1);
		
		
		cohese = mult(cohese, 1);
		
		
		
		move.add(allign);
		move.add(avoidDir);
		move.add(avoidObstacle);
		move.add(noise);
		move.add(cohese);
		move = limitMagnitude(move, ControlPanel.V0 );
		
		shade += getAverageColor() * 0.03;
		shade += (Math.random() * 2 -1);
		shade = (shade + 255) % 255;
	}
	
	/**
	 * 将附近的群体成员加入群体
	 */
	public void getFriends(ArrayList<Boid> boids) {
		ArrayList<Boid> nearby = new ArrayList<Boid>();
		for (int i = 0; i < boids.size(); i ++) {
			Boid test = boids.get(i);
			if (test == this) continue;
			if (Math.abs(test.pos.x - this.pos.x) < ControlPanel.Rc &&
					Math.abs(test.pos.y - this.pos.y) < ControlPanel.Rc){
				nearby.add(test);
			}
		}
		friends = nearby;
	}
	
	public double getAverageColor() {
		double total = 0;
		double count = 0;
		for (Boid boid : friends) {
			if (boid.shade - this.shade < -128) {
				total += boid.shade + 255 - shade;
			}
			else if (boid.shade - this.shade > 128) {
				total += boid.shade - 255 - shade;
			}
			else {
				total += boid.shade -shade;
			}
			count ++;
		}
		if (count == 0) return 0;
		else return total / (double)count;
	}
	
	
	/**
	 * 确定群体的平均方向
	 * 当friends加入时
	 * @return
	 */
	public Vector2d getAverageDir() {
		Vector2d sum = new Vector2d(0, 0);
		//int count = 0;
		
		for (Boid boid : friends) {
			double d = distance(pos, boid.pos);
			if ((d > 0) && (d < ControlPanel.Rc)) {
				Vector2d copy = boid.move;
				copy.normalize();
				copy = divide(copy, d);
				sum.add(copy);
				//count ++;
			}
		}
		return sum;
	}
	
	
	/**
	 * 确定群体彼此之间的避碰方向
	 * @return
	 */
	public Vector2d getAvoidDir() {
		Vector2d steer = new Vector2d(0, 0);
		//int count = 0;
		
		for (Boid boid : friends) {
			double d = distance(pos, boid.pos);
			if ((d > 0) && (d < ControlPanel.R0)) {
				Vector2d diff = new Vector2d();
				diff.sub(pos, boid.pos);
				diff.normalize();
				diff = divide(diff, d);
				steer.add(diff);
				//count ++;
			}
 		}
		return steer;
	}
	
	/**
	 * 确定避碰障碍物的反向
	 * @return
	 */
	public Vector2d getAvoidObstacle(ArrayList<Wall> obstacles) {
		Vector2d steer = new Vector2d(0, 0);
		//int count = 0;
		
		for (Wall obstacle : obstacles) {
			double d = distance(pos, obstacle.pos);
			if ((d > 0) && (d < ControlPanel.R0)) {
				Vector2d diff = new Vector2d();
				diff.sub(pos, obstacle.pos);
				diff.normalize();
				diff = divide(diff, d);
				steer.add(diff);
				//count ++;
			}
		}
		return steer;
	}
	
	
	
	/**
	 * 确定追逐的方向
	 * @return
	 */
	public Vector2d getCohesion() {
		Vector2d sum = new Vector2d(0, 0);
		int count = 0;
		
		for (Boid boid : friends) {
			double d = distance(pos, boid.pos);
			if ((d > 0) && (d < ControlPanel.Rc)) {
				sum.add(boid.pos);
				count ++;
			}
		}
		if (count > 0) {
			sum = divide(sum, count);
			Vector2d desired = new Vector2d();
			desired.sub(sum, pos);
			return setMagnitude(desired, 0.05);
		}
		else {
			return new Vector2d(0, 0);
		}
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
		return "(" + pos.x + ","  + pos.y + " " + move.x + ","  + move.y + ")" ;
	}
}

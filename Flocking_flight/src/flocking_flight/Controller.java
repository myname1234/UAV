package flocking_flight;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.vecmath.Vector2d;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.continuous.ContinuousSpace;

public class Controller {
	private Context<Object> context;// (<>，泛型，能记住集合中元素的类型)
	private ContinuousSpace<Object> space;
	private String filename1 = Define.filename1;
	private String filename2 = Define.filename2;
	private String filename3 = Define.filename3;
	
	ArrayList<Unit> allUnits;
	ArrayList<Flight> allFlights;
	ArrayList<Wall> allWalls;
	ArrayList<ScaleBar> allScaleBars;
	Target target;
	
	
	//public static double WScal = 0;
	public static double Time = 0;
	int timeStep = 0;
	
	
	public Controller(Context<Object> context, ContinuousSpace<Object> space) {
		this.context = context;
		this.space = space;
		
		allUnits = new ArrayList<>();
		allFlights = new ArrayList<>();
		
		//addWalls(context, space);
		//addLineWall(context, space);
		addSquareWall();
		
		//allWalls = new ArrayList<>();
		
		addScaleBar();
		
		for (int i = 0; i < Define.N; i ++) {
			double x = Math.random() * Define.width;
			double y = Math.random() * Define.height;
			allUnits.add(new Unit(x,  y,  context, space));
			allFlights.add(new Flight(x, y, context, space));
		}
		
		target = new Target(ControlPanel.Xtrg.x, ControlPanel.Xtrg.y, context, space);
		
		Print(Time, 0, filename1);
		Print(Time, 0, filename2);
		Print(Time, 0, filename3);
		
		//System.out.println(allUnits.toString());
	}
	
	@ScheduledMethod(start = 1, interval = 80)
	public void step() {
		timeStep ++;
		
		//ArrayList<Vector2d> Xshp = new ArrayList<>();
		Vector2d com = getCoM(allUnits);
		
//		for (int i = 0; i < allWalls.size(); i ++) {
//			allWalls.get(i).go(ControlPanel.Wall_x + i, ControlPanel.Wall_y, space);
//		}
		
		for (int i = 0; i < Define.N; i ++) {
			
			
			
			//Grid
	
			Unit unit = allUnits.get(i);
			
			unit.go(allUnits, allWalls, com, com, space);
			
			allFlights.get(i).go(unit.pos.x, unit.pos.y, space);
			
			
		
		}
		
		
		addCircleTrack(context, space);
		//target.go(ControlPanel.Xtrg.x, ControlPanel.Xtrg.y, space);
		//System.out.println(allUnits.toString());
		
		if (timeStep == (int)(1/ControlPanel.T)) {
			Time ++;
			
			
			Print(Time, getWScal(), filename1);
			Print(Time, getWvel(), filename2);
			Print(Time, getR(), filename3);
			timeStep = 0;
		}
	}
	
	
	public void addCircleTrack(Context<Object> context, ContinuousSpace<Object> space) {
		Vector2d pos = target.pos;
		double step = ControlPanel.Vflock * ControlPanel.T;
		//起始点（80， 80）
		//向下移动
		
		if (pos.x >= 80 && pos.y <= 80 && pos.y > 20) {
			pos.x = 80;
			pos.y -= step;
		}
		//向左移动(80, 20)
		if (pos.x <= 80 && pos.x > 20 && pos.y <= 20) {
			pos.x -= step;
			pos.y = 20;
		}
		//向上移动(20, 20)
		if (pos.x <= 20 && pos.y >= 20 && pos.y < 80) {
			pos.x = 20;
			pos.y += step;
		}
		//向右移动(20, 80)
		if (pos.x < 80 && pos.x >= 20 && pos.y >= 80) {
			pos.x += step;
			pos.y = 80;
		}
		
		target.go(pos.x, pos.y, space);
		
	}
	
	
	public void addWalls(Context<Object> context, ContinuousSpace<Object> space){
		allWalls = new ArrayList<>();
		
		for (int i = 0; i < Define.WallNum; i ++) {
			allWalls.add(new Wall(Math.random() * Define.spaceWidth, Math.random() * Define.spaceHeight, context, space));
		}
	}
	
	public void addLineWall(Context<Object> context, ContinuousSpace<Object> space) {
		allWalls = new ArrayList<>();
		
		for (int i = 0; i < 20; i ++) {
			allWalls.add(new Wall(ControlPanel.Wall_x + i, ControlPanel.Wall_y, context, space));
		}
	}
	
	public void addSquareWall() {
		allWalls = new ArrayList<>();
		
		for (int i = 0; i < 100; i ++) {
			allWalls.add(new Wall(0, i, context, space));
		}
		for (int i = 1; i < 100; i ++) {
			allWalls.add(new Wall(i, 99, context, space));
		}
		for (int i = 0; i < 99; i ++) {
			allWalls.add(new Wall(99, i, context, space));
		}
		for (int i = 1; i < 99; i ++) {
			allWalls.add(new Wall(i, 0, context, space));
		}
	}
	
	public void addScaleBar() {
		allScaleBars = new ArrayList<>();
		for (int i = 0; i < 10; i ++) {
			allScaleBars.add(new ScaleBar(i * 10, 0, i * 10, "bottom", context, space));
		}
		for (int i = 0; i < 10; i ++) {
			allScaleBars.add(new ScaleBar(99, i * 10, i * 10, "left", context, space));
		}
		for (int i = 0; i < 10; i ++) {
			allScaleBars.add(new ScaleBar(99 - i * 10, 99, i * 10, "top", context, space));
		}
		for (int i = 0; i < 10; i ++) {
			allScaleBars.add(new ScaleBar(0, 99 - i * 10, i * 10, "left", context, space));
		}
		
		
	}
	
	public double getWScal(){
		double sum1 = 0;
		for (int i = 0; i < Define.N; i ++) {
			double sum2 = 0;
			for (int j = 0; j < Define.N; j ++) {
				if (j == i) continue;
				Vector2d v1 = allUnits.get(i).velocity;
				Vector2d v2 = allUnits.get(j).velocity;
				sum2 += VmultV(v1, v2)/(v1.length() * v2.length());
			}
			sum1 += sum2;
		}
		return sum1/(Define.N * (Define.N - 1));
	}
	
	
	public double getWvel(){
		double sum = 0;
		for (int i = 0; i < Define.N; i ++) {
			sum += allUnits.get(i).velocity.length();
		}
		return sum/Define.N;
	} 
	
	public double getR() {
		double sum = 0;
		for (int i = 0; i < Define.N; i ++) {
			double r = Double.MAX_VALUE;
			for (int j = 0; j < Define.N; j ++) {
				if (j == i) continue;
				double d = distance(allUnits.get(j).pos, allUnits.get(i).pos);
				if (d < r) r = d;
			}
			sum += r;
		}
		return sum/Define.N;
	}
	
	public void Print(double x, double y, String filename) {
		
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			FileWriter filewriter = new FileWriter(file, true);
			filewriter.write(y + " " + x + "\n");
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public double distance(Vector2d v1, Vector2d v2) {
		return Math.pow((v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y), 0.5);
	}
	
	
	
	public double VmultV(Vector2d v1, Vector2d v2) {
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	
	public Vector2d getCoM(ArrayList<Unit> allUnits){
		Vector2d com = new Vector2d(0, 0);
		for (Unit other : allUnits) {
			com.add(other.pos);
		}
		return divide(com, Define.N);
	}
	
	
	public Vector2d divide(Vector2d v, double n) {
		return new Vector2d(v.x/n, v.y/n);
	}
}

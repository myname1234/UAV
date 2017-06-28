package flocking_flight;

import java.awt.Color;
import java.awt.Font;

import repast.simphony.visualizationOGL2D.StyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

public class BoidStyle implements StyleOGL2D<Boid>{

	private ShapeFactory2D shapefactory;
	
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		this.shapefactory = factory;
	}

	@Override
	public VSpatial getVSpatial(Boid object, VSpatial spatial) {
		// TODO Auto-generated method stub
		return shapefactory.createRectangle(25, 200);
	}

	@Override
	public Color getColor(Boid object) {
		// TODO Auto-generated method stub
		return Color.red;
	}

	@Override
	public int getBorderSize(Boid object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Color getBorderColor(Boid object) {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public float getRotation(Boid object) {
		// TODO Auto-generated method stub
		return (float)Math.abs(Math.atan(object.move.y / object.move.x) * 180 / Math.PI - 90);
	}

	@Override
	public float getScale(Boid object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel(Boid object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getLabelFont(Boid object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLabelXOffset(Boid object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLabelYOffset(Boid object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getLabelPosition(Boid object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(Boid object) {
		// TODO Auto-generated method stub
		return null;
	}

}

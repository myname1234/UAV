package flocking_flight;

import java.awt.Color;
import java.awt.Font;

import repast.simphony.visualizationOGL2D.StyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

public class WallStyle implements StyleOGL2D<Wall>{

	ShapeFactory2D shapefactory;
	
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		this.shapefactory = factory;
	}

	@Override
	public VSpatial getVSpatial(Wall object, VSpatial spatial) {
		// TODO Auto-generated method stub
		return shapefactory.createRectangle(1 * 100, 1 * 100);
	}

	@Override
	public Color getColor(Wall object) {
		// TODO Auto-generated method stub
		return Color.red;
	}

	@Override
	public int getBorderSize(Wall object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getBorderColor(Wall object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getRotation(Wall object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getScale(Wall object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel(Wall object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getLabelFont(Wall object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLabelXOffset(Wall object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLabelYOffset(Wall object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getLabelPosition(Wall object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(Wall object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

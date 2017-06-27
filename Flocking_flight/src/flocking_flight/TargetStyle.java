package flocking_flight;

import java.awt.Color;
import java.awt.Font;

import repast.simphony.visualizationOGL2D.StyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

public class TargetStyle implements StyleOGL2D<Target>{
	
	ShapeFactory2D shapefactory;
	
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		this.shapefactory = factory;
	}

	@Override
	public VSpatial getVSpatial(Target object, VSpatial spatial) {
		// TODO Auto-generated method stub
		return shapefactory.createCircle(25, 20);
	}

	@Override
	public Color getColor(Target object) {
		// TODO Auto-generated method stub
		return Color.red;
	}

	@Override
	public int getBorderSize(Target object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Color getBorderColor(Target object) {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public float getRotation(Target object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getScale(Target object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel(Target object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getLabelFont(Target object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLabelXOffset(Target object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLabelYOffset(Target object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getLabelPosition(Target object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(Target object) {
		// TODO Auto-generated method stub
		return null;
	}

}

package flocking_flight;

import java.awt.Color;
import java.awt.Font;

import repast.simphony.visualizationOGL2D.StyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

public class ScaleBarStyle implements StyleOGL2D<ScaleBar>{
	
	ShapeFactory2D factory;
	
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		this.factory = factory;
	}

	@Override
	public VSpatial getVSpatial(ScaleBar object, VSpatial spatial) {
		// TODO Auto-generated method stub
		if (object.loc.equals("bottom") || object.loc.equals("top")) return factory.createRectangle(20, 100);
		else return factory.createRectangle(100, 20);
	}

	@Override
	public Color getColor(ScaleBar object) {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public int getBorderSize(ScaleBar object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getBorderColor(ScaleBar object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getRotation(ScaleBar object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getScale(ScaleBar object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel(ScaleBar object) {
		// TODO Auto-generated method stub
		return object.id + "";
	}

	@Override
	public Font getLabelFont(ScaleBar object) {
		// TODO Auto-generated method stub
		if (object.loc.equals("bottom"))
		return new Font("TimesRoman", Font.PLAIN, 300);
		return null;
	}

	@Override
	public float getLabelXOffset(ScaleBar object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLabelYOffset(ScaleBar object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getLabelPosition(ScaleBar object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(ScaleBar object) {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}
	
}

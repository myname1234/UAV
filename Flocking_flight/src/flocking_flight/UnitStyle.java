package flocking_flight;

import java.awt.Color;
import java.awt.Font;

import repast.simphony.visualizationOGL2D.StyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

public class UnitStyle implements StyleOGL2D<Unit>{

	private ShapeFactory2D shapefactory;
	
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		this.shapefactory = factory;
	}

	@Override
	public VSpatial getVSpatial(Unit object, VSpatial spatial) {
		// TODO Auto-generated method stub
		return shapefactory.createRectangle(25, 200);
	}

	@Override
	public Color getColor(Unit object) {
		// TODO Auto-generated method stub
		return Color.RED;
	}

	@Override
	public int getBorderSize(Unit object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Color getBorderColor(Unit object) {
		// TODO Auto-generated method stub
		return Color.BLACK;
	}

	@Override
	public float getRotation(Unit object) {
		// TODO Auto-generated method stub
		return (float)Math.abs(Math.atan(object.velocity.y / object.velocity.x) * 180 / Math.PI - 90);
	}

	@Override
	public float getScale(Unit object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel(Unit object) {
		// TODO Auto-generated method stub
		//return object.id + "";
		return null;
	}

	@Override
	public Font getLabelFont(Unit object) {
		// TODO Auto-generated method stub
		
		//return new Font("TimesRoman",  Font.BOLD, 10);
		return null;
	}

	@Override
	public float getLabelXOffset(Unit object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLabelYOffset(Unit object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getLabelPosition(Unit object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(Unit object) {
		// TODO Auto-generated method stub
		return Color.black;
	}

}

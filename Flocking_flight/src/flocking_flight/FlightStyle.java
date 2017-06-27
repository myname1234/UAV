package flocking_flight;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import repast.simphony.visualizationOGL2D.StyleOGL2D;
import saf.v3d.ShapeFactory2D;
import saf.v3d.scene.Position;
import saf.v3d.scene.VSpatial;

public class FlightStyle implements StyleOGL2D<Flight>{

	ShapeFactory2D shapefactory;
	
	@Override
	public void init(ShapeFactory2D factory) {
		// TODO Auto-generated method stub
		this.shapefactory = factory;
	}

	@Override
	public VSpatial getVSpatial(Flight object, VSpatial spatial) {
		// TODO Auto-generated method stub
		//return shapefactory.createCircle(ControlPanel.FlightR, 20);
		try {
			return shapefactory.createImage("./icons/uav.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Color getColor(Flight object) {
		// TODO Auto-generated method stub
		return Color.white;
	}

	@Override
	public int getBorderSize(Flight object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Color getBorderColor(Flight object) {
		// TODO Auto-generated method stub
		return Color.red;
	}

	@Override
	public float getRotation(Flight object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getScale(Flight object) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel(Flight object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getLabelFont(Flight object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getLabelXOffset(Flight object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLabelYOffset(Flight object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position getLabelPosition(Flight object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getLabelColor(Flight object) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

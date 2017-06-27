package flocking_flight;

import javax.swing.JPanel;

import DataAnalysis.ScalChart;
import repast.simphony.userpanel.ui.UserPanelCreator;

public class ChartPanel implements UserPanelCreator{

	@Override
	public JPanel createPanel() {
		// TODO Auto-generated method stub
		ScalChart demo1 = new ScalChart(3000, "Scal", "");
		return demo1;
	}

}

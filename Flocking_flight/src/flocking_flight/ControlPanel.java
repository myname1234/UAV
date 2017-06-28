package flocking_flight;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.Graphics;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Vector2d;

import DataAnalysis.RChart;
import DataAnalysis.RealTimeChart;
import DataAnalysis.ScalChart;
import DataAnalysis.VelChart;
import repast.simphony.userpanel.ui.UserPanelCreator;

public class ControlPanel implements UserPanelCreator, ChangeListener{
	
	/*
	 * 
Model Settings:

	Repulsion Strength (D): default 1.0 (per s)
	EQ Distance (r0): default 10.0 (m)
	Friction Coefficient (Cfrict ): default 10.0 (m^2/s)
	Shill Coefficient (Cshill): default 2.0
	Flocking Speed (Vflock): default 4.0 (m/s)
	Preferred Speed (V0): default 4.0 (m/s)
	TRG Coefficient (alpha): default 1.0 
	COM Coefficient (beta): default 1.0

Hardware Settings:

	GPS Refresh Rate: default 0.2 (s)
	Sensor Range: default 100 (m)
	Relaxation Time of PID: default 1.0 (s)
	GPS xy Accuracy (inner noise): default 0.000 (m^2/s^2)

Environment Settings: 

	Delay Time: default 1 (s)
	Outer Noise: default 0.1 (m^2/s^3)

Simulation Settings:

	Visualization Speed: default 50 (per s)
	X Position of Target:
	Y Position of Target: 


	 */
	
	public static double Rtrg = 6.5;
	public static double Rcom = 15;
	public static double Rmin = 10;
	public static double d = 2;
	public static int Wall_x = 50;
	public static int Wall_y = 50;
	
	
	
	public static double D = 1;
	public static double R0 = 10;
	public static double Cfrict = 10;
	public static double Cshill = 2;
	public static double Vflock = 4;
	public static double V0 = 4;
	public static double Aa = 1;
	public static double Bb = 1;
	
	
	
	public static double Trec = 0.2;
	public static double Rc = 100;
	public static double t = 1;
	public static double innerNoise = 0;
	
	public static double Tdel = 1;
	public static double outerNoise = 0.1;
	
	public static double T = 0.02;//50/s
	public static Vector2d Xtrg = new Vector2d(80, 80);
	
	

	JSlider sliderD;
	JSlider sliderR0;
	JSlider sliderCfrict;
	JSlider sliderCshill;
	JSlider sliderVflock;
	JSlider sliderV0;
	JSlider sliderAa;
	JSlider sliderBb;
	
	JSlider sliderTrec;
	JSlider sliderRc;
	JSlider slidert;
	JSlider sliderInnerNoise;
	
	JSlider sliderTdel;
	JSlider sliderOuterNoise;
	
	JSlider sliderT;
	JSlider sliderXtrg_x;
	JSlider sliderXtrg_y;
	
	JLabel labelD;
	JLabel labelR0;
	JLabel labelCfrict;
	JLabel labelCshill;
	JLabel labelVflock;
	JLabel labelV0;
	JLabel labelAa;
	JLabel labelBb;
	
	JLabel labelTrec;
	JLabel labelRc;
	JLabel labelt;
	JLabel labelInnerNoise;
	
	JLabel labelTdel;
	JLabel labelOuterNoise;
	
	JLabel labelT;
	JLabel labelXtrg_x;
	JLabel labelXtrg_y;
	
	JPanel panel;
	JPanel panelSlider;
	JPanel panelSlider1;
	JPanel panelSlider2;
	JPanel panelSlider3;
	JPanel panelSlider4;
	JPanel panelChart;
	
	
	@Override
	public JPanel createPanel() {
		// TODO Auto-generated method stub
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("控制面板"));				
		panel.setBounds(5, 20, 1010, 900);
		
		ScalChart demo1 = new ScalChart(3000, "Scal", "");
		
		VelChart demo2 = new VelChart(3000, "Vel", "");
		
		RChart demo3 = new RChart(3000, "R", "");
		
		
		panelChart = new JPanel();
		panelChart.setLayout(null);
		panelChart.setBounds(5, 20, 450, 880);
		
		demo1.setBounds(0, 0, 445, 260);
		demo2.setBounds(0, 270, 445, 260);
		demo3.setBounds(0, 540, 445, 260);
		
		panelChart.add(demo1);
		panelChart.add(demo2);
		panelChart.add(demo3);
		
		panelSlider = new JPanel();
		panelSlider.setLayout(null);
		panelSlider.setBorder(BorderFactory.createTitledBorder("控制参数"));
		panelSlider.setBounds(450, 0, 550, 880);
		
		panelSlider1 = new JPanel();
		panelSlider1.setLayout(null);
		panelSlider1.setBorder(BorderFactory.createTitledBorder("Model Settings:"));
		panelSlider1.setBounds(5, 20, 540, 340);
		
		panelSlider2 = new JPanel();
		panelSlider2.setLayout(null);
		panelSlider2.setBorder(BorderFactory.createTitledBorder("Hardware Settings:"));
		panelSlider2.setBounds(5, 380, 540, 180);
		
		panelSlider3 = new JPanel();
		panelSlider3.setLayout(null);
		panelSlider3.setBorder(BorderFactory.createTitledBorder("Environment Settings"));
		panelSlider3.setBounds(5, 580, 540, 100);
		
		panelSlider4 = new JPanel();
		panelSlider4.setLayout(null);
		panelSlider4.setBorder(BorderFactory.createTitledBorder("Simulation Settings:"));
		panelSlider4.setBounds(5, 700, 540, 140);
		
		sliderD = new JSlider();
		sliderD.setMaximum(20);
		sliderD.setMinimum(0);
		sliderD.setPaintTicks(true);
		sliderD.setPaintLabels(true);
		sliderD.setValue((int) (D));
		labelD = new JLabel("Repulsion Strength (D): default 1.0 (per s)");
		sliderD.addChangeListener(this);
		
		sliderR0 = new JSlider();
		sliderR0.setMaximum(20);
		sliderR0.setMinimum(0);
		sliderR0.setPaintTicks(true);
		sliderR0.setPaintLabels(true);
		sliderR0.setValue((int) (R0));
		labelR0 = new JLabel("EQ Distance (r0): default 10.0 (m)");
		sliderR0.addChangeListener(this);
		
		sliderCfrict = new JSlider();
		sliderCfrict.setMaximum(100);
		sliderCfrict.setMinimum(0);
		sliderCfrict.setPaintTicks(true);
		sliderCfrict.setPaintLabels(true);
		sliderCfrict.setValue((int) (Cfrict));
		labelCfrict = new JLabel("Friction Coefficient (Cfrict ): default 10.0 (m^2/s)");
		sliderCfrict.addChangeListener(this);
		
		sliderCshill = new JSlider();
		sliderCshill.setMaximum(100);
		sliderCshill.setMinimum(0);
		sliderCshill.setPaintTicks(true);
		sliderCshill.setPaintLabels(true);
		sliderCshill.setValue((int) (Cshill));
		labelCshill = new JLabel("Shill Coefficient (Cshill): default 2.0");
		sliderCshill.addChangeListener(this);
		
		sliderVflock = new JSlider();
		sliderVflock.setMaximum(10);
		sliderVflock.setMinimum(0);
		sliderVflock.setPaintTicks(true);
		sliderVflock.setPaintLabels(true);
		sliderVflock.setValue((int) (Vflock));
		labelVflock = new JLabel("Flocking Speed (Vflock): default 4.0 (m/s)");
		sliderVflock.addChangeListener(this);
		
		sliderV0 = new JSlider();
		sliderV0.setMaximum(10);
		sliderV0.setMinimum(0);
		sliderV0.setPaintTicks(true);
		sliderV0.setPaintLabels(true);
		sliderV0.setValue((int) (V0));
		labelV0 = new JLabel("Preferred Speed (V0): default 4.0 (m/s)");
		sliderV0.addChangeListener(this);
		
		sliderAa = new JSlider();
		sliderAa.setMaximum(10);
		sliderAa.setMinimum(0);
		sliderAa.setPaintTicks(true);
		sliderAa.setPaintLabels(true);
		sliderAa.setValue((int) (Aa));
		labelAa = new JLabel("TRG Coefficient (alpha): default 1.0 ");
		sliderAa.addChangeListener(this);
		
		sliderBb = new JSlider();
		sliderBb.setMaximum(10);
		sliderBb.setMinimum(0);
		sliderBb.setPaintTicks(true);
		sliderBb.setPaintLabels(true);
		sliderBb.setValue((int) (Bb));
		labelBb = new JLabel("COM Coefficient (beta): default 1.0");
		sliderBb.addChangeListener(this);
		
		sliderD.setBounds(5, 20, 200, 35);
		labelD.setBounds(205, 20, 340, 35);
		sliderR0.setBounds(5, 60, 200, 35);
		labelR0.setBounds(205, 60, 340, 35);
		sliderCfrict.setBounds(5, 100, 200, 35);
		labelCfrict.setBounds(205, 100, 340, 35);
		sliderCshill.setBounds(5, 140, 200, 35);
		labelCshill.setBounds(205, 140, 340, 35);
		sliderVflock.setBounds(5, 180, 200, 35);
		labelVflock.setBounds(205, 180, 340, 35);
		sliderV0.setBounds(5, 220, 200, 35);
		labelV0.setBounds(205, 220, 340, 35);
		sliderAa.setBounds(5, 260, 200, 35);
		labelAa.setBounds(205, 260, 340, 35);
		sliderBb.setBounds(5, 300, 200, 35);
		labelBb.setBounds(205, 300, 340, 35);
		
		
		sliderTrec = new JSlider();
		sliderTrec.setMaximum(10);
		sliderTrec.setMinimum(0);
		sliderTrec.setPaintTicks(true);
		sliderTrec.setPaintLabels(true);
		sliderTrec.setValue((int) (Trec));
		labelTrec = new JLabel("GPS Refresh Rate: default 0.2 (s)");
		sliderTrec.addChangeListener(this);
		
		sliderRc = new JSlider();
		sliderRc.setMaximum(200);
		sliderRc.setMinimum(0);
		sliderRc.setPaintTicks(true);
		sliderRc.setPaintLabels(true);
		sliderRc.setValue((int) (Rc));
		labelRc = new JLabel("Sensor Range: default 100 (m)");
		sliderRc.addChangeListener(this);
		
		slidert = new JSlider();
		slidert.setMaximum(10);
		slidert.setMinimum(0);
		slidert.setPaintTicks(true);
		slidert.setPaintLabels(true);
		slidert.setValue((int) (t));
		labelt = new JLabel("Relaxation Time of PID: default 1.0 (s)");
		slidert.addChangeListener(this);
		
		sliderInnerNoise = new JSlider();
		sliderInnerNoise.setMaximum(10);
		sliderInnerNoise.setMinimum(0);
		sliderInnerNoise.setPaintTicks(true);
		sliderInnerNoise.setPaintLabels(true);
		sliderInnerNoise.setValue((int) (innerNoise));
		labelInnerNoise = new JLabel("GPS xy Accuracy (inner noise): default 0.000 (m^2/s^2)");
		sliderInnerNoise.addChangeListener(this);
		
		sliderTrec.setBounds(5, 20, 200, 35);
		labelTrec.setBounds(205, 20, 340, 35);
		sliderRc.setBounds(5, 60, 200, 35);
		labelRc.setBounds(205, 60, 340, 35);
		slidert.setBounds(5, 100, 200, 35);
		labelt.setBounds(205, 100, 340, 35);
		sliderInnerNoise.setBounds(5, 140, 200, 35);
		labelInnerNoise.setBounds(205, 140, 340, 35);
		
		
		sliderTdel = new JSlider();
		sliderTdel.setMaximum(10);
		sliderTdel.setMinimum(0);
		sliderTdel.setPaintTicks(true);
		sliderTdel.setPaintLabels(true);
		sliderTdel.setValue((int) (Tdel));
		labelTdel = new JLabel("Delay Time: default 1 (s)");
		sliderTdel.addChangeListener(this);
		
		sliderOuterNoise = new JSlider();
		sliderOuterNoise.setMaximum(10);
		sliderOuterNoise.setMinimum(0);
		sliderOuterNoise.setPaintTicks(true);
		sliderOuterNoise.setPaintLabels(true);
		sliderOuterNoise.setValue((int) (outerNoise));
		labelOuterNoise = new JLabel("Outer Noise: default 0.1 (m^2/s^3)");
		sliderOuterNoise.addChangeListener(this);
		
		sliderTdel.setBounds(5, 20, 200, 35);
		labelTdel.setBounds(205, 20, 340, 35);
		sliderOuterNoise.setBounds(5, 60, 200, 35);
		labelOuterNoise.setBounds(205, 60, 340, 35);
		
		
		sliderT = new JSlider();
		sliderT.setMaximum(20);
		sliderT.setMinimum(0);
		sliderT.setPaintTicks(true);
		sliderT.setPaintLabels(true);
		sliderT.setValue((int) (T * 1000));
		labelT = new JLabel("Visualization Speed: default 50 (per s)");
		sliderT.addChangeListener(this);
		
		sliderXtrg_x = new JSlider();
		sliderXtrg_x.setMaximum((int) (Define.spaceWidth - 20));
		sliderXtrg_x.setMinimum(20);
		sliderXtrg_x.setPaintTicks(true);
		sliderXtrg_x.setPaintLabels(true);
		sliderXtrg_x.setValue((int) (Xtrg.x));
		labelXtrg_x = new JLabel("X Position of Target" + Xtrg.x);
		sliderXtrg_x.addChangeListener(this);
		
		sliderXtrg_y = new JSlider();
		sliderXtrg_y.setMaximum((int) (Define.spaceHeight - 20));
		sliderXtrg_y.setMinimum(20);
		sliderXtrg_y.setPaintTicks(true);
		sliderXtrg_y.setPaintLabels(true);
		sliderXtrg_y.setValue((int) (Xtrg.y));
		labelXtrg_y = new JLabel("Y Position of Target:" + Xtrg.y);
		sliderXtrg_y.addChangeListener(this);
		
		sliderT.setBounds(5, 20, 200, 35);
		labelT.setBounds(205, 20, 340, 35);
		sliderXtrg_x.setBounds(5, 60, 200, 35);
		labelXtrg_x.setBounds(205, 60, 340, 35);
		sliderXtrg_y.setBounds(5, 100, 200, 35);
		labelXtrg_y.setBounds(205, 100, 340, 35);
		
		
		
		
	
	
		panelSlider1.add(sliderD);
		panelSlider1.add(labelD);
		panelSlider1.add(sliderR0);
		panelSlider1.add(labelR0);
		panelSlider1.add(sliderCfrict);
		panelSlider1.add(labelCfrict);
		panelSlider1.add(sliderCshill);
		panelSlider1.add(labelCshill);
		panelSlider1.add(sliderVflock);
		panelSlider1.add(labelVflock);
		panelSlider1.add(sliderV0);
		panelSlider1.add(labelV0);
		panelSlider1.add(sliderAa);
		panelSlider1.add(labelAa);
		panelSlider1.add(sliderBb);
		panelSlider1.add(labelBb);
		
		
		panelSlider2.add(sliderTrec);
		panelSlider2.add(labelTrec);
		panelSlider2.add(sliderRc);
		panelSlider2.add(labelRc);
		panelSlider2.add(slidert);
		panelSlider2.add(labelt);
		panelSlider2.add(sliderInnerNoise);
		panelSlider2.add(labelInnerNoise);
	
		
		panelSlider3.add(sliderTdel);
		panelSlider3.add(labelTdel);
		panelSlider3.add(sliderOuterNoise);
		panelSlider3.add(labelOuterNoise);
	
		
		panelSlider4.add(sliderT);
		panelSlider4.add(labelT);
		panelSlider4.add(sliderXtrg_x);
		panelSlider4.add(labelXtrg_x);
		panelSlider4.add(sliderXtrg_y);
		panelSlider4.add(labelXtrg_y);
	
		panelSlider.add(panelSlider1);
		panelSlider.add(panelSlider2);
		panelSlider.add(panelSlider3);
		panelSlider.add(panelSlider4);
		
		panel.add(panelChart);
		panel.add(panelSlider);
		
		return panel;
	}


	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		if ((JSlider)e.getSource() == sliderD) {
			labelD.setText("Repulsion Strength (D): " + sliderD.getValue() + " (per s)");
			D = sliderD.getValue();
		}
		if ((JSlider)e.getSource() == sliderR0) {
			labelR0.setText("EQ Distance (r0): " + sliderR0.getValue() + " (m)");
			R0 = sliderR0.getValue();
		}
		if ((JSlider)e.getSource() == sliderCfrict) {
			labelCfrict.setText("Friction Coefficient (Cfrict ): " + sliderCfrict.getValue() + " (m^2/s)");
			Cfrict = sliderCfrict.getValue();
		}
		if ((JSlider)e.getSource() == sliderCshill) {
			labelCshill.setText("Shill Coefficient (Cshill): " + sliderCshill.getValue());
			Cshill = sliderCshill.getValue();
		}
		if ((JSlider)e.getSource() == sliderVflock) {
			labelVflock.setText("Flocking Speed (Vflock): " + sliderVflock.getValue() + "(m/s)");
			Vflock = sliderVflock.getValue();
		}
		if ((JSlider)e.getSource() == sliderV0) {
			labelV0.setText("Preferred Speed (V0): " + sliderV0.getValue() + " (m/s)");
			V0 = sliderV0.getValue();
		}
		if ((JSlider)e.getSource() == sliderAa) {
			labelAa.setText("TRG Coefficient (alpha): " + sliderAa.getValue());
			Aa = sliderAa.getValue();
		}
		if ((JSlider)e.getSource() == sliderBb) {
			labelBb.setText("COM Coefficient (beta): " + sliderBb.getValue());
			Bb = sliderBb.getValue();
		}
		
		
		if ((JSlider)e.getSource() == sliderTrec) {
			labelTrec.setText("GPS Refresh Rate: " + sliderTrec.getValue() + " (s)");
			Trec = sliderTrec.getValue();
		}
		if ((JSlider)e.getSource() == sliderRc) {
			labelRc.setText("Sensor Range: " + sliderRc.getValue() + " (m)");
			Rc = sliderRc.getValue();
		}
		if ((JSlider)e.getSource() == slidert) {
			labelt.setText("Relaxation Time of PID: " + slidert.getValue() + " (s)");
			t = slidert.getValue();
		}
		if ((JSlider)e.getSource() == sliderInnerNoise) {
			labelInnerNoise.setText("GPS xy Accuracy (inner noise): " + sliderInnerNoise.getValue());
			innerNoise = sliderInnerNoise.getValue();
		}
		
		
		if ((JSlider)e.getSource() == sliderTdel) {
			labelTdel.setText("Delay Time: " + sliderTdel.getValue() + " (s)");
			Tdel = sliderTdel.getValue();
		}
		if ((JSlider)e.getSource() == sliderOuterNoise) {
			labelOuterNoise.setText("Outer Noise: " + sliderOuterNoise.getValue() + " (m^2/s^3)");
			outerNoise = sliderOuterNoise.getValue();
		}
		
		
		if ((JSlider)e.getSource() == sliderT) {
			labelT.setText("Visualization Speed: " + 1/((double)sliderT.getValue()/1000) + " (per s)");
			T = (double)sliderT.getValue()/1000;
		}
		if ((JSlider)e.getSource() == sliderXtrg_x) {
			labelXtrg_x.setText("X Position of Target: " + sliderXtrg_x.getValue());
			Xtrg.x = sliderXtrg_x.getValue();
		}
		if ((JSlider)e.getSource() == sliderXtrg_y) {
			labelXtrg_y.setText("Y Position of Target: " + sliderXtrg_y.getValue());
			Xtrg.y = sliderXtrg_y.getValue();
		}
	
	}

}


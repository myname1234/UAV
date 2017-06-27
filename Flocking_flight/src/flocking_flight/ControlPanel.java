package flocking_flight;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import java.awt.Graphics;

import java.awt.GridLayout;


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
	
	
	public static double R0 = 10;
	public static double Rmin = 10;
	public static double Rtrg = 6.5;
	public static double Rcom = 15;
	public static double RC = 100;
	
	public static double D = 1;
	public static double Cfrict = 10;
	public static double Cshill = 2;
	
	
	public static double Aa = 1;
	public static double Bb = 1;
	public static double d = 2;
	
	
	public static double V0 = 2;
	public static double Vflock = 4;
	
	
	public static double Tdel = 1;
	public static double t = 1;
	public static double T = 0.01;
	//public static double Trec = 5;
	
	
	public static double Noise = 0;
	
	public static Vector2d Xtrg = new Vector2d(80, 80);
	
	public static int Wall_x = 50;
	public static int Wall_y = 50;
	
	
	JSlider slider1;
	JSlider slider2;
	JSlider slider3;
	JSlider slider4;
	JSlider slider5;
	JSlider slider6;
	JSlider slider7;
	JSlider slider8;
	JSlider slider9;
	JSlider slider10;
	JSlider slider11;
	JSlider slider12;
	JSlider slider13;
	JSlider slider14;
	JSlider slider15;
	JSlider slider16;
	JSlider slider17;
	JSlider slider18;
	
	
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel label10;
	JLabel label11;
	JLabel label12;
	JLabel label13;
	JLabel label14;
	JLabel label15;
	JLabel label16;
	JLabel label17;
	JLabel label18;
	
	
	
	JPanel panel;
	JPanel panelSlider;
	JPanel panelChart;
	
	
	@Override
	public JPanel createPanel() {
		// TODO Auto-generated method stub
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
						
		ScalChart demo1 = new ScalChart(3000, "Scal", "");
		
		
		VelChart demo2 = new VelChart(3000, "Vel", "");
		
		
		RChart demo3 = new RChart(3000, "R", "");
		
		
		panelChart = new JPanel();
		panelChart.setLayout(null);
		
		demo1.setBounds(0, 0, 450, 280);
		demo2.setBounds(0, 300, 450, 280);
		demo3.setBounds(0, 600, 450, 280);
		
		panelChart.add(demo1);
		panelChart.add(demo2);
		panelChart.add(demo3);
		
		panelSlider = new JPanel();
		panelSlider.setLayout(new GridLayout(18, 2));
		
		slider1 = new JSlider();
		slider1.setMaximum(100);
		slider1.setMinimum(0);
		slider1.setPaintTicks(true);
		slider1.setPaintLabels(true);
		slider1.setValue((int) (Rtrg * 10));
		label1 = new JLabel("(目标区飞行半径)Rtrg：" + Rtrg);
		slider1.addChangeListener(this);
		
		slider2 = new JSlider();
		slider2.setMaximum(20);
		slider2.setMinimum(0);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setValue((int) (R0));
		label2 = new JLabel("(平衡半径)R0：" + R0);
		slider2.addChangeListener(this);
		
		slider3 = new JSlider();
		slider3.setMaximum(20);
		slider3.setMinimum(0);
		slider3.setPaintTicks(true);
		slider3.setPaintLabels(true);
		slider3.setValue((int) (Rmin));
		label3 = new JLabel("(最小半径)Rmin：" + Rmin);
		slider3.addChangeListener(this);
		
		slider4 = new JSlider();
		slider4.setMaximum(100);
		slider4.setMinimum(0);
		slider4.setPaintTicks(true);
		slider4.setPaintLabels(true);
		slider4.setValue((int) (Rcom));
		label4 = new JLabel("(编队飞行半径)Rcom：" + Rcom);
		slider4.addChangeListener(this);
		
		slider5 = new JSlider();
		slider5.setMaximum(100);
		slider5.setMinimum(0);
		slider5.setPaintTicks(true);
		slider5.setPaintLabels(true);
		slider5.setValue((int) (D));
		label5 = new JLabel("(相互排斥强度)D：" + D);
		slider5.addChangeListener(this);
		
		slider6 = new JSlider();
		slider6.setMaximum(100);
		slider6.setMinimum(0);
		slider6.setPaintTicks(true);
		slider6.setPaintLabels(true);
		slider6.setValue((int) (Cfrict));
		label6 = new JLabel("(速度对齐强度)Cfrict：" + Cfrict);
		slider6.addChangeListener(this);
		
		slider7 = new JSlider();
		slider7.setMaximum(10);
		slider7.setMinimum(0);
		slider7.setPaintTicks(true);
		slider7.setPaintLabels(true);
		slider7.setValue((int) (Cshill));
		label7 = new JLabel("(编队飞行虚拟边界作用力强度)Cshill：" + Cshill);
		slider7.addChangeListener(this);
		
		slider8 = new JSlider();
		slider8.setMaximum(10);
		slider8.setMinimum(0);
		slider8.setPaintTicks(true);
		slider8.setPaintLabels(true);
		slider8.setValue((int) (Noise));
		label8 = new JLabel("(噪音强度)Noise：" + Noise);
		slider8.addChangeListener(this);
		
		slider9 = new JSlider();
		slider9.setMaximum(10);
		slider9.setMinimum(0);
		slider9.setPaintTicks(true);
		slider9.setPaintLabels(true);
		slider9.setValue((int) (d));
		label9 = new JLabel("(编队飞行虚拟边界作用范围)d：" + d);
		slider9.addChangeListener(this);
		
		slider10 = new JSlider();
		slider10.setMaximum(10);
		slider10.setMinimum(0);
		slider10.setPaintTicks(true);
		slider10.setPaintLabels(true);
		slider10.setValue((int) (V0));
		label10 = new JLabel("(向目标飞行速度)V0：" + V0);
		slider10.addChangeListener(this);
		
		slider11 = new JSlider();
		slider11.setMaximum(100);
		slider11.setMinimum(0);
		slider11.setPaintTicks(true);
		slider11.setPaintLabels(true);
		slider11.setValue((int) (Vflock));
		label11 = new JLabel("(群聚速度)Vflock：" + Vflock);
		slider11.addChangeListener(this);
		
		slider12 = new JSlider();
		slider12.setMaximum(10);
		slider12.setMinimum(0);
		slider12.setPaintTicks(true);
		slider12.setPaintLabels(true);
		slider12.setValue((int) (Tdel));
		label12 = new JLabel("(传输延时)Tdel：" + Tdel);
		slider12.addChangeListener(this);
		
		slider13 = new JSlider();
		slider13.setMaximum(10);
		slider13.setMinimum(0);
		slider13.setPaintTicks(true);
		slider13.setPaintLabels(true);
		slider13.setValue((int) (t));
		label13 = new JLabel("(控制延时)t：" + t);
		slider13.addChangeListener(this);
		
		slider14 = new JSlider();
		slider14.setMaximum(20);
		slider14.setMinimum(0);
		slider14.setPaintTicks(true);
		slider14.setPaintLabels(true);
		slider14.setValue((int) (T * 1000));
		label14 = new JLabel("(刷新时间)T：" + T);
		slider14.addChangeListener(this);
		
		slider15 = new JSlider();
		slider15.setMaximum((int) (Define.spaceWidth - 20));
		slider15.setMinimum(20);
		slider15.setPaintTicks(true);
		slider15.setPaintLabels(true);
		slider15.setValue((int) (Xtrg.x));
		label15 = new JLabel("(目标区中心横坐标)Xtrg_x：" + Xtrg.x);
		slider15.addChangeListener(this);
		
		slider16 = new JSlider();
		slider16.setMaximum((int) (Define.spaceHeight - 20));
		slider16.setMinimum(20);
		slider16.setPaintTicks(true);
		slider16.setPaintLabels(true);
		slider16.setValue((int) (Xtrg.y));
		label16 = new JLabel("(目标区中心纵坐标)Xtrg_y：" + Xtrg.y);
		slider16.addChangeListener(this);
		
		/*
		slider17 = new JSlider();
		slider17.setMaximum((int) (Define.spaceWidth - 10));
		slider17.setMinimum(10);
		slider17.setPaintTicks(true);
		slider17.setPaintLabels(true);
		slider17.setValue(Wall_x);
		label17 = new JLabel("Wall_x：" + Wall_x);
		slider17.addChangeListener(this);
		
		slider18 = new JSlider();
		slider18.setMaximum((int) (Define.spaceHeight));
		slider18.setMinimum(0);
		slider18.setPaintTicks(true);
		slider18.setPaintLabels(true);
		slider18.setValue(Wall_y);
		label18 = new JLabel("Wall_y：" + Wall_y);
		slider18.addChangeListener(this);
		*/
		
		panelSlider.add(slider1);
		panelSlider.add(label1);
		panelSlider.add(slider2);
		panelSlider.add(label2);
		panelSlider.add(slider3);
		panelSlider.add(label3);
		panelSlider.add(slider4);
		panelSlider.add(label4);
		panelSlider.add(slider5);
		panelSlider.add(label5);
		panelSlider.add(slider6);
		panelSlider.add(label6);
		panelSlider.add(slider7);
		panelSlider.add(label7);
		panelSlider.add(slider8);
		panelSlider.add(label8);
		panelSlider.add(slider9);
		panelSlider.add(label9);
		panelSlider.add(slider10);
		panelSlider.add(label10);
		panelSlider.add(slider11);
		panelSlider.add(label11);
		panelSlider.add(slider12);
		panelSlider.add(label12);
		panelSlider.add(slider13);
		panelSlider.add(label13);
		panelSlider.add(slider14);
		panelSlider.add(label14);
		panelSlider.add(slider15);
		panelSlider.add(label15);
		panelSlider.add(slider16);
		panelSlider.add(label16);
		/*
		panelSlider.add(slider17);
		panelSlider.add(label17);
		panelSlider.add(slider18);
		panelSlider.add(label18);
		*/
		panel.add(panelChart);
		panel.add(panelSlider);
		
		return panel;
	}


	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		if ((JSlider)e.getSource() == slider1) {
			label1.setText("(目标区飞行半径)Rtrg：" + slider1.getValue()/10);
			Rtrg = slider1.getValue()/10;
		}
		if ((JSlider)e.getSource() == slider2) {
			label2.setText("(平衡半径)R0：" + slider2.getValue());
			R0 = slider2.getValue();
		}
		if ((JSlider)e.getSource() == slider3) {
			label3.setText("(最小半径)Rmin：" + slider3.getValue());
			Rmin = slider3.getValue();
		}
		if ((JSlider)e.getSource() == slider4) {
			label4.setText("(编队飞行半径)Rcom：" + slider4.getValue());
			Rcom = slider4.getValue();
		}
		if ((JSlider)e.getSource() == slider5) {
			label5.setText("(相互排斥强度)D：" + slider5.getValue());
			D = slider5.getValue();
		}
		if ((JSlider)e.getSource() == slider6) {
			label6.setText("(速度对齐强度)Cfrict：" + slider6.getValue());
			Cfrict = slider6.getValue();
		}
		if ((JSlider)e.getSource() == slider7) {
			label7.setText("(编队飞行虚拟边界作用力强度)Cshill：" + slider7.getValue());
			Cshill = slider7.getValue();
		}
		if ((JSlider)e.getSource() == slider8) {
			label8.setText("(噪音强度)Noise：" + slider8.getValue());
			Noise = slider8.getValue();
		}
		if ((JSlider)e.getSource() == slider9) {
			label9.setText("(编队飞行虚拟边界作用范围)d：" + slider9.getValue());
			d = slider9.getValue();
		}
		if ((JSlider)e.getSource() == slider10) {
			label10.setText("(向目标飞行速度)V0：" + slider10.getValue());
			V0 = slider10.getValue();
		}
		if ((JSlider)e.getSource() == slider11) {
			label11.setText("(群聚速度)Vflock：" + slider11.getValue());
			Vflock = slider11.getValue();
		}
		if ((JSlider)e.getSource() == slider12) {
			label12.setText("(传输延时)Tdel：" + slider12.getValue());
			Tdel = slider12.getValue();
		}
		if ((JSlider)e.getSource() == slider13) {
			label13.setText("(控制延时)t：" + slider13.getValue());
			t = slider13.getValue();
		}
		if ((JSlider)e.getSource() == slider14) {
			label14.setText("(刷新时间)T：" + (double)slider14.getValue()/1000);
			T = (double)slider14.getValue()/1000;
		}
		if ((JSlider)e.getSource() == slider15) {
			label15.setText("(目标区中心横坐标)Xtrg_x：" + slider15.getValue());
			Xtrg.x = slider15.getValue();
		}
		if ((JSlider)e.getSource() == slider16) {
			label16.setText("(目标区中心纵坐标)Xtrg_y：" + slider16.getValue());
			Xtrg.y = slider16.getValue();
		}
		/*
		if ((JSlider)e.getSource() == slider17) {
			label17.setText("Wall_x：" + slider17.getValue());
			Wall_x = slider17.getValue();
		}
		if ((JSlider)e.getSource() == slider18) {
			label18.setText("Wall_y：" + slider18.getValue());
			Wall_y = slider18.getValue();
		}*/
	}

}


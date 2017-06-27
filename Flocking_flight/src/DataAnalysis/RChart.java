package DataAnalysis;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import com.sun.javafx.geom.Point2D;

import flocking_flight.Define;

public class RChart extends JPanel
{
	static int count = 1;
	static String filePath = Define.filename3;
	static Point2D lastPoint = new Point2D(0, 0); 
	private XYSeries data;
	
	
    public class DataGenerator extends Timer
        implements ActionListener
    {
    	
    	
        public void actionPerformed(ActionEvent actionevent)
        {
        	
        	String res = readTxtLine(filePath, count);
        	if(res == null) {
        		addTotalObservation(lastPoint.x, lastPoint.y);
        		//System.out.println(0);
        	}
        	else {
        		String temp[] = res.split(" ");
        		lastPoint = new Point2D(count, (float) Double.parseDouble(temp[0]));
        		//System.out.println(temp[0]);
        		addTotalObservation(count, lastPoint.y);
        		
        		count ++;
        	}
            
   
            
        }

        public DataGenerator(int i)
        {
            super(i, null);
            addActionListener(this);
        }
    }

    public String readTxtLine(String txtPath, int line) {
    	String res = null;
    	
    	try {
            File txtFile = new File(txtPath);
            InputStream in = new FileInputStream(txtFile);
            InputStreamReader read = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(read);
            int i = 0;
            while (i < line) {
                res = reader.readLine();
                i++;
            }
            reader.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return res;
    }
    

    public RChart(int i, String dataName, String chartName) 
    {
        super(new BorderLayout());
        this.setBounds(0, 0, 300, 300);
        
        data = new XYSeries(dataName);
        data.setMaximumItemCount(i);
            
        XYSeriesCollection xyseriescollection = new XYSeriesCollection();
        xyseriescollection.addSeries(data);

        
        NumberAxis dateaxis = new NumberAxis("Time(s)");
        NumberAxis numberaxis = new NumberAxis(dataName);
        dateaxis.setTickLabelFont(new Font("SansSerif", 0, 12));
        numberaxis.setTickLabelFont(new Font("SansSerif", 0, 12));
        dateaxis.setLabelFont(new Font("SansSerif", 0, 14));
        numberaxis.setLabelFont(new Font("SansSerif", 0, 14));
        
        
        XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(true, false);
        xylineandshaperenderer.setSeriesPaint(0, Color.red);
        xylineandshaperenderer.setSeriesStroke(0, new BasicStroke(3F, 0, 2));

       
        XYPlot xyplot = new XYPlot(xyseriescollection, dateaxis, numberaxis, xylineandshaperenderer);
        xyplot.setBackgroundPaint(Color.lightGray);
        xyplot.setDomainGridlinePaint(Color.white);
        xyplot.setRangeGridlinePaint(Color.white);
        xyplot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        
        dateaxis.setAutoRange(true);
        dateaxis.setLowerMargin(0.0D);
        dateaxis.setUpperMargin(0.0D);
        dateaxis.setTickLabelsVisible(true);
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
       
        JFreeChart jfreechart = new JFreeChart(chartName, new Font("SansSerif", 1, 24), xyplot, true);
        jfreechart.setBackgroundPaint(Color.white);
      
        ChartPanel chartpanel = new ChartPanel(jfreechart, true);
        chartpanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4), BorderFactory.createLineBorder(Color.black)));
        add(chartpanel);
        new DataGenerator(100).start();
    }

    private void addTotalObservation(double x, double d)
    {
        data.add(x, d);
    }


   


}

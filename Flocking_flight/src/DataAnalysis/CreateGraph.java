package DataAnalysis;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class CreateGraph extends ApplicationFrame {
	List<Double> equation = null;
	// 设置多项式的次数
	int times = 2;

	public CreateGraph(String title) {
		super(title);
		

		// 生成Chart
		JFreeChart chart = this.getChart(getXYDataset(getData(new File("C:/Users/WangXinyu/Desktop/FLOCK/Wscal/1.txt"))));
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		chartPanel.setMouseZoomable(true, false);
		setContentPane(chartPanel);
		try { 
			ChartUtilities.saveChartAsPNG(new File("C:/Users/WangXinyu/Desktop/FLOCK/Wscal/"+ System.currentTimeMillis() +".png"), chart, 550, 250); 
			} catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
			}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateGraph demo = new CreateGraph("WScal");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

	// 生成chart
	public JFreeChart getChart(XYDataset xydataset) {
		// 获取X和Y轴数据集
		
		// 创建用坐标表示的折线图
		JFreeChart xyChart = ChartFactory.createXYLineChart("", "time(s)", "Wscal", xydataset,
				PlotOrientation.VERTICAL, true, true, false);
		// 生成坐标点点的形状
		XYPlot plot = (XYPlot) xyChart.getPlot();

		XYItemRenderer r = plot.getRenderer();
		if (r instanceof XYLineAndShapeRenderer) {
			XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
			renderer.setBaseShapesVisible(false);// 坐标点的形状是否可见
			renderer.setBaseShapesFilled(false);
		}
		ValueAxis yAxis = plot.getRangeAxis();
		yAxis.setLowerMargin(2);
		return xyChart;
	}

	// 数据集按照逻辑关系添加到对应的集合
	public XYDataset getXYDataset(List<List<Double>> data) {
		// 预设数据点数据集
		XYSeries s1 = new XYSeries("line");
		for (int i = 0; i < data.get(0).size(); i++) {
			s1.add(data.get(0).get(i), data.get(1).get(i));
		}
		

		

		// 把预设数据集合拟合数据集添加到XYSeriesCollection
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(s1);
		
		return dataset;

	}

	// 模拟设置绘图数据（点）
	public List<List<Double>> getData(File file) {
		// x为x轴坐标
		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String res = null;
			while ((res = bufferedReader.readLine()) != null) {
				String[] tem = res.trim().split(" ");
				y.add(Double.parseDouble(tem[0]));
				x.add(Double.parseDouble(tem[1]));
			}
			
			bufferedReader.close();
			fileReader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<List<Double>> list = new ArrayList<List<Double>>();
		list.add(x);
		list.add(y);
		return list;

	}

	


	// 获取List中Double数据的最大最小值
	public double getMax(List<Double> data) {
		double res = data.get(0);
		for (int i = 0; i < data.size() - 1; i++) {
			if (res < data.get(i + 1)) {
				res = data.get(i + 1);
			}
		}
		return res;
	}

	public double getMin(List<Double> data) {
		double res = data.get(0);
		for (int i = 0; i < data.size() - 1; i++) {
			if (res > data.get(i + 1)) {
				res = data.get(i + 1);
			}
		}
		return res;
	}

}
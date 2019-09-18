package test;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;

public class waterfallTest {

	
	public static void main(String[] args) {
		JFreeChart chart = createWaterfallChart();
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		chart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null, null);
		g2.dispose();
		
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		CategoryItemRenderer renderer = plot.getRenderer();
		StandardCategoryToolTipGenerator tt = new StandardCategoryToolTipGenerator();
		renderer.setSeriesToolTipGenerator(0, tt);
		CategoryToolTipGenerator tt2 = renderer.getToolTipGenerator(0, 0);

		StandardCategoryURLGenerator url1 = new StandardCategoryURLGenerator();
		renderer.setSeriesItemURLGenerator(0, url1);
		CategoryURLGenerator url2 = renderer.getItemURLGenerator(0, 0);
	}

	private static JFreeChart createWaterfallChart() {
		Number[][] data = new Integer[][]{{new Integer(-3), new Integer(-2)}, {new Integer(-1), new Integer (1)}, {new Integer(2), new Integer(3)}};

		CategoryDataset dataset = DatasetUtils.createCategoryDataset("S", "C", data);
return ChartFactory.createWaterfallChart( "Waterfall Chart", "Domain", "RAnge", dataset, PlotOrientation.HORIZONTAL, true, true, true);
	}
}

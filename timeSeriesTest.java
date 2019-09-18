package test;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
public class timeSeriesTest {
	public static void main(String[] args) {
		JFreeChart chart = createChart(); 
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		chart.draw(g2, new Rectangle2D.Double(0, 0, 200, 100), null, null);
		g2.dispose();

		XYSeries series1 = new XYSeries("Series 1");
		series1.add(10.0, 10.0);
		series1.add(20.0, 20.0);
		series1.add(30.0, 30.0);
		XYDataset dataset = new XYSeriesCollection(series1);

		LocalListener l = new LocalListener();
		chart.addChangeListener(l);
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDataset(dataset);
		ValueAxis axis = plot.getRangeAxis();
		Range range = axis.getRange();
		
	}

	private static JFreeChart createChart() {
        	XYSeries series1 = new XYSeries("Series 1");
        	series1.add(1.0, 1.0);
        	series1.add(2.0, 2.0);
        	series1.add(3.0, 3.0);
        	XYDataset dataset = new XYSeriesCollection(series1);
        	return ChartFactory.createTimeSeriesChart(
            	"XY Line Chart",  // chart title
            	"Domain",
            	"Range",
            	dataset,         // data
            	true,            // include legend
            	true,            // tooltips
            	true             // urls
        	);
    	}

    /**
     * A chart change listener.
     *
     */
    static class LocalListener implements ChartChangeListener {

        /** A flag. */
        private boolean flag = false;

        /**
         * Event handler.
         *
         * @param event  the event.
         */
        
        public void chartChanged(ChartChangeEvent event) {
            this.flag = true;
        }

    }
}

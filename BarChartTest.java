package test;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.ChartChangeEvent;
import org.jfree.chart.event.ChartChangeListener;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.Range;

/**
 * Tests for a bar chart.
 */
public class BarChartTest {
    private JFreeChart chart;

    private static JFreeChart createBarChart() {

        // create a dataset...
        Number[][] data = new Integer[][]
            {{new Integer(-3), new Integer(-2)},
             {new Integer(-1), new Integer(1)},
             {new Integer(2), new Integer(3)}};

        CategoryDataset dataset = DatasetUtils.createCategoryDataset("S",
                "C", data);

        // create the chart...
        return ChartFactory.createBarChart(
            "Bar Chart",
            "Domain", "Range",
            dataset,
            PlotOrientation.HORIZONTAL,
            true,     // include legend
            true,
            true
        );

    }
            
    static class LocalListener implements ChartChangeListener {

        /** A flag. */
        private boolean flag = false;

        /**
         * Event handler.
         *
         * @param event  the event.
         */
        @Override
        public void chartChanged(ChartChangeEvent event) {
            this.flag = true;
        }

    }

    public static void main(String[] args) {
        JFreeChart chart = createBarChart();
        
        // testReplaceDataset
	// create a dataset...
        Number[][] data = new Integer[][]
            {{new Integer(-30), new Integer(-20)},
             {new Integer(-10), new Integer(10)},
             {new Integer(20), new Integer(30)}};

        CategoryDataset newData = DatasetUtils.createCategoryDataset("S",
                "C", data);

        LocalListener l = new LocalListener();
        chart.addChangeListener(l);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
	plot.setDataset(newData);
	assert true == l.flag;
	ValueAxis axis = plot.getRangeAxis();
	Range range = axis.getRange();
	assert range.getLowerBound() <= -30;
	assert range.getUpperBound() >= 30;
    }
}

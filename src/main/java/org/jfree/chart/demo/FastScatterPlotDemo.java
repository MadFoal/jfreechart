package org.jfree.chart.demo;

import org.jfree.chart.legend.LegendTitle;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.FastScatterPlot;
import org.jfree.chart.swing.ApplicationFrame;
import org.jfree.chart.swing.UIUtils;

/** A demo of the fast scatter plot.
 *
 * @author David Gilbert
 */
public class FastScatterPlotDemo extends ApplicationFrame {

 /** A constant for the number of items in the sample dataset. */
    private static final int COUNT = 500;
    /** The data. */
    private float[][] data = new float[2][COUNT];
   /**
    * Creates a new fast scatter plot demo.
    *
    * @param title the frame title.
    */
    public FastScatterPlotDemo(String title) {
         super(title);
         populateData();
         NumberAxis domainAxis = new NumberAxis("X");
         domainAxis.setAutoRangeIncludesZero(false);
         NumberAxis rangeAxis = new NumberAxis("Y");
         rangeAxis.setAutoRangeIncludesZero(false);
         FastScatterPlot plot = new FastScatterPlot(data, domainAxis, rangeAxis);

         JFreeChart chart = new JFreeChart("Fast Scatter Plot", plot);
         chart.addLegend(chart.getLegend());
         chart.setAntiAlias(false);
         chart.setBorderVisible(false);
         ChartPanel panel = new ChartPanel(chart, true);
         panel.setPreferredSize(new java.awt.Dimension(500, 270));
         panel.setDomainZoomable(true);
         //panel.setMinimumDrawHeight(10);
         //panel.setMaximumDrawHeight(2000);
        // panel.setMinimumDrawWidth(20);
         //panel.setMaximumDrawWidth(2000);
         setContentPane(panel);
    }

    // ****************************************************************************
    // * COMMERCIAL SUPPORT / JfreeCHART DEVELOPER GUIDE *
    // * Please note that commercial support and documentation is available from: *
    // * *
    // * http://www.object-refinery.com/jfreechart/support.html *
    // * *
    // * This is not only a great service for developers, but is a VERY IMPORTANT *
    // * source of funding for the JFreeChart project. Please support us so that *
    // * we can continue developing free software. *
    // ****************************************************************************

    /**
     * Populates the data array with random values.
      */
    private void populateData() {
        for (int i = 0; i < data[0].length; i++) {
            //float x = (float) i + 100;
            float  x = (float) Math.random();
            data[0][i] = x;
           // data[1][i] = 100 + (float) Math.random() * COUNT;
            data[1][i] =  (float) Math.random();
        }
    }

    /**
       * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        FastScatterPlotDemo demo = new FastScatterPlotDemo("Fast Scatter Plot Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
      }
}

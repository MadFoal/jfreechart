package org.jfree.chart.demo;

import org.jfree.chart.swing.UIUtils;


public class TestPolygonPlotCase {
    /**
     * Starting point for the demonstration application.
     *
     * @param args  ignored.
     */
    public static void main(String[] args) {
        PolygonPlotCase demo = new PolygonPlotCase("PolygonPlot Demo");
        demo.pack();
        UIUtils.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}

/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2020, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.]
 *
 * -----------------
 * DateAxisTest.java
 * -----------------
 * (C) Copyright 2003-2020, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 */

package org.jfree.chart.axis;

import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.TimeZone;

import org.jfree.chart.api.RectangleEdge;

import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;

/**
 * Tests for the {@link DateAxis} class.
 */
public class DateDaylightSavingTest {

    static class MyDateAxis extends DateAxis {
        private static final long serialVersionUID = 1L;
        public MyDateAxis(String label) {
            super(label);
        }

        @Override
        public Date previousStandardDate(Date d, DateTickUnit unit) {
            return super.previousStandardDate(d, unit);
        }
    }

    @Test
    public void TimeZone_Demo() {

        // Creating a time zone object
        TimeZone timeobj
                = TimeZone
                .getTimeZone("Pacific/Pago_Pago");

        // Displaying the time zone

        System.out.println(timeobj);
        // Verifying daylight
        boolean bool_daylight
                = timeobj.observesDaylightTime();

        // Checking the value of day light
        System.out.println("The result is: "
                + bool_daylight);

    }

    @Test
    public void testIssue206() {
        Day startD1 = new Day(7, 11, 2021);
    }


//refreshTicksHorizontal
    @Test
    public void testPreviousStandardDateHourA() {
        System.out.println("**testPreviousStandardDateHourA");
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        Hour h0 = new Hour(3,2, 11, 2021);
        Hour h1 = new Hour(1, 7, 11, 2021);
        Hour h2 = new Hour(2, 7, 11, 2021);
        Hour h3 = new Hour(3, 7, 11, 2021);
        Hour h4 = new Hour(4, 7, 11, 2021);
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);

        // START: check d0 and d1
        axis.setTickMarkPosition(DateTickMarkPosition.START);
        System.out.println("***h0***");
        printInfo(h0);
        System.out.println("***h1***");
        printInfo(h1);
        System.out.println("***h2***");
        printInfo(h2);
        System.out.println("***h3***");
        printInfo(h3);
        System.out.println("***h4***");
        printInfo(h4);
    }

    private void printInfo(Hour h1){
      //  System.out.println("***printInfo***");
        Date d1 = new Date(h1.getFirstMillisecond());
        System.out.println("***entered date: " + d1);
        Date end = new Date(h1.getLastMillisecond());
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setRange(d1, end);

        axis.setTickUnit(unit);
        Date psd = axis.previousStandardDate(d1, unit);
        Date nextsd = axis.nextStandardDate(d1, unit);
     //   Date nsd = unit.addToDate(psd, TimeZone.getDefault());
        System.out.println("*****prev: " + psd);
        System.out.println("***** next: " + nextsd);
    }

    //runs both  protected AxisState drawTickMarksAndLabels ublic void testDrawWithNullInfo2() {
    @Test
    public void standardDateTest() {
        DateAxis axis = new DateAxis("Test Axis");

        Hour h0 = new Hour(0,7, 11, 2021);
        Hour h1 = new Hour(1, 7, 11, 2021);
        Hour h2 = new Hour(2, 7, 11, 2021);
        Hour h3 = new Hour(3, 7, 11, 2021);
        Hour h4 = new Hour(4, 7, 11, 2021);
        Date d0 = new Date(h0.getFirstMillisecond());
        Date d1 = new Date(h1.getFirstMillisecond());
        Date d2 = new Date(h2.getFirstMillisecond());
        Date d3 = new Date(h3.getFirstMillisecond());
        Date d4 = new Date(h4.getFirstMillisecond());

        DateTickUnit t1 = new DateTickUnit(DateTickUnitType.DAY, 1);
        Date result = axis.nextStandardDate(d2, t1);
       System.out.println("**result: " + result);
    }

    @Test
    public void del(){
        System.out.println("*****prev: hi");
        TimeZone zone = TimeZone.getTimeZone("America/New_York");
        DateFormat format = DateFormat.getDateTimeInstance();
        format.setTimeZone(zone);

        Hour h0 = new Hour(0,7, 11, 2021);
        Hour h1 = new Hour(1, 7, 11, 2021);
        Hour h2 = new Hour(2, 7, 11, 2021);
        Hour h3 = new Hour(3, 7, 11, 2021);
        Hour h4 = new Hour(4, 7, 11, 2021);
        Date d0 = new Date(h0.getFirstMillisecond());
        Date d1 = new Date(h1.getFirstMillisecond());
        Date d2 = new Date(h2.getFirstMillisecond());
        Date d3 = new Date(h3.getFirstMillisecond());
        Date d4 = new Date(h4.getFirstMillisecond());
        String x = format.format(d0);
        System.out.println(format.format(new Date()));
        System.out.println("*****d0");
        System.out.println(format.format(d0));
        System.out.println("*****d1");
        System.out.println(format.format(d1));
        System.out.println("*****d2");
        System.out.println(format.format(d2));
        System.out.println("*****d3");
        System.out.println(format.format(d3));
        System.out.println("*****d1");
        System.out.println(format.format(d4));
    }

    /**
     * A test to reproduce bug 2201869.
     */
    @Test
    public void testBug2201869() {
        // uses next standard date
        GregorianCalendar c = new GregorianCalendar();
        DateAxis axis = new DateAxis();
       // SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy", Locale.UK);
        //sdf.setCalendar(c);
       // axis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1, sdf));
        Hour h2 = new Hour(2, 7, 11, 2021);
        Hour h3 = new Hour(3, 7, 11, 2021);
        //Date d0 = new Date(h2.getFirstMillisecond());
      //  Date d1 = new Date(h3.getFirstMillisecond());
        Day d1 = new Day(7, 11, 2021);
        d1.peg(c);
        Day d2 = new Day(7, 11, 2021);
        d2.peg(c);

        axis.setRange(d1.getStart(), d2.getEnd());
        BufferedImage image = new BufferedImage(200, 100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle2D.Double(0.0, 0.0, 200, 100);
        axis.setTickMarkPosition(DateTickMarkPosition.END);
        List<? extends Tick> ticks = axis.refreshTicks(g2, new AxisState(), area,
                RectangleEdge.BOTTOM);
        System.out.println("**result ticks: " + ticks);
    }


}

/*


    protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {

        List result = new java.util.ArrayList();

        Font tickLabelFont = getTickLabelFont();
        g2.setFont(tickLabelFont);

        if (isAutoTickUnitSelection()) {
            selectAutoTickUnit(g2, dataArea, edge);
        }

        DateTickUnit unit = getTickUnit();
        Date tickDate = calculateLowestVisibleTickValue(unit);
        Date upperDate = getMaximumDate();
    }

 */

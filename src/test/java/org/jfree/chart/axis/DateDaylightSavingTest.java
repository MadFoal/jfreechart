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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.jfree.chart.TestUtils;
import org.jfree.chart.api.RectangleEdge;

import org.jfree.data.time.DateRange;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.Second;
import org.jfree.data.time.Year;

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
    public void testPreviousStandardDateMonthB() {
        MyDateAxis axis = new MyDateAxis("Month");
        Month nov2006 = new Month(11, 2006);
        Month dec2006 = new Month(12, 2006);

        // five dates to check...
        Date d0 = new Date(nov2006.getFirstMillisecond());
        Date d1 = new Date(nov2006.getFirstMillisecond() + 500L);
        Date d2 = new Date(nov2006.getMiddleMillisecond());
        Date d3 = new Date(nov2006.getMiddleMillisecond() + 500L);
        Date d4 = new Date(nov2006.getLastMillisecond());

        Date end = new Date(dec2006.getLastMillisecond());

        DateTickUnit unit = new DateTickUnit(DateTickUnitType.MONTH, 3);
        axis.setTickUnit(unit);

        // START: check d0 and d1
        axis.setTickMarkPosition(DateTickMarkPosition.START);

        axis.setRange(d0, end);
        Date psd = axis.previousStandardDate(d0, unit);
        Date nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d0.getTime());
        assertTrue(nsd.getTime() >= d0.getTime());

        axis.setRange(d1, end);
        psd = axis.previousStandardDate(d1, unit);
        nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d1.getTime());
        assertTrue(nsd.getTime() >= d1.getTime());

        // MIDDLE: check d1, d2 and d3
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);

        axis.setRange(d1, end);
        psd = axis.previousStandardDate(d1, unit);
        nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d1.getTime());
        assertTrue(nsd.getTime() >= d1.getTime());

        axis.setRange(d2, end);
        psd = axis.previousStandardDate(d2, unit);
        nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d2.getTime());
        assertTrue(nsd.getTime() >= d2.getTime());

        axis.setRange(d3, end);
        psd = axis.previousStandardDate(d3, unit);
        nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d3.getTime());
        assertTrue(nsd.getTime() >= d3.getTime());

        // END: check d3 and d4
        axis.setTickMarkPosition(DateTickMarkPosition.END);

        axis.setRange(d3, end);
        psd = axis.previousStandardDate(d3, unit);
        nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d3.getTime());
        assertTrue(nsd.getTime() >= d3.getTime());

        axis.setRange(d4, end);
        psd = axis.previousStandardDate(d4, unit);
        nsd = unit.addToDate(psd, TimeZone.getDefault());
        assertTrue(psd.getTime() < d4.getTime());
        assertTrue(nsd.getTime() >= d4.getTime());
    }

    /**
     * A test to reproduce bug 2201869.
     */
    @Test
    public void testBug2201869() {
        TimeZone tz = TimeZone.getTimeZone("GMT");
        GregorianCalendar c = new GregorianCalendar(tz, Locale.UK);
        DateAxis axis = new DateAxis("Date", tz, Locale.UK);
        SimpleDateFormat sdf = new SimpleDateFormat("d-MMM-yyyy", Locale.UK);
        sdf.setCalendar(c);
        axis.setTickUnit(new DateTickUnit(DateTickUnitType.MONTH, 1, sdf));
        Day d1 = new Day(1, 3, 2008);
        d1.peg(c);
        Day d2 = new Day(30, 6, 2008);
        d2.peg(c);
        axis.setRange(d1.getStart(), d2.getEnd());
        BufferedImage image = new BufferedImage(200, 100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle2D.Double(0.0, 0.0, 200, 100);
        axis.setTickMarkPosition(DateTickMarkPosition.END);
        List<? extends Tick> ticks = axis.refreshTicks(g2, new AxisState(), area,
                RectangleEdge.BOTTOM);
        assertEquals(3, ticks.size());
        DateTick t1 = (DateTick) ticks.get(0);
        assertEquals("31-Mar-2008", t1.getText());
        DateTick t2 = (DateTick) ticks.get(1);
        assertEquals("30-Apr-2008", t2.getText());
        DateTick t3 = (DateTick) ticks.get(2);
        assertEquals("31-May-2008", t3.getText());

        // now repeat for a vertical axis
        ticks = axis.refreshTicks(g2, new AxisState(), area,
                RectangleEdge.LEFT);
        assertEquals(3, ticks.size());
        t1 = (DateTick) ticks.get(0);
        assertEquals("31-Mar-2008", t1.getText());
        t2 = (DateTick) ticks.get(1);
        assertEquals("30-Apr-2008", t2.getText());
        t3 = (DateTick) ticks.get(2);
        assertEquals("31-May-2008", t3.getText());
    }

    /**
     * A test for bug 3484403 (SourceForge
     * https://sourceforge.net/p/jfreechart/bugs/1078/).
     */
    @Test
    public void testBug3484403() {

        final long[] dates =
                { 1304892000000L, 1304632800000L, 1304546400000L, 1304460000000L,
                        1304373600000L, 1304287200000L, 1320015600000L, 1309384800000L,
                        1319752800000L, 1319666400000L, 1319580000000L, 1319493600000L };
        Arrays.sort(dates);

        DateAxis axis = new DateAxis("Date");
        // set start and end date
        Date start = new Date(dates[0]);
        Date end = new Date(dates[dates.length-1]);
        axis.setMinimumDate(start);
        axis.setMaximumDate(end);

        BufferedImage image = new BufferedImage(200, 100,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = image.createGraphics();
        Rectangle2D area = new Rectangle2D.Double(0.0, 0.0, 500, 200);

        // if the bug is still present, this leads to an endless loop
        axis.refreshTicks(g2, new AxisState(), area, RectangleEdge.BOTTOM);
    }
}

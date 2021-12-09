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

/**
 * Issue 206 https://github.com/jfree/jfreechart/issues/206
 *
 * Details: Test class is a work in progress
 * Given: Dates and times around daylight saving time change
 * Result: Returns incorrect time regardless of timezone label
 *
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
    public boolean TimeZone_Demo() {
        TimeZone timeobj = TimeZone.getTimeZone("Pacific/Pago_Pago");

        return timeobj.observesDaylightTime();
    }

    @Test
    public void testPreviousAndNext() {
        //System.out.println("**testPreviousStandardDateHourA");
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
        //System.out.println("***h0***");
        printInfo(h0);
        //System.out.println("***h1***");
        printInfo(h1);
        //System.out.println("***h2***");
        printInfo(h2);
        //System.out.println("***h3***");
        printInfo(h3);
        //System.out.println("***h4***");
        printInfo(h4);
        assert (h1.getHour() != h2.getHour());
    }

    private Date printInfo(Hour h1){
        //System.out.println("***printInfo***");
        Date d1 = new Date(h1.getFirstMillisecond());
        Date end = new Date(h1.getLastMillisecond());
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setRange(d1, end);

        axis.setTickUnit(unit);
   //     Date psd = axis.previousStandardDate(d1, unit); Date nextsd = axis.nextStandardDate(d1, unit);
        return axis.nextStandardDate(d1, unit);
    }
}

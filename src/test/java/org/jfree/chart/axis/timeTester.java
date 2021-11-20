package org.jfree.chart.axis;

import org.jfree.data.time.Hour;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TimeZone;

public class timeTester {

    @Test
    public void testPreviousStandardDateHourA() {
        //System.out.println("**testPreviousStandardDateHourA");
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        Hour h0 = new Hour(3,2, 11, 2021);
        Hour h1 = new Hour(1, 7, 11, 2021);
        Hour h2 = new Hour(2, 7, 11, 2021);
        Hour h3 = new Hour(3, 7, 11, 2021);
        Hour h4 = new Hour(4, 7, 11, 2021);
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        TimeZone z =

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
    }

    private void printInfo(Hour h1){
        //  //System.out.println("***printInfo***");
        Date d1 = new Date(h1.getFirstMillisecond());
        //System.out.println("***entered date: " + d1);
        Date end = new Date(h1.getLastMillisecond());
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setRange(d1, end);

        axis.setTickUnit(unit);
        Date psd = axis.previousStandardDate(d1, unit);
        Date nextsd = axis.nextStandardDate(d1, unit);
        //   Date nsd = unit.addToDate(psd, TimeZone.getDefault());
        //System.out.println("*****prev: " + psd);
        //System.out.println("***** next: " + nextsd);
    }
}

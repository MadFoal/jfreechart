package org.jfree.chart.axis;

import org.jfree.data.time.Hour;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class timeTester {

    /**
     * Check that setting a tool tip generator for a series does override the
     * default generator.
     */
    @Test
    public void giveDaylightSavingDateAt1AM2020_expectedCorrectTime1HourBefore() {
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        Hour dstSwitch2020 = new Hour(3,12, 12, 2020);
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setTickMarkPosition(DateTickMarkPosition.START);
        long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2020);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Check that setting a tool tip generator for a series does override the
     * default generator.
     */
    @Test
    public void giveDaylightSavingDateAt1AM2021_expectedCorrectTime1HourBefore() {
        Hour dstSwitch2021 = new Hour(1, 7, 11, 2021);
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setTickMarkPosition(DateTickMarkPosition.START);
        long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2021);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Check that setting a tool tip generator for a series does override the
     * default generator.
     */
    @Test
    public void giveStandardDateAt1Am_expectedCorrectTime1HourBefore() {
        Hour dstSwitch2021 = new Hour(1, 7, 1, 2020);
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        // START: check d0 and d1

        axis.setTickMarkPosition(DateTickMarkPosition.START);
        getOneHourDifferenceInMilliseconds(dstSwitch2020);
        assertTrue(getOneHourDifferenceInMilliseconds(dstSwitch2020) == 3600000);
        //  assertTrue(tt2 == tt);
    }

    private long getOneHourDifferenceInMilliseconds(Hour hour){
        System.out.println("***printInfo*** h entered is: " + hour);
        Date testDate = new Date(hour.getFirstMillisecond());
        Date end = new Date(hour.getLastMillisecond());
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setRange(testDate, end);

        System.out.println("\n**run previous");
        Date previous = axis.previousStandardDate(testDate, unit);
        System.out.println("**Prev date returned: " + (previous.getTime() - testDate.getTime()));
        System.out.println("--------------------\n\n");
        return testDate.getTime() - previous.getTime();
    }
}

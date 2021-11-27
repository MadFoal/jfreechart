package org.jfree.chart.axis;

import org.jfree.data.time.Hour;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class timeTester {

    /**
     * Correct time is returned regardless of daylight saving time changes
     */
    @Test
    public void giveDaylightSavingSwitchDateAt1AM2020_expectedCorrectTime1HourBefore() {
        Hour dstSwitch2020 = new Hour(1,1, 11, 2020);
        long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2020);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Correct time is returned regardless of daylight saving time changes
     */
    @Test
    public void giveDaylightSavingSwitchDateAt1AM2021_expectedCorrectTime1HourBefore() {
        Hour dstSwitch2021 = new Hour(1, 7, 11, 2021);
        long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2021);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Correct time is returned regardless of daylight saving time changes
     */
    @Test
    public void giveStandardDateAt1Am_expectedCorrectTime1HourBefore() {
        Hour standardTime = new Hour(1, 7, 12, 2018);
        long timeDifference = getOneHourDifferenceInMilliseconds(standardTime);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Correct time is returned regardless of daylight saving time changes
     */
    @Test
    public void giveDaylightSavingDateAt1AM_expectedCorrectTime1HourBefore() {
        Hour daylightDate = new Hour(1, 18, 6, 2019);
        long timeDifference = getOneHourDifferenceInMilliseconds(daylightDate);

        assertTrue(timeDifference == 3600000);
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

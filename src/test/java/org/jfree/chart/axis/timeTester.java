package org.jfree.chart.axis;

import org.jfree.data.time.Hour;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class timeTester {

    /**
     * Issue 206 https://github.com/jfree/jfreechart/issues/206
     * Details: Correct time is returned regardless of daylight saving time changes
     *
     * Given: Date data that includes the date of a switch from Daylight Saving time to standard
     * Expected: Correct timezone abbreviation and hour during the change
     * Result: Success, the expected data is returned
     *
     */
    @Test
    public void giveDaylightSavingSwitchDateAt1AM2020_expectedCorrectTime1HourBefore() {
        Hour dstSwitch2020 = new Hour(1,1, 11, 2020);
        long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2020);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Issue 206 https://github.com/jfree/jfreechart/issues/206
     * Details: Correct time is returned regardless of daylight saving time changes
     *
     * Given: Date data that includes the date of a switch from Daylight Saving time to standard
     * Expected: Correct timezone abbreviation and hour during the change
     * Result: Success, the expected data is returned
     *
     */
    @Test
    public void giveDaylightSavingSwitchDateAt1AM2021_expectedCorrectTime1HourBefore() {
        Hour dstSwitch2021 = new Hour(1, 7, 11, 2021);
        long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2021);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Issue 206 https://github.com/jfree/jfreechart/issues/206
     * Details: Correct time is returned regardless of daylight saving time changes
     *
     * Given: Date data that includes the date of a switch from Daylight Saving time to standard
     * Expected: Correct timezone abbreviation and hour during the change
     * Result: Success, the expected data is returned
     *
     */
    @Test
    public void giveStandardDateAt1Am_expectedCorrectTime1HourBefore() {
        Hour standardTime = new Hour(1, 7, 12, 2018);
        long timeDifference = getOneHourDifferenceInMilliseconds(standardTime);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Issue 206 https://github.com/jfree/jfreechart/issues/206
     * Details: Correct time is returned regardless of daylight saving time changes
     *
     * Given: Date data that includes the date of a switch from Daylight Saving time to standard
     * Expected: Correct timezone abbreviation and hour during the change
     * Result: Success, the expected data is returned
     *
     */
    @Test
    public void giveDaylightSavingDateAt1AM_expectedCorrectTime1HourBefore() {
        Hour daylightDate = new Hour(1, 18, 6, 2019);
        long timeDifference = getOneHourDifferenceInMilliseconds(daylightDate);

        assertTrue(timeDifference == 3600000);
    }

    /**
     * Given: Various dates return output of previousStandardDate method causing the error
     */
    private long getOneHourDifferenceInMilliseconds(Hour hour){
        Date testDate = new Date(hour.getFirstMillisecond());
        Date end = new Date(hour.getLastMillisecond());
        DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setRange(testDate, end);

        Date previous = axis.previousStandardDate(testDate, unit);
        return testDate.getTime() - previous.getTime();
    }
}

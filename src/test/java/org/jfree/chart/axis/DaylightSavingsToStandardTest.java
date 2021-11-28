package org.jfree.chart.axis;

import org.jfree.data.time.Hour;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DaylightSavingsToStandardTest {

    public DaylightSavingsToStandardTest() {}

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
    public void giveDaylightSavingSwitchDateAt1AM2020ExpectedCorrectTime1HourBefore() {
        final Hour dstSwitch2020 = new Hour(1,1, 11, 2020);
        final long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2020);

        assertEquals(3_600_000, timeDifference);
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
    public void giveDaylightSavingSwitchDateAt1AM2021ExpectedCorrectTime1HourBefore() {
        final Hour dstSwitch2021 = new Hour(1, 7, 11, 2021);
        final long timeDifference = getOneHourDifferenceInMilliseconds(dstSwitch2021);

        assertEquals(3_600_000, timeDifference);
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
    public void giveStandardDateAt1AmExpectedCorrectTime1HourBefore() {
        final Hour standardTime = new Hour(1, 7, 12, 2018);
        final long timeDifference = getOneHourDifferenceInMilliseconds(standardTime);

        assertEquals(3_600_000, timeDifference);
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
    public void giveDaylightSavingDateAt1AMExpectedCorrectTime1HourBefore() {
        final Hour daylightDate = new Hour(1, 18, 6, 2019);
        final long timeDifference = getOneHourDifferenceInMilliseconds(daylightDate);

        assertEquals(3_600_000, timeDifference);
    }

    /**
     * Given: Various dates return output of previousStandardDate method causing the error
     */
    private long getOneHourDifferenceInMilliseconds(Hour hour){
        final Date testDate = new Date(hour.getFirstMillisecond());
        final Date end = new Date(hour.getLastMillisecond());
        final DateAxisTest.MyDateAxis axis = new DateAxisTest.MyDateAxis("Hour");
        final DateTickUnit unit = new DateTickUnit(DateTickUnitType.HOUR, 1);
        axis.setTickUnit(unit);
        axis.setRange(testDate, end);

        final Date previous = axis.previousStandardDate(testDate, unit);
        final long previousTime = previous.getTime();
        final long testDateTime = testDate.getTime();
        final long timeDifferent = testDateTime - previousTime;
        return timeDifferent;
    }
}

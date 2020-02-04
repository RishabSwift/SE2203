package Question2;

import Question1.ConsoleDisplayInterface;
import Question1.Rollable;

public class ConsoleDisplay<T> implements ConsoleDisplayInterface<Integer> {

    // Three wheels
    private Wheel hours, minutes, seconds;

    /**
     * Constructor with only one wheel
     *
     * @param seconds
     */
    public ConsoleDisplay(Wheel seconds) {
        this.seconds = seconds;
    }

    /**
     * Overloading a constructor with 2 parameters
     *
     * @param seconds
     * @param minutes
     */
    public ConsoleDisplay(Wheel seconds, Wheel minutes) {
        this.seconds = seconds;
        this.minutes = minutes;
    }

    /**
     * Overloading a constructor with 3 parameters
     *
     * @param hours
     * @param minutes
     * @param seconds
     */
    public ConsoleDisplay(Wheel hours, Wheel minutes, Wheel seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    /**
     * Reset method that resets all the wheels.
     */
    @Override
    public void reset() {
        hours.reset();
        minutes.reset();
        seconds.reset();
    }

    /**
     * Increase the counter by ensuring it's not rolled over and such.
     */
    @Override
    public void increase() {

        // increase the seconds... the default wheel by 1
        seconds.increase();

        // if the seconds is rolled over (meaning it reaches 60)
        // then reset it and increase the minutes by 1 and keep the count going
        if (seconds.lastRolledOver()) {
            seconds.reset();
            minutes.increase();
        }

        // if the minutes is rolled over (meaning it reaches 60)
        // then reset it and increase the hour by 1 and keep the count going
        if (minutes.lastRolledOver()) {
            minutes.reset();
            hours.increase();
        }

        // if the hours is rolled over (meaning it reaches 24)
        // then reset it all to 0
        if (hours.lastRolledOver()) {
            hours.reset();
            minutes.reset();
            seconds.reset();
        }


    }

    /**
     * Get the wheel value
     * @param theWheel
     * @return wheel value
     */
    @Override
    public Integer getWheelValue(int theWheel) {

        // If it's the first one then it's the seconds
        if (theWheel == 1) {
            return seconds.getValue();
        }

        // second one = minutes
        if (theWheel == 2) {
            return minutes.getValue();
        }

        // or else if it's 3, then it's hours
        return hours.getValue();
    }

    /**
     * Check how many wheels are being used
     * @return
     */
    @Override
    public int getWheelsInUse() {

        // set this to 0 and add 1 to it each time a wheel is being used
        int total = 0;
        if (hours != null) {
            total++;
        }

        if (minutes != null) {
            total++;
        }

        if (seconds != null) {
            total++;
        }

        return total;
    }
}

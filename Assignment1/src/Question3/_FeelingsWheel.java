package Question3;

import Question1.Rollable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class _FeelingsWheel implements Rollable<String> {

    // hold the feelings information here to start
    private List<String> rawFeelings = new ArrayList<String>();
    private String[] feelings;
    private int[] wheelSize;
    private int counter = 0;
    private boolean timeToFlip = false;

    public _FeelingsWheel(String filename) {
        readTextFile(filename);
        getFeelingNames();
        getWheelSize();

//        System.out.println(rawFeelings.toString());
    }

    /**
     * Read the text file
     *
     * @param filename
     */
    public void readTextFile(String filename) {
        File file = new File("./" + filename);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null && !st.equals("")) {
                rawFeelings.add(st);
            }
        } catch (Exception ex) {
            // caught an error... well shit
        }
    }

    /**
     * Get the wheel size
     * if we hit a 1, then we have rolled over...
     * so we need to count how many lines we skipped until we hit 1 again
     * e.g.
     * --------
     * romantic 1
     * fond 0
     * sentimental 1
     * --------
     * This would be size 2 since we hit 1 the third time, resetting it
     *
     * @return the wheel size
     */
    private int getWheelSize() {

        int size = 0;
        for (int i = 0; i < rawFeelings.size(); i++) {
            int num = Integer.parseInt(rawFeelings.get(i).split(" ")[1]);
            // the first one is always 1, if it's not it means it's an inner wheel
            // check if it's 1, if it is then we have hit the end of wheel
            if (num == 1 && i != 0) {
                size = i;
                break;
            }
        }

        return size;
    }

    private void getFeelingNames() {
        // initiate the feelings with the size of the array since we now know that
        feelings = new String[rawFeelings.size()];
        wheelSize = new int[rawFeelings.size()];

        // loop through the array list
        for (int i = 0; i < rawFeelings.size(); i++) {
            // split line by line and get the first index (the word)
            try {
                String line = rawFeelings.get(i);
                feelings[i] = line.split(" ")[0];
                wheelSize[i] = Integer.parseInt(line.split(" ")[1]);
            } catch (Exception e) {
                System.out.println(feelings[i]);
            }
        }
    }


    @Override
    public void reset() {
//        System.out.println("23" wheelSize.length);
//        if (counter == rawFeelings.size()) {
//            counter = 0;
//        }
//        if (counter == getWheelSize()) {
//            flipped = !flipped;
//        }

        // if it reaches the end, reset the counter
        if (counter == rawFeelings.size()) {
            counter = 0;
        }

//        System.out.println(counter);
//        System.out.println(wheelSize[counter]);

//        if (flipped) {
////            flipped = false;
////        }

        if (counter == getWheelSize()) {
            timeToFlip = true;
        }

        if (wheelSize[counter] == 1) {
            timeToFlip = true;
        } else {
            timeToFlip = false;
        }
    }

    @Override
    public void increase() {
        counter++;
    }


    @Override
    public boolean lastRolledOver() {
        if (!timeToFlip) {
            reset();
        }
        return timeToFlip;
//        return (counter == getWheelSize());
    }

    @Override
    public String getValue() {
        return String.valueOf(counter);
//        return feelings[counter];

    }

    public String toString() {
        return rawFeelings.get(0);
    }
}
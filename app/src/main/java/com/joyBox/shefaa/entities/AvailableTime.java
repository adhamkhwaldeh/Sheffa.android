package com.joyBox.shefaa.entities;

import java.util.List;
import java.util.Vector;

public class AvailableTime {
    private List<String> AvailableTimes;

    public List<String> getAvailableTimes() {
        if (AvailableTimes == null)
            AvailableTimes = new Vector<>();
        List<String> stringList = new Vector<>();
        for (String s : AvailableTimes) {
            if (!s.equals("booked"))
                stringList.add(s);
        }
        AvailableTimes = stringList;
        return AvailableTimes;
    }

    public void setAvailableTimes(List<String> availableTimes) {
        AvailableTimes = availableTimes;
    }
}

package com.joyBox.shefaa.helpers;


import com.JoyBox.Shefaa.R;


/**
 * Created by Adhamkh on 2017-07-27.
 */

public class ClinicHourHelper {

    public static int getDay(String day) {
        switch (day) {
            case "0":
                return R.string.Sunday;

            case "1":
                return R.string.Monday;

            case "2":
                return R.string.Tuesday;

            case "3":
                return R.string.Wednsday;

            case "4":
                return R.string.Thursday;

            case "5":
                return R.string.Friday;

            case "6":
                return R.string.Saturday;
        }
        return R.string.Sunday;
    }

    public static String getTime(String tm) {
        try {
            if (tm.length() == 2) {
                tm = "00" + tm;
            }
            if (tm.length() == 3) {
                tm = "0" + tm;
            }
            if (tm.length() < 4)
                return tm;
            return tm.substring(0, 2) + ":" + tm.substring(2, 4);
        } catch (Exception ex) {

        }
        return tm;
//        Long time = Long.valueOf(tm);
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(time * 60 * 1000);
//        String date = c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE);
//        return date;
//        Long time = Long.valueOf(tm);
//        Long hr = time / 60;
//        Long minute = time % 60;
//        return hr.toString() + ":" + minute.toString();
    }

}

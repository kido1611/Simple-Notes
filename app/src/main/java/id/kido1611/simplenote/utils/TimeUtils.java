package id.kido1611.simplenote.utils;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ahmad on 21/08/2016.
 */
public class TimeUtils {
    public static long getUnix(){
        return new Date().getTime();
    }

    public static String unixToTimeAgo(long unix){
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                unix,
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);

        return timeAgo.toString();
    }

    public static String longToTime(long time){
        return new SimpleDateFormat("HH:mm:ss\ndd MMM yyyy").format(new Date(time));
    }
}

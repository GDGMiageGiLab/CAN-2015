package com.gdg.miagegi.can2015.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.gdg.miagegi.can2015.MainApplication;
import android.app.NotificationManager;
import android.content.Context;

public class Constants {
    private static final String _BASE_API_DEV = "http://192.168.1.3:9000/api/1";
    private static final String _BASE_API_PROD = "http:///api/1";

    private static final boolean _PROD_MODE = false;

    public static final DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(MainApplication.getContext());

    public static final String EMPTY_STRING = "";
    public static final String ENCLOSURE_SEPARATOR = "[@]"; // exactly three
                                                            // characters!

    public static final String FALSE = "false";

    public static final String FIELD_ARTICLE_LINK = "link";



    public static final String FILE_URL = "file://";

    public static final String FROM_AUTO_REFRESH = "from_auto_refresh";

    public static final String HTTP = "http://";

    public static final String HTTPS = "https://";
    public static final String INTENT_FROM_WIDGET = "fromWidget";

    private static SimpleDateFormat mDateFormat;

    public static final String MIMETYPE_TEXT_PLAIN = "text/plain";
    public static NotificationManager NOTIF_MGR = (NotificationManager) MainApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);

    public static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static final String PREFS_APP_VERSION = "appVersion";
    public static final String PREFS_IMEI = "imei";

    public static final int PREFS_MODE = Context.MODE_PRIVATE;

    public static final String PREFS_NAME = "can";


    public static final String PREFS_REGISTRATION_ID = "registrationId";
   // public static final String SENDER_ID = "783221521699";
    public static final DateFormat TIME_FORMAT = android.text.format.DateFormat.getTimeFormat(MainApplication.getContext());
    public static final String TRUE = "true";
    public static final int UPDATE_THROTTLE_DELAY = 1000;
    public static final String UTF8 = "UTF-8";

    public static String getApiBase() {
        return (_PROD_MODE ? _BASE_API_PROD : _BASE_API_DEV);
    }

    public static SimpleDateFormat getDateFormat() {
        if (mDateFormat == null) {
            mDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        }
        return mDateFormat;
    }

    public static boolean isProd() {
        return _PROD_MODE;
    }

}
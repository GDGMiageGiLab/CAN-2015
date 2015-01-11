package com.gdg.miagegi.can2015.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gdg.miagegi.can2015.MainApplication;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.DisplayMetrics;

public class Utils {
	 public static final String DISABLE_PICTURES = "pictures.disable";

	    public static final String FETCH_PICTURES = "pictures.fetch";

	    public static final String FIRST_OPEN = "FIRST_OPEN";

	    public static final String FONT_SIZE = "fontsize";

	    public static final String IS_REFRESHING = "IS_REFRESHING";

	    public static final String KEEP_TIME = "keeptime";

	    public static final String LAST_SCHEDULED_REFRESH = "lastscheduledrefresh";

	    public static final String LIGHT_THEME = "lighttheme";

	    public static final String NOTIFICATIONS_ENABLED = "notifications.enabled";

	    public static final String NOTIFICATIONS_RINGTONE = "notifications.ringtone";

	    public static final String NOTIFICATIONS_VIBRATE = "notifications.vibrate";

	    public static final String REFRESH_ENABLED = "refresh.enabled";

	    public static final String REFRESH_INTERVAL = "refresh.interval";

	    public static final String REFRESH_ON_OPEN_ENABLED = "refreshonopen.enabled";

	    public static final String REFRESH_WIFI_ONLY = "refreshwifionly.enabled";
	    public static final String SHOW_READ = "show_read";

	    /**
	     * This method converts dp unit to equivalent pixels, depending on device
	     * density.
	     * 
	     * @param dp A value in dp (density independent pixels) unit. Which we need
	     *            to convert into pixels
	     * @param context Context to get resources and device specific display
	     *            metrics
	     * @return A float value to represent px equivalent to dp depending on
	     *         device density
	     */
	    public static float convertDpToPixel(float dp, Context context) {
	        Resources resources = context.getResources();
	        DisplayMetrics metrics = resources.getDisplayMetrics();
	        float px = dp * (metrics.densityDpi / 160f);
	        return px;
	    }

	    /**
	     * This method converts device specific pixels to density independent
	     * pixels.
	     * 
	     * @param px A value in px (pixels) unit. Which we need to convert into db
	     * @param context Context to get resources and device specific display
	     *            metrics
	     * @return A float value to represent dp equivalent to px value
	     */
	    public static float convertPixelsToDp(float px, Context context) {
	        Resources resources = context.getResources();
	        DisplayMetrics metrics = resources.getDisplayMetrics();
	        float dp = px / (metrics.densityDpi / 160f);
	        return dp;
	    }

	    public static List<String> extractSourceTags(String input) {
	        final List<String> result = new ArrayList<String>();

	        final Pattern pattern = Pattern
	                .compile("<source[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");

	        final Matcher matcher = pattern.matcher(input);
	        while (matcher.find()) {
	            result.add(matcher.group());
	        }

	        return result;
	    }

	    public static boolean getBoolean(String key, boolean defValue) {
	        final SharedPreferences settings = PreferenceManager
	                .getDefaultSharedPreferences(MainApplication.getContext());
	        return settings.getBoolean(key, defValue);
	    }

	    public static int getInt(String key, int defValue) {
	        final SharedPreferences settings = PreferenceManager
	                .getDefaultSharedPreferences(MainApplication.getContext());
	        return settings.getInt(key, defValue);
	    }

	    public static long getLong(String key, long defValue) {
	        final SharedPreferences settings = PreferenceManager
	                .getDefaultSharedPreferences(MainApplication.getContext());
	        return settings.getLong(key, defValue);
	    }

	    public static String getString(String key, String defValue) {
	        final SharedPreferences settings = PreferenceManager
	                .getDefaultSharedPreferences(MainApplication.getContext());
	        return settings.getString(key, defValue);
	    }

	    public static boolean isNull(String s) {
	        if ((s == null) || s.equalsIgnoreCase("null") || (s.trim().length() == 0)) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public static void launchPlayStoreAppProfile(String appPackageName) {
	        try {
	            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="
	                    + appPackageName));
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            MainApplication.getContext().startActivity(intent);
	        } catch (android.content.ActivityNotFoundException anfe) {
	            Intent intent = new Intent(Intent.ACTION_VIEW,
	                    Uri.parse("http://play.google.com/store/apps/details?id="
	                            + appPackageName));
	            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	            MainApplication.getContext().startActivity(intent);
	        }
	    }

	    public static void putBoolean(String key, boolean value) {
	        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
	                MainApplication.getContext()).edit();
	        editor.putBoolean(key, value);
	        editor.commit();
	    }

	    public static void putInt(String key, int value) {
	        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
	                MainApplication.getContext()).edit();
	        editor.putInt(key, value);
	        editor.commit();
	    }

	    public static void putLong(String key, long value) {
	        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
	                MainApplication.getContext()).edit();
	        editor.putLong(key, value);
	        editor.commit();
	    }

	    public static void putString(String key, String value) {
	        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
	                MainApplication.getContext()).edit();
	        editor.putString(key, value);
	        editor.commit();
	    }

	    public static void registerOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
	        try {
	            PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext())
	                    .registerOnSharedPreferenceChangeListener(listener);
	        } catch (final Exception ignored) { // Seems to be possible to have a
	                                            // NPE here... Why??
	        }
	    }

	    public static void remove(String key) {
	        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(
	                MainApplication.getContext()).edit();
	        editor.remove(key);
	        editor.commit();
	    }

	    public static void unregisterOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
	        try {
	            PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext())
	                    .unregisterOnSharedPreferenceChangeListener(listener);
	        } catch (final Exception ignored) { // Seems to be possible to have a
	                                            // NPE here... Why??
	        }
	    }


	public static JsonSerializer<Date> jsonSerializer = new JsonSerializer<Date>() {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc,
				JsonSerializationContext context) {
			return src == null ? null : new JsonPrimitive(src.getTime());
		}
	};

	public static JsonDeserializer<Date> jsonDeserializer = new JsonDeserializer<Date>() {
		@Override
		public Date deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return json == null ? null : new Date(json.getAsLong());
		}
	};

	public static int getCellID(Context context) {

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager
				.getCellLocation();

		int cellID = cellLocation.getCid();
		return cellID;
	}

	public static int getCellLac(Context context) {

		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager
				.getCellLocation();
		int lac = cellLocation.getLac();
		return lac;
	}

	public static int getMCC(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String operator = telephonyManager.getNetworkOperator();
		int mcc = Integer.parseInt(operator.substring(0, 3));
		return mcc;
	}

	public static int getMNC(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String operator = telephonyManager.getNetworkOperator();
		int mnc = Integer.parseInt(operator.substring(3));
		return mnc;
	}
}

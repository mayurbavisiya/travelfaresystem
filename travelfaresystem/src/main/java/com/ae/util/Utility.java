package com.ae.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Utility {

	public static String getStringFromDate(Date date, boolean isTimeInclude) {
		DateFormat dateFormat;
		if (isTimeInclude)
			dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		else
			dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static String getMasterDataProperty(String key) {
		return ResourceBundle.getBundle("masterdata", Locale.ENGLISH).getString(key);
	}
}

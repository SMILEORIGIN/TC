package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy��MM��dd��HH_mm_ss_SSS");
		Date date = new Date();
		return simpleDateFormat.format(date);
	}
}

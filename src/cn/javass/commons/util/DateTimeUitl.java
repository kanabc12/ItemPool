package cn.javass.commons.util;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

    /**
     * java.util.Date类型转换器
     * @author wxy
     *
     */
public class DateTimeUitl {

	public static Date getDate() {
		Date d = null;
		Calendar c = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(c.getTime());
		d = Timestamp.valueOf(date);
		return d;
	}
	
	/**
	 * 获得只含年月日的格式化当日日期
	 * @return
	 */
	public static Date getDateArchive() {
		Date d = null;
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String str = year+"-"+month+"-"+day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 获得只含年月日的格式化当日日期
	 * @return
	 */
	public static String getStringDateArchive() {
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String str = year+"-"+month+"-"+day;
		return str;
	}
	
	/**
	 * 获得只含年月日的格式化当日日期(例如：20110312)
	 * @return
	 */
	public static String getStringPureDateArchive() {
		Calendar c = new GregorianCalendar();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String str = "";
		if(month>=10 && day>=10){
			str = year+""+month+""+day;
		}else if(month>=10 && day<10){
			str = year+""+month+"0"+day;
		}else if(month<10 && day>=10){
			str = year+"0"+month+""+day;
		}else{
			str = year+"0"+month+"0"+day;
		}

		return str;
	}
	
	/**
	 * 获得只含年月日的格式化当日日期
	 * @return
	 */
	public static Date getDateArchive(Date date) {
		Date d = null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String str = year+"-"+month+"-"+day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 获得只含年月日的格式化当日日期(例如：20110312)
	 * @return
	 */
	public static String getStringPureDateArchive(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		String str = "";
		if(month>=10 && day>=10){
			str = year+""+month+""+day;
		}else if(month>=10 && day<10){
			str = year+""+month+"0"+day;
		}else if(month<10 && day>=10){
			str = year+"0"+month+""+day;
		}else{
			str = year+"0"+month+"0"+day;
		}
		return str;
	}
	
	public static Date parseStringToDate(String str){
		if (null == str) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date parseStringToDateArchive(String str){
		if (null == str || "".equalsIgnoreCase(str)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date parseStringToDateTime(String str){
		Date d = null;
		if (null == str) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			 // return d = new Timestamp(sdf.parse(str).getTime());

			String date = sdf.format(sdf.parse(str).getTime());
			d = Timestamp.valueOf(date);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Date praseDateToTime(String str){
		if (null == str) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static String getTimeShort(Date currentTime) {
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	       String dateString = formatter.format(currentTime);
	       return dateString;
	}
	
	public static String getTimeOnly(Date currentTime) {
	       SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

	       String dateString = formatter.format(currentTime);
	       return dateString;
	}
	
	/**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.add(Calendar.DATE, -1);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
                .getTime());
        return dayBefore;
    }
    
    /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getSpecifiedDayBefore(Date specifiedDay) {
        Calendar c = Calendar.getInstance();

        c.setTime(specifiedDay);
        c.add(Calendar.DATE, -1);
        
        return c.getTime();
    }
    
    /**
     * 获得指定日期的00:00:01时间。
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getStartTimeOfSpecifiedDay(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 1);
        
        return c.getTime();
    }
    
    /**
     * 获得指定日期的前一天的00:00:01时间。
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static Date getStartTimeBeforeSpecifiedDay(Date specifiedDay) {
        Calendar c = Calendar.getInstance();
        c.setTime(specifiedDay);
        
        c.add(Calendar.DATE, -1);
        c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 1);

        return c.getTime();
    }

    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        c.add(Calendar.DATE, 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
                .format(c.getTime());
        return dayAfter;
    }

}

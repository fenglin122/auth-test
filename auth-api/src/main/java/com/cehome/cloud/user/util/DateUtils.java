package com.cehome.cloud.user.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static Date fromMysqlTimestamp(int timeInSeconds) {
		if (timeInSeconds <= 0)
			return null;
		else
			return new Date(1000L * timeInSeconds);
	}

	public static int nowInSeconds() {
		return (int) (Calendar.getInstance().getTimeInMillis() / 1000L);
	}


	public static String longToString(Long time,String pattern){
        return DateFormatUtils.format(new Date(time), pattern);
    }

	public static String format(Date date, String pattern) {
		return DateFormatUtils.format(date, pattern);
	}

	public static int fromGoodsDate() {
		Calendar yesterday = Calendar.getInstance();
		yesterday.add(Calendar.DAY_OF_MONTH, -1);
		String tmp = DateUtils.format(yesterday.getTime(), "yyyyMMdd");
		Integer tmp1 = Integer.parseInt(tmp);
		return tmp1;
	}

	// 将html5 转换为时间戳
	public static int dateParseTimestamp(String dateString)
			throws ParseException {

		dateString = dateString.replaceAll("T", " ");
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
		int timestamp = (int) (date.getTime() / 1000);// JAVA的时间戳长度是13位
		return timestamp;
	}

    public static int dateParseIntTime(String dateString) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateString);
        int timestamp = (int) (date.getTime() / 1000);
        return timestamp;
    }

    /**
     * desc 将具体的天转为int
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static int dateParseIntDayTime(String dateString) throws ParseException{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        int timestamp = (int) (date.getTime() / 1000);
        return timestamp;
    }

    public static int dateParseTimestampSecond(String dateString)
            throws ParseException {

        dateString = dateString.replaceAll("T", " ");
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        int timestamp = (int) (date.getTime() / 1000);// JAVA的时间戳长度是13位
        return timestamp;
    }
    /**
     * 转换指定格式时间字符 为秒数
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    public static int dateParseTimestamp(String dateString, String format) {
        try {

            Date date = new SimpleDateFormat(format).parse(dateString);
            int timestamp = (int) (date.getTime() / 1000);
            return timestamp;
        } catch (Exception ex) {
            return nowInSeconds();
        }

    }

	// 将时间戳转成 yyyy-MM-dd'T'HH:mm:ss格式"
	public static String timeStampParseHtml5Date(int timeStamp) {
		if (timeStamp <= 0) {
			return "";
		} else {
			Date date = fromMysqlTimestamp(timeStamp);
			return DateUtils.format(date, "yyyy-MM-dd'T'HH:mm:ss");
		}
	}

	// 将时间戳转成 yyyy-MM-dd'T'HH:mm:ss格式"
	public static String timeStampParseDateStr(int timeStamp) {
		if (timeStamp <= 0) {
			return "";
		} else {
			Date date = fromMysqlTimestamp(timeStamp);
			return DateUtils.format(date, "yyyy-MM-dd HH:mm");
		}
	}

    public static String timeReversal(int timeStamp) {
        if (timeStamp <= 0) {
            return "";
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        }
    }

	// 将时间戳转成yyyyMMdd格式
	public static String timeStampParseDate(int timeStamp) {
		if (timeStamp <= 0) {
			return null;
		} else {
			Date date = fromMysqlTimestamp(timeStamp);
			return DateUtils.format(date, "yyyyMMdd");
		}

	}
	// 将时间戳转成yyyy-MM-dd格式
	public static String timeStampToFormatString(int timeStamp) {
		if (timeStamp <= 0) {
			return null;
		} else {
			Date date = fromMysqlTimestamp(timeStamp);
			return DateUtils.format(date, "yyyy-MM-dd");
		}

	}

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }


    /**
     * 指定格式转换
     * @param timeStamp
     * @param format
     * @return
     */
    public static String timeStampParseDate(int timeStamp,String format) {
        if (timeStamp <= 0) {
            return null;
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, format);
        }

    }



    // 将时间戳转成MM/dd格式
    public static String timeStampParseString(int timeStamp) {
        if (timeStamp <= 0) {
            return null;
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "MM/dd");
        }
    }

    // 将时间戳转成yyyy.MM.dd格式
    public static String timeStampParsePhoto(int timeStamp) {
        if (timeStamp <= 0) {
            return null;
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "yyyy.MM.dd");
        }
    }

    // 将时间戳转成yyyy-MM-dd格式
    public static String timeComment(int timeStamp) {
        if (timeStamp <= 0) {
            return null;
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "yyyy-MM-dd");
        }
    }

    //将时间戳转换成yymm
    public static String timeStampParseLink(int timeStamp) {
        if (timeStamp <= 0) {
            return null;
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "yyyyMMdd");
        }
    }

    // 将时间戳转成MM-dd格式
    public static String timeNewsString(int timeStamp) {
        if (timeStamp <= 0) {
            return null;
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "MM-dd");
        }
    }

	public static String timeAbstract(int datetime){
		Calendar firstTimeOfToday = Calendar.getInstance();
		firstTimeOfToday.set(Calendar.HOUR_OF_DAY, 0);
		firstTimeOfToday.set(Calendar.MINUTE, 0);
		firstTimeOfToday.set(Calendar.SECOND, 0);
		int timeToToday = (int)(firstTimeOfToday.getTimeInMillis() / 1000);
		firstTimeOfToday.roll(Calendar.DATE, false);
		int timeToYestoday = (int)(firstTimeOfToday.getTimeInMillis() / 1000);
		int timeToNow = DateUtils.nowInSeconds() - datetime;

		if(datetime < timeToYestoday){
			Date date = DateUtils.fromMysqlTimestamp(datetime);
			if(date != null)
				return DateUtils.format(date,"yyyy.MM.dd");
		}
		else if(datetime >= timeToYestoday && datetime < timeToToday)
			return "昨天";
		else{
		//60秒内
			if(timeToNow < 60)
				return "刚刚";
			else if(timeToNow >= 60 && timeToNow < 60*60)
				return (timeToNow / 60) + "分钟前";
			else if(timeToNow >= 60*60 && timeToNow < 24*60*60)
				return (timeToNow / (60*60)) + "小时前";
		}
		return "";
	}


    public static String timeAbstracts(int datetime){
        Calendar firstTimeOfToday = Calendar.getInstance();
        firstTimeOfToday.set(Calendar.HOUR_OF_DAY, 0);
        firstTimeOfToday.set(Calendar.MINUTE, 0);
        firstTimeOfToday.set(Calendar.SECOND, 0);
        int timeToToday = (int)(firstTimeOfToday.getTimeInMillis() / 1000);
        firstTimeOfToday.roll(Calendar.DATE, false);
        int timeToYestoday = (int)(firstTimeOfToday.getTimeInMillis() / 1000);
        int timeToNow = DateUtils.nowInSeconds() - datetime;

        if(datetime < timeToYestoday){
            Date date = DateUtils.fromMysqlTimestamp(datetime);
            if(date != null)
                return DateUtils.format(date,"MM月dd日");
        }
        else if(datetime >= timeToYestoday && datetime < timeToToday)
            return "昨天";
        else{
            //60秒内
            if(timeToNow < 60)
                return "刚刚";
            else if(timeToNow >= 60 && timeToNow < 60*60)
                return (timeToNow / 60) + "分钟前";
            else if(timeToNow >= 60*60 && timeToNow < 24*60*60)
                return (timeToNow / (60*60)) + "小时前";
        }
        return "";
    }

    public static String formatYYddStr(Integer datetime){
    	if(datetime==null)
    		return "";
    	
        int timeToNow = DateUtils.nowInSeconds() - datetime;
        //60秒内
        if(timeToNow < 60)
            return "1分钟前更新";
        else if(timeToNow >= 60 && timeToNow < 60*60)
            return (timeToNow / 60) + "分钟前更新";
        else if(timeToNow >= 60*60 && timeToNow < 24*60*60)
            return (timeToNow / (60*60)) + "小时前更新";
    	else{
            Date date = DateUtils.fromMysqlTimestamp(datetime);
            if(date != null)
                return DateUtils.format(date,"MM-dd")+"更新";
    	}
        return "";
    }
    
    public static String timeStampParseStr(int timeStamp) {
        if (timeStamp <= 0) {
            return "";
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int nowYear() {
        return (int) (Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 获取当年月份
     * @return
     */
    public static int nowMonth() {
        return (int) (Calendar.getInstance().get(Calendar.MONTH));
    }

    /**
     * 比较当前日期
     */
    public static int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当天
     * @return
     */
    public static String getNowDate() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(new Date());
    }
    public static String getYestoday() {
    	Date dNow = new Date();   //当前时间
    	Date dBefore = new Date();
    	Calendar calendar = Calendar.getInstance(); //得到日历
    	calendar.setTime(dNow);//把当前时间赋给日历
    	calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
    	dBefore = calendar.getTime();   //得到前一天的时间
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
    	String yestoday = sdf.format(dBefore);    //格式化前一天
		return yestoday; 
    }
    public static String getFirstDayOfMonth(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        String firstday;  
        // 获取前月的第一天  
        Calendar cale = Calendar.getInstance();  
        cale.add(Calendar.MONTH, 0);  
        cale.set(Calendar.DAY_OF_MONTH, 1);  
        firstday = format.format(cale.getTime());  
		return firstday;
    }
    /**
     * 获取N天前时间，0<n<=100
     * @param days
     * @return
     */
    public static String getNDaysBeforeNowDate(int days){
    	return getNDaysBeforeOrAfterNowDate(days,"before");
    }
    /**
     * 获取N天后时间，0<n<=100
     * @param days
     * @return
     */
    public static String getNDaysAfterNowDate(int days){
    	return getNDaysBeforeOrAfterNowDate(days,"after");
    }
    
	private static String getNDaysBeforeOrAfterNowDate(int days, String type) {
		int converDays = Math.abs(days);
		if (type.equals("before"))
		    converDays = -converDays;
		return getNDaysBeforeOrAfterNowDate(converDays);
	}
    
	private static String getNDaysBeforeOrAfterNowDate(int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		Date fixedDay = c.getTime();
		String fixedDayStr = sdf.format(fixedDay);
		return fixedDayStr;
	}
    
    /**   
     * @Title: getDateDiff   
     * @Description: 获得2个时间的时间差  
     * @param dateStart
     * @param dateStop
     * @return Long 
     */ 
    public static long getDateDiff(String dateStart, String dateStop){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	try {
			Date d1 = format.parse(dateStart);
			Date d2 = format.parse(dateStop);
			 //毫秒ms
            long diff = d2.getTime() - d1.getTime();
            long diffSeconds = diff/1000;
            return diffSeconds;
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return 0;
    }
    
    /**   
     * @Title: getNowTimeFormat   
     * @Description: 获取时间
     * @return String 
     */ 
    public static String getNowTimeFormat(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());
		return date;
    }

    /**
     * 获取当前时间的前一个小时
     * @return
     */
    public static String beforeOneHourToNowDate(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);

        String result=sdf.format(calendar.getTime());
        try{
            if(time!=null&&!"".equals(time))
                result=sdf.format(df.parse(time));
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return result.replaceAll(" ","");
    }

    /**
     * 时间增加一天
     * @param timeStamp
     * @return
     */
    public static String timeIncreasesOneDay(int timeStamp) {
        if (timeStamp <= 0) {
            return "";
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(fromMysqlTimestamp(timeStamp));
            c.add(Calendar.DAY_OF_MONTH, 1);
            return DateUtils.format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
        }
    }
    
    /**   
     * @Title: getSpecifiedDayBefore   
     * @Description: 获取当前时间前一天数据
     * @param patten
     * @return String 
     */ 
    public static String getSpecifiedDayBefore(String patten){ 
    	String specifiedDay = getNowDate();
    	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance(); 
    	Date date=null; 
    	try { 
    	date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
    	} catch (ParseException e) { 
    	e.printStackTrace(); 
    	} 
    	c.setTime(date); 
    	int day=c.get(Calendar.DATE); 
    	c.set(Calendar.DATE,day-1);

    	String dayBefore=new SimpleDateFormat(patten).format(c.getTime()); 
    	return dayBefore; 
    }
    
    /**   
     * @Title: getSpecifiedDayBefore   
     * @Description: 获取当前时间后一天数据
     * @param patten
     * @return String 
     */ 
    public static long getNextDaySeconds(String DateStr){ 
    	Calendar c = Calendar.getInstance(); 
    	Date date=null; 
    	try { 
    		date = new SimpleDateFormat("yy-MM-dd").parse(DateStr); 
    	} catch (ParseException e) { 
    		e.printStackTrace(); 
    	} 
    	c.setTime(date); 
    	int day=c.get(Calendar.DATE); 
    	c.set(Calendar.DATE, day+1);

    	String dayAfter=new SimpleDateFormat("yyyyMMdd").format(c.getTime()); 
    	String days = dayAfter + "000000000";
    	return Long.parseLong(days);
    }
    /**
     * 获取当前时间前(参数)天数
     * @return
     */
    public static String getDayBefore(Integer d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String specifiedDay = formatter.format(new Date());
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-d);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }
    /**
     * 获取当前时间后(参数)天数
     * @return
     */
    public static String getDayAfter(Integer d){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String specifiedDay = formatter.format(new Date());
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+d);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayBefore;
    }

	 // 将时间戳转成 HH:mm格式"
    public static String timeStampParseShow(int timeStamp) {
        if (timeStamp <= 0) {
            return "";
        } else {
            Date date = fromMysqlTimestamp(timeStamp);
            return DateUtils.format(date, "HH:mm");
        }
    }
    
    public static long getTodayEndSeconds() {
        Calendar c = Calendar.getInstance();
        long today = c.getTimeInMillis()/1000;
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.add(Calendar.DAY_OF_MONTH, 1);
        long tomorrow =  c.getTimeInMillis()/1000;
        return tomorrow - today;
    }
    
    public static String getSpecifiedDay(String patten){ 
    	String specifiedDay = getNowDate();
    	//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
    	Calendar c = Calendar.getInstance(); 
    	Date date=null; 
    	try { 
    	date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
    	} catch (ParseException e) { 
    	e.printStackTrace(); 
    	} 
    	c.setTime(date); 
    	int day=c.get(Calendar.DATE); 

    	String dayBefore=new SimpleDateFormat(patten).format(c.getTime()); 
    	return dayBefore; 
    	}

    public static String getNowDateConvertYyMmDd() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");

        return formatter.format(new Date());
    }


    /**
     * 获取当前时间前3个月
     * @return
     */
    public static String getSpecifiedMonthBefore(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);//得到前3个月
        Date  formNow3Month = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return  formatter.format(formNow3Month);
    }


    /**
     * 获取当前时间前1个月
     * @return
     */
    public static int getMonthBefore(){
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.MONTH, -1);//得到前1个月
        String tmp = DateUtils.format(yesterday.getTime(), "MM");
        Integer tmp1 = Integer.parseInt(tmp);
        return tmp1;
    }
    
    /**
     * 转成年或者月字符串
     * @param timeStamp
     * @return
     */
    public static String transferYearOrMonth(Integer timeStamp) {
    	if(timeStamp==null)
    		return null;
    	
    	if(timeStamp.toString().length()==6){
    		String month = timeStamp.toString().substring(4);
    		if(Integer.parseInt(month)<1 || Integer.parseInt(month)>12)
    			return timeStamp.toString().substring(0, 4) + "年";
    		else
    			return timeStamp.toString().substring(0, 4) + "年" + timeStamp.toString().substring(4, timeStamp.toString().length()) + "月";
    	}else if(timeStamp.toString().length()==4){
    		return timeStamp.toString() + "年";
    	}else{
    		return timeStamp.toString();
    	}
    }

    /**
     * 获取当前时间前n个月最后一天
     * @param num
     * @return
     */
    public static String getMonthBeforeLastDay(Integer num){
        Calendar day = Calendar.getInstance();
        day.set(Calendar.DAY_OF_MONTH, 1);
        day.add(Calendar.DAY_OF_MONTH, -1);
        day.add(Calendar.MONTH,-num);
        return DateUtils.format(day.getTime(), "yyyy-MM-dd");
    }

    /**
     * 获取当前时间前n个月第一天
     * @param num
     * @return
     */
    public static String getMonthBeforeFirstDay(Integer num){
        Calendar day = Calendar.getInstance();
        day.add(Calendar.MONTH, -num);
        day.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtils.format(day.getTime(), "yyyy-MM-dd");
    }
    
    public static long dateParseTimestampLong(String dateString, String format) {
        try {
            Date date = new SimpleDateFormat(format).parse(dateString);
            long timestamp = (long) (date.getTime());
            return timestamp;
        } catch (Exception ex) {
            return nowInSeconds();
        }

    }

    public static String getDateStr(Date date, Boolean dateWithoutTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateWithoutTime ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return formatter.format(cal.getTime());
    }

    public static long getZeroMillis() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.set(Calendar.HOUR_OF_DAY, 0);
        return c.getTimeInMillis();
    }
}
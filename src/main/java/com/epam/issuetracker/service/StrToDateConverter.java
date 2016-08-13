package com.epam.issuetracker.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StrToDateConverter {
	
	
    /*
     * To convert String to java.sql.Date, use
     * Date (long date) constructor.
     *
     * It creates java.sql.Date object from the milliseconds provided.
     */

    //first convert string to java.util.Date object using SimpleDateFormat
    
	
    public static Date convertStringToDate (String sDate){
    	
    	DateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
    	java.util.Date stDate = null;
		try {
			stDate = sdf.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	 java.sql.Date sqlDate = new java.sql.Date(stDate.getTime());
    	 return  sqlDate;
    }


}

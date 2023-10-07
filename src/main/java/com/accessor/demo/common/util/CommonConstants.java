package com.accessor.demo.common.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CommonConstants {
	public static final Logger log = LoggerFactory.getLogger(CommonConstants.class);
	@PersistenceContext
	public EntityManager em;
	public static final Properties appProperties = new Properties();
	public static final Properties configurationProperties = new Properties();
	public static final String RESPONSE_MSG_SUCCESS = "SUCCESS";
	public static final String RESPONSE_MSG_FAILED = "FAILED";
	public static final int RESPONSE_CODE_SUCCESS = 200;
	public static final int RESPONSE_CODE_FAILED = 150;
	public static final int API_RESPONSE_FAILED_CODE = 101;
	public static final int API_RESPONSE_FAILED_CODE_105 = 101;
	public static final int API_RESPONSE_FAILED_CODE_115 = 115;
	public static final String FORBIDDEN_MESSAGE = "Access denied for requesting resource.";
	public static final String RECORD_NOT_FOUND_MESSAGE = "Requested record not found.";
	public static final String DATABASE_FAILURE__MESSAGE = "Currently database is down. Please try after some time.";
	public static final String DATABASE_QUERY_ERROR__MESSAGE = "Currently database is down. Please try after some time.";
	public static final String INTERNAL_SERVER_ERROR__MESSAGE = "Internal server error.";
	public static final String HEADER_PARAM_USERID = "X-UserId";
	public static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	public static final String TASK_SKIP_SUCESS_MSG="task.completed.sucessfully"; 
	public static final String APPLICATION_ERROR_MSG="application.error.occurred";
	protected static final String[] MONTHS = { "", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT","NOV", "DEC" };
	public static final String USER_DOESNOT_EXISTS = "User doesn't exists";

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties");
		InputStream config = Thread.currentThread().getContextClassLoader().getResourceAsStream("configuration.properties");
		try {
			appProperties.load(in);
			configurationProperties.load(config);
		} catch (IOException e) {
			log.error("Problem reading the application.properties file from application resources.", e);
		}
		
	}

	public static String getAppProperty(String key) {
		return (appProperties.getProperty(key, ""));
	}
	public static String getCofigurationProperty(String key) {
		return (configurationProperties.getProperty(key, ""));
	}

	
	public static Boolean isValidString(String str) {	
		Boolean isValidString = false;
		if(Pattern.matches("^[a-zA-z]+([\\s][a-zA-Z]+)*$", str)) {
			isValidString = true;
		}
		return isValidString;
	}
	
	public static Date getDateFromUTCStringDate(String utcStringDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date date=null;
		try {
			date=formatter.parse(utcStringDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * @author satyasiba biswal
	 * @param 10-Jan-2019 as String
	 * @return Date
	 */
	public static Date getDateFromDDMMMYYYY(String date) {
        if(date==null) return null;
    java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter
                .ofPattern("dd-MMM-yyyy");
        LocalDate date2 = LocalDate.parse(date, dateTimeFormatter);
        return (Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
	
	public static Date getDateFromupDDMMMYYYY(String date) {
        if(date==null) return null;
    java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter
                .ofPattern("dd-MMM-yy");
        LocalDate date2 = LocalDate.parse(date, dateTimeFormatter);
        return (Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
	public static Date getDateFromteDDMMMYYYY(String date) {
        if(date==null) return null;
    java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter
                .ofPattern("yyyy-MM-dd");
        LocalDate date2 = LocalDate.parse(date, dateTimeFormatter);
        return (Date.from(date2.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
	public int getUniqueId() {
		BigDecimal num3 = null;
		try {
			Session session = (Session) em.getDelegate();
			org.hibernate.Query q = session.createSQLQuery("select commonuid.nextval as myid from dual");
			num3 = (BigDecimal) q.uniqueResult();
		} catch (Exception e) {
			log.error("CommonConstants.getUniqueId() SQL problem.", e);
		}
		return num3 == null ? 0 : num3.intValue();
	}
	
	/**
	 * 
	 * @param utcTime
	 * @return Local Time dd MMM YY
	 */
	public static String findLocalTimeByUTC(String utcTime) {
		DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date=null;
		try {
			date = utcFormat.parse(utcTime);
		} catch (ParseException e) {}
		DateFormat pstFormat = new SimpleDateFormat("dd MMM YY");
		pstFormat.setTimeZone(TimeZone.getTimeZone("PST"));

		return pstFormat.format(date);
	}
	
	/* 
	 * @param phoneNumber
	 * @return Boolean
	 */
	public static Boolean isValidPhoneNumber(String phoneNumber) {
		boolean isValidPhoneNumber = false;
		boolean isNumeric =true;
		BigInteger n=null;
		try {
		n= new BigInteger(phoneNumber); 
		}catch(Exception e) {
			isNumeric = false;
		}
		if(phoneNumber!=null && (phoneNumber.length()<=10 && isNumeric)) {
			isValidPhoneNumber =true;
		}
		return isValidPhoneNumber;
	}
	
	/**
	 * @Desciption :
	 * @param :
	 * @Return :
	 * 
	 */

	public boolean validateFileUpload(MultipartFile file) {
		boolean isValidFile = false;
		try {
			if (null == file || file.isEmpty()) {
				return false;
			} else {
				long fileSize = file.getSize();
				long fileSizeInMb = fileSize / 1024 / 1024;
				if (fileSizeInMb > 200) {
					return false;
				} else {
					return true;
				}
			}
		} catch (Exception e) {
			isValidFile = false;
		}
		return isValidFile;

	}

	public enum AppLogType {
		ERROR, WARNING, INFO
	}

	
	
	public int getRequestId() {
		BigDecimal num3 = null;
		try {
			Session session = (Session) em.getDelegate();
			org.hibernate.Query q = session.createSQLQuery("select SUPPORT_QUERY_SEQ.nextval as myid from dual");
			num3 = (BigDecimal) q.uniqueResult();
		} catch (Exception e) {
			log.error("CommonConstants.getRequestId() SQL problem.", e);
		}
		return num3 == null ? 0 : num3.intValue();
	}

}

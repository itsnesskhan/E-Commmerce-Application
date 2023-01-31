package com.ecom.utills;

import java.io.File;

public class Constants {

	public static final String ERROR_KEY = "Error";
	
	public static final Integer ZERO_AS_INTEGER = 0;
	
	public static final Integer ONE_AS_INTEGER = 1;
	
	public static final String ORDER_STATUS_PENDING = "PENDING";
	
	public static final String ORDER_STATUS_SHIPED = "SHIPED";
	
	public static final String ORDER_STATUS_ON_THE_WAY = "ON_THE_WAY";
	
	public static final String DEFAULT_PAGE_NUMBER = "0";
	
	public static final String DEFAULT_PAGE_SIZE = "4";
	
	public static final String SHORT_DIRECTION_ASC = "ASC";
	
	public static final String SORT_BY_ID="pid";
	
	public static final String SORT_BY_NAME="name";
	
	public static final String SORT_BY_DATE= "";
			
	public static final String SHORT_DIRECTION_DSC = "DSC";
	
	public static final String UPLOAD_DIR = "src\\main\\resources\\static\\profile";
	
	public static final String UPLOAD_DIR_2 = "static"+File.separator+"profile";
	
	public static final String USER_DOES_NOT_EXIST = "user does not exist";
	
	public static final String WRONG_PASSWORD="wrong password";
	
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String AUTHORIZATION_REFERESH_KEY = "AuthorizationRefresh";
}

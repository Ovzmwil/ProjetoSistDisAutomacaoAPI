package com.lsantana.automation.util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	
	private int retryCount = 1;
	private int maxRetryCount = 3;
	
	public boolean retry(ITestResult result) {
		try {
			if(retryCount < maxRetryCount) 
			{
				Thread.sleep(1000);
				retryCount++; 
				return true; 
			} 
			return false; 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

package com.guru99.demo.gtpl_bank.utilities;

//Listener class used to generate extent reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
		
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		String reportName="test-report-"+timeStamp+".html";
		
		htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+reportName);//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		htmlReporter.config().setDocumentTitle("gtpl-bank-v1"); // Title of the report
		htmlReporter.config().setReportName("Automation Report"); // Name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
		
		extentReports=new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		extentReports.setSystemInfo("hostname","localhost");
		extentReports.setSystemInfo("environment","QA");
		extentReports.setSystemInfo("user","Ankit");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		extentTest=extentReports.createTest(result.getName()); // create new entry in the report
		extentTest.log(Status.PASS,MarkupHelper.createLabel(result.getName(),ExtentColor.GREEN)); // send the PASS information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult result)
	{
		extentTest=extentReports.createTest(result.getName()); // create new entry in the report
		extentTest.log(Status.FAIL,MarkupHelper.createLabel(result.getName(),ExtentColor.RED)); // send the FAIL information to the report with RED color highlighted
		
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
		String screenshotPath=System.getProperty("user.dir")+"\\screenshots\\"+result.getName()+"-"+timeStamp+".png";
		
		File file = new File(screenshotPath); 
		
		if(file.exists())
		{
		try {
			extentTest.fail("Screenshot is below:" + extentTest.addScreenCaptureFromPath(screenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		extentTest=extentReports.createTest(result.getName()); // create new entry in the report
		extentTest.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extentReports.flush();
	}
}

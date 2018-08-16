package com.qa.quickstart.demosite;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class demo2 {
		
	private WebDriver driver = null;
	private static ExtentReports report = new ExtentReports("C:\\Users\\Admin\\Desktop\\AutomationReports\\BasicReport.html", true);
	public ExtentTest test;
	
		@Before
				public void setup() {
				System.setProperty( "webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
				driver = new ChromeDriver();
		}
		
		@After
				public void teardown() {
				driver.quit();
				report.endTest(test);
		}
		
		@AfterClass		
				public static void teardownclass() {
				report.flush();
		}
			
		@Test
				public void mthodTest() throws InterruptedException {
			
				driver.manage().window().maximize();

						FileInputStream file = null;
						try {
							file = new FileInputStream (constant.path_demosite_data);
						} catch(FileNotFoundException e) {}
						Thread.sleep(1000);
						XSSFWorkbook workbook = null;
						try {
							workbook = new XSSFWorkbook(file);
						} catch (IOException e) {}
						Thread.sleep(1000);
						XSSFSheet sheet = workbook.getSheetAt(0);
						
						for (int i = 1; i < 8; i++) {
							// instead of 8 write sheet.getPhysicalNumberOfRows()
							System.out.println(sheet.getPhysicalNumberOfRows());

							XSSFCell username = sheet.getRow(i).getCell(0);
							XSSFCell password = sheet.getRow(i).getCell(1);

							String user = username.getStringCellValue();
							String pass = password.getStringCellValue();
							
							System.out.println (username.getStringCellValue() + password.getStringCellValue());
							Thread.sleep(1000);
							
							driver.get("http://thedemosite.co.uk/addauser.php");
							
							AddUserPage add_page = PageFactory.initElements(driver, AddUserPage.class);
							add_page.create_user(user, pass);
							Thread.sleep(1000);
							
							driver.get("http://thedemosite.co.uk/login.php");
										
							AddLoginPage login_page = PageFactory.initElements(driver, AddLoginPage.class);
							login_page.create_login(user, pass);
							Thread.sleep(1000);
						}				
										
				test = report.startTest("Sucessful Login");
	            test.log(LogStatus.PASS,"Logged in");	
				
				assertEquals(driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")) , 
						driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")));
		}	
}
			


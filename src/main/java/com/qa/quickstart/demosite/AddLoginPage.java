package com.qa.quickstart.demosite;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddLoginPage {

		@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
		private WebElement username_field;
		@FindBy (xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
		private WebElement password_field;
		
		public void create_login(String username, String password) {
			username_field.sendKeys(username);
			password_field.sendKeys(password);
			password_field.submit();
		}
		
}



package com.eshopping.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.eshopping.webdriver.TestBase;

public class HomePage extends TestBase{
	 
	
	 @FindBy(xpath="//*[@id='menuUser']")
	 public WebElement  userMenu;
	

}

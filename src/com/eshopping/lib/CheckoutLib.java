package com.eshopping.lib;



import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.eshopping.pages.HomePage;
 

public class CheckoutLib extends HomePage{

	public void createNewAccount() throws InterruptedException {
		Thread.sleep(4000);
		HomePage hpage = PageFactory.initElements(dr, HomePage.class);
		hpage.userMenu.click();
		
		String parentWindowHandler = dr.getWindowHandle();   
		String subWindowHandler = null;

		Set<String> handles = dr.getWindowHandles();  
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next();
		}
		dr.switchTo().window(subWindowHandler);  
 
		 
		Actions actions = new Actions(dr);
		actions = actions.moveToElement(dr.findElement(By.linkText("CREATE NEW ACCOUNT")));
		actions.build().perform();
		(dr.findElement(By.linkText("CREATE NEW ACCOUNT"))).click();
		 
		dr.switchTo().window(parentWindowHandler);   
		
		dr.findElement(By.name("usernameRegisterPage")).click();
	    dr.findElement(By.name("usernameRegisterPage")).sendKeys("Punam");
	    dr.findElement(By.name("emailRegisterPage")).sendKeys("pu.musale@gmail.com");
	    dr.findElement(By.name("passwordRegisterPage")).sendKeys("test@123");
	    dr.findElement(By.name("confirm_passwordRegisterPage")).sendKeys("test@123");
	    dr.findElement(By.name("first_nameRegisterPage")).click();
	    dr.findElement(By.name("first_nameRegisterPage")).sendKeys("Punam");
	    dr.findElement(By.name("last_nameRegisterPage")).sendKeys("Punam");
	    dr.findElement(By.name("phone_numberRegisterPage")).sendKeys("1234567890");
	    dr.findElement(By.name("countryListboxRegisterPage")).click();
	    {
	      WebElement dropdown = dr.findElement(By.name("countryListboxRegisterPage"));
	      dropdown.findElement(By.xpath("//option[. = 'India']")).click();
	      
	    }
	    dr.findElement(By.name("cityRegisterPage")).click();
	    dr.findElement(By.name("cityRegisterPage")).sendKeys("Pune");
	    dr.findElement(By.name("addressRegisterPage")).click();
	    dr.findElement(By.name("addressRegisterPage")).sendKeys("pune");
	    dr.findElement(By.name("state_/_province_/_regionRegisterPage")).sendKeys("Maharashtra");
	    dr.findElement(By.name("postal_codeRegisterPage")).click();
	    dr.findElement(By.name("postal_codeRegisterPage")).sendKeys("411036");
	    dr.findElement(By.name("i_agree")).click();
	}
	 
	

}

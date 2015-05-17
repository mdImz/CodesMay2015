package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox_basic1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
FirefoxDriver fdriver=new FirefoxDriver();
fdriver.get("http://s-login.cengage.com");
fdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
fdriver.findElementById("emailId").sendKeys("cnow_inst11@qaittest.com");

//Or second way of writing the input is below
/*WebElement uid = fdriver.findElementByName("email");
uid.sendKeys("cnow_inst11@qaittest.com");*/

//Now entering the password.
WebElement pwd=fdriver.findElementById("password");
pwd.sendKeys("A123456");

//Now Login button
fdriver.findElementByXPath("//input[@type='submit']").click();
fdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

//catching the name of Institute.
String inst= fdriver.findElementById("welcomeBox").getText();
//System.out.print(inst);		//prints both the instructor and institutes names
//extracting the institutename.
boolean adamsT= inst.contains("ADAMS");
	if(adamsT==true)
		System.out.println("ADAMS");
			



//clicking on the book title 
fdriver.findElementByXPath("//span[contains(text(),'CengageNOW for')]").click();
fdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
String currenturl = fdriver.getCurrentUrl();
System.out.println(currenturl);

nextWindow(adamsT,fdriver);
	}
	
	//Method to work inside CNOW application.
	public static void nextWindow(boolean institute, FirefoxDriver fdriver)
	{
		String winHandleBefore=fdriver.getWindowHandle();
		
		for(String winHandle:fdriver.getWindowHandles())
		{
			fdriver.switchTo().window(winHandle);
		}
		
		
		//FirefoxDriver fd=new FirefoxDriver();
		String url=fdriver.getCurrentUrl();
		System.out.println(url);
		
		//boolean adamT=url.contains("ADAMS");
		/*if (adamT==institute)
		fd.get("http://stagingwest.instructor.ilrn.com/ilrn/home/home.do");*/
		
	}

}
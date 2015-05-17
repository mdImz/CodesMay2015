package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


public class SecondFlow_ccw {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirefoxDriver fdriver=new FirefoxDriver();
		fdriver.manage().window().maximize();
		fdriver.get("http://s-login.cengage.com");
		fdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		fdriver.findElementById("emailId").sendKeys("cnow_imran@qaittest.com");

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
		//System.out.print(inst);  //prints both the instructor and institutes names
		//extracting the institutename.
		boolean adamsT= inst.contains("ADAMS");
		 if(adamsT==true)
		  System.out.println("ADAMS");
		   



		//clicking on the book title 
		fdriver.findElementByXPath("//span[contains(text(),'CengageNOW for')]").click();
		fdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		fdriver.manage().window().maximize();
		String currenturl = fdriver.getCurrentUrl();
		System.out.println(currenturl);

		nextWindow(adamsT, fdriver);

	}

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
		  
		  courseCreation2(fdriver);
		 }

		public static void courseCreation2(FirefoxDriver fdriver)
		 {
		  fdriver.findElementByLinkText("Create a New Course").click();
		  fdriver.findElementByXPath("//input[@id='processTypeCopy']").click();
		  fdriver.findElementById("copyExistingLabel").click();
		  fdriver.findElementById("createCourseNextStepButton").click();
		  Select courseSelector = new Select(fdriver.findElementById("courseSelector"));
		  courseSelector.selectByIndex(0);
		  
		  fdriver.findElementById("createCourseNextStepButton").click();
		  
		   String coursenm = "CCW2_course1";  
		   fdriver.findElementById("name").sendKeys(coursenm);
		   fdriver.findElementById("startDateTimeFormat").click();
		   //fdriver.findElementByClassName("ui-state-default ui-state-highlight"); //Example of how compound class name is not permitted. 
		   fdriver.findElementByLinkText("12").click();
		   fdriver.findElementByXPath("//button[contains(text(),'Done')]").click();
		   
		   fdriver.findElementById("endDateTimeFormat").click();
		   //int year=2015, mnth=12;
		   
		   WebElement yd=fdriver.findElementByXPath("//select[@class='ui-datepicker-year']");
		   Select yeardrop= new Select(yd);
		   yeardrop.selectByVisibleText("2015");
		   
		   fdriver.findElementByLinkText("12").click();
		   fdriver.findElementByXPath("//button[contains(text(),'Done')]").click();
		   //fdriver.findElementByXPath("//td[@data-year="+year+"and @data-month="+mnth+"//a[contains(text(),'28')]]").click();
		   
		   fdriver.findElementByLinkText("Create Course").click();
		   
		   String ekey = fdriver.findElementByXPath("//div[@class='pageContentBody']//strong[contains(text(),'E-')]").getText();
		   System.out.println("Course is created and Details are as follows");
		   System.out.println("Course Name: "+coursenm+" and key is: "+ekey);
		   
		   fdriver.findElementByLinkText("Courses").click();//
		  
		 }
}

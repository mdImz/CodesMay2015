package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Ff_ccw {

 public static void main(String[] args) {
  // TODO Auto-generated method stub
FirefoxDriver fdriver=new FirefoxDriver();
fdriver.manage().window().maximize();
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
  
  courseCreation(fdriver);
 }

 //Method for course creation
 public static void courseCreation(FirefoxDriver fdriver)
 {
	  fdriver.findElementByLinkText("Courses").click();
	  fdriver.findElementByLinkText("Create a New Course").click();
	  fdriver.findElementByXPath("//label[contains(text(),'Build a Course Manually')]").click();
	  fdriver.findElementById("createCourseNextStepButton").click();
	  String btitle="Warren/Reeve/Duchac: Accounting, 24th Edition";
	
	  //WebElement tbook=fdriver.findElementById("bookSelector");
//      Select textBook=new Select(tbook);
	  
	  //Above code is transitive to following one liner.
	  Select textBook=new Select(fdriver.findElementById("bookSelector"));
	  
	  textBook.selectByVisibleText(btitle);
	  String coursenm = "AutomatedCourse7";  
	  fdriver.findElementById("name").sendKeys(coursenm);
	  fdriver.findElementById("startDateTimeFormat").click();
	  //fdriver.findElementByClassName("ui-state-default ui-state-highlight");	//Example of how compound class name is not permitted.	
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
 }
}
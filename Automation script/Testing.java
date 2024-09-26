package jAvascript;

import java.time.Duration;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing {
	
	public static void main(String [] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "F:\\Learning\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		dow
        driver.manage().window().maximize();

        driver.get("https://app.thepric.com/priclogin");
        
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/div/div[2]/div/div/div/div/div/div/div[2]/div/div/div[3]/p/a")));
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);

        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div")));
        
 
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element1);
        
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div/div[2]/div[1]/div[3]/div[1]/div/div/div")));
                
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element2);
        
        WebElement checkbx = wait.until(ExpectedConditions.elementToBeClickable(By.id("termsCheckbox")));
//        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
        
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", checkbx);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkbx);
        
        Thread.sleep(2000);
        
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.id("bigButton")));
//      ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
      
       ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn);

       Thread.sleep(2000);
       
       driver.findElement(By.name("name")).sendKeys("Rohan Hoval");
       Thread.sleep(1000);
       
       WebElement mobileField = driver.findElement(By.name("mobileNumber"));
       WebElement emailField = driver.findElement(By.name("email"));
       WebElement cityField = driver.findElement(By.name("city"));
       
       
       mobileField.sendKeys("9898989");
       Thread.sleep(1000);
       emailField.sendKeys("rohan.h@a.com");
       Thread.sleep(1000);
       cityField.sendKeys("Pune");
       Thread.sleep(1000);
       
       WebElement dropdownElement = driver.findElement(By.name("state"));
       Select dropdown = new Select(dropdownElement);
       
       dropdown.selectByVisibleText("Maharashtra");
       
       
       
       WebElement btn1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("bigButton")));
//     ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
     
      ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn1);
       
       String mobileValue = mobileField.getAttribute("value");
       if (mobileValue.isEmpty()) {
           System.out.println("Mobile number is required.");
       } else if (!isValidMobile(mobileValue)) {
           System.out.println("Invalid mobile number. It should contain exactly 10 digits.");
       } else {
           System.out.println("Mobile number is valid.");
       }

       // 2. Validate if email is empty or not valid (proper email format)
       String emailValue = emailField.getAttribute("value");
       if (emailValue.isEmpty()) {
           System.out.println("Email address is required.");
       } else if (!isValidEmail(emailValue)) {
           System.out.println("Invalid email format.");
       } else {
           System.out.println("Email ID is valid.");
       }

       // Proceed only if both mobile and email are valid
       if (isValidMobile(mobileValue) && isValidEmail(emailValue)) {
           System.out.println("All validations passed. Proceeding with form submission.");
           // Click the submit button if all validations pass
           ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn);
       } else {
           System.out.println("Form submission blocked due to validation failure.");
       }
       
       

       // Close the browser
       Thread.sleep(2000);
       driver.quit();
   }

   // Function to validate mobile number (only digits and must be 10 characters)
   public static boolean isValidMobile(String mobile) {
       // Check if the mobile number contains exactly 10 digits and no letters
       return mobile.matches("\\d{10}");
   }

   // Function to validate email address using regex
   public static boolean isValidEmail(String email) {
       // Regular expression for validating an email address
       String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
       Pattern pat = Pattern.compile(emailRegex);
       if (email == null)
           return false;
       return pat.matcher(email).matches();
   }
      
      
      
      

    }
	

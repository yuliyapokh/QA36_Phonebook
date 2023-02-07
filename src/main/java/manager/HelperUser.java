package manager;

import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        click(By.cssSelector("a[href='/login']"));
    }

    public void fillLoginRegistrationForm(String email,String password){
        // for email
        type(By.name("email"),email);
        // for password
        type(By.name("password"),password);

    }


    public void fillLoginRegistrationForm(User user){
        // for email
        type(By.name("email"),user.getEmail());
        // for password
        type(By.name("password"),user.getPassword());

    }

    public void submitLogin(){
        click(By.cssSelector("[name='login']"));


    }

    public boolean isLogged() {
//        try {
//            wd.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
//            return true;
//        }catch (Exception e){
//            return false;
//        }
        List<WebElement> list  = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        // List<WebElement> list  = wd.findElements(By.xpath("//button"));
        return list.size() > 0;
    }


    //List<WebElement> list  = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        //return list.size() > 0;


    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public boolean isErrorMessageDisplayedOld(String message) {
        Alert alert = wd.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
        return text.contains(message);
    }


    public boolean isErrorMessageDisplayed(String message) {
        //Alert alert = wd.switchTo().alert();

       Alert alert =  new WebDriverWait(wd, Duration.ofSeconds(5))
                .until(ExpectedConditions.alertIsPresent());


        String text = alert.getText();
        System.out.println(text);


        alert.accept();

        return text.contains(message);
    }


    public void submitRegistration() {
        click(By.cssSelector("[name='registration']"));
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}

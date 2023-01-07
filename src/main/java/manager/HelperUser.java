package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase
{

    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm(){
        click(By.cssSelector(""));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        //for email
        type(By.cssSelector(""), email);
        //for pass
        type(By.cssSelector(""), password);

    }

    public void submitLoginAndRegistration(){
        click(By.cssSelector(""));

    }
}

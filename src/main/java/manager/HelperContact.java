package manager;

import model.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Type;
import java.util.List;

public class HelperContact extends HelperBase {
    public HelperContact(WebDriver wd) {
        super(wd);
    }


    public void openContactForm() {
        pause(3000);
        click(By.cssSelector("[href='/add']"));

    }

    public void fillContactForm(Contact contact) {
        type(By.cssSelector("input[placeholder='Name']"), contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"), contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"), contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"), contact.getDescription());

    }

    public void submitContact() {
        click(By.xpath("//b[text()='Save']"));
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for (WebElement el : list) {
            if (el.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for (WebElement el : list) {
            if (el.getText().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSavePresent() {

        return wd.findElements(By.xpath("//b[text()='Save']")).size() > 0;

    }

    public boolean isMessageErrorPresent(String message) {

        Alert alert = wd.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);

        alert.accept();

        return text.contains(message);
    }

    public boolean isMessageErrorPresentForEmail(String message) {
        if (wd.findElement(By.cssSelector("input[placeholder='email']")) != null) {
            Alert alert = wd.switchTo().alert();
            String text = alert.getText();
            System.out.println(text);

            alert.accept();
            return text.contains(message);
        }
        return false;
    }

    public boolean isAddPageStillDisplayed() {
        return wd.findElements(By.cssSelector("a.active[href='/add']")).size()>0;
    }

    public boolean isContactAddedByEmail(String email) {
        List<WebElement> lis = wd.findElements(By.cssSelector(".contact-item_card__2SOIM"));
        for (WebElement el:lis){
            el.click();
            String text = wd.findElement(By.cssSelector(".contact-item-detailed_card__50dTS")).getText();
            if(text.contains(email)){
                return true;
            }
        }
        return false;
    }
}


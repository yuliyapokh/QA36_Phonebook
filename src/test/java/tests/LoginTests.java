package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void loginSuccess() {

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("email", "pass");
        app.getHelperUser().submitLoginAndRegistration();

    }

    @Test
    public void loginWrongEmail() {

    }

    @Test
    public void loginWrongPassword() {

    }

    @Test
    public void loginUnregisterUser() {

    }
}

package tests;

import manager.ListenerTNG;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ListenerTNG.class)

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logout();
        logger.info("Logout success");
        }

        @Test
        public void loginSuccessNew () {
        logger.info("Login with valid data: pokh@1i.ua  &  Yyp12345!");

            app.getHelperUser().openLoginRegistrationForm();
            app.getHelperUser().fillLoginRegistrationForm("pokh@1i.ua", "Yyp12345!");
            app.getHelperUser().submitLogin();
            Assert.assertTrue(app.getHelperUser().isLogged());
            logger.info("Test success");

        }
        @Test
        public void loginWrongEmail () {
            app.getHelperUser().openLoginRegistrationForm();
            logger.info("Login with valid data: pokh1i.ua  &  Yyp12345!");
            app.getHelperUser().fillLoginRegistrationForm("pokh1i.ua", "Yyp12345!");
            app.getHelperUser().submitLogin();
            Assert.assertFalse(app.getHelperUser().isLogged());
            Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
            logger.info("Test success");

        }
        @Test
        public void loginWrongPassword () {
            app.getHelperUser().openLoginRegistrationForm();
            app.getHelperUser().fillLoginRegistrationForm("pokh@1i.ua", "yp12345");
            app.getHelperUser().submitLogin();
            Assert.assertFalse(app.getHelperUser().isLogged());
            Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
            logger.info("Login with valid data: pokh@1i.ua  &  yp12345");
        }
        @Test
        public void loginUnregisterUser () {
            app.getHelperUser().openLoginRegistrationForm();
            app.getHelperUser().fillLoginRegistrationForm("gigi@gmail.com", "Ss12345$");
            app.getHelperUser().submitLogin();
            Assert.assertFalse(app.getHelperUser().isLogged());
            Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
            logger.info("Login with valid data: gigi@gmail.com  &  Ss12345$");

        }
    }

package tests;

import manager.DataProviderUser;
import manager.ListenerTNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(ListenerTNG.class)

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged())
            app.getHelperUser().logout();
        logger.info("Logout success");
    }

    ///////////////////////////////////////////

    @DataProvider
    public Iterator<Object[]> loginData(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"pokh@1i.ua","Yyp12345!"});
        list.add(new Object[]{"pokh@1i.ua","Yyp12345!"});
        list.add(new Object[]{"pokh@1i.ua","Yyp12345!"});

        return list.iterator();

    }
    @Test(dataProvider = "loginData")
    public void loginSuccess(String email, String password) {
        logger.info("Login with valid data: pokh@1i.ua  &  Yyp12345!");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");

    }

    @Test (dataProvider = "loginDataCls",dataProviderClass = DataProviderUser.class)
    public void loginSuccessDP(String email, String password){

        logger.info("Login with valid data :  email: "+email +" & password: "+password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Tests success");


    }
    @Test(invocationCount = 2)
    public void loginSuccess1(){

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("pokh@1i.ua","Yyp12345!");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    @Test (dataProvider ="loginDataUser",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user){

        logger.info("Tests start with user model ---" +user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }
    @Test (dataProvider ="loginDataUserFromFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelFromFile(User user){

        logger.info("Tests start with user model ---" +user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());

    }

    /////////////////////////////////////////////////

    @Test(groups = {"smoke"})
    public void loginWrongEmail() {
        app.getHelperUser().openLoginRegistrationForm();
        logger.info("Login with invalid data: pokh1i.ua  &  Yyp12345!");
        app.getHelperUser().fillLoginRegistrationForm("pokh1i.ua", "Yyp12345!");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test success");

    }

    @Test
    public void loginWrongPassword() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("pokh@1i.ua", "yp12345");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Login with valid data: pokh@1i.ua  &  yp12345");
    }

    @Test
    public void loginUnregisterUser() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("gigi@gmail.com", "Ss12345$");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Login with valid data: gigi@gmail.com  &  Ss12345$");

    }
}

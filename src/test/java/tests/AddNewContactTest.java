package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged())
            app.getHelperUser().login(User.builder().email("pokh@1i.ua").password("Yyp12345!").build());

    }

    @Test
    public void addContactTestSuccessAllFields(){

        Random random = new Random();
        int i= random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Henri"+i)
                .lastName("Petr")
                .phone("0888976757"+i)
                .email("petr"+1+"@i.ua")
                .address("Vienna")
                .description("friend")
                .build();


        app.getHelperContact().openContactForm();

        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }
    @Test(groups = {"task"})
    public void addContactTestSuccessRequiredFields() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Yuli" + i)
                .lastName("Poh")
                .phone("0888976757" + i)
                .email("petr" + 1 + "@i.ua")
                .address("LA")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));


    }

@Test(groups = {"smoke"})
    public void addContactTestFailWithoutName() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .lastName("Poh")
                .phone("0888976757" + i)
                .email("petr" + 1 + "@i.ua")
                .address("LA")
                .description("OK")
                .build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // Assert.assertTrue(app.getHelperContact().isSavePresent());
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());


    }

    @Test
    public void addContactTestFailWithoutEmail() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Juli"+i)
                .lastName("Poh")
                .phone("0888976757" + i)
                .address("LA")
                .description("OK")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        Assert.assertTrue(app.getHelperContact().isSavePresent());

    }

    @Test
    public void addContactTestFailWithFailPhone() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Juli"+i)
                .lastName("Poh")
                .phone("08889")
                .email("petr" + 1 + "@i.ua")
                .address("LA")
                .description("OK")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        app.getHelperContact().isMessageErrorPresent("Phone not valid: Phone number " +
                                                          "must contain only digits! And length min 10, max 15!");



    }

    @Test
    public void addContactTestFailWithFailEmail() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Juli"+i)
                .lastName("Poh")
                .phone("08889")
                .email("petr" + 1 + "@i.ua")
                .address("LA")
                .description("OK")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        app.getHelperContact().isMessageErrorPresentForEmail("Email not valid: должно иметь формат адреса электронной почты Phone not valid: " +
                "Phone number must contain only digits! And length min 10, max 15!");

    }

    @Test
    public void  addNewContactWrongEmail(){

        Contact contact = Contact.builder()
                .name("John")
                .lastName("Wick")
                .address("NY")
                .phone("11111234567")
                .email("johnmail.ru")
                .description("wrong Last name").build();
        System.out.println(contact.toString());

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submitContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Email not valid: must be a well-formed email address"));

    }



    @Test
    public void addContactTestFailWithoutAddress() {

        Random random = new Random();
        int i = random.nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Juli"+i)
                .lastName("Poh")
                .phone("0888976757" + i)
                .email("petr" + 1 + "@i.ua")
                .description("OK")
                .build();

        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        Assert.assertTrue(app.getHelperContact().isSavePresent());

    }

    }
package io.selendroid.demo.twitter;

import io.selendroid.client.SelendroidDriver;
import io.selendroid.common.SelendroidCapabilities;
import io.selendroid.standalone.SelendroidConfiguration;
import io.selendroid.standalone.SelendroidLauncher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;

public class TwitterTest {
    private SelendroidLauncher selendroidServer = null;
    private WebDriver driver = null;
    //Change user and password for specific users
    private String user = "BillyManavis";
    private String password = "123456YouAreABitch";
    private String loginpage = "https://mobile.twitter.com/login";
    private String signuppage = "http://mobile.twitter.com/signup";
    private String mainpage = "https://mobile.twitter.com/";

    public void login(){                                                        //Login method. NOT A TEST
        driver.get(loginpage);
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement userName =                                                   //Find user name text box
                driver.findElement(By.name("session[username_or_email]"));
        userName.sendKeys(user);                                                //Type the user
        WebElement passwd =                                                     //Find password text box
                driver.findElement(By.name("session[password]"));
        passwd.sendKeys(password);                                              //Type the password
        WebElement submit =                                                     //Find the login button
                driver.findElements(By.tagName("button"))
                        .stream()
                        .filter(b -> "Log in".equals(b.getAttribute("value")))
                        .findFirst().get();
        submit.click();                                                         //Click & wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
    }

    @Test
    public void updateBio() {                                                   //Update the bio of a user
        String biouptext = "Software Engineering";                              //The Bio update text
        login();
        driver.get(mainpage+user);                                              //Go to the profile & wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement editProfile = driver.findElement(By.className("MmJh82_T"));  //Find edit profile button
        editProfile.click();                                                    //Click it
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement bioTextArea =                                                //Find bio text box
                driver.findElements(By.tagName("textarea"))
                        .stream()
                        .filter(b -> "More about you".equals(b.getAttribute("placeholder")))
                        .findFirst().get();
        bioTextArea.clear();                                                    //Clear previous bio
        bioTextArea.sendKeys(biouptext);                                        //Send new bio
        WebElement saveButton = driver.findElement(By.className("SpbPGaHr"));   //Find save button
        saveButton.click();                                                     //Click & wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement bio = driver.findElement(By.className("EcSZ4Fq3"));          //Find bio element
        String sbio = bio.getText();                                            //Get the text of bio
        if (sbio.equals(biouptext))                                             //Perform equality check
            System.out.println("Bio has been successfully updated.");
        else
            System.out.println("Bio did not update successfully.");
    }

    @Test
    public void search() {                                                      //Search for a keyword
        String keyword = "Tesla";
        login();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement searchArea =                                                 //Find search area
                driver.findElements(By.tagName("a"))
                        .stream()
                        .filter(b -> "Search".equals(b.getAttribute("aria-label")))
                        .findFirst().get();
        searchArea.click();                                                     //Click and wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement searchBox =                                                  //Find search box
                driver.findElements(By.tagName("input"))
                        .stream()
                        .filter(b -> "Search Twitter".equals(b.getAttribute("placeholder")))
                        .findFirst().get();
        searchBox.sendKeys(keyword);                                            //Type the keyword
        searchBox.click();                                                      //Click and wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        String result =                                                         //Get the text of the search box
                driver.findElements(By.tagName("input"))
                        .stream()
                        .filter(b -> "Search Twitter".equals(b.getAttribute("placeholder")))
                        .findFirst().get().getAttribute("value");
        if (keyword.equals(result)){                                            //Perform equality check
            System.out.println("Successful search.");
        }
        else {
            System.out.println("Search failed");
        }
    }

    @Test
    public void postTweet() {                                                   //Post a tweet
        String tweet = "Hopefully my last test tweet";
        login();
        WebElement tweetButton = driver.findElement(By.className("_132qLRA5")); //Find tweet button
        tweetButton.click();                                                    //Click and wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement tweetArea =                                                  //Find Tweet area
                driver.findElements(By.tagName("textarea"))
                        .stream()
                        .filter(b -> "What's happening?".equals(b.getAttribute("placeholder")))
                        .findFirst().get();
        tweetArea.sendKeys(tweet);                                              //Type the tweet
        WebElement tweetBut =                                                   //Find the new tweet button
                driver.findElements(By.tagName("button"))
                        .stream()
                        .filter(b -> "tweet-button".equals(b.getAttribute("data-testid")))
                        .findFirst().get();
        tweetBut.click();                                                       //Click and wait a bit
       try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
       try {
           WebElement tweetElement =
                   driver.findElements(By.tagName("span"))
                           .stream()
                           .filter(b -> tweet.equals(b.getText()))
                           .findFirst().get();
           System.out.println("Successful tweet.");
       }
       catch (NoSuchElementException nsee) {
           System.out.println("Tweet post failed");
       }
    }

    @Test
    public void followUser() {                                                      //Follow a user
        login();
        String userid = "elonmusk";
        driver.get(mainpage+userid);                                                //Profile page of the user
        try { Thread.sleep(4000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement followButton =                                                   //Find follow button
                driver.findElements(By.tagName("button"))
                        .stream()
                        .filter(b -> "44196397-follow".equals(b.getAttribute("data-testid")))
                        .findFirst().get();
        followButton.click();                                                       //Click the button and wait
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        try {                                          //Try to find the unfollow button that should have been created
            WebElement unfollowButton =
                    driver.findElements(By.tagName("button"))
                            .stream()
                            .filter(b -> "44196397-unfollow".equals(b.getAttribute("data-testid")))
                            .findFirst().get();
            System.out.println("Following was successful");                         //No exception = successful following
        }
        catch (NoSuchElementException nee) {
            System.out.println("Following was unsuccessful");
        }
    }

    @Test
    public void signupWithInvalidPhoneNumber() {                                //Signup with invalid phone number test
        String name = "BillyGrande";
        String phone= "123456";
        String correct_hint = "Please enter a valid phone number.";
        driver.get(signuppage);                                                 //Get to signup page
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement nameTextArea = driver.findElement(By.id("oauth_signup_client_fullname"));      //Find name text area
        nameTextArea.sendKeys(name);                                            //Type the name
        WebElement phoneTextArea = driver.findElement(By.id("oauth_signup_client_phone_number")); //Find phone text area
        phoneTextArea.sendKeys(phone);                                          //Type the phone
        WebElement signupButton =                                               //Find signup button
                driver.findElements(By.tagName("input"))
                        .stream()
                        .filter(b -> "Sign up".equals(b.getAttribute("value")))
                        .findFirst().get();
        signupButton.click();                                                   //Click and wait a bit
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        String hint =                                                           //Find the hint about the wrong number
                driver.findElements(By.tagName("div"))
                        .stream()
                        .filter(b -> "hint".equals(b.getAttribute("class")))
                        .findFirst().get().getText();
        if (hint.equals(correct_hint)){                                         //Perform equality check
            System.out.println("Successful hint.");
        }
        else {
            System.out.println("Unsuccessful hint");
        }
    }

    @Before
    public void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();

        selendroidServer = new SelendroidLauncher(config);
        selendroidServer.launchSelendroid();

        DesiredCapabilities caps = SelendroidCapabilities.android();

        driver = new SelendroidDriver(caps);
    }

    @After
    public void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
}
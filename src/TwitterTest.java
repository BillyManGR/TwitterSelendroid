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

    public void login(){
        driver.get("https://mobile.twitter.com/login");
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement userName =
                driver.findElement(By.name("session[username_or_email]"));
        userName.sendKeys("BillyManavis");
        WebElement passwd =
                driver.findElement(By.name("session[password]"));
        passwd.sendKeys("123456YouAreABitch");
        WebElement submit =
                driver.findElements(By.tagName("button"))
                        .stream()
                        .filter(b -> "Log in".equals(b.getAttribute("value")))
                        .findFirst().get();
        submit.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
    }
/*
    @Test
    public void updateBio() {
        login();
        driver.get("http://mobile.twitter.com/BillyManavis");       //System.out.println("Loaded profile");
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement editProfile =
                driver.findElement(By.className("MmJh82_T"));       //System.out.println("Found edit profile button");
        editProfile.click();            //System.out.println("Clicked edit profile button");
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement bioTextArea =
                driver.findElements(By.tagName("textarea"))
                        .stream()
                        .filter(b -> "More about you".equals(b.getAttribute("placeholder")))
                        .findFirst().get();         //System.out.println("Found text area");
        bioTextArea.clear();
        bioTextArea.sendKeys("Grande bitches!!");   //System.out.println("Sent new bio");
        WebElement saveButton =
                driver.findElement(By.className("SpbPGaHr"));
        saveButton.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement bio =
                driver.findElement(By.className("EcSZ4Fq3"));
        String sbio = bio.getText();
        if (sbio.equals("Grande bitches!!"))
            System.out.println("Bio has been successfully updated.");
        else
            System.out.println("Bio did not update successfully.");
    }

    @Test
    public void search() {
        String keyword = "Tesla";
        login();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement searchArea =
                driver.findElements(By.tagName("a"))
                        .stream()
                        .filter(b -> "Search".equals(b.getAttribute("aria-label")))
                        .findFirst().get();
        System.out.println("Found search area");
        searchArea.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement searchBox =
                driver.findElements(By.tagName("input"))
                        .stream()
                        .filter(b -> "Search Twitter".equals(b.getAttribute("placeholder")))
                        .findFirst().get();
        searchBox.sendKeys(keyword);
        System.out.println("Sent keyword");
        searchBox.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        String result =
                driver.findElements(By.tagName("input"))
                        .stream()
                        .filter(b -> "Search Twitter".equals(b.getAttribute("placeholder")))
                        .findFirst().get().getAttribute("value");
        System.out.println("Found result");
        if (keyword.equals(result)){
            System.out.println("Successful search.");
        }
        else {
            System.out.println("Search failed");
        }
    }

    @Test
    public void writetweet() {
        String tweet = "Testing selendroid with my team ;) Test 2";
        login();
        WebElement tweetButton = driver.findElement(By.className("_132qLRA5"));
        tweetButton.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        WebElement searchArea =
                driver.findElements(By.tagName("textarea"))
                        .stream()
                        .filter(b -> "What's happening?".equals(b.getAttribute("placeholder")))
                        .findFirst().get();
        System.out.println("Found search area");
        searchArea.sendKeys(tweet);
        WebElement tweetBut =
                driver.findElements(By.tagName("button"))
                        .stream()
                        .filter(b -> "tweet-button".equals(b.getAttribute("data-testid")))
                        .findFirst().get();
        tweetBut.click();
        System.out.println("Clicked tweet button");
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }*/
        /*WebElement firstTweetRow =
                driver.findElements(By.tagName("div"))
                .stream()
                .filter(b -> "tweet".equals(b.getAttribute("data-testid")))
                .findFirst().get();
        System.out.println("Found firstTweetRow");
        WebElement firstTweet = firstTweetRow.findElement(By.className("_2gpfhfZk"));
        System.out.println("Found firstTweet");
        WebElement div1 = firstTweet.findElements(By.tagName("div")).
                stream().
                findFirst().
                get();
        System.out.println("Found div1");
        String res = div1.findElement(By.tagName("span")).findElement(By.tagName("h1")).getText();
        System.out.println("Found res: "+res);*/
     /*   String res = driver.findElement(By.className("Fe7u13Lt")).getText();
        if (res.equals("Your Tweet was posted!")){
            System.out.println("Successful tweet.");
        }
        else {
            System.out.println("Tweet post failed");
        }
    }*/

    /*@Test
    public void followUser() {
        login();
        String userid = "elonmusk";
        driver.get("http://mobile.twitter.com/"+userid);       //System.out.println("Loaded profile");
        try { Thread.sleep(4000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        System.out.println("Loaded website");
        WebElement followButton =
                driver.findElements(By.tagName("button"))
                        .stream()
                        .filter(b -> "44196397-follow".equals(b.getAttribute("data-testid")))
                        .findFirst().get();
        System.out.println("Found follow area");
        followButton.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        try {
            WebElement unfollowButton =
                    driver.findElements(By.tagName("button"))
                            .stream()
                            .filter(b -> "44196397-unfollow".equals(b.getAttribute("data-testid")))
                            .findFirst().get();
            System.out.println("Following was successful");
        }
        catch (NoSuchElementException nee) {
            System.out.println("Following was unsuccessful");
        }
    }*/

    @Test
    public void signup() {
        String name = "BillyGrande";
        String phone= "123456";
        driver.get("http://mobile.twitter.com/signup");
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        System.out.println("Loaded website");
        WebElement nameTextArea = driver.findElement(By.id("oauth_signup_client_fullname"));
        nameTextArea.sendKeys(name);
        WebElement phoneTextArea = driver.findElement(By.id("oauth_signup_client_phone_number"));
        phoneTextArea.sendKeys(phone);
        WebElement signupButton =
                driver.findElements(By.tagName("input"))
                        .stream()
                        .filter(b -> "Sign up".equals(b.getAttribute("value")))
                        .findFirst().get();
        signupButton.click();
        try { Thread.sleep(2000); } catch(InterruptedException ie) { Toolkit.getDefaultToolkit().beep(); }
        String hint =
                driver.findElements(By.tagName("div"))
                        .stream()
                        .filter(b -> "hint".equals(b.getAttribute("class")))
                        .findFirst().get().getText();
        if (hint.equals("Please enter a valid phone number.")){
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
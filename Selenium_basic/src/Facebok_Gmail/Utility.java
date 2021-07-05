/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebok_Gmail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;

/**
 *
 * @author giaki
 */
public class Utility {

    private WebDriver driver;
    private Actions actions;
    private Thread thread;
    private FileManage file;
    private String pathChrome;
    private boolean islogin;

    public boolean isIslogin() {
        return islogin;
    }

    public Utility(String pathChrome) {
        this.pathChrome = pathChrome;
        thread = new Thread();
        islogin = false;
    }

    public Utility(String pathChrome, String fileName) {
        this.pathChrome = pathChrome;
        thread = new Thread();
        file = new FileManage(fileName);
        islogin = false;
    }

    public void goToFacebook() {
        System.setProperty("webdriver.chrome.driver", pathChrome);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        String url = "https://www.facebook.com/";
        driver.get(url);
        System.out.println(driver.getCurrentUrl());
    }

    public void closeDriver() {
        try {
            driver.close();
            islogin = false;
            System.out.println("Driver is close!");
        } catch (Exception e) {
            System.out.println("Driver was close!");
        }
    }

    public void logiFacebook(String username, String password) {
        System.out.println("Login.....");
        try {
            goToFacebook();
            thread.sleep(3000);
            WebElement elementUsername = driver.findElement(By.id("email"));
            WebElement elementPassword = driver.findElement(By.id("pass"));
            WebElement elementButton = driver.findElement(By.name("login"));
            Action inputUsername = actions.moveToElement(elementUsername)
                    .click()
                    .sendKeys(elementUsername, username)
                    .build();
            Action inputPassword = actions.moveToElement(elementPassword)
                    .click()
                    .sendKeys(elementPassword, password)
                    .build();
            Action clickButtonLogin = actions.moveToElement(elementButton)
                    .click()
                    .build();
            inputUsername.perform();
            inputPassword.perform();
            clickButtonLogin.perform();
            thread.sleep(6000);
            driver.get("https://www.facebook.com/me/");
            thread.sleep(2000);
            WebElement body = driver.findElement(By.tagName("body"));
            Action clickBody = actions.moveToElement(body)
                    .click()
                    .build();
            clickBody.perform();
            thread.sleep(3000);
            WebElement elementNameProfile = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/span/h1"));
            System.out.println("Success!");
            System.out.println("Hello " + elementNameProfile.getText());
            islogin = true;
        } catch (InterruptedException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Login fail!!");
            closeDriver();
        }
    }

    public void getListFriends(String username, String password) {
        System.out.println("Waiting....");
        try {
            if (islogin) {
                closeDriver();
            }
            logiFacebook(username, password);
            thread.sleep(10000);
            WebElement elementButtonFriend = driver.findElement(By.xpath("//a[3]/div"));
            Action clickButtonFriend = actions.moveToElement(elementButtonFriend)
                    .click()
                    .build();
            clickButtonFriend.perform();
            thread.sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("(async (sleepDuration = 2000) => {\n"
                    + "  let previousHeight;\n"
                    + "  let newHeight = document.body.scrollHeight;\n"
                    + "  do {\n"
                    + "    previousHeight = newHeight;\n"
                    + "    window.scrollTo(0, previousHeight);\n"
                    + "    await new Promise(resolve => setTimeout(resolve, sleepDuration));\n"
                    + "    newHeight = document.body.scrollHeight;\n"
                    + "  } while (previousHeight !== newHeight);\n"
                    + "})();");
            thread.sleep(5000);
            List<WebElement> last_elementUrls = driver.findElements(By.xpath("//a[@class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl gmql0nx0 gpro0wi8']"));
            do {
                System.out.println("Scrolling.!");
                thread.sleep(3000);
                System.out.println("Scrolling..!");
                thread.sleep(3000);
                System.out.println("Scrolling...!");
                thread.sleep(4000);
                List<WebElement> new_elementUrls = driver.findElements(By.xpath("//a[@class='oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl gmql0nx0 gpro0wi8']"));
                if (last_elementUrls.size() == new_elementUrls.size()) {
                    break;
                }
                last_elementUrls = new_elementUrls;
            } while (true);

            Hashtable<String, String> datas = new Hashtable<String, String>();
            for (int i = 0; i < last_elementUrls.size(); i++) {
                try {
                    System.out.println("added " + last_elementUrls.get(i).getText() + " into file!");
                    thread.sleep(20);
                    datas.put(last_elementUrls.get(i).getText(), last_elementUrls.get(i).getAttribute("href"));
                } catch (Exception e) {
                    break;
                }
            }
            file.WriteFileHashTable(datas);
            System.out.println("Done!");
            closeDriver();
        } catch (InterruptedException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Something error! Try again!");
        }
    }

    public void showListFriends() {
        Hashtable datas = file.readFileHashtable();
        datas.forEach((t, u) -> {
            try {
                thread.sleep(20);
                System.out.println(t + " - " + u);
            } catch (InterruptedException ex) {
                Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        System.out.println("Total: " + datas.size() + " friends");
    }

    public void sendMessageForAllFriend(String username, String password, String[] message) {
        try {
            Hashtable<String, String> datas = file.readFileHashtable();
            Enumeration<String> enumeration = datas.keys();
            if (islogin) {
                closeDriver();
            }
            logiFacebook(username, password);
            thread.sleep(10000);
            System.out.println("Sending message.....");
            while (enumeration.hasMoreElements()) {
                try {
                    String name = enumeration.nextElement();
                    System.out.println("Sending for " + name);
                    driver.get(datas.get(name).replace("https://www.facebook.com/profile.php?id=", "https://www.facebook.com/").replace("https://www.facebook.com/", "https://www.facebook.com/messages/t/"));
                    thread.sleep(3000);
                    for (int i = 0; i < message.length; i++) {
                        WebElement elementInput = driver.findElement(By.xpath("//div[3]/div[2]/div/div/div/div/div/div[2]/div/div/div/div"));
                        Action senkeyMessage = actions.moveToElement(elementInput)
                                .click()
                                .sendKeys(message[i])
                                .sendKeys(Keys.ENTER)
                                .build();
                        senkeyMessage.perform();
                        thread.sleep(2000);
                    }
                    thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            closeDriver();
            System.out.println("Done!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Something error! try again!");
        }

    }

    public void InvitefriendsLikePage(String password, String username, String url, boolean isadminPage, int quantity) {
        try {
            if (islogin) {
                closeDriver();
            }
            logiFacebook(password, username);
            thread.sleep(10000);
            System.out.println("Inviting......");
            driver.get(url);
            thread.sleep(4000);
            WebElement elementButtonMenu;
            if (isadminPage) {
                elementButtonMenu = driver.findElement(By.xpath("//div[3]/div/div/div/div[2]/div/div/div[2]/div/div"));
            } else {
                elementButtonMenu = driver.findElement(By.cssSelector(".h676nmdw:nth-child(4) > .oajrlxb2 > .rq0escxv"));
            }
            Action clickButtonMenu = actions.moveToElement(elementButtonMenu)
                    .click()
                    .build();
            clickButtonMenu.perform();
            thread.sleep(4000);
            WebElement elementButtonInvite;
            if (isadminPage) {
                elementButtonInvite = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/div/div/div/div[3]/div[5]"));
            } else {
                elementButtonInvite = driver.findElement(By.cssSelector(".oajrlxb2:nth-child(5) .qzhwtbm6 > .d2edcug0"));
            }
            Action clickButtonInvite = actions.moveToElement(elementButtonInvite)
                    .click()
                    .build();
            clickButtonInvite.perform();
            thread.sleep(4000);
            List<WebElement> last_elementButtonInviteList = driver.findElements(By.cssSelector(".q5bimw55 > .j83agx80 > div > .oajrlxb2 > .ow4ym5g4"));
            int i = 0;
            while (i < last_elementButtonInviteList.size()) {
                while (i < last_elementButtonInviteList.size()) {
                    Action clickButtonInviteItem = actions.moveToElement(last_elementButtonInviteList.get(i))
                            .click()
                            .build();
                    clickButtonInviteItem.perform();
                    System.out.println(last_elementButtonInviteList.get(i).getText() + " clicked");
                    thread.sleep(1500);
                    List<WebElement> new_elementButtonInviteList = driver.findElements(By.cssSelector(".q5bimw55 > .j83agx80 > div > .oajrlxb2 > .ow4ym5g4"));
                    if (new_elementButtonInviteList.size() > last_elementButtonInviteList.size()) {
                        for (int j = last_elementButtonInviteList.size(); j < new_elementButtonInviteList.size(); j++) {
                            last_elementButtonInviteList.add(new_elementButtonInviteList.get(j));
                        }
                    }
                    if (last_elementButtonInviteList.size() == quantity) {
                        break;
                    }
                    i++;
                }
                thread.sleep(5000);
                List<WebElement> new_elementButtonInviteList = driver.findElements(By.cssSelector(".q5bimw55 > .j83agx80 > div > .oajrlxb2 > .ow4ym5g4"));
                if (new_elementButtonInviteList.size() > last_elementButtonInviteList.size()) {
                    for (int j = last_elementButtonInviteList.size(); j < new_elementButtonInviteList.size(); j++) {
                        last_elementButtonInviteList.add(new_elementButtonInviteList.get(j));
                    }
                }
                if (last_elementButtonInviteList.size() == quantity) {
                    break;
                }
            }
            thread.sleep(5000);
            WebElement elementButtonSend = driver.findElement(By.cssSelector(".rq0escxv:nth-child(2) > .rq0escxv > .oajrlxb2 > .rq0escxv"));
            Action clickButtonSend = actions.moveToElement(elementButtonSend)
                    .click()
                    .build();
            clickButtonSend.perform();
            thread.sleep(10000);
            closeDriver();
            System.out.println("Total: " + last_elementButtonInviteList.size());
            System.out.println("Done!!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Something error! Try again!");
        }
    }

    public void goToGmail() {
        closeDriver();
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", pathChrome);
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-blink-features=AutomationControlled");
//        options.addArguments("--disable-infobars");
//        options.addArguments("--disable-extensions");
//        options.addArguments("--profile-directory=Default");
//        options.addArguments("--incognito");
//        options.addArguments("--disable-plugins-discovery");
//        options.addArguments("--user-data-dir=C:\\Users\\giaki\\AppData\\Local\\Google\\Chrome\\User Data");
//        options.setExperimentalOption("useAutomationExtension", false);
//        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        actions = new Actions(driver);
        String url = "https://accounts.google.com/signin";
//        String url = "https://stackoverflow.com/users/login";
        driver.get(url);
        System.out.println(driver.getCurrentUrl());
    }

    public void loginToGmail(String email, String password) {
        try {
            goToGmail();
            thread.sleep(3000);
//            driver.findElement(By.xpath("//div[@id='openid-buttons']/button"))
//                    .click();
//            thread.sleep(3000);
            WebElement elementEmail = driver.findElement(By.xpath("//input[@type='email']"));
            WebElement elementNext = driver.findElement(By.xpath("//button[@type='button']/span[@class='VfPpkd-vQzf8d']"));
            Action inputElementEmail = actions.moveToElement(elementEmail)
                    .click()
                    .sendKeys(elementEmail, email)
                    .build();
            inputElementEmail.perform();
            thread.sleep(2000);
            Action clickElementNext = actions.moveToElement(elementNext)
                    .click()
                    .build();
            clickElementNext.perform();
            thread.sleep(4000);
            WebElement elementPassword = driver.findElement(By.xpath("//input[@name='password']"));
            Action inputElementPassword = actions.moveToElement(elementPassword)
                    .click()
                    .sendKeys(elementPassword, password)
                    .build();
            inputElementPassword.perform();
            thread.sleep(2000);
            elementNext = driver.findElement(By.xpath("//button[@type='button']/span[@class='VfPpkd-vQzf8d']"));
            clickElementNext = actions.moveToElement(elementNext)
                    .click()
                    .build();
            clickElementNext.perform();
            thread.sleep(4000);
            System.out.println("Done"); 
            closeDriver();
        } catch (InterruptedException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("Something error! try again!");
        }
    }
}

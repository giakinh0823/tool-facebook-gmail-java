/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebok_Gmail;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

/**
 *
 * @author giaki
 */
public class Tester {

    public static ArrayList<String> menu() {
        ArrayList<String> options = new ArrayList<>();
        options.add("Tutorial run tool!");
        options.add("Enter path chromedriver");
        options.add("Enter path file");
        options.add("Facebook-Enter password and username");
        options.add("Facebook-Login Facebook");
        options.add("Facebook-Get list friends facebook");
        options.add("Facebook-Show list friend on file");
        options.add("Facebook-Send message for all friends in file");
        options.add("Facebook-Invite friends to like the page");
        options.add("Gmail-Enter password and username");
        options.add("Gmail-login");
        return options;
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Manage manage = new Manage();
        do {
            int choice = menu.choice(menu());
            switch (choice) {
                case 1:
                    manage.tutorial();
                    break;
                case 2:
                    manage.addPathChrome();
                    break;
                case 3:
                    manage.enterFileName();
                    break;        
                case 4:
                    manage.adđUsernameAndPassword();
                    break;
                case 5:
                    manage.loginToFacebook();
                    break;
                case 6:
                    manage.getListFriends();
                    break;
                case 7:
                    manage.showListFriends();
                    break;
                case 8:
                    manage.sendMessageForAllFriends();
                    break;
                case 9:
                    manage.InvitefriendsLikePage();
                    break;
                case 10:
                    manage.enterUsernameAndPassswordGmail();
                    break;
                case 11:
                    manage.loginGmail();
                    break;
                case 0:
                    manage.end();
                    System.out.println("Bye");
                    break;
                default:
                    System.out.println("Please choose again");
                    break;
            }
            if (choice == 0) {
                break;
            }
        } while (true);
    }
}

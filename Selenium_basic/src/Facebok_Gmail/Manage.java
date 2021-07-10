/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebok_Gmail;

import java.util.List;

/**
 *
 * @author giaki
 */
public class Manage {

    private Inputter input = new Inputter();
    private Utility utility = null;
    private String username = null;
    private String password = null;
    private String usernameGmail = null;
    private String passwordGmail = null;
    private String pathChrome = null;
    private String fileName = null;

    public void tutorial() {
        System.out.println("------------------------------------");
        System.out.println("Step 1. Go to https://chromedriver.chromium.org/downloads");
        System.out.println("Step 2. Choose Chrome version 91!");
        System.out.println("Step 3. Copy the path you saved!");
        System.out.println("example: C:\\Users\\giaki\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.out.println("Step 4. Choose options 2");
        System.out.println("Step 5. Paste the link you copied to path chromedriver");
        System.out.println("Step 6. Create file txt and copy path");
        System.out.println("example: D:\\FPT UNIVERSITY\\STUDY\\KY 2\\PRO\\BIN\\Selenium\\Selenium_basic\\src\\Facebok_Gmail\\testList.txt");
        System.out.println("step 7. Paste the link you copied to file");
        System.out.println("------------------------------------");
    }

    public void addPathChrome() {
        pathChrome = input.inputString("Enter path chromedriver: ");
        utility = new Utility(pathChrome);
    }

    public void adđUsernameAndPassword() {
        username = input.inputStringNotSpace("Enter username: ").toLowerCase();
        password = input.inputString("Enter password: ");
    }
    
    public void enterFileName(){
        fileName = input.inputString("Enter path file: ");
        utility = new Utility(pathChrome,fileName);
    }

    public void loginToFacebook() {
        if (pathChrome == null) {
            System.out.println("Please enter Path chrome driver and filename");
            return;
        }
        if (username == null || password == null) {
            System.out.println("Please enter username and password!!");
            return;
        }
        if (utility.isIslogin()) {
            System.out.println("You was login!");
            return;
        }
        utility.logiFacebook(username, password);
    }

    public void getListFriends() {
        if (pathChrome == null) {
            System.out.println("Please enter Path chrome driver and filename");
            return;
        }
        if (fileName == null) {
             enterFileName();
        }
        if (username == null || password == null) {
            System.out.println("Please enter username and password!!");
            return;
        }
        utility.getListFriends(username, password);
    }

    public void showListFriends() {
        if (pathChrome == null) {
            System.out.println("Please enter Path chrome driver and filename");
            return;
        }
        if (fileName == null) {
            enterFileName();
        }
        utility.showListFriends();
    }

    public void sendMessageForAllFriends() {
        if (pathChrome == null) {
            System.out.println("Please enter Path chrome driver and filename");
            return;
        }
        if (fileName == null) {
            enterFileName();
        }
        if (username == null || password == null) {
            System.out.println("Please enter username and password!!");
            return;
        }
//        String message[] = {"Chào bạn",
//            "Bạn like fanpage này giúp mình với ạ!",
//            "https://www.facebook.com/105262485118977",
//            "Mình cảm ơn!"};
        FileManage fileManage = new FileManage();
        List<String> list = fileManage.readFileMessage("D:\\FPT UNIVERSITY\\STUDY\\KY 2\\PRO\\BIN\\Selenium\\Selenium_basic\\src\\Facebok_Gmail\\message.txt");
        utility.sendMessageForAllFriend(
                username,
                password,
                list);
    }

    public void InvitefriendsLikePage() {
        if (pathChrome == null) {
            System.out.println("Please enter Path chrome driver and filename");
            return;
        }
        if (username == null || password == null) {
            System.out.println("Please enter username and password!!");
            return;
        }
        String url = input.inputStringNotSpace("Enter url page: ");
        boolean isAdmin = input.inputBoolean("You have admin page(true/false): ");
        int quantity = input.inputInt("Number friends max(recommend-800-900): ");
        utility.InvitefriendsLikePage(username, password, url, isAdmin, quantity);
    }
    
    public void enterUsernameAndPassswordGmail(){
        usernameGmail = input.inputStringNotSpace("Enter username: ").toLowerCase();
        passwordGmail = input.inputString("Enter password: ");
    }
    
    public void loginGmail(){
        if (usernameGmail == null || passwordGmail == null) {
            System.out.println("Please enter username and password!!");
            return;
        }
        utility.loginToGmail(usernameGmail, passwordGmail);
    }

    public void end() {
        try {
            utility.closeDriver();
        } catch (Exception e) {
        }
        return;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebok_Gmail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giaki
 */
public class Inputter{

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public int inputInt(String message) {
        int number = 0;
        do {
            try { 
                System.out.print(message);
                try {
                    number = Integer.parseInt(bufferedReader.readLine().trim());
                } catch (IOException ex) {
                    Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
            }
        } while (true);
        return number;
    }
    
    public double inputDouble(String message, double min, double max) {
        if(min>max){
            double z=min;
            min=max;
            max=min;
        }
        double number = 0;
        do {
            try { 
                System.out.print(message);
                try {
                    number = Double.parseDouble(bufferedReader.readLine().trim());
                } catch (IOException ex) {
                    Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(number<min || number >max){
                    throw new NullPointerException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
            }
        } while (true);
        return number;
    }
    
    public String inputString(String message){
        String str=null;
        do {
            System.out.print(message);
            try {
                str = bufferedReader.readLine().trim();
            } catch (IOException ex) {
                Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(str.isEmpty() || str== null){
                System.out.println("Please input not empty!");
            }
        } while (str.isEmpty() || str== null);
        
        return str;
    }
    
    
    public String inputStringNotSpace(String message){
        String str=null;
        do {
            System.out.print(message);
            try {
                str = bufferedReader.readLine().trim();
            } catch (IOException ex) {
                Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(str.isEmpty() || str==null || str.indexOf(" ")!=-1){
                System.out.println("Please input not empty!");
            }
        } while (str.isEmpty() || str== null);
        
        return str;
    }
    
    
    public double inputDouble(String message) {
        double number = 0;
        do {
            try {
                System.out.print(message);
                try {
                    number = Double.parseDouble(bufferedReader.readLine().trim());
                } catch (IOException ex) {
                    Logger.getLogger(Inputter.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please input number!");
            }
        } while (true);
        return number;
    }
    
    public boolean inputBoolean(String message){
        boolean bo;
        do {
            try {
                System.out.print(message);
                bo = Boolean.parseBoolean(bufferedReader.readLine().trim());
                break;
            } catch (Exception e) {
                System.out.println("Please input true or false!");
            }
        } while (true);
        return bo;
    }
}

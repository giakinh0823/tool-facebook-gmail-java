/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebok_Gmail;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author giaki
 */
public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public int choice(ArrayList<String> options) {
        int d = 0;
        for (String option : options) {
            System.out.println((d + 1) + ". " + option);
            d++;
        }
        System.out.println("0. Exit");
        Inputter input = new Inputter();
        int choice = -1;
        do {
            choice = input.inputInt("Choose option: ");
        } while (choice < 0 || choice > options.size());
        return choice;
    }

    public int choice(Object options[]) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options.toString());
        }
        System.out.println("0. Exit");
        Inputter input = new Inputter();
        int choice = -1;
        do {
            choice = input.inputInt("Choose option: ");
        } while (choice < 0 || choice > options.length);
        return choice;
    }
}

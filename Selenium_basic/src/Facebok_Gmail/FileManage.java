/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facebok_Gmail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giaki
 */
public class FileManage {

    private String fileName;

    public FileManage() {
    }

    public FileManage(String fileName) {
        this.fileName = fileName;
    }

    public void WriteFileHashTable(Hashtable<String, String> datas) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            Enumeration<String> enumeration = datas.keys();
            while (enumeration.hasMoreElements()) {
                String name = enumeration.nextElement();
                fileWriter.write(name + " - " + datas.get(name) + "\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Cant not write file");
        }
    }

    public Hashtable<String, String> readFileHashtable() {
        Hashtable<String, String> datas = new Hashtable<String, String>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    StringTokenizer token = new StringTokenizer(line, "-");
                    try {
                        datas.put(token.nextToken().trim(), token.nextToken().trim());
                    } catch (NoSuchElementException e) {
                    }
                }
            } while (line != null);
            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
    
    
    public List<String> readFileMessage(String fileName) {
        List<String> datas = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            do {
                line = bufferedReader.readLine();
                if (line != null) {
                    try {
                        datas.add(line.trim());
                    } catch (NoSuchElementException e) {
                    }
                }
            } while (line != null);
            fileReader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datas;
    }
}

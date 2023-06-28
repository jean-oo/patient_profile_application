package ui;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main  {
    public static void main(String[] args) {
        new PatientProfileDisplay();
        try {
            new PatientProfileApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}




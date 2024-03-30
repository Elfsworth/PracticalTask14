package com.mycompany.practicaltask10_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileModifier {
    public static void main(String[] args) {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose your file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        int returnVal = fileChooser.showOpenDialog(null);
        if(returnVal != JFileChooser.APPROVE_OPTION) {
            System.out.println("Declined by user.");
            return;
        }

        File inputFile = fileChooser.getSelectedFile();
        
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            File outputFile = chooseOutputFile();
            writer = new BufferedWriter(new FileWriter(outputFile));
            
            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder reversedLine = new StringBuilder(line).reverse();
                writer.write(reversedLine.toString());
                writer.newLine();
            }
            
            System.out.println("File modding done. New file: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private static File chooseOutputFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose place to save");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int returnVal = fileChooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            System.out.println("Declined by user.");
            System.exit(0);
        }
        return null;
    }
}

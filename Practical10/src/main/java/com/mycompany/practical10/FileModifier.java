package com.mycompany.practical10;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

public class FileModifier {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setDialogTitle("Choose Input File");
        int inputResult = fileChooser.showOpenDialog(null);
        if (inputResult == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();

            fileChooser.setDialogTitle("Choose Gamma File");
            int gammaResult = fileChooser.showOpenDialog(null);
            if (gammaResult == JFileChooser.APPROVE_OPTION) {
                File gammaFile = fileChooser.getSelectedFile();

                fileChooser.setDialogTitle("Choose Output File");
                int outputResult = fileChooser.showSaveDialog(null);
                if (outputResult == JFileChooser.APPROVE_OPTION) {
                    File outputFile = fileChooser.getSelectedFile();

                    try {
                        byte[] gammaBytes = Files.readAllBytes(gammaFile.toPath());
                        byte[] inputBytes = Files.readAllBytes(inputFile.toPath());
                        byte[] outputBytes = new byte[inputBytes.length];

                        for (int i = 0; i < inputBytes.length; i++) {
                            outputBytes[i] = (byte) (inputBytes[i] ^ gammaBytes[i % gammaBytes.length]);
                        }
                        StringBuilder hexOutput = new StringBuilder();
                        for (int i = 0; i < outputBytes.length; i++) {
                            hexOutput.append(String.format("0x%02X", outputBytes[i]));
                            if (i < outputBytes.length - 1) {
                                hexOutput.append(", ");
                            }
                        }

                        Files.write(outputFile.toPath(), hexOutput.toString().getBytes());
                        JOptionPane.showMessageDialog(null, "Encryption completed successfully.");

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "An error occurred during encryption.");
                    }
                }
            }
        }
    }
}
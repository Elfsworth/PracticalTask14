package com.mycompany.practicaltask_12;

import com.mirea.kt.example.Message;
import java.awt.HeadlessException;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class MessageDeserializer {
    public static void main(String[] args) {
        try {
            // Десериализация объекта Message
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose file to deserialize");
            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToDeserialize = fileChooser.getSelectedFile();
                Message message;
                try (FileInputStream fileIn = new FileInputStream(fileToDeserialize); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    message = (Message) in.readObject();
                }

                System.out.println("Message deserialized:");
                System.out.println(message);

                // Выбор места для сохранения файла
                SwingUtilities.invokeLater(() -> {
                    JFileChooser saveFileChooser = new JFileChooser();
                    saveFileChooser.setDialogTitle("Choose place to save deserialized file");
                    int saveUserSelection = saveFileChooser.showSaveDialog(null);

                    if (saveUserSelection == JFileChooser.APPROVE_OPTION) {
                        File saveFile = saveFileChooser.getSelectedFile();
                        try (PrintWriter writer = new PrintWriter(saveFile + ".txt")) {
                            writer.println("Deserialized Message:");
                            writer.println(message.toString());
                            System.out.println("Result saved here: " + saveFile.getAbsolutePath() + ".txt");
                        } catch (IOException e) {
                        }
                    } else {
                        System.out.println("Saving declined by user.");
                    }
                });

            } else {
                System.out.println("Choosing of deserializing file was declined by user .");
            }

        } catch (HeadlessException | IOException | ClassNotFoundException e) {
        }
    }
}
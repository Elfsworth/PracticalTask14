package com.mycompany.practicaltask_14;

import java.util.concurrent.Semaphore;

public class StudentCafeteria {
    public static void main(String[] args) {
        Semaphore tableSemaphore = new Semaphore(2);

        for (int i = 1; i <= 7; i++) {
            int studentId = i;
            new Thread(() -> {
                try {
                    System.out.println("Student " + studentId + " is waiting for a free seat at the table.");
                    tableSemaphore.acquire();
                    Thread.sleep(3000);
                    
                    System.out.println("Student " + studentId + " started eating.");
                    Thread.sleep(3000);
                    
                    System.out.println("Student " + studentId + " finished eating and leaves the table.");
                    tableSemaphore.release();
                } catch (InterruptedException e) {
                }
            }).start();
        }
    }
}
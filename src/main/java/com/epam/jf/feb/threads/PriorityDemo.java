package com.epam.jf.feb.threads;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

public class PriorityDemo {
    public static void main(String... args) {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Clicker hi1 = new Clicker();
        Clicker hi2 = new Clicker();
        Clicker hi3 = new Clicker();
        Clicker hi4 = new Clicker();
        Clicker hi5 = new Clicker();
        Clicker lo1 = new Clicker();
        Clicker lo2 = new Clicker();
        Clicker lo3 = new Clicker();
        Clicker lo4 = new Clicker();
        Clicker lo5 = new Clicker();
        hi1.setPriority(Thread.MAX_PRIORITY);
        hi2.setPriority(Thread.MAX_PRIORITY);
        hi3.setPriority(Thread.MAX_PRIORITY);
        hi4.setPriority(Thread.MAX_PRIORITY);
        hi5.setPriority(Thread.MAX_PRIORITY);
        lo1.setPriority(Thread.MIN_PRIORITY);
        lo2.setPriority(Thread.MIN_PRIORITY);
        lo3.setPriority(Thread.MIN_PRIORITY);
        lo4.setPriority(Thread.MIN_PRIORITY);
        lo5.setPriority(Thread.MIN_PRIORITY);

        lo1.start();
        lo2.start();
        lo3.start();
        lo4.start();
        lo5.start();
        hi1.start();
        hi2.start();
        hi3.start();
        hi4.start();
        hi5.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        lo1.stopClick();
        lo2.stopClick();
        lo3.stopClick();
        lo4.stopClick();
        lo5.stopClick();
        hi1.stopClick();
        hi2.stopClick();
        hi3.stopClick();
        hi4.stopClick();
        hi5.stopClick();

        try {
            hi1.join();
            hi2.join();
            hi3.join();
            hi4.join();
            hi5.join();
            lo1.join();
            lo2.join();
            lo3.join();
            lo4.join();
            lo5.join();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        System.out.println("Low-priority thread1: " + lo1.getClick());
        System.out.println("Low-priority thread2: " + lo2.getClick());
        System.out.println("Low-priority thread3: " + lo3.getClick());
        System.out.println("Low-priority thread4: " + lo4.getClick());
        System.out.println("Low-priority thread5: " + lo5.getClick());
        System.out.println("High-priority thread1: " + hi1.getClick());
        System.out.println("High-priority thread2: " + hi2.getClick());
        System.out.println("High-priority thread3: " + hi3.getClick());
        System.out.println("High-priority thread4: " + hi4.getClick());
        System.out.println("High-priority thread5: " + hi5.getClick());

        double averageLo = getAverage(
                lo1.getClick(),
                lo2.getClick(),
                lo3.getClick(),
                lo4.getClick(),
                lo5.getClick());
        System.out.println(averageLo);

        double averageHi = getAverage(
                hi1.getClick(),
                hi2.getClick(),
                hi3.getClick(),
                hi4.getClick(),
                hi5.getClick());

        System.out.println(averageHi);

        System.out.println(Math.round(averageHi / averageLo * 100));
    }

    private static double getAverage(int... ints) {
        double avg = 0.0;
        for (int anInt : ints)
            avg += anInt;
        return avg / ints.length;
    }
}

@FieldDefaults(level = PRIVATE)
class Clicker extends Thread {
    @Getter
    int click;
    volatile boolean running = true;

    public void run() {
        while (running)
            click++;
    }

    public void stopClick() {
        running = false;
    }
}
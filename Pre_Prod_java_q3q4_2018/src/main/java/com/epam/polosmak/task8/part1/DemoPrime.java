package com.epam.polosmak.task8.part1;

import com.epam.polosmak.task8.part1.service.AbstractThreadService;
import com.epam.polosmak.task8.part1.service.CommonListThreadService;
import com.epam.polosmak.task8.part1.service.SeparateListsThreadService;

import java.util.Scanner;

public class DemoPrime {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter diapason of numbers: \nNumber from:");
        int from = scanner.nextInt();
        System.out.println("Number to:");
        int to = scanner.nextInt();
        System.out.println("Input number of threads:");
        int threads = scanner.nextInt();
        AbstractThreadService service = new CommonListThreadService(from, to, threads);
        System.out.println("Common Lists:\nThreads run by CommonList, search with Threads: ");
        service.startThreads();
        System.out.println("Threads run by CommonList,search with executor: ");
        service.startExecutorThreads();
        System.out.println("Separate lists:\nThreads run by SeparateList, search with Threads: ");
        service = new SeparateListsThreadService(from, to, threads);
        service.startThreads();
        System.out.println("Threads run by SeparateList, search with  executor: ");
        service.startExecutorThreads();
    }
}

package com.epam.polosmak.task5.part2.util;

import com.epam.polosmak.task5.part2.constants.constants;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd";
    private static final String DATE_TIME_REGEX = "(\\d{4})-(\\d{2})-(\\d{2})";

    public String getDirectoryPath() {
        String directory;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(constants.ENTER_DIRECTORY_PATH);
            if (!scanner.hasNext()) {
                scanner.next();
                System.out.println(constants.WRONG_DIRECTORY);
                continue;
            }
            directory = scanner.nextLine();
            if (!validateDirectory(directory)) {
                System.out.println(constants.WRONG_DIRECTORY);
                continue;
            }
            break;
        }
        return directory;
    }

    public int getSelect() {
        Scanner scanner = new Scanner(System.in);
        int select;
        while (true) {
            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println(constants.WRONG_INPUT);
                System.out.print("Wrong input, try again:");
                continue;
            }
            select = scanner.nextInt();
            if (select < 0 || select > 1) {
                System.out.println(constants.WRONG_INPUT);
                continue;
            }
            break;
        }
        return select;
    }

    public String getInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            if (!scanner.hasNext()) {
                scanner.next();
                System.out.println(constants.WRONG_INPUT);
                System.out.print("Try again:");
                continue;
            }
            input = scanner.nextLine();
            break;
        }
        return input;
    }

    public double getSize() {
        Scanner scanner = new Scanner(System.in);
        double size;
        while (true) {
            System.out.print(constants.INPUT_SIZE);
            if (!scanner.hasNextDouble()) {
                scanner.next();
                System.out.println(constants.WRONG_SIZE);
                System.out.print("Try again:");
                continue;
            }
            size = scanner.nextDouble();
            break;
        }
        return size;
    }

    public LocalDate getInputDateTime() {
        Scanner scanner = new Scanner(System.in);
        String dateTimeString;
        LocalDate dateTime;
        while (true) {
            System.out.print(constants.ENTER_DATE + " === " + DATE_TIME_FORMAT + ": ");
            if (!scanner.hasNextLine()) {
                scanner.next();
                System.out.println(constants.DATE_FORMAT_IS_WRONG);
                System.out.print("Try again:");
                continue;
            }
            dateTimeString = scanner.nextLine();
            if (!validateInputDate(dateTimeString)) {
                System.out.println(constants.DATE_FORMAT_IS_WRONG);
                System.out.print("Try again:");
                continue;
            }
            if ((dateTime = parseDateTime(dateTimeString)) == null) {
                continue;
            }
            break;
        }
        return dateTime;
    }

    private LocalDate parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
        return LocalDate.parse(dateTimeString, formatter);
    }

    private boolean validateDirectory(String path) {
        File dir = new File(path);
        return dir.exists();
    }

    private boolean validateInputDate(String date) {
        Pattern pattern = Pattern.compile(DATE_TIME_REGEX);
        Matcher mat = pattern.matcher(date);
        return mat.matches();
    }
}

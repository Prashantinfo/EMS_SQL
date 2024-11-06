package org.example;

import java.util.UUID;
import java.util.regex.Pattern;

public class utility {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$");

    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_DEPARTMENT_LENGTH = 30;
    private static final int MAX_POSITION_LENGTH = 30;
    private static final int MAX_EMAIL_LENGTH = 50;
    private static final int MAX_ADDRESS_LENGTH = 100;

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= MAX_NAME_LENGTH;
    }

    public static boolean isValidDepartment(String department) {
        return department != null && department.length() <= MAX_DEPARTMENT_LENGTH;
    }

    public static boolean isValidPosition(String position) {
        return position != null && position.length() <= MAX_POSITION_LENGTH;
    }



    public static boolean isValidEmail(String email) {
        return email != null && email.length() <= MAX_EMAIL_LENGTH && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidDate(String date) {
        return date != null && DATE_PATTERN.matcher(date).matches();
    }

    public static boolean isValidAddress(String address) {
        return address != null && address.length() <= MAX_ADDRESS_LENGTH;
    }

    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

}

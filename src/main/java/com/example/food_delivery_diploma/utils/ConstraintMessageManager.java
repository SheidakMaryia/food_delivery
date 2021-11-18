package com.example.food_delivery_diploma.utils;

public class ConstraintMessageManager {

    public static final String NOT_BLANK_ERROR = "Field must not be empty!";
    public static final String EMAIL_ERROR = "Enter a correct e-mail address";
    public static final String PASSWORD_ERROR = "You entered a simple password! " +
            "Password must contain a minimum of 6 characters, at least 1 uppercase letter, " +
            "1 lowercase letter, and 1 number with no spaces.";
    public static final String PICTURE_ERROR = "Enter a correct URL picture!";
    public static final String PHONE_NUMBER_ERROR = "Phone number cannot have less then 9 numbers";
    public static final String NAME_OF_COUNTRY_ERROR = "Name of country cannot have less then 3 and more then 16 letters";
    public static final String NAME_OF_CITY_ERROR = "Name of city cannot have less then 3 and more then 16 letters";
    public static final String NAME_OF_STREET_ERROR = "Name of street cannot have less then 3 and more then 16 letters";
    public static final String PRICE_ERROR = "Price cannot be negative or zero";

}

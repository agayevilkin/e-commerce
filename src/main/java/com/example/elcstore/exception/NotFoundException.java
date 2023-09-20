package com.example.elcstore.exception;

public class NotFoundException extends RuntimeException {
    public static final String BRAND_NOT_FOUND = "Brand not found!";
    public static final String CALL_TIME_INTERVAL_NOT_FOUND = "Call Time Interval not found!";
    public static final String CAMPAIGN_NOT_FOUND = "Campaign not found!";
    public static final String CATEGORY_NOT_FOUND = "Category not found!";
    public static final String COLOR_NOT_FOUND = "Color not found!";
    public static final String CONTACT_NOT_FOUND = "Contact not found!";
    public static final String DISCOUNT_NOT_FOUND = "Discount not found!";
    public static final String EVENT_NOT_FOUND = "Event not found!";
    public static final String HIGHLIGHT_NOT_FOUND = "Highlight not found!";
    public static final String HIGHLIGHT_DEFINITION_NOT_FOUND = "Highlight Definition not found!";
    public static final String IMAGE_NOT_FOUND = "Image not found!";
    public static final String PRODUCT_NOT_FOUND = "Product not found!";
    public static final String PRODUCT_OPTION_NOT_FOUND = "Product Option not found!";
    public static final String PRODUCT_COMMENT_NOT_FOUND = "Product Comment not found!";
    public static final String PRODUCT_IMAGE_DETAIL_NOT_FOUND = "Product Image Detail not found!";
    public static final String ORDER_NOT_FOUND = "Order not found!";
    public static final String ROLE_NOT_FOUND = "Role not found!";
    public static final String TECHNICAL_CHARACTERISTIC_NOT_FOUND = "Technical Characteristic not found!";
    public static final String TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND = "Technical Characteristic Title not found!";
    public static final String USER_NOT_FOUND = "User not found!";

    public NotFoundException(String message) {
        super(message);
    }
}

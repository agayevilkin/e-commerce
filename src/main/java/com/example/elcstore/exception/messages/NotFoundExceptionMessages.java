package com.example.elcstore.exception.messages;

import lombok.Getter;

@Getter
public enum NotFoundExceptionMessages {
    BRAND_NOT_FOUND("Brand not found!"),
    CALL_TIME_INTERVAL_NOT_FOUND("Call Time Interval not found!"),
    CAMPAIGN_NOT_FOUND("Campaign not found!"),
    CATEGORY_NOT_FOUND("Category not found!"),
    COLOR_NOT_FOUND("Color not found!"),
    CONTACT_NOT_FOUND("Contact not found!"),
    DISCOUNT_NOT_FOUND("Discount not found!"),
    EVENT_NOT_FOUND("Event not found!"),
    HIGHLIGHT_NOT_FOUND("Highlight not found!"),
    HIGHLIGHT_DEFINITION_NOT_FOUND("Highlight Definition not found!"),
    IMAGE_NOT_FOUND("Image not found!"),
    PRODUCT_NOT_FOUND("Product not found!"),
    PRODUCT_OPTION_NOT_FOUND("Product Option not found!"),
    PRODUCT_COMMENT_NOT_FOUND("Product Comment not found!"),
    PRODUCT_IMAGE_DETAIL_NOT_FOUND("Product Image Detail not found!"),
    ORDER_NOT_FOUND("Order not found!"),
    ROLE_NOT_FOUND("Role not found!"),
    TECHNICAL_CHARACTERISTIC_NOT_FOUND("Technical Characteristic not found!"),
    TECHNICAL_CHARACTERISTIC_TITLE_NOT_FOUND("Technical Characteristic Title not found!"),
    USER_NOT_FOUND("User not found!");

    private final String message;

    NotFoundExceptionMessages(String message) {
        this.message = message;
    }

}

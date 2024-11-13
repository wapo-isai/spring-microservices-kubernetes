package com.wapo.microservices.core.product.dto;

public class ProductDetailsDto {
    private String productName;
    private double productPrice;
    private String productDescription;
    private int productCalories;
    private String imageUrl;
    private OrderDto orderDto;
    private RatingDto ratingDto;

    public ProductDetailsDto(){}

    public ProductDetailsDto(String productName, double productPrice, String productDescription, int productCalories, String imageUrl, OrderDto orderDto, RatingDto ratingDto) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productCalories = productCalories;
        this.imageUrl = imageUrl;
        this.orderDto = orderDto;
        this.ratingDto = ratingDto;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductCalories() {
        return productCalories;
    }

    public void setProductCalories(int productCalories) {
        this.productCalories = productCalories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public RatingDto getRatingDto() {
        return ratingDto;
    }

    public void setRatingDto(RatingDto ratingDto) {
        this.ratingDto = ratingDto;
    }
}

package com.wapo.microservices.core.product.dto;

public class RatingDto {
    private String productId;
    private String author;
    private String subject;
    private String ratingContent;

    public RatingDto(){}
    public RatingDto(String productId, String author, String subject, String ratingContent) {
        this.productId = productId;
        this.author = author;
        this.subject = subject;
        this.ratingContent = ratingContent;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRatingContent() {
        return ratingContent;
    }

    public void setRatingContent(String ratingContent) {
        this.ratingContent = ratingContent;
    }
}

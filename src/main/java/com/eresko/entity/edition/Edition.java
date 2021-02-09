package com.eresko.entity.edition;

import java.io.Serializable;
import java.util.Objects;

public class Edition implements Serializable {

    private int id;
    private String title;
    private String publisher;
    private String description;
    private String avatarPath;
    private double price;
    private Category category;
    private Theme theme;

    public Edition() {
    }

    public Edition(String title, String publisher, String description, double price, Category category, Theme theme){
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.price = price;
        this.category = category;
        this.theme = theme;
    }

    public Edition(int id, String title, String publisher, String description, String avatarPath, double price, Category category, Theme theme) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.description = description;
        this.avatarPath = avatarPath;
        this.price = price;
        this.category = category;
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = Category.valueOf(category.toUpperCase().trim());
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = Theme.valueOf(theme.toUpperCase().trim());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edition edition = (Edition) o;
        return id == edition.id &&
                Double.compare(edition.price, price) == 0 &&
                title.equals(edition.title) &&
                publisher.equals(edition.publisher) &&
                description.equals(edition.description) &&
                Objects.equals(avatarPath, edition.avatarPath) &&
                category == edition.category &&
                theme == edition.theme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, publisher, description, avatarPath, price, category, theme);
    }

    @Override
    public String toString() {
        return "Edition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", description='" + description + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", theme=" + theme +
                '}';
    }
}

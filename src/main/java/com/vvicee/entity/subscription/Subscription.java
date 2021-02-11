package com.vvicee.entity.subscription;

import com.vvicee.entity.edition.Edition;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Subscription implements Serializable {
    private int id;
    private int userId;
    private int year;
    private Edition edition;
    private List<Month> months;

    public Subscription() {
    }

    public Subscription(int id, int userId, int year, List<Month> months) {
        this.id = id;
        this.userId = userId;
        this.year = year;
        this.months = months;
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return months.size() * edition.getPrice();
    }

    public String getMonthsAsString() {
        StringBuilder result = new StringBuilder();
        for (Month month : months) {
            result.append(String.valueOf(month).toLowerCase()).append(", ");
        }
        return result.toString().substring(0, result.length() - 2);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Month> getMonths() {
        return months;
    }

    public void setMonths(List<Month> months) {
        this.months = months;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id &&
                userId == that.userId &&
                year == that.year;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, edition.getId(), userId, year);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", editionId=" + edition.getId() +
                ", userId=" + userId +
                ", year=" + year +
                ", months=" + months +
                '}';
    }

}
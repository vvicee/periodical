package com.eresko.entity.subscription;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


public class Subscription implements Serializable {
    private int id;
    private int editionId;
    private int userId;
    private int year;
    private List<Month> months;

    public Subscription() {
    }

    public Subscription(int id, int editionId, int userId, int year, List<Month> months) {
        this.id = id;
        this.editionId = editionId;
        this.userId = userId;
        this.year = year;
        this.months = months;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEditionId() {
        return editionId;
    }

    public void setEditionId(int editionId) {
        this.editionId = editionId;
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
                editionId == that.editionId &&
                userId == that.userId &&
                year == that.year;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, editionId, userId, year);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", editionId=" + editionId +
                ", userId=" + userId +
                ", year=" + year +
                ", months=" + months +
                '}';
    }

}
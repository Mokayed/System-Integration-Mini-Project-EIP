package com.example.demo;

public class Food {
    private String name;
    private String food_category;
    private String description;

    public Food(String name, String food_category, String description) {
        this.name = name;
        this.food_category = food_category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFood_category() {
        return food_category;
    }

    public void setFood_category(String food_category) {
        this.food_category = food_category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", food_category='" + food_category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

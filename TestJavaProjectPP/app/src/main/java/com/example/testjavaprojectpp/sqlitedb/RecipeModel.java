package com.example.testjavaprojectpp.sqlitedb;

public class RecipeModel {
    private int id;
    private String recipeName;
    private String recipeIngredients;
    private String recipe;
    private String category;
    private String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "RecipeModel{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", recipeIngredients='" + recipeIngredients + '\'' +
                ", recipe='" + recipe + '\'' +
                ", category='" + category + '\'' +
                ", imageName='" + imageName + '\'' +
                '}';
    }

    public RecipeModel(int id, String recipeName, String recipeIngredients, String recipe, String category, String imageName) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.recipe = recipe;
        this.category = category;
        this.imageName = imageName;
    }
}

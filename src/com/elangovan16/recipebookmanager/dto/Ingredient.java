package com.elangovan16.recipebookmanager.dto;

public class Ingredient {
	private int ingredientId;
	private int recipeId;
	private String ingredientName;
	private double quantity;
	private String unit;

	public Ingredient(String ingredientName, double quantity, String unit) {
		this.ingredientName = ingredientName;
		this.quantity = quantity;
		this.unit = unit;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public double getQuantity() {
		return quantity;
	}

	public String getUnit() {
		return unit;
	}

}

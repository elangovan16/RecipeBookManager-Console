package com.elangovan16.recipebookmanager.dto;

import java.util.List;

public class Recipe {
	private String name;
	private List<Ingredient> ingredient;
	private List<String> instruction;

	public Recipe(String name, List<Ingredient> ingredients, List<String> instructions) {
		this.ingredient = ingredients;
		this.name = name;
		this.instruction = instructions;
	}

	public String getName() {
		return name;
	}

	public List<Ingredient> getIngredient() {
		return ingredient;
	}

	public List<String> getInstruction() {
		return instruction;
	}
}
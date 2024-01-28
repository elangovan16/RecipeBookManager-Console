package com.elangovan16.recipebookmanager.addrecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.elangovan16.recipebookmanager.dto.Ingredient;
import com.elangovan16.recipebookmanager.dto.Recipe;

public class AddRecipeView {
	private Scanner sc;
	private AddRecipeViewModel addRecipeViewModel;

	public AddRecipeView() {
		sc = new Scanner(System.in);
		addRecipeViewModel = new AddRecipeViewModel(this);
	}

	public void addNewRecipe() {

		System.out.print("Enter Recipe Name : ");
		String name = sc.nextLine();
		System.out.println();

		List<Ingredient> ingredients = getIngredients();
		List<String> instructions = getInstructions();

		Recipe recipe = new Recipe(name, ingredients, instructions);
		addRecipeViewModel.validateRecipe(recipe);
	}

	private List<Ingredient> getIngredients() {
		List<Ingredient> ingredients = new ArrayList<>();

		System.out.print("How Many Ingredients does Your Recipe have : ");
		int n = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < n; i++) {
			System.out.print("Enter the " + (i + 1) + " Ingredient Name : ");
			String ingredientName = sc.nextLine();

			System.out.print("Enter the " + (i + 1) + " Quantity : ");
			double quantity = sc.nextDouble();
			sc.nextLine();
			// Add input validation for quantity here if needed

			System.out.print("Enter the " + (i + 1) + " Unit : ");
			String unit = sc.nextLine();
			// Add input validation for unit here if needed

			Ingredient ingredient = new Ingredient(ingredientName, quantity, unit);
			ingredients.add(ingredient);
			System.out.println();
		}
		return ingredients;
	}

	private List<String> getInstructions() {
		List<String> instructions = new ArrayList<>();

		System.out.print("Enter instructions (type 'exit' to finish): ");
		String instruction = sc.nextLine();
		while (!instruction.equalsIgnoreCase("exit")) {
			instructions.add(instruction);
			System.out.print("Enter the next instruction : ");
			instruction = sc.nextLine();
			System.out.println();
		}
		return instructions;
	}

	public void onSuccess() {
		System.out.println("Added Successfully\n");
	}

}

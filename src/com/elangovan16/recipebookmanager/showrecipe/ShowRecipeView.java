package com.elangovan16.recipebookmanager.showrecipe;

import java.util.List;
import java.util.Scanner;

import com.elangovan16.recipebookmanager.dto.Ingredient;
import com.elangovan16.recipebookmanager.dto.Recipe;

public class ShowRecipeView {
	Scanner sc = new Scanner(System.in);
	private ShowRecipeViewModel showRecipeViewModel;

	public ShowRecipeView() {
		showRecipeViewModel = new ShowRecipeViewModel(this);
	}

	public void showRecipe() {
		List<String> recipe = showRecipeViewModel.getRecipeNames();

		if (recipe.size() > 0) {
			System.out.println("------------RecipeNames------------");
			for (String recipeName : recipe) {
				System.out.println("* " + recipeName);
			}
			System.out.println("------------------------------------");

			System.out.println("\nEnter recipe Name : ");

			String name = sc.nextLine();

			Recipe reci = showRecipeViewModel.getRecipe(name);
			if (reci != null) {

				int index = 1;
				System.out.println("------------Ingredients------------");
				for (Ingredient s : reci.getIngredient()) {
					System.out.println(index + ") " + s.getQuantity() + s.getUnit() + " " + s.getIngredientName());
					index++;
				}
				System.out.println("------------------------------------");
				index = 1;
				System.out.println("------------Instructions------------");
				for (String s : reci.getInstruction()) {
					System.out.println(index + ") " + s);
					index++;
				}
				System.out.println("------------------------------------");
			} else {
				System.out.println("Given Recipe Not-Found");
			}
		} else {
			System.out.println("RecipeBook is Empty");
		}
	}
}

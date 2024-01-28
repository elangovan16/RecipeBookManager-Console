package com.elangovan16.recipebookmanager;

import java.util.Scanner;

import com.elangovan16.recipebookmanager.addrecipe.AddRecipeView;
import com.elangovan16.recipebookmanager.removerecipe.RemoveRecipeView;
import com.elangovan16.recipebookmanager.repository.GetConnection;
import com.elangovan16.recipebookmanager.showrecipe.ShowRecipeView;

public class RecipeBookManager {
	public static void main(String[] args) {
		RecipeBookManager recipeBook = new RecipeBookManager();
		recipeBook.start();
	}

	private void start() {
		boolean flag = true;
		int choice;
		Scanner sc = new Scanner(System.in);
		do {
			info();
			choice = sc.nextInt();
			switch (choice) {
			case 1 -> {
				AddRecipeView addRecipe = new AddRecipeView();
				addRecipe.addNewRecipe();
			}
			case 2 -> {
				RemoveRecipeView removeRecipe = new RemoveRecipeView();
				removeRecipe.removeRecipe();
			}
			case 3 -> {
				ShowRecipeView ShowRecipe = new ShowRecipeView();
				ShowRecipe.showRecipe();
			}
			case 0 -> {
				flag = false;
			}
			default -> {
				System.out.println("Invalid choice. Please Enter Valid Choice.");
			}
			}
		} while (flag);
		sc.close();
		GetConnection.closeConnection();
	}

	private void info() {
		System.out.println("+----------------------+");
		System.out.println("|      RecipeBook      |");
		System.out.println("+----------------------+");
		System.out.println("|  1.Add Recipe        |");
		System.out.println("|  2.Remove Recipe     |");
		System.out.println("|  3.View Recipe       |");
		System.out.println("|  0.Exit...           |");
		System.out.println("+----------------------+");
		System.out.print("Enter Your Choice ----> ");
	}
}

package com.elangovan16.recipebookmanager.removerecipe;

import java.util.Scanner;

public class RemoveRecipeView {
	private RemoveRecipeViewModel removeRecipeViewModel;
	Scanner sc = new Scanner(System.in);

	public RemoveRecipeView() {
		removeRecipeViewModel = new RemoveRecipeViewModel(this);
	}

	public void removeRecipe() {
		System.out.println("Enter the recipe Name : ");
		String name = sc.nextLine();
		boolean del=removeRecipeViewModel.removeRecipe(name);
		if(del) {
			System.out.println("Removed");
		}
		else {
			System.out.println("Not-Removed");
		}
	}
}

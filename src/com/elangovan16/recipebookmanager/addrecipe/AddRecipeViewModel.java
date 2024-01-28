package com.elangovan16.recipebookmanager.addrecipe;

import com.elangovan16.recipebookmanager.dto.Recipe;
import com.elangovan16.recipebookmanager.repository.RecipeBookRepository;

public class AddRecipeViewModel {
	private AddRecipeView addRecipeView;

	public AddRecipeViewModel(AddRecipeView addRecipeView) {
		this.addRecipeView = addRecipeView;
	}

	public void validateRecipe(Recipe newRecipe) {
		RecipeBookRepository.getInstance().addRecipe(newRecipe);
		addRecipeView.onSuccess();
	}
}

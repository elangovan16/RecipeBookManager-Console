package com.elangovan16.recipebookmanager.removerecipe;

import com.elangovan16.recipebookmanager.repository.RecipeBookRepository;

public class RemoveRecipeViewModel {
	private RemoveRecipeView removeRecipeView;

	public RemoveRecipeViewModel(RemoveRecipeView removeRecipeView) {
		this.removeRecipeView = removeRecipeView;
	}

	public boolean removeRecipe(String name) {
		return RecipeBookRepository.getInstance().removeRecipe(name);
	}

}

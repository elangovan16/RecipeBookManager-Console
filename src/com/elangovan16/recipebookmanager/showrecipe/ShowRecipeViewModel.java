package com.elangovan16.recipebookmanager.showrecipe;

import java.util.List;

import com.elangovan16.recipebookmanager.dto.Recipe;
import com.elangovan16.recipebookmanager.repository.RecipeBookRepository;

public class ShowRecipeViewModel {
	private ShowRecipeView showRecipeView;

	public ShowRecipeViewModel(ShowRecipeView showRecipeView) {
		this.showRecipeView = showRecipeView;
	}

	public List<String> getRecipeNames() {
		return RecipeBookRepository.getInstance().getRecipeNames();
	}

	public Recipe getRecipe(String name) {
		return RecipeBookRepository.getInstance().getRecipe(name);
	}
}

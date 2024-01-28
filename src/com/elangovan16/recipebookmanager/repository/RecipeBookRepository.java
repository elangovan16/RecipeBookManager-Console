package com.elangovan16.recipebookmanager.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elangovan16.recipebookmanager.dto.Ingredient;
import com.elangovan16.recipebookmanager.dto.Recipe;

public class RecipeBookRepository {
	private static RecipeBookRepository repository;

	private RecipeBookRepository() {

	}

	public static RecipeBookRepository getInstance() {
		if (repository == null) {
			repository = new RecipeBookRepository();
		}
		return repository;
	}

	public void addRecipe(Recipe recipe) {
		String query = "INSERT INTO recipe(recipe_name) VALUES(?)";
		int recipeId;
		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, recipe.getName());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		recipeId = getRecipeId(recipe.getName());
		addIngredient(recipe.getIngredient(), recipeId);
		addInstruction(recipe.getInstruction(), recipeId);
	}

	public int getRecipeId(String name) {
		String query = "SELECT recipe_id FROM recipe WHERE recipe_name = ?";
		int recipeId = -1;
		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setString(1, name);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				recipeId = resultSet.getInt("recipe_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeId;
	}

	public void addIngredient(List<Ingredient> ingredients, int recipeId) {
		String query = "INSERT INTO ingredient (recipe_id, ingredient_name, quantity, unit) VALUES (?, ?, ?, ?)";
		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			for (Ingredient ingredient : ingredients) {
				pstmt.setInt(1, recipeId);
				pstmt.setString(2, ingredient.getIngredientName());
				pstmt.setDouble(3, ingredient.getQuantity());
				pstmt.setString(4, ingredient.getUnit());
				pstmt.addBatch();
			}

			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addInstruction(List<String> instructions, int recipeId) {
		String query = "INSERT INTO instruction (recipe_id, instruction_text) VALUES (?, ?)";

		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			for (String instruction : instructions) {
				pstmt.setInt(1, recipeId);
				pstmt.setString(2, instruction);
				pstmt.addBatch();
			}

			pstmt.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> getRecipeNames() {
		List<String> recipeNames = new ArrayList<>();
		String query = "SELECT recipe_name FROM recipe";
		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				recipeNames.add(resultSet.getString("recipe_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return recipeNames;
	}

	public Recipe getRecipe(String name) {
		Recipe recipe = null;
		int recipeId = getRecipeId(name);
		if (recipeId > 0) {
			List<Ingredient> ingredients = getIngredients(recipeId);
			List<String> instruction = getInstruction(recipeId);
			recipe = new Recipe(name, ingredients, instruction);
		}
		return recipe;
	}

	private List<String> getInstruction(int recipeId) {
		List<String> instruction = new ArrayList<>();
		String query = "SELECT instruction_text FROM instruction WHERE recipe_id = ?";

		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, recipeId);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				instruction.add(resultSet.getString("instruction_text"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return instruction;
	}

	private List<Ingredient> getIngredients(int recipeId) {
		List<Ingredient> ingredients = new ArrayList<>();
		String query = "SELECT ingredient_name, quantity, unit FROM ingredient WHERE recipe_id = ?";

		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);

			pstmt.setInt(1, recipeId);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				String ingredientName = resultSet.getString("ingredient_name");
				double quantity = resultSet.getDouble("quantity");
				String unit = resultSet.getString("unit");

				Ingredient ingredient = new Ingredient(ingredientName, quantity, unit);
				ingredients.add(ingredient);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ingredients;
	}

	public boolean removeRecipe(String name) {
		String deleteRecipeQuery = "DELETE FROM recipe WHERE recipe_name = ?";
		deleteIngredientsAndInstructions(name);
		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmtRecipe = con.prepareStatement(deleteRecipeQuery);
			pstmtRecipe.setString(1, name);
			int rowsAffected = pstmtRecipe.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void deleteIngredientsAndInstructions(String recipeName) {
		int recipeId = getRecipeId(recipeName);
		String deleteIngredientsQuery = "DELETE FROM ingredient WHERE recipe_id = ?";
		String deleteInstructionsQuery = "DELETE FROM instruction WHERE recipe_id = ? ";

		try {
			Connection con = GetConnection.getConnection();
			PreparedStatement pstmtIngredients = con.prepareStatement(deleteIngredientsQuery);
			PreparedStatement pstmtInstructions = con.prepareStatement(deleteInstructionsQuery);

			pstmtIngredients.setInt(1, recipeId);
			pstmtInstructions.setInt(1, recipeId);

			pstmtIngredients.executeUpdate();
			pstmtInstructions.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

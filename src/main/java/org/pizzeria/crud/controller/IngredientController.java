package org.pizzeria.crud.controller;


import java.util.List;

import org.pizzeria.crud.pojo.Ingredient;
import org.pizzeria.crud.pojo.Pizza;
import org.pizzeria.crud.pojo.Promotion;
import org.pizzeria.crud.serv.IngredientService;
import org.pizzeria.crud.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private PizzaService pizzaService;


// index
	@GetMapping
	public String index(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAllPizzas();
		model.addAttribute("ingredients", ingredients);

		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		Ingredient ingr = new Ingredient();
		model.addAttribute("ingr", ingr);
		
		model.addAttribute("routeName", "ingredients");
		model.addAttribute("type", "display");
			
		return "CRUDtemplates/ingredients/index";
	}
	
// Store
	@PostMapping("/store")
	public String storeIngredient(@Valid Ingredient ingredient) {
		
		List<Pizza> ingredientP = ingredient.getPizzas();

		
		for (Pizza pizza : ingredientP) {
			pizza.getIngredients().add(ingredient);
		}	
	
		ingredientService.save(ingredient);
	
		return "redirect:/ingredients";
	}
	

// Edit
	
// Update
	
	
// Delete
	@GetMapping("/delete/{id}")
	public String deleteingredient(@PathVariable("id") int id) {
		
		ingredientService.deleteIngredientById(id);
		
		return "redirect:/ingredients";
	}
}

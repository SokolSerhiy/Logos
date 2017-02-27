package ua.dto.form;

import ua.entity.Ingredient;
import ua.entity.MeasuringSystem;

public class AmountForm {
	
	private Long id;
	
	private String amount;
	
	private Ingredient ingredient;
	
	private MeasuringSystem system;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public MeasuringSystem getSystem() {
		return system;
	}

	public void setSystem(MeasuringSystem system) {
		this.system = system;
	}
}
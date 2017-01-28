package ua.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="amount")
public class Amount extends AbstractEntity{

	private BigDecimal amount;
	@ManyToOne(fetch=FetchType.LAZY)
	private Ingredient ingredient;
	@ManyToOne(fetch=FetchType.LAZY)
	private MeasuringSystem system;
	@ManyToMany(mappedBy="amounts")
	private List<Recipe> recipes = new ArrayList<>();
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
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
	public List<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
	
	public String getPresentation(){
		return String.valueOf(amount)+" "+system.getName()+" "+ingredient.getName();
	}
}

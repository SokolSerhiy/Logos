package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Amount;
import ua.service.AmountService;

public class AmountEditor extends PropertyEditorSupport{

	private final AmountService service;

	public AmountEditor(AmountService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Amount amount = service.findOne(Long.valueOf(text));
		setValue(amount);
	}
}

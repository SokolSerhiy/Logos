package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Street;
import ua.service.StreetService;

public class StreetEditor extends PropertyEditorSupport{

	private final StreetService service;

	public StreetEditor(StreetService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String name) throws IllegalArgumentException {
		Street street = service.findByName(name);
		setValue(street);
	}
}
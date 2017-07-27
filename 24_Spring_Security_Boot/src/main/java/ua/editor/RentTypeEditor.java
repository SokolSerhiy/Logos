package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.RentType;
import ua.service.RentTypeService;

public class RentTypeEditor extends PropertyEditorSupport{

	private final RentTypeService service;

	public RentTypeEditor(RentTypeService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String name) throws IllegalArgumentException {
		RentType type = service.findByName(name);
		setValue(type);
	}
}
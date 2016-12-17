package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.SpecificationString;
import ua.service.SpecificationStringService;

public class SpecificationStringEditor extends PropertyEditorSupport{

	private final SpecificationStringService service;

	public SpecificationStringEditor(SpecificationStringService service) {
		this.service = service;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		SpecificationString ss = service.findOne(Integer.valueOf(text));
		setValue(ss);
	}
}

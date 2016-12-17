package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.NameOfSpecificationString;
import ua.service.NameOfSpecificationStringService;

public class NameOfSpecificationStringEditor extends PropertyEditorSupport {

	private final NameOfSpecificationStringService nameOfSpecificationStringService;

	public NameOfSpecificationStringEditor(NameOfSpecificationStringService nameOfSpecificationStringService) {
		this.nameOfSpecificationStringService = nameOfSpecificationStringService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		NameOfSpecificationString noss = nameOfSpecificationStringService.findOne(Integer.valueOf(text));
		setValue(noss);
	}
}

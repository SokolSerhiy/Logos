package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.NameOfSpecificationDigital;
import ua.service.NameOfSpecificationDigitalService;

public class NameOfSpecificationDigitalEditor extends PropertyEditorSupport {

	private final NameOfSpecificationDigitalService digitalService;

	public NameOfSpecificationDigitalEditor(NameOfSpecificationDigitalService digitalService) {
		this.digitalService = digitalService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		NameOfSpecificationDigital nosd = digitalService.findOne(Integer.valueOf(text));
		setValue(nosd);
	}
}

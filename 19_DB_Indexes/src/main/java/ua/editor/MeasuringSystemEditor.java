package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

public class MeasuringSystemEditor extends PropertyEditorSupport{

	private final MeasuringSystemService measuringSystemService;

	public MeasuringSystemEditor(MeasuringSystemService measuringSystemService) {
		this.measuringSystemService = measuringSystemService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MeasuringSystem ms = measuringSystemService.findOne(Integer.valueOf(text));
		setValue(ms);
	}
}

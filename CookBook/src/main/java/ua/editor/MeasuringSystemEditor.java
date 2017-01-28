package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

public class MeasuringSystemEditor extends PropertyEditorSupport{

	private final MeasuringSystemService service;

	public MeasuringSystemEditor(MeasuringSystemService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MeasuringSystem measuringSystem = service.findOne(Long.valueOf(text));
		setValue(measuringSystem);
	}
}

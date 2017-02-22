package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.MeasuringSystem;
import ua.service.MeasuringSystemService;

public class MeasuringSystemEditor extends PropertyEditorSupport{

	private final MeasuringSystemService systemService;

	public MeasuringSystemEditor(MeasuringSystemService systemService) {
		this.systemService = systemService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		MeasuringSystem system = systemService.findOne(Long.valueOf(text));
		setValue(system);
	}
	
	
}

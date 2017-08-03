package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Area;
import ua.service.AreaService;

public class AreaEditor extends PropertyEditorSupport{

	private final AreaService service;

	public AreaEditor(AreaService service) {
		this.service = service;
	}

	@Override
	public void setAsText(String name) throws IllegalArgumentException {
		Area area = service.findByName(name);
		setValue(area);
	}
	
	
}

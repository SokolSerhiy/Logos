package ua.editor;

import java.beans.PropertyEditorSupport;

import ua.entity.Producer;
import ua.service.ProducerService;

public class ProducerEditor extends PropertyEditorSupport {

	private final ProducerService producerService;

	public ProducerEditor(ProducerService producerService) {
		this.producerService = producerService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Producer producer = producerService.findOne(Integer.valueOf(text));
		setValue(producer);
	}
}

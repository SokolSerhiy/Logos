package ua.service.utils;

import java.awt.image.BufferedImage;

public class Engine {

	private final BufferedImage old;

	private final BufferedImage present;

	private final int oldX;

	private final int oldY;

	private final boolean isVerticalStrategy;

	public Engine(BufferedImage old) {
		this.old = old;
		oldX = old.getWidth();
		oldY = old.getHeight();
		isVerticalStrategy = oldY > oldX;
		present = new BufferedImage(oldX > oldY ? oldY : oldX, oldX > oldY ? oldY : oldX, old.getType());
	}
	
	public BufferedImage crop(){
		if(isVerticalStrategy)werticalCrop();
		else horizontalCrop();
		return present;
	}

	private void werticalCrop(){
		final int offset = (oldY - oldX) / 2;
		for(int x = 0; x < present.getHeight(); x++){
			for(int y = 0; y < present.getWidth(); y++){
				present.setRGB(x, y, old.getRGB(x, y+offset));
			}
		}
	}
	
	private void horizontalCrop(){
		final int offset = (oldX - oldY) / 2;
		for(int x = 0; x < present.getHeight(); x++){
			for(int y = 0; y < present.getWidth(); y++){
				present.setRGB(x, y, old.getRGB(x+offset, y));
			}
		}
	}
}

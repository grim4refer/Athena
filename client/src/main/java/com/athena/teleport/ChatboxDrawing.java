package com.athena.teleport;

public interface ChatboxDrawing {
	
	public void draw(int chatboxWidth, int chatboxHeight, int offsetX, int offsetY);
	
	public boolean pressKey(int key);
	
}
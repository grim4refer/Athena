package com.athena.teleport.hierarchy;

import java.awt.*;

public interface HierarchyOption {

	public Dimension getDimension();
	
	public String getName();
	
	public int getShortcutKey();
	
	public String getDescription();
	
	public int[] getIndex();

	public HierarchyOption[] getOptions();
	
}

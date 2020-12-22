package org.yanille.teleport.hierarchy;


import org.yanille.*;
import org.yanille.teleport.ChatboxDrawing;

import java.awt.event.KeyEvent;


public abstract class HierarchyChatboxDrawing implements ChatboxDrawing {


	private Sprite close = Client.instance.spriteCache.get(1015);

	private Sprite closeHovered = Client.instance.spriteCache.get(1016);

	private long lastClick;

	private long hoverTimer;

	public HierarchyOption selectedHierarchy;

	public abstract HierarchyOption[] getOptions();

	public HierarchyOption getSelectedHierarchy() {
		return selectedHierarchy;
	}

	public void setSelectedHierarchy(HierarchyOption selectedHierarchy) {
		this.selectedHierarchy = selectedHierarchy;
	}

	@Override
	public void draw(int chatboxWidth, int chatboxHeight, int offsetX, int offsetY) {
		if(Client.openInterfaceID != -1) {
			Client.chatboxDrawing = null;
			return;
		}
		final int alpha = Client.instance.isFixed() ? 255 : 100;

		Rasterizer.drawHorizontal(7, 24 + offsetY, chatboxWidth - 14, 0x847963,
				alpha);
		Rasterizer.drawHorizontal(7, 23 + offsetY, chatboxWidth - 14, 0x847963,
				alpha);

		final int closeX = 7; final int closeY = 8 + offsetY;
		final boolean closeHover = Client.instance.mouseX >= closeX
				&& Client.instance.mouseX <= closeX + close.myWidth
				&& Client.instance.mouseY + 23 >= (Client.clientHeight - chatboxHeight + (Client.instance.isFixed() ? 7 : 10))
				&& Client.instance.mouseY + 23 <= (Client.clientHeight - chatboxHeight + (Client.instance.isFixed() ? 7 : 10)) + close.myHeight;
		final Sprite closeImage = closeHover ? closeHovered : close;

		closeImage.drawSprite(closeX, closeY);

		if (closeHover && Client.instance.clickType == Client.instance.LEFT) {
			Client.chatboxDrawing = null;
		}


		int mouseX = Client.instance.mouseX;
		int mouseY = Client.instance.mouseY - (Client.clientHeight - chatboxHeight) + offsetY + 23;

		final RSFontSystem font = Client.instance.newRegularFont;

		int optionX = 10;
		int optionY = 36 + offsetY;
		int index = 0;
		int hoveredIndex = -1;
		int hoveredOptionX = -1;
		int hoveredOptionY = -1;
		HierarchyOption[] options = getOptions();
		int shiftedX = optionX / 2;
		for (HierarchyOption hierarchyOption : options) {
			if (shiftedX == optionX / 2)
				shiftedX += hierarchyOption.getDimension().width;
			if (optionY >= chatboxHeight - 23 + offsetY) {
				optionY = 36;
				optionX += hierarchyOption.getDimension().width;
				shiftedX += hierarchyOption.getDimension().width;
			}
			String shortcutKey = null;
			if (hierarchyOption.getShortcutKey() != -1) {
				shortcutKey = KeyEvent.getKeyText(hierarchyOption.getShortcutKey()) + ".";
				font.drawBasicString(shortcutKey, optionX, optionY,
						!Client.instance.isFixed() ? 0 : 0x696969, -1);
			}
			font.drawBasicString(hierarchyOption.getName(), 15 + optionX, optionY,
					!Client.instance.isFixed() ? 0xFFFFFF : 0x000000, -1);

			if (hierarchyOption.getIndex() == null) {
				int textWidth = font.getTextWidth(hierarchyOption.getName());
				int color = Client.instance.isFixed() ? 0x847963 : 0x808080;

				Rasterizer.drawHorizontalLine(20 + textWidth + optionX, optionY - 4,
						10, color);

				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 5,
						4, color);
				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 6,
						3, color);
				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 7,
						2, color);
				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 8,
						1, color);

				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 3,
						4, color);
				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 2,
						3, color);
				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 1,
						2, color);
				Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY,
						1, color);
			}

			if (mouseX >= optionX
					&& mouseX <= optionX + hierarchyOption.getDimension().width
					&& mouseY >= optionY - 10
					&& mouseY <= optionY - 10 + hierarchyOption.getDimension().height) {
				hoveredIndex = index;
				hoveredOptionX = optionX;
				hoveredOptionY = optionY - hierarchyOption.getDimension().height;
			}

			optionY += font.baseCharacterHeight;
			index++;
		}

		if (hoveredIndex >= 0 && hoveredIndex < options.length) {
			HierarchyOption hierarchyOption = options[hoveredIndex];
			Rasterizer.fillRectangle(5 + (hoveredOptionX > 10 ? hoveredOptionX - 5 : 0),
					hoveredOptionY + 1,
					hierarchyOption.getDimension().width + 5,
					hierarchyOption.getDimension().height,
					0, 50);

			if (Client.instance.clickType == Client.instance.LEFT
					&& System.currentTimeMillis() - lastClick >= 300) {
				if (hierarchyOption.getIndex() == null)
					selectedHierarchy = hierarchyOption;
				else
					Client.instance.sendHierarchy(hierarchyOption,this);
				lastClick = System.currentTimeMillis();
			}
		}

		Rasterizer.drawVertical(shiftedX + 3, 25 + offsetY, chatboxHeight - 55, 0x847963, alpha);
		Rasterizer.drawVertical(shiftedX + 4, 25 + offsetY, chatboxHeight - 55, 0x847963, alpha);

		if (selectedHierarchy != null) {
			optionX = 2 + optionX + selectedHierarchy.getDimension().width;
			optionY = 36 + offsetY;
			index = 0;
			hoveredIndex = -1;
			hoveredOptionX = -1;
			hoveredOptionY = -1;
			options = selectedHierarchy.getOptions();
			if (options == null)
				return;
			for (HierarchyOption hierarchyOption : options) {
				if (optionY >= chatboxHeight - 23 + offsetY) {
					optionY = 36 + offsetY;
					optionX += hierarchyOption.getDimension().width;
				}
				String shortcutKey = null;
				if (hierarchyOption.getShortcutKey() != -1) {
					shortcutKey = KeyEvent.getKeyText(hierarchyOption.getShortcutKey()) + ".";
					font.drawBasicString(shortcutKey, optionX, optionY,
							!Client.instance.isFixed() ? 0 : 0x696969, -1);
				}
				font.drawBasicString(hierarchyOption.getName(), 15 + optionX, optionY,
						!Client.instance.isFixed() ? 0xFFFFFF : 0x000000, -1);

				if (hierarchyOption.getIndex() == null) {
					int textWidth = font.getTextWidth(hierarchyOption.getName());

					Rasterizer.drawHorizontalLine(20 + textWidth + optionX, optionY - 4,
							10, 0x847963);

					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 5,
							4, 0x847963);
					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 6,
							3, 0x847963);
					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 7,
							2, 0x847963);
					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 8,
							1, 0x847963);

					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 3,
							4, 0x847963);
					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 2,
							3, 0x847963);
					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY - 1,
							2, 0x847963);
					Rasterizer.drawHorizontalLine(25 + textWidth + optionX, optionY,
							1, 0x847963);
				}

				if (mouseX >= optionX
						&& mouseX <= optionX + hierarchyOption.getDimension().width
						&& mouseY >= optionY - 10
						&& mouseY <= optionY - 10 + hierarchyOption.getDimension().height) {
					hoveredIndex = index;
					hoveredOptionX = optionX;
					hoveredOptionY = optionY - hierarchyOption.getDimension().height;
				}

				optionY += font.baseCharacterHeight;
				index++;
			}

			if (hoveredIndex >= 0 && hoveredIndex < options.length) {
				HierarchyOption hierarchyOption = options[hoveredIndex];
				Rasterizer.fillRectangle(hoveredOptionX,
						hoveredOptionY + 1,
						hierarchyOption.getDimension().width,
						hierarchyOption.getDimension().height,
						0, 50);

				if (Client.instance.clickType == Client.instance.LEFT
						&& System.currentTimeMillis() - lastClick >= 300) {
					if (hierarchyOption.getIndex() == null)
						selectedHierarchy = hierarchyOption;
					else
						Client.instance.sendHierarchy(hierarchyOption,this);
					lastClick = System.currentTimeMillis();
				}

				if (hoverTimer++ >= 30
						&& hierarchyOption.getDescription() != null) {
					final RSFontSystem descriptionFont = Client.instance.newSmallFont;
					int width = 380 - hierarchyOption.getDimension().width;
					final String[] descriptionSplit = StringUtils.split(descriptionFont, hierarchyOption.getDescription(), width, "<n>");
					final int height = (descriptionFont.baseCharacterHeight + 2) * descriptionSplit.length + 2;



					int tooltipX = hoveredIndex >= 7 ?
							width - hierarchyOption.getDimension().width
							: 5 + (hoveredOptionX > 10 ? hoveredOptionX - 5 : 0) + hierarchyOption.getDimension().width;
					int tooltipY = offsetY + 24;

				//	Rasterizer.drawRoundedRectangle(tooltipX, tooltipY, width + 6, height,
				//			0xFFA500, 50, true, false);
					//Rasterizer.drawRoundedRectangle(tooltipX -1, tooltipY - 1, width + 8, height + 2,
				//			0xFFFFFF, 180, true, false);

					Rasterizer.drawRectangle(tooltipY, height, 200, 0xFFFFFF, width+6, tooltipX);
					Rasterizer.fillRectangle(0x000000, tooltipY - 1, width+8, height+2, 200, tooltipX -1);

					tooltipY += 7;
					for (String description : descriptionSplit) {
						if (description == null)
							continue;
						descriptionFont.drawBasicString(description, tooltipX + 3, tooltipY + 5, 0xFFFFFF, 0);
						tooltipY += descriptionFont.baseCharacterHeight + 2;
					}
				}
			} else {
				hoverTimer = 0;
			}
		}
	}

	@Override
	public boolean pressKey(int key) {
		if (key == KeyEvent.VK_DELETE) {
			selectedHierarchy = null;
			return true;
		}

		boolean found = false;
		if (selectedHierarchy != null
				&& selectedHierarchy.getOptions() != null) {

			HierarchyOption[] options = selectedHierarchy.getOptions();
			for (HierarchyOption hierarchyOption : options) {

				if (hierarchyOption.getShortcutKey() == key) {

					if (hierarchyOption.getIndex() != null) {
						Client.instance.sendHierarchy(hierarchyOption,this);
					} else {
						selectedHierarchy = hierarchyOption;
					}
					return true;
				}
			}
		}

		if (!found) {
			HierarchyOption[] options = getOptions();
			for (HierarchyOption hierarchyOption : options) {
				if (hierarchyOption.getShortcutKey() == key) {
					if (hierarchyOption.getIndex() != null) {
						Client.instance.sendHierarchy(hierarchyOption,this);
					} else {
						selectedHierarchy = hierarchyOption;
					}
					return true;
				}
			}
		}
		return false;
	}

}

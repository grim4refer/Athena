package org.yanille;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Hashtable;

public final class AnimatedSprite extends Sprite {
	public static final int SCALE_DETAIL_DEFAULT = 0;
	public static final int SCALE_DETAIL_QUALITY = 1;
	public static final int SCALE_DETAIL_SPEED = 2;
	private int scaleDetail;
	private boolean autoUpdate;
	public final int realWidth;
	public final int realHeight;
	public final boolean scaled;
	public final Image image;
	public final Component component;
	public final int width;
	public final int height;
	public final int[] pixels;
	public final BufferedImage buffer;
	public static final Component DUMMY_COMPONENT = new Canvas();

	public AnimatedSprite(String file) {
		this(file, DUMMY_COMPONENT);
	}

	public AnimatedSprite(String file, Component component) {
		this((String)file, component, 0, 0);
	}

	public AnimatedSprite(File file) {
		this(file, DUMMY_COMPONENT);
	}

	public AnimatedSprite(File file, Component component) {
		this(loadFile(file), component);
	}

	public AnimatedSprite(byte[] data) {
		this(data, DUMMY_COMPONENT);
	}

	public AnimatedSprite(byte[] data, Component component) {
		this(loadImage(data, component), component);
	}

	public AnimatedSprite(Image image) {
		this(image, DUMMY_COMPONENT);
	}

	public AnimatedSprite(Image image, Component component) {
		this(image, component, 0, 0);
	}

	public AnimatedSprite(String file, int width, int height) {
		this(file, DUMMY_COMPONENT, width, height);
	}

	public AnimatedSprite(String file, Component component, int width, int height) {
		this(new File(file), component, width, height);
	}

	public AnimatedSprite(File file, int width, int height) {
		this(file, DUMMY_COMPONENT, width, height);
	}

	public AnimatedSprite(File file, Component component, int width, int height) {
		this(loadFile(file), component, width, height);
	}

	public AnimatedSprite(byte[] data, int width, int height) {
		this(data, DUMMY_COMPONENT, width, height);
	}

	public AnimatedSprite(byte[] data, Component component, int width, int height) {
		this(loadImage(data, component), component, width, height);
	}

	public AnimatedSprite(Image image, int width, int height) {
		this(image, DUMMY_COMPONENT, width, height);
	}

	public AnimatedSprite(URL url) {
		this(url, DUMMY_COMPONENT);
	}

	public AnimatedSprite(URL url, Component component) {
		this((URL)url, component, 0, 0);
	}

	public AnimatedSprite(URL url, int width, int height) {
		this(url, DUMMY_COMPONENT, width, height);
	}

	public AnimatedSprite(URL url, Component component, int width, int height) {
		this(loadImage(url, component), component, width, height);
	}

	public AnimatedSprite(Image image, Component component, int width, int height) {
		if (component == null) {
			component = DUMMY_COMPONENT;
		}

		this.image = image;
		this.component = component;
		int newWidth = 0;
		int newHeight = 0;
		if (image != null) {
			newWidth = image.getWidth(component);
			newHeight = image.getHeight(component);
			if (newWidth <= 0 || newHeight <= 0) {
				newHeight = 0;
				newWidth = 0;
			}
		}

		this.realWidth = newWidth;
		this.realHeight = newHeight;
		boolean scaled = newWidth > 0 && newHeight > 0 && width > 0 && height > 0 && (width != newWidth || height != newHeight);
		if (scaled) {
			newWidth = width;
			newHeight = height;
		}

		if (newWidth < 1) {
			newWidth = 1;
		}

		if (newHeight < 1) {
			newHeight = 1;
		}

		this.scaled = scaled;
		this.width = newWidth;
		this.height = newHeight;
		int pixelCount = newWidth * newHeight;
		int[] pixels = new int[pixelCount];
		this.pixels = pixels;
		DirectColorModel model = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
		this.buffer = new BufferedImage(model, Raster.createWritableRaster(model.createCompatibleSampleModel(newWidth, newHeight), new DataBufferInt(pixels, pixelCount), (Point)null), false, new Hashtable());
		this.scaleDetail = 0;
		this.autoUpdate = true;
		this.update();
	}

	public AnimatedSprite(AnimatedSprite sprite, int width, int height, boolean absScale) {
		if (sprite == null) {
			throw new NullPointerException();
		} else {
			this.image = sprite.image;
			this.component = sprite.component;
			int newWidth = sprite.realWidth;
			int newHeight = sprite.realHeight;
			this.realWidth = newWidth;
			this.realHeight = newHeight;
			boolean scaled = newWidth > 0 && newHeight > 0 && width > 0 && height > 0 && (width != newWidth || height != newHeight);
			if (scaled) {
				if (!absScale) {
					newWidth = newWidth * width / sprite.width;
					newHeight = newHeight * height / sprite.height;
				} else {
					newWidth = width;
					newHeight = height;
				}
			} else if (!absScale) {
				newWidth = sprite.width;
				newHeight = sprite.height;
			}

			if (newWidth < 1) {
				newWidth = 1;
			}

			if (newHeight < 1) {
				newHeight = 1;
			}

			this.scaled = scaled;
			this.width = newWidth;
			this.height = newHeight;
			int pixelCount = newWidth * newHeight;
			int[] pixels = new int[pixelCount];
			this.pixels = pixels;
			DirectColorModel model = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
			this.buffer = new BufferedImage(model, Raster.createWritableRaster(model.createCompatibleSampleModel(newWidth, newHeight), new DataBufferInt(pixels, pixelCount), (Point)null), false, new Hashtable());
			this.scaleDetail = sprite.scaleDetail;
			this.autoUpdate = sprite.autoUpdate;
			this.update();
		}
	}

	public AnimatedSprite getInstance(int width, int height) {
		return this.getInstance(width, height, false);
	}

	public AnimatedSprite getInstance(int width, int height, boolean absScale) {
		return new AnimatedSprite(this, width, height, absScale);
	}

	public Image getInstanceFromBuffer(int width, int height) {
		AnimatedSprite sprite = this.getInstance(width, height);
		return sprite.buffer;
	}

	private static int blend(int dst, int src) {
		int alpha = src >>> 24;
		if (alpha == 0) {
			return dst;
		} else if (alpha == 255) {
			return src;
		} else {
			int delta = 255 - alpha;
			return (src & -16777216 | ((src & 16711935) * alpha + (dst & 16711935) * delta & -16711936 | (src & '\uff00') * alpha + (dst & '\uff00') * delta & 16711680) >>> 8) & 16777215;
		}
	}

	private static int blend(int dst, int src, int alpha) {
		alpha = (src >>> 24) * alpha / 255;
		if (alpha <= 0) {
			return dst;
		} else if (alpha >= 255) {
			return src;
		} else {
			int delta = 255 - alpha;
			return (src & -16777216 | ((src & 16711935) * alpha + (dst & 16711935) * delta & -16711936 | (src & '\uff00') * alpha + (dst & '\uff00') * delta & 16711680) >>> 8) & 16777215;
		}
	}

	public void block_copy_mask(int[] src, int scanSize, byte[] dir, int height, int[] dst, int tmp, int dstOff, int dstPtr, int srcOff, int srcPtr) {
		int scanSizeHigh = -(scanSize >> 2);
		scanSize = -(scanSize & 3);

		for(int y = -height; y != 0; ++y) {
			int i;
			for(i = scanSizeHigh; i != 0; ++i) {
				if (dir[dstPtr] == 0) {
					dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				} else {
					++dstPtr;
				}

				if (dir[dstPtr] == 0) {
					dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				} else {
					++dstPtr;
				}

				if (dir[dstPtr] == 0) {
					dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				} else {
					++dstPtr;
				}

				if (dir[dstPtr] == 0) {
					dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				} else {
					++dstPtr;
				}
			}

			for(i = scanSize; i != 0; ++i) {
				if (dir[dstPtr] == 0) {
					dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				} else {
					++dstPtr;
				}
			}

			dstPtr += dstOff;
			srcPtr += srcOff;
		}

	}

	public void block_copy_alpha(int srcPtr, int scanSize, int[] dst, int[] src, int srcOff, int height, int dstOff, int alpha, int dstPtr) {
		int scanSizeHigh = -(scanSize >> 2);
		scanSize = -(scanSize & 3);

		for(int y = -height; y != 0; ++y) {
			int i;
			for(i = scanSizeHigh; i != 0; ++i) {
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++], alpha);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++], alpha);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++], alpha);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++], alpha);
			}

			for(i = scanSize; i != 0; ++i) {
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++], alpha);
			}

			dstPtr += dstOff;
			srcPtr += srcOff;
		}

	}

	public void block_copy(int dstPtr, int scanSize, int height, int srcOff, int srcPtr, int dstOff, int[] src, int[] dst) {
		int scanSizeHigh = -(scanSize >> 2);
		scanSize = -(scanSize & 3);

		for(int y = -height; y != 0; ++y) {
			int i;
			for(i = scanSizeHigh; i != 0; ++i) {
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
			}

			for(i = scanSize; i != 0; ++i) {
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
			}

			dstPtr += dstOff;
			srcPtr += srcOff;
		}

	}

	public void block_copy_trans(int[] dst, int[] src, int srcPtr, int dstPtr, int scanSize, int height, int dstOff, int srcOff) {
		int scanSizeHigh = -(scanSize >> 2);
		scanSize = -(scanSize & 3);

		for(int y = -height; y != 0; ++y) {
			int i;
			for(i = scanSizeHigh; i != 0; ++i) {
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
			}

			for(i = scanSize; i != 0; ++i) {
				dst[dstPtr] = blend(dst[dstPtr++], src[srcPtr++]);
			}

			dstPtr += dstOff;
			srcPtr += srcOff;
		}

	}

	private static Object getScaleDetail(int scaleDetail) {
		if (scaleDetail == 1) {
			return RenderingHints.VALUE_RENDER_SPEED;
		} else {
			return scaleDetail == 2 ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_DEFAULT;
		}
	}

	public void autoUpdate() {
		if (this.autoUpdate) {
			this.update();
		}

	}

	public void update() {
		try {
			Graphics g = this.buffer.getGraphics();
			if (g != null) {
				try {
					if (!this.scaled) {
						g.drawImage(this.image, 0, 0, this.component);
					} else {
						if (g instanceof Graphics2D) {
							((Graphics2D)g).setRenderingHint(RenderingHints.KEY_RENDERING, getScaleDetail(this.scaleDetail));
						}

						g.drawImage(this.image, 0, 0, this.width, this.height, this.component);
					}
				} finally {
					g.dispose();
				}
			}
		} catch (Exception var6) {
		}

		this.drawOffsetX = this.drawOffsetY = 0;
		this.myWidth = this.maxWidth = this.width;
		this.myHeight = this.maxHeight = this.height;
		this.myPixels = this.pixels;
	}

	private static byte[] loadFile(File file) {
		if (file != null && file.exists() && file.isFile() && file.canRead()) {
			long size = file.length();
			if (size >= 0L && size <= 2147483647L) {
				int count = (int)size;
				byte[] data = null;

				try {
					FileInputStream input = new FileInputStream(file);

					try {
						data = new byte[count];

						int read;
						for(int offset = 0; count != 0; count -= read) {
							read = input.read(data, offset, count);
							if (read < 0) {
								break;
							}

							if (read > count) {
								read = count;
							}

							offset += read;
						}
					} finally {
						input.close();
					}
				} catch (Exception var12) {
				}

				return count == 0 ? data : null;
			} else {
				return null;
			}
		} else {
			System.out.println("Can find: " + file.exists() + " Is a file: " + file.isFile() + " Can read: " + file.canRead());
			return null;
		}
	}

	private static Image loadImage(byte[] data, Component component) {
		if (data != null) {
			try {
				Toolkit toolkit = null;
				if (component == null) {
					component = DUMMY_COMPONENT;
				}

				try {
					toolkit = component.getToolkit();
				} catch (Exception var5) {
				}

				if (toolkit == null) {
					toolkit = Toolkit.getDefaultToolkit();
				}

				if (toolkit != null) {
					Image image = toolkit.createImage(data);
					if (image != null) {
						MediaTracker tracker = new MediaTracker(component);
						tracker.addImage(image, 0);
						tracker.waitForAll();
						if (!tracker.isErrorAny() && image.getWidth(component) >= 0 && image.getHeight(component) >= 0) {
							return image;
						}
					}
				}
			} catch (Exception var6) {
			}
		}

		return null;
	}

	private static Image loadImage(URL url, Component component) {
		if (url != null) {
			try {
				Toolkit toolkit = null;
				if (component == null) {
					component = DUMMY_COMPONENT;
				}

				try {
					toolkit = component.getToolkit();
				} catch (Exception var5) {
				}

				if (toolkit == null) {
					toolkit = Toolkit.getDefaultToolkit();
				}

				if (toolkit != null) {
					Image image = toolkit.createImage(url);
					if (image != null) {
						MediaTracker tracker = new MediaTracker(component);
						tracker.addImage(image, 0);
						tracker.waitForAll();
						if (!tracker.isErrorAny() && image.getWidth(component) >= 0 && image.getHeight(component) >= 0) {
							return image;
						}
					}
				}
			} catch (Exception var6) {
			}
		}

		return null;
	}

	public void setScaleDetailDefault() {
		this.scaleDetail = 0;
	}

	public void setScaleDetailQuality() {
		this.scaleDetail = 1;
	}

	public void setScaleDetailSpeed() {
		this.scaleDetail = 2;
	}

	public void setScaleDetail(int scaleDetail) {
		if (scaleDetail != 0 && scaleDetail != 1 && scaleDetail != 2) {
			throw new IllegalArgumentException("Invalid scale detail (" + scaleDetail + ")!");
		} else {
			this.scaleDetail = scaleDetail;
		}
	}

	public boolean isScaleDetailDefault() {
		int scaleDetail = this.scaleDetail;
		return scaleDetail != 1 && scaleDetail != 2;
	}

	public boolean isScaleDetailQuality() {
		return this.scaleDetail == 1;
	}

	public boolean isScaleDetailSpeed() {
		return this.scaleDetail == 2;
	}

	public int getScaleDetail() {
		return this.scaleDetail;
	}

	public void setAutoUpdate(boolean autoUpdate) {
		autoUpdate = this.autoUpdate;
	}

	public boolean getAutoUpdate() {
		return this.autoUpdate;
	}

	public boolean isValid() {
		return this.realWidth > 0 && this.realHeight > 0;
	}
}

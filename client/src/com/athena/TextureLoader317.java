package com.athena;




public class TextureLoader317 {
	/*   8 */   public static int textureammount = 72;
	/*     */
	/*  10 */   private static int[][] texturePalettes = new int[textureammount][];
	/*     */   private static int loadedTextureCount;
	/*  12 */   public static Background[] textureImages = new Background[textureammount];
	/*  13 */   public static boolean[] textureIsTransparent = new boolean[textureammount];
	/*  14 */   private static int[] averageTextureColour = new int[textureammount];
	/*     */
	/*  16 */   private static int[][] texelCache = new int[textureammount][];
	/*  17 */   public static int[] textureLastUsed = new int[textureammount];
	/*     */   private static int textureTexelPoolPointer;
	/*     */   private static int[][] texelArrayPool;
	/*     */   public static int textureGetCount;

	public static void clear() {
		textureImages = null;
		textureIsTransparent = null;
		averageTextureColour = null;
		texelArrayPool = null;
		texelCache = null;
		textureLastUsed = null;
		texturePalettes = null;
	}
			
	public static void calculateTexturePalette(double brightness) {
		for (int l = 0; l < 72; l++)
			if (textureImages[l] != null) {
				int ai[] = textureImages[l].palette;
				texturePalettes[l] = new int[ai.length];
				for (int j1 = 0; j1 < ai.length; j1++) {
					texturePalettes[l][j1] = Rasterizer.adjustBrightness(ai[j1], brightness);
					if ((texturePalettes[l][j1] & 0xf8f8ff) == 0 && j1 != 0)
						texturePalettes[l][j1] = 1;
				}

			}

		for (int i1 = 0; i1 < 72; i1++)
			resetTexture(i1);
	}
	
	public static void clearTextureCache() {
		texelArrayPool = null;
		for (int j = 0; j < 72; j++)
			texelCache[j] = null;

	}
	
	public static void resetTextures() {
		if(Client.getOption("hd_tex")) {
			TextureLoader667.resetTextures();
			return;
		}
		if (texelArrayPool == null) {
			textureTexelPoolPointer = 20;// was parameter
			if (Rasterizer.lowMem)
				texelArrayPool = new int[textureTexelPoolPointer][16384];
			else
				texelArrayPool = new int[textureTexelPoolPointer][0x10000];
			for (int k = 0; k < 72; k++)
				texelCache[k] = null;

		}
	}

	     public static void unpackTextures(CacheArchive streamLoader) {
		/*  74 */     loadedTextureCount = 0;
		/*  75 */     for (int j = 0; j < textureammount; j++) {
			/*     */       try {
				/*  77 */         textureImages[j] = new Background(streamLoader, String.valueOf(j), 0);
				/*     */
				/*  79 */         if ((Rasterizer.lowMem) && (textureImages[j].libWidth == 128)) {
					/*  80 */           textureImages[j].reduceSetOffset();
					/*     */         } else
					/*  82 */           textureImages[j].setOffset();
				/*  83 */         loadedTextureCount += 1;
				/*     */       }
			/*     */       catch (Exception localException) {}
			/*     */     }
		/*     */   }
	/*     */
	/*     */   public static int getAverageTextureColour(int textureId) {
		/*  90 */     if (averageTextureColour[textureId] != 0)
			/*  91 */       return averageTextureColour[textureId];
		/*  92 */     int red = 0;
		/*  93 */     int green = 0;
		/*  94 */     int blue = 0;
		/*  95 */     int colourCount = texturePalettes[textureId].length;
		/*     */
		/*  97 */     for (int k1 = 0; k1 < colourCount; k1++) {
			/*  98 */       red += (texturePalettes[textureId][k1] >> 16 & 0xFF);
			/*  99 */       green += (texturePalettes[textureId][k1] >> 8 & 0xFF);
			/* 100 */       blue += (texturePalettes[textureId][k1] & 0xFF);
			/*     */     }
		/*     */
		/* 103 */     int rgb = (red / colourCount << 16) + (green / colourCount << 8) + blue / colourCount;
		/* 104 */     rgb = Rasterizer.adjustBrightness(rgb, 1.4D);
		/* 105 */     if ((textureId == 1) || (textureId == 24)) {
			/* 106 */       rgb = adjustBrightnessLinear(rgb, 679);
			/* 107 */       rgb = Rasterizer.adjustBrightness(rgb, 4.800000190734863D);
			/*     */     } else {
			/* 109 */       rgb = Rasterizer.adjustBrightness(rgb, 1.4D); }
		/* 110 */     if (rgb == 0)
			/* 111 */       rgb = 1;
		/* 112 */     averageTextureColour[textureId] = rgb;
		/* 113 */     return rgb;
		/*     */   }
	/*     */
	/*     */   private static int adjustBrightnessLinear(int rgb, int factor) {
		/* 117 */     return ((rgb >>> 16) * factor & 0xFF00) << 8 |
				/* 118 */       (rgb >>> 8 & 0xFF) * factor & 0xFF00 |
				/* 119 */       (rgb & 0xFF) * factor >> 8;
		/*     */   }
	/*     */
	/* 122 */   public static void resetTexture(int textureId) { if (texelCache[textureId] == null)
		/* 123 */       return;
		/* 124 */     texelArrayPool[(textureTexelPoolPointer++)] = texelCache[textureId];
		/* 125 */     texelCache[textureId] = null;
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */   public static int[] getTexturePixels(int textureId)
	/*     */   {
		/* 133 */     if (textureId == 1) {
			/* 134 */       textureId = 24;
			/*     */     }
		/* 136 */     textureLastUsed[textureId] = (textureGetCount++);
		/* 137 */     if (texelCache[textureId] != null)
			/* 138 */       return texelCache[textureId];
		/*     */     int[] ai;
		/* 140 */     if (textureTexelPoolPointer > 0) {
			/* 141 */        ai = texelArrayPool[(--textureTexelPoolPointer)];
			/* 142 */       texelArrayPool[textureTexelPoolPointer] = null;
			/*     */     } else {
			/* 144 */       int j = 0;
			/* 145 */       int k = -1;
			/* 146 */       for (int l = 0; l < loadedTextureCount; l++) {
				/* 147 */         if ((texelCache[l] != null) && ((textureLastUsed[l] < j) || (k == -1))) {
					/* 148 */           j = textureLastUsed[l];
					/* 149 */           k = l;
					/*     */         }
				/*     */       }
			/* 152 */       ai = texelCache[k];
			/* 153 */       texelCache[k] = null;
			/*     */     }
		/* 155 */     texelCache[textureId] = ai;
		/* 156 */     Background background = textureImages[textureId];
		/* 157 */     int[] ai1 = texturePalettes[textureId];
		/* 158 */     if (Rasterizer.lowMem) {
			/* 159 */       textureIsTransparent[textureId] = false;
			/* 160 */       for (int i1 = 0; i1 < 4096; i1++) {
				/* 161 */         int i2 = ai[i1] = ai1[background.imgPixels[i1]] & 0xF8F8FF;
				/* 162 */         if (i2 == 0)
					/* 163 */           textureIsTransparent[textureId] = true;
				/* 164 */         ai[(4096 + i1)] = (i2 - (i2 >>> 3) & 0xF8F8FF);
				/* 165 */         ai[(8192 + i1)] = (i2 - (i2 >>> 2) & 0xF8F8FF);
				/* 166 */         ai[(12288 + i1)] = (i2 - (i2 >>> 2) - (i2 >>> 3) & 0xF8F8FF);
				/*     */       }
			/*     */     }
		/*     */     else {
			/* 170 */       if (background.imgWidth == 64) {
				/* 171 */         for (int j1 = 0; j1 < 128; j1++) {
					/* 172 */           for (int j2 = 0; j2 < 128; j2++) {
						/* 173 */             ai[(j2 + (j1 << 7))] = ai1[background.imgPixels[((j2 >> 1) + (j1 >> 1 << 6))]];
						/*     */           }
					/*     */
					/*     */         }
				/*     */       } else {
				/* 178 */         for (int k1 = 0; k1 < 16384; k1++) {
					/* 179 */           ai[k1] = ai1[background.imgPixels[k1]];
					/*     */         }
				/*     */       }
			/* 182 */       textureIsTransparent[textureId] = false;
			/* 183 */       for (int l1 = 0; l1 < 16384; l1++) {
				/* 184 */         ai[l1] &= 0xF8F8FF;
				/* 185 */         int k2 = ai[l1];
				/* 186 */         if (k2 == 0)
					/* 187 */           textureIsTransparent[textureId] = true;
				/* 188 */         ai[(16384 + l1)] = (k2 - (k2 >>> 3) & 0xF8F8FF);
				/* 189 */         ai[(32768 + l1)] = (k2 - (k2 >>> 2) & 0xF8F8FF);
				/* 190 */         ai[(49152 + l1)] = (k2 - (k2 >>> 2) - (k2 >>> 3) & 0xF8F8FF);
				/*     */       }
			/*     */     }
		/*     */
		/* 194 */     return ai;
		/*     */   }
	/*     */ }
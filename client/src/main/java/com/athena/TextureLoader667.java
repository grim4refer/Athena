/*     */ package com.athena;
/*     */
/*     */
/*     */ public class TextureLoader667
		/*     */ {
	/*     */   private static int loadedTextureCount;
	/*     */
	/*     */   private static int textureTexelPoolPointer;
	/*     */   private static int[][] texelArrayPool;
	/*     */   private static int[][] texelCache;
	/*     */   public static int[] textureLastUsed;
	/*     */   public static int textureGetCount;
	/*     */
	/*     */   public static void initTextures(int count, OnDemandFetcher updateManager_)
	/*     */   {
		/*  16 */     Texture.init(count, updateManager_);
		/*  17 */     loadedTextureCount = count;
		/*  18 */     textureLastUsed = new int[count];
		/*  19 */     texelCache = new int[count][];
		/*     */   }
	/*     */
	/*     */   public static void clearTextureCache() {
		/*  23 */     texelArrayPool = null;
		/*  24 */     for (int i = 0; i < loadedTextureCount; i++) {
			/*  25 */       texelCache[i] = null;
			/*     */     }
		/*     */   }
	/*     */
	/*     */   public static void resetTextures()
	/*     */   {
		/*  31 */     if (texelArrayPool == null) {
			/*  32 */       textureTexelPoolPointer = 50;
			/*  33 */       texelArrayPool = new int[textureTexelPoolPointer][65536];
			/*  34 */       for (int k = 0; k < loadedTextureCount; k++) {
				/*  35 */         texelCache[k] = null;
				/*     */       }
			/*     */     }
		/*     */   }
	/*     */
	/*     */   public static void resetTexture(int textureId)
	/*     */   {
		/*  42 */     if (texelCache[textureId] == null) {
			/*  43 */       return;
			/*     */     }
		/*  45 */     texelArrayPool[(textureTexelPoolPointer++)] = texelCache[textureId];
		/*  46 */     texelCache[textureId] = null;
		/*     */   }
	/*     */
	/*     */   public static int[] getTexturePixels(int textureId) {
		/*  50 */     if (textureId == 0)
			/*  51 */       textureId = 24;
		/*  52 */     Texture texture = Texture.get(textureId);
		/*  53 */     if (texture == null) {
			/*  54 */       return null;
			/*     */     }
		/*  56 */     textureLastUsed[textureId] = (textureGetCount++);
		/*  57 */     if (texelCache[textureId] != null) {
			/*  58 */       return texelCache[textureId];
			/*     */     }
		/*     */
		/*     */     int[] texels;
		/*  62 */     if (textureTexelPoolPointer > 0) {
			/*  63 */       texels = texelArrayPool[(--textureTexelPoolPointer)];
			/*  64 */       texelArrayPool[textureTexelPoolPointer] = null;
			/*     */     } else {
			/*  66 */       int lastUsed = 0;
			/*  67 */       int target = -1;
			/*  68 */       for (int i = 0; i < loadedTextureCount; i++) {
				/*  69 */         if ((texelCache[i] != null) && ((textureLastUsed[i] < lastUsed) || (target == -1))) {
					/*  70 */           lastUsed = textureLastUsed[i];
					/*  71 */           target = i;
					/*     */         }
				/*     */       }
			/*     */
			/*  75 */       texels = texelCache[target];
			/*  76 */       texelCache[target] = null;
			/*     */     }
		/*  78 */     texelCache[textureId] = texels;
		/*     */
		/*  80 */     if (texture.width == 64) {
			/*  81 */       for (int y = 0; y < 128; y++) {
				/*  82 */         for (int x = 0; x < 128; x++) {
					/*  83 */           texels[(x + (y << 7))] = texture.getPixel((x >> 1) + (y >> 1 << 6));
					/*     */         }
				/*     */       }
			/*     */     } else {
			/*  87 */       for (int texelPtr = 0; texelPtr < 16384; texelPtr++) {
				/*  88 */         texels[texelPtr] = texture.getPixel(texelPtr);
				/*     */       }
			/*     */     }
		/*  91 */     TextureDef def = (textureId >= 0) && (textureId < TextureDef.textures.length) ? TextureDef.textures[textureId] : null;
		/*  92 */     int blendType = def != null ? def.anInt1226 : 0;
		/*  93 */     if ((blendType != 1) && (blendType != 2)) {
			/*  94 */       blendType = 0;
			/*     */     }
		/*  96 */     for (int texelPtr = 0; texelPtr < 16384; texelPtr++) {
			/*  97 */
			int texel = texels[texelPtr];
			int alpha;
			if (blendType == 2)
				alpha = texel >>> 24;
			else if (blendType == 1)
				alpha = texel != 0 ? 0xff : 0;

			else {
				alpha = texel >>> 24;
				if (def != null && !def.aBoolean1223)
					alpha /= 5;

			}
				/*     */
			/* 111 */       texel &= 0xFFFFFF;
			/* 112 */       if ((textureId == 1) || (textureId == 24)) {
				/* 113 */         texel = adjustBrightnessLinear(texel, 379);
				/*     */       } else
				/* 115 */         texel = adjustBrightnessLinear(texel, 179);
			/* 116 */       if ((textureId == 1) || (textureId == 24)) {
				/* 117 */         texel = adjustBrightness(texel, 0.90093F);
				/*     */       } else
				/* 119 */         texel = adjustBrightness(texel, brightness);
			/* 120 */       texel &= 0xF8F8FF;
			/* 121 */       texels[texelPtr] = (texel | alpha << 24);
			/* 122 */       texels[(16384 + texelPtr)] = (texel - (texel >>> 3) & 0xF8F8FF | alpha << 24);
			/* 123 */       texels[(32768 + texelPtr)] = (texel - (texel >>> 2) & 0xF8F8FF | alpha << 24);
			/* 124 */       texels[(49152 + texelPtr)] = (texel - (texel >>> 3) - (texel >>> 3) & 0xF8F8FF | alpha << 24);
			/*     */     }
		/*     */
		/* 127 */     return texels;
		/*     */   }
	/*     */
	/*     */   public boolean isValid()
	/*     */   {
		/* 132 */     return (texelArrayPool != null) && (texelCache != null);
		/*     */   }
	/*     */
	/* 135 */   private static float brightness = 1.0F;
	/*     */
	/*     */   private static int adjustBrightness(int rgb, float brightness) {
		/* 138 */     return (int)((float)Math.pow((rgb >>> 16) / 256.0F, brightness) * 256.0F) << 16 |
				/* 139 */       (int)((float)Math.pow((rgb >>> 8 & 0xFF) / 256.0F, brightness) * 256.0F) << 8 |
				/* 140 */       (int)((float)Math.pow((rgb & 0xFF) / 256.0F, brightness) * 256.0F);
		/*     */   }
	/*     */
	/*     */
	/*     */   private static int adjustBrightnessLinear(int rgb, int factor)
	/*     */   {
		/* 146 */     return ((rgb >>> 16) * factor & 0xFF00) << 8 |
				/* 147 */       (rgb >>> 8 & 0xFF) * factor & 0xFF00 |
				/* 148 */       (rgb & 0xFF) * factor >> 8;
		/*     */   }
	/*     */
	/*     */   public static void calculateTexturePalette(double brightness) {
		/* 152 */     for (int textureId = 0; textureId != loadedTextureCount; textureId++)
			/* 153 */       resetTexture(textureId);
		/*     */   }
	/*     */
	/*     */   public static void clear() {
		/* 157 */     texelArrayPool = null;
		/* 158 */     texelCache = null;
		/* 159 */     textureLastUsed = null;
		/* 160 */     brightness = 1.0F;
		/*     */   }
	/*     */ }


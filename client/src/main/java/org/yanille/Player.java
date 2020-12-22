/*     */ package org.yanille;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public final class Player
		/*     */   extends Entity
		/*     */ {
	/*  14 */   public int frontLight = 68;
	/*  15 */   public int backLight = 820;
	/*  16 */   public int rightLight = 0;
	/*  17 */   public int middleLight = -1;
	/*  18 */   public int leftLight = 0;
	/*  19 */   public int[][] modifiedColors = new int[12][];
	/*     */   public int constitution;
	/*     */
	/*  22 */   public Model getRotatedModel() { if (!this.visible)
		/*  23 */       return null;
		/*  24 */     Model model = method452();
		/*  25 */     if (model == null)
			/*  26 */       return null;
		/*  27 */     this.height = model.modelHeight;
		/*  28 */     model.rendersWithinOneTile = true;
		/*  29 */     if (this.aBoolean1699)
			/*  30 */       return model;
		/*  31 */     if ((this.anInt1520 != -1) && (this.currentAnim != -1)) {
			/*  32 */       SpotAnim spotAnim = SpotAnim.cache[this.anInt1520];
			/*  33 */       Model model_2 = spotAnim.getModel();
			/*  34 */       if ((spotAnim.animation != null) &&
					/*  35 */         (FrameReader.animationlist[Integer.parseInt(Integer.toHexString(spotAnim.animation.frameIDs[0]).substring(0, Integer.toHexString(spotAnim.animation.frameIDs[0])
/*  36 */         .length() - 4), 16)].length == 0)) {
				/*  37 */         model_2 = null;
				/*     */       }
			/*     */
			/*     */
			/*     */
			/*  42 */       if (model_2 != null) {
				/*  43 */         Model model_3 = new Model(true, FrameReader.isNullFrame(this.currentAnim), false, model_2);
				/*  44 */         model_3.translate(0, -this.graphicHeight, 0);
				/*  45 */         model_3.createBones();
				/*  46 */         model_3.scaleT(132, 132, 132);
				/*  47 */         model_3.applyTransform(spotAnim.animation.frameIDs[this.currentAnim]);
				/*  48 */         model_3.triangleSkin = null;
				/*  49 */         model_3.vertexSkin = null;
				/*  50 */         if ((spotAnim.sizeXY != 128) || (spotAnim.sizeZ != 128))
					/*  51 */           model_3.scaleT(spotAnim.sizeXY, spotAnim.sizeXY, spotAnim.sizeZ);
				/*  52 */         model_3.light(64 + spotAnim.shadow, 850 + spotAnim.lightness, -30, -50, -30, true);
				/*  53 */         Model[] aclass30_sub2_sub4_sub6_1s = { model, model_3 };
				/*  54 */         model = new Model(aclass30_sub2_sub4_sub6_1s);
				/*     */       }
			/*     */     }
		/*  57 */     if (this.tranformIntoModel != null) {
			/*  58 */       if (Client.loopCycle >= this.transformedTimer)
				/*  59 */         this.tranformIntoModel = null;
			/*  60 */       if ((Client.loopCycle >= this.startTimeTransform) && (Client.loopCycle < this.transformedTimer)) {
				/*  61 */         Model model_1 = this.tranformIntoModel;
				/*  62 */         model_1.light(this.frontLight, this.backLight, this.rightLight, this.middleLight, this.leftLight, true);
				/*  63 */         if (this.turnDirection == 512) {
					/*  64 */           model_1.rotateBy90();
					/*  65 */           model_1.rotateBy90();
					/*  66 */           model_1.rotateBy90();
					/*  67 */         } else if (this.turnDirection == 1024) {
					/*  68 */           model_1.rotateBy90();
					/*  69 */           model_1.rotateBy90();
					/*  70 */         } else if (this.turnDirection == 1536) {
					/*  71 */           model_1.rotateBy90(); }
				/*  72 */         Model[] models = { model, model_1 };
				/*  73 */         model = new Model(models);
				/*  74 */         if (this.turnDirection == 512) {
					/*  75 */           model_1.rotateBy90();
					/*  76 */         } else if (this.turnDirection == 1024) {
					/*  77 */           model_1.rotateBy90();
					/*  78 */           model_1.rotateBy90();
					/*  79 */         } else if (this.turnDirection == 1536) {
					/*  80 */           model_1.rotateBy90();
					/*  81 */           model_1.rotateBy90();
					/*  82 */           model_1.rotateBy90();
					/*     */         }
				/*  84 */         model_1.translate(this.x - this.resizeX, this.z - this.resizeZ, this.y - this.resizeY);
				/*     */       }
			/*     */     }
		/*  87 */     model.rendersWithinOneTile = true;
		/*  88 */     return model;
		/*     */   }
	/*     */
	/*     */
	/*     */   public void updatePlayerAppearance(Stream stream)
	/*     */   {
		/*  94 */     stream.currentOffset = 0;
		/*  95 */     this.myGender = stream.readUnsignedByte();
		/*  96 */     this.headIcon = stream.readUnsignedByte();
		/*  97 */     this.bountyHunterIcon = stream.readUnsignedByte();
		/*  98 */     this.skulled = (stream.readUnsignedWord() == 1);
		/*  99 */     if ((this.bountyHunterIcon > 4) && (this.bountyHunterIcon != 255))
			/* 100 */       this.bountyHunterIcon = 4;
		/* 101 */     this.desc = null;
		/* 102 */     this.team = 0;
		/* 103 */     for (int partId = 0; partId < 12; partId++) {
			/* 104 */       int firstByte = stream.readUnsignedByte();
			/* 105 */       if (firstByte == 0) {
				/* 106 */         this.equipment[partId] = 0;
				/*     */       }
			/*     */       else {
				/* 109 */         int secondByte = stream.readUnsignedByte();
				/* 110 */         this.equipment[partId] = ((firstByte << 8) + secondByte);
				/* 111 */         if ((partId == 0) && (this.equipment[0] == 65535)) {
					/* 112 */           this.desc = NPCDef.forID(stream.readUnsignedWord());
					/* 113 */           break;
					/*     */         }
				/* 115 */         if (partId == 1) {
					/* 116 */           int length = stream.readUnsignedByte();
					/* 117 */           if (length > 0) {
						/* 118 */             int[] colors = new int[length];
						/* 119 */             for (int i = 0; i < length; i++) {
							/* 120 */               colors[i] = stream.readInt();
							/*     */             }
						/* 122 */             this.modifiedColors[partId] = colors;
						/*     */
						/* 124 */             this.maxCapeParticleColor = JagexColor.toRGB(colors[0]);
						/*     */           } else {
						/* 126 */             this.modifiedColors[partId] = null;
						/*     */
						/* 128 */             this.maxCapeParticleColor = this.defaultParticleColor;
						/*     */           }
					/* 130 */           modelCache.clear();
					/* 131 */           if (this == Client.myPlayer) {
						/* 132 */             Client.getClient().updateMaxCapeColors(this.modifiedColors[partId]);
						/*     */           }
					/*     */         }
				/* 135 */         if (partId == 8)
					/* 136 */           Client.myHeadAndJaw[0] = (this.equipment[partId] - 256);
				/* 137 */         if (partId == 11)
					/* 138 */           Client.myHeadAndJaw[1] = (this.equipment[partId] - 256);
				/* 139 */         if ((this.equipment[partId] >= 512) && (this.equipment[partId] - 512 < ItemDef.totalItems)) {
					/* 140 */           int l1 = ItemDef.forID(this.equipment[partId] - 512).team;
					/* 141 */           if (l1 != 0)
						/* 142 */             this.team = l1;
					/*     */         }
				/*     */       }
			/*     */     }
		/* 146 */     for (int l = 0; l < 5; l++) {
			/* 147 */       int j1 = stream.readUnsignedByte();
			/* 148 */       if ((j1 < 0) || (j1 >= Client.anIntArrayArray1003[l].length))
				/* 149 */         j1 = 0;
			/* 150 */       this.anIntArray1700[l] = j1;
			/*     */     }
		/*     */
		/* 153 */     this.anInt1511 = stream.readUnsignedWord();
		/* 154 */     if (this.anInt1511 == 65535)
			/* 155 */       this.anInt1511 = -1;
		/* 156 */     this.anInt1512 = stream.readUnsignedWord();
		/* 157 */     if (this.anInt1512 == 65535)
			/* 158 */       this.anInt1512 = -1;
		/* 159 */     this.anInt1554 = stream.readUnsignedWord();
		/* 160 */     if (this.anInt1554 == 65535)
			/* 161 */       this.anInt1554 = -1;
		/* 162 */     this.anInt1555 = stream.readUnsignedWord();
		/* 163 */     if (this.anInt1555 == 65535)
			/* 164 */       this.anInt1555 = -1;
		/* 165 */     this.anInt1556 = stream.readUnsignedWord();
		/* 166 */     if (this.anInt1556 == 65535)
			/* 167 */       this.anInt1556 = -1;
		/* 168 */     this.anInt1557 = stream.readUnsignedWord();
		/* 169 */     if (this.anInt1557 == 65535)
			/* 170 */       this.anInt1557 = -1;
		/* 171 */     this.runAnimation = stream.readUnsignedWord();
		/* 172 */     if (this.runAnimation == 65535) {
			/* 173 */       this.runAnimation = -1;
			/*     */     }
		/* 175 */     this.name = TextClass.fixName(TextClass.nameForLong(stream.readQWord()));
		/* 176 */     this.combatLevel = stream.readUnsignedByte();
		/* 177 */     this.playerRights = stream.readUnsignedWord();
		/* 178 */     this.playerTitle = stream.readString();
		/*     */
		/* 180 */     this.visible = true;
		/* 181 */     this.aLong1718 = 0L;
		/*     */
		/* 183 */     if (this.desc != null) {
			/* 184 */       this.combatLevel = this.desc.combatLevel;
			/* 185 */       this.anInt1511 = this.desc.standAnim;
			/* 186 */       this.anInt1512 = this.desc.standAnim;
			/* 187 */       this.anInt1554 = this.desc.walkAnim;
			/* 188 */       this.anInt1555 = this.desc.standAnim;
			/* 189 */       this.anInt1556 = this.desc.walkAnim;
			/* 190 */       this.anInt1557 = this.desc.walkAnim;
			/* 191 */       this.runAnimation = this.desc.walkAnim;
			/*     */     }
		/*     */
		/*     */
		/* 195 */     for (int k1 = 0; k1 < 12; k1++) {
			/* 196 */       this.aLong1718 <<= 4;
			/* 197 */       if (this.equipment[k1] >= 256) {
				/* 198 */         this.aLong1718 += this.equipment[k1] - 256;
				/*     */       }
			/*     */     }
		/* 201 */     if (this.equipment[0] >= 256)
			/* 202 */       this.aLong1718 += (this.equipment[0] - 256 >> 4);
		/* 203 */     if (this.equipment[1] >= 256)
			/* 204 */       this.aLong1718 += (this.equipment[1] - 256 >> 8);
		/* 205 */     for (int i2 = 0; i2 < 5; i2++) {
			/* 206 */       this.aLong1718 <<= 3;
			/* 207 */       this.aLong1718 += this.anIntArray1700[i2];
			/*     */     }
		/*     */
		/* 210 */     this.aLong1718 <<= 1;
		/* 211 */     this.aLong1718 += this.myGender;
		/*     */   }
	/*     */
	/*     */   public boolean maxCapeIds(int itemId) {
		/* 215 */     return itemId == 14019;
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */   public int maxConstitution;
	/*     */
	/*     */
	/*     */   public int playerRights;
	/*     */
	/*     */
	/*     */   public String playerTitle;
	/*     */
	/*     */   public Model method452()
	/*     */   {
		/* 230 */     if (this.desc != null)
			/*     */     {
			/* 232 */       int currentFrame = -1;
			/* 233 */       int nextFrame = -1;
			/* 234 */       int cycle1 = 0;
			/* 235 */       int cycle2 = 0;
			/* 236 */       if ((this.anim >= 0) && (this.animationDelay == 0)) {
				/* 237 */         Animation animation = Animation.anims[this.anim];
				/* 238 */         currentFrame = animation.frameIDs[this.currentAnimFrame];
				/* 239 */         nextFrame = animation.frameIDs[this.nextAnimationFrame];
				/* 240 */         cycle1 = animation.delays[this.currentAnimFrame];
				/* 241 */         cycle2 = this.anInt1528;
				/* 242 */       } else if (this.anInt1517 >= 0) {
				/* 243 */         Animation animation = Animation.anims[this.anInt1517];
				/* 244 */         currentFrame = animation.frameIDs[this.currentForcedAnimFrame];
				/* 245 */         nextFrame = animation.frameIDs[this.nextIdleAnimationFrame];
				/* 246 */         cycle1 = animation.delays[this.currentForcedAnimFrame];
				/* 247 */         cycle2 = this.anInt1519;
				/*     */       }
			/* 249 */       Model model = this.desc.method164(-1, currentFrame, null, nextFrame, cycle1, cycle2);
			/* 250 */       return model;
			/*     */     }
		/*     */
		/*     */
		/*     */
		/* 255 */     long l = this.aLong1718;
		/* 256 */     int currentFrame = -1;
		/* 257 */     int nextFrame = -1;
		/* 258 */     int cycle1 = 0;
		/* 259 */     int cycle2 = 0;
		/* 260 */     int i1 = -1;
		/* 261 */     int j1 = -1;
		/* 262 */     int k1 = -1;
		/* 263 */     if ((this.anim >= 0) && (this.animationDelay == 0))
			/*     */     {
			/* 265 */       Animation animation = Animation.anims[this.anim];
			/* 266 */       currentFrame = animation.frameIDs[this.currentAnimFrame];
			/* 267 */       if (this.nextAnimationFrame < animation.frameIDs.length)
				/* 268 */         nextFrame = animation.frameIDs[this.nextAnimationFrame];
			/* 269 */       cycle1 = animation.delays[this.currentAnimFrame];
			/* 270 */       cycle2 = this.anInt1528;
			/* 271 */       if ((this.anInt1517 >= 0) && (this.anInt1517 != this.anInt1511))
				/* 272 */         i1 = Animation.anims[this.anInt1517].frameIDs[this.currentForcedAnimFrame];
			/* 273 */       if (animation.leftHandItem >= 0)
				/*     */       {
				/* 275 */         j1 = animation.leftHandItem;
				/* 276 */         l += (j1 - this.equipment[5] << 40);
				/*     */       }
			/* 278 */       if (animation.rightHandItem >= 0)
				/*     */       {
				/* 280 */         k1 = animation.rightHandItem;
				/* 281 */         l += (k1 - this.equipment[3] << 48);
				/*     */       }
			/*     */     }
		/* 284 */     else if (this.anInt1517 >= 0) {
			/* 285 */       Animation animation = Animation.anims[this.anInt1517];
			/* 286 */       currentFrame = animation.frameIDs[this.currentForcedAnimFrame];
			/* 287 */       nextFrame = animation.frameIDs[this.nextIdleAnimationFrame];
			/* 288 */       cycle1 = animation.delays[this.currentForcedAnimFrame];
			/* 289 */       cycle2 = this.anInt1519;
			/*     */     }
		/*     */
		/*     */
		/* 293 */     Model model_1 = (Model)modelCache.get(l);
		/* 294 */     if (model_1 == null) {
			/* 295 */       boolean fetchModels = false;
			/* 296 */       for (int bodyPartId = 0; bodyPartId < 12; bodyPartId++) {
				/* 297 */         int partId = this.equipment[bodyPartId];
				/* 298 */         if ((k1 >= 0) && (bodyPartId == 3))
					/* 299 */           partId = k1;
				/* 300 */         if ((j1 >= 0) && (bodyPartId == 5))
					/* 301 */           partId = j1;
				/* 302 */         if (partId - 256 != 846)
					/*     */         {
					/* 304 */           if ((partId >= 256) && (partId < 512) && (!IDK.cache[(partId - 256)].bodyModelIsFetched()))
						/* 305 */             fetchModels = true;
					/* 306 */           if ((partId >= 512) && (!ItemDef.forID(partId - 512).equipModelFetched(this.myGender))) {
						/* 307 */             fetchModels = true;
						/*     */           }
					/*     */         }
				/*     */       }
			/*     */
			/* 312 */       if (fetchModels) {
				/* 313 */         if (this.aLong1697 != -1L)
					/* 314 */           model_1 = (Model)modelCache.get(this.aLong1697);
				/* 315 */         if (model_1 == null)
					/* 316 */           return null;
				/*     */       }
			/*     */     }
		/* 319 */     boolean hasMaxCape = false;
		/* 320 */     if (model_1 == null) {
			/* 321 */       Model[] bodyPartModels = new Model[13];
			/* 322 */       int j2 = 0;
			/* 323 */       for (int currentPart = 0; currentPart < 12; currentPart++) {
				/* 324 */         int i3 = this.equipment[currentPart];
				/* 325 */         if ((k1 >= 0) && (currentPart == 3))
					/* 326 */           i3 = k1;
				/* 327 */         if ((j1 >= 0) && (currentPart == 5))
					/* 328 */           i3 = j1;
				/* 329 */         if ((i3 >= 256) && (i3 < 512)) {
					/* 330 */           Model model_3 = null;
					/* 331 */           if (i3 - 256 < IDK.cache.length)
						/* 332 */             model_3 = IDK.cache[(i3 - 256)].fetchBodyModel();
					/* 333 */           if (model_3 != null)
						/* 334 */             bodyPartModels[(j2++)] = model_3;
					/*     */         }
				/* 336 */         if (i3 >= 512) {
					/* 337 */           int itemId = i3 - 512;
					/* 338 */           ItemDef def = ItemDef.forID(i3 - 512);
					/* 339 */           Model model_4 = def.getEquipModel(this.myGender);
					/* 340 */           if (maxCapeIds(itemId)) {
						/* 341 */             hasMaxCape = true;
						/*     */           }
					/* 343 */           if (this.modifiedColors[currentPart] != null) {
						/* 344 */             for (int i11 = 0; i11 < def.editedModelColor.length; i11++)
							/* 345 */               model_4.recolour(def.editedModelColor[i11], this.modifiedColors[currentPart][i11]);
						/*     */           }
					/* 347 */           if (model_4 != null) {
						/* 348 */             bodyPartModels[(j2++)] = model_4;
						/*     */           }
					/*     */         }
				/*     */       }
			/* 352 */       model_1 = new Model(j2, bodyPartModels);
			/* 353 */       for (int j3 = 0; j3 < 5; j3++) {
				/* 354 */         if (this.anIntArray1700[j3] != 0) {
					/* 355 */           model_1.recolour(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][this.anIntArray1700[j3]]);
					/* 356 */           if (j3 == 1)
						/* 357 */             model_1.recolour(Client.anIntArray1204[0], Client.anIntArray1204[this.anIntArray1700[j3]]);
					/*     */         }
				/*     */       }
			/* 360 */       model_1.createBones();
			/* 361 */       model_1.light(this.frontLight, this.backLight, this.rightLight, this.middleLight, this.leftLight, true);
			/* 365 */       modelCache.put(model_1, l);
			/* 366 */       this.aLong1697 = l;
			/*     */     }
		/* 368 */     if (this.aBoolean1699)
			/* 369 */       return model_1;
		/* 370 */     Model model_2 = Model.entityModelDesc;
		/* 371 */     model_2.method464(model_1, FrameReader.isNullFrame(currentFrame) & FrameReader.isNullFrame(i1));
		/* 372 */     if ((currentFrame != -1) && (i1 != -1)) {
			/* 373 */       model_2.method471(Animation.anims[this.anim].animationFlowControl, i1, currentFrame);
			/* 374 */     } else if ((currentFrame != -1) && (nextFrame != -1)) {
			/* 375 */       model_2.applyTransform(currentFrame, nextFrame, cycle1, cycle2);
			/*     */     } else
			/* 377 */       model_2.applyTransform(currentFrame);
		/* 378 */     model_2.calculateDiagonals();
		/* 379 */     model_2.triangleSkin = null;
		/* 380 */     model_2.vertexSkin = null;

		/* 384 */     return model_2;
		/*     */   }
	/*     */
	/*     */   public boolean isVisible() {
		/* 388 */     return this.visible;
		/*     */   }
	/*     */
	/*     */
	/*     */
	/*     */   public Model getPlayerModel()
	/*     */   {
		/* 395 */     if (!this.visible)
			/* 396 */       return null;
		/* 397 */     if (this.desc != null)
			/* 398 */       return this.desc.getHeadModel();
		/* 399 */     boolean flag = false;
		/* 400 */     for (int i = 0; i < 12; i++) {
			/* 401 */       int j = this.equipment[i];
			/*     */       try {
				/* 403 */         if ((j >= 256) && (j < 512) && (!IDK.cache[(j - 256)].headModelFetched()))
					/* 404 */           flag = true;
				/* 405 */         if ((j >= 512) && (!ItemDef.forID(j - 512).dialogueModelFetched(this.myGender))) {
					/* 406 */           flag = true;
					/*     */         }
				/*     */       } catch (Exception e) {
				/* 409 */         flag = true;
				/*     */       }
			/*     */     }
		/*     */
		/* 413 */     if (flag)
			/* 414 */       return null;
		/* 415 */     Model[] models = new Model[12];
		/* 416 */     int k = 0;
		/* 417 */     for (int l = 0; l < 12; l++) {
			/* 418 */       int i1 = this.equipment[l];
			/* 419 */       if ((i1 >= 256) && (i1 < 512)) {
				/* 420 */         Model model_1 = IDK.cache[(i1 - 256)].fetchHeadModel();
				/* 421 */         if (model_1 != null)
					/* 422 */           models[(k++)] = model_1;
				/*     */       }
			/* 424 */       if (i1 >= 512) {
				/* 425 */         Model model_2 = ItemDef.forID(i1 - 512).getDialogueModel(this.myGender);
				/* 426 */         if (model_2 != null) {
					/* 427 */           models[(k++)] = model_2;
					/*     */         }
				/*     */       }
			/*     */     }
		/* 431 */     Model model = new Model(k, models);
		/* 432 */     for (int j1 = 0; j1 < 5; j1++) {
			/* 433 */       if (this.anIntArray1700[j1] != 0) {
				/* 434 */         model.recolour(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][this.anIntArray1700[j1]]);
				/* 435 */         if (j1 == 1)
					/* 436 */           model.recolour(Client.anIntArray1204[0], Client.anIntArray1204[this.anIntArray1700[j1]]);
				/*     */       }
			/*     */     }
		/* 439 */     return model;
		/*     */   }
	/*     */
	/*     */   Player() {
		/* 443 */     this.aLong1697 = -1L;
		/* 444 */     this.aBoolean1699 = false;
		/* 445 */     this.anIntArray1700 = new int[5];
		/* 446 */     this.visible = false;
		/* 447 */     this.anInt1715 = 9;
		/* 448 */     this.equipment = new int[12];
		/*     */   }
	/*     */
	/* 451 */   private int defaultParticleColor = 9883875;
	/* 452 */   private int maxCapeParticleColor = this.defaultParticleColor;
	/*     */   private long aLong1697;
	/*     */   public NPCDef desc;
	/*     */   boolean aBoolean1699;
	/*     */   final int[] anIntArray1700;
	/*     */   public int team;
	/*     */   public int myGender;
	/*     */   public String name;
	/* 460 */   static MemCache modelCache = new MemCache(260);
	/*     */   public int combatLevel;
	/*     */   public int headIcon;
	/*     */   public int bountyHunterIcon;
	/*     */   public int hintIcon;
	/*     */   public boolean skulled;
	/*     */   public int summonLevel;
	/*     */   public int SummonLevel;
	/*     */   public int startTimeTransform;
	/*     */   int transformedTimer;
	/*     */   int z;
	/*     */   boolean visible;
	/*     */   int resizeX;
	/*     */   int resizeZ;
	/*     */   int resizeY;
	/*     */   Model tranformIntoModel;
	/*     */   private int anInt1715;
	/*     */   public final int[] equipment;
	/*     */   private long aLong1718;
	/*     */   int extendedXMin;
	/*     */   int extendedYMin;
	/*     */   int extendedXMax;
	/*     */   int extendedYMax;
	/* 483 */   boolean updateColor = true;
	/*     */ }

package com.athena;


import java.awt.*;

public class Model extends Animable {

	public static int MAX_POLYGON = 23762;

	private static boolean upscaled = true;

	private static int[] OFFSETS_512_334 = null;

	private static int[] OFFSETS_765_503 = null;

	public static boolean newmodel[];

	public static String ccString = "Cla";

	public static String xxString = "at Cl";
	public static String vvString = "nt";

	public static String aString9_9 = "" + ccString + "n Ch" + xxString + "ie" + vvString + " ";

	public static int anInt1620;

	public static Model entityModelDesc = new Model(true);

	protected static int anIntArray1622[] = new int[3000];

	protected static int anIntArray1623[] = new int[3000];

	protected static int anIntArray1624[] = new int[3000];

	protected static int anIntArray1625[] = new int[3000];

	static  ModelHeader modelHeader[];

	static OnDemandFetcherParent resourceManager;

	static boolean hasAnEdgeToRestrict[] = new boolean[MAX_POLYGON];

	static boolean outOfReach[] = new boolean[MAX_POLYGON];

	static int projected_vertex_x[] = new int[MAX_POLYGON];

	static int projected_vertex_y[] = new int[MAX_POLYGON];

	static int projected_vertex_z[] = new int[MAX_POLYGON];

	static int anIntArray1667[] = new int[MAX_POLYGON];

	static int camera_vertex_y[] = new int[MAX_POLYGON];

	static int camera_vertex_x[] = new int[MAX_POLYGON];

	static int camera_vertex_z[] = new int[MAX_POLYGON];//jesus fucking christ why are all these 65535? 65k?> l0l no wonder mem usage went up through the roof jesus

	static int depthListIndices[] = new int[MAX_POLYGON];

	static int faceLists[][] = new int[MAX_POLYGON][512];

	static int anIntArray1673[] = new int[12];

	static int anIntArrayArray1674[][] = new int[12][MAX_POLYGON];

	static int anIntArray1675[] = new int[MAX_POLYGON];

	static int anIntArray1676[] = new int[MAX_POLYGON];

	static int anIntArray1677[] = new int[12];

	static int anIntArray1678[] = new int[10];

	static int anIntArray1679[] = new int[10];

	static int anIntArray1680[] = new int[10];

	static int anIntArray1638[] = new int[10];

	static int anInt1681;

	static int anInt1682;

	static int anInt1683;

	public static boolean objectExists;

	public static int lineOffsets[];

	public static int currentCursorX;

	public static int currentCursorY;

	public static int objectsRendered;
	public static int objectsInCurrentRegion[] = new int[1000];
	public static int mapObjectIds[] = new int[1000];
	public static int SINE[];

	public static int COSINE[];

	static int hsl2rgb[];

	static int lightDecay[];

	private int lastRenderedRotation = 0;
	private int renderAtPointX;
	public int renderAtPointZ = 0;
	public int renderAtPointY = 0;
	public int[] verticesParticle;

	static {
		SINE = Rasterizer.SINE;
		COSINE = Rasterizer.COSINE;
		hsl2rgb = Rasterizer.hsl2rgb;
		lightDecay = Rasterizer.light_decay;
	}

	public static Model fetchModel(int j) {
		if (modelHeader == null)
			return null;
		if (j == 0)
			return null;
		ModelHeader class21 = modelHeader[j];
		if (class21 == null) {
			resourceManager.get(j);
			return null;
		} else {
			return new Model(j);
		}
	}

	public static int[] getOffsets(int j, int k) {
		if (j == 512 && k == 334 && OFFSETS_512_334 != null)
			return OFFSETS_512_334;

		if (j == 765 + 1 && k == 503 && OFFSETS_765_503 != null)
			return OFFSETS_765_503;

		int[] t = new int[k];
		for (int l = 0; l < k; l++)
			t[l] = j * l;

		if (j == 512 && k == 334)
			OFFSETS_512_334 = t;

		if (j == 765 + 1 && k == 503)
			OFFSETS_765_503 = t;

		return t;
	}

	public static void initialise(int i, OnDemandFetcherParent onDemandFetcher) {
		modelHeader = new ModelHeader[150000];
		newmodel = new boolean[150000];
		resourceManager = onDemandFetcher;
	}

	public static int method481(int i, int j, int k) {
		if (i == 65535)
			return 0;
		if ((k & 2) == 2) {
			if (j < 0)
				j = 0;
			else if (j > 127)
				j = 127;
			j = 127 - j;
			return j;
		}

		j = j * (i & 0x7f) >> 7;
		if (j < 2)
			j = 2;
		else if (j > 126)
			j = 126;
		return (i & 0xff80) + j;
	}

	public static boolean modelIsFetched(int i) {
		if (modelHeader == null)
			return false;

		ModelHeader class21 = modelHeader[i];
		if (class21 == null) {
			resourceManager.get(i);
			return false;
		} else {
			return true;
		}
	}



	public static void nullLoader() {
		modelHeader = null;
		hasAnEdgeToRestrict = null;
		outOfReach = null;
		projected_vertex_y = null;
		anIntArray1667 = null;
		camera_vertex_y = null;
		camera_vertex_x = null;
		camera_vertex_z = null;
		depthListIndices = null;
		faceLists = null;
		anIntArray1673 = null;
		anIntArrayArray1674 = null;
		anIntArray1675 = null;
		anIntArray1676 = null;
		anIntArray1677 = null;
		SINE = null;
		COSINE = null;
		hsl2rgb = null;
		lightDecay = null;
	}

	public static void readFirstModelData(byte[] abyte0, int j) {
		/*      */     try {
			/*  222 */       if (abyte0 == null) {
				/*  223 */         ModelHeader class21 = modelHeader[j] = new ModelHeader();
				/*  224 */         class21.verticeCount = 0;
				/*  225 */         class21.triangleCount = 0;
				/*  226 */         class21.texturedTriangleCount = 0;
				/*  227 */         return;
				/*      */       }
			/*  229 */       Stream stream = new Stream(abyte0);
			/*  230 */       stream.currentOffset = (abyte0.length - 18);
			/*  231 */       ModelHeader class21_1 = modelHeader[j] = new ModelHeader();
			/*  232 */       class21_1.modelData = abyte0;
			/*  233 */       class21_1.verticeCount = stream.readUnsignedWord();
			/*  234 */       class21_1.triangleCount = stream.readUnsignedWord();
			/*  235 */       class21_1.texturedTriangleCount = stream.readUnsignedByte();
			/*  236 */       int k = stream.readUnsignedByte();
			/*  237 */       int l = stream.readUnsignedByte();
			/*  238 */       int i1 = stream.readUnsignedByte();
			/*  239 */       int j1 = stream.readUnsignedByte();
			/*  240 */       int k1 = stream.readUnsignedByte();
			/*  241 */       int l1 = stream.readUnsignedWord();
			/*  242 */       int i2 = stream.readUnsignedWord();
			/*  243 */       int j2 = stream.readUnsignedWord();
			/*  244 */       int k2 = stream.readUnsignedWord();
			/*  245 */       int l2 = 0;
			/*  246 */       class21_1.verticesModOffset = l2;
			/*  247 */       l2 += class21_1.verticeCount;
			/*  248 */       class21_1.triMeshLinkOffset = l2;
			/*  249 */       l2 += class21_1.triangleCount;
			/*  250 */       class21_1.facePriorityBasePos = l2;
			/*  251 */       if (l == 255) {
				/*  252 */         l2 += class21_1.triangleCount;
				/*      */       } else
				/*  254 */         class21_1.facePriorityBasePos = (-l - 1);
			/*  255 */       class21_1.tskinBasepos = l2;
			/*  256 */       if (j1 == 1) {
				/*  257 */         l2 += class21_1.triangleCount;
				/*      */       } else
				/*  259 */         class21_1.tskinBasepos = -1;
			/*  260 */       class21_1.drawTypeBasePos = l2;
			/*  261 */       if (k == 1) {
				/*  262 */         l2 += class21_1.triangleCount;
				/*      */       } else
				/*  264 */         class21_1.drawTypeBasePos = -1;
			/*  265 */       class21_1.vskinBasePos = l2;
			/*  266 */       if (k1 == 1) {
				/*  267 */         l2 += class21_1.verticeCount;
				/*      */       } else
				/*  269 */         class21_1.vskinBasePos = -1;
			/*  270 */       class21_1.alphaBasepos = l2;
			/*  271 */       if (i1 == 1) {
				/*  272 */         l2 += class21_1.triangleCount;
				/*      */       } else
				/*  274 */         class21_1.alphaBasepos = -1;
			/*  275 */       class21_1.triVPointOffset = l2;
			/*  276 */       l2 += k2;
			/*  277 */       class21_1.triColourOffset = l2;
			/*  278 */       l2 += class21_1.triangleCount * 2;
			/*  279 */       class21_1.textureInfoBasePos = l2;
			/*  280 */       l2 += class21_1.texturedTriangleCount * 6;
			/*  281 */       class21_1.verticesXOffset = l2;
			/*  282 */       l2 += l1;
			/*  283 */       class21_1.verticesYOffset = l2;
			/*  284 */       l2 += i2;
			/*  285 */       class21_1.verticesZOffset = l2;
			/*  286 */       l2 += j2;
			/*      */     }
		/*      */     catch (Exception localException) {}
		/*      */   }
	/*      */
	/*      */   public static void removeFromHeader(int j) {
		/*  292 */     modelHeader[j] = null;
		/*      */   }
	/*      */
	/*  295 */   private boolean filtered = false;
	/*      */
	/*      */



	private boolean aBoolean1618;
	public int numberOfVerticeCoordinates;
	public int verticesXCoordinate[];
	public int verticesYCoordinate[];
	public int verticesZCoordinate[];
	public int numberOfTriangleFaces;
	public int face_a[];
	public int face_b[];
	public int face_c[];
	public int face_shade_a[];
	public int face_shade_b[];
	public int face_shade_c[];
	public int face_render_type[];
	public int face_render_priorities[];
	public int face_alpha[];
	public int face_color[];
	public int face_priority;
	public int numberOfTexturesFaces;
	public int textures_face_a[];
	public int textures_face_b[];
	public int textures_face_c[];
	public int anInt1646;
	public int anInt1647;
	public int anInt1648;
	public int anInt1649;
	public int anInt1650;
	public int anInt1651;
	public int diagonal3D;
	public int anInt1653;
	public int myPriority;
	public int vertexVSkin[];
	public int triangleTSkin[];
	public int vertexSkin[][];
	public int triangleSkin[][];
	public boolean rendersWithinOneTile;
	VertexNormal vertexNormalOffset[];

	public Model() {
	}

	private Model(boolean flag) {
		aBoolean1618 = true;
		rendersWithinOneTile = false;
	}

	public Model(boolean flag, boolean flag1, boolean flag2, Model model) {
		/*  342 */     this.aBoolean1618 = true;
		/*  343 */     this.rendersWithinOneTile = false;
		/*  344 */     anInt1620 += 1;
		/*  345 */     this.numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		/*  346 */     this.numberOfTriangleFaces = model.numberOfTriangleFaces;
		/*  347 */     this.numberOfTexturesFaces = model.numberOfTexturesFaces;
		/*  348 */     if (flag2) {
			/*  349 */       this.verticesParticle = model.verticesParticle;
			/*  350 */       this.verticesXCoordinate = model.verticesXCoordinate;
			/*  351 */       this.verticesYCoordinate = model.verticesYCoordinate;
			/*  352 */       this.verticesZCoordinate = model.verticesZCoordinate;
			/*      */     } else {
			/*  354 */       this.verticesParticle = new int[this.numberOfVerticeCoordinates];
			/*  355 */       this.verticesXCoordinate = new int[this.numberOfVerticeCoordinates];
			/*  356 */       this.verticesYCoordinate = new int[this.numberOfVerticeCoordinates];
			/*  357 */       this.verticesZCoordinate = new int[this.numberOfVerticeCoordinates];
			/*  358 */       for (int j = 0; j < this.numberOfVerticeCoordinates; j++) {
				/*  359 */         this.verticesParticle[j] = model.verticesParticle[j];
				/*  360 */         this.verticesXCoordinate[j] = model.verticesXCoordinate[j];
				/*  361 */         this.verticesYCoordinate[j] = model.verticesYCoordinate[j];
				/*  362 */         this.verticesZCoordinate[j] = model.verticesZCoordinate[j];
				/*      */       }
			/*      */     }
		/*      */
		/*  366 */     if (flag) {
			/*  367 */       this.face_color = model.face_color;
			/*      */     } else {
			/*  369 */       this.face_color = new int[this.numberOfTriangleFaces];
			/*  370 */       for (int k = 0; k < this.numberOfTriangleFaces; k++) {
				/*  371 */         this.face_color[k] = model.face_color[k];
				/*      */       }
			/*      */     }
		/*  374 */     if (flag1) {
			/*  375 */       this.face_alpha = model.face_alpha;
			/*      */     } else {
			/*  377 */       this.face_alpha = new int[this.numberOfTriangleFaces];
			/*  378 */       if (model.face_alpha == null) {
				/*  379 */         for (int l = 0; l < this.numberOfTriangleFaces; l++) {
					/*  380 */           this.face_alpha[l] = 0;
					/*      */         }
				/*      */       } else {
				/*  383 */         for (int i1 = 0; i1 < this.numberOfTriangleFaces; i1++) {
					/*  384 */           this.face_alpha[i1] = model.face_alpha[i1];
					/*      */         }
				/*      */       }
			/*      */     }
		/*  388 */     this.vertexVSkin = model.vertexVSkin;
		/*  389 */     this.triangleTSkin = model.triangleTSkin;
		/*  390 */     this.face_render_type = model.face_render_type;
		/*  391 */     this.face_a = model.face_a;
		/*  392 */     this.face_b = model.face_b;
		/*  393 */     this.face_c = model.face_c;
		/*  394 */     this.face_render_priorities = model.face_render_priorities;
		/*  395 */     this.face_priority = model.face_priority;
		/*  396 */     this.textures_face_a = model.textures_face_a;
		/*  397 */     this.textures_face_b = model.textures_face_b;
		/*  398 */     this.textures_face_c = model.textures_face_c;
		/*      */   }
	/*      */
	/*      */   public Model(boolean flag, boolean flag1, Model model) {
		/*  402 */     this.aBoolean1618 = true;
		/*  403 */     this.rendersWithinOneTile = false;
		/*  404 */     anInt1620 += 1;
		/*  405 */     this.numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		/*  406 */     this.numberOfTriangleFaces = model.numberOfTriangleFaces;
		/*  407 */     this.numberOfTexturesFaces = model.numberOfTexturesFaces;
		/*  408 */     if (flag) {
			/*  409 */       this.verticesYCoordinate = new int[this.numberOfVerticeCoordinates];
			/*  410 */       for (int j = 0; j < this.numberOfVerticeCoordinates; j++) {
				/*  411 */         this.verticesYCoordinate[j] = model.verticesYCoordinate[j];
				/*      */       }
			/*      */     } else {
			/*  414 */       this.verticesYCoordinate = model.verticesYCoordinate;
			/*      */     }
		/*  416 */     if (flag1) {
			/*  417 */       this.face_shade_a = new int[this.numberOfTriangleFaces];
			/*  418 */       this.face_shade_b = new int[this.numberOfTriangleFaces];
			/*  419 */       this.face_shade_c = new int[this.numberOfTriangleFaces];
			/*  420 */       for (int k = 0; k < this.numberOfTriangleFaces; k++) {
				/*  421 */         this.face_shade_a[k] = model.face_shade_a[k];
				/*  422 */         this.face_shade_b[k] = model.face_shade_b[k];
				/*  423 */         this.face_shade_c[k] = model.face_shade_c[k];
				/*      */       }
			/*      */
			/*  426 */       this.face_render_type = new int[this.numberOfTriangleFaces];
			/*  427 */       if (model.face_render_type == null) {
				/*  428 */         for (int l = 0; l < this.numberOfTriangleFaces; l++) {
					/*  429 */           this.face_render_type[l] = 0;
					/*      */         }
				/*      */       } else {
				/*  432 */         for (int i1 = 0; i1 < this.numberOfTriangleFaces; i1++) {
					/*  433 */           this.face_render_type[i1] = model.face_render_type[i1];
					/*      */         }
				/*      */       }
			/*  436 */       this.vertexNormals = new VertexNormal[this.numberOfVerticeCoordinates];
			/*  437 */       for (int j1 = 0; j1 < this.numberOfVerticeCoordinates; j1++) {
				/*  438 */         VertexNormal vertNormal = this.vertexNormals[j1] = new VertexNormal();
				/*  439 */         VertexNormal class33_1 = model.vertexNormals[j1];
				/*  440 */         vertNormal.anInt602 = class33_1.anInt602;
				/*  441 */         vertNormal.anInt603 = class33_1.anInt603;
				/*  442 */         vertNormal.anInt604 = class33_1.anInt604;
				/*  443 */         vertNormal.anInt605 = class33_1.anInt605;
				/*      */       }
			/*      */
			/*  446 */       this.vertexNormalOffset = model.vertexNormalOffset;
			/*      */     } else {
			/*  448 */       this.face_shade_a = model.face_shade_a;
			/*  449 */       this.face_shade_b = model.face_shade_b;
			/*  450 */       this.face_shade_c = model.face_shade_c;
			/*  451 */       this.face_render_type = model.face_render_type;
			/*      */     }
		/*  453 */     this.verticesParticle = model.verticesParticle;
		/*  454 */     this.verticesXCoordinate = model.verticesXCoordinate;
		/*  455 */     this.verticesZCoordinate = model.verticesZCoordinate;
		/*  456 */     this.face_color = model.face_color;
		/*  457 */     this.face_alpha = model.face_alpha;
		/*  458 */     this.face_render_priorities = model.face_render_priorities;
		/*  459 */     this.face_priority = model.face_priority;
		/*  460 */     this.face_a = model.face_a;
		/*  461 */     this.face_b = model.face_b;
		/*  462 */     this.face_c = model.face_c;
		/*  463 */     this.textures_face_a = model.textures_face_a;
		/*  464 */     this.textures_face_b = model.textures_face_b;
		/*  465 */     this.textures_face_c = model.textures_face_c;
		/*  466 */     this.modelHeight = model.modelHeight;
		/*      */
		/*  468 */     this.anInt1650 = model.anInt1650;
		/*  469 */     this.anInt1653 = model.anInt1653;
		/*  470 */     this.diagonal3D = model.diagonal3D;
		/*  471 */     this.anInt1646 = model.anInt1646;
		/*  472 */     this.anInt1648 = model.anInt1648;
		/*  473 */     this.anInt1649 = model.anInt1649;
		/*  474 */     this.anInt1647 = model.anInt1647;
		/*      */   }
	public Model(int modelId) {
		/*  478 */     byte[] is = modelHeader[modelId].modelData;
		/*  479 */     if ((is[(is.length - 1)] == -1) && (is[(is.length - 2)] == -1)) {
			/*  480 */       read622Model(is, modelId);
			/*      */     } else
			/*  482 */       readOldModel(modelId);
		/*  483 */     int[] newBoots = { 29249, 29254, 29250, 94427,29255, 29252, 29253, 19855 };
		               int[] priorityFix = new int[] {64000};
		/*  484 */     int[] arrayOfInt1; int j = (arrayOfInt1 = newBoots).length; for (int i = 0; i < j; i++) {  i = arrayOfInt1[i];
			/*  485 */       if (modelId == i) {
				/*  486 */         for (j = 0; j < this.face_render_priorities.length; j++)
					/*  487 */           this.face_render_priorities[j] = 10;
				/*      */       }
			/*      */     }
		/*  490 */     if (newmodel[modelId]) {
			/*  491 */       if (Configuration.upscaling) {
				/*  492 */         scale2(32, 32, 32);
				/*      */       }
			/*      */
			/*  495 */       if (modelId != 74011) {
				/*  496 */         recolour(0, 255);
				/*      */       }
			/*      */		if (this.face_render_priorities != null) {
			/*  499 */       for (j = 0; j < this.face_render_priorities.length; j++) {
				/*  500 */         this.face_render_priorities[j] = 10;
				/*      */       }
			}
			/*      */     }
		if (face_render_priorities == null) {
			face_render_priorities = new int[numberOfTriangleFaces];
		}
		for (int i = 0; i < face_render_priorities.length; i++) {
			face_render_priorities[i] = 10;
		}
		/*  503 */     if ((modelId == 48841) || (modelId == 48825) || (modelId == 48817) || (modelId == 48802) || (modelId == 48840) ||
				/*  504 */       (modelId == 45536) || (modelId == 38284) || (modelId == 45522) || (modelId == 45517) || (modelId == 45514) ||
				/*  505 */       (modelId == 45490) || (modelId == 48790) || (modelId == 59583)) {
			/*  506 */       scaleT(32, 32, 32);
			/*  507 */       translate(0, 6, 0);
			/*      */     }
		/*  509 */     if (modelId == 65478) {
			/*  510 */       scaleT(32, 32, 32);
			/*  511 */       translate(0, 0, 0);
			/*      */     }
		/*      */
		/*  514 */     int[][] attachments = ParticleAttachment.getAttachments(modelId);
		/*  515 */     if (attachments != null) {
			/*  516 */       for (int n = 0; n < attachments.length; n++) {
				/*  517 */         int[] attach = attachments[n];
				/*  518 */         if (attach[0] == -1) {
					/*  519 */           for (int z = 0; z < this.face_a.length; z++) {
						/*  520 */             this.verticesParticle[this.face_a[z]] = (attach[1] + 1);
						/*      */           }
					/*  522 */         } else if (attach[0] == -2) {
					/*  523 */           for (int z = 0; z < this.face_b.length; z++) {
						/*  524 */             this.verticesParticle[this.face_b[z]] = (attach[1] + 1);
						/*      */           }
					/*  526 */         } else if (attach[0] == -3) {
					/*  527 */           for (int z = 0; z < this.face_c.length; z++) {
						/*  528 */             this.verticesParticle[this.face_c[z]] = (attach[1] + 1);
						/*      */           }
					/*  530 */         } else if (attach[0] == -4) {
					/*  531 */           for (int z = 0; z < this.face_a.length; z++) {
						/*  532 */             this.verticesParticle[this.face_a[z]] = (attach[1] + 1);
						/*      */           }
					/*      */
					/*  535 */           for (int z = 0; z < this.face_b.length; z++) {
						/*  536 */             this.verticesParticle[this.face_b[z]] = (attach[1] + 1);
						/*      */           }
					/*      */
					/*  539 */           for (int z = 0; z < this.face_c.length; z++) {
						/*  540 */             this.verticesParticle[this.face_c[z]] = (attach[1] + 1);
						/*      */           }
					/*      */         } else {
					/*  543 */           this.verticesParticle[attach[0]] = (attach[1] + 1);
					/*      */         }
				/*      */       }
			/*      */     }
		/*      */   }
	/*      */   public Model(int i, Model[] amodel) {
		/*  550 */     this.aBoolean1618 = true;
		/*  551 */     this.rendersWithinOneTile = false;
		/*  552 */     anInt1620 += 1;
		/*  553 */     boolean flag = false;
		/*  554 */     boolean flag1 = false;
		/*  555 */     boolean flag2 = false;
		/*  556 */     boolean flag3 = false;
		/*  557 */     this.numberOfVerticeCoordinates = 0;
		/*  558 */     this.numberOfTriangleFaces = 0;
		/*  559 */     this.numberOfTexturesFaces = 0;
		/*  560 */     this.face_priority = -1;
		/*  561 */     for (int k = 0; k < i; k++) {
			/*  562 */       Model model = amodel[k];
			/*  563 */       if (model != null) {
				/*  564 */         this.numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
				/*  565 */         this.numberOfTriangleFaces += model.numberOfTriangleFaces;
				/*  566 */         this.numberOfTexturesFaces += model.numberOfTexturesFaces;
				/*  567 */         flag |= model.face_render_type != null;
				/*  568 */         if (model.face_render_priorities != null) {
					/*  569 */           flag1 = true;
					/*      */         } else {
					/*  571 */           if (this.face_priority == -1)
						/*  572 */             this.face_priority = model.face_priority;
					/*  573 */           if (this.face_priority != model.face_priority)
						/*  574 */             flag1 = true;
					/*      */         }
				/*  576 */         flag2 |= model.face_alpha != null;
				/*  577 */         flag3 |= model.triangleTSkin != null;
				/*      */       }
			/*      */     }
		/*      */
		/*  581 */     this.verticesParticle = new int[this.numberOfVerticeCoordinates];
		/*  582 */     this.verticesXCoordinate = new int[this.numberOfVerticeCoordinates];
		/*  583 */     this.verticesYCoordinate = new int[this.numberOfVerticeCoordinates];
		/*  584 */     this.verticesZCoordinate = new int[this.numberOfVerticeCoordinates];
		/*  585 */     this.vertexVSkin = new int[this.numberOfVerticeCoordinates];
		/*  586 */     this.face_a = new int[this.numberOfTriangleFaces];
		/*  587 */     this.face_b = new int[this.numberOfTriangleFaces];
		/*  588 */     this.face_c = new int[this.numberOfTriangleFaces];
		/*  589 */     this.textures_face_a = new int[this.numberOfTexturesFaces];
		/*  590 */     this.textures_face_b = new int[this.numberOfTexturesFaces];
		/*  591 */     this.textures_face_c = new int[this.numberOfTexturesFaces];
		/*  592 */     if (flag)
			/*  593 */       this.face_render_type = new int[this.numberOfTriangleFaces];
		/*  594 */     if (flag1)
			/*  595 */       this.face_render_priorities = new int[this.numberOfTriangleFaces];
		/*  596 */     if (flag2)
			/*  597 */       this.face_alpha = new int[this.numberOfTriangleFaces];
		/*  598 */     if (flag3)
			/*  599 */       this.triangleTSkin = new int[this.numberOfTriangleFaces];
		/*  600 */     this.face_color = new int[this.numberOfTriangleFaces];
		/*  601 */     this.numberOfVerticeCoordinates = 0;
		/*  602 */     this.numberOfTriangleFaces = 0;
		/*  603 */     this.numberOfTexturesFaces = 0;
		/*  604 */     int l = 0;
		/*  605 */     for (int i1 = 0; i1 < i; i1++) {
			/*  606 */       Model model_1 = amodel[i1];
			/*  607 */       if (model_1 != null) {
				/*  608 */         for (int j1 = 0; j1 < model_1.numberOfTriangleFaces; j1++) {
					/*  609 */           if (flag)
						/*  610 */             if (model_1.face_render_type == null) {
							/*  611 */               this.face_render_type[this.numberOfTriangleFaces] = 0;
							/*      */             } else {
							/*  613 */               int k1 = model_1.face_render_type[j1];
							/*  614 */               if ((k1 & 0x2) == 2)
								/*  615 */                 k1 += (l << 2);
							/*  616 */               this.face_render_type[this.numberOfTriangleFaces] = k1;
							/*      */             }
					/*  618 */           if (flag1)
						/*  619 */             if (model_1.face_render_priorities == null) {
							/*  620 */               this.face_render_priorities[this.numberOfTriangleFaces] = model_1.face_priority;
							/*      */             } else
							/*  622 */               this.face_render_priorities[this.numberOfTriangleFaces] = model_1.face_render_priorities[j1];
					/*  623 */           if (flag2) {
						/*  624 */             if (model_1.face_alpha == null) {
							/*  625 */               this.face_alpha[this.numberOfTriangleFaces] = 0;
							/*      */             } else
							/*  627 */               this.face_alpha[this.numberOfTriangleFaces] = model_1.face_alpha[j1];
						/*      */           }
					/*  629 */           if ((flag3) && (model_1.triangleTSkin != null))
						/*  630 */             this.triangleTSkin[this.numberOfTriangleFaces] = model_1.triangleTSkin[j1];
					/*  631 */           this.face_color[this.numberOfTriangleFaces] = model_1.face_color[j1];
					/*  632 */           this.face_a[this.numberOfTriangleFaces] = method465(model_1, model_1.face_a[j1]);
					/*  633 */           this.face_b[this.numberOfTriangleFaces] = method465(model_1, model_1.face_b[j1]);
					/*  634 */           this.face_c[this.numberOfTriangleFaces] = method465(model_1, model_1.face_c[j1]);
					/*  635 */           this.numberOfTriangleFaces += 1;
					/*      */         }
				/*      */
				/*  638 */         for (int l1 = 0; l1 < model_1.numberOfTexturesFaces; l1++) {
					/*  639 */           this.textures_face_a[this.numberOfTexturesFaces] = method465(model_1, model_1.textures_face_a[l1]);
					/*  640 */           this.textures_face_b[this.numberOfTexturesFaces] = method465(model_1, model_1.textures_face_b[l1]);
					/*  641 */           this.textures_face_c[this.numberOfTexturesFaces] = method465(model_1, model_1.textures_face_c[l1]);
					/*  642 */           this.numberOfTexturesFaces += 1;
					/*      */         }
				/*      */
				/*  645 */         l += model_1.numberOfTexturesFaces;
				/*      */       }
			/*      */     }
		/*      */   }


	/*      */   public Model(Model[] amodel)
	/*      */   {
		/*  652 */     int i = 2;
		/*  653 */     this.aBoolean1618 = true;
		/*  654 */     this.rendersWithinOneTile = false;
		/*  655 */     anInt1620 += 1;
		/*  656 */     boolean flag1 = false;
		/*  657 */     boolean flag2 = false;
		/*  658 */     boolean flag3 = false;
		/*  659 */     boolean flag4 = false;
		/*  660 */     this.numberOfVerticeCoordinates = 0;
		/*  661 */     this.numberOfTriangleFaces = 0;
		/*  662 */     this.numberOfTexturesFaces = 0;
		/*  663 */     this.face_priority = -1;
		/*  664 */     for (int k = 0; k < i; k++) {
			/*  665 */       Model model = amodel[k];
			/*  666 */       if (model != null) {
				/*  670 */         this.numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
				/*  671 */         this.numberOfTriangleFaces += model.numberOfTriangleFaces;
				/*  672 */         this.numberOfTexturesFaces += model.numberOfTexturesFaces;
				/*  673 */         flag1 |= model.face_render_type != null;
				/*  674 */         if (model.face_render_priorities != null) {
					/*  675 */           flag2 = true;
					/*      */         } else {
					/*  677 */           if (this.face_priority == -1)
						/*  678 */             this.face_priority = model.face_priority;
					/*  679 */           if (this.face_priority != model.face_priority)
						/*  680 */             flag2 = true;
					/*      */         }
				/*  682 */         flag3 |= model.face_alpha != null;
				/*  683 */         flag4 |= model.face_color != null;
				/*      */       }
			/*      */     }
		/*      */
		/*  687 */     this.verticesParticle = new int[this.numberOfVerticeCoordinates];
		/*  688 */     this.verticesXCoordinate = new int[this.numberOfVerticeCoordinates];
		/*  689 */     this.verticesYCoordinate = new int[this.numberOfVerticeCoordinates];
		/*  690 */     this.verticesZCoordinate = new int[this.numberOfVerticeCoordinates];
		/*  691 */     this.face_a = new int[this.numberOfTriangleFaces];
		/*  692 */     this.face_b = new int[this.numberOfTriangleFaces];
		/*  693 */     this.face_c = new int[this.numberOfTriangleFaces];
		/*  694 */     this.face_shade_a = new int[this.numberOfTriangleFaces];
		/*  695 */     this.face_shade_b = new int[this.numberOfTriangleFaces];
		/*  696 */     this.face_shade_c = new int[this.numberOfTriangleFaces];
		/*  697 */     this.textures_face_a = new int[this.numberOfTexturesFaces];
		/*  698 */     this.textures_face_b = new int[this.numberOfTexturesFaces];
		/*  699 */     this.textures_face_c = new int[this.numberOfTexturesFaces];
		/*  700 */     if (flag1)
			/*  701 */       this.face_render_type = new int[this.numberOfTriangleFaces];
		/*  702 */     if (flag2)
			/*  703 */       this.face_render_priorities = new int[this.numberOfTriangleFaces];
		/*  704 */     if (flag3)
			/*  705 */       this.face_alpha = new int[this.numberOfTriangleFaces];
		/*  706 */     if (flag4)
			/*  707 */       this.face_color = new int[this.numberOfTriangleFaces];
		/*  708 */     this.numberOfVerticeCoordinates = 0;
		/*  709 */     this.numberOfTriangleFaces = 0;
		/*  710 */     this.numberOfTexturesFaces = 0;
		/*  711 */     int i1 = 0;
		/*  712 */     for (int j1 = 0; j1 < i; j1++) {
			/*  713 */       Model model_1 = amodel[j1];
			/*  714 */       if (model_1 != null) {
				/*  715 */         int k1 = this.numberOfVerticeCoordinates;
				/*  716 */         for (int l1 = 0; l1 < model_1.numberOfVerticeCoordinates; l1++) {
					/*  717 */           this.verticesParticle[this.numberOfVerticeCoordinates] = model_1.verticesParticle[l1];
					/*  718 */           this.verticesXCoordinate[this.numberOfVerticeCoordinates] = model_1.verticesXCoordinate[l1];
					/*  719 */           this.verticesYCoordinate[this.numberOfVerticeCoordinates] = model_1.verticesYCoordinate[l1];
					/*  720 */           this.verticesZCoordinate[this.numberOfVerticeCoordinates] = model_1.verticesZCoordinate[l1];
					/*  721 */           this.numberOfVerticeCoordinates += 1;
					/*      */         }
				/*      */
				/*  724 */         for (int i2 = 0; i2 < model_1.numberOfTriangleFaces; i2++) {
					/*  725 */           this.face_a[this.numberOfTriangleFaces] = (model_1.face_a[i2] + k1);
					/*  726 */           this.face_b[this.numberOfTriangleFaces] = (model_1.face_b[i2] + k1);
					/*  727 */           this.face_c[this.numberOfTriangleFaces] = (model_1.face_c[i2] + k1);
					/*  728 */           this.face_shade_a[this.numberOfTriangleFaces] = model_1.face_shade_a[i2];
					/*  729 */           this.face_shade_b[this.numberOfTriangleFaces] = model_1.face_shade_b[i2];
					/*  730 */           this.face_shade_c[this.numberOfTriangleFaces] = model_1.face_shade_c[i2];
					/*  731 */           if (flag1)
						/*  732 */             if (model_1.face_render_type == null) {
							/*  733 */               this.face_render_type[this.numberOfTriangleFaces] = 0;
							/*      */             } else {
							/*  735 */               int j2 = model_1.face_render_type[i2];
							/*  736 */               if ((j2 & 0x2) == 2)
								/*  737 */                 j2 += (i1 << 2);
							/*  738 */               this.face_render_type[this.numberOfTriangleFaces] = j2;
							/*      */             }
					/*  740 */           if (flag2)
						/*  741 */             if (model_1.face_render_priorities == null) {
							/*  742 */               this.face_render_priorities[this.numberOfTriangleFaces] = model_1.face_priority;
							/*      */             } else
							/*  744 */               this.face_render_priorities[this.numberOfTriangleFaces] = model_1.face_render_priorities[i2];
					/*  745 */           if (flag3)
						/*  746 */             if (model_1.face_alpha == null) {
							/*  747 */               this.face_alpha[this.numberOfTriangleFaces] = 0;
							/*      */             } else
							/*  749 */               this.face_alpha[this.numberOfTriangleFaces] = model_1.face_alpha[i2];
					/*  750 */           if ((flag4) && (model_1.face_color != null)) {
						/*  751 */             this.face_color[this.numberOfTriangleFaces] = model_1.face_color[i2];
						/*      */           }
					/*  753 */           this.numberOfTriangleFaces += 1;
					/*      */         }
				/*      */
				/*  756 */         for (int k2 = 0; k2 < model_1.numberOfTexturesFaces; k2++) {
					/*  757 */           this.textures_face_a[this.numberOfTexturesFaces] = (model_1.textures_face_a[k2] + k1);
					/*  758 */           this.textures_face_b[this.numberOfTexturesFaces] = (model_1.textures_face_b[k2] + k1);
					/*  759 */           this.textures_face_c[this.numberOfTexturesFaces] = (model_1.textures_face_c[k2] + k1);
					/*  760 */           this.numberOfTexturesFaces += 1;
					/*      */         }
				/*      */
				/*  763 */         i1 += model_1.numberOfTexturesFaces;
				/*      */       }
			/*      */     }
		/*      */
		/*  767 */     calculateDiagonals();
		/*      */   }

	public void applyTransform(int i) {
		if (vertexSkin == null)
			return;
		if (i == -1)
			return;
		FrameReader class36 = FrameReader.forID(i);
		if (class36 == null)
			return;
		SkinList class18 = class36.mySkinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		for (int k = 0; k < class36.stepCount; k++) {
			int l = class36.opCodeLinkTable[k];
			method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k],
					class36.zOffset[k]);
		}

	}

	public void mixTransform(int ai[], int j, int k) {
		if (k == -1)
			return;
		if (ai == null || j == -1) {
			applyTransform(k);
			return;
		}
		FrameReader class36 = FrameReader.forID(k);
		if (class36 == null)
			return;
		FrameReader class36_1 = FrameReader.forID(j);
		if (class36_1 == null) {
			applyTransform(k);
			return;
		}
		SkinList class18 = class36.mySkinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		int l = 0;
		int i1 = ai[l++];
		for (int j1 = 0; j1 < class36.stepCount; j1++) {
			int k1;
			for (k1 = class36.opCodeLinkTable[j1]; k1 > i1; i1 = ai[l++])
				;
			if (k1 != i1 || class18.opcodes[k1] == 0)
				method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1], class36.zOffset[j1]);
		}

		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		l = 0;
		i1 = ai[l++];
		for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
			int i2;
			for (i2 = class36_1.opCodeLinkTable[l1]; i2 > i1; i1 = ai[l++])
				;
			if (i2 == i1 || class18.opcodes[i2] == 0)
				method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1], class36_1.zOffset[l1]);
		}
	}

	public void applyTransform(int firstFrame, int nextFrame, int end, int cycle) {

		try {
			if (vertexSkin != null && firstFrame != -1) {
				FrameReader currentAnimation = FrameReader.forID(firstFrame);
				SkinList list1 = currentAnimation.mySkinList;
				anInt1681 = 0;
				anInt1682 = 0;
				anInt1683 = 0;
				FrameReader nextAnimation = null;
				SkinList list2 = null;
				if (nextFrame != -1) {
					nextAnimation = FrameReader.forID(nextFrame);
					if (nextAnimation.mySkinList != list1)
						nextAnimation = null;
					list2 = nextAnimation.mySkinList;
				}
				if(nextAnimation == null || list2 == null) {
					for (int i_263_ = 0; i_263_ < currentAnimation.stepCount; i_263_++) {
						int i_264_ = currentAnimation.opCodeLinkTable[i_263_];
						method472(list1.opcodes[i_264_], list1.skinList[i_264_], currentAnimation.xOffset[i_263_], currentAnimation.yOffset[i_263_], currentAnimation.zOffset[i_263_]);

					}
				} else {
					for (int i1 = 0; i1 < currentAnimation.stepCount; i1++) {
						int n1 = currentAnimation.opCodeLinkTable[i1];
						int opcode = list1.opcodes[n1];
						int[] skin = list1.skinList[n1];
						int x = currentAnimation.xOffset[i1];
						int y = currentAnimation.yOffset[i1];
						int z = currentAnimation.zOffset[i1];
						boolean found = false;
						for (int i2 = 0; i2 < nextAnimation.stepCount; i2++) {
							int n2 = nextAnimation.opCodeLinkTable[i2];
							if (list2.skinList[n2].equals(skin)) {
								if (opcode != 2) {
									x += (nextAnimation.xOffset[i2] - x) * cycle / end;
									y += (nextAnimation.yOffset[i2] - y) * cycle / end;
									z += (nextAnimation.zOffset[i2] - z) * cycle / end;
								} else {
									x &= 0x7ff;
									y &= 0x7ff;
									z &= 0x7ff;
									int dx = nextAnimation.xOffset[i2] - x & 0x7ff;
									int dy = nextAnimation.yOffset[i2] - y & 0x7ff;
									int dz = nextAnimation.zOffset[i2] - z & 0x7ff;
									if (dx >= 1024) {
										dx -= 2048;
									}
									if (dy >= 1024) {
										dy -= 2048;
									}
									if (dz >= 1024) {
										dz -= 2048;
									}
									x = x + dx * cycle / end & 0x7ff;
									y = y + dy * cycle / end & 0x7ff;
									z = z + dz * cycle / end & 0x7ff;
								}
								found = true;
								break;
							}
						}
						if (!found) {
							if (opcode != 3 && opcode != 2) {
								x = x * (end - cycle) / end;
								y = y * (end - cycle) / end;
								z = z * (end - cycle) / end;
							} else if (opcode == 3) {
								x = (x * (end - cycle) + (cycle << 7)) / end;
								y = (y * (end - cycle) + (cycle << 7)) / end;
								z = (z * (end - cycle) + (cycle << 7)) / end;
							} else {
								x &= 0x7ff;
								y &= 0x7ff;
								z &= 0x7ff;
								int dx = -x & 0x7ff;
								int dy = -y & 0x7ff;
								int dz = -z & 0x7ff;
								if (dx >= 1024) {
									dx -= 2048;
								}
								if (dy >= 1024) {
									dy -= 2048;
								}
								if (dz >= 1024) {
									dz -= 2048;
								}
								x = x + dx * cycle / end & 0x7ff;
								y = y + dy * cycle / end & 0x7ff;
								z = z + dz * cycle / end & 0x7ff;
							}
						}
						method472(opcode, skin, x, y, z);
					}
				}
			}

		} catch(Exception e) {
			//e.printStackTrace();
			applyTransform(firstFrame); //Cheap fix
		}
	}

	public void applyTransform_2(int i, int frame2) {
		if (vertexSkin == null)
			return;
		if (i == -1)
			return;
		FrameReader class36 = FrameReader.forID(i);
		if (class36 == null)
			return;
		FrameReader class36_1 = FrameReader.forID(frame2);
		FrameReader fr = FrameReader.getTween(class36, class36_1);
		class36 = fr;
		SkinList class18 = class36.mySkinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		for (int k = 0; k < class36.stepCount; k++) {
			int l = class36.opCodeLinkTable[k];
			method472(class18.opcodes[l], class18.skinList[l], class36.xOffset[k], class36.yOffset[k],
					class36.zOffset[k]);
		}

	}

	public void calcDiagonalsAndStats(int i) {
		super.modelHeight = 0;
		anInt1650 = 0;
		anInt1651 = 0;
		anInt1646 = 0xf423f;
		anInt1647 = 0xfff0bdc1;
		anInt1648 = 0xfffe7961;
		anInt1649 = 0x1869f;
		for (int j = 0; j < numberOfVerticeCoordinates; j++) {
			int k = verticesXCoordinate[j];
			int l = verticesYCoordinate[j];
			int i1 = verticesZCoordinate[j];
			if (k < anInt1646)
				anInt1646 = k;
			if (k > anInt1647)
				anInt1647 = k;
			if (i1 < anInt1649)
				anInt1649 = i1;
			if (i1 > anInt1648)
				anInt1648 = i1;
			if (-l > super.modelHeight)
				super.modelHeight = -l;
			if (l > anInt1651)
				anInt1651 = l;
			int j1 = k * k + i1 * i1;
			if (j1 > anInt1650)
				anInt1650 = j1;
		}

		anInt1650 = (int) Math.sqrt(anInt1650);
		anInt1653 = (int) Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight);
		if (i != 21073) {
			return;
		} else {
			diagonal3D = anInt1653 + (int) Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651);
			return;
		}
	}

	public void calculateDiagonals() {
		super.modelHeight = 0;
		anInt1650 = 0;
		anInt1651 = 0;
		for (int i = 0; i < numberOfVerticeCoordinates; i++) {
			int j = verticesXCoordinate[i];
			int k = verticesYCoordinate[i];
			int l = verticesZCoordinate[i];
			if (-k > super.modelHeight)
				super.modelHeight = -k;
			if (k > anInt1651)
				anInt1651 = k;
			int i1 = j * j + l * l;
			if (i1 > anInt1650)
				anInt1650 = i1;
		}
		anInt1650 = (int) (Math.sqrt(anInt1650) + 0.98999999999999999D);
		anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight)
				+ 0.98999999999999999D);
		diagonal3D = anInt1653
				+ (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
	}

	public void createBones() {
		if (vertexVSkin != null) {
			int ai[] = new int[256];
			int j = 0;
			for (int l = 0; l < numberOfVerticeCoordinates; l++) {
				int j1 = vertexVSkin[l];
				ai[j1]++;
				if (j1 > j)
					j = j1;
			}
			vertexSkin = null;
			vertexSkin = new int[j + 1][];
			for (int k1 = 0; k1 <= j; k1++) {
				vertexSkin[k1] = new int[ai[k1]];
				ai[k1] = 0;
			}

			for (int j2 = 0; j2 < numberOfVerticeCoordinates; j2++) {
				int l2 = vertexVSkin[j2];
				vertexSkin[l2][ai[l2]++] = j2;
			}

			vertexVSkin = null;
			ai = null;
		}
		if (triangleTSkin != null) {
			int ai1[] = new int[256];
			int k = 0;
			for (int i1 = 0; i1 < numberOfTriangleFaces; i1++) {
				int l1 = triangleTSkin[i1];
				ai1[l1]++;
				if (l1 > k)
					k = l1;
			}
			triangleSkin = null;
			triangleSkin = new int[k + 1][];
			for (int i2 = 0; i2 <= k; i2++) {
				triangleSkin[i2] = new int[ai1[i2]];
				ai1[i2] = 0;
			}

			for (int k2 = 0; k2 < numberOfTriangleFaces; k2++) {
				int i3 = triangleTSkin[k2];
				triangleSkin[i3][ai1[i3]++] = k2;
			}

			triangleTSkin = null;
			ai1 = null;
		}
	}

	private final boolean cursorOn(int cursorX, int cursorY, int proj_vertex_yA, int proj_vertex_yB, int proj_vertex_yC,
								   int proj_vertex_xA, int proj_vertex_xB, int proj_vertex_xC) {
		if (cursorY < proj_vertex_yA && cursorY < proj_vertex_yB && cursorY < proj_vertex_yC)
			return false;
		if (cursorY > proj_vertex_yA && cursorY > proj_vertex_yB && cursorY > proj_vertex_yC)
			return false;
		if (cursorX < proj_vertex_xA && cursorX < proj_vertex_xB && cursorX < proj_vertex_xC)
			return false;
		return cursorX <= proj_vertex_xA || cursorX <= proj_vertex_xB || cursorX <= proj_vertex_xC;
	}

	public void filterTriangles() {
		for (int triangleId = 0; triangleId < numberOfTriangleFaces; triangleId++) {
			int l = face_a[triangleId];
			int k1 = face_b[triangleId];
			int j2_ = face_c[triangleId];
			boolean b = true;
			label2: for (int triId = 0; triId < numberOfTriangleFaces; triId++) {
				if (triId == triangleId)
					continue label2;
				if (face_a[triId] == l) {
					b = false;
					break label2;
				}
				if (face_b[triId] == k1) {
					b = false;
					break label2;
				}
				if (face_c[triId] == j2_) {
					b = false;
					break label2;
				}
			}
			if (b) {
				if (face_render_type != null)
					// face_render_type[triangleId] = -1;
					face_alpha[triangleId] = 255;

			}
		}
	}

	public void light(int i, int j, int k, int l, int i1, boolean flag) {
		try {
			int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
			int k1 = j * j1 >> 8;
			if (face_shade_a == null) {
				face_shade_a = new int[numberOfTriangleFaces];
				face_shade_b = new int[numberOfTriangleFaces];
				face_shade_c = new int[numberOfTriangleFaces];
			}
			if (super.vertexNormals == null) {
				super.vertexNormals = new VertexNormal[numberOfVerticeCoordinates];
				for (int l1 = 0; l1 < numberOfVerticeCoordinates; l1++)
					super.vertexNormals[l1] = new VertexNormal();

			}
			for (int i2 = 0; i2 < numberOfTriangleFaces; i2++) {
				if (face_color != null && face_alpha != null) {
					if (face_color[i2] == 65535 || face_color[i2] == 1 || face_color[i2] == 16705
							|| face_color[i2] == 255)
						face_alpha[i2] = 255;
				}
				int j2 = face_a[i2];
				int l2 = face_b[i2];
				int i3 = face_c[i2];
				int j3 = verticesXCoordinate[l2] - verticesXCoordinate[j2];
				int k3 = verticesYCoordinate[l2] - verticesYCoordinate[j2];
				int l3 = verticesZCoordinate[l2] - verticesZCoordinate[j2];
				int i4 = verticesXCoordinate[i3] - verticesXCoordinate[j2];
				int j4 = verticesYCoordinate[i3] - verticesYCoordinate[j2];
				int k4 = verticesZCoordinate[i3] - verticesZCoordinate[j2];
				int l4 = k3 * k4 - j4 * l3;
				int i5 = l3 * i4 - k4 * j3;
				int j5;
				for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192
						|| j5 < -8192; j5 >>= 1) {
					l4 >>= 1;
					i5 >>= 1;
				}

				int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
				if (k5 <= 0)
					k5 = 1;
				l4 = (l4 * 256) / k5;
				i5 = (i5 * 256) / k5;
				j5 = (j5 * 256) / k5;

				if (face_render_type == null || (face_render_type[i2] & 1) == 0) {

					VertexNormal vNormal = super.vertexNormals[j2];
					vNormal.anInt602 += l4;
					vNormal.anInt603 += i5;
					vNormal.anInt604 += j5;
					vNormal.anInt605++;
					vNormal = super.vertexNormals[l2];
					vNormal.anInt602 += l4;
					vNormal.anInt603 += i5;
					vNormal.anInt604 += j5;
					vNormal.anInt605++;
					vNormal = super.vertexNormals[i3];
					vNormal.anInt602 += l4;
					vNormal.anInt603 += i5;
					vNormal.anInt604 += j5;
					vNormal.anInt605++;
					vNormal = null;

				} else {

					int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
					face_shade_a[i2] = method481(face_color[i2], l5, face_render_type[i2]);

				}
			}

			if (flag) {
				method480(i, k1, k, l, i1);
			} else {
				vertexNormalOffset = new VertexNormal[numberOfVerticeCoordinates];
				for (int k2 = 0; k2 < numberOfVerticeCoordinates; k2++) {
					VertexNormal vNormal = super.vertexNormals[k2];
					vertexNormalOffset[k2] = new VertexNormal();
					vertexNormalOffset[k2].anInt602 = vNormal.anInt602;
					vertexNormalOffset[k2].anInt603 = vNormal.anInt603;
					vertexNormalOffset[k2].anInt604 = vNormal.anInt604;
					vertexNormalOffset[k2].anInt605 = vNormal.anInt605;
				}

			}
			if (flag) {
				calculateDiagonals();
				return;
			} else {
				calcDiagonalsAndStats(21073);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void method464(Model model, boolean flag) {
		numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		numberOfTriangleFaces = model.numberOfTriangleFaces;
		numberOfTexturesFaces = model.numberOfTexturesFaces;
		if (anIntArray1622.length < numberOfVerticeCoordinates) {
			anIntArray1622 = new int[numberOfVerticeCoordinates + 10000];
			anIntArray1623 = new int[numberOfVerticeCoordinates + 10000];
			anIntArray1624 = new int[numberOfVerticeCoordinates + 10000];
		}
		verticesParticle = new int[numberOfVerticeCoordinates];
		verticesXCoordinate = anIntArray1622;
		verticesYCoordinate = anIntArray1623;
		verticesZCoordinate = anIntArray1624;
		for (int k = 0; k < numberOfVerticeCoordinates; k++) {
			verticesParticle[k] = model.verticesParticle[k];
			verticesXCoordinate[k] = model.verticesXCoordinate[k];
			verticesYCoordinate[k] = model.verticesYCoordinate[k];
			verticesZCoordinate[k] = model.verticesZCoordinate[k];
		}

		if (flag) {
			face_alpha = model.face_alpha;
		} else {
			if (anIntArray1625.length < numberOfTriangleFaces)
				anIntArray1625 = new int[numberOfTriangleFaces + 100];
			face_alpha = anIntArray1625;
			if (model.face_alpha == null) {
				for (int l = 0; l < numberOfTriangleFaces; l++)
					face_alpha[l] = 0;

			} else {
				for (int i1 = 0; i1 < numberOfTriangleFaces; i1++)
					face_alpha[i1] = model.face_alpha[i1];

			}
		}
		face_render_type = model.face_render_type;
		face_color = model.face_color;
		face_render_priorities = model.face_render_priorities;
		face_priority = model.face_priority;
		triangleSkin = model.triangleSkin;
		vertexSkin = model.vertexSkin;
		face_a = model.face_a;
		face_b = model.face_b;
		face_c = model.face_c;
		face_shade_a = model.face_shade_a;
		face_shade_b = model.face_shade_b;
		face_shade_c = model.face_shade_c;
		textures_face_a = model.textures_face_a;
		textures_face_b = model.textures_face_b;
		textures_face_c = model.textures_face_c;
	}

	private final int method465(Model model, int i) {
		int j = -1;
		int var4 = model.verticesParticle[i];
		int k = model.verticesXCoordinate[i];
		int l = model.verticesYCoordinate[i];
		int i1 = model.verticesZCoordinate[i];
		for (int j1 = 0; j1 < numberOfVerticeCoordinates; j1++) {
			if (k != verticesXCoordinate[j1] || l != verticesYCoordinate[j1] || i1 != verticesZCoordinate[j1])
				continue;
			j = j1;
			break;
		}

		if (j == -1) {
			verticesParticle[numberOfVerticeCoordinates] = var4;
			verticesXCoordinate[numberOfVerticeCoordinates] = k;
			verticesYCoordinate[numberOfVerticeCoordinates] = l;
			verticesZCoordinate[numberOfVerticeCoordinates] = i1;
			if (model.vertexVSkin != null)
				vertexVSkin[numberOfVerticeCoordinates] = model.vertexVSkin[i];
			j = numberOfVerticeCoordinates++;
		}
		return j;
	}
	public void method1337(int j) {
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			face_color[k] = j;
		}
	}

	public void method1333(int j,int modelId, int npcId) {
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			for(int i = 0; i < NPCDef.forID(npcId).models.length; i++) {
				if(modelId == NPCDef.forID(npcId).models[i]) {
					face_color[k] = j;
				}
			}
		}
	}

	public void method1338(int j) {
		j += 100;
		int kcolor = 0;
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			kcolor = face_color[k];
			if (k + j >= 0)
				face_color[k] = kcolor + j;
		}
	}

	public void method1339(int j) {
		j += 1;
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			if (k + j >= 0)
				face_color[k] = k + j;
		}
	}
	public void method471(int ai[], int j, int k) {
		if (k == -1)
			return;
		if (ai == null || j == -1) {
			applyTransform(k);
			return;
		}
		FrameReader class36 = FrameReader.forID(k);
		if (class36 == null)
			return;
		FrameReader class36_1 = FrameReader.forID(j);
		if (class36_1 == null) {
			applyTransform(k);
			return;
		}
		SkinList class18 = class36.mySkinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		int l = 0;
		int i1 = ai[l++];
		for (int j1 = 0; j1 < class36.stepCount; j1++) {
			int k1;
			for (k1 = class36.opCodeLinkTable[j1]; k1 > i1; i1 = ai[l++])
				;
			if (k1 != i1 || class18.opcodes[k1] == 0)
				method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1],
						class36.zOffset[j1]);
		}

		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		l = 0;
		i1 = ai[l++];
		try {
			for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
				int i2;
				for (i2 = class36_1.opCodeLinkTable[l1]; i2 > i1; i1 = ai[l++])
					;
				if (i2 == i1 || class18.opcodes[i2] == 0)
					method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1],
							class36_1.zOffset[l1]);
			}
		} catch (Exception e) {
		}

	}

	public void method471_2(int mixingData[], int j, int frameId, int frameId2) {
		if (frameId == -1)
			return;
		if (mixingData == null || j == -1) {
			applyTransform(frameId);
			return;
		}
		FrameReader class36 = FrameReader.forID(frameId);
		if (class36 == null)
			return;
		FrameReader class36_1 = FrameReader.forID(j);
		if (class36_1 == null) {
			applyTransform(frameId);
			return;
		}
		FrameReader fr = FrameReader.forID(frameId2);
		FrameReader tween = FrameReader.getTween(class36_1, fr);
		class36 = tween;
		SkinList class18 = class36.mySkinList;
		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		int l = 0;
		int i1 = mixingData[l++];
		for (int j1 = 0; j1 < class36.stepCount; j1++) {
			int k1;
			for (k1 = class36.opCodeLinkTable[j1]; k1 > i1; i1 = mixingData[l++])
				;
			if (k1 != i1 || class18.opcodes[k1] == 0)
				method472(class18.opcodes[k1], class18.skinList[k1], class36.xOffset[j1], class36.yOffset[j1],
						class36.zOffset[j1]);
		}

		anInt1681 = 0;
		anInt1682 = 0;
		anInt1683 = 0;
		l = 0;
		i1 = mixingData[l++];
		for (int l1 = 0; l1 < class36_1.stepCount; l1++) {
			int i2;
			for (i2 = class36_1.opCodeLinkTable[l1]; i2 > i1; i1 = mixingData[l++])
				;
			if (i2 == i1 || class18.opcodes[i2] == 0)
				method472(class18.opcodes[i2], class18.skinList[i2], class36_1.xOffset[l1], class36_1.yOffset[l1],
						class36_1.zOffset[l1]);
		}

	}

	/*private void method472(int i, int ai[], int j, int k, int l) {

		int i1 = ai.length;
		if (i == 0) {
			int j1 = 0;
			anInt1681 = 0;
			anInt1682 = 0;
			anInt1683 = 0;
			for (int k2 = 0; k2 < i1; k2++) {
				int l3 = ai[k2];
				if (l3 < vertexSkin.length) {
					int ai5[] = vertexSkin[l3];
					for (int i5 = 0; i5 < ai5.length; i5++) {
						int j6 = ai5[i5];
						anInt1681 += verticesXCoordinate[j6];
						anInt1682 += verticesYCoordinate[j6];
						anInt1683 += verticesZCoordinate[j6];
						j1++;
					}

				}
			}

			if (j1 > 0) {
				anInt1681 = anInt1681 / j1 + j;
				anInt1682 = anInt1682 / j1 + k;
				anInt1683 = anInt1683 / j1 + l;
				return;
			} else {
				anInt1681 = j;
				anInt1682 = k;
				anInt1683 = l;
				return;
			}
		}
		if (i == 1) {
			for (int k1 = 0; k1 < i1; k1++) {
				int l2 = ai[k1];
				if (l2 < vertexSkin.length) {
					int ai1[] = vertexSkin[l2];
					for (int i4 = 0; i4 < ai1.length; i4++) {
						int j5 = ai1[i4];
						verticesXCoordinate[j5] += j;
						verticesYCoordinate[j5] += k;
						verticesZCoordinate[j5] += l;
					}

				}
			}

			return;
		}
		if (i == 2) {
			for (int l1 = 0; l1 < i1; l1++) {
				int i3 = ai[l1];
				if (i3 < vertexSkin.length) {
					int ai2[] = vertexSkin[i3];
					for (int j4 = 0; j4 < ai2.length; j4++) {
						int k5 = ai2[j4];
						verticesXCoordinate[k5] -= anInt1681;
						verticesYCoordinate[k5] -= anInt1682;
						verticesZCoordinate[k5] -= anInt1683;
						int k6 = (j & 0xff) * 8;
						int l6 = (k & 0xff) * 8;
						int i7 = (l & 0xff) * 8;
						if (i7 != 0) {
							int j7 = SINE[i7];
							int i8 = COSINE[i7];
							int l8 = verticesYCoordinate[k5] * j7 + verticesXCoordinate[k5] * i8 >> 16;
							verticesYCoordinate[k5] = verticesYCoordinate[k5] * i8 - verticesXCoordinate[k5] * j7 >> 16;
							verticesXCoordinate[k5] = l8;
						}
						if (k6 != 0) {
							int k7 = SINE[k6];
							int j8 = COSINE[k6];
							int i9 = verticesYCoordinate[k5] * j8 - verticesZCoordinate[k5] * k7 >> 16;
							verticesZCoordinate[k5] = verticesYCoordinate[k5] * k7 + verticesZCoordinate[k5] * j8 >> 16;
							verticesYCoordinate[k5] = i9;
						}
						if (l6 != 0) {
							int l7 = SINE[l6];
							int k8 = COSINE[l6];
							int j9 = verticesZCoordinate[k5] * l7 + verticesXCoordinate[k5] * k8 >> 16;
							verticesZCoordinate[k5] = verticesZCoordinate[k5] * k8 - verticesXCoordinate[k5] * l7 >> 16;
							verticesXCoordinate[k5] = j9;
						}
						verticesXCoordinate[k5] += anInt1681;
						verticesYCoordinate[k5] += anInt1682;
						verticesZCoordinate[k5] += anInt1683;
					}

				}
			}
			return;
		}
		if (i == 3) {
			for (int i2 = 0; i2 < i1; i2++) {
				int j3 = ai[i2];
				if (j3 < vertexSkin.length) {
					int ai3[] = vertexSkin[j3];
					for (int k4 = 0; k4 < ai3.length; k4++) {
						int l5 = ai3[k4];
						verticesXCoordinate[l5] -= anInt1681;
						verticesYCoordinate[l5] -= anInt1682;
						verticesZCoordinate[l5] -= anInt1683;
						verticesXCoordinate[l5] = (verticesXCoordinate[l5] * j) / 128;
						verticesYCoordinate[l5] = (verticesYCoordinate[l5] * k) / 128;
						verticesZCoordinate[l5] = (verticesZCoordinate[l5] * l) / 128;
						verticesXCoordinate[l5] += anInt1681;
						verticesYCoordinate[l5] += anInt1682;
						verticesZCoordinate[l5] += anInt1683;
					}
				}
			}
			return;
		}
		if (i == 5 && triangleSkin != null && face_alpha != null) {
			for (int j2 = 0; j2 < i1; j2++) {
				int k3 = ai[j2];
				if (k3 < triangleSkin.length) {
					int ai4[] = triangleSkin[k3];
					for (int l4 = 0; l4 < ai4.length; l4++) {
						int i6 = ai4[l4];
						(face_alpha)[i6] += j * 8;
						if ((face_alpha)[i6] < 0)
							(face_alpha)[i6] = 0;
						if ((face_alpha)[i6] > 255)
							(face_alpha)[i6] = 255;
					}
				}
			}
		}
	}*/
	private void method472(int animationType, int skin[], int xOffset, int yOffset, int zOffset) {

		int i1 = skin.length;
		if (animationType == 0) {
			int j1 = 0;
			anInt1681 = 0;
			anInt1682 = 0;
			anInt1683 = 0;
			for (int k2 = 0; k2 < i1; k2++) {
				int l3 = skin[k2];
				if (l3 < vertexSkin.length) {
					int ai5[] = vertexSkin[l3];
					for (int i5 = 0; i5 < ai5.length; i5++) {
						int j6 = ai5[i5];
						anInt1681 += verticesXCoordinate[j6];
						anInt1682 += verticesYCoordinate[j6];
						anInt1683 += verticesZCoordinate[j6];
						j1++;
					}

				}
			}

			if (j1 > 0) {
				anInt1681 = (int)(anInt1681 / j1 + xOffset);
				anInt1682 = (int)(anInt1682 / j1 + yOffset);
				anInt1683 = (int)(anInt1683 / j1 + zOffset);
				return;
			} else {
				anInt1681 = (int)xOffset;
				anInt1682 = (int)yOffset;
				anInt1683 = (int)zOffset;
				return;
			}
		}
		if (animationType == 1) {
			for (int k1 = 0; k1 < i1; k1++) {
				int l2 = skin[k1];
				if (l2 < vertexSkin.length) {
					int ai1[] = vertexSkin[l2];
					for (int i4 = 0; i4 < ai1.length; i4++) {
						int j5 = ai1[i4];
						verticesXCoordinate[j5] += xOffset;
						verticesYCoordinate[j5] += yOffset;
						verticesZCoordinate[j5] += zOffset;
					}

				}
			}

			return;
		}
		if (animationType == 2) {
			for (int l1 = 0; l1 < i1; l1++) {
				int i3 = skin[l1];
				if (i3 < vertexSkin.length) {
					int ai2[] = vertexSkin[i3];
					for (int j4 = 0; j4 < ai2.length; j4++) {
						int k5 = ai2[j4];
						verticesXCoordinate[k5] -= anInt1681;
						verticesYCoordinate[k5] -= anInt1682;
						verticesZCoordinate[k5] -= anInt1683;
						//int j, int k, int l
						if (zOffset != 0) {
							int j7 = SINE[zOffset];
							int i8 = COSINE[zOffset];
							int l8 = verticesYCoordinate[k5] * j7 + verticesXCoordinate[k5] * i8 >> 16;
							verticesYCoordinate[k5] = verticesYCoordinate[k5] * i8 - verticesXCoordinate[k5] * j7 >> 16;
							verticesXCoordinate[k5] = l8;
						}
						if (xOffset != 0) {
							int k7 = SINE[xOffset];
							int j8 = COSINE[xOffset];
							int i9 = verticesYCoordinate[k5] * j8 - verticesZCoordinate[k5] * k7 >> 16;
							verticesZCoordinate[k5] = verticesYCoordinate[k5] * k7 + verticesZCoordinate[k5] * j8 >> 16;
							verticesYCoordinate[k5] = i9;
						}
						if (yOffset != 0) {
							int l7 = SINE[yOffset];
							int k8 = COSINE[yOffset];
							int j9 = verticesZCoordinate[k5] * l7 + verticesXCoordinate[k5] * k8 >> 16;
							verticesZCoordinate[k5] = verticesZCoordinate[k5] * k8 - verticesXCoordinate[k5] * l7 >> 16;
							verticesXCoordinate[k5] = j9;
						}
						verticesXCoordinate[k5] += anInt1681;
						verticesYCoordinate[k5] += anInt1682;
						verticesZCoordinate[k5] += anInt1683;
					}

				}
			}
			return;
		}
		/*if (animationType == 2) {
			for (int l1 = 0; l1 < i1; l1++) {
				int i3 = skin[l1];
				if (i3 < vertexSkin.length) {
					int ai2[] = vertexSkin[i3];
					for (int j4 = 0; j4 < ai2.length; j4++) {
						int k5 = ai2[j4];
						verticesXCoordinate[k5] -= xAnimOffset;
						verticesYCoordinate[k5] -= yAnimOffset;
						verticesZCoordinate[k5] -= zAnimOffset;

						double x_ = xOffset - Math.floor(xOffset);
						double y_ = yOffset - Math.floor(yOffset);
						double z_ = zOffset - Math.floor(zOffset);
						xOffset = (int) xOffset & 0xff;
						yOffset = (int) yOffset & 0xff;
						zOffset = (int) zOffset & 0xff;
						xOffset += x_;
						yOffset += y_;
						zOffset += z_;
						int k6 = (int)((xOffset) * 8);
						int l6 = (int)((yOffset) * 8);
						int i7 = (int)((zOffset) * 8);
						if (i7 != 0) {
							int j7 = SINE[i7];
							int i8 = COSINE[i7];
							int l8 = verticesYCoordinate[k5] * j7 + verticesXCoordinate[k5] * i8 >> 16;
							verticesYCoordinate[k5] = verticesYCoordinate[k5] * i8 - verticesXCoordinate[k5] * j7 >> 16;
							verticesXCoordinate[k5] = l8;
						}
						if (k6 != 0) {
							int k7 = SINE[k6];
							int j8 = COSINE[k6];
							int i9 = verticesYCoordinate[k5] * j8 - verticesZCoordinate[k5] * k7 >> 16;
							verticesZCoordinate[k5] = verticesYCoordinate[k5] * k7 + verticesZCoordinate[k5] * j8 >> 16;
							verticesYCoordinate[k5] = i9;
						}
						if (l6 != 0) {
							int l7 = SINE[l6];
							int k8 = COSINE[l6];
							int j9 = verticesZCoordinate[k5] * l7 + verticesXCoordinate[k5] * k8 >> 16;
							verticesZCoordinate[k5] = verticesZCoordinate[k5] * k8 - verticesXCoordinate[k5] * l7 >> 16;
							verticesXCoordinate[k5] = j9;
						}
						verticesXCoordinate[k5] += xAnimOffset;
						verticesYCoordinate[k5] += yAnimOffset;
						verticesZCoordinate[k5] += zAnimOffset;
					}

				}
			}

			return;
		}*/
		if (animationType == 3) {
			for (int i2 = 0; i2 < i1; i2++) {
				int j3 = skin[i2];
				if (j3 < vertexSkin.length) {
					int ai3[] = vertexSkin[j3];
					for (int k4 = 0; k4 < ai3.length; k4++) {
						int l5 = ai3[k4];
						verticesXCoordinate[l5] -= anInt1681;
						verticesYCoordinate[l5] -= anInt1682;
						verticesZCoordinate[l5] -= anInt1683;
						verticesXCoordinate[l5] = (int)((verticesXCoordinate[l5] * xOffset) / 128);
						verticesYCoordinate[l5] = (int)((verticesYCoordinate[l5] * yOffset) / 128);
						verticesZCoordinate[l5] = (int)((verticesZCoordinate[l5] * zOffset) / 128);
						verticesXCoordinate[l5] += anInt1681;
						verticesYCoordinate[l5] += anInt1682;
						verticesZCoordinate[l5] += anInt1683;
					}

				}
			}

			return;
		}
		if (animationType == 5 && triangleSkin != null && face_alpha != null) {
			for (int j2 = 0; j2 < i1; j2++) {
				int k3 = skin[j2];
				if (k3 < triangleSkin.length) {
					int ai4[] = triangleSkin[k3];
					for (int l4 = 0; l4 < ai4.length; l4++) {
						int i6 = ai4[l4];
						face_alpha[i6] += xOffset * 8;
						if (face_alpha[i6] < 0)
							face_alpha[i6] = 0;
						if (face_alpha[i6] > 255)
							face_alpha[i6] = 255;
					}

				}
			}

		}
	}

	public void method480(int i, int j, int k, int l, int i1) {
		for (int j1 = 0; j1 < numberOfTriangleFaces; j1++) {
			int k1 = face_a[j1];
			int i2 = face_b[j1];
			int j2 = face_c[j1];
			if (face_render_type == null) {
				int i3 = face_color[j1];
				VertexNormal class33 = super.vertexNormals[k1];
				int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604)
						/ (j * class33.anInt605);
				face_shade_a[j1] = method481(i3, k2, 0);
				class33 = super.vertexNormals[i2];
				k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				face_shade_b[j1] = method481(i3, k2, 0);
				class33 = super.vertexNormals[j2];
				k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
				face_shade_c[j1] = method481(i3, k2, 0);
			} else if ((face_render_type[j1] & 1) == 0) {
				int j3 = face_color[j1];
				int k3 = face_render_type[j1];
				VertexNormal class33_1 = super.vertexNormals[k1];
				int l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
						/ (j * class33_1.anInt605);
				face_shade_a[j1] = method481(j3, l2, k3);
				class33_1 = super.vertexNormals[i2];
				l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
						/ (j * class33_1.anInt605);
				face_shade_b[j1] = method481(j3, l2, k3);
				class33_1 = super.vertexNormals[j2];
				l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604)
						/ (j * class33_1.anInt605);
				face_shade_c[j1] = method481(j3, l2, k3);
			}
		}

		super.vertexNormals = null;
		vertexNormalOffset = null;
		vertexVSkin = null;
		triangleTSkin = null;
		if (face_render_type != null) {
			for (int l1 = 0; l1 < numberOfTriangleFaces; l1++)
				if ((face_render_type[l1] & 2) == 2)
					return;

		}
		//face_color = null;
	}

	public void mirrorModel() {
		for (int j = 0; j < numberOfVerticeCoordinates; j++)
			verticesZCoordinate[j] = -verticesZCoordinate[j];
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			int l = face_a[k];
			face_a[k] = face_c[k];
			face_c[k] = l;
		}
	}

	public void normalise() {
		super.modelHeight = 0;
		anInt1651 = 0;
		for (int i = 0; i < numberOfVerticeCoordinates; i++) {
			int j = verticesYCoordinate[i];
			if (-j > super.modelHeight)
				super.modelHeight = -j;
			if (j > anInt1651)
				anInt1651 = j;
		}

		anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight)
				+ 0.98999999999999999D);
		diagonal3D = anInt1653
				+ (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
	}

	private final void rasterise(int i) {
		if (outOfReach[i]) {
			reduce(i);
			return;
		}
		int j = face_a[i];
		int k = face_b[i];
		int l = face_c[i];
		Rasterizer.restrict_edges = hasAnEdgeToRestrict[i];
		if (face_alpha == null)
			Rasterizer.alpha = 0;
		else
			Rasterizer.alpha = (face_alpha)[i];
		int i1;
		if (face_render_type == null)
			i1 = 0;
		else
			i1 = face_render_type[i] & 3;
		if (i1 == 0) {
			Rasterizer.drawDepthShadedTriangle(projected_vertex_y[j], projected_vertex_y[k],
					projected_vertex_y[l], projected_vertex_x[j],
					projected_vertex_x[k], projected_vertex_x[l],
					projected_vertex_z[j],
					projected_vertex_z[k], projected_vertex_z[l],
					face_shade_a[i], face_shade_b[i], face_shade_c[i]);
			return;
		}
		if (i1 == 1) {
			Rasterizer.drawDepthFlatTriangle(projected_vertex_y[j], projected_vertex_y[k],
					projected_vertex_y[l], projected_vertex_x[j],
					projected_vertex_x[k], projected_vertex_x[l], projected_vertex_z[j],
					projected_vertex_z[k], projected_vertex_z[l],
					hsl2rgb[face_shade_a[i]]);
			return;
		}
		if (i1 == 2) {
			int j1 = face_render_type[i] >> 2;
			int l1 = textures_face_a[j1];
			int j2 = textures_face_b[j1];
			int l2 = textures_face_c[j1];
			if(Client.getOption("hd_tex")) {
				Rasterizer.drawTexturedTriangle_model(projected_vertex_y[j], projected_vertex_y[k],
						projected_vertex_y[l], projected_vertex_x[j],
						projected_vertex_x[k], projected_vertex_x[l], projected_vertex_z[j],
						projected_vertex_z[k], projected_vertex_z[l],
						face_shade_a[i], face_shade_b[i], face_shade_c[i],
						camera_vertex_y[l1], camera_vertex_y[j2],
						camera_vertex_y[l2], camera_vertex_x[l1],
						camera_vertex_x[j2], camera_vertex_x[l2],
						camera_vertex_z[l1], camera_vertex_z[j2],
						camera_vertex_z[l2], face_color[i], face_color[i], false, false);
			} else {
				Rasterizer.drawDepthTexturedTriangle(projected_vertex_y[j], projected_vertex_y[k],
						projected_vertex_y[l], projected_vertex_x[j],
						projected_vertex_x[k], projected_vertex_x[l], projected_vertex_z[j],
						projected_vertex_z[k], projected_vertex_z[l],
						face_shade_a[i], face_shade_b[i], face_shade_c[i],
						camera_vertex_y[l1], camera_vertex_y[j2],
						camera_vertex_y[l2], camera_vertex_x[l1],
						camera_vertex_x[j2], camera_vertex_x[l2],
						camera_vertex_z[l1], camera_vertex_z[j2],
						camera_vertex_z[l2], face_color[i]);
			}
			return;
		}
		if (i1 == 3) {
			int k1 = face_render_type[i] >> 2;
			int i2 = textures_face_a[k1];
			int k2 = textures_face_b[k1];
			int i3 = textures_face_c[k1];
			if(Client.getOption("hd_tex")) {
				Rasterizer.drawTexturedTriangle_model(projected_vertex_y[j], projected_vertex_y[k],
						projected_vertex_y[l], projected_vertex_x[j],
						projected_vertex_x[k], projected_vertex_x[l], projected_vertex_z[j],
						projected_vertex_z[k], projected_vertex_z[l],
						face_shade_a[i], face_shade_a[i], face_shade_a[i],
						camera_vertex_y[i2], camera_vertex_y[k2],
						camera_vertex_y[i3], camera_vertex_x[i2],
						camera_vertex_x[k2], camera_vertex_x[i3],
						camera_vertex_z[i2], camera_vertex_z[k2],
						camera_vertex_z[i3], face_color[i], face_color[i], false, false);
			} else {
				Rasterizer.drawDepthTexturedTriangle(projected_vertex_y[j], projected_vertex_y[k],
						projected_vertex_y[l], projected_vertex_x[j],
						projected_vertex_x[k], projected_vertex_x[l], projected_vertex_z[j],
						projected_vertex_z[k], projected_vertex_z[l],
						face_shade_a[i], face_shade_a[i], face_shade_a[i],
						camera_vertex_y[i2], camera_vertex_y[k2],
						camera_vertex_y[i3], camera_vertex_x[i2],
						camera_vertex_x[k2], camera_vertex_x[i3],
						camera_vertex_z[i2], camera_vertex_z[k2],
						camera_vertex_z[i3], face_color[i]);
			}
		}
	}

	/*      */
	/*      */   public void read525Model(byte[] abyte0, int modelID) {
		/* 1886 */     Stream nc1 = new Stream(abyte0);
		/* 1887 */     Stream nc2 = new Stream(abyte0);
		/* 1888 */     Stream nc3 = new Stream(abyte0);
		/* 1889 */     Stream nc4 = new Stream(abyte0);
		/* 1890 */     Stream nc5 = new Stream(abyte0);
		/* 1891 */     Stream nc6 = new Stream(abyte0);
		/* 1892 */     Stream nc7 = new Stream(abyte0);
		/* 1893 */     nc1.currentOffset = (abyte0.length - 23);
		/* 1894 */     int numVertices = nc1.readUnsignedWord();
		/* 1895 */     int numTriangles = nc1.readUnsignedWord();
		/* 1896 */     int numTexTriangles = nc1.readUnsignedByte();
		/* 1897 */     ModelHeader ModelDef_1 = modelHeader[modelID] = new ModelHeader();
		/* 1898 */     ModelDef_1.modelData = abyte0;
		/* 1899 */     ModelDef_1.verticeCount = numVertices;
		/* 1900 */     ModelDef_1.triangleCount = numTriangles;
		/* 1901 */     ModelDef_1.texturedTriangleCount = numTexTriangles;
		/* 1902 */     int l1 = nc1.readUnsignedByte();
		/* 1903 */     boolean bool = (0x1 & l1 ^ 0xFFFFFFFF) == -2;
		/* 1904 */     boolean bool_78_ = (l1 & 0x2 ^ 0xFFFFFFFF) == -3;
		/* 1905 */     int i2 = nc1.readUnsignedByte();
		/* 1906 */     int j2 = nc1.readUnsignedByte();
		/* 1907 */     int k2 = nc1.readUnsignedByte();
		/* 1908 */     int l2 = nc1.readUnsignedByte();
		/* 1909 */     int i3 = nc1.readUnsignedByte();
		/* 1910 */     int j3 = nc1.readUnsignedWord();
		/* 1911 */     int k3 = nc1.readUnsignedWord();
		/* 1912 */     int l3 = nc1.readUnsignedWord();
		/* 1913 */     int i4 = nc1.readUnsignedWord();
		/* 1914 */     int j4 = nc1.readUnsignedWord();
		/* 1915 */     int k4 = 0;
		/* 1916 */     int l4 = 0;
		/* 1917 */     int i5 = 0;
		/* 1918 */     int v = 0;
		/* 1919 */     int hb = 0;
		/* 1920 */     int P = 0;
		/* 1921 */     byte G = 0;
		/* 1922 */     byte[] x = null;
		/* 1923 */     byte[] O = null;
		/* 1924 */     byte[] J = null;
		/* 1925 */     byte[] F = null;
		/* 1926 */     byte[] cb = null;
		/* 1927 */     byte[] gb = null;
		/* 1928 */     byte[] lb = null;
		/* 1929 */     int[] ab = null;
		/* 1930 */     int[] kb = null;
		/* 1931 */     int[] y = null;
		/* 1932 */     int[] N = null;
		/* 1933 */     short[] D = null;
		/* 1934 */     int[] triangleColours2 = new int[numTriangles];
		/* 1935 */     if (numTexTriangles > 0) {
			/* 1936 */       O = new byte[numTexTriangles];
			/* 1937 */       nc1.currentOffset = 0;
			/* 1938 */       for (int j5 = 0; j5 < numTexTriangles; j5++) {
				/* 1939 */         byte byte0 = O[j5] = nc1.readSignedByte();
				/* 1940 */         if (byte0 == 0)
					/* 1941 */           k4++;
				/* 1942 */         if ((byte0 >= 1) && (byte0 <= 3))
					/* 1943 */           l4++;
				/* 1944 */         if (byte0 == 2)
					/* 1945 */           i5++;
				/*      */       }
			/*      */     }
		/* 1948 */     int k5 = numTexTriangles;
		/* 1949 */     int l5 = k5;
		/* 1950 */     k5 += numVertices;
		/* 1951 */     int i6 = k5;
		/* 1952 */     if (l1 == 1)
			/* 1953 */       k5 += numTriangles;
		/* 1954 */     int j6 = k5;
		/* 1955 */     k5 += numTriangles;
		/* 1956 */     int k6 = k5;
		/* 1957 */     if (i2 == 255)
			/* 1958 */       k5 += numTriangles;
		/* 1959 */     int l6 = k5;
		/* 1960 */     if (k2 == 1)
			/* 1961 */       k5 += numTriangles;
		/* 1962 */     int i7 = k5;
		/* 1963 */     if (i3 == 1)
			/* 1964 */       k5 += numVertices;
		/* 1965 */     int j7 = k5;
		/* 1966 */     if (j2 == 1)
			/* 1967 */       k5 += numTriangles;
		/* 1968 */     int k7 = k5;
		/* 1969 */     k5 += i4;
		/* 1970 */     int l7 = k5;
		/* 1971 */     if (l2 == 1)
			/* 1972 */       k5 += numTriangles * 2;
		/* 1973 */     int i8 = k5;
		/* 1974 */     k5 += j4;
		/* 1975 */     int j8 = k5;
		/* 1976 */     k5 += numTriangles * 2;
		/* 1977 */     int k8 = k5;
		/* 1978 */     k5 += j3;
		/* 1979 */     int l8 = k5;
		/* 1980 */     k5 += k3;
		/* 1981 */     int i9 = k5;
		/* 1982 */     k5 += l3;
		/* 1983 */     int j9 = k5;
		/* 1984 */     k5 += k4 * 6;
		/* 1985 */     int k9 = k5;
		/* 1986 */     k5 += l4 * 6;
		/* 1987 */     int l9 = k5;
		/* 1988 */     k5 += l4 * 6;
		/* 1989 */     int i10 = k5;
		/* 1990 */     k5 += l4;
		/* 1991 */     int j10 = k5;
		/* 1992 */     k5 += l4;
		/* 1993 */     int k10 = k5;
		/* 1994 */     k5 += l4 + i5 * 2;
		/* 1995 */     v = numVertices;
		/* 1996 */     hb = numTriangles;
		/* 1997 */     P = numTexTriangles;
		/* 1998 */     this.verticesParticle = new int[numVertices];
		/* 1999 */     int[] vertexX = new int[numVertices];
		/* 2000 */     int[] vertexY = new int[numVertices];
		/* 2001 */     int[] vertexZ = new int[numVertices];
		/* 2002 */     int[] facePoint1 = new int[numTriangles];
		/* 2003 */     int[] facePoint2 = new int[numTriangles];
		/* 2004 */     int[] facePoint3 = new int[numTriangles];
		/* 2005 */     this.vertexVSkin = new int[numVertices];
		/* 2006 */     this.face_render_type = new int[numTriangles];
		/* 2007 */     this.face_render_priorities = new int[numTriangles];
		/* 2008 */     this.face_alpha = new int[numTriangles];
		/* 2009 */     this.triangleTSkin = new int[numTriangles];
		/* 2010 */     if (i3 == 1)
			/* 2011 */       this.vertexVSkin = new int[numVertices];
		/* 2012 */     if (bool)
			/* 2013 */       this.face_render_type = new int[numTriangles];
		/* 2014 */     if (i2 == 255) {
			/* 2015 */       this.face_render_priorities = new int[numTriangles];
			/*      */     } else
			/* 2017 */       G = (byte)i2;
		/* 2018 */     if (j2 == 1)
			/* 2019 */       this.face_alpha = new int[numTriangles];
		/* 2020 */     if (k2 == 1)
			/* 2021 */       this.triangleTSkin = new int[numTriangles];
		/* 2022 */     if (l2 == 1)
			/* 2023 */       D = new short[numTriangles];
		/* 2024 */     if ((l2 == 1) && (numTexTriangles > 0))
			/* 2025 */       x = new byte[numTriangles];
		/* 2026 */     triangleColours2 = new int[numTriangles];
		/* 2027 */     int i_115_ = k5;
		/* 2028 */     int[] texTrianglesPoint1 = null;
		/* 2029 */     int[] texTrianglesPoint2 = null;
		/* 2030 */     int[] texTrianglesPoint3 = null;
		/* 2031 */     if (numTexTriangles > 0) {
			/* 2032 */       texTrianglesPoint1 = new int[numTexTriangles];
			/* 2033 */       texTrianglesPoint2 = new int[numTexTriangles];
			/* 2034 */       texTrianglesPoint3 = new int[numTexTriangles];
			/* 2035 */       if (l4 > 0) {
				/* 2036 */         kb = new int[l4];
				/* 2037 */         N = new int[l4];
				/* 2038 */         y = new int[l4];
				/* 2039 */         gb = new byte[l4];
				/* 2040 */         lb = new byte[l4];
				/* 2041 */         F = new byte[l4];
				/*      */       }
			/* 2043 */       if (i5 > 0) {
				/* 2044 */         cb = new byte[i5];
				/* 2045 */         J = new byte[i5];
				/*      */       }
			/*      */     }
		/* 2048 */     nc1.currentOffset = l5;
		/* 2049 */     nc2.currentOffset = k8;
		/* 2050 */     nc3.currentOffset = l8;
		/* 2051 */     nc4.currentOffset = i9;
		/* 2052 */     nc5.currentOffset = i7;
		/* 2053 */     int l10 = 0;
		/* 2054 */     int i11 = 0;
		/* 2055 */     int j11 = 0;
		/* 2056 */     for (int k11 = 0; k11 < numVertices; k11++) {
			/* 2057 */       int l11 = nc1.readUnsignedByte();
			/* 2058 */       int j12 = 0;
			/* 2059 */       if ((l11 & 0x1) != 0)
				/* 2060 */         j12 = nc2.method421();
			/* 2061 */       int l12 = 0;
			/* 2062 */       if ((l11 & 0x2) != 0)
				/* 2063 */         l12 = nc3.method421();
			/* 2064 */       int j13 = 0;
			/* 2065 */       if ((l11 & 0x4) != 0)
				/* 2066 */         j13 = nc4.method421();
			/* 2067 */       vertexX[k11] = (l10 + j12);
			/* 2068 */       vertexY[k11] = (i11 + l12);
			/* 2069 */       vertexZ[k11] = (j11 + j13);
			/* 2070 */       l10 = vertexX[k11];
			/* 2071 */       i11 = vertexY[k11];
			/* 2072 */       j11 = vertexZ[k11];
			/* 2073 */       if (this.vertexVSkin != null)
				/* 2074 */         this.vertexVSkin[k11] = nc5.readUnsignedByte();
			/*      */     }
		/* 2076 */     nc1.currentOffset = j8;
		/* 2077 */     nc2.currentOffset = i6;
		/* 2078 */     nc3.currentOffset = k6;
		/* 2079 */     nc4.currentOffset = j7;
		/* 2080 */     nc5.currentOffset = l6;
		/* 2081 */     nc6.currentOffset = l7;
		/* 2082 */     nc7.currentOffset = i8;
		/* 2083 */     for (int i12 = 0; i12 < numTriangles; i12++) {
			/* 2084 */       triangleColours2[i12] = nc1.readUnsignedWord();
			/* 2085 */       if (l1 == 1) {
				/* 2086 */         this.face_render_type[i12] = nc2.readSignedByte();
				/* 2087 */         if (this.face_render_type[i12] == 2)
					/* 2088 */           triangleColours2[i12] = 65535;
				/* 2089 */         this.face_render_type[i12] = 0;
				/*      */       }
			/* 2091 */       if (i2 == 255) {
				/* 2092 */         this.face_render_priorities[i12] = nc3.readSignedByte();
				/*      */       }
			/* 2094 */       if (j2 == 1) {
				/* 2095 */         this.face_alpha[i12] = nc4.readSignedByte();
				/* 2096 */         if (this.face_alpha[i12] < 0)
					/* 2097 */           this.face_alpha[i12] = (256 + this.face_alpha[i12]);
				/*      */       }
			/* 2099 */       if (k2 == 1)
				/* 2100 */         this.triangleTSkin[i12] = nc5.readUnsignedByte();
			/* 2101 */       if (l2 == 1)
				/* 2102 */         D[i12] = ((short)(nc6.readUnsignedWord() - 1));
			/* 2103 */       if (x != null)
				/* 2104 */         if (D[i12] != -1) {
					/* 2105 */           x[i12] = ((byte)(nc7.readUnsignedByte() - 1));
					/*      */         } else
					/* 2107 */           x[i12] = -1;
			/*      */     }
		/* 2109 */     nc1.currentOffset = k7;
		/* 2110 */     nc2.currentOffset = j6;
		/* 2111 */     int k12 = 0;
		/* 2112 */     int i13 = 0;
		/* 2113 */     int k13 = 0;
		/* 2114 */     int l13 = 0;
		/* 2115 */     for (int i14 = 0; i14 < numTriangles; i14++) {
			/* 2116 */       int j14 = nc2.readUnsignedByte();
			/* 2117 */       if (j14 == 1) {
				/* 2118 */         k12 = nc1.method421() + l13;
				/* 2119 */         l13 = k12;
				/* 2120 */         i13 = nc1.method421() + l13;
				/* 2121 */         l13 = i13;
				/* 2122 */         k13 = nc1.method421() + l13;
				/* 2123 */         l13 = k13;
				/* 2124 */         facePoint1[i14] = k12;
				/* 2125 */         facePoint2[i14] = i13;
				/* 2126 */         facePoint3[i14] = k13;
				/*      */       }
			/* 2128 */       if (j14 == 2) {
				/* 2129 */         i13 = k13;
				/* 2130 */         k13 = nc1.method421() + l13;
				/* 2131 */         l13 = k13;
				/* 2132 */         facePoint1[i14] = k12;
				/* 2133 */         facePoint2[i14] = i13;
				/* 2134 */         facePoint3[i14] = k13;
				/*      */       }
			/* 2136 */       if (j14 == 3) {
				/* 2137 */         k12 = k13;
				/* 2138 */         k13 = nc1.method421() + l13;
				/* 2139 */         l13 = k13;
				/* 2140 */         facePoint1[i14] = k12;
				/* 2141 */         facePoint2[i14] = i13;
				/* 2142 */         facePoint3[i14] = k13;
				/*      */       }
			/* 2144 */       if (j14 == 4) {
				/* 2145 */         int l14 = k12;
				/* 2146 */         k12 = i13;
				/* 2147 */         i13 = l14;
				/* 2148 */         k13 = nc1.method421() + l13;
				/* 2149 */         l13 = k13;
				/* 2150 */         facePoint1[i14] = k12;
				/* 2151 */         facePoint2[i14] = i13;
				/* 2152 */         facePoint3[i14] = k13;
				/*      */       }
			/*      */     }
		/* 2155 */     nc1.currentOffset = j9;
		/* 2156 */     nc2.currentOffset = k9;
		/* 2157 */     nc3.currentOffset = l9;
		/* 2158 */     nc4.currentOffset = i10;
		/* 2159 */     nc5.currentOffset = j10;
		/* 2160 */     nc6.currentOffset = k10;
		/* 2161 */     for (int k14 = 0; k14 < numTexTriangles; k14++) {
			/* 2162 */       int i15 = O[k14] & 0xFF;
			/* 2163 */       if (i15 == 0) {
				/* 2164 */         texTrianglesPoint1[k14] = nc1.readUnsignedWord();
				/* 2165 */         texTrianglesPoint2[k14] = nc1.readUnsignedWord();
				/* 2166 */         texTrianglesPoint3[k14] = nc1.readUnsignedWord();
				/*      */       }
			/* 2168 */       if (i15 == 1) {
				/* 2169 */         texTrianglesPoint1[k14] = nc2.readUnsignedWord();
				/* 2170 */         texTrianglesPoint2[k14] = nc2.readUnsignedWord();
				/* 2171 */         texTrianglesPoint3[k14] = nc2.readUnsignedWord();
				/* 2172 */         kb[k14] = nc3.readUnsignedWord();
				/* 2173 */         N[k14] = nc3.readUnsignedWord();
				/* 2174 */         y[k14] = nc3.readUnsignedWord();
				/* 2175 */         gb[k14] = nc4.readSignedByte();
				/* 2176 */         lb[k14] = nc5.readSignedByte();
				/* 2177 */         F[k14] = nc6.readSignedByte();
				/*      */       }
			/* 2179 */       if (i15 == 2) {
				/* 2180 */         texTrianglesPoint1[k14] = nc2.readUnsignedWord();
				/* 2181 */         texTrianglesPoint2[k14] = nc2.readUnsignedWord();
				/* 2182 */         texTrianglesPoint3[k14] = nc2.readUnsignedWord();
				/* 2183 */         kb[k14] = nc3.readUnsignedWord();
				/* 2184 */         N[k14] = nc3.readUnsignedWord();
				/* 2185 */         y[k14] = nc3.readUnsignedWord();
				/* 2186 */         gb[k14] = nc4.readSignedByte();
				/* 2187 */         lb[k14] = nc5.readSignedByte();
				/* 2188 */         F[k14] = nc6.readSignedByte();
				/* 2189 */         cb[k14] = nc6.readSignedByte();
				/* 2190 */         J[k14] = nc6.readSignedByte();
				/*      */       }
			/* 2192 */       if (i15 == 3) {
				/* 2193 */         texTrianglesPoint1[k14] = nc2.readUnsignedWord();
				/* 2194 */         texTrianglesPoint2[k14] = nc2.readUnsignedWord();
				/* 2195 */         texTrianglesPoint3[k14] = nc2.readUnsignedWord();
				/* 2196 */         kb[k14] = nc3.readUnsignedWord();
				/* 2197 */         N[k14] = nc3.readUnsignedWord();
				/* 2198 */         y[k14] = nc3.readUnsignedWord();
				/* 2199 */         gb[k14] = nc4.readSignedByte();
				/* 2200 */         lb[k14] = nc5.readSignedByte();
				/* 2201 */         F[k14] = nc6.readSignedByte();
				/*      */       }
			/*      */     }
		/* 2204 */     if (i2 != 255) {
			/* 2205 */       for (int i12 = 0; i12 < numTriangles; i12++)
				/* 2206 */         this.face_render_priorities[i12] = i2;
			/*      */     }
		/* 2208 */     this.face_color = triangleColours2;
		/* 2209 */     this.numberOfVerticeCoordinates = numVertices;
		/* 2210 */     this.numberOfTriangleFaces = numTriangles;
		/* 2211 */     this.verticesXCoordinate = vertexX;
		/* 2212 */     this.verticesYCoordinate = vertexY;
		/* 2213 */     this.verticesZCoordinate = vertexZ;
		/* 2214 */     this.face_a = facePoint1;
		/* 2215 */     this.face_b = facePoint2;
		/* 2216 */     this.face_c = facePoint3;
		/* 2217 */     filterTriangles();
		/*      */   }
	/*      */
	/*      */   public void read622Model(byte[] abyte0, int modelID) {
		/* 2221 */     Stream nc1 = new Stream(abyte0);
		/* 2222 */     Stream nc2 = new Stream(abyte0);
		/* 2223 */     Stream nc3 = new Stream(abyte0);
		/* 2224 */     Stream nc4 = new Stream(abyte0);
		/* 2225 */     Stream nc5 = new Stream(abyte0);
		/* 2226 */     Stream nc6 = new Stream(abyte0);
		/* 2227 */     Stream nc7 = new Stream(abyte0);
		/* 2228 */     nc1.currentOffset = (abyte0.length - 23);
		/* 2229 */     int numVertices = nc1.readUnsignedWord();
		/* 2230 */     int numTriangles = nc1.readUnsignedWord();
		/* 2231 */     int numTexTriangles = nc1.readUnsignedByte();
		/* 2232 */     ModelHeader ModelDef_1 = modelHeader[modelID] = new ModelHeader();
		/* 2233 */     ModelDef_1.modelData = abyte0;
		/* 2234 */     ModelDef_1.verticeCount = numVertices;
		/* 2235 */     ModelDef_1.triangleCount = numTriangles;
		/* 2236 */     ModelDef_1.texturedTriangleCount = numTexTriangles;
		/* 2237 */     int l1 = nc1.readUnsignedByte();
		/* 2238 */     boolean bool = (0x1 & l1 ^ 0xFFFFFFFF) == -2;
		/* 2239 */     boolean bool_78_ = (l1 & 0x2 ^ 0xFFFFFFFF) == -3;
		/* 2240 */     boolean bool_25_ = (0x4 & l1) == 4;
		/* 2241 */     boolean bool_26_ = (0x8 & l1) == 8;
		/* 2242 */     if (!bool_26_) {
			/* 2243 */       read525Model(abyte0, modelID);
			/* 2244 */       return;
			/*      */     }
		/* 2246 */     int newformat = 0;
		/* 2247 */     if (bool_26_) {
			/* 2248 */       nc1.currentOffset -= 7;
			/* 2249 */       newformat = nc1.readUnsignedByte();
			/* 2250 */       nc1.currentOffset += 6;
			/*      */     }
		/* 2252 */     if (newformat == 15)
			/* 2253 */       newmodel[modelID] = true;
		/* 2254 */     int i2 = nc1.readUnsignedByte();
		/* 2255 */     int j2 = nc1.readUnsignedByte();
		/* 2256 */     int k2 = nc1.readUnsignedByte();
		/* 2257 */     int l2 = nc1.readUnsignedByte();
		/* 2258 */     int i3 = nc1.readUnsignedByte();
		/* 2259 */     int j3 = nc1.readUnsignedWord();
		/* 2260 */     int k3 = nc1.readUnsignedWord();
		/* 2261 */     int l3 = nc1.readUnsignedWord();
		/* 2262 */     int i4 = nc1.readUnsignedWord();
		/* 2263 */     int j4 = nc1.readUnsignedWord();
		/* 2264 */     int k4 = 0;
		/* 2265 */     int l4 = 0;
		/* 2266 */     int i5 = 0;
		/* 2267 */     int v = 0;
		/* 2268 */     int hb = 0;
		/* 2269 */     int P = 0;
		/* 2270 */     byte G = 0;
		/* 2271 */     byte[] x = null;
		/* 2272 */     byte[] O = null;
		/* 2273 */     byte[] J = null;
		/* 2274 */     byte[] F = null;
		/* 2275 */     byte[] cb = null;
		/* 2276 */     byte[] gb = null;
		/* 2277 */     byte[] lb = null;
		/* 2278 */     int[] ab = null;
		/* 2279 */     int[] kb = null;
		/* 2280 */     int[] y = null;
		/* 2281 */     int[] N = null;
		/* 2282 */     short[] D = null;
		/* 2283 */     int[] triangleColours2 = new int[numTriangles];
		/* 2284 */     if (numTexTriangles > 0) {
			/* 2285 */       O = new byte[numTexTriangles];
			/* 2286 */       nc1.currentOffset = 0;
			/* 2287 */       for (int j5 = 0; j5 < numTexTriangles; j5++) {
				/* 2288 */         byte byte0 = O[j5] = nc1.readSignedByte();
				/* 2289 */         if (byte0 == 0)
					/* 2290 */           k4++;
				/* 2291 */         if ((byte0 >= 1) && (byte0 <= 3))
					/* 2292 */           l4++;
				/* 2293 */         if (byte0 == 2)
					/* 2294 */           i5++;
				/*      */       }
			/*      */     }
		/* 2297 */     int k5 = numTexTriangles;
		/* 2298 */     int l5 = k5;
		/* 2299 */     k5 += numVertices;
		/* 2300 */     int i6 = k5;
		/* 2301 */     if (bool)
			/* 2302 */       k5 += numTriangles;
		/* 2303 */     if (l1 == 1)
			/* 2304 */       k5 += numTriangles;
		/* 2305 */     int j6 = k5;
		/* 2306 */     k5 += numTriangles;
		/* 2307 */     int k6 = k5;
		/* 2308 */     if (i2 == 255)
			/* 2309 */       k5 += numTriangles;
		/* 2310 */     int l6 = k5;
		/* 2311 */     if (k2 == 1)
			/* 2312 */       k5 += numTriangles;
		/* 2313 */     int i7 = k5;
		/* 2314 */     if (i3 == 1)
			/* 2315 */       k5 += numVertices;
		/* 2316 */     int j7 = k5;
		/* 2317 */     if (j2 == 1)
			/* 2318 */       k5 += numTriangles;
		/* 2319 */     int k7 = k5;
		/* 2320 */     k5 += i4;
		/* 2321 */     int l7 = k5;
		/* 2322 */     if (l2 == 1)
			/* 2323 */       k5 += numTriangles * 2;
		/* 2324 */     int i8 = k5;
		/* 2325 */     k5 += j4;
		/* 2326 */     int j8 = k5;
		/* 2327 */     k5 += numTriangles * 2;
		/* 2328 */     int k8 = k5;
		/* 2329 */     k5 += j3;
		/* 2330 */     int l8 = k5;
		/* 2331 */     k5 += k3;
		/* 2332 */     int i9 = k5;
		/* 2333 */     k5 += l3;
		/* 2334 */     int j9 = k5;
		/* 2335 */     k5 += k4 * 6;
		/* 2336 */     int k9 = k5;
		/* 2337 */     k5 += l4 * 6;
		/* 2338 */     int i_59_ = 6;
		/* 2339 */     if (newformat != 14) {
			/* 2340 */       if (newformat >= 15)
				/* 2341 */         i_59_ = 9;
			/*      */     } else
			/* 2343 */       i_59_ = 7;
		/* 2344 */     int l9 = k5;
		/* 2345 */     k5 += i_59_ * l4;
		/* 2346 */     int i10 = k5;
		/* 2347 */     k5 += l4;
		/* 2348 */     int j10 = k5;
		/* 2349 */     k5 += l4;
		/* 2350 */     int k10 = k5;
		/* 2351 */     k5 += l4 + i5 * 2;
		/* 2352 */     v = numVertices;
		/* 2353 */     hb = numTriangles;
		/* 2354 */     P = numTexTriangles;
		/* 2355 */     this.verticesParticle = new int[numVertices];
		/* 2356 */     int[] vertexX = new int[numVertices];
		/* 2357 */     int[] vertexY = new int[numVertices];
		/* 2358 */     int[] vertexZ = new int[numVertices];
		/* 2359 */     int[] facePoint1 = new int[numTriangles];
		/* 2360 */     int[] facePoint2 = new int[numTriangles];
		/* 2361 */     int[] facePoint3 = new int[numTriangles];
		/* 2362 */     this.vertexVSkin = new int[numVertices];
		/* 2363 */     this.face_render_type = new int[numTriangles];
		/* 2364 */     this.face_render_priorities = new int[numTriangles];
		/* 2365 */     this.face_alpha = new int[numTriangles];
		/* 2366 */     this.triangleTSkin = new int[numTriangles];
		/* 2367 */     if (i3 == 1)
			/* 2368 */       this.vertexVSkin = new int[numVertices];
		/* 2369 */     if (bool)
			/* 2370 */       this.face_render_type = new int[numTriangles];
		/* 2371 */     if (i2 == 255) {
			/* 2372 */       this.face_render_priorities = new int[numTriangles];
			/*      */     } else
			/* 2374 */       G = (byte)i2;
		/* 2375 */     if (j2 == 1)
			/* 2376 */       this.face_alpha = new int[numTriangles];
		/* 2377 */     if (k2 == 1)
			/* 2378 */       this.triangleTSkin = new int[numTriangles];
		/* 2379 */     if (l2 == 1)
			/* 2380 */       D = new short[numTriangles];
		/* 2381 */     if ((l2 == 1) && (numTexTriangles > 0))
			/* 2382 */       x = new byte[numTriangles];
		/* 2383 */     triangleColours2 = new int[numTriangles];
		/* 2384 */     int i_115_ = k5;
		/* 2385 */     int[] texTrianglesPoint1 = null;
		/* 2386 */     int[] texTrianglesPoint2 = null;
		/* 2387 */     int[] texTrianglesPoint3 = null;
		/* 2388 */     if (numTexTriangles > 0) {
			/* 2389 */       texTrianglesPoint1 = new int[numTexTriangles];
			/* 2390 */       texTrianglesPoint2 = new int[numTexTriangles];
			/* 2391 */       texTrianglesPoint3 = new int[numTexTriangles];
			/* 2392 */       if (l4 > 0) {
				/* 2393 */         kb = new int[l4];
				/* 2394 */         N = new int[l4];
				/* 2395 */         y = new int[l4];
				/* 2396 */         gb = new byte[l4];
				/* 2397 */         lb = new byte[l4];
				/* 2398 */         F = new byte[l4];
				/*      */       }
			/* 2400 */       if (i5 > 0) {
				/* 2401 */         cb = new byte[i5];
				/* 2402 */         J = new byte[i5];
				/*      */       }
			/*      */     }
		/* 2405 */     nc1.currentOffset = l5;
		/* 2406 */     nc2.currentOffset = k8;
		/* 2407 */     nc3.currentOffset = l8;
		/* 2408 */     nc4.currentOffset = i9;
		/* 2409 */     nc5.currentOffset = i7;
		/* 2410 */     int l10 = 0;
		/* 2411 */     int i11 = 0;
		/* 2412 */     int j11 = 0;
		/* 2413 */     for (int k11 = 0; k11 < numVertices; k11++) {
			/* 2414 */       int l11 = nc1.readUnsignedByte();
			/* 2415 */       int j12 = 0;
			/* 2416 */       if ((l11 & 0x1) != 0)
				/* 2417 */         j12 = nc2.method421();
			/* 2418 */       int l12 = 0;
			/* 2419 */       if ((l11 & 0x2) != 0)
				/* 2420 */         l12 = nc3.method421();
			/* 2421 */       int j13 = 0;
			/* 2422 */       if ((l11 & 0x4) != 0)
				/* 2423 */         j13 = nc4.method421();
			/* 2424 */       vertexX[k11] = (l10 + j12);
			/* 2425 */       vertexY[k11] = (i11 + l12);
			/* 2426 */       vertexZ[k11] = (j11 + j13);
			/* 2427 */       l10 = vertexX[k11];
			/* 2428 */       i11 = vertexY[k11];
			/* 2429 */       j11 = vertexZ[k11];
			/* 2430 */       if (this.vertexVSkin != null)
				/* 2431 */         this.vertexVSkin[k11] = nc5.readUnsignedByte();
			/*      */     }
		/* 2433 */     nc1.currentOffset = j8;
		/* 2434 */     nc2.currentOffset = i6;
		/* 2435 */     nc3.currentOffset = k6;
		/* 2436 */     nc4.currentOffset = j7;
		/* 2437 */     nc5.currentOffset = l6;
		/* 2438 */     nc6.currentOffset = l7;
		/* 2439 */     nc7.currentOffset = i8;
		/* 2440 */     for (int i12 = 0; i12 < numTriangles; i12++) {
			/* 2441 */       triangleColours2[i12] = nc1.readUnsignedWord();
			/* 2442 */       if (l1 == 1) {
				/* 2443 */         this.face_render_type[i12] = nc2.readSignedByte();
				/* 2444 */         if (this.face_render_type[i12] == 2)
					/* 2445 */           triangleColours2[i12] = 65535;
				/* 2446 */         this.face_render_type[i12] = 0;
				/*      */       }
			/* 2448 */       if (i2 == 255) {
				/* 2449 */         this.face_render_priorities[i12] = nc3.readSignedByte();
				/*      */       }
			/* 2451 */       if (j2 == 1) {
				/* 2452 */         this.face_alpha[i12] = nc4.readSignedByte();
				/* 2453 */         if (this.face_alpha[i12] < 0)
					/* 2454 */           this.face_alpha[i12] = (256 + this.face_alpha[i12]);
				/*      */       }
			/* 2456 */       if (k2 == 1)
				/* 2457 */         this.triangleTSkin[i12] = nc5.readUnsignedByte();
			/* 2458 */       if (l2 == 1)
				/* 2459 */         D[i12] = ((short)(nc6.readUnsignedWord() - 1));
			/* 2460 */       if (x != null)
				/* 2461 */         if (D[i12] != -1) {
					/* 2462 */           x[i12] = ((byte)(nc7.readUnsignedByte() - 1));
					/*      */         } else
					/* 2464 */           x[i12] = -1;
			/*      */     }
		/* 2466 */     nc1.currentOffset = k7;
		/* 2467 */     nc2.currentOffset = j6;
		/* 2468 */     int point1 = 0;
		/* 2469 */     int point2 = 0;
		/* 2470 */     int point3 = 0;
		/* 2471 */     int offset = 0;
		/* 2472 */     for (int i14 = 0; i14 < numTriangles; i14++) {
			/* 2473 */       int triangleType = nc2.readUnsignedByte();
			/* 2474 */       if (triangleType == 1) {
				/* 2475 */         point1 = nc1.method421() + offset;
				/* 2476 */         offset = point1;
				/* 2477 */         point2 = nc1.method421() + offset;
				/* 2478 */         offset = point2;
				/* 2479 */         point3 = nc1.method421() + offset;
				/* 2480 */         offset = point3;
				/* 2481 */         facePoint1[i14] = point1;
				/* 2482 */         facePoint2[i14] = point2;
				/* 2483 */         facePoint3[i14] = point3;
				/*      */       }
			/* 2485 */       if (triangleType == 2) {
				/* 2486 */         point2 = point3;
				/* 2487 */         point3 = nc1.method421() + offset;
				/* 2488 */         offset = point3;
				/* 2489 */         facePoint1[i14] = point1;
				/* 2490 */         facePoint2[i14] = point2;
				/* 2491 */         facePoint3[i14] = point3;
				/*      */       }
			/* 2493 */       if (triangleType == 3) {
				/* 2494 */         point1 = point3;
				/* 2495 */         point3 = nc1.method421() + offset;
				/* 2496 */         offset = point3;
				/* 2497 */         facePoint1[i14] = point1;
				/* 2498 */         facePoint2[i14] = point2;
				/* 2499 */         facePoint3[i14] = point3;
				/*      */       }
			/* 2501 */       if (triangleType == 4) {
				/* 2502 */         int pointOffset = point1;
				/* 2503 */         point1 = point2;
				/* 2504 */         point2 = pointOffset;
				/* 2505 */         point3 = nc1.method421() + offset;
				/* 2506 */         offset = point3;
				/* 2507 */         facePoint1[i14] = point1;
				/* 2508 */         facePoint2[i14] = point2;
				/* 2509 */         facePoint3[i14] = point3;
				/*      */       }
			/*      */     }
		/* 2512 */     nc1.currentOffset = j9;
		/* 2513 */     nc2.currentOffset = k9;
		/* 2514 */     nc3.currentOffset = l9;
		/* 2515 */     nc4.currentOffset = i10;
		/* 2516 */     nc5.currentOffset = j10;
		/* 2517 */     nc6.currentOffset = k10;
		/* 2518 */     for (int k14 = 0; k14 < numTexTriangles; k14++) {
			/* 2519 */       int i15 = O[k14] & 0xFF;
			/* 2520 */       if (i15 == 0) {
				/* 2521 */         texTrianglesPoint1[k14] = nc1.readUnsignedWord();
				/* 2522 */         texTrianglesPoint2[k14] = nc1.readUnsignedWord();
				/* 2523 */         texTrianglesPoint3[k14] = nc1.readUnsignedWord();
				/*      */       }
			/* 2525 */       if (i15 == 1) {
				/* 2526 */         texTrianglesPoint1[k14] = nc2.readUnsignedWord();
				/* 2527 */         texTrianglesPoint2[k14] = nc2.readUnsignedWord();
				/* 2528 */         texTrianglesPoint3[k14] = nc2.readUnsignedWord();
				/* 2529 */         if (newformat < 15) {
					/* 2530 */           kb[k14] = nc3.readUnsignedWord();
					/* 2531 */           if (newformat >= 14) {
						/* 2532 */             N[k14] = nc3.v(-1);
						/*      */           } else
						/* 2534 */             N[k14] = nc3.readUnsignedWord();
					/* 2535 */           y[k14] = nc3.readUnsignedWord();
					/*      */         } else {
					/* 2537 */           kb[k14] = nc3.v(-1);
					/* 2538 */           N[k14] = nc3.v(-1);
					/* 2539 */           y[k14] = nc3.v(-1);
					/*      */         }
				/* 2541 */         gb[k14] = nc4.readSignedByte();
				/* 2542 */         lb[k14] = nc5.readSignedByte();
				/* 2543 */         F[k14] = nc6.readSignedByte();
				/*      */       }
			/* 2545 */       if (i15 == 2) {
				/* 2546 */         texTrianglesPoint1[k14] = nc2.readUnsignedWord();
				/* 2547 */         texTrianglesPoint2[k14] = nc2.readUnsignedWord();
				/* 2548 */         texTrianglesPoint3[k14] = nc2.readUnsignedWord();
				/* 2549 */         if (newformat >= 15) {
					/* 2550 */           kb[k14] = nc3.v(-1);
					/* 2551 */           N[k14] = nc3.v(-1);
					/* 2552 */           y[k14] = nc3.v(-1);
					/*      */         } else {
					/* 2554 */           kb[k14] = nc3.readUnsignedWord();
					/* 2555 */           if (newformat < 14) {
						/* 2556 */             N[k14] = nc3.readUnsignedWord();
						/*      */           } else
						/* 2558 */             N[k14] = nc3.v(-1);
					/* 2559 */           y[k14] = nc3.readUnsignedWord();
					/*      */         }
				/* 2561 */         gb[k14] = nc4.readSignedByte();
				/* 2562 */         lb[k14] = nc5.readSignedByte();
				/* 2563 */         F[k14] = nc6.readSignedByte();
				/* 2564 */         cb[k14] = nc6.readSignedByte();
				/* 2565 */         J[k14] = nc6.readSignedByte();
				/*      */       }
			/* 2567 */       if (i15 == 3) {
				/* 2568 */         texTrianglesPoint1[k14] = nc2.readUnsignedWord();
				/* 2569 */         texTrianglesPoint2[k14] = nc2.readUnsignedWord();
				/* 2570 */         texTrianglesPoint3[k14] = nc2.readUnsignedWord();
				/* 2571 */         if (newformat < 15) {
					/* 2572 */           kb[k14] = nc3.readUnsignedWord();
					/* 2573 */           if (newformat < 14) {
						/* 2574 */             N[k14] = nc3.readUnsignedWord();
						/*      */           } else
						/* 2576 */             N[k14] = nc3.v(-1);
					/* 2577 */           y[k14] = nc3.readUnsignedWord();
					/*      */         } else {
					/* 2579 */           kb[k14] = nc3.v(-1);
					/* 2580 */           N[k14] = nc3.v(-1);
					/* 2581 */           y[k14] = nc3.v(-1);
					/*      */         }
				/* 2583 */         gb[k14] = nc4.readSignedByte();
				/* 2584 */         lb[k14] = nc5.readSignedByte();
				/* 2585 */         F[k14] = nc6.readSignedByte();
				/*      */       }
			/*      */     }
		/* 2588 */     if (i2 != 255) {
			/* 2589 */       for (int i12 = 0; i12 < numTriangles; i12++)
				/* 2590 */         this.face_render_priorities[i12] = i2;
			/*      */     }
		/* 2592 */     this.face_color = triangleColours2;
		/* 2593 */     this.numberOfVerticeCoordinates = numVertices;
		/* 2594 */     this.numberOfTriangleFaces = numTriangles;
		/* 2595 */     this.verticesXCoordinate = vertexX;
		/* 2596 */     this.verticesYCoordinate = vertexY;
		/* 2597 */     this.verticesZCoordinate = vertexZ;
		/* 2598 */     this.face_a = facePoint1;
		/* 2599 */     this.face_b = facePoint2;
		/* 2600 */     this.face_c = facePoint3;
		/* 2601 */     filterTriangles();
		/*      */   }


	private void readOldModel(int i) {
		int j = -870;
		aBoolean1618 = true;
		rendersWithinOneTile = false;
		anInt1620++;
		ModelHeader header = modelHeader[i];
		numberOfVerticeCoordinates = header.verticeCount;
		numberOfTriangleFaces = header.triangleCount;
		numberOfTexturesFaces = header.texturedTriangleCount;
		this.verticesParticle = new int[numberOfVerticeCoordinates];
		verticesXCoordinate = new int[numberOfVerticeCoordinates];
		verticesYCoordinate = new int[numberOfVerticeCoordinates];
		verticesZCoordinate = new int[numberOfVerticeCoordinates];
		face_a = new int[numberOfTriangleFaces];
		face_b = new int[numberOfTriangleFaces];
		while (j >= 0)
			aBoolean1618 = !aBoolean1618;
		face_c = new int[numberOfTriangleFaces];
		textures_face_a = new int[numberOfTexturesFaces];
		textures_face_b = new int[numberOfTexturesFaces];
		textures_face_c = new int[numberOfTexturesFaces];
		if (header.vskinBasePos >= 0)
			vertexVSkin = new int[numberOfVerticeCoordinates];
		if (header.drawTypeBasePos >= 0)
			face_render_type = new int[numberOfTriangleFaces];
		if (header.facePriorityBasePos >= 0)
			face_render_priorities = new int[numberOfTriangleFaces];
		else
			face_priority = -header.facePriorityBasePos - 1;
		if (header.alphaBasepos >= 0)
			face_alpha = new int[numberOfTriangleFaces];
		if (header.tskinBasepos >= 0)
			triangleTSkin = new int[numberOfTriangleFaces];
		face_color = new int[numberOfTriangleFaces];
		Stream stream = new Stream(header.modelData);
		stream.currentOffset = header.verticesModOffset;
		Stream stream_1 = new Stream(header.modelData);
		stream_1.currentOffset = header.verticesXOffset;
		Stream stream_2 = new Stream(header.modelData);
		stream_2.currentOffset = header.verticesYOffset;
		Stream stream_3 = new Stream(header.modelData);
		stream_3.currentOffset = header.verticesZOffset;
		Stream stream_4 = new Stream(header.modelData);
		stream_4.currentOffset = header.vskinBasePos;
		int k = 0;
		int l = 0;
		int i1 = 0;
		for (int j1 = 0; j1 < numberOfVerticeCoordinates; j1++) {
			int k1 = stream.readUnsignedByte();
			int i2 = 0;
			if ((k1 & 0x1) != 0)
				i2 = stream_1.method421();
			int k2 = 0;
			if ((k1 & 0x2) != 0)
				k2 = stream_2.method421();
			int i3 = 0;
			if ((k1 & 0x4) != 0)
				i3 = stream_3.method421();
			verticesXCoordinate[j1] = k + i2;
			verticesYCoordinate[j1] = l + k2;
			verticesZCoordinate[j1] = i1 + i3;
			k = verticesXCoordinate[j1];
			l = verticesYCoordinate[j1];
			i1 = verticesZCoordinate[j1];
			if (vertexVSkin != null)
				vertexVSkin[j1] = stream_4.readUnsignedByte();
		}
		stream.currentOffset = header.triColourOffset;
		stream_1.currentOffset = header.drawTypeBasePos;
		stream_2.currentOffset = header.facePriorityBasePos;
		stream_3.currentOffset = header.alphaBasepos;
		stream_4.currentOffset = header.tskinBasepos;
		for (int l1 = 0; l1 < numberOfTriangleFaces; l1++) {
			face_color[l1] = stream.readUnsignedWord();
			if (face_render_type != null)
				face_render_type[l1] = stream_1.readUnsignedByte();
			if (face_render_priorities != null)
				face_render_priorities[l1] = stream_2.readUnsignedByte();
			if (face_alpha != null) {
				face_alpha[l1] = stream_3.readUnsignedByte();
			}
			if (triangleTSkin != null)
				triangleTSkin[l1] = stream_4.readUnsignedByte();
		}
		if (this.face_alpha == null)
			/* 2689 */       this.face_alpha = new int[this.numberOfTriangleFaces];
		stream.currentOffset = header.triVPointOffset;
		stream_1.currentOffset = header.triMeshLinkOffset;
		int j2 = 0;
		int l2 = 0;
		int j3 = 0;
		int k3 = 0;
		for (int l3 = 0; l3 < numberOfTriangleFaces; l3++) {
			int i4 = stream_1.readUnsignedByte();
			if (i4 == 1) {
				j2 = stream.method421() + k3;
				k3 = j2;
				l2 = stream.method421() + k3;
				k3 = l2;
				j3 = stream.method421() + k3;
				k3 = j3;
				face_a[l3] = j2;
				face_b[l3] = l2;
				face_c[l3] = j3;
			}
			if (i4 == 2) {
				j2 = j2;
				l2 = j3;
				j3 = stream.method421() + k3;
				k3 = j3;
				face_a[l3] = j2;
				face_b[l3] = l2;
				face_c[l3] = j3;
			}
			if (i4 == 3) {
				j2 = j3;
				l2 = l2;
				j3 = stream.method421() + k3;
				k3 = j3;
				face_a[l3] = j2;
				face_b[l3] = l2;
				face_c[l3] = j3;
			}
			if (i4 == 4) {
				int k4 = j2;
				j2 = l2;
				l2 = k4;
				j3 = stream.method421() + k3;
				k3 = j3;
				face_a[l3] = j2;
				face_b[l3] = l2;
				face_c[l3] = j3;
			}
		}
		stream.currentOffset = header.textureInfoBasePos;
		for (int j4 = 0; j4 < numberOfTexturesFaces; j4++) {
			textures_face_a[j4] = stream.readUnsignedWord();
			textures_face_b[j4] = stream.readUnsignedWord();
			textures_face_c[j4] = stream.readUnsignedWord();
		}
		// filterTriangles();
	}

	public void forceRecolour(int i, int j) {
		for (int k = 0; k < numberOfTriangleFaces; k++)
			face_color[k] = j;
	}

	public void recolour(int itemId, int editedColor, int originalColor) {
		for (int k = 0; k < numberOfTriangleFaces; k++) {
			if (face_color[k] == editedColor) {
				face_color[k] = originalColor;
			}
		}
	}

	public void recolour(int i, int j) {
		for (int k = 0; k < numberOfTriangleFaces; k++)
			if (face_color[k] == i) {
				face_color[k] = j;
			}
	}

	public void recolour2(Color i, int j) {
		if (i == Color.decode("#7A7A7A")) {
			setTexture(j);
		}
	}

	private final void reduce(int i) {
		if (face_color != null)
			if (face_color[i] == 65535)
				return;

		int j = Rasterizer.center_x;
		int k = Rasterizer.center_y;
		int l = 0;
		int i1 = face_a[i];
		int j1 = face_b[i];
		int k1 = face_c[i];
		int l1 = camera_vertex_z[i1];
		int i2 = camera_vertex_z[j1];
		int j2 = camera_vertex_z[k1];

		if (l1 >= 50) {
			anIntArray1678[l] = projected_vertex_x[i1];
			anIntArray1679[l] = projected_vertex_y[i1];
			anIntArray1680[l++] = face_shade_a[i];
		} else {
			int k2 = camera_vertex_y[i1];
			int k3 = camera_vertex_x[i1];
			int k4 = face_shade_a[i];
			if (j2 >= 50) {
				int k5 = (50 - l1) * lightDecay[j2 - l1];
				anIntArray1678[l] = j + (k2 + ((camera_vertex_y[k1] - k2) * k5 >> 16)) * WorldController.focalLength / 50;
				anIntArray1679[l] = k + (k3 + ((camera_vertex_x[k1] - k3) * k5 >> 16)) * WorldController.focalLength / 50;
				anIntArray1680[l++] = k4 + ((face_shade_c[i] - k4) * k5 >> 16);
			}
			if (i2 >= 50) {
				int l5 = (50 - l1) * lightDecay[i2 - l1];
				anIntArray1678[l] = j + (k2 + ((camera_vertex_y[j1] - k2) * l5 >> 16)) * WorldController.focalLength / 50;
				anIntArray1679[l] = k + (k3 + ((camera_vertex_x[j1] - k3) * l5 >> 16)) * WorldController.focalLength / 50;
				anIntArray1680[l++] = k4 + ((face_shade_b[i] - k4) * l5 >> 16);
			}
		}
		if (i2 >= 50) {
			anIntArray1678[l] = projected_vertex_x[j1];
			anIntArray1679[l] = projected_vertex_y[j1];
			anIntArray1680[l++] = face_shade_b[i];
		} else {
			int l2 = camera_vertex_y[j1];
			int l3 = camera_vertex_x[j1];
			int l4 = face_shade_b[i];
			if (l1 >= 50) {
				int i6 = (50 - i2) * lightDecay[l1 - i2];
				anIntArray1678[l] = j + (l2 + ((camera_vertex_y[i1] - l2) * i6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1679[l] = k + (l3 + ((camera_vertex_x[i1] - l3) * i6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1680[l++] = l4 + ((face_shade_a[i] - l4) * i6 >> 16);
			}
			if (j2 >= 50) {
				int j6 = (50 - i2) * lightDecay[j2 - i2];
				anIntArray1678[l] = j + (l2 + ((camera_vertex_y[k1] - l2) * j6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1679[l] = k + (l3 + ((camera_vertex_x[k1] - l3) * j6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1680[l++] = l4 + ((face_shade_c[i] - l4) * j6 >> 16);
			}
		}
		if (j2 >= 50) {
			anIntArray1678[l] = projected_vertex_x[k1];
			anIntArray1679[l] = projected_vertex_y[k1];
			anIntArray1680[l++] = face_shade_c[i];
		} else {
			int i3 = camera_vertex_y[k1];
			int i4 = camera_vertex_x[k1];
			int i5 = face_shade_c[i];
			if (i2 >= 50) {
				int k6 = (50 - j2) * lightDecay[i2 - j2];
				anIntArray1678[l] = j + (i3 + ((camera_vertex_y[j1] - i3) * k6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1679[l] = k + (i4 + ((camera_vertex_x[j1] - i4) * k6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1680[l++] = i5 + ((face_shade_b[i] - i5) * k6 >> 16);
			}
			if (l1 >= 50) {
				int l6 = (50 - j2) * lightDecay[l1 - j2];
				anIntArray1678[l] = j + (i3 + ((camera_vertex_y[i1] - i3) * l6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1679[l] = k + (i4 + ((camera_vertex_x[i1] - i4) * l6 >> 16)) * WorldController.focalLength / 50;
				anIntArray1680[l++] = i5 + ((face_shade_a[i] - i5) * l6 >> 16);
			}
		}
		int j3 = anIntArray1678[0];
		int j4 = anIntArray1678[1];
		int j5 = anIntArray1678[2];
		int i7 = anIntArray1679[0];
		int j7 = anIntArray1679[1];
		int k7 = anIntArray1679[2];
		if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
			Rasterizer.restrict_edges = false;
			if (l == 3) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.viewportRX
						|| j4 > DrawingArea.viewportRX || j5 > DrawingArea.viewportRX)
					Rasterizer.restrict_edges = true;
				int l7;
				if (face_render_type == null)
					l7 = 0;
				else
					l7 = face_render_type[i] & 3;
				if (l7 == 0)
					Rasterizer.drawShadedTriangle(i7, j7, k7, j3, j4, j5,
							anIntArray1680[0], anIntArray1680[1],
							anIntArray1680[2]);
				else if (l7 == 1)
					Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5,
							hsl2rgb[face_shade_a[i]]);
				else if (l7 == 2) {
					int j8 = face_render_type[i] >> 2;
					int k9 = textures_face_a[j8];
					int k10 = textures_face_b[j8];
					int k11 = textures_face_c[j8];
					if(Client.getOption("hd_tex")) {
						Rasterizer.drawTexturedTriangle_model(i7, j7, k7, j3, j4, j5,
								anIntArray1680[0], anIntArray1680[1],
								anIntArray1680[2], camera_vertex_y[k9],
								camera_vertex_y[k10], camera_vertex_y[k11],
								projected_vertex_z[k9],
								projected_vertex_z[k10], projected_vertex_z[k11],
								camera_vertex_x[k9], camera_vertex_x[k10],
								camera_vertex_x[k11], camera_vertex_z[k9],
								camera_vertex_z[k10], camera_vertex_z[k11],
								face_color[i], face_color[i], false, false);
					} else {
						Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5,
								anIntArray1680[0], anIntArray1680[1],
								anIntArray1680[2], camera_vertex_y[k9],
								camera_vertex_y[k10], camera_vertex_y[k11],
								camera_vertex_x[k9], camera_vertex_x[k10],
								camera_vertex_x[k11], camera_vertex_z[k9],
								camera_vertex_z[k10], camera_vertex_z[k11],
								face_color[i]);
					}
				} else if (l7 == 3) {
					int k8 = face_render_type[i] >> 2;
					int l9 = textures_face_a[k8];
					int l10 = textures_face_b[k8];
					int l11 = textures_face_c[k8];
					if(Client.getOption("hd_tex")) {
						Rasterizer.drawTexturedTriangle_model(i7, j7, k7, j3, j4, j5, -1, -1, -1,
								face_shade_a[i], face_shade_a[i], face_shade_a[i],
								camera_vertex_y[l9], camera_vertex_y[l10],
								camera_vertex_y[l11], camera_vertex_x[l9],
								camera_vertex_x[l10], camera_vertex_x[l11],
								camera_vertex_z[l9], camera_vertex_z[l10],
								camera_vertex_z[l11], face_color[i], face_color[i], false, false);
					} else
						Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5,
								face_shade_a[i], face_shade_a[i], face_shade_a[i],
								camera_vertex_y[l9], camera_vertex_y[l10],
								camera_vertex_y[l11], camera_vertex_x[l9],
								camera_vertex_x[l10], camera_vertex_x[l11],
								camera_vertex_z[l9], camera_vertex_z[l10],
								camera_vertex_z[l11], face_color[i]);
				}
			}
			if (l == 4) {
				if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.viewportRX
						|| j4 > DrawingArea.viewportRX || j5 > DrawingArea.viewportRX
						|| anIntArray1678[3] < 0
						|| anIntArray1678[3] > DrawingArea.viewportRX)
					Rasterizer.restrict_edges = true;
				int i8;
				if (face_render_type == null)
					i8 = 0;
				else
					i8 = face_render_type[i] & 3;
				if (i8 == 0) {
					Rasterizer.drawShadedTriangle(i7, j7, k7, j3, j4, j5,
							anIntArray1680[0], anIntArray1680[1],
							anIntArray1680[2]);
					Rasterizer.drawShadedTriangle(i7, k7, anIntArray1679[3], j3, j5,
							anIntArray1678[3], anIntArray1680[0],
							anIntArray1680[2], anIntArray1680[3]);
					return;
				}
				if (i8 == 1) {
					int l8 = hsl2rgb[face_shade_a[i]];
					Rasterizer.drawFlatTriangle(i7, j7, k7, j3, j4, j5, l8);
					Rasterizer.drawFlatTriangle(i7, k7, anIntArray1679[3], j3, j5,
							anIntArray1678[3], l8);
					return;
				}
				if (i8 == 2) {
					int i9 = face_render_type[i] >> 2;
					int i10 = textures_face_a[i9];
					int i11 = textures_face_b[i9];
					int i12 = textures_face_c[i9];

					if(Client.getOption("hd_tex")) {
						Rasterizer.drawTexturedTriangle_model(i7, j7, k7, j3, j4, j5, -1, -1, -1,
								anIntArray1680[0], anIntArray1680[1],
								anIntArray1680[2], camera_vertex_y[i10],
								camera_vertex_y[i11], camera_vertex_y[i12],
								camera_vertex_x[i10], camera_vertex_x[i11],
								camera_vertex_x[i12], camera_vertex_z[i10],
								camera_vertex_z[i11], camera_vertex_z[i12],
								face_color[i], face_color[i], false, false);
						Rasterizer.drawTexturedTriangle_model(i7, k7, anIntArray1679[3], j3, j5, -1, -1, -1,
								anIntArray1678[3], anIntArray1680[0],
								anIntArray1680[2], anIntArray1680[3],
								camera_vertex_y[i10], camera_vertex_y[i11],
								camera_vertex_y[i12], camera_vertex_x[i10],
								camera_vertex_x[i11], camera_vertex_x[i12],
								camera_vertex_z[i10], camera_vertex_z[i11],
								camera_vertex_z[i12], face_color[i], face_color[i], false, false);
					} else {
						Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5,
								anIntArray1680[0], anIntArray1680[1],
								anIntArray1680[2], camera_vertex_y[i10],
								camera_vertex_y[i11], camera_vertex_y[i12],
								camera_vertex_x[i10], camera_vertex_x[i11],
								camera_vertex_x[i12], camera_vertex_z[i10],
								camera_vertex_z[i11], camera_vertex_z[i12],
								face_color[i]);
						Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5,
								anIntArray1678[3], anIntArray1680[0],
								anIntArray1680[2], anIntArray1680[3],
								camera_vertex_y[i10], camera_vertex_y[i11],
								camera_vertex_y[i12], camera_vertex_x[i10],
								camera_vertex_x[i11], camera_vertex_x[i12],
								camera_vertex_z[i10], camera_vertex_z[i11],
								camera_vertex_z[i12], face_color[i]);
					}
					return;
				}
				if (i8 == 3) {
					int j9 = face_render_type[i] >> 2;
					int j10 = textures_face_a[j9];
					int j11 = textures_face_b[j9];
					int j12 = textures_face_c[j9];
					if(Client.getOption("hd_tex")) {
						Rasterizer.drawTexturedTriangle_model(i7, j7, k7, j3, j4, j5, -1, -1, -1,
								face_shade_a[i], face_shade_a[i], face_shade_a[i],
								camera_vertex_y[j10], camera_vertex_y[j11],
								camera_vertex_y[j12], camera_vertex_x[j10],
								camera_vertex_x[j11], camera_vertex_x[j12],
								camera_vertex_z[j10], camera_vertex_z[j11],
								camera_vertex_z[j12], face_color[i], face_color[i], false, false);
						Rasterizer.drawTexturedTriangle_model(i7, k7, anIntArray1679[3], j3, j5, -1, -1, -1,
								anIntArray1678[3], face_shade_a[i],
								face_shade_a[i], face_shade_a[i],
								camera_vertex_y[j10], camera_vertex_y[j11],
								camera_vertex_y[j12], camera_vertex_x[j10],
								camera_vertex_x[j11], camera_vertex_x[j12],
								camera_vertex_z[j10], camera_vertex_z[j11],
								camera_vertex_z[j12], face_color[i], face_color[i], false, false);
					} else {
						Rasterizer.drawTexturedTriangle(i7, j7, k7, j3, j4, j5,
								face_shade_a[i], face_shade_a[i], face_shade_a[i],
								camera_vertex_y[j10], camera_vertex_y[j11],
								camera_vertex_y[j12], camera_vertex_x[j10],
								camera_vertex_x[j11], camera_vertex_x[j12],
								camera_vertex_z[j10], camera_vertex_z[j11],
								camera_vertex_z[j12], face_color[i]);
						Rasterizer.drawTexturedTriangle(i7, k7, anIntArray1679[3], j3, j5,
								anIntArray1678[3], face_shade_a[i],
								face_shade_a[i], face_shade_a[i],
								camera_vertex_y[j10], camera_vertex_y[j11],
								camera_vertex_y[j12], camera_vertex_x[j10],
								camera_vertex_x[j11], camera_vertex_x[j12],
								camera_vertex_z[j10], camera_vertex_z[j11],
								camera_vertex_z[j12], face_color[i]);
					}
				}
			}
		}
	}

	public void renderAtPoint(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2, int id) {
		renderAtPointX = j1 + Client.instance.xCameraPos;
		renderAtPointY = l1 + Client.instance.yCameraPos;
		renderAtPointZ = k1 + Client.instance.zCameraPos;
		lastRenderedRotation = i;
		int j2 = l1 * i1 - j1 * l >> 16;
		int k2 = k1 * j + j2 * k >> 16;
		int l2 = anInt1650 * k >> 16;
		int i3 = k2 + l2;

		if (i3 <= 50 || k2 >= 3500)
			return;
		int j3 = l1 * l + j1 * i1 >> 16;
		int k3 = (j3 - anInt1650) * WorldController.focalLength;
		if (k3 / i3 >= DrawingArea.viewport_centerX)
			return;
		int l3 = (j3 + anInt1650) * WorldController.focalLength;
		if (l3 / i3 <= -DrawingArea.viewport_centerX)
			return;
		int i4 = k1 * k - j2 * j >> 16;
		int j4 = anInt1650 * j >> 16;
		int k4 = (i4 + j4) * WorldController.focalLength;
		if (k4 / i3 <= -DrawingArea.viewport_centerY)
			return;
		int l4 = j4 + (super.modelHeight * k >> 16);
		int i5 = (i4 - l4) * WorldController.focalLength;
		if (i5 / i3 >= DrawingArea.viewport_centerY)
			return;
		int j5 = l2 + (super.modelHeight * j >> 16);
		boolean flag = false;
		if (k2 - j5 <= 50)
			flag = true;
		boolean flag1 = false;
		if (i2 > 0 && objectExists) {
			int k5 = k2 - l2;
			if (k5 <= 50)
				k5 = 50;
			if (j3 > 0) {
				k3 /= i3;
				l3 /= k5;
			} else {
				l3 /= i3;
				k3 /= k5;
			}
			if (i4 > 0) {
				i5 /= i3;
				k4 /= k5;
			} else {
				k4 /= i3;
				i5 /= k5;
			}
			int i6 = currentCursorX - Rasterizer.center_x;
			int k6 = currentCursorY - Rasterizer.center_y;
			if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4)
				if (rendersWithinOneTile) {
					mapObjectIds[objectsRendered] = id;
					objectsInCurrentRegion[objectsRendered++] = i2;
				} else
					flag1 = true;
		}
		int l5 = Rasterizer.center_x;
		int j6 = Rasterizer.center_y;
		int l6 = 0;
		int i7 = 0;
		if (i != 0) {
			l6 = SINE[i];
			i7 = COSINE[i];
		}
		for (int j7 = 0; j7 < numberOfVerticeCoordinates; j7++) {
			int k7 = verticesXCoordinate[j7];
			int l7 = verticesYCoordinate[j7];
			int i8 = verticesZCoordinate[j7];
			if (i != 0) {
				int j8 = i8 * l6 + k7 * i7 >> 16;
				i8 = i8 * i7 - k7 * l6 >> 16;
				k7 = j8;
			}
			k7 += j1;
			l7 += k1;
			i8 += l1;
			int k8 = i8 * l + k7 * i1 >> 16;
			i8 = i8 * i1 - k7 * l >> 16;
			k7 = k8;
			k8 = l7 * k - i8 * j >> 16;
			i8 = l7 * j + i8 * k >> 16;
			l7 = k8;
			anIntArray1667[j7] = i8 - k2;
			if (i8 >= 50) {
				projected_vertex_x[j7] = l5 + k7 * WorldController.focalLength / i8;
				projected_vertex_y[j7] = j6 + l7 * WorldController.focalLength / i8;
				projected_vertex_z[j7] = i8;
			} else {
				projected_vertex_x[j7] = -5000;
				flag = true;
			}
			if (flag || numberOfTexturesFaces > 0) {
				camera_vertex_y[j7] = k7;
				camera_vertex_x[j7] = l7;
				camera_vertex_z[j7] = i8;
			}
		}

		try {
			translateToScreen(flag, flag1, i2, id);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	public void renderSingle(int rotation_2, int offsetX, int rotation_1, int offsetY, int zoom_sine, int zoom_cosine) {
		try {
			int i = 0;
			int base_draw_x = Rasterizer.center_x;
			int base_draw_y = Rasterizer.center_y;
			int base_sine = SINE[i];
			int base_cosine = COSINE[i];
			int rot_2_sine = SINE[rotation_2];
			int rot_2_cosine = COSINE[rotation_2];
			int offsetX_sine = SINE[offsetX];
			int offsetX_cosine = COSINE[offsetX];
			int rot_1_sine = SINE[rotation_1];
			int rot_1_cosine = COSINE[rotation_1];
			int calculated_zoom = zoom_sine * rot_1_sine + zoom_cosine * rot_1_cosine >> 16;
			for (int vertexId = 0; vertexId < numberOfVerticeCoordinates; vertexId++) {
				int baseVertexX = verticesXCoordinate[vertexId];
				int baseVertexY = verticesYCoordinate[vertexId];
				int baseVertexZ = verticesZCoordinate[vertexId];
				if (offsetX != 0) {
					int calculatedVertexX = baseVertexY * offsetX_sine + baseVertexX * offsetX_cosine >> 16;
					baseVertexY = baseVertexY * offsetX_cosine - baseVertexX * offsetX_sine >> 16;
					baseVertexX = calculatedVertexX;
				}
				if (i != 0) {
					int calculatedVertexY = baseVertexY * base_cosine - baseVertexZ * base_sine >> 16;
					baseVertexZ = baseVertexY * base_sine + baseVertexZ * base_cosine >> 16;
					baseVertexY = calculatedVertexY;
				}
				if (rotation_2 != 0) {
					int calculatedVertexZ = baseVertexZ * rot_2_sine + baseVertexX * rot_2_cosine >> 16;
					baseVertexZ = baseVertexZ * rot_2_cosine - baseVertexX * rot_2_sine >> 16;
					baseVertexX = calculatedVertexZ;
				}
				baseVertexX += offsetY;
				baseVertexY += zoom_sine;
				baseVertexZ += zoom_cosine;
				int j6 = baseVertexY * rot_1_cosine - baseVertexZ * rot_1_sine >> 16;
				baseVertexZ = baseVertexY * rot_1_sine + baseVertexZ * rot_1_cosine >> 16;
				baseVertexY = j6;
				anIntArray1667[vertexId] = baseVertexZ - calculated_zoom;
				projected_vertex_x[vertexId] = base_draw_x + (baseVertexX << 9) / baseVertexZ;
				projected_vertex_y[vertexId] = base_draw_y + (baseVertexY << 9) / baseVertexZ;
				projected_vertex_z[vertexId] = baseVertexZ;
				if (numberOfTexturesFaces > 0) {
					camera_vertex_y[vertexId] = baseVertexX;
					camera_vertex_x[vertexId] = baseVertexY;
					camera_vertex_z[vertexId] = baseVertexZ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			translateToScreen(false, false, 0, -1);
			return;
		} catch (Exception _ex) {
			return;
		}
	}

	public void reset() {
		verticesXCoordinate = null;
		verticesYCoordinate = null;
		verticesZCoordinate = null;
		face_a = null;
		face_b = null;
		face_c = null;
		face_shade_a = null;
		face_shade_b = null;
		face_shade_c = null;
		face_render_type = null;
		face_render_priorities = null;
		face_alpha = null;
		face_color = null;
		textures_face_a = textures_face_b = textures_face_c = null;
		vertexVSkin = null;
		triangleTSkin = null;
		vertexSkin = null;
		triangleSkin = null;
		vertexNormalOffset = null;
	}

	public void rotateBy90() {
		for (int j = 0; j < numberOfVerticeCoordinates; j++) {
			int k = verticesXCoordinate[j];
			verticesXCoordinate[j] = verticesZCoordinate[j];
			verticesZCoordinate[j] = -k;
		}
	}

	public void rotateX(int i) {
		int k = SINE[i];
		int l = COSINE[i];
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			int j1 = verticesYCoordinate[i1] * l - verticesZCoordinate[i1] * k >> 16;
			verticesZCoordinate[i1] = verticesYCoordinate[i1] * k + verticesZCoordinate[i1] * l >> 16;
			verticesYCoordinate[i1] = j1;
		}
	}

	public void scale2(int i, int j , int k) {
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			verticesXCoordinate[i1] = verticesXCoordinate[i1] * i >> 7;
			verticesYCoordinate[i1] = verticesYCoordinate[i1] * j >> 7;
			verticesZCoordinate[i1] = verticesZCoordinate[i1] * k >> 7;
		}
	}

	public void scaleT(int i, int j, int l) {
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			verticesXCoordinate[i1] = (verticesXCoordinate[i1] * i) / 128;
			verticesYCoordinate[i1] = (verticesYCoordinate[i1] * l) / 128;
			verticesZCoordinate[i1] = (verticesZCoordinate[i1] * j) / 128;
		}

	}


	public void setTexture(int tex,int fromColor) {
		numberOfTexturesFaces = numberOfTriangleFaces;

		int set2 = 0;
		this.numberOfTexturesFaces = this.numberOfTriangleFaces;

		if (this.face_render_type == null) {
			this.face_render_type = new int[this.numberOfTriangleFaces];
		}

		if (this.face_color == null) {
			this.face_color = new int[this.numberOfTriangleFaces];
		}
		textures_face_a = new int[numberOfTriangleFaces];
		textures_face_b = new int[numberOfTriangleFaces];
		textures_face_c = new int[numberOfTriangleFaces];

		int assigned = 0;
		this.face_priority = 10;

		for(int i = 0; i < this.numberOfTriangleFaces; ++i) {
			if (this.face_color[i] == fromColor) {
				this.face_priority = 10;
				this.face_color[i] = tex;
				this.face_render_type[i] = 3 +set2;
				set2 += 4;
				this.textures_face_a[assigned] = this.face_a[i];
				this.textures_face_b[assigned] = this.face_b[i];
				this.textures_face_c[assigned] = this.face_c[i];
				assigned ++;
			}
		}
	}



	public void setTexture(int tex,int fromColor,int toColor) {

		int set2 = 0;
		this.numberOfTexturesFaces = this.numberOfTriangleFaces;

		if (this.face_render_type == null) {
			this.face_render_type = new int[this.numberOfTriangleFaces];
		}

		if (this.face_color == null) {
			this.face_color = new int[this.numberOfTriangleFaces];
		}
		textures_face_a = new int[numberOfTriangleFaces];
		textures_face_b = new int[numberOfTriangleFaces];
		textures_face_c = new int[numberOfTriangleFaces];
		this.face_priority = 10;

		int assigned = 0;
		for(int i = 0; i < this.numberOfTriangleFaces; ++i) {
			if (this.face_color[i] >= fromColor && this.face_color[i] <= toColor) {
				this.face_priority = 10;
				this.face_color[i] = tex;
				this.face_render_type[i] = 3 +set2;
				set2 += 4;
				this.textures_face_a[assigned] = this.face_a[i];
				this.textures_face_b[assigned] = this.face_b[i];
				this.textures_face_c[assigned] = this.face_c[i];
				assigned ++;
			}
		}
	}

	public void setTexture(int tex,int fromColor,int toColor, int tex2) {

		int set2 = 0;
		this.numberOfTexturesFaces = this.numberOfTriangleFaces;

		if (this.face_render_type == null) {
			this.face_render_type = new int[this.numberOfTriangleFaces];
		}

		if (this.face_color == null) {
			this.face_color = new int[this.numberOfTriangleFaces];
		}
		textures_face_a = new int[numberOfTriangleFaces];
		textures_face_b = new int[numberOfTriangleFaces];
		textures_face_c = new int[numberOfTriangleFaces];
		this.face_priority = 10;

		int assigned = 0;
		for(int i = 0; i < this.numberOfTriangleFaces; ++i) {
			if (this.face_color[i] >= fromColor && this.face_color[i] <= toColor) {
				this.face_priority = 10;
				this.face_color[i] = tex;
			} else {
				this.face_color[i] = tex2;
			}

				this.face_render_type[i] = 3 +set2;
				set2 += 4;
				this.textures_face_a[assigned] = this.face_a[i];
				this.textures_face_b[assigned] = this.face_b[i];
				this.textures_face_c[assigned] = this.face_c[i];
				assigned ++;
			}
	}


	public void setTexture(int tex) {


		int set2 = 0;
		this.numberOfTexturesFaces = this.numberOfTriangleFaces;

		if (this.face_render_type == null) {
			this.face_render_type = new int[this.numberOfTriangleFaces];
		}
		if (this.face_color == null) {
			this.face_color = new int[this.numberOfTriangleFaces];
		}
		textures_face_a = new int[numberOfTriangleFaces];
		textures_face_b = new int[numberOfTriangleFaces];
		textures_face_c = new int[numberOfTriangleFaces];

		this.face_priority = 10;
		for(int i = 0; i < this.numberOfTriangleFaces; ++i) {
			this.face_priority = 10;
			this.face_color[i] = tex;
			this.face_render_type[i] = 3 +set2;
			set2 += 4;
			this.textures_face_a[i] = face_a[i];
			this.textures_face_b[i] = face_b[i];
			this.textures_face_c[i] = face_c[i];
		}
	}

	public void setInstanceTicket(int tex) {


		int set2 = 0;
		this.numberOfTexturesFaces = this.numberOfTriangleFaces;

		if (this.face_render_type == null) {
			this.face_render_type = new int[this.numberOfTriangleFaces];
		}
		if (this.face_color == null) {
			this.face_color = new int[this.numberOfTriangleFaces];
		}
		textures_face_a = new int[numberOfTriangleFaces];
		textures_face_b = new int[numberOfTriangleFaces];
		textures_face_c = new int[numberOfTriangleFaces];

this.face_priority = 10;

		for(int i = 0; i < this.numberOfTriangleFaces; ++i) {
			this.face_priority = 10;
			this.face_render_type[i] = 3 +set2;
			set2 += 4;
			this.textures_face_a[i] = face_a[i]+5;
			this.textures_face_b[i] = face_b[i]+15;
			this.textures_face_c[i] = face_c[i]+25;
			this.face_color[i] = tex;

		}
	}

	public void setTexture() {

		numberOfTexturesFaces = numberOfTriangleFaces;

		int set2 = 0;
		this.numberOfTexturesFaces = this.numberOfTriangleFaces;

		if (this.face_render_type == null) {
			this.face_render_type = new int[this.numberOfTriangleFaces];
		}

		if (this.face_color == null) {
			this.face_color = new int[this.numberOfTriangleFaces];
		}
		textures_face_a = new int[numberOfTriangleFaces];
		textures_face_b = new int[numberOfTriangleFaces];
		textures_face_c = new int[numberOfTriangleFaces];

		for (int i = 0; i < this.numberOfTriangleFaces; ++i) {
			this.face_priority = 10;
			this.face_render_type[i] = 2 + set2;
			set2 += 4;
			this.textures_face_a[i] = this.face_a[i];
			this.textures_face_b[i] = this.face_b[i];
			this.textures_face_c[i] = this.face_c[i];
		}

	}

	public void translate(int i, int j, int l) {
		for (int i1 = 0; i1 < numberOfVerticeCoordinates; i1++) {
			verticesXCoordinate[i1] += i;
			verticesYCoordinate[i1] += j;
			verticesZCoordinate[i1] += l;
		}
	}

	private final void translateToScreen(boolean flag, boolean needAddToSelectedObjects, int i, int id) {
		for (int j = 0; j < diagonal3D; j++)
			depthListIndices[j] = 0;

		// filterTriangles();
		for (int triangleId = 0; triangleId < numberOfTriangleFaces; triangleId++) {
			if (face_render_type != null && face_render_type[triangleId] == -1
					|| face_alpha != null && face_alpha[triangleId] >= 255)
				continue;
			int face_a_pos = face_a[triangleId];
			int face_b_pos = face_b[triangleId];
			int face_c_pos = face_c[triangleId];

			int vertexXA = projected_vertex_x[face_a_pos];
			int vertexXB = projected_vertex_x[face_b_pos];
			int vertexXC = projected_vertex_x[face_c_pos];
			if (flag && (vertexXA == -5000 || vertexXB == -5000 || vertexXC == -5000)) {
				outOfReach[triangleId] = true;
				int j5 = (anIntArray1667[face_a_pos] + anIntArray1667[face_b_pos] + anIntArray1667[face_c_pos]) / 3
						+ anInt1653;
				faceLists[j5][depthListIndices[j5]++] = triangleId;
				continue;
			}
			if (needAddToSelectedObjects && cursorOn(currentCursorX, currentCursorY, projected_vertex_y[face_a_pos],
					projected_vertex_y[face_b_pos], projected_vertex_y[face_c_pos], vertexXA, vertexXB, vertexXC)) {
				mapObjectIds[objectsRendered] = id;
				objectsInCurrentRegion[objectsRendered++] = i;
				needAddToSelectedObjects = false;
			}
			if ((vertexXA - vertexXB) * (projected_vertex_y[face_c_pos] - projected_vertex_y[face_b_pos])
					- (projected_vertex_y[face_a_pos] - projected_vertex_y[face_b_pos]) * (vertexXC - vertexXB) > 0) {
				outOfReach[triangleId] = false;
				hasAnEdgeToRestrict[triangleId] = vertexXA < 0 || vertexXB < 0 || vertexXC < 0
						|| vertexXA > DrawingArea.viewportRX || vertexXB > DrawingArea.viewportRX
						|| vertexXC > DrawingArea.viewportRX;
				int k5 = (anIntArray1667[face_a_pos] + anIntArray1667[face_b_pos] + anIntArray1667[face_c_pos]) / 3
						+ anInt1653;
				faceLists[k5][depthListIndices[k5]++] = triangleId;
			}
		}

		if (face_render_priorities == null) {
			for (int i1 = diagonal3D - 1; i1 >= 0; i1--) {
				int l1 = depthListIndices[i1];
				if (l1 > 0) {
					int ai[] = faceLists[i1];
					for (int j3 = 0; j3 < l1; j3++)
						rasterise(ai[j3]);

				}
			}

			return;
		}
		for (int j1 = 0; j1 < 12; j1++) {
			anIntArray1673[j1] = 0;
			anIntArray1677[j1] = 0;
		}

		for (int i2 = diagonal3D - 1; i2 >= 0; i2--) {
			int k2 = depthListIndices[i2];
			if (k2 > 0) {
				int ai1[] = faceLists[i2];
				for (int i4 = 0; i4 < k2; i4++) {
					int l4 = ai1[i4];
					int l5 = face_render_priorities[l4];
					int j6 = anIntArray1673[l5]++;
					anIntArrayArray1674[l5][j6] = l4;
					if (l5 < 10)
						anIntArray1677[l5] += i2;
					else if (l5 == 10)
						anIntArray1675[j6] = i2;
					else
						anIntArray1676[j6] = i2;
				}

			}
		}

		int l2 = 0;
		if (anIntArray1673[1] > 0 || anIntArray1673[2] > 0)
			l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
		int k3 = 0;
		if (anIntArray1673[3] > 0 || anIntArray1673[4] > 0)
			k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
		int j4 = 0;
		if (anIntArray1673[6] > 0 || anIntArray1673[8] > 0)
			j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
		int i6 = 0;
		int k6 = anIntArray1673[10];
		int ai2[] = anIntArrayArray1674[10];
		int ai3[] = anIntArray1675;
		if (i6 == k6) {
			i6 = 0;
			k6 = anIntArray1673[11];
			ai2 = anIntArrayArray1674[11];
			ai3 = anIntArray1676;
		}
		int i5;
		if (i6 < k6)
			i5 = ai3[i6];
		else
			i5 = -1000;
		for (int l6 = 0; l6 < 10; l6++) {
			while (l6 == 0 && i5 > l2) {
				rasterise(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			while (l6 == 3 && i5 > k3) {
				rasterise(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			while (l6 == 5 && i5 > j4) {
				rasterise(ai2[i6++]);
				if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
					i6 = 0;
					k6 = anIntArray1673[11];
					ai2 = anIntArrayArray1674[11];
					ai3 = anIntArray1676;
				}
				if (i6 < k6)
					i5 = ai3[i6];
				else
					i5 = -1000;
			}
			int i7 = anIntArray1673[l6];
			int ai4[] = anIntArrayArray1674[l6];
			for (int j7 = 0; j7 < i7; j7++)
				rasterise(ai4[j7]);

		}

		while (i5 != -1000) {
			rasterise(ai2[i6++]);
			if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
				i6 = 0;
				ai2 = anIntArrayArray1674[11];
				k6 = anIntArray1673[11];
				ai3 = anIntArray1676;
			}
			if (i6 < k6)
				i5 = ai3[i6];
			else
				i5 = -500;
		}

		for (int vertex = 0; vertex < numberOfVerticeCoordinates; ++vertex) {
			int pid = verticesParticle[vertex] - 1;
			if (pid >= 0) {
				ParticleDefinition def = ParticleDefinition.cache[pid];
				int x = verticesXCoordinate[vertex];
				int y = verticesYCoordinate[vertex];
				int z = verticesXCoordinate[vertex];
				int depth = projected_vertex_z[vertex];
				if (lastRenderedRotation != 0) {
					int sine = SINE[lastRenderedRotation];
					int cosine = COSINE[lastRenderedRotation];
					int rotatedX = z * sine + x * cosine >> 16;
					z = z * cosine - x * sine >> 16;
					x = rotatedX;
				}
				x += renderAtPointX;
				z += renderAtPointY;

				ParticleVector pos = new ParticleVector(x, -y, z);
				for (int p = 0; p < def.getSpawnRate(); p++) {
					Particle particle = new Particle(def, pos, depth, pid);
					Client.instance.addParticle(particle);
				}
			}
		}
	}
}

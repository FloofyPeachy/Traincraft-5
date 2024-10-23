//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2021 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: U18BB
// Model Creator: Bida
// Created on: 14.07.2021 - 22:37:09
// Last changed on: 14.07.2021 - 22:37:09

package com.jcirmodelsquad.tcjcir.models.trains; //Path where the model is located

import com.jcirmodelsquad.tcjcir.models.trucks.ModelBlombergBnew;
import com.jcirmodelsquad.tcjcir.models.trucks.ModelFB2_new;
import com.jcirmodelsquad.tcjcir.models.trucks.ModelTypeBnew;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tmt.ModelConverter;
import tmt.ModelRendererTurbo;
import tmt.Tessellator;
import train.client.renderhelper.ModelRenderHelper;
import train.common.api.AbstractTrains;
import train.common.enums.BoxName;
import train.common.library.Info;

public class ModelU18BB extends ModelConverter //Same as Filename
{
	int textureX = 512;
	int textureY = 256;

	public ModelU18BB() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[243];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 257, 37, textureX, textureY); // Box 0
		bodyModel[1] = new ModelRendererTurbo(this, 99, 114, textureX, textureY); // Box 3
		bodyModel[2] = new ModelRendererTurbo(this, 284, 2, textureX, textureY); // Box 6
		bodyModel[3] = new ModelRendererTurbo(this, 101, 156, textureX, textureY); // Box 8
		bodyModel[4] = new ModelRendererTurbo(this, 233, 65, textureX, textureY); // Box 18
		bodyModel[5] = new ModelRendererTurbo(this, 350, 113, textureX, textureY); // Box 19
		bodyModel[6] = new ModelRendererTurbo(this, 246, 68, textureX, textureY); // Box 21
		bodyModel[7] = new ModelRendererTurbo(this, 1, 123, textureX, textureY); // Box 25
		bodyModel[8] = new ModelRendererTurbo(this, 114, 81, textureX, textureY); // Box 31
		bodyModel[9] = new ModelRendererTurbo(this, 97, 81, textureX, textureY); // Box 33
		bodyModel[10] = new ModelRendererTurbo(this, 218, 102, textureX, textureY); // Box 35
		bodyModel[11] = new ModelRendererTurbo(this, 184, 102, textureX, textureY); // Box 36
		bodyModel[12] = new ModelRendererTurbo(this, 206, 99, textureX, textureY); // Box 37
		bodyModel[13] = new ModelRendererTurbo(this, 240, 99, textureX, textureY); // Box 38
		bodyModel[14] = new ModelRendererTurbo(this, 1, 9, textureX, textureY); // Box 61
		bodyModel[15] = new ModelRendererTurbo(this, 156, 133, textureX, textureY); // Box 63
		bodyModel[16] = new ModelRendererTurbo(this, 464, 115, textureX, textureY); // Box 148
		bodyModel[17] = new ModelRendererTurbo(this, 471, 110, textureX, textureY); // Box 149
		bodyModel[18] = new ModelRendererTurbo(this, 447, 110, textureX, textureY); // Box 150
		bodyModel[19] = new ModelRendererTurbo(this, 271, 82, textureX, textureY); // Box 156
		bodyModel[20] = new ModelRendererTurbo(this, 146, 132, textureX, textureY); // Box 195
		bodyModel[21] = new ModelRendererTurbo(this, 82, 132, textureX, textureY); // Box 196
		bodyModel[22] = new ModelRendererTurbo(this, 420, 102, textureX, textureY); // Box 197
		bodyModel[23] = new ModelRendererTurbo(this, 152, 132, textureX, textureY); // Box 198
		bodyModel[24] = new ModelRendererTurbo(this, 422, 94, textureX, textureY); // Box 199
		bodyModel[25] = new ModelRendererTurbo(this, 421, 96, textureX, textureY); // Box 200
		bodyModel[26] = new ModelRendererTurbo(this, 421, 99, textureX, textureY); // Box 201
		bodyModel[27] = new ModelRendererTurbo(this, 142, 91, textureX, textureY); // Box 202
		bodyModel[28] = new ModelRendererTurbo(this, 143, 86, textureX, textureY); // Box 203
		bodyModel[29] = new ModelRendererTurbo(this, 142, 88, textureX, textureY); // Box 204
		bodyModel[30] = new ModelRendererTurbo(this, 141, 94, textureX, textureY); // Box 205
		bodyModel[31] = new ModelRendererTurbo(this, 100, 105, textureX, textureY); // box65
		bodyModel[32] = new ModelRendererTurbo(this, 438, 165, textureX, textureY); // Box 215
		bodyModel[33] = new ModelRendererTurbo(this, 433, 128, textureX, textureY); // Box 218
		bodyModel[34] = new ModelRendererTurbo(this, 500, 128, textureX, textureY); // Box 220
		bodyModel[35] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 230
		bodyModel[36] = new ModelRendererTurbo(this, 449, 141, textureX, textureY); // Box 231
		bodyModel[37] = new ModelRendererTurbo(this, 96, 134, textureX, textureY); // Box 61
		bodyModel[38] = new ModelRendererTurbo(this, 140, 134, textureX, textureY); // Box 63
		bodyModel[39] = new ModelRendererTurbo(this, 103, 109, textureX, textureY); // Box 80
		bodyModel[40] = new ModelRendererTurbo(this, 263, 62, textureX, textureY); // Box 251
		bodyModel[41] = new ModelRendererTurbo(this, 201, 65, textureX, textureY); // Box 252
		bodyModel[42] = new ModelRendererTurbo(this, 447, 128, textureX, textureY); // Box 256
		bodyModel[43] = new ModelRendererTurbo(this, 487, 139, textureX, textureY); // Box 259
		bodyModel[44] = new ModelRendererTurbo(this, 451, 123, textureX, textureY); // Box 267
		bodyModel[45] = new ModelRendererTurbo(this, 445, 139, textureX, textureY); // Box 269
		bodyModel[46] = new ModelRendererTurbo(this, 116, 45, textureX, textureY); // Box 276
		bodyModel[47] = new ModelRendererTurbo(this, 119, 42, textureX, textureY); // Box 277
		bodyModel[48] = new ModelRendererTurbo(this, 213, 65, textureX, textureY); // Box 281
		bodyModel[49] = new ModelRendererTurbo(this, 149, 65, textureX, textureY); // Box 284
		bodyModel[50] = new ModelRendererTurbo(this, 159, 79, textureX, textureY); // Box 285
		bodyModel[51] = new ModelRendererTurbo(this, 186, 81, textureX, textureY); // Box 288
		bodyModel[52] = new ModelRendererTurbo(this, 159, 90, textureX, textureY); // Box 300
		bodyModel[53] = new ModelRendererTurbo(this, 186, 90, textureX, textureY); // Box 303
		bodyModel[54] = new ModelRendererTurbo(this, 442, 110, textureX, textureY); // Box 296
		bodyModel[55] = new ModelRendererTurbo(this, 371, 125, textureX, textureY); // Box 305
		bodyModel[56] = new ModelRendererTurbo(this, 365, 125, textureX, textureY); // Box 305
		bodyModel[57] = new ModelRendererTurbo(this, 336, 85, textureX, textureY); // Box 19
		bodyModel[58] = new ModelRendererTurbo(this, 315, 89, textureX, textureY); // Box 19
		bodyModel[59] = new ModelRendererTurbo(this, 357, 98, textureX, textureY); // Box 305
		bodyModel[60] = new ModelRendererTurbo(this, 351, 97, textureX, textureY); // Box 305
		bodyModel[61] = new ModelRendererTurbo(this, 336, 81, textureX, textureY); // Box 440
		bodyModel[62] = new ModelRendererTurbo(this, 346, 81, textureX, textureY); // Box 441
		bodyModel[63] = new ModelRendererTurbo(this, 467, 23, textureX, textureY); // Box 293
		bodyModel[64] = new ModelRendererTurbo(this, 266, 122, textureX, textureY); // Box 19
		bodyModel[65] = new ModelRendererTurbo(this, 266, 105, textureX, textureY); // Box 19
		bodyModel[66] = new ModelRendererTurbo(this, 481, 109, textureX, textureY, "lamp"); // Box 240 numberboard
		bodyModel[67] = new ModelRendererTurbo(this, 481, 109, textureX, textureY, "lamp"); // Box 241 numberboard
		bodyModel[68] = new ModelRendererTurbo(this, 141, 9, textureX, textureY); // Box 114
		bodyModel[69] = new ModelRendererTurbo(this, 141, 6, textureX, textureY); // Box 74
		bodyModel[70] = new ModelRendererTurbo(this, 141, 12, textureX, textureY); // Box 78
		bodyModel[71] = new ModelRendererTurbo(this, 139, 1, textureX, textureY); // Box 78
		bodyModel[72] = new ModelRendererTurbo(this, 72, 133, textureX, textureY); // Box 2603
		bodyModel[73] = new ModelRendererTurbo(this, 84, 132, textureX, textureY); // Box 261
		bodyModel[74] = new ModelRendererTurbo(this, 492, 127, textureX, textureY); // Box 263
		bodyModel[75] = new ModelRendererTurbo(this, 493, 114, textureX, textureY); // Box 264
		bodyModel[76] = new ModelRendererTurbo(this, 108, 145, textureX, textureY); // Box 193
		bodyModel[77] = new ModelRendererTurbo(this, 201, 60, textureX, textureY, BoxName.ditch); // Box 190 ditchlight f1
		bodyModel[78] = new ModelRendererTurbo(this, 469, 164, textureX, textureY); // Box 273
		bodyModel[79] = new ModelRendererTurbo(this, 213, 60, textureX, textureY, BoxName.ditch); // Box 276 ditchlight r1
		bodyModel[80] = new ModelRendererTurbo(this, 447, 112, textureX, textureY, "lamp"); // Box 284 Headlight Rear
		bodyModel[81] = new ModelRendererTurbo(this, 447, 112, textureX, textureY, "lamp"); // Box 285 Headlight Rear
		bodyModel[82] = new ModelRendererTurbo(this, 17, 3, textureX, textureY); // Box 2
		bodyModel[83] = new ModelRendererTurbo(this, 17, 11, textureX, textureY); // Box 267
		bodyModel[84] = new ModelRendererTurbo(this, 435, 127, textureX, textureY); // Box 270434
		bodyModel[85] = new ModelRendererTurbo(this, 433, 114, textureX, textureY); // Box 271
		bodyModel[86] = new ModelRendererTurbo(this, 444, 125, textureX, textureY); // Box 280
		bodyModel[87] = new ModelRendererTurbo(this, 488, 130, textureX, textureY); // Box 281
		bodyModel[88] = new ModelRendererTurbo(this, 488, 115, textureX, textureY); // Box 282
		bodyModel[89] = new ModelRendererTurbo(this, 203, 16, textureX, textureY); // Box 292
		bodyModel[90] = new ModelRendererTurbo(this, 215, 2, textureX, textureY); // Box 293
		bodyModel[91] = new ModelRendererTurbo(this, 213, 8, textureX, textureY); // Box 294
		bodyModel[92] = new ModelRendererTurbo(this, 179, 99, textureX, textureY); // Box 296
		bodyModel[93] = new ModelRendererTurbo(this, 179, 79, textureX, textureY); // Box 297
		bodyModel[94] = new ModelRendererTurbo(this, 1, 65, textureX, textureY, "lamp"); // Box 307 glowey marker
		bodyModel[95] = new ModelRendererTurbo(this, 1, 65, textureX, textureY, "lamp"); // Box 308 glowey marker
		bodyModel[96] = new ModelRendererTurbo(this, 314, 117, textureX, textureY); // Box 310
		bodyModel[97] = new ModelRendererTurbo(this, 243, 117, textureX, textureY); // Box 311
		bodyModel[98] = new ModelRendererTurbo(this, 207, 114, textureX, textureY); // Box 318
		bodyModel[99] = new ModelRendererTurbo(this, 207, 129, textureX, textureY); // Box 319
		bodyModel[100] = new ModelRendererTurbo(this, 445, 135, textureX, textureY); // Box 320
		bodyModel[101] = new ModelRendererTurbo(this, 97, 121, textureX, textureY); // Box 321
		bodyModel[102] = new ModelRendererTurbo(this, 433, 67, textureX, textureY); // Box 298
		bodyModel[103] = new ModelRendererTurbo(this, 500, 152, textureX, textureY, "lamp"); // Box 339 glowey marker
		bodyModel[104] = new ModelRendererTurbo(this, 500, 152, textureX, textureY, "lamp"); // Box 340 glowey marker
		bodyModel[105] = new ModelRendererTurbo(this, 3, 83, textureX, textureY); // Box 402
		bodyModel[106] = new ModelRendererTurbo(this, 483, 32, textureX, textureY); // Box 342
		bodyModel[107] = new ModelRendererTurbo(this, 462, 10, textureX, textureY); // Box 349
		bodyModel[108] = new ModelRendererTurbo(this, 467, 32, textureX, textureY); // Box 350
		bodyModel[109] = new ModelRendererTurbo(this, 207, 123, textureX, textureY); // Box 253
		bodyModel[110] = new ModelRendererTurbo(this, 207, 126, textureX, textureY); // Box 280
		bodyModel[111] = new ModelRendererTurbo(this, 422, 23, textureX, textureY); // Box 283
		bodyModel[112] = new ModelRendererTurbo(this, 417, 1, textureX, textureY); // Box 284
		bodyModel[113] = new ModelRendererTurbo(this, 98, 95, textureX, textureY); // Box 292
		bodyModel[114] = new ModelRendererTurbo(this, 99, 112, textureX, textureY); // Box 293
		bodyModel[115] = new ModelRendererTurbo(this, 84, 93, textureX, textureY); // Box 294
		bodyModel[116] = new ModelRendererTurbo(this, 84, 91, textureX, textureY); // Box 295
		bodyModel[117] = new ModelRendererTurbo(this, 84, 91, textureX, textureY); // Box 296
		bodyModel[118] = new ModelRendererTurbo(this, 136, 126, textureX, textureY); // Box 298
		bodyModel[119] = new ModelRendererTurbo(this, 100, 126, textureX, textureY); // Box 299
		bodyModel[120] = new ModelRendererTurbo(this, 448, 140, textureX, textureY); // Box 300
		bodyModel[121] = new ModelRendererTurbo(this, 484, 140, textureX, textureY); // Box 301
		bodyModel[122] = new ModelRendererTurbo(this, 56, 90, textureX, textureY); // Box 410
		bodyModel[123] = new ModelRendererTurbo(this, 130, 91, textureX, textureY); // Box 411
		bodyModel[124] = new ModelRendererTurbo(this, 407, 99, textureX, textureY); // Box 412
		bodyModel[125] = new ModelRendererTurbo(this, 414, 99, textureX, textureY); // Box 413
		bodyModel[126] = new ModelRendererTurbo(this, 137, 91, textureX, textureY); // Box 414
		bodyModel[127] = new ModelRendererTurbo(this, 130, 86, textureX, textureY); // Box 431
		bodyModel[128] = new ModelRendererTurbo(this, 407, 94, textureX, textureY); // Box 432
		bodyModel[129] = new ModelRendererTurbo(this, 414, 94, textureX, textureY); // Box 433
		bodyModel[130] = new ModelRendererTurbo(this, 137, 86, textureX, textureY); // Box 434
		bodyModel[131] = new ModelRendererTurbo(this, 213, 65, textureX, textureY); // Box 442
		bodyModel[132] = new ModelRendererTurbo(this, 213, 60, textureX, textureY, BoxName.ditch); // Box 443 ditchlight r1
		bodyModel[133] = new ModelRendererTurbo(this, 201, 60, textureX, textureY, BoxName.ditch); // Box 446 ditchlight f1
		bodyModel[134] = new ModelRendererTurbo(this, 201, 65, textureX, textureY); // Box 447
		bodyModel[135] = new ModelRendererTurbo(this, 141, 94, textureX, textureY); // Box 448
		bodyModel[136] = new ModelRendererTurbo(this, 142, 91, textureX, textureY); // Box 449
		bodyModel[137] = new ModelRendererTurbo(this, 142, 88, textureX, textureY); // Box 450
		bodyModel[138] = new ModelRendererTurbo(this, 143, 86, textureX, textureY); // Box 451
		bodyModel[139] = new ModelRendererTurbo(this, 420, 102, textureX, textureY); // Box 452
		bodyModel[140] = new ModelRendererTurbo(this, 421, 99, textureX, textureY); // Box 453
		bodyModel[141] = new ModelRendererTurbo(this, 421, 96, textureX, textureY); // Box 454
		bodyModel[142] = new ModelRendererTurbo(this, 422, 94, textureX, textureY); // Box 455
		bodyModel[143] = new ModelRendererTurbo(this, 180, 112, textureX, textureY); // Box 322
		bodyModel[144] = new ModelRendererTurbo(this, 180, 136, textureX, textureY); // Box 323
		bodyModel[145] = new ModelRendererTurbo(this, 111, 99, textureX, textureY, "cull"); // Box 339 cull crossover
		bodyModel[146] = new ModelRendererTurbo(this, 237, 49, textureX, textureY); // Box 418
		bodyModel[147] = new ModelRendererTurbo(this, 228, 46, textureX, textureY); // Box 419
		bodyModel[148] = new ModelRendererTurbo(this, 226, 49, textureX, textureY); // Box 420
		bodyModel[149] = new ModelRendererTurbo(this, 228, 52, textureX, textureY); // Box 421
		bodyModel[150] = new ModelRendererTurbo(this, 151, 55, textureX, textureY, BoxName.prime1); // Box 6 PRIME1-1
		bodyModel[151] = new ModelRendererTurbo(this, 151, 55, textureX, textureY, BoxName.prime3); // Box 7 PRIME1-3
		bodyModel[152] = new ModelRendererTurbo(this, 151, 55, textureX, textureY, BoxName.prime2); // Box 8 PRIME1-2
		bodyModel[153] = new ModelRendererTurbo(this, 151, 55, textureX, textureY, BoxName.prime4); // Box 9 PRIME1-4
		bodyModel[154] = new ModelRendererTurbo(this, 161, 56, textureX, textureY, "cull"); // Box 426 cull
		bodyModel[155] = new ModelRendererTurbo(this, 151, 59, textureX, textureY); // Box 428
		bodyModel[156] = new ModelRendererTurbo(this, 112, 38, textureX, textureY); // Box 413
		bodyModel[157] = new ModelRendererTurbo(this, 476, 32, textureX, textureY); // Box 362
		bodyModel[158] = new ModelRendererTurbo(this, 462, 1, textureX, textureY); // Box 364
		bodyModel[159] = new ModelRendererTurbo(this, 471, 11, textureX, textureY); // Box 365
		bodyModel[160] = new ModelRendererTurbo(this, 478, 10, textureX, textureY); // Box 366
		bodyModel[161] = new ModelRendererTurbo(this, 440, 57, textureX, textureY); // Box 369
		bodyModel[162] = new ModelRendererTurbo(this, 440, 44, textureX, textureY); // Box 370
		bodyModel[163] = new ModelRendererTurbo(this, 446, 53, textureX, textureY); // Box 371
		bodyModel[164] = new ModelRendererTurbo(this, 251, 40, textureX, textureY); // Box 373
		bodyModel[165] = new ModelRendererTurbo(this, 259, 45, textureX, textureY); // Box 374
		bodyModel[166] = new ModelRendererTurbo(this, 248, 45, textureX, textureY); // Box 375
		bodyModel[167] = new ModelRendererTurbo(this, 251, 28, textureX, textureY); // Box 376
		bodyModel[168] = new ModelRendererTurbo(this, 263, 28, textureX, textureY); // Box 377
		bodyModel[169] = new ModelRendererTurbo(this, 254, 24, textureX, textureY); // Box 378
		bodyModel[170] = new ModelRendererTurbo(this, 315, 89, textureX, textureY); // Box 548
		bodyModel[171] = new ModelRendererTurbo(this, 315, 89, textureX, textureY); // Box 549
		bodyModel[172] = new ModelRendererTurbo(this, 315, 89, textureX, textureY); // Box 550
		bodyModel[173] = new ModelRendererTurbo(this, 260, 89, textureX, textureY); // Box 551 fuel tank lorge
		bodyModel[174] = new ModelRendererTurbo(this, 279, 139, textureX, textureY); // Box 552 fuel tank lorge
		bodyModel[175] = new ModelRendererTurbo(this, 266, 118, textureX, textureY); // Box 553 fuel tank lorge
		bodyModel[176] = new ModelRendererTurbo(this, 279, 148, textureX, textureY); // Box 554 fuel tank lorge
		bodyModel[177] = new ModelRendererTurbo(this, 125, 78, textureX, textureY); // Box 555
		bodyModel[178] = new ModelRendererTurbo(this, 134, 78, textureX, textureY); // Box 556
		bodyModel[179] = new ModelRendererTurbo(this, 215, 99, textureX, textureY); // Box 557
		bodyModel[180] = new ModelRendererTurbo(this, 249, 99, textureX, textureY); // Box 558
		bodyModel[181] = new ModelRendererTurbo(this, 463, 111, textureX, textureY); // Box 277
		bodyModel[182] = new ModelRendererTurbo(this, 447, 110, textureX, textureY); // Box 278
		bodyModel[183] = new ModelRendererTurbo(this, 447, 110, textureX, textureY); // Box 279
		bodyModel[184] = new ModelRendererTurbo(this, 462, 104, textureX, textureY); // Box 288
		bodyModel[185] = new ModelRendererTurbo(this, 1, 91, textureX, textureY, "lamp"); // Box 289 gyralight rear l
		bodyModel[186] = new ModelRendererTurbo(this, 8, 91, textureX, textureY, "lamp"); // Box 290gyralight rear r
		bodyModel[187] = new ModelRendererTurbo(this, 463, 94, textureX, textureY); // Box 291
		bodyModel[188] = new ModelRendererTurbo(this, 414, 94, textureX, textureY); // Box 292
		bodyModel[189] = new ModelRendererTurbo(this, 407, 94, textureX, textureY); // Box 293
		bodyModel[190] = new ModelRendererTurbo(this, 130, 86, textureX, textureY); // Box 294
		bodyModel[191] = new ModelRendererTurbo(this, 137, 86, textureX, textureY); // Box 295
		bodyModel[192] = new ModelRendererTurbo(this, 275, 118, textureX, textureY); // Box 296 fuel tank smol support
		bodyModel[193] = new ModelRendererTurbo(this, 275, 118, textureX, textureY); // Box 296 fuel tank smol support
		bodyModel[194] = new ModelRendererTurbo(this, 307, 118, textureX, textureY); // Box 296 fuel tank smol support
		bodyModel[195] = new ModelRendererTurbo(this, 307, 118, textureX, textureY); // Box 296 fuel tank smol support
		bodyModel[196] = new ModelRendererTurbo(this, 121, 113, textureX, textureY); // Box 300
		bodyModel[197] = new ModelRendererTurbo(this, 115, 113, textureX, textureY); // Box 301
		bodyModel[198] = new ModelRendererTurbo(this, 119, 117, textureX, textureY); // Box 302
		bodyModel[199] = new ModelRendererTurbo(this, 107, 117, textureX, textureY); // Box 303
		bodyModel[200] = new ModelRendererTurbo(this, 69, 90, textureX, textureY); // Box 310
		bodyModel[201] = new ModelRendererTurbo(this, 122, 114, textureX, textureY); // Box 312
		bodyModel[202] = new ModelRendererTurbo(this, 110, 114, textureX, textureY); // Box 313
		bodyModel[203] = new ModelRendererTurbo(this, 425, 91, textureX, textureY); // Box 314
		bodyModel[204] = new ModelRendererTurbo(this, 421, 91, textureX, textureY); // Box 315
		bodyModel[205] = new ModelRendererTurbo(this, 455, 111, textureX, textureY); // Box 324
		bodyModel[206] = new ModelRendererTurbo(this, 457, 107, textureX, textureY); // Box 325
		bodyModel[207] = new ModelRendererTurbo(this, 475, 107, textureX, textureY); // Box 326
		bodyModel[208] = new ModelRendererTurbo(this, 467, 111, textureX, textureY); // Box 327
		bodyModel[209] = new ModelRendererTurbo(this, 459, 98, textureX, textureY, "cull"); // Box 328 cull crossover
		bodyModel[210] = new ModelRendererTurbo(this, 55, 188, textureX, textureY); // Box 275
		bodyModel[211] = new ModelRendererTurbo(this, 26, 197, textureX, textureY); // Box 412
		bodyModel[212] = new ModelRendererTurbo(this, 28, 189, textureX, textureY); // Box 86
		bodyModel[213] = new ModelRendererTurbo(this, 63, 98, textureX, textureY); // Box 106
		bodyModel[214] = new ModelRendererTurbo(this, 1, 47, textureX, textureY, "lamp"); // Box 186 Headlight Front up
		bodyModel[215] = new ModelRendererTurbo(this, 1, 52, textureX, textureY, "lamp"); // Box 187 Headlight Front down
		bodyModel[216] = new ModelRendererTurbo(this, 4, 71, textureX, textureY, "lamp"); // Box 247 gyralight Front R
		bodyModel[217] = new ModelRendererTurbo(this, 12, 81, textureX, textureY, "lamp"); // Box 248 gyralight Front L
		bodyModel[218] = new ModelRendererTurbo(this, 56, 99, textureX, textureY); // Box 325
		bodyModel[219] = new ModelRendererTurbo(this, 72, 90, textureX, textureY); // Box 326
		bodyModel[220] = new ModelRendererTurbo(this, 186, 215, textureX, textureY); // Box 321
		bodyModel[221] = new ModelRendererTurbo(this, 193, 211, textureX, textureY); // Box 322
		bodyModel[222] = new ModelRendererTurbo(this, 171, 211, textureX, textureY); // Box 323
		bodyModel[223] = new ModelRendererTurbo(this, 192, 176, textureX, textureY); // Box 324
		bodyModel[224] = new ModelRendererTurbo(this, 171, 205, textureX, textureY); // Box 325
		bodyModel[225] = new ModelRendererTurbo(this, 169, 197, textureX, textureY); // Box 326
		bodyModel[226] = new ModelRendererTurbo(this, 171, 191, textureX, textureY); // Box 327
		bodyModel[227] = new ModelRendererTurbo(this, 205, 212, textureX, textureY); // Box 328
		bodyModel[228] = new ModelRendererTurbo(this, 217, 213, textureX, textureY); // Box 329
		bodyModel[229] = new ModelRendererTurbo(this, 208, 211, textureX, textureY); // Box 330
		bodyModel[230] = new ModelRendererTurbo(this, 231, 213, textureX, textureY); // Box 331
		bodyModel[231] = new ModelRendererTurbo(this, 226, 215, textureX, textureY); // Box 332
		bodyModel[232] = new ModelRendererTurbo(this, 462, 104, textureX, textureY); // Box 333
		bodyModel[233] = new ModelRendererTurbo(this, 481, 109, textureX, textureY, "lamp"); // Box 334 numberboard
		bodyModel[234] = new ModelRendererTurbo(this, 481, 109, textureX, textureY, "lamp"); // Box 335 numberboard
		bodyModel[235] = new ModelRendererTurbo(this, 262, 193, textureX, textureY); // Box 336
		bodyModel[236] = new ModelRendererTurbo(this, 233, 193, textureX, textureY); // Box 337
		bodyModel[237] = new ModelRendererTurbo(this, 291, 196, textureX, textureY); // Box 338
		bodyModel[238] = new ModelRendererTurbo(this, 293, 204, textureX, textureY); // Box 339
		bodyModel[239] = new ModelRendererTurbo(this, 293, 190, textureX, textureY); // Box 340
		bodyModel[240] = new ModelRendererTurbo(this, 234, 177, textureX, textureY); // Box 341
		bodyModel[241] = new ModelRendererTurbo(this, 153, 111, textureX, textureY); // Box 342
		bodyModel[242] = new ModelRendererTurbo(this, 153, 102, textureX, textureY); // Box 343

		bodyModel[0].addBox(0F, 0F, 0F, 66, 2, 22, 0F); // Box 0
		bodyModel[0].setRotationPoint(-33F, -1F, -11F);

		bodyModel[1].addBox(0F, 0F, 0F, 0, 8, 20, 0F); // Box 3
		bodyModel[1].setRotationPoint(-37.01F, -1F, -10F);

		bodyModel[2].addBox(0F, 0F, 0F, 47, 20, 14, 0F); // Box 6
		bodyModel[2].setRotationPoint(-14F, -21F, -7F);

		bodyModel[3].addBox(0F, 0F, 0F, 4, 2, 14, 0F); // Box 8
		bodyModel[3].setRotationPoint(-37F, -1F, -7F);

		bodyModel[4].addBox(0F, 0F, 0F, 2, 1, 5, 0F); // Box 18
		bodyModel[4].setRotationPoint(7.5F, -23.5F, -2.5F);

		bodyModel[5].addBox(0F, 0F, 0F, 1, 3, 18, 0F); // Box 19
		bodyModel[5].setRotationPoint(7F, 4F, -9F);

		bodyModel[6].addBox(0F, 0F, 0F, 3, 1, 6, 0F); // Box 21
		bodyModel[6].setRotationPoint(7F, -22.5F, -3F);

		bodyModel[7].addBox(0F, 0F, 0F, 13, 4, 22, 0F); // Box 25
		bodyModel[7].setRotationPoint(-27F, -5F, -11F);

		bodyModel[8].addBox(0F, 0F, 0F, 3, 4, 4, 0F); // Box 31
		bodyModel[8].setRotationPoint(-30F, -5F, -11F);

		bodyModel[9].addBox(0F, 0F, 0F, 4, 4, 4, 0F); // Box 33
		bodyModel[9].setRotationPoint(-31F, -5F, 7F);

		bodyModel[10].addBox(0F, 0F, 0F, 8, 4, 4, 0F); // Box 35
		bodyModel[10].setRotationPoint(-14F, -5F, 7F);

		bodyModel[11].addBox(0F, 0F, 0F, 8, 4, 4, 0F); // Box 36
		bodyModel[11].setRotationPoint(-14F, -5F, -11F);

		bodyModel[12].addBox(0F, 0F, 0F, 1, 2, 3, 0F); // Box 37
		bodyModel[12].setRotationPoint(-6F, -3F, -10F);

		bodyModel[13].addBox(0F, 0F, 0F, 1, 2, 3, 0F); // Box 38
		bodyModel[13].setRotationPoint(-6F, -3F, 7F);

		bodyModel[14].addBox(0F, 0F, 0F, 4, 2, 3, 0F); // Box 61
		bodyModel[14].setRotationPoint(-41F, 3F, -1.5F);

		bodyModel[15].addShapeBox(0F, 0F, 0F, 1, 7, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F); // Box 63
		bodyModel[15].setRotationPoint(-33F, 1F, -11F);

		bodyModel[16].addBox(0F, 0F, 0F, 1, 21, 2, 0F); // Box 148
		bodyModel[16].setRotationPoint(33F, -22F, -1F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 1, 21, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 149
		bodyModel[17].setRotationPoint(33F, -22F, 1F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 1, 21, 7, 0F,0F, 0F, -0.5F, -1F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -1F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 150
		bodyModel[18].setRotationPoint(33F, -22F, -8F);

		bodyModel[19].addBox(0F, 0F, 0F, 27, 1, 3, 0F); // Box 156
		bodyModel[19].setRotationPoint(-33F, 1F, -11F);

		bodyModel[20].addBox(0F, 0F, 0F, 1, 9, 3, 0F); // Box 195
		bodyModel[20].setRotationPoint(-34F, -1F, -10F);

		bodyModel[21].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 196
		bodyModel[21].setRotationPoint(-34F, 7F, 10F);

		bodyModel[22].addBox(0F, 0F, 0F, 3, 0, 3, 0F); // Box 197
		bodyModel[22].setRotationPoint(-37F, 8F, 8F);

		bodyModel[23].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 198
		bodyModel[23].setRotationPoint(-34F, 7F, -11F);

		bodyModel[24].addBox(0F, 0F, 0F, 3, 0, 1, 0F); // Box 199
		bodyModel[24].setRotationPoint(-37F, -1F, 7F);

		bodyModel[25].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 200
		bodyModel[25].setRotationPoint(-37F, 2F, 7F);

		bodyModel[26].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 201
		bodyModel[26].setRotationPoint(-37F, 5F, 8F);

		bodyModel[27].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 202
		bodyModel[27].setRotationPoint(-37F, 5F, -10F);

		bodyModel[28].addBox(0F, 0F, 0F, 3, 0, 1, 0F); // Box 203
		bodyModel[28].setRotationPoint(-37F, -1F, -8F);

		bodyModel[29].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 204
		bodyModel[29].setRotationPoint(-37F, 2F, -9F);

		bodyModel[30].addBox(0F, 0F, 0F, 3, 0, 3, 0F); // Box 205
		bodyModel[30].setRotationPoint(-37F, 8F, -11F);

		bodyModel[31].addShapeBox(0F, 0F, 0F, 0, 1, 18, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // box65
		bodyModel[31].setRotationPoint(-37F, 8F, -9F);

		bodyModel[32].addBox(0F, 0F, 0F, 4, 2, 14, 0F); // Box 215
		bodyModel[32].setRotationPoint(33F, -1F, -7F);

		bodyModel[33].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 218
		bodyModel[33].setRotationPoint(33F, 7F, 10F);

		bodyModel[34].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 220
		bodyModel[34].setRotationPoint(33F, 7F, -11F);

		bodyModel[35].addBox(0F, 0F, 0F, 4, 2, 3, 0F); // Box 230
		bodyModel[35].setRotationPoint(37F, 3F, -1.5F);

		bodyModel[36].addShapeBox(0F, 0F, 0F, 0, 1, 18, 0F,0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 231
		bodyModel[36].setRotationPoint(37F, 8F, -9F);

		bodyModel[37].addBox(0F, 0F, 0F, 0, 7, 1, 0F); // Box 61
		bodyModel[37].setRotationPoint(-37F, -3F, 10F);

		bodyModel[38].addBox(0F, 0F, 0F, 0, 7, 1, 0F); // Box 63
		bodyModel[38].setRotationPoint(-37F, -3F, -11F);

		bodyModel[39].addBox(0F, 0F, 0F, 0, 8, 16, 0F); // Box 80
		bodyModel[39].setRotationPoint(-37F, -9F, -8F);

		bodyModel[40].addBox(0F, 0F, 0F, 66, 2, 16, 0F); // Box 251
		bodyModel[40].setRotationPoint(-33F, 1F, -8F);

		bodyModel[41].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 252
		bodyModel[41].setRotationPoint(-37F, -4F, -6.75F);

		bodyModel[42].addBox(0F, 0F, 0F, 0, 8, 20, 0F); // Box 256
		bodyModel[42].setRotationPoint(37.01F, -1F, -10F);

		bodyModel[43].addBox(0F, 0F, 0F, 0, 7, 1, 0F); // Box 259
		bodyModel[43].setRotationPoint(37.01F, -3F, 10F);

		bodyModel[44].addBox(0F, 0F, 0F, 0, 8, 16, 0F); // Box 267
		bodyModel[44].setRotationPoint(37.01F, -9F, -8F);

		bodyModel[45].addBox(0F, 0F, 0F, 0, 7, 1, 0F); // Box 269
		bodyModel[45].setRotationPoint(37.01F, -3F, -11F);

		bodyModel[46].addBox(0F, 0F, 0F, 4, 4, 0, 0F); // Box 276
		bodyModel[46].setRotationPoint(-32F, -12.5F, 7.5F);

		bodyModel[47].addBox(0F, 0F, 0F, 1, 1, 1, 0F); // Box 277
		bodyModel[47].setRotationPoint(-30.5F, -11F, 6.5F);

		bodyModel[48].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 281
		bodyModel[48].setRotationPoint(36F, -4F, -6.75F);

		bodyModel[49].addBox(0F, 0F, 0F, 3, 2, 0, 0F); // Box 284
		bodyModel[49].setRotationPoint(-19F, -24F, 3F);

		bodyModel[50].addBox(0F, 0F, 0F, 8, 10, 0, 0F); // Box 285
		bodyModel[50].setRotationPoint(-14F, -13F, 11.01F);

		bodyModel[51].addBox(0F, 0F, 0F, 36, 8, 0, 0F); // Box 288
		bodyModel[51].setRotationPoint(-2F, -9F, 11F);

		bodyModel[52].addBox(0F, 0F, 0F, 8, 10, 0, 0F); // Box 300
		bodyModel[52].setRotationPoint(-14F, -13F, -11.01F);

		bodyModel[53].addBox(0F, 0F, 0F, 36, 8, 0, 0F); // Box 303
		bodyModel[53].setRotationPoint(-2F, -9F, -11F);

		bodyModel[54].addBox(0F, 0F, 0F, 0, 13, 2, 0F); // Box 296
		bodyModel[54].setRotationPoint(34F, -9F, -11F);

		bodyModel[55].addBox(0F, 0F, 0F, 1, 5, 0, 0F); // Box 305
		bodyModel[55].setRotationPoint(7F, 1F, -9.01F);

		bodyModel[56].addBox(0F, 0F, 0F, 1, 5, 0, 0F); // Box 305
		bodyModel[56].setRotationPoint(7F, 1F, 9.01F);

		bodyModel[57].addBox(0F, 0F, 0F, 1, 3, 18, 0F); // Box 19
		bodyModel[57].setRotationPoint(-8F, 4F, -9F);

		bodyModel[58].addShapeBox(0F, 0F, 0F, 1, 1, 18, 0F,0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F); // Box 19
		bodyModel[58].setRotationPoint(-9F, 5F, -9F);

		bodyModel[59].addBox(0F, 0F, 0F, 1, 4, 0, 0F); // Box 305
		bodyModel[59].setRotationPoint(-8F, 2F, -9.01F);

		bodyModel[60].addBox(0F, 0F, 0F, 1, 5, 0, 0F); // Box 305
		bodyModel[60].setRotationPoint(-8F, 1F, 9.01F);

		bodyModel[61].addBox(0F, 0F, 0F, 2, 1, 2, 0F); // Box 440
		bodyModel[61].setRotationPoint(-21.5F, 3F, -1F);

		bodyModel[62].addBox(0F, 0F, 0F, 2, 1, 2, 0F); // Box 441
		bodyModel[62].setRotationPoint(19.5F, 3F, -1F);

		bodyModel[63].addBox(0F, 0F, 0F, 10, 6, 1, 0F); // Box 293
		bodyModel[63].setRotationPoint(22F, -14F, -7.75F);

		bodyModel[64].addBox(0F, 0F, 0F, 1, 3, 1, 0F); // Box 19
		bodyModel[64].setRotationPoint(-2F, 1.75F, -11.5F);
		bodyModel[64].rotateAngleX = 1.13446401F;

		bodyModel[65].addBox(0F, 0F, -1F, 1, 3, 1, 0F); // Box 19
		bodyModel[65].setRotationPoint(-2F, 1.75F, 11.5F);
		bodyModel[65].rotateAngleX = -1.13446401F;

		bodyModel[66].addBox(0F, 0F, -5F, 1, 2, 5, 0F); // Box 240 numberboard
		bodyModel[66].setRotationPoint(32.3F, -19F, 6F);
		bodyModel[66].rotateAngleY = 0.15707963F;

		bodyModel[67].addBox(0F, 0F, 0F, 1, 2, 5, 0F); // Box 241 numberboard
		bodyModel[67].setRotationPoint(32.3F, -19F, -6F);
		bodyModel[67].rotateAngleY = -0.15707963F;

		bodyModel[68].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F, 0.5F, 0F, 0.5F); // Box 114
		bodyModel[68].setRotationPoint(-8F, 3F, 9.5F);

		bodyModel[69].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,-0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, -0.2F, 0F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 74
		bodyModel[69].setRotationPoint(-8F, 2F, 9.5F);

		bodyModel[70].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 78
		bodyModel[70].setRotationPoint(-7.75F, 3.5F, 9.75F);

		bodyModel[71].addShapeBox(0F, 0F, 0F, 1, 1, 3, 0F,0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 78
		bodyModel[71].setRotationPoint(-7.75F, 1F, 7.75F);

		bodyModel[72].addShapeBox(0F, 0F, 0F, 1, 7, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F); // Box 2603
		bodyModel[72].setRotationPoint(-33F, 1F, 7F);

		bodyModel[73].addBox(0F, 0F, 0F, 1, 9, 3, 0F); // Box 261
		bodyModel[73].setRotationPoint(-34F, -1F, 7F);

		bodyModel[74].addShapeBox(0F, 0F, 0F, 1, 7, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 263
		bodyModel[74].setRotationPoint(32F, 1F, -11F);

		bodyModel[75].addBox(0F, 0F, 0F, 1, 9, 3, 0F); // Box 264
		bodyModel[75].setRotationPoint(33F, -1F, -10F);

		bodyModel[76].addShapeBox(0F, 0F, 0F, 5, 4, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -4F, 0F, 0F, -4F, 0F, 0F, 0F, 0F, 0F); // Box 193
		bodyModel[76].setRotationPoint(-37F, 1F, -3F);

		bodyModel[77].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 190 ditchlight f1
		bodyModel[77].setRotationPoint(-37.25F, -4F, -6.75F);

		bodyModel[78].addShapeBox(0F, 0F, 0F, 5, 4, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -4F, 0F, 0F); // Box 273
		bodyModel[78].setRotationPoint(32F, 1F, -3F);

		bodyModel[79].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 276 ditchlight r1
		bodyModel[79].setRotationPoint(36.75F, -4F, -6.75F);

		bodyModel[80].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 284 Headlight Rear
		bodyModel[80].setRotationPoint(33.25F, -17.5F, -1F);

		bodyModel[81].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 285 Headlight Rear
		bodyModel[81].setRotationPoint(33.25F, -19.5F, -1F);

		bodyModel[82].addShapeBox(0F, 0F, 0F, 1, 3, 4, 0F,0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 2
		bodyModel[82].setRotationPoint(-38F, 2.5F, -2F);

		bodyModel[83].addShapeBox(0F, 0F, 0F, 1, 3, 4, 0F,0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 267
		bodyModel[83].setRotationPoint(37F, 2.5F, -2F);

		bodyModel[84].addShapeBox(0F, 0F, 0F, 1, 7, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 270434
		bodyModel[84].setRotationPoint(32F, 1F, 7F);

		bodyModel[85].addBox(0F, 0F, 0F, 1, 9, 3, 0F); // Box 271
		bodyModel[85].setRotationPoint(33F, -1F, 7F);

		bodyModel[86].addShapeBox(0F, 0F, 0F, 0, 4, 1, 0F,0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 1.5F, 0F, 0F, 1.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 280
		bodyModel[86].setRotationPoint(34F, -5F, -11.5F);

		bodyModel[87].addShapeBox(0F, 0F, 0F, 0, 4, 1, 0F,0F, 0F, 1.5F, 0F, 0F, 1.5F, 0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 281
		bodyModel[87].setRotationPoint(34F, -5F, 10.5F);

		bodyModel[88].addBox(0F, 0F, 0F, 0, 13, 2, 0F); // Box 282
		bodyModel[88].setRotationPoint(34F, -9F, 9F);

		bodyModel[89].addShapeBox(0F, 0F, 0F, 26, 1, 4, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 292
		bodyModel[89].setRotationPoint(-14F, -22F, -7F);

		bodyModel[90].addShapeBox(0F, 0F, 0F, 26, 1, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 293
		bodyModel[90].setRotationPoint(-14F, -22F, 3F);

		bodyModel[91].addBox(0F, 0F, 0F, 26, 1, 6, 0F); // Box 294
		bodyModel[91].setRotationPoint(-14F, -22F, -3F);

		bodyModel[92].addShapeBox(0F, 0F, 0F, 4, 1, 0, 0F,0F, 0F, 0F, 0F, -4F, 0F, 0F, -4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 4F, 0F, 0F, 4F, 0F, 0F, 0F, 0F); // Box 296
		bodyModel[92].setRotationPoint(-6F, -13F, -11F);

		bodyModel[93].addShapeBox(0F, 0F, 0F, 4, 1, 0, 0F,0F, 0F, 0F, 0F, -4F, 0F, 0F, -4F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 4F, 0F, 0F, 4F, 0F, 0F, 0F, 0F); // Box 297
		bodyModel[93].setRotationPoint(-6F, -13F, 11F);

		bodyModel[94].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,-0.65F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F); // Box 307 glowey marker
		bodyModel[94].setRotationPoint(-33.9F, -21F, -7F);

		bodyModel[95].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,-0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F); // Box 308 glowey marker
		bodyModel[95].setRotationPoint(-33.9F, -21F, 5F);

		bodyModel[96].addBox(0F, 0F, 0F, 2, 1, 14, 0F); // Box 310
		bodyModel[96].setRotationPoint(6F, 3F, -7F);

		bodyModel[97].addBox(0F, 0F, 0F, 2, 1, 14, 0F); // Box 311
		bodyModel[97].setRotationPoint(-8F, 3F, -7F);

		bodyModel[98].addBox(0F, 0F, 0F, 4, 1, 22, 0F); // Box 318
		bodyModel[98].setRotationPoint(18.5F, 1F, -11F);

		bodyModel[99].addBox(0F, 0F, 0F, 4, 1, 3, 0F); // Box 319
		bodyModel[99].setRotationPoint(-22.5F, 1F, 8F);

		bodyModel[100].addBox(0F, 0F, 0F, 0, 1, 22, 0F); // Box 320
		bodyModel[100].setRotationPoint(37.01F, 7F, -11F);

		bodyModel[101].addBox(0F, 0F, 0F, 0, 1, 22, 0F); // Box 321
		bodyModel[101].setRotationPoint(-37.01F, 7F, -11F);

		bodyModel[102].addBox(0F, 0F, 0F, 21, 2, 16, 0F); // Box 298
		bodyModel[102].setRotationPoint(12F, -22F, -8F);

		bodyModel[103].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0.2F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 339 glowey marker
		bodyModel[103].setRotationPoint(33F, -21.7F, -7.5F);

		bodyModel[104].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.65F, -0.25F, -0.25F, 0.2F, -0.25F, -0.25F); // Box 340 glowey marker
		bodyModel[104].setRotationPoint(33F, -21.7F, 5.5F);

		bodyModel[105].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F); // Box 402
		bodyModel[105].setRotationPoint(-34F, -22.5F, -1F);

		bodyModel[106].addBox(0F, 0F, 0F, 2, 6, 1, 0F); // Box 342
		bodyModel[106].setRotationPoint(16.5F, -14F, -7.75F);

		bodyModel[107].addBox(0F, 0F, 0F, 3, 6, 1, 0F); // Box 349
		bodyModel[107].setRotationPoint(13F, -7.5F, 6.75F);

		bodyModel[108].addBox(0F, 0F, 0F, 3, 6, 1, 0F); // Box 350
		bodyModel[108].setRotationPoint(13F, -7.5F, -7.75F);

		bodyModel[109].addBox(0F, 0F, 0F, 5, 1, 1, 0F); // Box 253
		bodyModel[109].setRotationPoint(-27F, 1F, 9F);
		bodyModel[109].rotateAngleZ = -0.27925268F;

		bodyModel[110].addBox(0F, 0F, 0F, 5, 1, 1, 0F); // Box 280
		bodyModel[110].setRotationPoint(-32F, 1F, 9F);

		bodyModel[111].addBox(0F, 0F, 0F, 21, 19, 1, 0F); // Box 283
		bodyModel[111].setRotationPoint(12F, -20F, -7.5F);

		bodyModel[112].addBox(0F, 0F, 0F, 21, 19, 1, 0F); // Box 284
		bodyModel[112].setRotationPoint(12F, -20F, 6.5F);

		bodyModel[113].addBox(0F, 0F, 0F, 3, 8, 0, 0F); // Box 292
		bodyModel[113].setRotationPoint(-30F, -13F, -11F);

		bodyModel[114].addShapeBox(0F, 0F, 0F, 0, 4, 1, 0F,0F, 0F, -1.5F, 0F, 0F, -1.5F, 0F, 0F, 1.5F, 0F, 0F, 1.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F); // Box 293
		bodyModel[114].setRotationPoint(-34F, -5F, -11.5F);

		bodyModel[115].addBox(0F, 0F, 0F, 0, 14, 2, 0F); // Box 294
		bodyModel[115].setRotationPoint(-34F, -10F, -11F);

		bodyModel[116].addShapeBox(0F, 0F, 0F, 2, 1, 0, 0F,0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, 3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 3F, 0F); // Box 295
		bodyModel[116].setRotationPoint(-32F, -13F, -11F);

		bodyModel[117].addShapeBox(0F, 0F, 0F, 0, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.23F, 0F, 0F, 0.23F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.23F, 0F, 0F, 0.23F); // Box 296
		bodyModel[117].setRotationPoint(-32F, -10F, -11F);
		bodyModel[117].rotateAngleY = 1.11701072F;

		bodyModel[118].addShapeBox(0F, 0F, 0F, 0, 6, 1, 0F,0F, -2F, 2F, 0F, -2F, 2F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 298
		bodyModel[118].setRotationPoint(-37.01F, -9F, -10F);
		bodyModel[118].rotateAngleY = -3.14159265F;

		bodyModel[119].addShapeBox(0F, 0F, 0F, 0, 6, 1, 0F,0F, 0F, -3F, 0F, 0F, -3F, 0F, -2F, 2F, 0F, -2F, 2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 299
		bodyModel[119].setRotationPoint(-37.01F, -9F, 11F);
		bodyModel[119].rotateAngleY = -3.14159265F;

		bodyModel[120].addShapeBox(0F, 0F, 0F, 0, 6, 1, 0F,0F, -2F, 2F, 0F, -2F, 2F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 300
		bodyModel[120].setRotationPoint(37.01F, -9F, -10F);
		bodyModel[120].rotateAngleY = -3.14159265F;

		bodyModel[121].addShapeBox(0F, 0F, 0F, 0, 6, 1, 0F,0F, 0F, -3F, 0F, 0F, -3F, 0F, -2F, 2F, 0F, -2F, 2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 301
		bodyModel[121].setRotationPoint(37.01F, -9F, 11F);
		bodyModel[121].rotateAngleY = -3.14159265F;

		bodyModel[122].addBox(0F, 0F, 0F, 6, 8, 0, 0F); // Box 410
		bodyModel[122].setRotationPoint(-33F, -13F, 11F);

		bodyModel[123].addBox(0F, 0F, 0F, 3, 2, 0, 0F); // Box 411
		bodyModel[123].setRotationPoint(-37F, 6F, -8F);

		bodyModel[124].addBox(0F, 0F, 0F, 3, 2, 0, 0F); // Box 412
		bodyModel[124].setRotationPoint(-37F, 6F, 8F);

		bodyModel[125].addBox(0F, 0F, 0F, 3, 2, 0, 0F); // Box 413
		bodyModel[125].setRotationPoint(34F, 6F, 8F);

		bodyModel[126].addBox(0F, 0F, 0F, 3, 2, 0, 0F); // Box 414
		bodyModel[126].setRotationPoint(34F, 6F, -8F);

		bodyModel[127].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 431
		bodyModel[127].setRotationPoint(-37F, 1F, -7F);

		bodyModel[128].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 432
		bodyModel[128].setRotationPoint(-37F, 1F, 7F);

		bodyModel[129].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 433
		bodyModel[129].setRotationPoint(34F, 1F, 7F);

		bodyModel[130].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 434
		bodyModel[130].setRotationPoint(34F, 1F, -7F);

		bodyModel[131].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 442
		bodyModel[131].setRotationPoint(36F, -4F, 4.75F);

		bodyModel[132].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 443 ditchlight r1
		bodyModel[132].setRotationPoint(36.75F, -4F, 4.75F);

		bodyModel[133].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 446 ditchlight f1
		bodyModel[133].setRotationPoint(-37.25F, -4F, 4.75F);

		bodyModel[134].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 447
		bodyModel[134].setRotationPoint(-37F, -4F, 4.75F);

		bodyModel[135].addBox(0F, 0F, 0F, 3, 0, 3, 0F); // Box 448
		bodyModel[135].setRotationPoint(34F, 8F, -11F);

		bodyModel[136].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 449
		bodyModel[136].setRotationPoint(34F, 5F, -10F);

		bodyModel[137].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 450
		bodyModel[137].setRotationPoint(34F, 2F, -9F);

		bodyModel[138].addBox(0F, 0F, 0F, 3, 0, 1, 0F); // Box 451
		bodyModel[138].setRotationPoint(34F, -1F, -8F);

		bodyModel[139].addBox(0F, 0F, 0F, 3, 0, 3, 0F); // Box 452
		bodyModel[139].setRotationPoint(34F, 8F, 8F);

		bodyModel[140].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 453
		bodyModel[140].setRotationPoint(34F, 5F, 8F);

		bodyModel[141].addBox(0F, 0F, 0F, 3, 0, 2, 0F); // Box 454
		bodyModel[141].setRotationPoint(34F, 2F, 7F);

		bodyModel[142].addBox(0F, 0F, 0F, 3, 0, 1, 0F); // Box 455
		bodyModel[142].setRotationPoint(34F, -1F, 7F);

		bodyModel[143].addBox(0F, 0F, 0F, 2, 1, 22, 0F); // Box 322
		bodyModel[143].setRotationPoint(19.5F, 2F, -11F);

		bodyModel[144].addBox(0F, 0F, 0F, 2, 1, 22, 0F); // Box 323
		bodyModel[144].setRotationPoint(-21.5F, 2F, -11F);

		bodyModel[145].addBox(-4F, 0F, 0F, 4, 1, 4, 0F); // Box 339 cull crossover
		bodyModel[145].setRotationPoint(-37F, -1F, -2F);
		bodyModel[145].rotateAngleZ = -1.48352986F;

		bodyModel[146].addBox(0F, 0F, 0F, 1, 2, 1, 0F); // Box 418
		bodyModel[146].setRotationPoint(-0.5F, -22.5F, -6.5F);

		bodyModel[147].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 419
		bodyModel[147].setRotationPoint(-2F, -23.25F, -5.5F);

		bodyModel[148].addBox(0F, 0F, 0F, 4, 1, 1, 0F); // Box 420
		bodyModel[148].setRotationPoint(-3F, -23.5F, -6.5F);

		bodyModel[149].addBox(0F, 0F, 0F, 3, 1, 1, 0F); // Box 421
		bodyModel[149].setRotationPoint(-1.5F, -23.25F, -7.5F);

		bodyModel[150].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.25F, 0F, -0.25F, -1F, 0F, -1F, -1F, 0F, -1F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -1F, 0F, -1F, -1F, 0F, -1F, -0.25F, 0F, -0.25F); // Box 6 PRIME1-1
		bodyModel[150].setRotationPoint(-24F, -23.5F, 6.5F);

		bodyModel[151].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-1F, 0F, -1F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -1F, 0F, -1F, -1F, 0F, -1F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -1F, 0F, -1F); // Box 7 PRIME1-3
		bodyModel[151].setRotationPoint(-24F, -23.5F, 6.5F);

		bodyModel[152].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-1F, 0F, -1F, -1F, 0F, -1F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -1F, 0F, -1F, -1F, 0F, -1F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F); // Box 8 PRIME1-2
		bodyModel[152].setRotationPoint(-24F, -23.5F, 6.5F);

		bodyModel[153].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,-0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -1F, 0F, -1F, -1F, 0F, -1F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -1F, 0F, -1F, -1F, 0F, -1F); // Box 9 PRIME1-4
		bodyModel[153].setRotationPoint(-24F, -23.5F, 6.5F);

		bodyModel[154].addShapeBox(0F, 0F, 0F, 3, 3, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, -3F); // Box 426 cull
		bodyModel[154].setRotationPoint(-24.5F, -22F, 6F);

		bodyModel[155].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 428
		bodyModel[155].setRotationPoint(-24F, -23F, 6.5F);

		bodyModel[156].addShapeBox(0F, 0F, 0F, 3, 1, 0, 0F,-1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 413
		bodyModel[156].setRotationPoint(-23F, -23.5F, 0F);

		bodyModel[157].addBox(0F, 0F, 0F, 2, 6, 1, 0F); // Box 362
		bodyModel[157].setRotationPoint(19F, -14F, -7.75F);

		bodyModel[158].addBox(0F, 0F, 0F, 10, 6, 1, 0F); // Box 364
		bodyModel[158].setRotationPoint(22F, -14F, 6.75F);

		bodyModel[159].addBox(0F, 0F, 0F, 2, 6, 1, 0F); // Box 365
		bodyModel[159].setRotationPoint(19F, -14F, 6.75F);

		bodyModel[160].addBox(0F, 0F, 0F, 2, 6, 1, 0F); // Box 366
		bodyModel[160].setRotationPoint(16.5F, -14F, 6.75F);

		bodyModel[161].addShapeBox(0F, 0F, 0F, 21, 1, 7, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 369
		bodyModel[161].setRotationPoint(12F, -23F, 1F);

		bodyModel[162].addShapeBox(0F, 0F, 0F, 21, 1, 7, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 370
		bodyModel[162].setRotationPoint(12F, -23F, -8F);

		bodyModel[163].addShapeBox(0F, 0F, 0F, 20, 1, 2, 0F,0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 371
		bodyModel[163].setRotationPoint(12F, -23F, -1F);

		bodyModel[164].addBox(0F, 0F, 0F, 6, 2, 1, 0F); // Box 373
		bodyModel[164].setRotationPoint(-13.5F, -20F, -7.25F);

		bodyModel[165].addBox(0F, 0F, 0F, 2, 4, 1, 0F); // Box 374
		bodyModel[165].setRotationPoint(-13.5F, -17.5F, -7.25F);

		bodyModel[166].addBox(0F, 0F, 0F, 4, 4, 1, 0F); // Box 375
		bodyModel[166].setRotationPoint(-11F, -17.5F, -7.25F);

		bodyModel[167].addBox(0F, 0F, 0F, 4, 4, 1, 0F); // Box 376
		bodyModel[167].setRotationPoint(-11F, -17.5F, 6.25F);

		bodyModel[168].addBox(0F, 0F, 0F, 2, 4, 1, 0F); // Box 377
		bodyModel[168].setRotationPoint(-13.5F, -17.5F, 6.25F);

		bodyModel[169].addBox(0F, 0F, 0F, 6, 2, 1, 0F); // Box 378
		bodyModel[169].setRotationPoint(-13.5F, -20F, 6.25F);

		bodyModel[170].addShapeBox(0F, 0F, 0F, 1, 1, 18, 0F,0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F); // Box 548
		bodyModel[170].setRotationPoint(-7F, 5F, -9F);

		bodyModel[171].addShapeBox(0F, 0F, 0F, 1, 1, 18, 0F,0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F); // Box 549
		bodyModel[171].setRotationPoint(8F, 5F, -9F);

		bodyModel[172].addShapeBox(0F, 0F, 0F, 1, 1, 18, 0F,0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F); // Box 550
		bodyModel[172].setRotationPoint(6F, 5F, -9F);

		bodyModel[173].addBox(0F, 0F, 0F, 12, 3, 22, 0F); // Box 551 fuel tank lorge
		bodyModel[173].setRotationPoint(-6F, 2F, -11F);

		bodyModel[174].addShapeBox(0F, 0F, 0F, 12, 4, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 552 fuel tank lorge
		bodyModel[174].setRotationPoint(-6F, 5F, -11F);

		bodyModel[175].addBox(0F, 0F, 0F, 12, 4, 16, 0F); // Box 553 fuel tank lorge
		bodyModel[175].setRotationPoint(-6F, 5F, -8F);

		bodyModel[176].addShapeBox(0F, 0F, 0F, 12, 4, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, -3F); // Box 554 fuel tank lorge
		bodyModel[176].setRotationPoint(-6F, 5F, 8F);

		bodyModel[177].addBox(0F, 0F, 0F, 1, 2, 3, 0F); // Box 555
		bodyModel[177].setRotationPoint(-31F, -3F, -10F);

		bodyModel[178].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 556
		bodyModel[178].setRotationPoint(-31F, -5F, -11F);

		bodyModel[179].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 557
		bodyModel[179].setRotationPoint(-6F, -5F, -11F);

		bodyModel[180].addBox(0F, 0F, 0F, 1, 4, 1, 0F); // Box 558
		bodyModel[180].setRotationPoint(-6F, -5F, 10F);

		bodyModel[181].addShapeBox(0F, 0F, 0F, 2, 1, 2, 0F,0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 277
		bodyModel[181].setRotationPoint(32F, -23F, -1F);

		bodyModel[182].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,0F, -0.5F, -0.5F, -1F, -0.5F, -0.5F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0F, -0.5F, -1F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 278
		bodyModel[182].setRotationPoint(33F, -23F, -8F);

		bodyModel[183].addShapeBox(0F, 0F, 0F, 1, 1, 7, 0F,0F, 0.5F, 0F, 0F, 0F, 0F, -1F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 279
		bodyModel[183].setRotationPoint(33F, -23F, 1F);

		bodyModel[184].addBox(0F, 0F, 0F, 1, 2, 4, 0F); // Box 288
		bodyModel[184].setRotationPoint(33.75F, -21.75F, -2F);

		bodyModel[185].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 289 gyralight rear l
		bodyModel[185].setRotationPoint(34.5F, -21.7F, -2F);

		bodyModel[186].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 290gyralight rear r
		bodyModel[186].setRotationPoint(34.5F, -21.7F, 0F);

		bodyModel[187].addShapeBox(-1F, 0F, -1F, 2, 1, 2, 0F,-0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F); // Box 291
		bodyModel[187].setRotationPoint(33F, -23.25F, 0F);

		bodyModel[188].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 292
		bodyModel[188].setRotationPoint(34F, 4F, 8F);

		bodyModel[189].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 293
		bodyModel[189].setRotationPoint(-37F, 4F, 8F);

		bodyModel[190].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 294
		bodyModel[190].setRotationPoint(-37F, 4F, -8F);

		bodyModel[191].addBox(0F, 0F, 0F, 3, 1, 0, 0F); // Box 295
		bodyModel[191].setRotationPoint(34F, 4F, -8F);

		bodyModel[192].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F); // Box 296 fuel tank smol support
		bodyModel[192].setRotationPoint(-6F, 3F, -7F);

		bodyModel[193].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F); // Box 296 fuel tank smol support
		bodyModel[193].setRotationPoint(-6F, 3F, 6F);

		bodyModel[194].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F); // Box 296 fuel tank smol support
		bodyModel[194].setRotationPoint(4F, 3F, -7F);

		bodyModel[195].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F); // Box 296 fuel tank smol support
		bodyModel[195].setRotationPoint(4F, 3F, 6F);

		bodyModel[196].addBox(0F, 0F, 0F, 1, 9, 0, 0F); // Box 300
		bodyModel[196].setRotationPoint(-38F, -9F, -2F);

		bodyModel[197].addBox(0F, 0F, 0F, 1, 9, 0, 0F); // Box 301
		bodyModel[197].setRotationPoint(-38F, -9F, 2F);

		bodyModel[198].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,-1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 302
		bodyModel[198].setRotationPoint(-38F, -1F, -7F);

		bodyModel[199].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 303
		bodyModel[199].setRotationPoint(-38F, -1F, 2F);

		bodyModel[200].addBox(0F, 0F, 0F, 1, 17, 0, 0F); // Box 310
		bodyModel[200].setRotationPoint(-34F, -13F, 11F);

		bodyModel[201].addShapeBox(0F, 0F, 0F, 1, 0, 2, 0F,-1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 312
		bodyModel[201].setRotationPoint(-35F, -1F, -10F);

		bodyModel[202].addShapeBox(0F, 0F, 0F, 1, 0, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 313
		bodyModel[202].setRotationPoint(-35F, -1F, 8F);

		bodyModel[203].addShapeBox(0F, 0F, 0F, 1, 0, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F); // Box 314
		bodyModel[203].setRotationPoint(34F, -1F, 8F);

		bodyModel[204].addShapeBox(0F, 0F, 0F, 1, 0, 2, 0F,0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 315
		bodyModel[204].setRotationPoint(34F, -1F, -10F);

		bodyModel[205].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 324
		bodyModel[205].setRotationPoint(37F, -1F, -7F);

		bodyModel[206].addBox(0F, 0F, 0F, 1, 9, 0, 0F); // Box 325
		bodyModel[206].setRotationPoint(37F, -9F, -2F);

		bodyModel[207].addBox(0F, 0F, 0F, 1, 9, 0, 0F); // Box 326
		bodyModel[207].setRotationPoint(37F, -9F, 2F);

		bodyModel[208].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F); // Box 327
		bodyModel[208].setRotationPoint(37F, -1F, 2F);

		bodyModel[209].addBox(-4F, 0F, 0F, 4, 1, 4, 0F); // Box 328 cull crossover
		bodyModel[209].setRotationPoint(37F, -1F, 2F);
		bodyModel[209].rotateAngleY = -3.14159265F;
		bodyModel[209].rotateAngleZ = -1.48352986F;

		bodyModel[210].addBox(0F, 0F, 0F, 1, 4, 3, 0F); // Box 275
		bodyModel[210].setRotationPoint(-27F, -14F, 3F);

		bodyModel[211].addBox(0F, 0F, 0F, 4, 9, 2, 0F); // Box 412
		bodyModel[211].setRotationPoint(-27F, -12F, 2F);
		bodyModel[211].rotateAngleY = -1.57079633F;

		bodyModel[212].addShapeBox(-1F, 0F, -0.5F, 11, 4, 2, 0F,0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -2F, 0F, -5F, -2F, 0F, -5F, -2F, 0F, 0F, -2F, 0F); // Box 86
		bodyModel[212].setRotationPoint(-26.5F, -14F, 2F);
		bodyModel[212].rotateAngleY = -1.57079633F;

		bodyModel[213].addShapeBox(0F, 0F, 0F, 2, 6, 12, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F); // Box 106
		bodyModel[213].setRotationPoint(-16F, -21F, -6F);

		bodyModel[214].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 186 Headlight Front up
		bodyModel[214].setRotationPoint(-34.25F, -19.5F, -1F);

		bodyModel[215].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F); // Box 187 Headlight Front down
		bodyModel[215].setRotationPoint(-34.25F, -17.5F, -1F);

		bodyModel[216].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,-0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F); // Box 247 gyralight Front R
		bodyModel[216].setRotationPoint(-35.5F, -21.7F, -2F);

		bodyModel[217].addShapeBox(0F, 0F, 0F, 1, 2, 2, 0F,-0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, -0.5F, -0.25F, -0.25F); // Box 248 gyralight Front L
		bodyModel[217].setRotationPoint(-35.5F, -21.7F, 0F);

		bodyModel[218].addBox(0F, 0F, 0F, 6, 8, 0, 0F); // Box 325
		bodyModel[218].setRotationPoint(-33F, -13F, -11F);

		bodyModel[219].addBox(0F, 0F, 0F, 1, 17, 0, 0F); // Box 326
		bodyModel[219].setRotationPoint(-34F, -13F, -11F);

		bodyModel[220].addBox(0F, 0F, 0F, 1, 20, 2, 0F); // Box 321
		bodyModel[220].setRotationPoint(-34F, -21F, -1F);

		bodyModel[221].addShapeBox(0F, 0F, 0F, 1, 20, 6, 0F,-1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 322
		bodyModel[221].setRotationPoint(-34F, -21F, -7F);

		bodyModel[222].addShapeBox(0F, 0F, 0F, 1, 20, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 323
		bodyModel[222].setRotationPoint(-34F, -21F, 1F);

		bodyModel[223].addBox(0F, 0F, 0F, 6, 20, 14, 0F); // Box 324
		bodyModel[223].setRotationPoint(-33F, -21F, -7F);

		bodyModel[224].addShapeBox(0F, 0F, 0F, 6, 1, 4, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 325
		bodyModel[224].setRotationPoint(-33F, -22F, -7F);

		bodyModel[225].addBox(0F, 0F, 0F, 5, 1, 6, 0F); // Box 326
		bodyModel[225].setRotationPoint(-32F, -22F, -3F);

		bodyModel[226].addShapeBox(0F, 0F, 0F, 6, 1, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 327
		bodyModel[226].setRotationPoint(-33F, -22F, 3F);

		bodyModel[227].addBox(0F, 0F, 0F, 2, 1, 2, 0F); // Box 328
		bodyModel[227].setRotationPoint(-34F, -22F, -1F);

		bodyModel[228].addShapeBox(0F, 0F, 0F, 1, 1, 6, 0F,0F, 0F, 0F, 0F, 0F, -2F, 0F, -1F, 0F, -1F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, 0F, -1F, 0F, 0F); // Box 329
		bodyModel[228].setRotationPoint(-34F, -22F, 1F);

		bodyModel[229].addShapeBox(0F, 0F, 0F, 1, 1, 6, 0F,-1F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, -2F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, 0F); // Box 330
		bodyModel[229].setRotationPoint(-34F, -22F, -7F);

		bodyModel[230].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F); // Box 331
		bodyModel[230].setRotationPoint(-33F, -22F, -3F);

		bodyModel[231].addShapeBox(0F, 0F, 0F, 1, 1, 2, 0F,1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 332
		bodyModel[231].setRotationPoint(-33F, -22F, 1F);

		bodyModel[232].addBox(0F, 0F, 0F, 1, 2, 4, 0F); // Box 333
		bodyModel[232].setRotationPoint(-34.75F, -21.75F, -2F);

		bodyModel[233].addBox(-2F, 0F, -5F, 1, 2, 5, 0F); // Box 334 numberboard
		bodyModel[233].setRotationPoint(-31.3F, -19F, 6F);
		bodyModel[233].rotateAngleY = -0.15707963F;

		bodyModel[234].addBox(-2F, 0F, 0F, 1, 2, 5, 0F); // Box 335 numberboard
		bodyModel[234].setRotationPoint(-31.3F, -19F, -6F);
		bodyModel[234].rotateAngleY = 0.15707963F;

		bodyModel[235].addBox(0F, 0F, 0F, 13, 16, 1, 0F); // Box 336
		bodyModel[235].setRotationPoint(-27F, -21F, -7F);

		bodyModel[236].addBox(0F, 0F, 0F, 13, 16, 1, 0F); // Box 337
		bodyModel[236].setRotationPoint(-27F, -21F, 6F);

		bodyModel[237].addBox(0F, 0F, 0F, 13, 1, 6, 0F); // Box 338
		bodyModel[237].setRotationPoint(-27F, -22.5F, -3F);

		bodyModel[238].addShapeBox(0F, 0F, 0F, 13, 1, 4, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 339
		bodyModel[238].setRotationPoint(-27F, -22.5F, -7F);

		bodyModel[239].addShapeBox(0F, 0F, 0F, 13, 1, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 340
		bodyModel[239].setRotationPoint(-27F, -22.5F, 3F);

		bodyModel[240].addShapeBox(0F, 0F, 0F, 13, 1, 14, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 341
		bodyModel[240].setRotationPoint(-27F, -21.5F, -7F);

		bodyModel[241].addBox(0F, 0F, 0F, 13, 8, 0, 0F); // Box 342
		bodyModel[241].setRotationPoint(-27F, -13F, -11F);

		bodyModel[242].addBox(0F, 0F, 0F, 13, 8, 0, 0F); // Box 343
		bodyModel[242].setRotationPoint(-27F, -13F, 11F);
	}
	ModelFB2_new theTrucks2 = new ModelFB2_new();
	ModelBlombergBnew theTrucks3 = new ModelBlombergBnew();
	ModelTypeBnew theTrucks4 = new ModelTypeBnew();

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		ModelRenderHelper.renderModelWithRollingStockLightControls(bodyModel, entity, f5);

		if (entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 6||entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 7324) {
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/newBogies/fb2_new_silver_a.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(-1.3, 0.0, 0);
			theTrucks2.render(entity, f, f1, f2, f3, f4, f5);

			GL11.glTranslated(2.58, 0, 0);
			theTrucks2.render(entity, f, f1, f2, f3, f4, f5);
			GL11.glPopMatrix();
		} else if (entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 6324||entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 2436){
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/TypeB_2_Silver.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(-1.3, -0.025, 0);
			theTrucks4.render(entity, f, f1, f2, f3, f4, f5);

			GL11.glTranslated(2.6, 0, 0);
			theTrucks4.render(entity, f, f1, f2, f3, f4, f5);
			GL11.glPopMatrix();
		} else if (entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 27){
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/TypeB_2_Black.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(-1.3, -0.025, 0);
			theTrucks4.render(entity, f, f1, f2, f3, f4, f5);

			GL11.glTranslated(2.6, 0, 0);
			theTrucks4.render(entity, f, f1, f2, f3, f4, f5);
			GL11.glPopMatrix();
		} else if (entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 4 || entity instanceof AbstractTrains && ((AbstractTrains) entity).getColor() == 9){
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/blombergB_2_Blac.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(-1.3, -0.01, 0);
			theTrucks3.render(entity, f, f1, f2, f3, f4, f5);

			GL11.glTranslated(2.6, 0, 0);
			theTrucks3.render(entity, f, f1, f2, f3, f4, f5);
			GL11.glPopMatrix();
		} else {
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/newBogies/fb2_new_black_a.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(-1.3, 0.0, 0);
			theTrucks2.render(entity, f, f1, f2, f3, f4, f5);

			GL11.glTranslated(2.58, 0, 0);
			theTrucks2.render(entity, f, f1, f2, f3, f4, f5);
			GL11.glPopMatrix();
		}
	}
}
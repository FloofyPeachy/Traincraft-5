/*******************************************************************************
 * Copyright (c) 2012 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.library;

import com.jcirmodelsquad.tcjcir.features.containers.ItemFortyFootContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import com.jcirmodelsquad.tcjcir.render.ItemRenderMILWSwitchStand;
import train.common.items.*;

public enum BlockIDs {

	assemblyTableI(false, null),
	assemblyTableII(false, null),
	assemblyTableIII(false, null),

	distilIdle(false, null),
	distilActive(false, null),
	signal(false, null),
	
	//book(true, ItemBlockBook.class),

	trainWorkbench(false, null),

	stopper(false, null),

	americanstopper(false, null),

	openFurnaceIdle(false, null),
	openFurnaceActive(false, null),
	oreTC(true, ItemBlockOreTC.class),
	lantern(false, null),
	switchStand(false, null),
	waterWheel(true, ItemBlockGeneratorWaterWheel.class),
	windMill(true, ItemBlockGeneratorWindMill.class),
	generatorDiesel(true, ItemBlockGeneratorDiesel.class),
	mtcTransmitterSpeed(false, null),
	mtcTransmitterMTC(false, null),
	mtcATOStopTransmitter(false, null),
	mtcReceiverMTC(false, null),
	mtcReceiverDestination(false, null),
	pdmInstructionBlock(false, null),
	//actualSignalBlock(false, null),
	//betterDetector(false, null),
	//mtcMultiTransmitter(false, null),
	//mtcMultiReceiver(false, null),
	//trainMonitor(false, null),
	mtcMarker(false, null),
    //FortyFootContainer(true, ItemFortyFootContainer.class),
	//Liquids
	diesel(false, ItemBlockFluid.class),
	refinedFuel(false, ItemBlockFluid.class),
	
	tcRailGag(false,null),
	tcRail(false,null),
	bridgePillar(false,null),

	MILWSwitchStand(false, null),
	autoSwtichStand(false, null),
	owoSwitchStand(false,null),
	circleSwitchStand(false,null),
	owoYardSwitchStand(false,null),
	Racor36D_1(false,null),
	Racor36D_2(false,null),
	Racor36H(false,null),

	snowGravel(false,null),
    mtcVBCController(false,null ),

	//crossing stuff
	MFPBWigWag(false, null),
	WigWag(false, null),
	CrossingTest(false, null),
	CrossingBase(false, null),
	Flashers(false, null),
	StandardCantilever1(false, null),
	StandardCantilever2(false, null),
	StandardCantilever3(false, null),
	MediumCantileverRight(false, null),
	MediumCantileverLeft(false, null),
	LargeCantilever(false, null),
	PedestrianCrossing(false, null),
	StandardCrossingArm(false, null),
	StandardCrossingArm2(false, null),
	WoodenCrossingBuck(false, null),
	SmallBungalo(false, null),
	MediumBungalo(false, null),
	LargeBungalo(false, null),
	;

	public Block block;
	public boolean hasItemBlock;
	public Class itemBlockClass;

	BlockIDs(boolean hasItemBlock, Class<? extends ItemBlock> itemBlockClass) {
		this.hasItemBlock = hasItemBlock;
		this.itemBlockClass = itemBlockClass;
	}
}

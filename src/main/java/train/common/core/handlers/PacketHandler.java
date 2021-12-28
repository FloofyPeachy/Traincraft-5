/*******************************************************************************
 * Copyright (c) 20getNetworkID()2 Mrbrutal. All rights reserved.
 * 
 * @name TrainCraft
 * @author Mrbrutal
 ******************************************************************************/

package train.common.core.handlers;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import train.common.Traincraft;
import train.common.adminbook.ItemAdminBook;
import train.common.core.network.*;
import train.common.library.Info;
import train.common.mtc.network.*;
import train.common.mtc.network.handlers.*;

public class PacketHandler {

	public static void init(){
		Traincraft.tcLog.info("Initialize Packets");
		Traincraft.modChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Info.channel);
		Traincraft.keyChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Info.keyChannel);
		Traincraft.rotationChannel = NetworkRegistry.INSTANCE.newSimpleChannel(Info.rotationChannel);


		Traincraft.slotschannel = NetworkRegistry.INSTANCE.newSimpleChannel("slots");
		Traincraft.ignitionChannel = NetworkRegistry.INSTANCE.newSimpleChannel("ignition");
		Traincraft.brakeChannel = NetworkRegistry.INSTANCE.newSimpleChannel("brake");
		Traincraft.lockChannel = NetworkRegistry.INSTANCE.newSimpleChannel("lock");
		Traincraft.builderChannel = NetworkRegistry.INSTANCE.newSimpleChannel("builder");



		Traincraft.keyChannel.registerMessage(ItemAdminBook.PacketAdminBook.Handler.class, ItemAdminBook.PacketAdminBook.class, 4, Side.CLIENT);
		Traincraft.keyChannel.registerMessage(ItemAdminBook.PacketAdminBookClient.Handler.class, ItemAdminBook.PacketAdminBookClient.class, 5, Side.SERVER);

		Traincraft.keyChannel.registerMessage(PacketKeyPress.Handler.class, PacketKeyPress.class, 1,
				Side.SERVER);
		Traincraft.rotationChannel.registerMessage(PacketRollingStockRotation.Handler.class,
				PacketRollingStockRotation.class, 2, Side.CLIENT);
		 Traincraft.modChannel.registerMessage(PacketSetJukeboxStreamingUrl.Handler.class,
				PacketSetJukeboxStreamingUrl.class, 1, Side.SERVER);
		Traincraft.slotschannel.registerMessage(PacketSlotsFilled.Handler.class, PacketSlotsFilled.class,
				3, Side.CLIENT);
		Traincraft.brakeChannel.registerMessage(PacketParkingBrake.Handler.class, PacketParkingBrake.class,
				4, Side.SERVER);
		Traincraft.lockChannel.registerMessage(PacketSetTrainLockedToClient.Handler.class,
				PacketSetTrainLockedToClient.class, 5, Side.SERVER);
		Traincraft.ignitionChannel.registerMessage(PacketSetLocoTurnedOn.Handler.class, PacketSetLocoTurnedOn.class,
				6, Side.SERVER);
		Traincraft.modChannel.registerMessage(PacketLantern.Handler.class, PacketLantern.class, 7,
				Side.SERVER);
		Traincraft.builderChannel.registerMessage(PacketTrackBuilderHeight.Handler.class,
				PacketTrackBuilderHeight.class, 8, Side.SERVER);
		Traincraft.builderChannel.registerMessage(PacketTrackBuilderFollow.Handler.class,
				PacketTrackBuilderFollow.class, 9, Side.SERVER);

		Traincraft.mtcBlockChannel.registerMessage(HANDLERS[0], PacketUpdateSpeedTransmitter.class, 200, Side.SERVER);
		Traincraft.mtcBlockChannel.registerMessage(HANDLERS[1], PacketUpdateStopPointTransmitter.class, 201, Side.SERVER);
		Traincraft.mtcChannel.registerMessage(HANDLERS[2], PacketSpeedLimit.class, 203, Side.CLIENT);
		Traincraft.mtcChannel.registerMessage(HANDLERS[3], PacketStopPoint.class, 204, Side.CLIENT);
		Traincraft.mtcChannel.registerMessage(HANDLERS[4], PacketMTCStatus.class, 205, Side.CLIENT);

	}

	private static final IMessageHandler[] HANDLERS = new IMessageHandler[]{
			new IMessageHandler<IMessage, IMessage>() {
				@Override public IMessage onMessage(IMessage message, MessageContext ctx) {return null;}
			},
			new IMessageHandler<IMessage, IMessage>() {
				@Override public IMessage onMessage(IMessage message, MessageContext ctx) {return null;}
			},
			new IMessageHandler<IMessage, IMessage>() {
				@Override public IMessage onMessage(IMessage message, MessageContext ctx) {return null;}
			},
			new IMessageHandler<IMessage, IMessage>() {
				@Override public IMessage onMessage(IMessage message, MessageContext ctx) {return null;}
			},
			new IMessageHandler<IMessage, IMessage>() {
				@Override public IMessage onMessage(IMessage message, MessageContext ctx) {return null;}
			},
			new IMessageHandler<IMessage, IMessage>() {
				@Override public IMessage onMessage(IMessage message, MessageContext ctx) {return null;}
			}
	};
}


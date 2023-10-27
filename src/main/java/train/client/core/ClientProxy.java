package train.client.core;

import com.jcirmodelsquad.tcjcir.features.WigglyWobblyHandler;
import com.jcirmodelsquad.tcjcir.features.containers.GuiFortyFootContainer;
import com.jcirmodelsquad.tcjcir.features.containers.TileFortyFootContainer;
import com.jcirmodelsquad.tcjcir.features.containers.render.FortyFootContainerRender;
import com.jcirmodelsquad.tcjcir.features.containers.render.ItemRenderFortyFootContainer;
import com.jcirmodelsquad.tcjcir.render.*;
import com.jcirmodelsquad.tcjcir.tile.*;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import javazoom.jl.decoder.JavaLayerUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import train.client.core.handlers.ClientTickHandler;
import train.client.core.handlers.CustomRenderHandler;
import train.client.core.handlers.RecipeBookHandler;
import train.client.core.handlers.TCKeyHandler;
import train.client.core.helpers.JLayerHook;
import train.client.gui.*;
import train.client.render.*;
import train.common.Traincraft;
import train.common.adminbook.GUIAdminBook;
import train.common.api.EntityBogie;
import train.common.api.EntityRollingStock;
import train.common.core.CommonProxy;
import train.common.core.Traincraft_EventSounds;
import train.common.entity.digger.EntityRotativeDigger;
import train.common.entity.digger.EntityRotativeWheel;
import train.common.entity.rollingStock.EntityJukeBoxCart;
import train.common.entity.zeppelin.EntityZeppelinOneBalloon;
import train.common.entity.zeppelin.EntityZeppelinTwoBalloons;
import train.common.library.BlockIDs;
import train.common.library.GuiIDs;
import train.common.library.Info;
import train.common.mtc.render.RenderMTCBlock;
import train.common.mtc.tile.TileTransmitterSpeed;
import train.common.tile.*;
import train.common.tile.tileSwitch.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

public class ClientProxy extends CommonProxy {

	public static boolean isHoliday() {
		Calendar cal = Calendar.getInstance();
		return(cal.get(Calendar.MONTH) == Calendar.DECEMBER || (cal.get(Calendar.MONTH) == Calendar.JANUARY) && cal.get(Calendar.DATE) < 7);
	}


	@Override
	public void throwAlphaException() {
		throw new AlphaExpiredException();
	}

	@Override




	public void registerEvents(FMLPreInitializationEvent event) {
		super.registerEvents(event);
		ClientTickHandler tickHandler = new ClientTickHandler();
		CustomRenderHandler renderHandler = new CustomRenderHandler();
		HUDloco huDloco = new HUDloco();
		WigglyWobblyHandler wiggle = new WigglyWobblyHandler();
		HUDMTC hudMTC = new HUDMTC();
		registerEvent(hudMTC);
	/*	HudTiltingHandler tiltingHandler = new HudTiltingHandler();
		registerEvent(tiltingHandler);*/
		registerEvent(tickHandler);
		registerEvent(renderHandler);
		registerEvent(huDloco);
		registerEvent(new HUDAipkit());
		registerEvent(wiggle);
	}

	@Override
	public void registerRenderInformation() {
		FMLCommonHandler.instance().bus().register(new ClientTickHandler());

		RenderingRegistry.registerEntityRenderingHandler(EntityRollingStock.class, new RenderRollingStock());
		RenderingRegistry.registerEntityRenderingHandler(EntityZeppelinTwoBalloons.class, new RenderZeppelins());
		RenderingRegistry.registerEntityRenderingHandler(EntityZeppelinOneBalloon.class, new RenderZeppelins());
		RenderingRegistry.registerEntityRenderingHandler(EntityRotativeDigger.class, new RenderRotativeDigger());
		RenderingRegistry.registerEntityRenderingHandler(EntityRotativeWheel.class, new RenderRotativeWheel());
		//bogies
		RenderingRegistry.registerEntityRenderingHandler(EntityBogie.class, new RenderBogie());

		ClientRegistry.bindTileEntitySpecialRenderer(TileRacor36D_1.class, new RenderRacor36D_1());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.Racor36D_1.block), new ItemRenderRacor36D_1());

		ClientRegistry.bindTileEntitySpecialRenderer(TileRacor36D_2.class, new RenderRacor36D_2());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.Racor36D_2.block), new ItemRenderRacor36D_2());

		ClientRegistry.bindTileEntitySpecialRenderer(TileRacor36H.class, new RenderRacor36H());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.Racor36H.block), new ItemRenderRacor36H());

		ClientRegistry.bindTileEntitySpecialRenderer(TileOwoYardSwitchStand.class, new RenderowoYardSwtichStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.owoYardSwitchStand.block), new ItemRenderowoYardSwitchStand());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMILWSwitchStand.class, new RenderMILWSwitchStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.MILWSwitchStand.block), new ItemRenderMILWSwitchStand());

		ClientRegistry.bindTileEntitySpecialRenderer(TileCircleSwitchStand.class, new RendercircleSwitchStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.circleSwitchStand.block), new ItemRendercircleSwitchStand());

		ClientRegistry.bindTileEntitySpecialRenderer(TileOwoSwitchStand.class, new RenderowoSwitchStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.owoSwitchStand.block), new ItemRenderowoSwitchStand());

		ClientRegistry.bindTileEntitySpecialRenderer(TileAutoSwitchStand.class, new RenderautoSwitchStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.autoSwtichStand.block), new ItemRenderautoSwitchStand());

		ClientRegistry.bindTileEntitySpecialRenderer(TileStopper.class, new RenderStopper());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.stopper.block), new ItemRenderStopper());

		ClientRegistry.bindTileEntitySpecialRenderer(TileAmericanStopper.class, new RenderAmericanStopper());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.americanstopper.block), new ItemRenderAmericanStopper());
		
		//ClientRegistry.bindTileEntitySpecialRenderer(TileBook.class, new RenderTCBook());
		//MinecraftForgeClient.registerItemRenderer(BlockIDs.book.blockID, new ItemRenderBook());

		ClientRegistry.bindTileEntitySpecialRenderer(TileSignal.class, new RenderSignal());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.signal.block), new ItemRenderSignal());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileLantern.class, new RenderLantern());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.lantern.block), new ItemRenderLantern());

		ClientRegistry.bindTileEntitySpecialRenderer(TileSwitchStand.class, new RenderSwitchStand());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.switchStand.block), new ItemRenderSwitchStand());

		ClientRegistry.bindTileEntitySpecialRenderer(TileWaterWheel.class, new RenderWaterWheel());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.waterWheel.block), new ItemRenderWaterWheel());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileWindMill.class, new RenderWindMill());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.windMill.block), new ItemRenderWindMill());

		ClientRegistry.bindTileEntitySpecialRenderer(TileGeneratorDiesel.class, new RenderGeneratorDiesel());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.generatorDiesel.block), new ItemRenderGeneratorDiesel());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileTCRail.class, new RenderTCRail());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileBridgePillar.class, new RenderBridgePillar());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.bridgePillar.block), new ItemRenderBridgePillar());

		ClientRegistry.bindTileEntitySpecialRenderer(TileTransmitterSpeed.class, new RenderMTCBlock());

		//ClientRegistry.bindTileEntitySpecialRenderer(TileFortyFootContainer.class, new FortyFootContainerRender());
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.FortyFootContainer.block), new ItemRenderFortyFootContainer());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMTCMarker.class, new RenderMTCMarker());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMFPBWigWag.class, new RenderMFPBWigWag());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.MFPBWigWag.block), new ItemRenderMFPBWigWag());

		ClientRegistry.bindTileEntitySpecialRenderer(TileWigWag.class, new RenderWigWag());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.WigWag.block), new ItemRenderWigWag());

		ClientRegistry.bindTileEntitySpecialRenderer(TileCrossingBase.class, new RenderCrossingBase());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.CrossingBase.block), new ItemRenderCrossingBase());

		ClientRegistry.bindTileEntitySpecialRenderer(TileFlashers.class, new RenderFlashers());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.Flashers.block), new ItemRenderFlashers());

		ClientRegistry.bindTileEntitySpecialRenderer(TileStandardCantilever1.class, new RenderStandardCantilever1());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.StandardCantilever1.block), new ItemRenderStandardCantilever1());

		ClientRegistry.bindTileEntitySpecialRenderer(TileStandardCantilever2.class, new RenderStandardCantilever2());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.StandardCantilever2.block), new ItemRenderStandardCantilever2());

		ClientRegistry.bindTileEntitySpecialRenderer(TileStandardCantilever3.class, new RenderStandardCantilever3());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.StandardCantilever3.block), new ItemRenderStandardCantilever3());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMediumCantileverRight.class, new RenderMediumCantileverRight());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.MediumCantileverRight.block), new ItemRenderMediumCantileverRight());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMediumCantileverLeft.class, new RenderMediumCantileverLeft());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.MediumCantileverLeft.block), new ItemRenderMediumCantileverLeft());

		ClientRegistry.bindTileEntitySpecialRenderer(TileLargeCantilever.class, new RenderLargeCantilever());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.LargeCantilever.block), new ItemRenderLargeCantilever());

		ClientRegistry.bindTileEntitySpecialRenderer(TilePedestrianCrossing.class, new RenderPedestrianCrossing());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.PedestrianCrossing.block), new ItemRenderPedestrianCrossing());

		ClientRegistry.bindTileEntitySpecialRenderer(TileFlashersWithGate.class, new RenderFlashersWithGate());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.StandardCrossingArm.block), new ItemRenderFlashersWithGate());

		ClientRegistry.bindTileEntitySpecialRenderer(TileFlashersWithGate2.class, new RenderFlashersWithGate2());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.StandardCrossingArm2.block), new ItemRenderFlashersWithGate2());

		ClientRegistry.bindTileEntitySpecialRenderer(TileWoodenCrossingBuck.class, new RenderWoodenCrossingBuck());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.WoodenCrossingBuck.block), new ItemRenderWoodenCrossingBuck());

		ClientRegistry.bindTileEntitySpecialRenderer(TileSmallBungalo.class, new RenderSmallBungalo());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.SmallBungalo.block), new ItemRenderSmallBungalo());

		ClientRegistry.bindTileEntitySpecialRenderer(TileMediumBungalo.class, new RenderMediumBungalo());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.MediumBungalo.block), new ItemRenderMediumBungalo());

		ClientRegistry.bindTileEntitySpecialRenderer(TileLargeBungalo.class, new RenderLargeBungalo());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockIDs.LargeBungalo.block), new ItemRenderLargeBungalo());


	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		EntityPlayer riddenByEntity = null;
		Entity entity = player.ridingEntity;
		if (player.ridingEntity != null) {
			riddenByEntity = (EntityPlayer) entity.riddenByEntity;
		}

		Entity entity1 = null;
		if (y == -1) {
			for (Object ent : world.loadedEntityList) {
				if (((Entity) ent).getEntityId() == x)
					entity1 = (Entity) ent;
			}
		}
		switch (ID) {
		case (GuiIDs.CRAFTER_TIER_I):
			return te != null && te instanceof TileCrafterTierI ? new GuiCrafterTier(player.inventory, (TileCrafterTierI) te) : null;
		case (GuiIDs.CRAFTER_TIER_II):
			return te != null && te instanceof TileCrafterTierII ? new GuiCrafterTier(player.inventory, (TileCrafterTierII) te) : null;
		case (GuiIDs.CRAFTER_TIER_III):
			return te != null && te instanceof TileCrafterTierIII ? new GuiCrafterTier(player.inventory, (TileCrafterTierIII) te) : null;
		case (GuiIDs.DISTIL):
			return te != null && te instanceof TileEntityDistil ? new GuiDistil(player.inventory, (TileEntityDistil) te) : null;
		case (GuiIDs.GENERATOR_DIESEL):
			return te != null && te instanceof TileGeneratorDiesel ? new GuiGeneratorDiesel(player.inventory, (TileGeneratorDiesel) te) : null;
		case (GuiIDs.OPEN_HEARTH_FURNACE):
			return te != null && te instanceof TileEntityOpenHearthFurnace ? new GuiOpenHearthFurnace(player.inventory, (TileEntityOpenHearthFurnace) te) : null;
		case GuiIDs.TRAIN_WORKBENCH:
			return te != null && te instanceof TileTrainWbench ? new GuiTrainCraftingBlock(player.inventory, player.worldObj, (TileTrainWbench) te) : null;
		case (GuiIDs.LOCO):
			return riddenByEntity != null ? new GuiLoco2(riddenByEntity.inventory, entity) : null;
		case (GuiIDs.FORNEY):
			return riddenByEntity != null ? new GuiForney(riddenByEntity.inventory, entity) : null;
		case (GuiIDs.CRAFTING_CART):
			return riddenByEntity != null ? new GuiCraftingCart(riddenByEntity.inventory, world) : null;
		case (GuiIDs.FURNACE_CART):
			return riddenByEntity != null ? new GuiFurnaceCart(riddenByEntity.inventory, entity) : null;
		case (GuiIDs.ZEPPELIN):
			return riddenByEntity != null ? new GuiZepp(riddenByEntity.inventory, entity) : null;
		case (GuiIDs.DIGGER):
			return riddenByEntity != null ? new GuiRotativeDigger(riddenByEntity.inventory, entity) : null;
		case (GuiIDs.MTC_INFO):
			return riddenByEntity != null && Loader.isModLoaded("ComputerCraft") ? new GuiMTCInfo(player) : null;

			//Stationary entities while player is not riding. 
		case (GuiIDs.FREIGHT):
			return entity1 != null ? new GuiFreight(player,player.inventory, entity1) : null;
		case (GuiIDs.TENDER):
			return entity1 != null ? new GuiTender(player,player.inventory, entity1) : null;
		case (GuiIDs.BUILDER):
			return entity1 != null ? new GuiBuilder(player,player.inventory, entity1) : null;
		case (GuiIDs.LIQUID):
			return entity1 != null ? new GuiLiquid(player,player.inventory, entity1) : null;
		case (GuiIDs.RECIPE_BOOK):
			return new GuiRecipeBook(player, player.getCurrentEquippedItem());
		/*case (GuiIDs.RECIPE_BOOK2):
			return te != null && te instanceof TileBook ? new GuiRecipeBook2(player, player.getCurrentEquippedItem()) : new GuiRecipeBook2(player, player.getCurrentEquippedItem());*/
		case (GuiIDs.PAINTBRUSH):
			return entity1 != null ? new GuiPaintbrushMenu(player, (EntityRollingStock) entity1) : null;
		case (GuiIDs.LOCK_MENU):
			if (entity != null) { // If player is riding the entity (locomotives).
				return new GuiLockMenu(player, (EntityRollingStock) entity);
			} else { // If player is not riding the entity (freight).
				return entity1 != null ? new GuiLockMenu(player, ((EntityRollingStock) entity1)) : null;
			}
		case (GuiIDs.LOCK_MENU_SWITCHES):
			return (te instanceof TileSwitchStand ? new GuiLockMenuSwitches(player, ((TileSwitchStand) te)) : null);
		case (GuiIDs.CARGO_MENU):
				return entity1 != null ? new GuiCargoSelection(player, ((EntityRollingStock) entity1)) : null;
		case (GuiIDs.CREDITS_BOOK):
				return new GUICreditsBook(player, player.getCurrentEquippedItem());
		case (GuiIDs.LANTERN):
			return new GuiLantern(player, (TileLantern)te);
		case (GuiIDs.JUKEBOX):
			return entity1 != null ? new GuiJukebox(player,(EntityJukeBoxCart)entity1) : null;
		case (GuiIDs.FORTY_FOOT_CONTAINER):
			return te instanceof TileFortyFootContainer ? new GuiFortyFootContainer(player.inventory, (TileFortyFootContainer) te) : null;
		default:
			return null;
		}
	}

	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

	@Override
	public GuiScreen getCurrentScreen() {
		return Minecraft.getMinecraft().currentScreen;
	}
	@Override
	public void registerVillagerSkin(int villagerId, String textureName) {
		VillagerRegistry.instance().registerVillagerSkin(villagerId, new ResourceLocation(Info.resourceLocation,Info.villagerPrefix + textureName));
	}

	@Override
	public void registerSounds() {
		MinecraftForge.EVENT_BUS.register(new Traincraft_EventSounds());
	}
	
	@Override
	public void registerBookHandler() {
		RecipeBookHandler recipeBookHandler = new RecipeBookHandler();
	}

	@Override
	public Minecraft getMinecraft() {
		return Minecraft.getMinecraft();
	}
	
	@Override
	public EntityPlayer getPlayer() {
		return getMinecraft().thePlayer;
	}

	@Optional.Method(modid = "NotEnoughItems")
	@Override
	public void doNEICheck(ItemStack stack) {
		if (Minecraft.getMinecraft().thePlayer != null) {
			if(Loader.isModLoaded("Not Enough Items")) {
				try {
					Class neiApi = Class.forName("codechicken.nei.api.API");
					Method hideItem = neiApi.getDeclaredMethod("hideItem", stack.getClass());
					hideItem.invoke(null, stack);
				} catch (ClassNotFoundException e) {
					Traincraft.tcLog.log(Level.WARN, "Chicken core didn't have required class: Wrong version of the library or something is horribly wrong", e);
				} catch (NoSuchMethodException e) {
					Traincraft.tcLog.log(Level.WARN, "Chicken core didn't have required method: Wrong version of the library or something is horribly wrong", e);
				} catch (SecurityException e) {
					Traincraft.tcLog.log(Level.FATAL, "Something is horribly wrong", e);
				} catch (IllegalAccessException e) {
					Traincraft.tcLog.log(Level.FATAL, "Something is horribly wrong", e);
				} catch (IllegalArgumentException e) {
					Traincraft.tcLog.log(Level.WARN, "Chicken core had the method but it's signature was wrong: Wrong version of the library or something is horribly wrong", e);
				} catch (InvocationTargetException e) {
					Traincraft.tcLog.log(Level.WARN, "The method we called from Chicken core threw an exception", e);
				}
			}
        }
	}
	
	@Override
	public float getJukeboxVolume() {
		return Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.RECORDS) * Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER);
	}

	@Override
	public void openadmingui(String data){
		Minecraft.getMinecraft().displayGuiScreen(new GUIAdminBook(data));
	}

	@Override
	public void registerKeyBindingHandler() {
		FMLCommonHandler.instance().bus().register(new TCKeyHandler());
	}
	
	@Override
	public void setHook() {
		JavaLayerUtils.setHook(new JLayerHook(Minecraft.getMinecraft()));
	}
}
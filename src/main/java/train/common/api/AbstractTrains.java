package train.common.api;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import mods.railcraft.api.carts.IMinecart;
import mods.railcraft.api.carts.IRoutableCart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.util.Constants;
import org.apache.commons.lang3.ArrayUtils;
import train.client.gui.GuiTCTextField;
import train.client.render.RenderEnum;
import train.common.Traincraft;
import train.common.adminbook.ItemAdminBook;
import train.common.core.handlers.ConfigHandler;
import train.common.core.handlers.TrainHandler;
import train.common.entity.CargoManager;
import train.common.entity.TrustedPlayer;
import train.common.items.ItemChunkLoaderActivator;
import train.common.items.ItemRollingStock;
import train.common.items.ItemWrench;
import train.common.library.EnumTrains;
import train.common.overlaytexture.OverlayTextureManager;

import java.util.*;

public abstract class AbstractTrains extends EntityMinecart implements IMinecart, IRoutableCart, IEntityAdditionalSpawnData {

	/**
	 * The color of the current rollingstock -1 if default
	 */
	protected int color;
	public boolean isAttached = false;
	public boolean isAttaching = false;
	public static int numberOfTrains;
	public EntityPlayer playerEntity;
	public double Link1;
	public double Link2;
	protected boolean linked = false;
	public EntityRollingStock cartLinked1;
	public EntityRollingStock cartLinked2;
	public int clearLinkTimer = 0;
	//private Set chunks;
	protected Ticket chunkTicket;
	public float renderYaw;
	protected float renderPitch;
	public TrainHandler train;
	public List<ChunkCoordIntPair> loadedChunks = new ArrayList<ChunkCoordIntPair>();
	public boolean shouldChunkLoad = true;
	protected boolean itemdropped =false;
	/**
	 * A reference to EnumTrains containing all spec for this specific train
	 */
	protected EnumTrains trainSpec;
	private RenderEnum renderSpec;
	/**
	 * The name of the train based on the item name
	 */
	public String trainName = "";
	public double accelerate = 0.7D;
	public double brake = 0.96D;
	/**
	 * determines the mass of the carts from 0 to 10 it's then multiplied by 10
	 * to pretend this is [tons]
	 */
	public double mass = 1;
	/**
	 * the default mass, not affected by weight of items/liquids
	 */
	public double defaultMass = 1;
	/**
	 * the power of locomotives, 0 for carts
	 */
	public int power = 0;
	/**
	 * Whether this train is locked and can only be used by the Owner
	 */
	public boolean locked = false;
	/**
	 * The owner of the train: The user who spawned it
	 */
	public String trainOwner = "";

	public String getTrainOwner() {
		return trainOwner;
	}

	public void setTrainOwner(String trainOwner) {
		this.trainOwner = trainOwner;
	}

	/**
	 * The creator of the train
	 */
	public String trainCreator = "";

	/**
	 * The type of the train: steam tender diesel electric freight flat tank
	 * passenger work special
	 */
	public String trainType = "";

	/**
	 * player who destroyed the train
	 */
	protected String trainDestroyer = "";

	/**
	 * unique ID for a train. ID is create when item is created. This allows to
	 * track a train not only in his entity form
	 */
	public int uniqueID = -1;
	/**
	 * supposed to store the last ID given;
	 */
	public static int uniqueIDs = 1;

	/**
	 * The distance this train has traveled
	 */
	public double trainDistanceTraveled = 0;

	public String destination = "";

	public GuiTCTextField guiTCTextFieldTrainNote;
	public String trainNote = "";
	private OverlayTextureManager overlayTextureContainer;
	private boolean acceptsOverlayTextures = false;


	/**
	 * Registers all possible color textures
	 */
	public ArrayList<Integer> acceptedColors;

	/**
	 * <p>List of players trusted to use the train</p>
	 */
	private List<TrustedPlayer> trustedList = new ArrayList<>();
	private CargoManager cargoManager = null;
	public final Map<Integer, String> textureDescriptionMap = new HashMap<>();

	@Override
	public float getBrightness(float p_70013_1_) {
		getBrightnessForRender(2);
		getBrightness(2);
		isBurning();
		isImmuneToFire();
		return super.getBrightness(p_70013_1_);
	}

	public AbstractTrains(World world) {
		super(world);
		renderDistanceWeight = 2.0D;
		color = -1;
		dataWatcher.addObject(12, color);
		acceptedColors = new ArrayList<Integer>();
		dataWatcher.addObject(6, trainType);
		dataWatcher.addObject(7, trainOwner);
		dataWatcher.addObject(8, trainDestroyer);
		dataWatcher.addObject(9, trainName);
		dataWatcher.addObject(10, numberOfTrains);
		dataWatcher.addObject(11, uniqueID);
		dataWatcher.addObject(13, trainCreator);
		dataWatcher.addObject(31, trainNote); //train note

		// Chunk loading by default should always be disabled when placing a locomotive
		this.setFlag(7, false);


		for (EnumTrains trains : EnumTrains.values()) {
			if (trains.getEntityClass().equals(this.getClass())) {
				this.setDefaultMass(trains.getMass());
				trainSpec = trains;
				if (trains.getColors() != null) {
					for (int i = 0; i < trains.getColors().length; i++) {
						this.acceptedColors.add((trains.getColors()[i]));
					}
				}
				this.setSize(0.98f, 1.98f);
				this.setMinecartName(trainSpec.name());

				break;
			}
		}
		for (RenderEnum render : RenderEnum.values()) {
			if (render.getEntityClass().equals(this.getClass())) {
				renderSpec = render;
				break;
			}
		}
	}

	public AbstractTrains(World world, double x, double y, double z){
		this(world);
		this.setPosition(x, y, z);
	}

	public boolean IsChunkLoading()
	{
		return getFlag(7);
	}

	@Override
	public AxisAlignedBB getCollisionBox(Entity p_70114_1_) {
		if(riddenByEntity!=p_70114_1_){
			return super.getCollisionBox(p_70114_1_);
		} else {
				return null;
			}
	}

	/**
	 * <p>This method is called on the client side when an entity is being loaded in. The additionalData buffer is sent from the server
	 * and is populated by the server using the writeSpawnData method.</p>
	 * <br></br><p>"this is basically NBT for entity spawn, to keep data between client and server in sync because some data is not automatically shared."</p>
	 * @param additionalData The packet data stream
	 */
	@Override
	public void readSpawnData(ByteBuf additionalData) {
		locked = additionalData.readBoolean();
	}

	/**
	 * <p>This method is called on the server side when a connected client is loading the entity. Data written
	 * to the ByteBuffer will be synced with the client and available to the client through the readSpawnData method.</p>
	 * <br></br><p>"this is basically NBT for entity spawn, to keep data between client and server in sync because some data is not automatically shared."</p>
	 * @param buffer The packet data stream
	 */
	@Override
	public void writeSpawnData(ByteBuf buffer) {
		buffer.writeBoolean(locked);
	}


	public abstract boolean isLocomotive();

	public abstract boolean isPassenger();

	public abstract boolean isFreightCart();

	public abstract boolean isFreightOrPassenger();

	public abstract boolean isBuilder();

	public abstract boolean isTender();

	public abstract boolean isWorkCart();

	public abstract boolean isElectricTrain();

	public abstract boolean isLinked();

	protected abstract boolean canOverheat();

	protected abstract int getOverheatTime();

	public abstract float getLinkageDistance(EntityMinecart cart);

	//public abstract int getID();

	public abstract boolean canBeAdjusted(EntityMinecart cart2);

	public abstract float getOptimalDistance(EntityMinecart cart2);

	public abstract List<ItemStack> getItemsDropped();

	public int getUniqueTrainID(){
		return uniqueID;
	}

	public String getTrainNote() {
		return dataWatcher.getWatchableObjectString(31);
	}

	public void manageChunkLoading(){
		//if(this instanceof Locomotive)System.out.println("I'm alive. Remote: " + worldObj.isRemote);
		if (!worldObj.isRemote && this.uniqueID == -1) {
			if (FMLCommonHandler.instance().getMinecraftServerInstance() != null) {
				//TraincraftSaveHandler.createFile(FMLCommonHandler.instance().getMinecraftServerInstance());
				//int readID = TraincraftSaveHandler.readInt(FMLCommonHandler.instance().getMinecraftServerInstance(), "numberOfTrains:");
				//int newID = setNewUniqueID(readID);
				setNewUniqueID(this.getEntityId());
				//TraincraftSaveHandler.writeValue(FMLCommonHandler.instance().getMinecraftServerInstance(), "numberOfTrains:", new String("" + newID));
				//System.out.println("Train is missing an ID, adding new one for "+this.trainName+" "+this.uniqueID);
			}
		}
		shouldChunkLoad = getFlag(7);
		if (shouldChunkLoad){
			if(this.chunkTicket == null) {
				this.requestTicket();
			}
		}
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(!(this instanceof EntityRollingStock)) {
			manageChunkLoading();
		}
		/*
		 * if (worldObj.isRemote) { if (this.getFlag(6)) { if (this.chunks !=
		 * null) { Iterator var3 = this.chunks.iterator(); while
		 * (var3.hasNext()) { ChunkCoordIntPair var4 = (ChunkCoordIntPair)
		 * var3.next(); int x = var4.getCenterXPos(); int z =
		 * var4.getCenterZPosition();
		 * this.worldObj.spawnParticle("mobSpellAmbient", x, posY, z, 0, 0, 0);
		 * for (int i = 0; i < 10; i++) { this.worldObj.spawnParticle("portal",
		 * x + (this.rand.nextDouble() - 0.5D) * (double) 8, this.posY +
		 * this.rand.nextDouble() * (double) 6 - 0.25D, z +
		 * (this.rand.nextDouble() - 0.5D) * (double) 8, (this.rand.nextDouble()
		 * - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() -
		 * 0.5D) * 2.0D); } } } else { this.setupChunks(this.chunkCoordX,
		 * this.chunkCoordZ); } } } else if (!ConfigHandler.CHUNK_LOADING) {
		 * this.releaseTicket(); } else { if (this.ticket == null) {
		 * this.requestTicket(); } }
		 */
	}
	@Override
	public void setDead() {
		ForgeChunkManager.releaseTicket(chunkTicket);
	}

	public int setNewUniqueID(int numberOfTrains) {
		// System.out.println(numberOfTrains);
		if (numberOfTrains <= 0) {
			numberOfTrains = uniqueIDs++;
		}
		else {
			uniqueIDs = numberOfTrains++;
		}
		this.uniqueID = numberOfTrains;
		getEntityData().setInteger("uniqueID", numberOfTrains);
		// System.out.println("setting new ID "+uniqueID);
		return numberOfTrains;
	}

	@Override
	public boolean interactFirst(EntityPlayer entityplayer) {
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if (!worldObj.isRemote && ConfigHandler.CHUNK_LOADING && (this instanceof Locomotive) ) {
			if (itemstack != null && itemstack.getItem() instanceof ItemChunkLoaderActivator) {
				this.playerEntity = entityplayer;
				if (getFlag(7)) {
					this.setFlag(7, false);
					entityplayer.addChatMessage(new ChatComponentText("Stop loading chunks"));
					ForgeChunkManager.releaseTicket(chunkTicket);
					chunkTicket = null;
				}
				else if (!getFlag(7)) {
					this.setFlag(7, true);
					entityplayer.addChatMessage(new ChatComponentText("Start loading chunks"));
				}
				itemstack.damageItem(1, entityplayer);
				return true;
			} else if(lockThisCart(itemstack, entityplayer)) {
				return true;
			}
		}
		return false;
	}

	public void setPacketChunkLoading(boolean shouldChunkLoad)
	{
		this.shouldChunkLoad = shouldChunkLoad;
		if (getFlag(7))
		{
			this.setFlag(7, false);

			ForgeChunkManager.releaseTicket(chunkTicket);
			chunkTicket = null;
		}
	}

	/**
	 * set the color of the rollingstock
	 *
	 * @see ItemRollingStock
	 * @param color
	 */
	/*public void setColor(int color) {
		if (color==-1 && EnumTrains.getCurrentTrain(getCartItem().getItem()).getColors()!=null){
			color = getColorFromString(EnumTrains.getCurrentTrain(getCartItem().getItem()).getColors()[0]);
		}
		dataWatcher.updateObject(12, color);
	}*/
	public void setColor(int color) {
		if (EnumTrains.getCurrentTrain(getCartItem().getItem()).getColors()!=null){
			if (color==-1 || !ArrayUtils.contains(EnumTrains.getCurrentTrain(getCartItem().getItem()).getColors(),(byte)color)) {
				color = (EnumTrains.getCurrentTrain(getCartItem().getItem()).getColors()[0]);
			}
		}
		dataWatcher.updateObject(12, color);
		this.getEntityData().setInteger("color", color);
	}

	public void setRenderYaw(float yaw) {
		this.renderYaw = yaw;
	}

	public void setRenderPitch(float pitch) {
		this.renderPitch = pitch;
	}

	public int getColor() {
		return dataWatcher.getWatchableObjectInt(12);
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		//super.writeEntityToNBT(nbttagcompound);
		nbttagcompound.setInteger("color", getColor());
		nbttagcompound.setBoolean("chunkLoadingState", getFlag(7));
		nbttagcompound.setDouble("trainDistanceTraveled", trainDistanceTraveled);
		nbttagcompound.setString("theOwner", trainOwner);
		nbttagcompound.setBoolean("locked", locked);
		nbttagcompound.setString("theCreator", trainCreator);
		nbttagcompound.setString("theName", trainName);
		nbttagcompound.setString("theType", trainType);
		nbttagcompound.setInteger("uniqueID", uniqueID);
		//nbttagcompound.setInteger("uniqueIDs",uniqueIDs);

		nbttagcompound.setInteger("numberOfTrains", AbstractTrains.numberOfTrains);
		nbttagcompound.setBoolean("isAttached", this.isAttached);
		nbttagcompound.setBoolean("linked", this.linked);
		//nbttagcompound.setDouble("motionX", motionX);
		//nbttagcompound.setDouble("motionZ", motionZ);
		nbttagcompound.setDouble("Link1", Link1);
		nbttagcompound.setDouble("Link2", Link2);

		nbttagcompound.setInteger("Dim", this.dimension);

		nbttagcompound.setLong("UUIDM", this.getUniqueID().getMostSignificantBits());
		nbttagcompound.setLong("UUIDL", this.getUniqueID().getLeastSignificantBits());
		nbttagcompound.setString("trainNote", trainNote);
		exportTrustedListToNBT(nbttagcompound);
		if (cargoManager != null) {
			getCargoManager().exportToNBT(nbttagcompound);
		}

		nbttagcompound.setBoolean("acceptsOverlayTextures", acceptsOverlayTextures);
		if (acceptsOverlayTextures) {
			nbttagcompound.setTag("overlayTextureConfigTag", overlayTextureContainer.getOverlayConfigTag());
		}
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		//super.readEntityFromNBT(nbttagcompound);
		setColor(nbttagcompound.getInteger("color"));
		setFlag(7, nbttagcompound.getBoolean("chunkLoadingState"));
		trainDistanceTraveled = nbttagcompound.getDouble("trainDistanceTraveled");
		trainOwner = nbttagcompound.getString("theOwner");
		this.locked = nbttagcompound.getBoolean("locked");
		setFlag(8, locked);
		trainCreator = nbttagcompound.getString("theCreator");
		trainName = nbttagcompound.getString("theName");
		trainType = nbttagcompound.getString("theType");
		uniqueID = nbttagcompound.getInteger("uniqueID");
		//uniqueIDs = nbttagcompound.getInteger("uniqueIDs");
		setInformation(trainType, trainOwner, trainCreator, trainName, uniqueID);

		numberOfTrains = nbttagcompound.getInteger("numberOfTrains");
		isAttached = nbttagcompound.getBoolean("isAttached");
		linked = nbttagcompound.getBoolean("linked");
		//motionX = nbttagcompound.getDouble("motionX");
		//motionZ = nbttagcompound.getDouble("motionZ");
		Link1 = nbttagcompound.getDouble("Link1");
		Link2 = nbttagcompound.getDouble("Link2");
		if(nbttagcompound.hasKey("Dim")){
			this.dimension=nbttagcompound.getInteger("Dim");
		}
		if(nbttagcompound.hasKey("trainNote")) trainNote = nbttagcompound.getString("trainNote");
		dataWatcher.updateObject(31, trainNote);

		if(nbttagcompound.hasKey("UUIDM")){
			this.entityUniqueID = new UUID(nbttagcompound.getLong("UUIDM"), nbttagcompound.getLong("UUIDL"));
		}
		importTrustedListFromNBT(nbttagcompound);
		if (cargoManager != null) {
			getCargoManager().importFromNBTTagCompound(nbttagcompound);
		}

		if (nbttagcompound.getBoolean("acceptsOverlayTextures")) {
			acceptsOverlayTextures = true;
			overlayTextureContainer.importFromConfigTag(nbttagcompound.getCompoundTag("overlayTextureConfigTag"));
		}
	}

	@Override
	public boolean writeMountToNBT(NBTTagCompound tag){
		return false;
	}
	@Override
	public boolean writeToNBTOptional(NBTTagCompound p_70039_1_) {
		if (!this.isDead && this.getEntityString() != null) {
			p_70039_1_.setString("id", this.getEntityString());
			this.writeToNBT(p_70039_1_);
			return true;
		}
		return false;
	}

	public void setInformation(String trainType, String trainOwner, String trainCreator, String trainName, int uniqueID) {
		if (!worldObj.isRemote) {
			dataWatcher.updateObject(6, trainType);
			dataWatcher.updateObject(7, trainOwner);
			dataWatcher.updateObject(9, trainName);
			dataWatcher.updateObject(11, uniqueID);
			dataWatcher.updateObject(31, trainNote);

			if (trainCreator != null && trainCreator.length() > 0){ dataWatcher.updateObject(13, trainCreator);}
		}
	}

	/**
	 * @author 02skaplan
	 * <p>Called to setup the overlay texture manager for the given AbstractTrain. It is recommended
	 * to call this from the constructor of the AbstractTrain-derived entity class.</p>
	 * <p>After calling, it is recommended to use getOverlayTextureContainer to initialze the fixed, dynamic, or both
	 * fixed and dynamic overlays with their respective settings.</p>
	 * @param acceptedType Whether the overlay manager will allow fixed, dynamic, or both fixed and dynamic overlays.
	 */
	public void initOverlayTextures(OverlayTextureManager.Type acceptedType) {
		overlayTextureContainer = new OverlayTextureManager(acceptedType, this);
		acceptsOverlayTextures = true;
	}

	public OverlayTextureManager getOverlayTextureContainer() {
		return overlayTextureContainer;
	}

	public boolean acceptsOverlayTextures() {
		return acceptsOverlayTextures;
	}

	public static String getColorAsString(int i) {
		switch (i) {
		case 0:
			return "Black";
		case 1:
			return "Red";
		case 2:
			return "Green";
		case 3:
			return "Brown";
		case 4:
			return "Blue";
		case 5:
			return "Purple";
		case 6:
			return "Cyan";
		case 7:
			return "LightGrey";
		case 8:
			return "Grey";
		case 13:
			return "Magenta";
		case 10:
			return "Lime";
		case 11:
			return "Yellow";
		case 12:
			return "LightBlue";
		case 9:
			return "Pink";
		case 14:
			return "Orange";
		case 15:
			return "White";
		case 16:
			return "Skin16";
		case 17:
			return "Skin17";
		case 18:
			return "Skin18";
		case 19:
			return "Skin19";
		case 20:
			return "Skin20";
		case 21:
			return "Skin21";
		case 22:
			return "Skin22";
		case 23:
			return "Skin23";
		case 24:
			return "Skin24";
		case 25:
			return "Skin25";
		case 26:
			return "Skin26";
		case 27:
			return "Skin27";
		case 28:
			return "Skin28";
		case 29:
			return "Skin29";
		case 30:
			return "Skin30";
		case 31:
			return "Skin31";
		case 32:
			return "Skin32";
		case 33:
			return "Skin33";
		case 34:
			return "Skin34";
		case 35:
			return "Skin35";
		case 36:
			return "Skin36";
		case 37:
			return "Skin37";
		case 38:
			return "Skin38";
		case 39:
			return "Skin39";
		case 40:
			return "Skin40";
		case 41:
			return "Skin41";
		case 42:
			return "Skin42";
		case 43:
			return "Skin43";
		case 44:
			return "Skin44";
		case 45:
			return "Skin45";
		case 46:
			return "Skin46";
		case 47:
			return "Skin47";
		case 48:
			return "Skin48";
		case 49:
			return "Skin49";
		case 50:
			return "Skin50";
		case 100:
			return "Empty";
		case 101:
			return "Full";
		default:
			return "" + i;
		}
	}

	public String getColorAsString() {
		switch (getColor()) {
		case 0:
			return "Black";
		case 1:
			return "Red";
		case 2:
			return "Green";
		case 3:
			return "Brown";
		case 4:
			return "Blue";
		case 5:
			return "Purple";
		case 6:
			return "Cyan";
		case 7:
			return "LightGrey";
		case 8:
			return "Grey";
		case 13:
			return "Magenta";
		case 10:
			return "Lime";
		case 11:
			return "Yellow";
		case 12:
			return "LightBlue";
		case 9:
			return "Pink";
		case 14:
			return "Orange";
		case 15:
			return "White";
		case 16:
			return "Skin16";
		case 17:
			return "Skin17";
		case 18:
			return "Skin18";
		case 19:
			return "Skin19";
		case 20:
			return "Skin20";
		case 21:
			return "Skin21";
		case 22:
			return "Skin22";
		case 23:
			return "Skin23";
		case 24:
			return "Skin24";
		case 25:
			return "Skin25";
		case 26:
			return "Skin26";
		case 27:
			return "Skin27";
		case 28:
			return "Skin28";
		case 29:
			return "Skin29";
		case 30:
			return "Skin30";
		case 31:
			return "Skin31";
		case 32:
			return "Skin32";
		case 33:
			return "Skin33";
		case 34:
			return "Skin34";
		case 35:
			return "Skin35";
		case 36:
			return "Skin36";
		case 37:
			return "Skin37";
		case 38:
			return "Skin38";
		case 39:
			return "Skin39";
		case 40:
			return "Skin40";
		case 41:
			return "Skin41";
		case 42:
			return "Skin42";
		case 43:
			return "Skin43";
		case 44:
			return "Skin44";
		case 45:
			return "Skin45";
		case 46:
			return "Skin46";
		case 47:
			return "Skin47";
		case 48:
			return "Skin48";
		case 49:
			return "Skin49";
		case 50:
			return "Skin50";
		case 100:
			return "Empty";
		case 101:
			return "Full";
		default:
			return "" + getColor();
		}
	}

	public static int getColorFromString(String color) {
		if (color.equals("Black")) return 0;
		if (color.equals("Red")) return 1;
		if (color.equals("Green")) return 2;
		if (color.equals("Brown")) return 3;
		if (color.equals("Blue")) return 4;
		if (color.equals("Purple")) return 5;
		if (color.equals("Cyan")) return 6;
		if (color.equals("LightGrey")) return 7;
		if (color.equals("Grey")) return 8;
		if (color.equals("Magenta")) return 13;
		if (color.equals("Lime")) return 10;
		if (color.equals("Yellow")) return 11;
		if (color.equals("LightBlue")) return 12;
		if (color.equals("Pink")) return 9;
		if (color.equals("Orange")) return 14;
		if (color.equals("White")) return 15;
		if (color.equals("Skin16")) return 16;
		if (color.equals("Skin17")) return 17;
		if (color.equals("Skin18")) return 18;
		if (color.equals("Skin19")) return 19;
		if (color.equals("Skin20")) return 20;
		if (color.equals("Skin21")) return 21;
		if (color.equals("Skin22")) return 22;
		if (color.equals("Skin23")) return 23;
		if (color.equals("Skin24")) return 24;
		if (color.equals("Skin25")) return 25;
		if (color.equals("Skin26")) return 26;
		if (color.equals("Skin27")) return 27;
		if (color.equals("Skin28")) return 28;
		if (color.equals("Skin29")) return 29;
		if (color.equals("Skin30")) return 30;
		if (color.equals("Skin31")) return 31;
		if (color.equals("Skin32")) return 32;
		if (color.equals("Skin33")) return 33;
		if (color.equals("Skin34")) return 34;
		if (color.equals("Skin35")) return 35;
		if (color.equals("Skin36")) return 36;
		if (color.equals("Skin37")) return 37;
		if (color.equals("Skin38")) return 38;
		if (color.equals("Skin39")) return 39;
		if (color.equals("Skin40")) return 40;
		if (color.equals("Skin41")) return 41;
		if (color.equals("Skin42")) return 42;
		if (color.equals("Skin43")) return 43;
		if (color.equals("Skin44")) return 44;
		if (color.equals("Skin45")) return 45;
		if (color.equals("Skin46")) return 46;
		if (color.equals("Skin47")) return 47;
		if (color.equals("Skin48")) return 48;
		if (color.equals("Skin49")) return 49;
		if (color.equals("Skin50")) return 50;
		if (color.equals("Empty")) return 100;
		if (color.equals("Full")) return 101;
		return -1;
	}

	public void dropCartAsItem(boolean isCreative) {
		if (!isCreative && !itemdropped) {
			itemdropped=true;
			for (ItemStack item : getItemsDropped()) {
				if (item.getItem() instanceof ItemRollingStock){
					ItemStack stack = ItemRollingStock.setPersistentData(item,this,this.getUniqueTrainID(),trainCreator, trainOwner, getColor(), trainNote);
					exportTrustedListToNBT(stack != null ? stack.getTagCompound() : null);
					if (cargoManager!= null && cargoManager.getSelectedCargo() != 0 && stack != null) {
						stack.getTagCompound().setInteger("cargoSelection", cargoManager.getSelectedCargo());
					}
					entityDropItem(stack!=null?stack:item,0);
				} else {
					setUniqueIDToItem(item);
					entityDropItem(item, 0);
				}
			}
		}
	}

	protected void setUniqueIDToItem(ItemStack stack) {
		NBTTagCompound var3 = stack.getTagCompound();
		if (var3 == null) {
			var3 = new NBTTagCompound();
			stack.setTagCompound(var3);
		}
		if (this.uniqueID != -1) stack.getTagCompound().setInteger("uniqueID", this.uniqueID);
		if (this.trainCreator != null && this.trainCreator.length() > 0) stack.getTagCompound().setString("trainCreator", this.trainCreator);
		if (this.getColor() != -1) stack.getTagCompound().setInteger("trainColor", this.getColor());
	}

	protected void setDefaultMass(double def) {
		this.mass = def;
		this.defaultMass = def;
	}

	protected double getDefaultMass() {
		return defaultMass;
	}

	/**
	 * Lock packet
	 */
	public boolean getTrainLockedFromPacket() {
		return locked;
	}

	/**
	 * Lock packet
	 */
	public void setTrainLockedFromPacket(boolean set) {
		// System.out.println(worldObj.isRemote + " " + set);
		locked = set;
	}


	/** Locking for passengers, flat, caboose, jukebox,workcart */
	protected boolean lockThisCart(ItemStack itemstack, EntityPlayer entityplayer) {
		if (itemstack != null && (itemstack.getItem() instanceof ItemWrench || itemstack.getItem() instanceof ItemAdminBook)) {
			if (entityplayer.getDisplayName().equals(this.trainOwner) || entityplayer.getGameProfile().getName().equals(this.trainOwner)
					|| this.trainOwner.equals("") || entityplayer.canCommandSenderUseCommand(2, "")) {
				if (locked) {
					locked = false;
					if(worldObj.isRemote) {
						entityplayer.addChatMessage(new ChatComponentText("Unlocked"));
					}
				}
				else {
					locked = true;
					if(worldObj.isRemote) {
						entityplayer.addChatMessage(new ChatComponentText("Locked"));
					}
				}
			}
			else if (worldObj.isRemote) {
				entityplayer.addChatMessage(new ChatComponentText("You are not the owner!"));
			}
			return true;
		}
		return false;
	}

	protected boolean canBeRiddenWhileLocked(AbstractTrains train) {
		return (train instanceof Locomotive) || (train instanceof IPassenger) || (train instanceof AbstractWorkCart);
	}

	protected boolean canBeDestroyedByPlayer(DamageSource damagesource) {
		if (this.getTrainLockedFromPacket()) {
			if (damagesource.getEntity() instanceof EntityPlayer) {
				if ((damagesource.getEntity() instanceof EntityPlayerMP) &&
						((EntityPlayerMP)damagesource.getEntity()).canCommandSenderUseCommand(2, "") &&
						((EntityPlayer) damagesource.getEntity()).inventory.getCurrentItem() != null &&
						((EntityPlayer) damagesource.getEntity()).inventory.getCurrentItem().getItem() instanceof ItemWrench) {

					((EntityPlayer) damagesource.getEntity()).addChatMessage(new ChatComponentText("Removing the train using OP permission"));
					return false;
				}
				else if (!((EntityPlayer) damagesource.getEntity()).getDisplayName().equalsIgnoreCase(this.trainOwner) && !(this.isPlayerTrustedToBreak(((EntityPlayerMP) damagesource.getEntity()).getDisplayName()))) {
					((EntityPlayer) damagesource.getEntity()).addChatMessage(new ChatComponentText("You are not the owner!"));
					return true;
				}
			}
			else if (!damagesource.isProjectile()) {
				return true;
			}
		}
		return false;
	}

	/** Railcraft routing integration */
	@Override
	public boolean doesCartMatchFilter(ItemStack stack, EntityMinecart cart) {
		if (stack == null || cart == null) { return false; }
		ItemStack cartItem = cart.getCartItem();
		return cartItem.getItem() == stack.getItem();
	}

	@Override
	public String getDestination() {
		if (destination == null) return "";
		return destination;
	}

	/**
	 * Only locomotives can receive a destination from a track. It is then
	 * transmitted to attached carts
	 */
	@Override
	public boolean setDestination(ItemStack ticket) {
		return false;
	}

	public static String getTicketDestination(ItemStack ticket) {
		if ((ticket == null)) { return ""; }
		NBTTagCompound nbt = ticket.getTagCompound();
		if (nbt == null) { return ""; }
		return nbt.getString("dest");
	}


	@Override
	public String getCommandSenderName(){
		String s = EntityList.getEntityString(this);
		if (s == null) {
			s = "generic";
		}

		return StatCollector.translateToLocal("entity." + s + ".name");
	}



	public void setTicket(ForgeChunkManager.Ticket ticket){
		this.chunkTicket = ticket;
	}
	public ForgeChunkManager.Ticket getTicket(){
		return this.chunkTicket;
	}

	public void requestTicket() {
		ForgeChunkManager.Ticket chunkTicket = ForgeChunkManager.requestTicket(Traincraft.instance, worldObj , ForgeChunkManager.Type.ENTITY);
		if(chunkTicket != null) {
			chunkTicket.setChunkListDepth(25);
			chunkTicket.bindEntity(this);
			this.setTicket(chunkTicket);
		}
	}

	public String getPersistentUUID() {
		if(getEntityData().hasKey("puuid")) {
			return getEntityData().getString("puuid");
		} else {
			System.out.println("setting UUID");
			getEntityData().setString("puuid", getUniqueID().toString());
			return this.getUniqueID().toString();
		}
	}

	/**
	 * @return Returns String ArrayList of trusted players' usernames.
	 */
	public List<TrustedPlayer> getTrustedList() {
		return trustedList;
	}
	public void setTrustedList(List<TrustedPlayer> trustedList) { this.trustedList = trustedList; }

	/**
	 * <p>Returns whether or not a player is trusted to a piece of rolling stock.</p>
	 * @param displayName Case-insensitive display name of player.
	 * @return True if the player is trusted, false if the player is not trusted.
	 */
	public boolean isPlayerTrusted(String displayName) {
		for (TrustedPlayer trustedPlayer : this.getTrustedList()) {
			if (trustedPlayer.getDisplayName().equalsIgnoreCase(displayName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * <p>Returns whether or not a player is trusted to break a piece of rolling stock.</p>
	 * @param displayName Case-insensitive display name of player.
	 * @return True if player has break access, false if player does not have break access.
	 */
	public boolean isPlayerTrustedToBreak(String displayName) {
		for (TrustedPlayer trustedPlayer : this.getTrustedList()) {
			if (trustedPlayer.getDisplayName().equalsIgnoreCase(displayName)) {
				return trustedPlayer.hasBreakAccess();
			}
		}
		return false;
	}

	/**
	 * <p>Export trusted players to NBT tag for data saving.</p>
	 * @param nbttagcompound NBT tag into which to write trusted list.
	 */
	public void exportTrustedListToNBT(NBTTagCompound nbttagcompound) {
		if (!trustedList.isEmpty()) {
			NBTTagList trustedList = new NBTTagList();
			for (TrustedPlayer trustedPlayer : this.trustedList) {
				NBTTagCompound trustedPlayerTag = new NBTTagCompound();
				trustedPlayerTag.setString("playerName", trustedPlayer.getDisplayName());
				trustedPlayerTag.setBoolean("breakAccess", trustedPlayer.hasBreakAccess());
				trustedList.appendTag(trustedPlayerTag);
			}
			nbttagcompound.setTag("trustedList", trustedList);
			nbttagcompound.setString("trustedListPreviousOwner", getTrainOwner());
		}
	}

	/**
	 * <p>Import a trusted player list from a given NBT tag.</p>
	 * @param nbttagcompound NBT tag from which to import trusted list.
	 */
	public void importTrustedListFromNBT(NBTTagCompound nbttagcompound) {
		if (nbttagcompound.hasKey("trustedList")) {
			NBTTagList trustedList = nbttagcompound.getTagList("trustedList", Constants.NBT.TAG_COMPOUND);
			this.trustedList.clear();
			for (int i = 0; i < trustedList.tagCount(); i++) {
				if (!trustedList.getCompoundTagAt(i).getString("playerName").equalsIgnoreCase(trainOwner)) // Check to ensure we're not adding the current owner to the trusted list...
					this.trustedList.add(new TrustedPlayer(trustedList.getCompoundTagAt(i).getString("playerName"), trustedList.getCompoundTagAt(i).getBoolean("breakAccess")));
			}
			if (nbttagcompound.hasKey("trustedListPreviousOwner")) { // If the previous owner is not the one who placed down the piece of rolling stock...
				if (!nbttagcompound.getString("trustedListPreviousOwner").equalsIgnoreCase(trainOwner)) {
					getTrustedList().add(new TrustedPlayer(nbttagcompound.getString("trustedListPreviousOwner"), true));
				}
			}
		}
	}

	/**
	 * @author 02skaplan
	 * @return CargoManager for entity if entity supports custom cargo, else null.
	 */
	public CargoManager getCargoManager() {
		return cargoManager;
	}

	/**
	 * @author 02skaplan
	 * <p>Call after super and init in a Class<\? extends EntityRollingStock\>'s constructor with a new CargoManager containing
	 * all cargo options for a given entity.</p>
	 */
	public void setCargoManager(CargoManager cargoManager) {
		this.cargoManager = cargoManager;
	}

	public RenderEnum getRenderSpec() {
		return renderSpec;
	}

}
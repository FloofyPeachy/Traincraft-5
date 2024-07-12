package train.common.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import train.common.Traincraft;
import train.common.core.handlers.ConfigHandler;
import train.common.core.network.PacketKeyPress;
import train.common.library.EnumSounds;
import train.common.library.GuiIDs;
import train.common.library.Info;

import java.util.List;

public abstract class AbstractControlCar extends EntityRollingStock implements IInventory, IPassenger, IRollingStockLightControls {

    public Locomotive connectedLocomotive;
    public int whistleDelay;

    //region inventory
    public int inventorySize;
    private int slotsFilled=0;
    protected ItemStack controlCarInventory[];
    public int numCargoSlots;
    public int numCargoSlots1;
    public int numCargoSlots2;
    //endregion inventory

    private boolean forwardPressed = false;
    private boolean backwardPressed = false;
    private boolean brakePressed = false;

    public boolean isLightsEnabled = false;
    public boolean isBeaconEnabled = false;
    public byte beaconCycleIndex = 0;
    public byte ditchLightMode = 0;
    
    public AbstractControlCar(World world)
    {
        super(world);
        numCargoSlots = 3;
        numCargoSlots1 = 3;
        numCargoSlots2 = 3;
        inventorySize = numCargoSlots + numCargoSlots2 + numCargoSlots1 + 1;
        controlCarInventory = new ItemStack[inventorySize];
        dataWatcher.addObject(28, lightingDetailsJSON());
        if (connectedLocomotive == null) {
            dataWatcher.addObject(29, 0);
        } else {
            dataWatcher.addObject(29, connectedLocomotive.getEntityId());
        }


    }

    public AbstractControlCar(World world, double d, double d1, double d2) {
        super(world);
        
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setString("lightingDetailsJSON", lightingDetailsJSON());
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound ntc)
    {
        super.readEntityFromNBT(ntc);
        JsonObject lightingDetailsJSONObject;
        try {
            lightingDetailsJSONObject = new JsonParser().parse(ntc.getString("lightingDetailsJSON")).getAsJsonObject();
        }
        catch (Exception e)
        {
            lightingDetailsJSONObject = lightingDetailsAsJSON();
        }

        isLightsEnabled = lightingDetailsJSONObject.get("isLightsEnabled").getAsBoolean();
        isBeaconEnabled = lightingDetailsJSONObject.get("isBeaconEnabled").getAsBoolean();
        ditchLightMode = lightingDetailsJSONObject.get("ditchLightMode").getAsByte();
        beaconCycleIndex = lightingDetailsJSONObject.get("beaconCycleIndex").getAsByte();

        dataWatcher.updateObject(28, lightingDetailsJSON());
    }


    private void cycleBeaconIndex()
    {
        if (isBeaconEnabled && ticksExisted % 5 == 0)
        {
            beaconCycleIndex++;
            if (beaconCycleIndex == 4)
            {
                beaconCycleIndex = 0;
            }
        }
    }

    @Override
    public void onUpdate()
    {
        cycleBeaconIndex();

        if (worldObj.isRemote == false)
        {
            //Server side stuff.

            if (cartLinked1 != null) {
                if ((cartLinked1).train != null && (cartLinked1).train.getTrains().size() != 0) {
                    for (int j1 = 0; j1 < (cartLinked1).train.getTrains().size(); j1++) {
                        EntityRollingStock daRollingStock = (cartLinked1).train.getTrains().get(j1);
                        if (daRollingStock instanceof Locomotive) {
                            connectedLocomotive = (Locomotive) daRollingStock;
                            break;
                        }
                    }
                }

            }
            if (connectedLocomotive == null) {
                dataWatcher.updateObject(29, 0);
            } else {
                dataWatcher.updateObject(29, connectedLocomotive.getEntityId());
            }


        } else
            {
            //Client side stuff.
        }
        handleTrainMovement();
        if (whistleDelay > 0) {
            whistleDelay--;
        }

        super.onUpdate();
        if (!worldObj.isRemote)
        {
            dataWatcher.updateObject(28, lightingDetailsJSON());
        }
    }


    public void soundHorn() {
        for (EnumSounds sounds : EnumSounds.values()) {
            if (sounds.getEntityClass() != null && sounds.getEntityClass().equals(this.getClass()) && whistleDelay == 0) {
                worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getHornString(), sounds.getHornVolume(), 1.0F);
                whistleDelay = 65;
            }
        }
        List entities = worldObj.getEntitiesWithinAABB(EntityAnimal.class, AxisAlignedBB.getBoundingBox(
                this.posX-20,this.posY-5,this.posZ-20,
                this.posX+20,this.posY+5,this.posZ+20));

        for(Object e : entities) {
            if(e instanceof EntityAnimal) {
                ((EntityAnimal) e).setTarget(this);
                ((EntityAnimal) e).getNavigator().setPath(null, 0);
            }
        }
    }
    @Override
    public void updateRiderPosition() {
        if(riddenByEntity!=null) {
            riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset() + 0.2, posZ);
        }
    }

    @Override
    public void pressKey(int i) {
        if (i == 7 && riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
        {
            System.out.println("UwU");
            ((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.CONTROL_CAR, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);
        }
    }

    @Override
    public void setDead() {
        super.setDead();
        isDead = true;
    }

    //I'm moving this to a separate function because it's really, really thick, and I want to try to make things look neater.
    public void handleTrainMovement() {
        if (worldObj.isRemote) {
            if (ticksExisted % 2 == 0 && !Minecraft.getMinecraft().ingameGUI.getChatGUI().getChatOpen()) {

                if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                        && !forwardPressed) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(4));
                    forwardPressed = true;
                } else if (!Keyboard
                        .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindForward.getKeyCode())
                        && forwardPressed) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(13));
                    forwardPressed = false;
                }
                if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                        && !backwardPressed) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(5));
                    backwardPressed = true;
                } else if (!Keyboard
                        .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindBack.getKeyCode())
                        && backwardPressed) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(14));
                    backwardPressed = false;
                }
                if (Keyboard.isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                        && !brakePressed) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(12));
                    brakePressed = true;
                } else if (!Keyboard
                        .isKeyDown(FMLClientHandler.instance().getClient().gameSettings.keyBindJump.getKeyCode())
                        && brakePressed) {
                    Traincraft.keyChannel.sendToServer(new PacketKeyPress(15));
                    brakePressed = false;
                }
            }
        } else {
            //Server stuff.
            if (connectedLocomotive != null) {
                if (forwardPressed || backwardPressed) {

                    if (connectedLocomotive.getFuel() > 0 && connectedLocomotive.isLocoTurnedOn() && rand.nextInt(4) == 0 && !worldObj.isRemote) {
                        if (this.getTrainLockedFromPacket() && !((EntityPlayer) this.riddenByEntity).getDisplayName()
                                .toLowerCase().equals(this.getTrainOwner().toLowerCase())) {
                            return;
                        }
                        if (riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
                            int dir = MathHelper
                                    .floor_double((((EntityPlayer) riddenByEntity).rotationYaw * 4F) / 360F + 0.5D) & 3;
                            System.out.println(dir);
                            if (dir == 2) {
                                if (forwardPressed) {
                                    connectedLocomotive.motionZ -= 0.0075 * this.accelerate;
                                    System.out.println("A");
                                } else {
                                    connectedLocomotive.motionZ += 0.0075 * this.accelerate;
                                    System.out.println("B");
                                }
                            } else if (dir == 0) {
                                if (forwardPressed) {
                                    connectedLocomotive.motionZ += 0.0075 * this.accelerate;
                                    System.out.println("C");
                                } else {
                                    connectedLocomotive.motionZ -= 0.0075 * this.accelerate;
                                    System.out.println("D");
                                }
                            } else if (dir == 1) {
                                if (forwardPressed) {
                                    connectedLocomotive.motionX -= 0.0075 * this.accelerate;
                                    System.out.println("E");
                                } else {
                                    connectedLocomotive.motionX += 0.0075 * this.accelerate;
                                    System.out.println("F");
                                }
                            } else {
                                if (forwardPressed) {
                                    connectedLocomotive.motionX += 0.0075 * this.accelerate;
                                    System.out.println("G");
                                } else {
                                    connectedLocomotive.motionX -= 0.0075 * this.accelerate;
                                    System.out.println("H");
                                }
                            }
                        }
                    }
                } else if (brakePressed) {
                    connectedLocomotive.motionX *= brake;
                    connectedLocomotive.motionZ *= brake;
                }
            }
        }

    }

    @Override
    public void keyHandlerFromPacket(int i) {
        if (this.getTrainLockedFromPacket()) {
            if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
                    && !((EntityPlayer) this.riddenByEntity).getDisplayName().toLowerCase()
                    .equals(this.getTrainOwner().toLowerCase())) {
                return;
            }
        }
        pressKey(i);
        if (i == 8 && ConfigHandler.SOUNDS) {
            soundHorn();
        }
        if (i == 4) {
            forwardPressed = true;
        }
        if (i == 5) {
            backwardPressed = true;
        }
        if (i == 12) {
            brakePressed = true;
        }
        if (i == 13) {
            forwardPressed = false;
        }
        if (i == 14) {
            backwardPressed = false;
        }
        if (i == 15) {
            brakePressed = false;
        }
    }

    public String lightingDetailsJSON()
    {
        JsonObject lightingDetailsJSON = new JsonObject();
        lightingDetailsJSON.addProperty("isLightsEnabled", isLightsEnabled);
        lightingDetailsJSON.addProperty("isBeaconEnabled", isBeaconEnabled);
        lightingDetailsJSON.addProperty("beaconCycleIndex", beaconCycleIndex);
        lightingDetailsJSON.addProperty("ditchLightMode", ditchLightMode);
        return lightingDetailsJSON.toString();
    }

    public JsonObject lightingDetailsAsJSON()
    {
        JsonObject lightingDetailsJSON = new JsonObject();
        lightingDetailsJSON.addProperty("isLightsEnabled", isLightsEnabled);
        lightingDetailsJSON.addProperty("isBeaconEnabled", isBeaconEnabled);
        lightingDetailsJSON.addProperty("beaconCycleIndex", beaconCycleIndex);
        lightingDetailsJSON.addProperty("ditchLightMode", ditchLightMode);
        return lightingDetailsJSON;
    }

    /**
     *
     * @param isLightsOn set 0 if lights is false, 1 if true
     */
    public void setPacketLights(boolean isLightsOn)
    {
        isLightsEnabled = isLightsOn;
    }

    /**
     *
     * @param isBeaconOn set 0 if beacon is false, 1 if true
     */
    public void setPacketBeacon(boolean isBeaconOn)
    {
        isBeaconEnabled = isBeaconOn;
    }

    /**Sets the Ditch light mode
     *
     * @param ditchLightMode set 0 for off,
     */
    public void setPacketDitchLightsMode(byte ditchLightMode)
    {
        this.ditchLightMode = ditchLightMode;
    }

    public int getLocomotiveBeingControlledEntityID()
    {
        return dataWatcher.getWatchableObjectInt(29);
    }

    public boolean isLightsEnabled()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get("isLightsEnabled").getAsBoolean();
    }

    public boolean isBeaconEnabled()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get("isBeaconEnabled").getAsBoolean();
    }

    public byte getBeaconCycleIndex()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get("beaconCycleIndex").getAsByte();
    }

    public boolean isDitchLightsEnabled()
    {
        return AsJsonObject(dataWatcher.getWatchableObjectString(28)).get("ditchLightMode").getAsByte() > 0;
    }

    private JsonObject AsJsonObject(String string)
    {
        return new JsonParser().parse(string).getAsJsonObject();
    }



    //region Implement IInventory
    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public void markDirty() { }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public ItemStack[] getInventory() {
        return controlCarInventory;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return controlCarInventory[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.controlCarInventory[par1] != null) {
            ItemStack var2 = this.controlCarInventory[par1];
            this.controlCarInventory[par1] = null;
            return var2;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (controlCarInventory[i] != null) {
            if (controlCarInventory[i].stackSize <= j) {
                ItemStack itemstack = controlCarInventory[i];
                controlCarInventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = controlCarInventory[i].splitStack(j);
            if (controlCarInventory[i].stackSize == 0) {
                controlCarInventory[i] = null;
            }
            return itemstack1;

        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        controlCarInventory[i] = itemstack;
        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    //endregion Implement IInventory
}

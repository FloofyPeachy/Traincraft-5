package train.common.api;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import train.common.Traincraft;
import train.common.core.handlers.ItemHandler;
import train.common.entity.CargoManager;
import train.common.enums.CargoItemFilter;
import train.common.library.GuiIDs;

/**
 * Your Standard Basic Freight Car
 */
public abstract class AbstractStandardFreightCar extends Freight
{
    private int freightInventorySize;

    protected CargoItemFilter cargoFilterCategory = CargoItemFilter.DEFAULT;

    public CargoItemFilter GetCargoFilterCategory()
    {
        return cargoFilterCategory;
    }

    public AbstractStandardFreightCar(World world)
    {
        super(world);
        // Freight Car Setup
        freightInventorySize = trainSpec.getCargoCapacity();
        cargoItems = new ItemStack[freightInventorySize];
        setupTextureDescription();
        setCargoManager(setupCargoManager());
    }

    public abstract CargoManager setupCargoManager();

    public abstract void setupTextureDescription();

    @Override
    protected final void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < cargoItems.length; i++) {
            if (cargoItems[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                cargoItems[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

   @Override
   protected final void readEntityFromNBT(NBTTagCompound nbttagcompound) {
       super.readEntityFromNBT(nbttagcompound);

       NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
       cargoItems = new ItemStack[getSizeInventory()];
       for (int i = 0; i < nbttaglist.tagCount(); i++) {
           NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
           int j = nbttagcompound1.getByte("Slot") & 0xff;
           if (j >= 0 && j < cargoItems.length) {
               cargoItems[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
           }
       }
   }

    @Override
    public final int getSizeInventory() {
        return freightInventorySize;
    }

    @Override
    public final void setDead() {
        super.setDead();
        isDead = true;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer)
    {
        if ((super.interactFirst(entityplayer)))
        {
            return false;
        }

        boolean isTrustedPlayer = isPlayerTrusted(playerEntity.getDisplayName());
        if (this.getTrainLockedFromPacket() && !playerEntity.getDisplayName().equalsIgnoreCase(this.getTransportOwner()) && !isTrustedPlayer)
        {
            if (!worldObj.isRemote)
            {
                if (entityplayer.isSneaking() || this instanceof AbstractPassengerCombineCar == false)
                {
                    entityplayer.addChatMessage(new ChatComponentText("Train is locked by " + this.getTransportOwner() + "."));
                }
            }
            return true;
        }

        if (this instanceof AbstractPassengerCombineCar == false || this instanceof AbstractPassengerCombineCar && entityplayer.isSneaking())
        {
            entityplayer.openGui(Traincraft.instance, GuiIDs.FREIGHT, worldObj, this.getEntityId(), -1, (int) this.posZ);
            return true;
        }

        return false;
    }

    @Override
    public final boolean isStorageCart()
    {
        return true;
    }

    @Override
    public final boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return ItemHandler.handleFreight(this, itemstack);
    }

    public abstract String getInventoryName();

    public abstract float getOptimalDistance(EntityMinecart cart);
}



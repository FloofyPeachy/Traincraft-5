package com.jcirmodelsquad.tcjcir.vehicles.rollingstock;

import com.jcirmodelsquad.tcjcir.models.loads.Modelpipeload1;
import com.jcirmodelsquad.tcjcir.models.loads.Modelroadrailer_trailer_53;
import com.jcirmodelsquad.tcjcir.models.loads.Modeltoastertiedown;
import com.jcirmodelsquad.tcjcir.models.trains.ModelAEM7;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import train.common.Traincraft;
import train.common.api.Freight;
import train.common.entity.CargoManager;
import train.common.entity.CargoSpecification;
import train.common.library.GuiIDs;

public class Gunderson89ftFlat extends Freight implements IInventory {
    public int freightInventorySize;
    public int numFreightSlots;
    public Gunderson89ftFlat(World world) {
        super(world);
        initFreightCart();
        textureDescriptionMap.put(0, "TrailerTrain Cooperation");
        textureDescriptionMap.put(1, "NEP");
        textureDescriptionMap.put(2, "NEP");
        setCargoManager(new CargoManager(new CargoSpecification[][] {
        /*{ new CargoSpecification(Modelroadrailer_trailer_53.class,
                "loads/roadrailer_53_generic", "53 Ft Dryvan", 0, 2.5, 0) },
        { new CargoSpecification(Modelroadrailer_trailer_53.class,
                "roadrailer_53_fernrock", "53 Ft Refrididgerator (Fernrock Foods)", 0, 2.5, 0) },*/
        { new CargoSpecification(ModelAEM7.class, "trains/AEM-7_Grey", "highly important cargo", 0, 2.58, 0),
              new CargoSpecification(Modeltoastertiedown.class, "loads/AEM_tiedown", "highly important cargo", 0, 2.58, 0) },
        }));
    }

    public Gunderson89ftFlat(World world, double d, double d1, double d2){
        this(world);
        setPosition(d, d1 + yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
        initFreightCart();
    }

    public void initFreightCart() {
        numFreightSlots = 6;
        freightInventorySize = trainSpec.getCargoCapacity();
        cargoItems = new ItemStack[freightInventorySize];
    }
    @Override
    public void setDead() {
        super.setDead();
        isDead = true;
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
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
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
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
    public String getInventoryName() {
        return "Gunderson 89' Lowbody Flat Car";
    }

    @Override
    public int getSizeInventory() {
        return freightInventorySize;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        if ((super.interactFirst(entityplayer))) {
            return true;
        }
        entityplayer.openGui(Traincraft.instance, GuiIDs.FREIGHT, worldObj, this.getEntityId(), -1, (int) this.posZ);
        return true;
    }

    @Override
    public boolean isStorageCart() {
        return true;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 4.4F;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }
}

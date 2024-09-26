package com.jcirmodelsquad.tcjcir.vehicles.rollingstock;

import com.jcirmodelsquad.tcjcir.models.loads.*;
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

public class gsco52millgon extends Freight implements IInventory {
    public int freightInventorySize;
    public int numFreightSlots;
    public gsco52millgon(World world) {
        super(world);
        initFreightCart();
        textureDescriptionMap.put(0, "BN");
        textureDescriptionMap.put(1, "CB&Q (Green)");
        textureDescriptionMap.put(2, "CB&Q (Red)");
        textureDescriptionMap.put(3, "CB&Q (Brown)");
        textureDescriptionMap.put(4, "AA");
        textureDescriptionMap.put(5, "Generic Black");
        textureDescriptionMap.put(6, "Generic Boxcar Red");
        textureDescriptionMap.put(7, "NYC");
        textureDescriptionMap.put(8, "Erie");
        textureDescriptionMap.put(9, "EL");
        textureDescriptionMap.put(10, "SLSF (Brown)");
        textureDescriptionMap.put(11, "SLSF (Black)");
        textureDescriptionMap.put(12, "RI");

        setCargoManager(new CargoManager(new CargoSpecification[][] {
                { new CargoSpecification(Modelgondola_load_flat_aggregates_medium.class, "loads/gondola_load_flat_aggregates_medium_dort",
                        "Aggregate - Dirt", 0, 2.475, 0)},
                { new CargoSpecification(Modelgondola_load_flat_aggregates_medium.class, "loads/gondola_load_flat_aggregates_medium_peagravel",
                        "Aggregate - Peagravel", 0, 2.475, 0)},
                { new CargoSpecification(Modelgondola_load_flat_aggregates_medium.class, "loads/gondola_load_flat_aggregates_medium_scrap1",
                        "Scrapmetal A", 0, 2.475, 0)},
                { new CargoSpecification(Modelgondola_load_flat_aggregates_medium.class, "loads/gondola_load_flat_aggregates_medium_scrap2",
                        "Scrapmetal B", 0, 2.475, 0)},
                { new CargoSpecification(Modelgondola_load_flat_aggregates_medium.class, "loads/gondola_load_flat_cullet2_medium",
                        "Cullet / Scrap Glass", 0, 2.475, 0)},
                { new CargoSpecification(Modelrebar_bundle.class, "loads/rebar_bundle2",
                        "Rebar Bundle", 0, 2.5, 0)},
                { new CargoSpecification(Modelcoil_load.class, "loads/coils",
                        "Metal Coils", 0, 2.5, 0)},
        }));
    }

    public gsco52millgon(World world, double d, double d1, double d2){
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
        numFreightSlots = 5;
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
        return "GSCO 52' Drop-End Mill Gondola";
    }//GSCO 67' Mill Gondola

    @Override
    public int getSizeInventory() {
        return freightInventorySize;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        if ((super.interactFirst(entityplayer))) {
            return false;
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
        return 3.3F;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return true;
    }
}
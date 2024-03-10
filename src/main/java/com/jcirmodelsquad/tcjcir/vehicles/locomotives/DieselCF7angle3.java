package com.jcirmodelsquad.tcjcir.vehicles.locomotives;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import train.common.Traincraft;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.library.EnumTrains;
import train.common.library.GuiIDs;


public class DieselCF7angle3 extends DieselTrain {
    public DieselCF7angle3(World world) {
        super(world, EnumTrains.CF7angle3.getTankCapacity(), LiquidManager.dieselFilter());
        initLoco();
        textureDescriptionMap.put(0, "Avanste Northeastern");
        textureDescriptionMap.put(1, "Carbondale & Pine Valley");
        textureDescriptionMap.put(2, "FNCC (KIT-L, CEE Built, Dual Canon)");
        textureDescriptionMap.put(3, "FNCC (KIT-L, Ex ATSF, Dual Canon)");
        textureDescriptionMap.put(4, "KCRC 2583");
        textureDescriptionMap.put(5, "Atlas & Red Sands Railroad");
        textureDescriptionMap.put(6, "Seattle & North Coast 102 (Post 1983)");
        textureDescriptionMap.put(7, "Seattle & North Coast 103 (Post 1983)");
        textureDescriptionMap.put(8, "Port of Latona Terminal Railroad");
        textureDescriptionMap.put(9, "Fox Union Rail Resources (FURRX)");
        textureDescriptionMap.put(10, "Washaska & Old Fox RR Co 19");
        textureDescriptionMap.put(11, "Washaska & Old Fox RR Co 20");
        textureDescriptionMap.put(12, "TIR 1538");
        textureDescriptionMap.put(13, "TIR 1539");
        textureDescriptionMap.put(14, "Great Lakes & Northern Territories");
        textureDescriptionMap.put(15, "WFR (EX CSWR)");
        textureDescriptionMap.put(16, "Blandsville & Blankerston");
        textureDescriptionMap.put(17, "Morristown Tenneva & Southern");
        textureDescriptionMap.put(18, "Morristown Tenneva & Southern (Ex ANE)");
        textureDescriptionMap.put(19, "Morristown Tenneva & Southern Spirit of 1776");
        textureDescriptionMap.put(20, "");
        textureDescriptionMap.put(21, "");
        textureDescriptionMap.put(22, "");
        textureDescriptionMap.put(23, "");
        textureDescriptionMap.put(24, "");
        textureDescriptionMap.put(25, "");
        textureDescriptionMap.put(26, "");
        textureDescriptionMap.put(27, "");
        textureDescriptionMap.put(28, "");
        textureDescriptionMap.put(29, "");
        textureDescriptionMap.put(30, "");
        textureDescriptionMap.put(31, "");
        textureDescriptionMap.put(32, "");
        textureDescriptionMap.put(33, "");
        textureDescriptionMap.put(34, "");
        textureDescriptionMap.put(35, "");
        textureDescriptionMap.put(36, "");
        textureDescriptionMap.put(37, "");
        textureDescriptionMap.put(38, "");
    }
    public DieselCF7angle3(World world, double d, double d1, double d2){
        this(world);
        setPosition(d, d1 + yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    public void initLoco() {
        fuelTrain = 0;
        locoInvent = new ItemStack[inventorySize];
    }

    @Override
    public void updateRiderPosition() {
        if (riddenByEntity == null) {return;}
        double pitchRads = this.anglePitchClient * Math.PI / 180.0D;
        double distance = 2.5; //how far forward/backwards on the entity you ride; forward > 0; backwards < 0;
        double distanceLR = -0.35; //how far left/right on the entity you ride; left > 0; right < 0;
        double yOffset = 0.2;
        float rotationCos1 = (float) Math.cos(Math.toRadians(this.renderYaw + 90));
        float rotationSin1 = (float) Math.sin(Math.toRadians((this.renderYaw + 90)));
        float rotationCosLR1 = (float) Math.cos(Math.toRadians(this.renderYaw));
        float rotationSinLR1 = (float) Math.sin(Math.toRadians((this.renderYaw)));
        if(side.isServer()){
            rotationCos1 = (float) Math.cos(Math.toRadians(this.serverRealRotation + 90));
            rotationSin1 = (float) Math.sin(Math.toRadians((this.serverRealRotation + 90)));
            rotationCosLR1 = (float) Math.cos(Math.toRadians(this.serverRealRotation));
            rotationSinLR1 = (float) Math.sin(Math.toRadians((this.serverRealRotation)));
            anglePitchClient = serverRealPitch*60;
        }
        float pitch = (float) (posY + ((Math.tan(pitchRads) * distance) + getMountedYOffset())
                + riddenByEntity.getYOffset() + yOffset);
        float pitch1 = (float) (posY + getMountedYOffset() + riddenByEntity.getYOffset() + yOffset);
        double bogieX1 = (this.posX + (rotationCos1 * distance) + (rotationCosLR1 * distanceLR));
        double bogieZ1 = (this.posZ + (rotationSin1* distance) + (rotationSinLR1 * distanceLR));
        //System.out.println(rotationCos1+" "+rotationSin1);
        if(anglePitchClient>20 && rotationCos1 == 1){
            bogieX1-=pitchRads*2;
            pitch-=pitchRads*1.2;
        }
        if(anglePitchClient>20 && rotationSin1 == 1){
            bogieZ1-=pitchRads*2 + 1;
            pitch-=pitchRads*1.2;
        }
        if (pitchRads == 0.0) {
            riddenByEntity.setPosition(bogieX1, pitch1, bogieZ1 -0.0);
        }
        if (pitchRads > -1.01 && pitchRads < 1.01) {
            riddenByEntity.setPosition(bogieX1, pitch, bogieZ1 +0.0);
        }
    }
    @Override
    public void setDead() {
        super.setDead();
        isDead = true;
    }



    @Override
    public void pressKey(int i) {
        if (i == 7 && riddenByEntity != null && riddenByEntity instanceof EntityPlayer) {
            ((EntityPlayer) riddenByEntity).openGui(Traincraft.instance, GuiIDs.LOCO, worldObj, (int) this.posX, (int) this.posY, (int) this.posZ);

        }
    }

    @Override
    public void onUpdate() {
        checkInvent(locoInvent[0]);
        super.onUpdate();
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
        super.writeEntityToNBT(nbttagcompound);

        nbttagcompound.setShort("fuelTrain", (short) fuelTrain);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < locoInvent.length; i++) {
            if (locoInvent[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte) i);
                locoInvent[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
        super.readEntityFromNBT(nbttagcompound);

        //lampOn = nbttagcompound.getBoolean("lampOn");
        fuelTrain = nbttagcompound.getShort("fuelTrain");
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        locoInvent = new ItemStack[getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if (j >= 0 && j < locoInvent.length) {
                locoInvent[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
    }


    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 1.2F;
    }

    @Override
    public int getSizeInventory() {
        return inventorySize;
    }

    @Override
    public String getInventoryName() {
        return "EMD CF7 Angle Cab";
    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        playerEntity = entityplayer;
        if ((super.interactFirst(entityplayer))) {
            return false;
        }
        if (!worldObj.isRemote) {
            if (riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer) {
                return true;
            }
            entityplayer.mountEntity(this);
        }
        return true;
    }

    @Override
    public boolean canBeAdjusted(EntityMinecart cart) {
        return canBeAdjusted;
    }

}

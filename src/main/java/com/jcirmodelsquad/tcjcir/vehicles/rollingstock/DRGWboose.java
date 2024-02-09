package com.jcirmodelsquad.tcjcir.vehicles.rollingstock;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import train.common.api.EntityRollingStock;
import train.common.api.IPassenger;

public class DRGWboose extends EntityRollingStock implements IPassenger {
    public DRGWboose(World world) {
        super(world);
        textureDescriptionMap.put(0, "idk what this is, fix it dominik");
        textureDescriptionMap.put(1, "idk what this is, fix it dominik");
        textureDescriptionMap.put(2, "idk what this is, fix it dominik");
        textureDescriptionMap.put(3, "idk what this is, fix it dominik");
        textureDescriptionMap.put(4, "idk what this is, fix it dominik");
        textureDescriptionMap.put(5, "idk what this is, fix it dominik");
        textureDescriptionMap.put(6, "idk what this is, fix it dominik");
        textureDescriptionMap.put(7, "idk what this is, fix it dominik");
        textureDescriptionMap.put(8, "idk what this is, fix it dominik");
        textureDescriptionMap.put(9, "idk what this is, fix it dominik");
        textureDescriptionMap.put(10, "idk what this is, fix it dominik");
        textureDescriptionMap.put(11, "idk what this is, fix it dominik");
        textureDescriptionMap.put(12, "idk what this is, fix it dominik");
        textureDescriptionMap.put(13, "DLMR");
        textureDescriptionMap.put(14, "C&PV");

    }

    public DRGWboose(World world, double d, double d1, double d2){
        this(world);
        setPosition(d, d1 + yOffset, d2);
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        prevPosX = d;
        prevPosY = d1;
        prevPosZ = d2;
    }

    /*@Override
    public void updateRiderPosition() {
        if(riddenByEntity!=null) {
            riddenByEntity.setPosition(posX + 0.0f, posY + getMountedYOffset() + riddenByEntity.getYOffset() + -0.15, posZ + 0.0);
        }
    }*/
    /*public void updateRiderPosition() {
        if (riddenByEntity == null) {return;}
        double pitchRads = this.anglePitchClient * Math.PI / 180.0D;
        double distance = -1.1;
        double yOffset = 0.4;
        float rotationCos1 = (float) Math.cos(Math.toRadians(this.renderYaw));
        float rotationSin1 = (float) Math.sin(Math.toRadians((this.renderYaw)));
        if(side.isServer()){
            rotationCos1 = (float) Math.cos(Math.toRadians(this.serverRealRotation));
            rotationSin1 = (float) Math.sin(Math.toRadians((this.serverRealRotation)));
            anglePitchClient = serverRealPitch*60;
        }
        float pitch = (float) (posY + ((Math.tan(pitchRads) * distance) + getMountedYOffset())
                + riddenByEntity.getYOffset() + yOffset);
        float pitch1 = (float) (posY + getMountedYOffset() + riddenByEntity.getYOffset() + yOffset);
        double bogieX1 = (this.posX + (rotationCos1 * distance));
        double bogieZ1 = (this.posZ + (rotationSin1* distance));
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
            riddenByEntity.setPosition(bogieX1, pitch1, bogieZ1);
        }
        if (pitchRads > -1.01 && pitchRads < 1.01) {
            riddenByEntity.setPosition(bogieX1, pitch, bogieZ1);
        }
    }*/

    @Override
    public void setDead() {
        super.setDead();
        isDead = true;
    }

    @Override
    public boolean interactFirst(EntityPlayer entityplayer) {
        playerEntity = entityplayer;
        if ((super.interactFirst(entityplayer))) {
            return false;
        }
        if (!worldObj.isRemote) {
            ItemStack itemstack = entityplayer.inventory.getCurrentItem();
            if(lockThisCart(itemstack, entityplayer))return true;
            if (riddenByEntity != null && (riddenByEntity instanceof EntityPlayer) && riddenByEntity != entityplayer) {
                return true;
            }
            if (!worldObj.isRemote) {
                entityplayer.mountEntity(this);
            }
        }
        return true;
    }

    @Override
    public boolean canBeRidden() {
        return true;
    }

    @Override
    public boolean isStorageCart() {
        return false;
    }

    @Override
    public boolean isPoweredCart() {
        return false;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart) {
        return 2.4F;
    }
}
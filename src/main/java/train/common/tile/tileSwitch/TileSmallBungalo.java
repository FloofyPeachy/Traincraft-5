package train.common.tile.tileSwitch;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileSmallBungalo extends TileMFPBWigWag {

    public float rotation =0;
    public boolean powered =false;
    public boolean flip = true;



    // private net.minecraft.client.audio.ISound bell = new PositionedSound(new ResourceLocation(Info.modID,"bell"))
    /*
    @SideOnly(Side.CLIENT)//be sure sound is only created on client
    private net.minecraft.client.audio.PositionedSound bell = new PositionedSound(new ResourceLocation(Info.modID,"bell")){

        @Override
        public float getXPosF() {return xCoord;}
        @Override
        public float getYPosF() {return yCoord+1;}
        @Override
        public float getZPosF() {return zCoord;}


    };

     */
    @Override
    public void updateEntity() {
        /**
         * Remove any block on top of this wind mill
         */
/*        else {
            if (updateTicks % 20 == 0) {
                if (!this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord)) {
                    Block block = this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord);
                    if (block != null) {
                        EntityItem entityitem = new EntityItem(worldObj, this.xCoord, this.yCoord + 1, this.zCoord, new ItemStack(Item.getItemFromBlock(BlockIDs.CrossingBase.block), 1));
                        float f3 = 0.05F;
                        entityitem.motionX = (float) rand.nextGaussian() * f3;
                        entityitem.motionY = (float) rand.nextGaussian() * f3 + 0.2F;
                        entityitem.motionZ = (float) rand.nextGaussian() * f3;
                        worldObj.spawnEntityInWorld(entityitem);
                    }
                    this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
                }
            }
        }*/
    }


/*    public void setFacing(ForgeDirection face) {

        if (facing != face)
            this.facing = face;
    }*/

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox()
    {
        return AxisAlignedBB.getBoundingBox(xCoord-1, yCoord-1, zCoord-1, xCoord + 2, yCoord + 4, zCoord + 3);
    }

}
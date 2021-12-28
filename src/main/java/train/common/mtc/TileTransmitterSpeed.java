package train.common.mtc;


import cpw.mods.fml.common.network.NetworkRegistry;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import train.common.Traincraft;
import train.common.api.Locomotive;
import train.common.mtc.network.PacketSpeedLimit;
import train.common.mtc.network.PacketMTCStatus;

import java.util.ArrayList;
import java.util.List;

public class TileTransmitterSpeed extends TileEntity implements IPeripheral {
    public boolean isActivated = true;
    //These are arrays. 0 is profile 1, and  is profile 2.

  /*  public int speedLimit = 0;
    public int nextSpeedLimit = 0;
    public double xFromSpeedChange = 0.0;
    public double yFromSpeedChange = 0.0;
    public double zFromSpeedChange = 0.0;*/

    public List<int[]> speedProfiles = new ArrayList<>();
    //For computer mods, profile 1 is always default.
    //(In order:)
    //1st int: speedLimit
    //2nd int: nextSpeedLimit
    //3rd int: xNextSpeedLimit
    //4th int: yNextSpeedLimit
    //5th int: zNextSpeedLimit


    //On redstone:
    //0: Do nothing
    //1: Activate
    //2: Switch to profile
    public int onRedstone;
    public int onRedstoneProfile;

    public AxisAlignedBB boundingBox = null;
	public int trainNumber = 0;

	public boolean hadSentPacket = false;
	public boolean hadSentMTCPacket = false;

    public boolean enforceSpeedLimits = false;

    public TileTransmitterSpeed() {
        speedProfiles.add(new int[]{0, 0, 0, 0, 0});
        speedProfiles.add(new int[]{0, 0, 0, 0, 0});
        speedProfiles.add(new int[]{0, 0, 0, 0, 0});
        speedProfiles.add(new int[]{0, 0, 0, 0, 0});
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.isActivated = nbttagcompound.getBoolean("isActivated");
        /*this.speedLimit = nbttagcompound.getInteger("setSpeed");
        this.nextSpeedLimit = nbttagcompound.getInteger("nextSpeedLimit");
        this.xFromSpeedChange = nbttagcompound.getDouble("xFromSpeedChange");
        this.yFromSpeedChange = nbttagcompound.getDouble("yFromSpeedChange");
        this.zFromSpeedChange = nbttagcompound.getDouble("zFromSpeedChange");*/
        this.enforceSpeedLimits = nbttagcompound.getBoolean("enforceSpeedLimits");

    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setBoolean("isActivated", this.isActivated);
     /*   nbttagcompound.setInteger("setSpeed", this.speedLimit);
        nbttagcompound.setInteger("nextSpeedLimit", this.nextSpeedLimit);
        nbttagcompound.setDouble("xFromSpeedChange", this.xFromSpeedChange);
        nbttagcompound.setDouble("yFromSpeedChange",  this.yFromSpeedChange);
        nbttagcompound.setDouble("zFromSpeedChange",  this.zFromSpeedChange);*/
        nbttagcompound.setBoolean("enforceSpeedLimits", this.enforceSpeedLimits);
    }
    @Override

    public void updateEntity() {

        if (worldObj == null && !worldObj.isRemote) {
            return;
        }
        isActivated = true;

        int activeProfile = 0; //Default.

        if (worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if (onRedstone == 2) {
                activeProfile = onRedstoneProfile;
                System.out.println("powered.");
            } else {
                System.out.println("powered.");
                isActivated = true;
            }

        } else {
            if (onRedstone == 1) {
                isActivated = false;
            }
        }

        if (isActivated) {
            List<Object> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(null, this.getRenderBoundingBox());
            if (list.size() == 0 ) {
                hadSentPacket = false;
                hadSentMTCPacket = false;
                trainNumber = 0;
                //Reset everything.
            }
            if (list.size() > 0) {
                for (Object entity : list) {

                    if (entity instanceof Locomotive) {

                        Locomotive daTrain = (Locomotive) entity;
                        daTrain.enforceSpeedLimits = enforceSpeedLimits;
                        if (daTrain.mtcOverridePressed) { return;}

                        if (daTrain.mtcStatus == 0  && !hadSentMTCPacket) {
                            daTrain.mtcStatus = 1;
                            //Change MTC Status
                            Traincraft.mtcChannel.sendToAllAround(new PacketMTCStatus(daTrain.getEntityId(),  1, 1)  , new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, daTrain.posX, daTrain.posY, daTrain.posZ, 150.0D));
                            hadSentMTCPacket = true;
                        }

                       /* if (daTrain.speedLimit != speedLimit) {

                            if (daTrain.riddenByEntity != null ) {

                                // worldObj.playSoundAtEntity(daTrain.ridingEntity, Info.resourceLocation + ":" + "mtc_speedchange", 1.0F, 1.0F);
                                // worldObj.playSoundAtEntity(this, Info.resourceLocation + ":" + sounds.getHornString(), sounds.getHornVolume(), 1.0F);
                                //Todo Play Sound on Client

                               // Traincraft.playSoundOnClientChannel.sendTo(new PacketPlaySoundOnClient(7, "tc:mtc_speedchange"), (EntityPlayerMP)daTrain.riddenByEntity);
                            }

                        }*/

                        daTrain.speedLimit = speedProfiles.get(activeProfile)[0];
                        if (!worldObj.isRemote && !hadSentPacket) {

                            trainNumber = daTrain.getEntityId();
                            hadSentPacket = true;
                            //Traincraft.itsChannel.sendToAllAround(new PacketSetSpeed(speedLimit, this.xCoord, this.yCoord, this.zCoord, daTrain.getEntityId()) , new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, daTrain.posX, daTrain.posY, daTrain.posZ, 150.0D));

                            daTrain.nextSpeedLimit = speedProfiles.get(activeProfile)[1];
                            daTrain.speedChange3 = Vec3.createVectorHelper(speedProfiles.get(activeProfile)[2], speedProfiles.get(activeProfile)[3], speedProfiles.get(activeProfile)[4]);
                            Traincraft.mtcChannel.sendToAllAround(new PacketSpeedLimit(trainNumber, speedProfiles.get(activeProfile)[0], speedProfiles.get(activeProfile)[1], speedProfiles.get(activeProfile)[2], speedProfiles.get(activeProfile)[3], speedProfiles.get(activeProfile)[4]),
                                    new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 150.0D));


                        }
                    }

                }
            }

        }
    }
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        if (boundingBox == null) {
            boundingBox = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 2, yCoord + 2, zCoord + 2);
        }
        return boundingBox;
    }


    @Override
    public String getType() {
        return "info_transmitter_speed";
    }

    @Override
    public String[] getMethodNames() {
        return new String[]  {"activate", "deactivate", "setSpeed", "getSpeed", "setNextSpeed", "setNextX", "setNextY", "setNextZ"};
    }

    @Override
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
        switch(method) {
            case 0: {
                isActivated = true;
                return new Object[] {true};

            } case 1: {
                isActivated = false;
                return new Object[] {true};

            } case 2: {

                if (arguments[0] instanceof Double) {
                    speedProfiles.get(0)[0] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
                    return new Object[] {"true"};
                } else {
                    return new Object[] {"nil"};
                }

            } case 3: {
                return new Object[]{speedProfiles.get(0)[0]};

            } case 4: {

                if (arguments[0] instanceof Double) {
                    speedProfiles.get(0)[1] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
                    return new Object[] {"true"};
                } else {
                    return new Object[] {"nil"};
                }

            } case 5: {
                if (arguments[0] instanceof Double) {
                    speedProfiles.get(0)[2] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
                    return new Object[] {"true"};
                } else {
                    return new Object[] {"nil"};
                }
            } case 6: {
                if (arguments[0] instanceof Double) {
                    speedProfiles.get(0)[3] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
                    return new Object[] {"true"};
                } else {
                    return new Object[] {"nil"};
                }
            }case 7: {
                if (arguments[0] instanceof Double) {
                    speedProfiles.get(0)[5] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
                    return new Object[] {"true"};
                } else {
                    return new Object[] {"nil"};
                }
            } default:
                return new Object[] {"nil"};
        }


    }


    @Override
    public void attach(IComputerAccess computer) {

    }

    @Override
    public void detach(IComputerAccess computer) {

    }

    @Override
    public boolean equals(IPeripheral other) {
        return false;
    }





}

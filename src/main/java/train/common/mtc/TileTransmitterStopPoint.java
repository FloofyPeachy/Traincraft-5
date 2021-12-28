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
import train.common.mtc.network.PacketATOSetStopPoint;
import train.common.mtc.network.PacketStopPoint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TileTransmitterStopPoint extends TileEntity implements IPeripheral {

   public boolean isActivated = false;

   public List<int[]> stopProfiles = new ArrayList<>();
   //(In order:)
   //1st int: stopX
   //2nd int: stopY
   //3rd int: stopZ
   //4th int: type (0 for Full Stop, 1 for Station Stop)


   //On redstone:
   //0: Do nothing
   //1: Activate
   //2: Switch to profile
   public int onRedstone;
   public int onRedstoneProfile;
   public AxisAlignedBB boundingBox = null;

   public TileTransmitterStopPoint() {
      stopProfiles.add(new int[]{0, 0, 0, 0, 0});
      stopProfiles.add(new int[]{0, 0, 0, 0, 0});
      stopProfiles.add(new int[]{0, 0, 0, 0, 0});
      stopProfiles.add(new int[]{0, 0, 0, 0, 0});
   }

   public void updateEntity() {


      if (worldObj == null) return;
      if (worldObj.isRemote) return;
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




      if(isActivated) {
         List<Object> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(null, this.getRenderBoundingBox());

         for (Object entity : list) {
            if (entity instanceof Locomotive) {
               Locomotive locomotive = (Locomotive) entity;
               if (locomotive.mtcOverridePressed) return;

               if (locomotive.mtcStatus == 1 |locomotive.mtcStatus == 2 ) {
                  int[] profile = stopProfiles.get(activeProfile);

                  if (profile[3] == 0) {
                     //Full Stop.
                     locomotive.stopPoint3 = Vec3.createVectorHelper(profile[0], profile[1], profile[2]);
                  } else {
                     //Station Stop.
                     locomotive.stationStop3 = Vec3.createVectorHelper(profile[0], profile[1], profile[2]);
                  }

                  Traincraft.mtcChannel.sendToAllAround(new PacketStopPoint(locomotive.getEntityId(), profile[0], profile[1], profile[2], profile[3]) , new NetworkRegistry.TargetPoint(this.worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 150.0D));

               }
            }
         }


      }

   }

   public String getType() {
      return "ato_stoppoint_transmitter";
   }

   public String[] getMethodNames() {
      return new String[]{"activate", "deactivate", "setX", "setY", "setZ"};
   }

   public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
      switch(method) {
      case 0:
         this.isActivated = true;
         return new Object[]{true};
      case 1:
         this.isActivated = false;
         return new Object[]{false};
      case 2:

         if (arguments[0] instanceof Double) {
            stopProfiles.get(0)[0] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
            return new Object[] {"true"};
         } else {
            return new Object[] {"nil"};
         }
      case 3:
         if (arguments[0] instanceof Double) {
            stopProfiles.get(0)[1] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
            return new Object[] {"true"};
         } else {
            return new Object[] {"nil"};
         }
      case 4:
         if (arguments[0] instanceof Double) {
            stopProfiles.get(0)[2] = (int)Math.round(Double.parseDouble(arguments[0].toString()));
            return new Object[] {"true"};
         } else {
            return new Object[] {"nil"};
         }
      default:
         return new Object[]{"nil"};
      }
   }

   public void attach(IComputerAccess computer) {}

   public void detach(IComputerAccess computer) {}

   public boolean equals(IPeripheral other) {
      return false;
   }

   @Override
   public AxisAlignedBB getRenderBoundingBox() {
      if (boundingBox == null) {
         boundingBox = AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 2, yCoord + 2, zCoord + 2);
      }
      return boundingBox;
   }

/*
  @Override
   public void readFromNBT(NBTTagCompound nbttagcompound) {
      super.readFromNBT(nbttagcompound);
      this.isActivated = nbttagcompound.getBoolean("isActivated");
      this.stopX = nbttagcompound.getDouble("stopX");
      this.stopY = nbttagcompound.getDouble("stopY");
      this.stopZ = nbttagcompound.getDouble("stopZ");
   }

   @Override
   public void writeToNBT(NBTTagCompound nbttagcompound) {
      super.writeToNBT(nbttagcompound);
      nbttagcompound.setBoolean("isActivated", this.isActivated);
      nbttagcompound.setDouble("stopX", this.stopX);
      nbttagcompound.setDouble("stopY", this.stopY);
      nbttagcompound.setDouble("stopZ", this.stopZ);
   }
*/

}

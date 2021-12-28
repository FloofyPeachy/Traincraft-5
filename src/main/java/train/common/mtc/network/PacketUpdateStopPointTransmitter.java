package train.common.mtc.network;



import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.DimensionManager;
import train.common.mtc.TileTransmitterSpeed;
import train.common.mtc.TileTransmitterStopPoint;

public class PacketUpdateStopPointTransmitter implements IMessage {
    private int dimensionId, x, y, z, stopType, profile, onRedstone, onRedstoneProfile;
    private Vec3 stopPoint;

    public PacketUpdateStopPointTransmitter() {
    }

    public PacketUpdateStopPointTransmitter(int dimensionId, int x, int y, int z, int stopType, Vec3 stopPoint, int profile, int onRedstone, int onRedstoneProfile) {
        this.dimensionId = dimensionId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.stopType = stopType;
        this.stopPoint = stopPoint;
        this.profile = profile;
        this.onRedstone = onRedstone;
        this.onRedstoneProfile = onRedstoneProfile;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {

        dimensionId = bbuf.readInt();
        x = bbuf.readInt();
        y = bbuf.readInt();
        z = bbuf.readInt();
        stopType = bbuf.readInt();
        double sx = bbuf.readDouble();
        double sy = bbuf.readDouble();
        double sz = bbuf.readDouble();
        profile = bbuf.readInt();
        onRedstone = bbuf.readInt();
        onRedstoneProfile = bbuf.readInt();
        stopPoint = Vec3.createVectorHelper(sx, sy, sz);

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            TileEntity te = DimensionManager.getWorld(dimensionId).getTileEntity(x, y, z);

            if (te instanceof TileTransmitterStopPoint) {
                //Do stuff with your TileEntity here!
                TileTransmitterStopPoint tileTransmitterStopPoint = (TileTransmitterStopPoint) te;
                tileTransmitterStopPoint.stopProfiles.get(profile)[0] = (int) stopPoint.xCoord;
                tileTransmitterStopPoint.stopProfiles.get(profile)[1] = (int) stopPoint.yCoord;
                tileTransmitterStopPoint.stopProfiles.get(profile)[2] = (int) stopPoint.zCoord;
                tileTransmitterStopPoint.stopProfiles.get(profile)[3] = stopType;
                tileTransmitterStopPoint.onRedstone = onRedstone;
                tileTransmitterStopPoint.onRedstoneProfile = onRedstoneProfile;
            }
        }
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
            if (te instanceof TileTransmitterStopPoint) {
                TileTransmitterStopPoint tileTransmitterStopPoint = (TileTransmitterStopPoint) te;
                tileTransmitterStopPoint.stopProfiles.get(profile)[0] = (int) stopPoint.xCoord;
                tileTransmitterStopPoint.stopProfiles.get(profile)[1] = (int) stopPoint.yCoord;
                tileTransmitterStopPoint.stopProfiles.get(profile)[2] = (int) stopPoint.zCoord;
                tileTransmitterStopPoint.stopProfiles.get(profile)[3] = stopType;
                tileTransmitterStopPoint.onRedstone = onRedstone;
                tileTransmitterStopPoint.onRedstoneProfile = onRedstoneProfile;
            }
        }

        bbuf.writeInt(dimensionId);
        bbuf.writeInt(x);
        bbuf.writeInt(y);
        bbuf.writeInt(z);
        bbuf.writeInt(stopType);
        bbuf.writeDouble(stopPoint.xCoord);
        bbuf.writeDouble(stopPoint.yCoord);
        bbuf.writeDouble(stopPoint.zCoord);
        bbuf.writeInt(profile);
        bbuf.writeInt(onRedstone);
        bbuf.writeInt(onRedstoneProfile);
    }
}
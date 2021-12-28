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

public class PacketUpdateSpeedTransmitter implements IMessage {
    private int dimensionId, x, y, z, speedLimit, nextSpeedLimit, profile, onRedstone, onRedstoneProfile;
    private Vec3 speedChange;

    public PacketUpdateSpeedTransmitter() {
    }

    public PacketUpdateSpeedTransmitter(int dimensionId, int x, int y, int z, int speedLimit, int nextSpeedLimit, Vec3 speedChange, int profile, int onRedstone, int onRedstoneProfile) {
        this.dimensionId = dimensionId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.speedLimit = speedLimit;
        this.nextSpeedLimit = nextSpeedLimit;
        this.speedChange = speedChange;
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
        speedLimit = bbuf.readInt();
        nextSpeedLimit = bbuf.readInt();
        double sx = bbuf.readDouble();
        double sy = bbuf.readDouble();
        double sz = bbuf.readDouble();
        profile = bbuf.readInt();
        onRedstone = bbuf.readInt();
        onRedstoneProfile = bbuf.readInt();
        speedChange = Vec3.createVectorHelper(sx, sy, sz);

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            TileEntity te = DimensionManager.getWorld(dimensionId).getTileEntity(x, y, z);

            if (te instanceof TileTransmitterSpeed) {
                //Do stuff with your TileEntity here!
                TileTransmitterSpeed tileTransmitterSpeed = (TileTransmitterSpeed) te;
                tileTransmitterSpeed.speedProfiles.get(profile)[0] = speedLimit;
                tileTransmitterSpeed.speedProfiles.get(profile)[1] = nextSpeedLimit;
                tileTransmitterSpeed.speedProfiles.get(profile)[2] = (int) speedChange.xCoord;
                tileTransmitterSpeed.speedProfiles.get(profile)[3] = (int) speedChange.yCoord;
                tileTransmitterSpeed.speedProfiles.get(profile)[4] = (int) speedChange.zCoord;
                tileTransmitterSpeed.onRedstone = onRedstone;
                tileTransmitterSpeed.onRedstoneProfile = onRedstoneProfile;
            }
        }
    }

    @Override
    public void toBytes(ByteBuf bbuf) {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            TileEntity te = Minecraft.getMinecraft().theWorld.getTileEntity(x, y, z);
            if (te instanceof TileTransmitterSpeed) {
                TileTransmitterSpeed tileTransmitterSpeed = (TileTransmitterSpeed) te;
                tileTransmitterSpeed.speedProfiles.get(profile)[0] = speedLimit;
                tileTransmitterSpeed.speedProfiles.get(profile)[1] = nextSpeedLimit;
                tileTransmitterSpeed.speedProfiles.get(profile)[2] = (int) speedChange.xCoord;
                tileTransmitterSpeed.speedProfiles.get(profile)[3] = (int) speedChange.yCoord;
                tileTransmitterSpeed.speedProfiles.get(profile)[4] = (int) speedChange.zCoord;
                tileTransmitterSpeed.onRedstone = onRedstone;
                tileTransmitterSpeed.onRedstoneProfile = onRedstoneProfile;
            }
        }

        bbuf.writeInt(dimensionId);
        bbuf.writeInt(x);
        bbuf.writeInt(y);
        bbuf.writeInt(z);
        bbuf.writeInt(speedLimit);
        bbuf.writeInt(nextSpeedLimit);
        bbuf.writeDouble(speedChange.xCoord);
        bbuf.writeDouble(speedChange.yCoord);
        bbuf.writeDouble(speedChange.zCoord);
        bbuf.writeInt(profile);
        bbuf.writeInt(onRedstone);
        bbuf.writeInt(onRedstoneProfile);
    }
}
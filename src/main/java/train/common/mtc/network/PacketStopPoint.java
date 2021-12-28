package train.common.mtc.network;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import train.common.api.Locomotive;

public class PacketStopPoint implements IMessage {
    private int entityId, type;
    private double xStopPoint, yStopPoint, zStopPoint;
    public PacketStopPoint() {
    }

    public PacketStopPoint(int entityId, double xStopPoint, double yNextSpeedLimit, double zNextSpeedLimit, int type) {

        this.entityId = entityId;
        this.xStopPoint = xStopPoint;
        this.yStopPoint = yNextSpeedLimit;
        this.zStopPoint = zNextSpeedLimit;
        this.type = type;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        entityId = bbuf.readInt();
        xStopPoint = bbuf.readDouble();
        yStopPoint = bbuf.readDouble();
        zStopPoint = bbuf.readDouble();
        type = bbuf.readInt();
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(entityId);
            if (entity instanceof Locomotive) {
                Locomotive locomotive = (Locomotive)entity;
                locomotive.stopPoint3 = Vec3.createVectorHelper(xStopPoint, yStopPoint, zStopPoint);

            }
        }

    }

    @Override
    public void toBytes(ByteBuf bbuf) {

        bbuf.writeInt(entityId);
        bbuf.writeDouble(xStopPoint);
        bbuf.writeDouble(yStopPoint);
        bbuf.writeDouble(zStopPoint);
        bbuf.writeInt(type);
    }
}
package train.common.mtc.network;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import train.common.api.Locomotive;

public class PacketMTCStatus implements IMessage {
    private int entityId;
    private int mtcStatus, mtcType;
    public PacketMTCStatus() {
    }

    public PacketMTCStatus(int entityId, int mtcStatus, int mtcType) {

        this.entityId = entityId;
        this.mtcStatus = mtcStatus;
        this.mtcType = mtcType;
    }

    @Override
    public void fromBytes(ByteBuf bbuf) {
        entityId = bbuf.readInt();
        mtcStatus = bbuf.readInt();
        mtcType = bbuf.readInt();

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(entityId);
            if (entity instanceof Locomotive) {
                Locomotive locomotive = (Locomotive)entity;
                locomotive.mtcStatus = mtcStatus;
                locomotive.mtcType = mtcType;

            }
        }

    }

    @Override
    public void toBytes(ByteBuf bbuf) {

        bbuf.writeInt(entityId);
        bbuf.writeInt(mtcStatus);
        bbuf.writeInt(mtcType);
    }
}
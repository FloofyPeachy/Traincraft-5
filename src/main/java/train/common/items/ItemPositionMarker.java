package train.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import train.common.Traincraft;
import train.common.library.GuiIDs;
import train.common.library.Info;

import java.util.List;

public class ItemPositionMarker extends Item {
    int coordinateX;
    int coordinateY;
    int coordinateZ;

    public ItemPositionMarker() {
        super();
        maxStackSize = 1;
        setCreativeTab(Traincraft.tcTab);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Info.modID.toLowerCase() + ":paintbrushThing");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {

        NBTTagCompound tagCompound = stack.getTagCompound();
        coordinateX = x;
        coordinateY = y;
        coordinateZ = z;
        if (tagCompound == null) {
            tagCompound = new NBTTagCompound();
            tagCompound.setInteger("coordinateX", x);
            tagCompound.setInteger("coordinateY", y);
            tagCompound.setInteger("coordinateZ", z);
            stack.setTagCompound(tagCompound);
        } else {
            stack.getTagCompound().setInteger("coordinateX", x);
            stack.getTagCompound().setInteger("coordinateY", y);
            stack.getTagCompound().setInteger("coordinateZ", z);
        }

        if (!world.isRemote) {
            player.addChatComponentMessage(new ChatComponentText(
                    "X: " + EnumChatFormatting.GREEN + x
            ));
            player.addChatComponentMessage(new ChatComponentText(
                    "Y: " + EnumChatFormatting.GREEN + y
            ));
            player.addChatComponentMessage(new ChatComponentText(
                    "Z: " + EnumChatFormatting.GREEN + z
            ));
        }

        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("\u00a77" + "Informs you about the coordinates of a block. ");
        par3List.add("\u00a77" + EnumChatFormatting.GREEN + "Right click" + EnumChatFormatting.GRAY + " and the coordinates will be sent through the chat. ");
        par3List.add("\u00a77" + "You can fill MTC blocks with this.");
        par3List.add("\u00a77" + EnumChatFormatting.GREEN + "{" + coordinateX + "," + coordinateY + "," + coordinateZ + "}");
    }
}

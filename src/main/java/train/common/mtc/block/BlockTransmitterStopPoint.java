package train.common.mtc.block;



import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import train.common.mtc.tile.TileTransmitterStopPoint;


public class BlockTransmitterStopPoint extends BlockContainer implements IPeripheralProvider {

   public static BlockTransmitterStopPoint instance;


   public BlockTransmitterStopPoint(Material p_i45386_1_) {
      super(p_i45386_1_);
      instance = this;


   }

   public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
      TileEntity tileEntity = world.getTileEntity(x, y, z);
      return (IPeripheral)tileEntity;
   }



   @Override
   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new TileTransmitterStopPoint();
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float sideX, float sideY, float sideZ) {
      // System.out.println("ting");
      if (world.isRemote) {
         openGUI(world, x, y, z);
      }
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void openGUI(World world,int x, int y, int z) {
      Minecraft.getMinecraft().displayGuiScreen(new train.client.gui.GuiStopPointTransmitter(world.getTileEntity(x, y, z)));
   }
}
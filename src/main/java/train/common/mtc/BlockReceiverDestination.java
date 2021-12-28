package train.common.mtc;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockReceiverDestination extends BlockContainer implements IPeripheralProvider {

    public static BlockReceiverDestination instance;
    public BlockReceiverDestination(Material mtrl) {
        super(mtrl);
        instance = this;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileReceiverDestination();
    }

    public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        return (IPeripheral)tileEntity;
    }


}


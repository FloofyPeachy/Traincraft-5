package train.common.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tmt.ModelConverter;
import tmt.Tessellator;
import train.common.api.AbstractTrains;
import train.common.library.Info;

/**
 * @author 02skaplan
 * <p>Stores and retrieves cargo information for carts with changable display models & textures.</p>
 */
public class CargoManager {
    private int selectedCargo = 0;
    private final CargoSpecification[][] cargoSpecificationList;
    private ModelConverter[] renderModels;

    /**
     * @author 02skaplan
     * @param cargoSpecificationList List of CargoSpecifications detailing possible cargo options.
     */
    public CargoManager(CargoSpecification[][] cargoSpecificationList) {
        this.cargoSpecificationList = cargoSpecificationList;
    }

    public CargoSpecification[][] getCargoSpecificationList() {
        return cargoSpecificationList;
    }

    public int getSelectedCargo() {
        return selectedCargo;
    }

    public void setSelectedCargo(int selectedCargo) {
        this.selectedCargo = selectedCargo;
        if (selectedCargo > 0) {
            try {
                renderModels = new ModelConverter[getCargoSpecificationList()[selectedCargo - 1].length];
                for (int i = 0; i < renderModels.length; i++) {
                    renderModels[i] = getCargoSpecificationList()[selectedCargo - 1][i].cargoModelClass.newInstance();
                }
            } catch (InstantiationException | IllegalAccessException ignored) {}
        }
    }

    /**
     * @author 02skaplan
     * <p>This method needs to be called at the bottom of the render method of the model file of a supported entity.</p>
     * @param entity Train entity on which to render the cargo.
     */
    public void renderCargo(AbstractTrains entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (getSelectedCargo() > 0) {
            int cargoNumber = getSelectedCargo();
            // This if statement should always be activated, but is useful in case a CargoSpec is removed from the list.
            if (cargoNumber - 1 < cargoSpecificationList.length) {
                for (int i = 0; i < getCargoSpecificationList()[cargoNumber - 1].length; i++) {
                    if (!getCargoSpecificationList()[cargoNumber - 1][i].textureFile.isEmpty())
                        Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/" + getCargoSpecificationList()[cargoNumber - 1][i].textureFile + ".png"));
                    GL11.glPushMatrix();
                    GL11.glTranslated(getCargoSpecificationList()[cargoNumber - 1][i].offsetX, getCargoSpecificationList()[cargoNumber - 1][i].offsetY - 3, getCargoSpecificationList()[cargoNumber - 1][i].offsetZ);
                    GL11.glScaled(getCargoSpecificationList()[cargoNumber - 1][i].scaleXa + 1, getCargoSpecificationList()[cargoNumber - 1][i].scaleYa + 1, getCargoSpecificationList()[cargoNumber - 1][i].scaleZa + 1);
                    renderModels[i].render(entity, f, f1, f2, f3, f4, f5);
                    GL11.glPopMatrix();
                }
            }
        }
    }

    public void exportToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setInteger("cargoLoad", selectedCargo);
    }

    public void importFromNBTTagCompound(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound.hasKey("cargoLoad")) {
            if (nbtTagCompound.getInteger("cargoLoad") != 0) {
                setSelectedCargo(nbtTagCompound.getInteger("cargoLoad"));
            }
        }
    }
}
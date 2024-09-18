package train.client.gui;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jcirmodelsquad.tcjcir.features.autotrain.IAT2Compatible;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import train.common.Traincraft;
import train.common.api.*;
import train.common.core.network.*;
import train.common.inventory.InventoryControlCar;
import train.common.library.Info;

public class GuiControlCar extends GuiContainer
{
    private String texture = Info.guiPrefix + "customButton.png";
    private int textureX = 0;
    private int textureY = 46;
    private int textureSizeX = 40;
    private int textureSizeY = 13;
    private int buttonPosX = 0;
    private int buttonPosY = 0;
    private GuiButton buttonLock;

    private AbstractControlCar controlCar;
    private Locomotive locomotiveUnderControl;

    public GuiControlCar(InventoryPlayer inventoryplayer, Entity entityminecart)
    {
        super(new InventoryControlCar(inventoryplayer,  (AbstractControlCar)entityminecart));
        controlCar = (AbstractControlCar) entityminecart;
        locomotiveUnderControl = (Locomotive) Minecraft.getMinecraft().theWorld.getEntityByID(controlCar.getLocomotiveBeingControlledEntityID());

    }

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        //region ParkingBrake
        if (locomotiveUnderControl != null)
        {
            if (!locomotiveUnderControl.getParkingBrakeFromPacket())
            {
                if (locomotiveUnderControl instanceof SteamTrain) {
                    textureX = 41;
                    textureY = 13;
                    textureSizeX = 40;
                    textureSizeY = 13;
                }
                else {
                    textureX = 126;
                    textureY = 13;
                    textureSizeX = 43;
                    textureSizeY = 13;
                }
                buttonPosX = 43;
                buttonPosY = -13;
                buttonList.add(new GuiCustomButton(2, ((width - xSize) / 2) + buttonPosX - 12, ((height - ySize) / 2) + buttonPosY, textureSizeX, textureSizeY, "", texture, textureX, textureY));//Brake: Off
            }
            else
            {
                if (locomotiveUnderControl instanceof SteamTrain) {
                    textureX = 0;
                    textureY = 13;
                    textureSizeX = 40;
                    textureSizeY = 13;
                }
                else {
                    textureX = 82;
                    textureY = 13;
                    textureSizeX = 43;
                    textureSizeY = 13;
                }
                buttonPosX = 0;
                buttonPosY = -13;
                buttonList.add(new GuiCustomButton(2, ((width - xSize) / 2) + buttonPosX, ((height - ySize) / 2) + buttonPosY, textureSizeX, textureSizeY, "", texture, textureX, textureY));//Brake: On
            }
        }
        //endregion ParkingBrake

        //region TrainLocked
        int var1 = (this.width - xSize) / 2;
        int var2 = (this.height - ySize) / 2;
        if (!controlCar.getTrainLockedFromPacket()) {
            this.buttonList.add(this.buttonLock = new GuiButton(3, var1 + 108, var2 - 10, 67, 10, "Unlocked"));
        }
        else {
            if (controlCar.getTrainOwner().equalsIgnoreCase(((EntityPlayer) controlCar.riddenByEntity).getDisplayName()))
                this.buttonList.add(this.buttonLock = new GuiButton(3, var1 + 108, var2 - 10, 67, 10, "Locked"));
            else if (controlCar.isPlayerTrusted(((EntityPlayer) controlCar.riddenByEntity).getDisplayName()))
                if (controlCar.isPlayerTrustedToBreak(((EntityPlayer) controlCar.riddenByEntity).getDisplayName()))
                    this.buttonList.add(this.buttonLock = new GuiButton(3, var1 + 104, var2 - 10, 71, 10, "Trusted+"));
                else
                    this.buttonList.add(this.buttonLock = new GuiButton(3, var1 + 106, var2 - 10, 69, 10, "Trusted"));
        }
        //endregion TrainLocked

        //region guiTCTextFieldTrainNote
        controlCar.guiTCTextFieldTrainNote = new GuiTCTextField(fontRendererObj, width/2 - 85, var2 - 39, 170,15);
        controlCar.guiTCTextFieldTrainNote.setText(controlCar.getTrainNote());
        //endregion guiTCTextFieldTrainNote

        //region Lights On/Off
        if (controlCar.isLightsEnabled())
        {
            buttonList.add(this.buttonLock = new GuiButton(6, var1 + 108, var2 + 166, 67, 12, "Lights: On"));
        }
        else
        {
            buttonList.add(this.buttonLock = new GuiButton(6, var1 + 108, var2 + 166, 67, 12, "Lights: Off"));
        }
        //endregion Lights On/Off

        //region Beacon On/Off
        if (controlCar.isBeaconEnabled())
        {
            buttonList.add(this.buttonLock = new GuiButton(7, var1 + 41, var2 + 166, 67, 12, "Beacon: On"));
        }
        else
        {
            buttonList.add(this.buttonLock = new GuiButton(7, var1 + 41, var2 + 166, 67, 12, "Beacon: Off"));
        }
        //endregion Beacon On/Off

        //region DitchLights On/Off
        if (controlCar.isDitchLightsEnabled())
        {
            buttonList.add(this.buttonLock = new GuiButton(8, var1 + 90, var2 + 178, 85, 12, "Ditch Lights: On"));
        }
        else
        {
            buttonList.add(this.buttonLock = new GuiButton(8, var1 + 90, var2 + 178, 85, 12, "Ditch Lights: Off"));
        }
        //endregion DitchLights On/Off
    }

    @Override
    protected void actionPerformed(GuiButton guibutton)
    {
        switch (guibutton.id)
        {
            case 2:
                if ((!locomotiveUnderControl.parkingBrake) && locomotiveUnderControl.getSpeed() < 10) {
                    Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(true, locomotiveUnderControl.getEntityId()));
                    locomotiveUnderControl.parkingBrake=true;
                    locomotiveUnderControl.isBraking=true;
                    guibutton.displayString = "Brake: On";
                    this.initGui();
                }
                else if (locomotiveUnderControl.getSpeed() < 10) {
                    Traincraft.brakeChannel.sendToServer(new PacketParkingBrake(false, locomotiveUnderControl.getEntityId()));
                    locomotiveUnderControl.parkingBrake=false;
                    locomotiveUnderControl.isBraking=false;
                    guibutton.displayString = "Brake: Off";
                    this.initGui();
                }
            break;

            case 3: // Lock Control Car
                if (controlCar.riddenByEntity instanceof EntityPlayer && ((EntityPlayer) controlCar.riddenByEntity).getDisplayName().equals(controlCar.getTrainOwner())) {
                    if ((!controlCar.getTrainLockedFromPacket())) {
                        if (!isShiftKeyDown()) {
                            Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(true, controlCar.getTrustedList(), controlCar.getEntityId(), false));
                            controlCar.locked = true;
                            guibutton.displayString = "Locked";
                            this.initGui();
                        }
                    } else {
                        if (!isShiftKeyDown()) {
                            Traincraft.lockChannel.sendToServer(new PacketSetTrainLockedToClient(false, controlCar.getTrustedList(), controlCar.getEntityId(), false));
                            controlCar.locked = false;
                            guibutton.displayString = "UnLocked";
                            this.initGui();
                        }
                    }
                }
                else if (controlCar.riddenByEntity instanceof EntityPlayer) {
                    ((EntityPlayer) controlCar.riddenByEntity).addChatMessage(new ChatComponentText("You are not the owner!"));
                }
            break;

            case 6: // Lights
                if (controlCar.isLightsEnabled())
                {
                    Traincraft.rollingStockLightsChannel.sendToServer(new PacketRollingStockLights(false, controlCar.getEntityId()));
                    controlCar.isLightsEnabled = false;
                    guibutton.displayString = "Lights: Off";
                }
                else
                {
                    Traincraft.rollingStockLightsChannel.sendToServer(new PacketRollingStockLights(true, controlCar.getEntityId()));
                    controlCar.isLightsEnabled = true;
                    guibutton.displayString = "Lights: On";
                }
            break;
            case 7: // Beacon
                if (controlCar.isBeaconEnabled())
                {
                    Traincraft.rollingStockBeaconChannel.sendToServer(new PacketRollingStockBeacon(false, controlCar.getEntityId()));
                    controlCar.isBeaconEnabled = false;
                    guibutton.displayString = "Beacon: Off";
                }
                else
                {
                    Traincraft.rollingStockBeaconChannel.sendToServer(new PacketRollingStockBeacon(true, controlCar.getEntityId()));
                    controlCar.isBeaconEnabled = true;
                    guibutton.displayString = "Beacon: On";
                }
            break;
            case 8: // DitchLights
                if (controlCar.isDitchLightsEnabled())
                {
                    Traincraft.rollingStockDitchLightsChannel.sendToServer(new PacketRollingStockDitchLights((byte)0, controlCar.getEntityId()));
                    controlCar.ditchLightMode = 0;
                    guibutton.displayString = "Ditch Lights: Off";
                }
                else
                {
                    Traincraft.rollingStockDitchLightsChannel.sendToServer(new PacketRollingStockDitchLights((byte)1, controlCar.getEntityId()));
                    controlCar.ditchLightMode = 1;
                    guibutton.displayString = "Ditch Lights: On";
                }
            break;
        }
    }

    @Override
    protected void drawCreativeTabHoveringText(String str, int t, int g) {

        String state = "";
        if (controlCar.getTrainLockedFromPacket()) {
            if (controlCar.getTrainOwner().equalsIgnoreCase(((EntityPlayer) controlCar.riddenByEntity).getDisplayName()))
                state = "Locked";
            else if (controlCar.isPlayerTrusted(((EntityPlayer) controlCar.riddenByEntity).getDisplayName()))
                if (controlCar.isPlayerTrustedToBreak(((EntityPlayer) controlCar.riddenByEntity).getDisplayName()))
                    state = "Trusted Access+";
                else
                    state = "Trusted Access";
        } else {
            state = "Unlocked";
        }

        int textWidth = fontRendererObj.getStringWidth("the GUI, change speed, destroy it.");
        int startX = 90;
        int startY = 5;

        int i4 = 0xf0100010;
        drawGradientRect(startX - 3, startY - 4, startX + textWidth + 3, startY + 52, i4, i4);
        drawGradientRect(startX - 4, startY - 3, startX + textWidth + 4, startY + 51, i4, i4);
        int colour1 = 0x505000ff;
        int colour2 = (colour1 & 0xfefefe) >> 1 | colour1 & 0xff000000;
        drawGradientRect(startX - 3, startY - 3, startX + textWidth + 3, startY + 51, colour1, colour2);
        drawGradientRect(startX - 2, startY - 2, startX + textWidth + 2, startY + 50, i4, i4);
        fontRendererObj.drawStringWithShadow(str, startX, startY, -1);
        fontRendererObj.drawStringWithShadow("only its owner can open", startX, startY + 10, -1);
        fontRendererObj.drawStringWithShadow("the GUI, change speed, destroy it.", startX, startY + 20, -1);
        fontRendererObj.drawStringWithShadow("Current state: " + state, startX, startY + 30, -1);
        fontRendererObj.drawStringWithShadow("Owner: " + controlCar.getTrainOwner().trim(), startX,
                startY + 40, -1);
    }

    public boolean intersectsWith(int mouseX, int mouseY) {
        //System.out.println(mouseX+" "+mouseY);
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        return (mouseX >= j + 124 && mouseX <= j + 174 && mouseY >= k - 10 && mouseY <= k);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j) {
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        fontRendererObj.drawString(controlCar.getInventoryName(), 39, 7, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 41, 5, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 39, 5, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 41, 7, 0x000000);

        fontRendererObj.drawString(controlCar.getInventoryName(), 39, 6, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 41, 6, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 40, 7, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 40, 5, 0x000000);
        fontRendererObj.drawString(controlCar.getInventoryName(), 40, 6, 0xd3a900);

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        if (intersectsWith(i, j)) {
            drawCreativeTabHoveringText("When a locomotive is locked,", i, j);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float par3)
    {
        super.drawScreen(mouseX, mouseY,par3);
        controlCar.guiTCTextFieldTrainNote.drawTextBox();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (controlCar.guiTCTextFieldTrainNote.isFocused()) {
            controlCar.guiTCTextFieldTrainNote.updateCursorCounter();
        }
    }

    @Override
    protected void keyTyped(char par1, int par2)
    {
        if (controlCar.guiTCTextFieldTrainNote.isFocused()) {
            controlCar.guiTCTextFieldTrainNote.textboxKeyTyped(par1, par2);
        } else if (par1 == 1 || (par2 == this.mc.gameSettings.keyBindInventory.getKeyCode() || par2 == Keyboard.KEY_ESCAPE)){
            Traincraft.lockChannel.sendToServer(new PacketAddNote(controlCar.getEntityId(), controlCar.guiTCTextFieldTrainNote.getText()));
            mc.thePlayer.closeScreen();
        } else {
            super.keyTyped(par1, par2);
        }
    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
        controlCar.guiTCTextFieldTrainNote.mouseClicked(par1, par2, par3);
        super.mouseClicked(par1, par2, par3);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int t, int g) {
        String controlCarGUIFilePath = Info.guiPrefix + "gui_loco.png";

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(new ResourceLocation(Info.resourceLocation, controlCarGUIFilePath));
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawTexturedModalRect(j, k, 0, 0, xSize, ySize);

        for (int i1 = controlCar.numCargoSlots; i1 < 5; i1++) {
            drawTexturedModalRect(j + 79 + 18 * i1, k + 17, 190, 0, 18, 18);
        }
        for (int j1 = controlCar.numCargoSlots1; j1 < 5; j1++) {
            drawTexturedModalRect(j + 79 + 18 * j1, k + 35, 190, 0, 18, 18);
        }
        for (int k1 = controlCar.numCargoSlots2; k1 < 5; k1++) {
            drawTexturedModalRect(j + 79 + 18 * k1, k + 53, 190, 0, 18, 18);
        }

        if (locomotiveUnderControl != null)
        {
            JsonObject guiDetails = new JsonParser().parse(locomotiveUnderControl.guiDetailsDW()).getAsJsonObject();
            fontRendererObj.drawStringWithShadow("Carts pulled: " + guiDetails. get("cartsPulled"), 1, 10, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Mass pulled: " + guiDetails.get("massPulled"), 1, 20, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Speed reduction: " + guiDetails.get("slowDown") + " km/h", 1, 30, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Accel reduction: " + guiDetails.get("accelSlowDown"), 1, 40, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Brake reduction: " + guiDetails.get("brakeSlowDown"), 1, 50, 0xFFFFFF);


            fontRendererObj.drawStringWithShadow("Fuel consumption: " + ((locomotiveUnderControl.getFuelConsumption() *0.2)+"").substring(0,Math.min(((locomotiveUnderControl.getFuelConsumption() *0.2)+"").length(),4))+ " mB/s", 1,
                    60, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Fuel: " + locomotiveUnderControl.getFuel(), 1, 70, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Power: " + locomotiveUnderControl.getPower() + " Mhp", 1, 80, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("State: " + locomotiveUnderControl.getState(), 1, 90, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Heat level: " + locomotiveUnderControl.getOverheatLevel(), 1, 100, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Maximum Speed: " + (locomotiveUnderControl.getCustomSpeedGUI()) + " km/h", 1, 110, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Destination: " + (locomotiveUnderControl.getDestinationGUI()), 1, 120, 0xFFFFFF);
            fontRendererObj.drawStringWithShadow("Primary Loco: " + (locomotiveUnderControl.getInventoryName() + " [" + locomotiveUnderControl.getTrainNote() + "]"), 1, 130, 0xFFFFFF);

        }
    }
}

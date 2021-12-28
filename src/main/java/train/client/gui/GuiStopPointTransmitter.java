package train.client.gui;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import org.lwjgl.input.Keyboard;
import train.common.Traincraft;
import train.common.items.ItemPositionMarker;
import train.common.mtc.TileTransmitterStopPoint;
import train.common.mtc.network.PacketUpdateSpeedTransmitter;
import train.common.mtc.network.PacketUpdateStopPointTransmitter;

import java.util.ArrayList;
import java.util.List;

public class GuiStopPointTransmitter extends GuiScreen {
    private TileTransmitterStopPoint baseTile;


    private GuiTextField stopPointXField;
    private GuiTextField stopPointYField;
    private GuiTextField stopPointZField;
    private GuiTextField profileTextField;

    private GuiButton profileChange;
    private GuiButton onRedstone; //On Redstone: Do nothing, secondary profile
    private GuiButton done; //On Redstone: Do nothing, secondary profile
    private GuiButton addFromItem;
    private GuiButton stopType;

    public int xSize = 305;
    public int ySize = 274;
    public int guiLeft;
    public int guiTop;

    public List<int[]> stopProfiles = new ArrayList<>();

    int profile = 0;
    int redstoneReaction;
    int redstoneProfile;

    NBTTagCompound autofillCompound;

    //0: Do nothing
    //1: Activate/Deactivate
    //2: Use Profile 2
    public GuiStopPointTransmitter() {
        stopProfiles.add(new int[]{0, 0, 0, 0, 0});
        stopProfiles.add(new int[]{0, 0, 0, 0, 0});
        stopProfiles.add(new int[]{0, 0, 0, 0, 0});
        stopProfiles.add(new int[]{0, 0, 0, 0, 0});
    }

    public GuiStopPointTransmitter(TileEntity te) {
        baseTile = (TileTransmitterStopPoint)te;
    }

    @Override
    public void initGui() {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int width = sr.getScaledWidth();
        int height = sr.getScaledHeight();
        this.guiLeft = (this.width - this.xSize) / 2;
        this.guiTop = (this.height - this.ySize) / 2;
        int theW = mc.fontRenderer.getStringWidth("MTC Stop Point Transmitter");

        stopPointXField = new GuiTextField(fontRendererObj, width / 2 + 5, 46, 50, 15);
        stopPointYField = new GuiTextField(fontRendererObj, width / 2 + 5, 66, 50, 15);
        stopPointZField = new GuiTextField(fontRendererObj, width / 2 + 5, 86, 50, 15);
        profileTextField = new GuiTextField(fontRendererObj, width / 2 + 130, 148, 15, 15);
      //  onRedstoneProfileField = new GuiTextField(fontRendererObj, width / 2 + 20, 160, 50, 15);

        stopProfiles = baseTile.stopProfiles;

        stopPointXField.setText(String.valueOf(stopProfiles.get(0)[0]));
        stopPointYField.setText(String.valueOf(stopProfiles.get(0)[1]));
        stopPointZField.setText(String.valueOf(stopProfiles.get(0)[2]));


       // nextSpeedY = new GuiTextField(fontRendererObj, width / 2 - 12, 36, 27, 15);
       // nextSpeedZ = new GuiTextField(fontRendererObj, width / 2 - 12, 39, 27, 15);
        onRedstone = new GuiButton(2, width / 2 + 4, 146, 120/**/, 20,
                baseTile.onRedstone == 0 ? "Do nothing" : (baseTile.onRedstone == 1 ? "Activate/Deactivate" : "Change to profile: "));
        done = new GuiButton(1, width / 2 - 30, 200, 60, 20, "Done");
        profileChange = new GuiButton(3, width / 2 - 30, 20, 60, 20, "Profile 0");
        addFromItem = new GuiButton(4, width / 2 - 60, 180, 130, 20, "Fill from Position Marker");
        stopType = new GuiButton(5, width / 2 + 4, 106, 100, 20, (baseTile.stopProfiles.get(0)[3] == 0 ? "Standard Stop" : "Station Stop"));
       buttonList.add(onRedstone);
       buttonList.add(done);
       buttonList.add(profileChange);
        buttonList.add(stopType);
       if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null && Minecraft.getMinecraft().thePlayer.getHeldItem().getItem() instanceof ItemPositionMarker) {
           NBTTagCompound tagCompound = Minecraft.getMinecraft().thePlayer.getHeldItem().getTagCompound();
           System.out.println(tagCompound==null);
            if (tagCompound != null) {
                autofillCompound = tagCompound;
                buttonList.add(addFromItem);
            }
       }
        profileTextField.setText(String.valueOf(baseTile.onRedstoneProfile));
        redstoneReaction = baseTile.onRedstone;
        redstoneProfile = baseTile.onRedstoneProfile;
        profileTextField.setMaxStringLength(1);
        /*timeoutTime = new GuiTextField(fontRendererObj, width / 2 - 8, 26, 27, 15);
        timeoutTime.setText(String.valueOf(timeoutAmount));
        increaseButton = new GuiButton(1, width / 2 + 25, 24, 20, 20, "+");
        decreaseButton = new GuiButton(2, width / 2 + 45, 24, 20, 20, "-");
        crossingStatusButton = new GuiButton(3, width / 2 - 20, 64, 60, 20, "Activate");
        buttonList.add(increaseButton);
        buttonList.add(decreaseButton);
        buttonList.add(crossingStatusButton);*/
    /*    buttonList.add(increaseButton);
        buttonList.add(decreaseButton);*/

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int width = sr.getScaledWidth();
        int height = sr.getScaledHeight();

        /*this.guiLeft = (sr.getScaledWidth() - this.xSize) / 2;

        int x = width / 2;
        int y = height / 2;

        this.guiTop = (sr.getScaledHeight() - this.ySize) / 2;*/
        drawDefaultBackground();
        int padding = 2;
        int margin = 4;
       // Gui.drawRect(width / 2 + 40, height / 2 + 40,width / 2 + 50, height / 2 + 80, 0xFFFFFFFF);

        int theW = mc.fontRenderer.getStringWidth("MTC Stop Point Transmitter");
        fontRendererObj.drawString("MTC Stop Point Transmitter", width / 2 - theW / 2, 6 , 0xFFFFFF);

         fontRendererObj.drawString("X Coordinate:", width / 2 - theW / 2 - 10, 49, 0xFFFFFF);
         fontRendererObj.drawString("Y Coordinate:", width / 2 - theW / 2 - 10, 69, 0xFFFFFF);
         fontRendererObj.drawString("Z Coordinate:", width / 2 - theW / 2 - 10, 89, 0xFFFFFF);
            fontRendererObj.drawString("Stop Type: ", width / 2 - theW / 2 - 10, 112, 0xFFFFFF);
         stopPointXField.drawTextBox();
         stopPointYField.drawTextBox();
         stopPointZField.drawTextBox();


        if (redstoneReaction == 2) {
            profileTextField.drawTextBox();
        }
        fontRendererObj.drawString("On redstone:", width / 2 - theW / 2 - 8, 152, 0xFFFFFF);
       /* if (baseTile.isActivated) {
            crossingStatusButton.displayString = "Deactivate";
        } else {
            crossingStatusButton.displayString = "Activate";
        }*/
       // timeoutTime.drawTextBox();
        //timeoutTime.setText(String.valueOf(timeoutAmount));

        //nextSpeedX.drawTextBox();
       // nextSpeedY.drawTextBox();
        //nextSpeedZ.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            int profil = 0;
            for (int[] array : stopProfiles) {
                Traincraft.mtcBlockChannel.sendToServer(new PacketUpdateStopPointTransmitter(baseTile.getWorldObj().provider.dimensionId, baseTile.xCoord, baseTile.yCoord, baseTile.zCoord,
                        array[3], Vec3.createVectorHelper(array[0], array[1], array[2]), profil, redstoneReaction, redstoneProfile));
                profil++;
            }

            mc.thePlayer.closeScreen();
        }

        if (button.id == 2) {
            //Switch mode.
            if (redstoneReaction == 0) {
                //Change it to activate/deactivate.
                redstoneReaction = 1;
                onRedstone.displayString = "Activate/Deactivate";
            } else if (redstoneReaction == 1) {
                //Change it to change to a profile.
                redstoneReaction = 2;
                onRedstone.displayString = "Change to profile:";
            } else if (redstoneReaction == 2) {
                //Change it to do nothing.
                redstoneReaction = 0;
                onRedstone.displayString = "Do nothing";
            }
        }
        if (button.id == 3) {
            //Switch profile.
            if (profile == 3) {
                profile = 0;
            } else {
                profile++;
            }

            profileChange.displayString = "Profile " + profile;
            stopPointXField.setText(String.valueOf(stopProfiles.get(profile)[0]));
            stopPointYField.setText(String.valueOf(stopProfiles.get(profile)[1]));
            stopPointZField.setText(String.valueOf(stopProfiles.get(profile)[2]));
        }
        if (button.id == 4) {
            if (autofillCompound != null) {
                stopProfiles.get(profile)[0] = autofillCompound.getInteger("coordinateX");
                stopProfiles.get(profile)[1] = autofillCompound.getInteger("coordinateY");
                stopProfiles.get(profile)[2] = autofillCompound.getInteger("coordinateZ");
                stopPointXField.setText(String.valueOf(stopProfiles.get(profile)[0]));
                stopPointYField.setText(String.valueOf(stopProfiles.get(profile)[1]));
                stopPointZField.setText(String.valueOf(stopProfiles.get(profile)[2]));
            }

        }
        if (button.id == 5) {
            if (stopProfiles.get(profile)[3] == 0) {
                //Set it to station stop.
                stopProfiles.get(profile)[3] = 1;
                stopType.displayString = "Station Stop";
            } else if (stopProfiles.get(profile)[3] == 1) {
                stopProfiles.get(profile)[3] = 0;
                stopType.displayString = "Standard Stop";
            }
        }
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (stopPointXField.isFocused()) stopPointXField.updateCursorCounter();
        if (stopPointYField.isFocused()) stopPointYField.updateCursorCounter();
        if (stopPointZField.isFocused()) stopPointZField.updateCursorCounter();
        if (profileTextField.isFocused()) profileTextField.updateCursorCounter();
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    @Override
    protected void keyTyped(char par1, int par2) {
        if (par2 == Keyboard.KEY_ESCAPE) {
          //  PeachysRailtech.updateVariableChannel.sendToServer(new UpdateBaseTimeoutPacket(baseTile.xCoord, baseTile.yCoord, baseTile.zCoord, timeoutAmount));
            mc.thePlayer.closeScreen();
        }
        if (stopPointXField.isFocused() && (Character.isDigit(par1) || par2 == Keyboard.KEY_BACK)) stopPointXField.textboxKeyTyped(par1, par2);
        if (stopPointYField.isFocused() && (Character.isDigit(par1) || par2 == Keyboard.KEY_BACK)) stopPointYField.textboxKeyTyped(par1, par2);
        if (stopPointZField.isFocused() && (Character.isDigit(par1) || par2 == Keyboard.KEY_BACK)) stopPointZField.textboxKeyTyped(par1, par2);
        if (profileTextField.isFocused() && (Character.isDigit(par1) || par2 == Keyboard.KEY_BACK) && Character.getNumericValue(par1) < 4) profileTextField.textboxKeyTyped(par1, par2);


            stopProfiles.get(profile)[0] = isInteger(stopPointXField.getText()) ? Integer.parseInt(stopPointXField.getText()) : 0;
            stopProfiles.get(profile)[1] = isInteger(stopPointYField.getText()) ? Integer.parseInt(stopPointYField.getText()) : 0;
            stopProfiles.get(profile)[2] = isInteger(stopPointZField.getText()) ? Integer.parseInt(stopPointZField.getText()) : 0;
         redstoneProfile = isInteger(profileTextField.getText()) ? Integer.parseInt(profileTextField.getText()) : 0;

    }

    @Override
    protected void mouseClicked(int par1, int par2, int par3) {
       // timeoutTime.mouseClicked(par1, par2, par3);
        super.mouseClicked(par1, par2, par3);
        stopPointXField.mouseClicked(par1, par2, par3);
        stopPointYField.mouseClicked(par1, par2, par3);
        stopPointZField.mouseClicked(par1, par2, par3);
        profileTextField.mouseClicked(par1, par2, par3);
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}



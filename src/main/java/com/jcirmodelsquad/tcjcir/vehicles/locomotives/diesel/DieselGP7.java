package com.jcirmodelsquad.tcjcir.vehicles.locomotives.diesel;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.DieselTrain;
import train.common.api.LiquidManager;
import train.common.core.util.TraincraftUtil;
import train.common.enums.LockoutGroup;
import train.common.library.EnumSounds;
import train.common.library.EnumTrains;
import train.common.library.sounds.SoundRecord;

public class DieselGP7 extends DieselTrain {
    @Override
    public SoundRecord getSoundRecord()
    {
        return EnumSounds.DieselGP7;
    }
    public DieselGP7(World world) {
        super(world, LiquidManager.dieselFilter());

        InsertTexture(0, "Avanste Northeastern (Late)", LockoutGroup.ANE);
        InsertTexture(1, "Burlington Northern (Ex CBQ)");
        InsertTexture(2, "Butte, Anaconda & Pacific (Early)");
        InsertTexture(3, "Chicago Northwestern");
        InsertTexture(4, "Avanste Northeastern (As Delivered)", LockoutGroup.ANE);
        InsertTexture(5, "Fox Union Resources (FURRX)");
        InsertTexture(6, "Bangor & Aroostook (Early)");
        InsertTexture(7, "Bangor & Aroostook (Late)");
        InsertTexture(8, "West Creek Pacific");
        InsertTexture(9, "Carbondale & Pine Valley", LockoutGroup.CPV);
        InsertTexture(10, "Carbondale & Pine Valley (CSWR Buyout)", LockoutGroup.CPV);
        InsertTexture(11, "Carbondale & Pine Valley 11", LockoutGroup.CPV);
        InsertTexture(12, "waltuh white gp7");
        InsertTexture(13, "Deadwood & La Mesa (Early)", LockoutGroup.DLMR);
        InsertTexture(14, "Deadwood & La Mesa (Late)", LockoutGroup.DLMR);
        InsertTexture(15, "Deadwood & La Mesa (Passenger)", LockoutGroup.DLMR);
        InsertTexture(16, "Blandsville & Blankerston");
        InsertTexture(17, "Amtrak MOW");
        InsertTexture(18, "Seaboard Coast Line");
        InsertTexture(19, "Denver & Rio Grande Western (Early)");
        InsertTexture(20, "Denver & Rio Grande Western (Early 2?)");
        InsertTexture(21, "Denver & Rio Grande Western (Late Small Logo)");
        InsertTexture(22, "Denver & Rio Grande Western (Late Big Logo)");
        InsertTexture(23, "US Army");
        InsertTexture(24, "Great Northern (Emp Builder)");
        InsertTexture(25, "Great Northern (EB Simp)");
        InsertTexture(26, "Great Northern (BSB)");
        InsertTexture(27, "Washaska & Old Fox RR Co 21");
        InsertTexture(28, "Washaska & Old Fox RR Co 26");
        InsertTexture(29, "New York Central (Early)");
        InsertTexture(30, "New York Central (Lightning Bolt)");
        InsertTexture(31, "New York Central (Late)");
        InsertTexture(32, "New York Central (Grey Lighting Bolt)");
        InsertTexture(33, "FNCC (KIT-L)", LockoutGroup.FNCC);
        InsertTexture(34, "ATSF (Pinstripe)");
        InsertTexture(35, "ATSF (Zebrastripe)");
        InsertTexture(36, "MKT");
        InsertTexture(37, "Penn Central");
        InsertTexture(38, "Northern Pacific");
        InsertTexture(39, "Northern Pacific");
        InsertTexture(40, "Cotton Belt/ Southern Pacific");
        InsertTexture(41, "Cotton Belt/ Southern Pacific");
        InsertTexture(42, "Southern Pacific");
        InsertTexture(43, "Western Pacific");
        InsertTexture(44, "Deadwood & Northern", LockoutGroup.DLMR);
        InsertTexture(45, "CNW 1518");
        InsertTexture(46, "Great Lakes & Northern Territories (Ex FNCC)");
        InsertTexture(47, "Monongahela");
        InsertTexture(48, "WCP (stupid bozo chop)");
        InsertTexture(49, "CDCS", LockoutGroup.CDCS);
        InsertTexture(50, "JRN");
        InsertTexture(51, "GCCR");
        InsertTexture(52, "WM (As Delivered)");
        InsertTexture(53, "WM (Chop Nose)");
        InsertTexture(54, "WM (Chop Nose Circus Scheme)");
        InsertTexture(55, "Seaboard System");
        InsertTexture(56, "Seaboard System (Chop Nose)");
        InsertTexture(57, "Chessie System");
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }

    @Override
    public void updateRiderPosition() { TraincraftUtil.updateRider(this, 2.55, 0.15, -0.35); }

    @Override
    public float getOptimalDistance(EntityMinecart cart) { return 1.1F;
    }

    @Override
    public String transportYear() {
        return "1949-1954";
    }

    @Override
    public String getInventoryName() {
        return "EMD GP7";
    }

}
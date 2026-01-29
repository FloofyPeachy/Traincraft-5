package com.jcirmodelsquad.tcjcir.vehicles.rollingstock.workcart;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.AbstractWorkCart;
import train.common.enums.LockoutGroup;

public class CDCScaboose extends AbstractWorkCart
{
    public CDCScaboose(World world) {
        super(world);
        InsertTexture(0, "CDCS", LockoutGroup.CDCS);
        InsertTexture(1, "CDCS (Modernized)", LockoutGroup.CDCS);
        InsertTexture(2, "NPS");
        InsertTexture(3, "MOW");
    }

    @Override
    public String transportCountry()
    {
        return "US";
    }

    @Override
    public double getAdditionalYOffset()
    {
        return -0.15F;
    }

    @Override
    public float getOptimalDistance(EntityMinecart cart)
    {
        return 2.0375F;
    }

    @Override
    public boolean isFictional() {
        return true;
    }

    @Override
    public String getInventoryName() {
        return "CDCS Shopbuilt Caboose";
    }
}
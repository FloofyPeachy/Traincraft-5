package com.jcirmodelsquad.tcjcir.vehicles.locomotives.steam;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;
import train.common.api.LiquidManager;
import train.common.api.SteamTrain;
import train.common.core.util.TraincraftUtil;
import train.common.enums.LockoutGroup;
import train.common.library.EnumSounds;
import train.common.library.EnumTrains;
import train.common.library.sounds.SoundRecord;

public class SteamP01a extends SteamTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.SteamP01a;
	}

	public SteamP01a(World world) {
		super(world, EnumTrains.P01a.getTankCapacity(), LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "WRX 10", LockoutGroup.BIDA);
		InsertTexture(1, "WRX 12", LockoutGroup.BIDA);
		InsertTexture(2, "CDC&S 7", LockoutGroup.CDCS);
	}

	@Override
	public void updateRiderPosition() { TraincraftUtil.updateRider(this, -0.9, 0.1, -0.4); }

	@Override
	public String getInventoryName() {
		return "P01a 2-6-2 Prairie";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.05F;
	}

	@Override
	public boolean isFictional() {
		return true;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}
}
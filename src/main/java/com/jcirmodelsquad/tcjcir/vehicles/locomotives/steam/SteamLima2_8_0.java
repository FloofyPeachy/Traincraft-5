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

public class SteamLima2_8_0 extends SteamTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.SteamLima2_8_0;
	}
	public SteamLima2_8_0(World world) {
		super(world, EnumTrains.Lima2_8_0.getTankCapacity(), LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "Generic");
		InsertTexture(1, "CDCS 20", LockoutGroup.CDCS);
		InsertTexture(2, "CDCS 21", LockoutGroup.CDCS);
		InsertTexture(3, "CDCS 22", LockoutGroup.CDCS);
		InsertTexture(4, "A&WRR");
		InsertTexture(5, "CRIP");
	}

	@Override
	public void updateRiderPosition() { TraincraftUtil.updateRider(this, -0.72, 0.05, -0.45); }

	@Override
	public String getInventoryName() {
		return "Lima 2-8-0";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.17F;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}
}
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

public class SteamAlco460 extends SteamTrain {

	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.SteamAlco460;
	}
	public SteamAlco460(World world) {
		super(world, EnumTrains.Alco460.getTankCapacity(), LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "Blank");
		InsertTexture(1, "CDC&S", LockoutGroup.CDCS);
		InsertTexture(2, "RI");
		InsertTexture(3, "%Lemo");
		InsertTexture(4, "GCM 67", LockoutGroup.GCM);
		InsertTexture(5, "GCM 60", LockoutGroup.GCM);
	}

	@Override
	public void updateRiderPosition() { TraincraftUtil.updateRider(this, -0.6, 0.1, -0.45); }

	@Override
	public String getInventoryName() {
		return "ALCo 4-6-0";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.2F;
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}
}
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

public class SteamAlco0_6_0T extends SteamTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.SteamAlco0_6_0T;
	}
	public SteamAlco0_6_0T(World world) {
		super(world, EnumTrains.Alco0_6_0T.getTankCapacity(), LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "Generic");
		InsertTexture(1, "CDCS", LockoutGroup.CDCS);
		InsertTexture(2, "SPR", LockoutGroup.SPR);
	}

	@Override
	public void updateRiderPosition() { TraincraftUtil.updateRider(this, -0.33, -0.23, -0.45); }

	@Override
	public String getInventoryName() {
		return "Alco 0-6-0T";
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.15F;
	}

}
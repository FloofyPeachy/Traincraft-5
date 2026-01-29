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

public class SteamClimaxB extends SteamTrain {
	@Override
	public SoundRecord getSoundRecord()
	{
		return EnumSounds.SteamClimaxB;
	}
	public SteamClimaxB(World world) {
		super(world, EnumTrains.ClimaxB.getTankCapacity(), LiquidManager.WATER_FILTER);
		
		InsertTexture(0, "Hillcrest Lumber Co #9");
		InsertTexture(1, "Washaska Resources #6", LockoutGroup.BIDA);
		InsertTexture(2, "Washaska Resources #7", LockoutGroup.BIDA);
		InsertTexture(3, "Clarks Milling & Lumber Co", LockoutGroup.CDCS);
	}

	@Override
	public void updateRiderPosition() { TraincraftUtil.updateRider(this, 0.3, 0.0, -0.4); }

	@Override
	public String getInventoryName() {
		return "50 Ton Class B Climax";
	}

	@Override
	public String transportCountry()
	{
		return "US";
	}

	@Override
	public float getOptimalDistance(EntityMinecart cart) {
		return 1.0F;
	}

}
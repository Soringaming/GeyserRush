package me.soringaming.moon.korra.GeyserRush;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.BlockSource;
import com.projectkorra.projectkorra.util.ClickType;

public class GeyserRush extends WaterAbility implements AddonAbility {
	
	private Block sourceBlock;
	private boolean sourceSelected;

	public GeyserRush(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	public void selectSource() {
		sourceBlock = BlockSource.getWaterSourceBlock(player, 15, ClickType.SHIFT_DOWN, true, true, false, false, false);
		if (sourceBlock != null && !GeneralMethods.isRegionProtectedFromBuild(this, sourceBlock.getLocation())) {
			sourceSelected = true;
			start();
		}
	}

	@Override
	public long getCooldown() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isHarmlessAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void progress() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

}

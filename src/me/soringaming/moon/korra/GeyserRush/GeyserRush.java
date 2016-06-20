package me.soringaming.moon.korra.GeyserRush;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.BlockSource;
import com.projectkorra.projectkorra.util.ClickType;

public class GeyserRush extends WaterAbility implements AddonAbility {

	private Player player;
	private Location loc;
	private Vector dir;
	private Permission perm;
	private Block sourceBlock;
	private boolean sourceSelected;

	public GeyserRush(Player player) {
		super(player);
		this.player = player;
		this.loc = player.getLocation();
		this.dir = player.getEyeLocation().getDirection().normalize().multiply(1);
		start();
	}

	public void selectSource() {
		sourceBlock = BlockSource.getWaterSourceBlock(player, 15, ClickType.SHIFT_DOWN, true, true, false, false,
				false);
		if (sourceBlock != null && !GeneralMethods.isRegionProtectedFromBuild(this, sourceBlock.getLocation())) {
			sourceSelected = true;
			start();
		}
	}

	@Override
	public long getCooldown() {
		return 0;
	}

	@Override
	public Location getLocation() {
		return null;
	}

	@Override
	public String getName() {
		return "GeyserRush";
	}

	@Override
	public boolean isHarmlessAbility() {
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		return true;
	}

	@Override
	public void progress() {
		if (player.isOnline() || !player.isOnline()) {
			return;
		}

	}

	@Override
	public String getAuthor() {
		return "Soringaming & Moon243";
	}

	@Override
	public String getVersion() {
		return "v1.0";
	}

	@Override
	public void load() {
		ProjectKorra.plugin.getServer().getLogger().log(Level.INFO,
				getName() + " " + getVersion() + " Developed By " + getAuthor() + " Has Been Enabled");
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new GeyserRushListener(),
				ProjectKorra.plugin);
		perm = new Permission("bending.ability.GeyserRush");
		perm.setDefault(PermissionDefault.TRUE);
		ProjectKorra.plugin.getServer().getPluginManager().addPermission(perm);
	}

	@Override
	public void stop() {
		ProjectKorra.plugin.getServer().getLogger().log(Level.INFO,
				getName() + " " + getVersion() + " Developed By " + getAuthor() + " Has Been Disabled");
		ProjectKorra.plugin.getServer().getPluginManager().removePermission(perm);
		super.remove();
	}

}

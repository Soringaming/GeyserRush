package me.soringaming.moon.korra.GeyserRush;

import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;

public class GeyserRush extends WaterAbility implements AddonAbility {
	
	private Player player;
	private Location loc;
	private Vector dir;
	private Permission perm;
	
	public GeyserRush(Player player) {
		super(player);
		this.player = player;
		this.loc = player.getLocation();
		this.dir = player.getEyeLocation().getDirection().normalize().multiply(1);
		start();
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
		ProjectKorra.plugin.getServer().getLogger().log(Level.INFO, getName() + " " + getVersion() + " Developed By " + getAuthor() + " Has Been Enabled" );
		ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new GeyserRushListener(), ProjectKorra.plugin);
		perm = new Permission("bending.ability.GeyserRush");
		perm.setDefault(PermissionDefault.TRUE);
		ProjectKorra.plugin.getServer().getPluginManager().addPermission(perm);
	}

	@Override
	public void stop() {
		ProjectKorra.plugin.getServer().getLogger().log(Level.INFO, getName() + " " + getVersion() + " Developed By " + getAuthor() + " Has Been Disabled" );
		ProjectKorra.plugin.getServer().getPluginManager().removePermission(perm);
		super.remove();
	}

}

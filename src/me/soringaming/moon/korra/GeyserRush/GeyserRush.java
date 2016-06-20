package me.soringaming.moon.korra.GeyserRush;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.Vector;

import com.projectkorra.projectkorra.GeneralMethods;
import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.util.BlockSource;
import com.projectkorra.projectkorra.util.ClickType;
import com.projectkorra.projectkorra.util.TempBlock;

public class GeyserRush extends WaterAbility implements AddonAbility {
	
	private Player player;
	private Location loc;
	private Vector dir;
	private Permission perm;
	private Block sourceBlock;
	private boolean sourceSelected;
	
	private static final ConcurrentHashMap<Block, Long> GeyserHead = new ConcurrentHashMap<Block, Long>();
	private static final ConcurrentHashMap<Block, Long> GeyserMid = new ConcurrentHashMap<Block, Long>();
	private static final ConcurrentHashMap<Block, Long> GeyserTrail = new ConcurrentHashMap<Block, Long>();
;
	public GeyserRush(Player player) {
		super(player);
		this.player = player;
		this.loc = sourceBlock.getLocation();
		this.dir = player.getEyeLocation().getDirection().normalize().multiply(1);
		if(sourceSelected) {
			start();
		}
	}
	
	public boolean selectSource() {
		Block block = BlockSource.getWaterSourceBlock(player, 15, ClickType.SHIFT_DOWN, true, true, false, false, false);
		if (block != null && !GeneralMethods.isRegionProtectedFromBuild(this, sourceBlock.getLocation())) {
			sourceBlock = block;
			sourceSelected = true;
			return true;
		}
		return false;
	}
	
	public static void RevertHead(boolean doRevert) {
		for(Block b : GeyserHead.keySet()) {
			long time = GeyserHead.get(b);
			if(time < System.currentTimeMillis() || doRevert) {
				TempBlock.revertBlock(b, Material.AIR);
				GeyserHead.remove(b);
			}
		}
	}
	
	public static void RevertMid(boolean doRevert) {
		for(Block b : GeyserMid.keySet()) {
			long time = GeyserMid.get(b);
			if(time < System.currentTimeMillis() || doRevert) {
				TempBlock.revertBlock(b, Material.AIR);
				GeyserMid.remove(b);
			}
		}
	}
	
	public static void RevertTrail(boolean doRevert) {
		for(Block b : GeyserTrail.keySet()) {
			long time = GeyserTrail.get(b);
			if(time < System.currentTimeMillis() || doRevert) {
				TempBlock.revertBlock(b, Material.AIR);
				GeyserTrail.remove(b);
			}
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
		dir.setY(0);
		RevertHead(false);
		RevertMid(false);
		RevertTrail(false);
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

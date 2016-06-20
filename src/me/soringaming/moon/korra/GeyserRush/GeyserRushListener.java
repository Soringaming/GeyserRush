package me.soringaming.moon.korra.GeyserRush;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;

public class GeyserRushListener implements Listener {
	
	@EventHandler
	public void selectSource(PlayerAnimationEvent e) {
		Player p = e.getPlayer();
		if(canBend(p)) {
			new GeyserRush(p);
		}
		
	}
	
	public boolean canBend(Player player) {
		BendingPlayer bp = BendingPlayer.getBendingPlayer(player.getName());
		if(bp.canBend(CoreAbility.getAbility("GeyserRush"))) {
			return true;
		}
		return false;
	}
	
	

}

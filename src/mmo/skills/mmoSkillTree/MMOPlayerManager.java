package mmo.skills.mmoSkillTree;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class MMOPlayerManager {
	private Map<String, MMOPlayer> mmoPlayers;
	
	public MMOPlayerManager() {
		this.mmoPlayers = new HashMap<String, MMOPlayer>();
	}
	
	public MMOPlayer get(Player player) {
		String name = player.getName().toLowerCase();
		MMOPlayer mmoPlayer = mmoPlayers.get(name);
		if( mmoPlayer != null ) {
		    if( mmoPlayer.getPlayer().getEntityId() != player.getEntityId() ) {
		        mmoPlayers.remove(name);
		    } else {
		        return mmoPlayer;
		    }
		}
		//null, so make it.
		//mmoPlayer = Load From Storage.
		add(player);
		mmoPlayer = mmoPlayers.get(name);
		return mmoPlayer;
    }
	
	public void add(Player player) {
		mmoPlayers.put(player.getName().toLowerCase(), new MMOPlayer(player));
	}
}

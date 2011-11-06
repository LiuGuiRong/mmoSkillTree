package mmo.SkillTree.Events;

import mmo.SkillTree.GUI.SkillsGui;

import mmo.SkillTree.MMOSkillTree;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class ServerChatPlayerListener extends PlayerListener {
	
	private static SkillsGui gui;
	
	public ServerChatPlayerListener(){
		gui = new SkillsGui();
	}
	
	/*public void onPlayerChat(PlayerChatEvent event){
		Player p = event.getPlayer();
		String msg = event.getMessage().toLowerCase();
		if( msg.contains("hi") && msg.contains("server") ){
			p.sendMessage(ChatColor.RED + "[Server] " + ChatColor.WHITE + "Hi!");
		}
	}*/
	
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		gui.skillHint(p);
		p.sendMessage(ChatColor.RED + "[rph] " + ChatColor.WHITE + "The RPG mod I'm making doesn't do anything yet, " +
				"but you can see the progress." +
				" I also removed mcmmo because it's pretty boring. I added Heroes and some other things." +
				"\nDonate if you can too, please. <3");
	}
}
package mmo.SkillTree.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import mmo.Core.MMOListener;
import mmo.Core.DamageAPI.MMODamageEvent;
import mmo.SkillTree.MMOPlayer;
import mmo.SkillTree.MMOSkillTree;
import mmo.SkillTree.Weapons.WeaponSet;

public class CombatXpListener extends MMOListener {

	private static MMOSkillTree plugin;

	public CombatXpListener(MMOSkillTree plugin) {
		CombatXpListener.plugin = plugin;
	}

	@Override
	public void onMMOPVEDamage(MMODamageEvent event) {
		MMODamageEvent mmoDmgEvent = (MMODamageEvent) event;
		int dmg = mmoDmgEvent.getDamage();
		//Entity mob = event.getDefender();
		Entity attacker = mmoDmgEvent.getRealAttacker();
		if (attacker instanceof Player) {
			Player p = (Player) attacker;
			ItemStack wielding = p.getItemInHand();
			if (wielding != null) {
				WeaponSet weaponSet = itemSet(wielding);
				int expVal = dmg;

				MMOPlayer mmoPlayer = MMOSkillTree.mmoPlayerManager.get(p);
				mmoPlayer.addXp(expVal, weaponSet.getCombatSkillSet());
			}
		}
	}

	/*
	 * TODO: Extend item set to return this. :( Need to know what weaponSet an item is all over.
	 */
	private WeaponSet itemSet(ItemStack wielding) {
		int id = wielding.getTypeId();
		if (id == 278/*diamond*/ || id == 257/*iron*/ || id == 285/*gold*/ || id == 274/*stone*/ || id == 270/*wood*/) {
			return WeaponSet.Pickaxe;
		} else if (id == 277/*diamond*/ || id == 256/*iron*/ || id == 285/*gold*/ || id == 273/*stone*/ || id == 269/*wood*/) {
			return WeaponSet.Shovel;
		} else if (id == 279/*diamond*/ || id == 258/*iron*/ || id == 286/*gold*/ || id == 275/*stone*/ || id == 271/*wood*/) {
			return WeaponSet.Axe;
		} else if (id == 290 || id == 291 || id == 292 || id == 293 || id == 294) {
			return WeaponSet.Hoe;
		} else if (id == 268/*wood*/ || id == 272/*stone*/ || id == 267/*iron*/ || id == 283/*gold*/ || id == 276/*diamond*/) {
			return WeaponSet.Sword;
		} else if (id == 261) {
			return WeaponSet.Bow;
		} else {
			return WeaponSet.Unarmed; //This is actually going to make anything but the weapons count as tools, pretty much.  So you can hit with blocks to gain tool or unarmed combat xp
		}
	}
}
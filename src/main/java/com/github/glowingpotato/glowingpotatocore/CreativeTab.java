package com.github.glowingpotato.glowingpotatocore;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTab extends net.minecraft.creativetab.CreativeTabs {
	private ItemStack tabIcon;

	@Override
	public ItemStack getTabIconItem() {
		return tabIcon;
	}

	public CreativeTab(String label, Item item) {
		super(label);
		tabIcon = new ItemStack(item);
	}
}

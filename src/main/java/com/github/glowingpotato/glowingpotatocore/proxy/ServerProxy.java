package com.github.glowingpotato.glowingpotatocore.proxy;

import com.github.glowingpotato.glowingpotatocore.BlockBase;
import com.github.glowingpotato.glowingpotatocore.ItemBase;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ServerProxy implements IProxy {
	@Override
	public void registerItemRenderer(ItemBase item, int meta, String id) {
	}

	@Override
	public void registerItemRenderer(ItemBlock item, BlockBase block) {
	}

	@Override
	public void registerItemRenderer(Item item, int meta, String modid, String id) {
	}
}

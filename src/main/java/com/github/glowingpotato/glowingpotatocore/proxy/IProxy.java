package com.github.glowingpotato.glowingpotatocore.proxy;

import com.github.glowingpotato.glowingpotatocore.BlockBase;
import com.github.glowingpotato.glowingpotatocore.ItemBase;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public interface IProxy {
	void registerItemRenderer(Item item, int meta, String modid, String id);

	void registerItemRenderer(ItemBlock item, BlockBase block);

	void registerItemRenderer(ItemBase item, int meta, String id);
}

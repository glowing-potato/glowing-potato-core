package com.github.glowingpotato.glowingpotatocore.proxy;

import com.github.glowingpotato.glowingpotatocore.BlockBase;
import com.github.glowingpotato.glowingpotatocore.ItemBase;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy implements IProxy {
	@Override
	public void registerItemRenderer(Item item, int meta, String modid, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(modid.concat(":").concat(id), "inventory"));
	}

	@Override
	public void registerItemRenderer(ItemBase item, int meta, String id) {
		registerItemRenderer(item, meta, item.getModid(), id);
	}

	@Override
	public void registerItemRenderer(ItemBlock item, BlockBase block) {
		registerItemRenderer(item, 0, block.getModid(), block.getName());
	}
}

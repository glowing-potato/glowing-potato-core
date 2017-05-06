package com.github.glowingpotato.glowingpotatocore;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public abstract class ModBase<T extends ModBase<T, ?, ?, ?>, TBlocks extends ModBlockList<TBlocks, ? extends ModBase<T, TBlocks, ?, ?>>, TItems extends ModItemList<TItems, ? extends ModBase<T, ?, TItems, ?>>, TCreativeTabs extends ModCreativeTabList<TCreativeTabs, T>> {
	protected abstract Class<TBlocks> blockClass();

	protected abstract Class<TItems> itemClass();

	public abstract String githubUrl();

	public void preinit(FMLPreInitializationEvent event) {
		ModBlockList.init(blockClass());
		ModItemList.init(itemClass(), this);
	}
}

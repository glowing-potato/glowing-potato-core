package com.github.glowingpotato.glowingpotatocore;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class ModBlockList<T extends ModBlockList<T, ?>, TMod extends ModBase<TMod, ? extends ModBlockList<T, TMod>, ?, ?>>
		extends net.minecraft.init.Blocks {
	private static final Logger log = LogManager.getLogger();

	static void init(Class<? extends ModBlockList<?, ?>> list) {
		List<BlockBase> blocks = new ArrayList<BlockBase>();
		try {
			for (Field field : list.getFields()) {
				if (Modifier.isStatic(field.getModifiers()) && BlockBase.class.isAssignableFrom(field.getType())) {
					blocks.add((BlockBase) field.get(null));
				}
			}
		} catch (ReflectiveOperationException ex) {
			log.fatal("Unable to register blocks from {}", list);
			log.catching(ex);
		}
		for (BlockBase block : blocks) {
			GameRegistry.register(block);
			ItemBlock item = new ItemBlock(block);
			item.setRegistryName(block.getRegistryName());
			GameRegistry.register(item);
			block.registerModel(item);
		}
		log.info("Registered {} blocks from {}", blocks.size(), list);
	}
}

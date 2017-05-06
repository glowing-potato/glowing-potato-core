package com.github.glowingpotato.glowingpotatocore;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.glowingpotato.glowingpotatocore.item.BugReportItem;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

public abstract class ModItemList<T extends ModItemList<T, ?>, TMod extends ModBase<TMod, ?, ? extends ModItemList<T, TMod>, ?>>
		extends net.minecraft.init.Items {
	private static final Logger log = LogManager.getLogger();

	static void init(Class<? extends ModItemList<?, ?>> list, ModBase<?, ?, ?, ?> mod) {
		List<ItemBase> items = new ArrayList<ItemBase>();
		try {
			for (Field field : list.getFields()) {
				if (Modifier.isStatic(field.getModifiers()) && ItemBase.class.isAssignableFrom(field.getType())) {
					items.add((ItemBase) field.get(null));
				}
			}
		} catch (ReflectiveOperationException ex) {
			log.fatal("Unable to register items from {}", list);
			log.catching(ex);
		}
		BugReportItem bugReport = new BugReportItem(mod);
		bugReport.setModid(mod.getClass().getAnnotation(Mod.class).modid());
		items.add(bugReport);
		for (ItemBase item : items) {
			GameRegistry.register(item);
			item.registerModel();
		}
		log.info("Registered {} items from {}", items.size(), list);
	}
}

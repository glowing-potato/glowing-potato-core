package com.github.glowingpotato.glowingpotatocore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;

public abstract class ItemBase extends Item {
	private static final Logger _log = LogManager.getLogger();
	protected final Logger log;
	private String modid;
	private String name;

	public String getModid() {
		return modid;
	}

	public void setModid(String modid) {
		this.modid = modid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void registerModel() {
		GlowingPotatoCoreMod.PROXY.registerItemRenderer(this, 0, getName());
	}

	public ItemBase() {
		log = LogManager.getLogger();
		Class<?> cls = getClass();
		String clsName = cls.getSimpleName();
		if (clsName.endsWith("Item")) {
			setName(clsName.substring(0, clsName.length() - 4).toLowerCase());
		} else {
			setName(clsName.toLowerCase());
		}
		setUnlocalizedName(getName());
		setRegistryName(getName());
		try {
			for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
				if (element.getMethodName().equals("preinit")) {
					Class<?> modCls = Class.forName(element.getClassName());
					Mod mod = modCls.getAnnotation(Mod.class);
					if (mod != null) {
						setModid(mod.modid());
						break;
					}
				}
			}
		} catch (ReflectiveOperationException ex) {
			_log.fatal("Unable to determine mod class name", ex);
		}
	}
}

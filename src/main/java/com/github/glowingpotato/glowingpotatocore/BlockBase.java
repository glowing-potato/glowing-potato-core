package com.github.glowingpotato.glowingpotatocore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Mod;

public abstract class BlockBase extends Block {
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

	public void registerModel(ItemBlock item) {
		GlowingPotatoCoreMod.PROXY.registerItemRenderer(item, this);
	}

	private void _init() {
		Class<?> cls = getClass();
		String clsName = cls.getSimpleName();
		if (clsName.endsWith("Block")) {
			setName(clsName.substring(0, clsName.length() - 5).toLowerCase());
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

	protected BlockBase(Material blockMaterialIn, MapColor blockMapColorIn) {
		super(blockMaterialIn, blockMapColorIn);
		log = LogManager.getLogger();
		_init();
	}

	protected BlockBase(Material materialIn) {
		super(materialIn);
		log = LogManager.getLogger();
		_init();
	}
}

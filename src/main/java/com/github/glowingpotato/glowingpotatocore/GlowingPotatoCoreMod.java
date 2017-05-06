package com.github.glowingpotato.glowingpotatocore;

import com.github.glowingpotato.glowingpotatocore.proxy.IProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GlowingPotatoCoreMod.MODID, version = GlowingPotatoCoreMod.VERSION)
public class GlowingPotatoCoreMod extends ModBase<GlowingPotatoCoreMod, Blocks, Items, CreativeTabs> {
	public static final String MODID = "glowingpotatocore";
	public static final String VERSION = "1.0";
	@Mod.Instance(MODID)
	public static GlowingPotatoCoreMod INSTANCE;
	@SidedProxy(serverSide = "com.github.glowingpotato.glowingpotatocore.proxy.ServerProxy", clientSide = "com.github.glowingpotato.glowingpotatocore.proxy.ClientProxy")
	public static IProxy PROXY;

	@Override
	protected Class<Blocks> blockClass() {
		return Blocks.class;
	}

	@Override
	protected Class<Items> itemClass() {
		return Items.class;
	}

	@Override
	public String githubUrl() {
		return "https://github.com/glowing-potato/glowing-potato-core";
	}

	@Override
	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		super.preinit(event);
	}
}

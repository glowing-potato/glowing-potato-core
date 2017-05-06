package com.github.glowingpotato.glowingpotatocore.item;

import java.awt.Desktop;
import java.net.URI;

import com.github.glowingpotato.glowingpotatocore.CreativeTabs;
import com.github.glowingpotato.glowingpotatocore.GlowingPotatoCoreMod;
import com.github.glowingpotato.glowingpotatocore.ItemBase;
import com.github.glowingpotato.glowingpotatocore.ModBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class BugReportItem extends ItemBase {
	private static final long MIN_REPORT_PERIOD = 5000;
	private final ModBase<?, ?, ?, ?> mod;
	private long lastOpen;

	@Override
	public synchronized EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		long time = System.currentTimeMillis();
		if (lastOpen + MIN_REPORT_PERIOD < time) {
			try {
				Desktop.getDesktop().browse(new URI(mod.githubUrl().concat("/issues/new")));
			} catch (Exception ex) {
				log.error("Unable to open link", ex);
				player.sendStatusMessage(new TextComponentString(mod.githubUrl().concat("/issues/new")), false);
			}
		}
		lastOpen = time;
		return EnumActionResult.SUCCESS;
	}

	@Override
	public void registerModel() {
		GlowingPotatoCoreMod.PROXY.registerItemRenderer(this, 0, GlowingPotatoCoreMod.MODID, getName());
	}

	public BugReportItem(ModBase<?, ?, ?, ?> mod) {
		this.mod = mod;
		lastOpen = 0;
		setUnlocalizedName(getName().concat(".").concat(getModid()));
		setCreativeTab(CreativeTabs.BUG_REPORT);
	}
}

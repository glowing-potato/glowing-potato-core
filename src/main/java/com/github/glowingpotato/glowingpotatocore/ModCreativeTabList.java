package com.github.glowingpotato.glowingpotatocore;

public abstract class ModCreativeTabList<T extends ModCreativeTabList<T, ?>, TMod extends ModBase<TMod, ?, ?, ?>>
		extends CreativeTab {
	protected ModCreativeTabList() {
		super(null, null);
		throw new UnsupportedOperationException();
	}
}

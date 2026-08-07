package de.whitoutcookies.debug_changer.client;

import net.minecraft.client.Minecraft;

public final class ScreenAccess {

    private ScreenAccess() {
    }

    public static boolean isScreenOpen(Minecraft client) {
        return client.screen != null;
    }
}

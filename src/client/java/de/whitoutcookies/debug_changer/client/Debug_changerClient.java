package de.whitoutcookies.debug_changer.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class Debug_changerClient implements ClientModInitializer {

    public static KeyMapping debugMenuKeyBinding;
    private static boolean debugKeyHeld;
    public static final KeyMapping.Category DEBUG_CATEGORY = new KeyMapping.Category(
        Identifier.fromNamespaceAndPath("debug_changer", "debug")
    );

    @Override
    public void onInitializeClient() {
        // Register the F3 debug menu keybinding with custom category
        debugMenuKeyBinding = new KeyMapping(
            "key.debug_changer.open_debug_menu",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F3,
            DEBUG_CATEGORY
        );
        KeyMappingHelper.registerKeyMapping(debugMenuKeyBinding);
    }

    public static boolean isDebugKeyHeld() {
        return debugKeyHeld;
    }

    public static void setDebugKeyHeld(boolean held) {
        debugKeyHeld = held;
    }
}

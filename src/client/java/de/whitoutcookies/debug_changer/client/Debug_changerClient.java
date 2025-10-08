package de.whitoutcookies.debug_changer.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class Debug_changerClient implements ClientModInitializer {

    public static KeyBinding debugMenuKeyBinding;
    public static final KeyBinding.Category DEBUG_CATEGORY = new KeyBinding.Category(
        Identifier.of("debug_changer", "debug")
    );

    @Override
    public void onInitializeClient() {
        // Register the F3 debug menu keybinding with custom category
        debugMenuKeyBinding = new KeyBinding(
            "key.debug_changer.open_debug_menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F3,
            DEBUG_CATEGORY
        );
        KeyBindingHelper.registerKeyBinding(debugMenuKeyBinding);
    }
}

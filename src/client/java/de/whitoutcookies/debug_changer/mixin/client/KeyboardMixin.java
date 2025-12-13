package de.whitoutcookies.debug_changer.mixin.client;

import de.whitoutcookies.debug_changer.client.Debug_changerClient;
import de.whitoutcookies.debug_changer.mixin.client.accessor.KeyInputAccessor;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyInput;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Shadow @Final
    private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"))
    private void debug_changer$trackDebugKey(long window, int action, KeyInput keyInput, CallbackInfo ci) {
        if (this.client.getWindow().getHandle() != window) {
            return;
        }

        if (Debug_changerClient.debugMenuKeyBinding != null
            && Debug_changerClient.debugMenuKeyBinding.matchesKey(keyInput)) {
            Debug_changerClient.setDebugKeyHeld(action != GLFW.GLFW_RELEASE);
        }
    }

    @ModifyVariable(method = "onKey", at = @At("HEAD"), argsOnly = true)
    private KeyInput debug_changer$remapDebugKey(KeyInput keyInput) {
        if (this.client.currentScreen == null && Debug_changerClient.debugMenuKeyBinding.matchesKey(keyInput)) {
            int scancode = GLFW.glfwGetKeyScancode(GLFW.GLFW_KEY_F3);
            if (scancode == GLFW.GLFW_KEY_UNKNOWN) {
                scancode = keyInput.scancode();
            }
            return KeyInputAccessor.debug_changer$create(GLFW.GLFW_KEY_F3, scancode, keyInput.modifiers());
        }
        return keyInput;
    }
}

package de.whitoutcookies.debug_changer.mixin.client;

import de.whitoutcookies.debug_changer.DebugHudToggleable;
import de.whitoutcookies.debug_changer.client.Debug_changerClient;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Shadow @Final
    private MinecraftClient client;

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void onKeyPressed(long window, int action, KeyInput keyInput, CallbackInfo ci) {
        if (this.client.getWindow().getHandle() == window && action == 1) {
            // Check if our custom debug keybinding is pressed using the bound key
            if (Debug_changerClient.debugMenuKeyBinding.matchesKey(keyInput)) {
                // Toggle F3 debug info screen (FPS, coordinates, etc.)
                DebugHudToggleable debugHud = (DebugHudToggleable) this.client.inGameHud.getDebugHud();
                debugHud.toggleDebug();
                ci.cancel();
            }
        }
    }
}

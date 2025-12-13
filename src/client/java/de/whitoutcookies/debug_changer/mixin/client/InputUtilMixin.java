package de.whitoutcookies.debug_changer.mixin.client;

import de.whitoutcookies.debug_changer.client.Debug_changerClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InputUtil.class)
public class InputUtilMixin {

    @Inject(method = "isKeyPressed", at = @At("HEAD"), cancellable = true)
    private static void debug_changer$spoofF3(Window window, int key, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return;
        }

        if (key == GLFW.GLFW_KEY_F3
            && window == client.getWindow()
            && Debug_changerClient.isDebugKeyHeld()
            && client.currentScreen == null) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}

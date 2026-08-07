package de.whitoutcookies.debug_changer.mixin.client;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;
import de.whitoutcookies.debug_changer.client.Debug_changerClient;
import de.whitoutcookies.debug_changer.client.ScreenAccess;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InputConstants.class)
public class InputConstantsMixin {

    @Inject(method = "isKeyDown", at = @At("HEAD"), cancellable = true)
    private static void debug_changer$spoofF3(Window window, int key, CallbackInfoReturnable<Boolean> cir) {
        Minecraft client = Minecraft.getInstance();
        if (client == null) {
            return;
        }

        if (key == GLFW.GLFW_KEY_F3
            && window == client.getWindow()
            && Debug_changerClient.isDebugKeyHeld()
            && !ScreenAccess.isScreenOpen(client)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}

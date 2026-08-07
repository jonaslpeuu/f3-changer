package de.whitoutcookies.debug_changer.mixin.client;

import de.whitoutcookies.debug_changer.client.Debug_changerClient;
import de.whitoutcookies.debug_changer.client.ScreenAccess;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.input.KeyEvent;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Inject(method = "keyPress", at = @At("HEAD"))
    private void debug_changer$trackDebugKey(long handle, int action, KeyEvent event, CallbackInfo ci) {
        if (this.minecraft.getWindow().handle() != handle) {
            return;
        }

        if (Debug_changerClient.debugMenuKeyBinding != null
            && Debug_changerClient.debugMenuKeyBinding.matches(event)) {
            Debug_changerClient.setDebugKeyHeld(action != GLFW.GLFW_RELEASE);
        }
    }

    @ModifyVariable(method = "keyPress", at = @At("HEAD"), argsOnly = true)
    private KeyEvent debug_changer$remapDebugKey(KeyEvent event) {
        if (!ScreenAccess.isScreenOpen(this.minecraft) && Debug_changerClient.debugMenuKeyBinding.matches(event)) {
            int scancode = GLFW.glfwGetKeyScancode(GLFW.GLFW_KEY_F3);
            if (scancode == GLFW.GLFW_KEY_UNKNOWN) {
                scancode = event.scancode();
            }
            return new KeyEvent(GLFW.GLFW_KEY_F3, scancode, event.modifiers());
        }
        return event;
    }
}

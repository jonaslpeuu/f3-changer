package de.whitoutcookies.debug_changer.mixin.client;

import de.whitoutcookies.debug_changer.DebugHudToggleable;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DebugHud.class)
public class DebugHudMixin implements DebugHudToggleable {

    @Shadow
    private boolean showDebugHud;

    @Override
    public void toggleDebug() {
        this.showDebugHud = !this.showDebugHud;
    }
}

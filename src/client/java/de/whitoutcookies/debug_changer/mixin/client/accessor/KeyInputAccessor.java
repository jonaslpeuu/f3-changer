package de.whitoutcookies.debug_changer.mixin.client.accessor;

import net.minecraft.client.input.KeyInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(KeyInput.class)
public interface KeyInputAccessor {

    @Invoker("<init>")
    static KeyInput debug_changer$create(int key, int scancode, int modifiers) {
        throw new AssertionError("Mixin invoker not transformed");
    }
}

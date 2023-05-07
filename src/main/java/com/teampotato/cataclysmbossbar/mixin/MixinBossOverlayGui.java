package com.teampotato.cataclysmbossbar.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teampotato.cataclysmbossbar.CataclysmBossBar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.BossHealthOverlay;
import net.minecraft.client.gui.components.LerpingBossEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(value = BossHealthOverlay.class, priority = 10)
public abstract class MixinBossOverlayGui {
    @Shadow @Final Map<UUID, LerpingBossEvent> events;
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "render", at = @At("TAIL"))
    private void displayCataclysmBossBar(PoseStack matrix, CallbackInfo ci) {
        CataclysmBossBar.display(events, matrix, minecraft);
    }
}

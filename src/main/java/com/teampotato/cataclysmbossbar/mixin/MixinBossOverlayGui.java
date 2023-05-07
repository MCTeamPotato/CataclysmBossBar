package com.teampotato.cataclysmbossbar.mixin;

import L_Ender.cataclysm.init.ModEntities;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.teampotato.cataclysmbossbar.CataclysmBossBar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.ClientBossInfo;
import net.minecraft.client.gui.overlay.BossOverlayGui;
import net.minecraft.world.BossInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Mixin(value = BossOverlayGui.class, priority = 10)
public abstract class MixinBossOverlayGui {
    @Shadow @Final private Map<UUID, ClientBossInfo> events;
    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "render", at = @At("TAIL"))
    private void displayBossBar(MatrixStack matrix, CallbackInfo ci) {
        if (this.events.isEmpty()) return;
        int width = this.minecraft.getWindow().getGuiScaledWidth();
        int j = 12;
        for (ClientBossInfo info : this.events.values()) {
            int k = width / 2 - 91;
            String name = info.getName().toString();
            if (name.contains(Objects.requireNonNull(ModEntities.NETHERITE_MONSTROSITY.get().getRegistryName()).getPath()) || name.contains(Objects.requireNonNull(ModEntities.ENDER_GUARDIAN.get().getRegistryName()).getPath())) {
                this.minecraft.textureManager.bind(CataclysmBossBar.TEXTURE);
                if (info.getColor().equals(BossInfo.Color.PURPLE)) {
                    AbstractGui.blit(matrix, k - 7, j - 2, -4.0F, 9, 192, 9, 256, 256);
                } else if (info.getColor().equals(BossInfo.Color.RED)) {
                    AbstractGui.blit(matrix, k - 7, j - 2, -4.0F, 0, 192, 9, 256, 256);
                }
            }
            j += 10 + 9;
            if (j > this.minecraft.getWindow().getGuiScaledHeight() / 3) break;
        }
    }
}

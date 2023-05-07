package com.teampotato.cataclysmbossbar;

import L_Ender.cataclysm.init.ModEntities;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.LerpingBossEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.BossEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Mod(CataclysmBossBar.ID)
public class CataclysmBossBar {
    public static final ResourceLocation TEXTURE = new ResourceLocation("cataclysm", "textures/gui/bossbar_frames.png");
    public static final String ID = "cataclysmbossbar";

    public static void display(Map<UUID, LerpingBossEvent> events, PoseStack matrix, Minecraft minecraft) {
        if (events.isEmpty()) return;
        int width = minecraft.getWindow().getGuiScaledWidth();
        int j = 12;
        for (LerpingBossEvent info : events.values()) {
            int k = width / 2 - 91;
            String name = info.getName().toString();
            if (name.contains(Objects.requireNonNull(ModEntities.NETHERITE_MONSTROSITY.get().getRegistryName()).getPath()) || name.contains(Objects.requireNonNull(ModEntities.ENDER_GUARDIAN.get().getRegistryName()).getPath())) {
                RenderSystem.setShaderTexture(0, CataclysmBossBar.TEXTURE);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                if (info.getColor().equals(BossEvent.BossBarColor.PURPLE)) {
                    GuiComponent.blit(matrix, k - 7, j - 2, -4.0F, 9, 192, 9, 256, 256);
                } else if (info.getColor().equals(BossEvent.BossBarColor.RED)) {
                    GuiComponent.blit(matrix, k - 7, j - 2, -4.0F, 0, 192, 9, 256, 256);
                }
            }
            j += 10 + 9;
            if (j > minecraft.getWindow().getGuiScaledHeight() / 3) break;
        }
    }
}

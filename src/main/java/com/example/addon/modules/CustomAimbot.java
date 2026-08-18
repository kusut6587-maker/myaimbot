package com.example.addon.modules;

import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.DoubleSetting;
import meteordevelopment.meteorclient.settings.Setting;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.systems.modules.Categories;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class CustomAimbot extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Double> range = sgGeneral.add(new DoubleSetting.Builder()
        .name("menzil-blok")
        .description("Aimbot un oyunculari takip edecegi maksimum blok mesafesi.")
        .defaultValue(6.0)
        .min(1.0)
        .max(64.0)
        .sliderMax(30.0)
        .build()
    );

    public CustomAimbot() {
        super(Categories.Combat, "custom-aimbot", "Menzil icindeki oyunculari otomatik takip eder.");
    }

    @EventHandler
    private void onTick(TickEvent.Pre event) {
        if (mc.player == null || mc.world == null) return;

        PlayerEntity target = getClosestPlayer();

        if (target != null) {
            lookAtPlayer(target);
        }
    }

    private PlayerEntity getClosestPlayer() {
        PlayerEntity closest = null;
        double minDistance = range.get();

        for (PlayerEntity player : mc.world.getPlayers()) {
            if (player == mc.player || player.isSpectator() || !player.isAlive()) continue;

            double distance = mc.player.distanceTo(player);
            if (distance <= minDistance) {
                minDistance = distance;
                closest = player;
            }
        }

        return closest;
    }

    private void lookAtPlayer(PlayerEntity target) {
        Vec3d targetPos = target.getEyePos();
        Vec3d playerPos = mc.player.getEyePos();

        double diffX = targetPos.x - playerPos.x;
        double diffY = targetPos.y - playerPos.y;
        double diffZ = targetPos.z - playerPos.z;

        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);

        float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90F;
        float pitch = (float) -Math.toDegrees(Math.atan2(diffY, diffXZ));

        mc.player.setYaw(yaw);
        mc.player.setPitch(pitch);
    }
}

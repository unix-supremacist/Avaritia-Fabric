package net.laith.avaritia.common.handler;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ArmorHandler implements ServerTickEvents.StartTick, ServerLivingEntityEvents.AllowDamage, ClientTickEvents.StartTick {
    public static final AbilitySource avaritiaFlight = Pal.getAbilitySource(AvaritiaMod.MOD_ID, "flight");

    public void onServerTick(ServerPlayerEntity player) {
        // Check if the player is wearing the specific armor set

        if (BooleanHelper.isWearingChestplate(player)) {
            // Enable flying
            avaritiaFlight.grantTo(player, VanillaAbilities.ALLOW_FLYING);
        } else {
            avaritiaFlight.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
        }

        if(BooleanHelper.isWearingBoots(player)) {
            player.setStepHeight(1.08f);
        }
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player: server.getPlayerManager().getPlayerList()) {
            onServerTick(player);
        }
    }

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity player) {
            if(BooleanHelper.isWearingTheFullArmor(player)) {
                return false;
            }
        }
            return true;
    }

    @Override
    public void onStartTick(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        if (player != null) {
            if (BooleanHelper.isWearingBoots(player)) {
                    boolean flying = player.getAbilities().flying;
                    boolean swimming = player.isSwimming();
                    if (player.isOnGround() || flying || swimming) {
                        boolean sneaking = player.isSneaking();

                        float speed = 0.15f * (flying ? 1.1f : 1.0f)
                                * (swimming ? 1.2f : 1.0f)
                                * (sneaking ? 0.1f : 1.0f);

                        if (player.forwardSpeed > 0f) {
                            player.updateVelocity(speed, new Vec3d(0, 0, 1));
                        } else if (player.forwardSpeed < 0f) {
                            player.updateVelocity(-speed * 0.3f, new Vec3d(0, 0, 1));
                        }

                        if (player.sidewaysSpeed != 0f) {
                            player.updateVelocity(speed * 0.5f * Math.signum(player.sidewaysSpeed), new Vec3d(1, 0, 0));
                        }
                    }
                }
        }
    }
}


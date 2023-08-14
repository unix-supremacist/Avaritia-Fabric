package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.util.ToolHelper;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class InfinityPickaxe extends PickaxeItem {

    public InfinityPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            NbtCompound tags = stack.getOrCreateNbt();
            if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) < 10) {
                stack.addEnchantment(Enchantments.FORTUNE, 10);
            }
            tags.putBoolean("hammer", !tags.getBoolean("hammer"));
            user.setMainArm(Arm.RIGHT);
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getOrCreateNbt().getBoolean("hammer")) {
            if (!(target instanceof PlayerEntity)) {
                int i = 10;
                target.addVelocity(-MathHelper.sin(attacker.getYaw() * (float) Math.PI / 180.0F) * i * 0.5F, 2.0D, MathHelper.cos(attacker.getYaw() * (float) Math.PI / 180.0F) * i * 0.5F);
            }
        }
        return true;
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (stack.getOrCreateNbt().getBoolean("hammer")) {
            if (state.isIn(BlockTags.PICKAXE_MINEABLE)) {
                ToolHelper.mineCube((PlayerEntity) miner, world);
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }
}
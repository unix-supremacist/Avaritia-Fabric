package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(AvaritiaMod.MOD_ID, "shaped"),
                ExtremeCraftingShapedRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(AvaritiaMod.MOD_ID, "extreme_crafting"),
                ExtremeCraftingShapedRecipe.Type.INSTANCE);
    }
}

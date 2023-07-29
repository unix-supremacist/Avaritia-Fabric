package net.laith.avaritia.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.item.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final Item INFINITY_CATALYST = registerItem("infinity_catalyst",
            new InfinityCatalyst(new FabricItemSettings()));

    public static final Item CRYSTAL_MATRIX_INGOT = registerItem("crystal_matrix_ingot",
            new CrystalMatrixIngot(new FabricItemSettings()));

    public static final Item INFINITY_INGOT = registerItem("infinity_ingot",
            new InfinityIngot(new FabricItemSettings()));

    public static final Item RECORD_FRAGMENT = registerItem("record_fragment",
            new RecordFragment(new FabricItemSettings()));

    public static final Item NEUTRONIUM_INGOT = registerItem("neutronium_ingot",
            new NeutroniumIngot(new FabricItemSettings()));

    public static final Item NEUTRONIUM_NUGGET = registerItem("neutronium_nugget",
            new NeutroniumNugget(new FabricItemSettings()));

    public static final Item PILE_OF_NEUTRONS = registerItem("pile_of_neutrons",
            new PileOfNeutrons(new FabricItemSettings()));

    public static final Item ULTIMATE_STEW = registerItem("ultimate_stew",
            new Item(new FabricItemSettings()));

    public static final Item DIAMOND_LATTICE = registerItem("diamond_lattice",
            new Item(new FabricItemSettings()));

    public static final Item COSMIC_MEATBALLS = registerItem("cosmic_meatballs",
            new Item(new FabricItemSettings()));

    public static final Item ENDEST_PEARL = registerItem("endest_pearl",
            new Item(new FabricItemSettings()));

    public static final Item IRON_SINGULARITY = registerItem("iron_singularity",
            new Item(new FabricItemSettings()));

    public static final Item GOLD_SINGULARITY = registerItem("gold_singularity",
            new Item(new FabricItemSettings()));

    public static final Item LAPIS_SINGULARITY = registerItem("lapis_singularity",
            new Item(new FabricItemSettings()));

    public static final Item REDSTONE_SINGULARITY = registerItem("redstone_singularity",
            new Item(new FabricItemSettings()));

    public static final Item COPPER_SINGULARITY = registerItem("copper_singularity",
            new Item(new FabricItemSettings()));

    public static final Item QUARTZ_SINGULARITY = registerItem("quartz_singularity",
            new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,
                new Identifier(AvaritiaMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        AvaritiaMod.LOGGER.info("Registering Mod Items for " + AvaritiaMod.MOD_ID);
    }
}


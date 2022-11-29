package com.stal111.valhelsia_structures.common.world.structures.pools;

import com.stal111.valhelsia_structures.core.ValhelsiaStructures;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.valhelsia.valhelsia_core.core.registry.RegistryClass;
import net.valhelsia.valhelsia_core.core.registry.helper.TemplatePoolRegistryHelper;


/**
 * @author Valhelsia Team
 */
public class SpawnerDungeonPools implements RegistryClass {

    public static final TemplatePoolRegistryHelper HELPER = ValhelsiaStructures.REGISTRY_MANAGER.getHelper(Registry.TEMPLATE_POOL_REGISTRY);

    public static final Holder<StructureTemplatePool> PATTERN = HELPER.register("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrances", builder -> builder.element("large").element("small_1").element("small_2").element("small_3").removeWater(), TerrainAdjustment.BEARD_THIN);

    public static final Holder<StructureTemplatePool> LARGE_CAVE_ENTRANCE = HELPER.register("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/large_bottom", builder -> builder.element("large_bottom").removeWater());
    public static final Holder<StructureTemplatePool> SMALL_1_CAVE_ENTRANCE = HELPER.register("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_1_bottom", builder -> builder.element("small_1_bottom").removeWater());
    public static final Holder<StructureTemplatePool> SMALL_2_CAVE_ENTRANCE = HELPER.register("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_2_bottom", builder -> builder.element("small_2_bottom").removeWater());
    public static final Holder<StructureTemplatePool> SMALL_3_CAVE_ENTRANCE = HELPER.register("spawner_dungeon/cave_entrance", "spawner_dungeon/cave_entrance/small_3_bottom", builder -> builder.element("small_3_bottom").removeWater());

    public static final Holder<StructureTemplatePool> CAVES = HELPER.register("spawner_dungeon/cave", "spawner_dungeon/caves", builder -> builder.element("cave_1").element("cave_2").removeWater());
    public static final Holder<StructureTemplatePool> ENTRANCES = HELPER.register("spawner_dungeon/entrance", "spawner_dungeon/entrances", builder -> builder.element("entrance_1").element("entrance_2").removeWater());
    public static final Holder<StructureTemplatePool> MAIN_ROOMS = HELPER.register("spawner_dungeon/main_room", "spawner_dungeon/main_rooms", builder -> builder.element("main_room_1").element("main_room_2").element("main_room_3").removeWater());
    public static final Holder<StructureTemplatePool> SIDE_ROOMS = HELPER.register("spawner_dungeon/side_room", "spawner_dungeon/side_rooms", builder -> builder.element("side_room_1").element("side_room_2").element("side_room_3").element("side_room_4").removeWater());
    public static final Holder<StructureTemplatePool> SPAWNER_ROOMS = HELPER.register("spawner_dungeon/spawner_room", "spawner_dungeon/spawner_rooms", builder -> builder.element("zombie_spawner_room").element("skeleton_spawner_room").element("spider_spawner_room").removeWater());

    public static final Holder<StructureTemplatePool> STAIRS = HELPER.register("spawner_dungeon/stairs", "spawner_dungeon/stairs", builder -> builder.element("stairs_1").element("stairs_2").element("stairs_3").element("stairs_4").element("stairs_5").element("stairs_6").removeWater());
    public static final Holder<StructureTemplatePool> PASSAGES = HELPER.register("spawner_dungeon/passage", "spawner_dungeon/passages", builder -> builder.element("passage_1").element("passage_2").element("passage_3").element("passage_4").element("passage_5").element("passage_6").removeWater());

}
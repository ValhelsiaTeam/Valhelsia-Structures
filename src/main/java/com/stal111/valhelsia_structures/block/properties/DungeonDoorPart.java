package com.stal111.valhelsia_structures.block.properties;

import net.minecraft.util.IStringSerializable;

/**
 * Dungeon Door Part
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.properties.DungeonDoorPart
 *
 * @author Valhelsia Team
 * @version 16.1.0
 * @since 2021-01-18
 */
public enum DungeonDoorPart implements IStringSerializable {
    LEFT_1("left_1", true),
    LEFT_2("left_2"),
    LEFT_3("left_3"),
    LEFT_4("left_4"),
    MIDDLE_1("middle_1", true),
    MIDDLE_2("middle2"),
    MIDDLE_3("middle_3"),
    MIDDLE_4("middle_4"),
    RIGHT_1("right_1", true),
    RIGHT_2("right_2"),
    RIGHT_3("right_3"),
    RIGHT_4("right_4");

    private final String name;
    private final boolean bottom;

    DungeonDoorPart(String name) {
        this.name = name;
        this.bottom = false;
    }

    DungeonDoorPart(String name, boolean bottom) {
        this.name = name;
        this.bottom = bottom;
    }

    @Override
    public String getString() {
        return this.name;
    }

    public boolean isBottom() {
        return bottom;
    }
}

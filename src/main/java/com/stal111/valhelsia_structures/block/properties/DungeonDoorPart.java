package com.stal111.valhelsia_structures.block.properties;

import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

/**
 * Dungeon Door Part <br>
 * Valhelsia Structures - com.stal111.valhelsia_structures.block.properties.DungeonDoorPart
 *
 * @author Valhelsia Team
 * @version 1.17.1-0.1.0
 * @since 2021-01-18
 */
public enum DungeonDoorPart implements StringRepresentable {
    LEFT_1("left_1"),
    LEFT_2("left_2"),
    LEFT_3("left_3"),
    LEFT_4("left_4"),
    MIDDLE_1("middle_1"),
    MIDDLE_2("middle2"),
    MIDDLE_3("middle_3"),
    MIDDLE_4("middle_4"),
    RIGHT_1("right_1"),
    RIGHT_2("right_2"),
    RIGHT_3("right_3"),
    RIGHT_4("right_4");

    private final String name;

    DungeonDoorPart(String name) {
        this.name = name;
    }

    @Nonnull
    @Override
    public String getSerializedName() {
        return this.name;
    }

    public boolean isBottom() {
        return this.name.endsWith("1");
    }

    public boolean isLeft() {
        return this.name.startsWith("left");
    }

    public boolean isRight() {
        return this.name.startsWith("right");
    }

    public boolean isMiddle() {
        return this.name.startsWith("middle");
    }
}

package me.apika.projectlaura

import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour

object CommonObject {
    fun init() {}

    fun register(registration: Registration) {
        val exampleBlock = registration.registerBlock("example_block", {
            SlabBlock(
                BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.GRAVEL)
                    .lightLevel { 7 }
            )
        })
        registration.registerItem("example_block", { BlockItem(exampleBlock.get(), Item.Properties()) })
    }
}

package me.apika.projectlaura

import me.apika.projectlaura.platform.Services
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour

object CommonObject {
    fun init() {
        Constants.LOG.info(
            "Hello from Common init on {}! we are currently in a {} environment!",
            Services.PLATFORM.getPlatformName(),
            Services.PLATFORM.getEnvironmentName()
        )
        Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND))
    }

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

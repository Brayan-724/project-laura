package me.apika.projectlaura

import net.fabricmc.api.ModInitializer
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block


fun init() {
    CommonObject.init()
}

class ExampleModBlocks : ModInitializer {
    override fun onInitialize() {
        CommonObject.register(FabricRegistration())
    }
}

class FabricRegistration() : Registration {
    fun <T> register(
        registry: ResourceKey<Registry<T>>,
        name: String,
        value: (ResourceLocation) -> T
    ): RegistrationHolder<T> {
        val location = name.location()
        val holder = RegistrationHolder.create(registry, location)

        val registry = BuiltInRegistries.REGISTRY.get(registry.location()) as? Registry<T>
        if (registry == null) {
            throw NullPointerException("Cannot retrieve registry")
        } else {
            val value = value(location)
            if (value != null) {
                Registry.register<T, T>(registry, location, value)
            }
        }

        return holder
    }

    override fun registerItem(name: String, item: (ResourceLocation) -> Item): RegistrationHolder<Item> {
        return register(Registries.ITEM, name, item)
    }

    override fun registerBlock(name: String, block: (ResourceLocation) -> Block): RegistrationHolder<Block> {
        return register(Registries.BLOCK, name, block)
    }
}

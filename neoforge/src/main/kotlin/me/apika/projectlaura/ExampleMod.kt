package me.apika.projectlaura

import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.registries.RegisterEvent


@Mod(Constants.MOD_ID)
class ExampleMod(eventBus: IEventBus, modContainer: ModContainer) {
    init {
        eventBus.addListener(::register)
    }

    fun register(event: RegisterEvent) {
        CommonObject.register(NeoForgeRegistration(event))
    }
}

class NeoForgeRegistration(val event: RegisterEvent) : Registration {
    fun <T> register(
        registry: ResourceKey<Registry<T>>,
        name: String,
        value: (ResourceLocation) -> T
    ): RegistrationHolder<T> {
        val location = name.location()
        val holder = RegistrationHolder.create(registry, location)

        event.register<T>(registry, location, {value(location)})

        return holder
    }

    override fun registerItem(name: String, item: (ResourceLocation) -> Item): RegistrationHolder<Item> {
        return register(Registries.ITEM, name, item)
    }

    override fun registerBlock(name: String, block: (ResourceLocation) -> Block): RegistrationHolder<Block> {
        return register(Registries.BLOCK, name, block)
    }
}

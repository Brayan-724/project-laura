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
import net.neoforged.neoforge.registries.DeferredHolder
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.RegisterEvent


//
//val EXAMPLE_BLOCK: DeferredHolder<Block, SlabBlock> = BLOCKS.register("example_block") { _ ->
//    SlabBlock(
//        BlockBehaviour.Properties.of()
//            .destroyTime(2.0f)
//            .explosionResistance(10.0f)
//            .sound(SoundType.GRAVEL)
//            .lightLevel { 7 }
//    )
//}

@Mod(Constants.MOD_ID)
class ExampleMod(eventBus: IEventBus, modContainer: ModContainer) {
    init {
//        DeferredRegister.Blocks.createBlocks("").register<>()
        eventBus.addListener(::register)

        Constants.LOG.info("Hello NeoForge world from Kotlin!")
    }

    fun register(event: RegisterEvent) {
        CommonObject.register(NeoForgeRegistration(event))
    }
}

class NeoForgeRegistration(val event: RegisterEvent) : Registration {
    fun location(name: String): ResourceLocation {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name)
    }

    fun <T> register(
        registry: ResourceKey<Registry<T>>,
        name: String,
        value: (ResourceLocation) -> T
    ): RegistrationHolder<T> {
        val location = location(name)
        event.register<T>(registry, location, {value(location)})
        val holder = RegistrationHolder.create(registry, location)
        return holder
    }

    override fun registerItem(name: String, item: (ResourceLocation) -> Item): RegistrationHolder<Item> {
        return register(Registries.ITEM, name, item)
    }

    override fun registerBlock(name: String, block: (ResourceLocation) -> Block): RegistrationHolder<Block> {
        return register(Registries.BLOCK, name, block)
    }
}

package me.apika.projectlaura

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricDefaultAttributeRegistry
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

fun init() {
    CommonObject.init()
}

class ExampleModBlocks : ModInitializer {
    override fun onInitialize() {
        Blocks.initialize(FabricRegistration())
        Entities.initialize(FabricRegistration())
        Items.initialize(FabricRegistration())

        AttributesRepository { entityType, attributeSupplier ->
            FabricDefaultAttributeRegistry.register(entityType, attributeSupplier)
        }

        EntityRenderers(object : EntityRenderers.Impl {
            override fun <E : Entity> register(entityType: EntityType<E>, rendererProvider: EntityRendererProvider<E>) {
                net.minecraft.client.renderer.entity.EntityRenderers.register(entityType, rendererProvider)
            }
        })
    }
}

class FabricRegistration<T> : RegistryRepository.Impl<T> {
    override fun register(
        registry: Registry<T>,
        name: ResourceLocation,
        value: Lazy<T>
    ) {
        Registry.register<T, T>(registry, name, value.value!!)
    }
}

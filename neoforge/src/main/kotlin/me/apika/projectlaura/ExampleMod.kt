package me.apika.projectlaura

import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.registries.RegisterEvent

@Mod(Constants.MOD_ID)
class ExampleMod(eventBus: IEventBus, modContainer: ModContainer) {
    init {
        eventBus.addListener(::attributes)
        eventBus.addListener(::entityRenderers)
        eventBus.addListener(::layers)
        eventBus.addListener(::register)
    }

    fun attributes(event: EntityAttributeCreationEvent) {
        AttributesRepository { entityType, attributeSupplier ->
            event.put(entityType, attributeSupplier)
        }
    }

    fun entityRenderers(event: RegisterRenderers) {
        EntityRenderers(object : EntityRenderers.Impl {
            override fun <E : Entity> register(entityType: EntityType<E>, rendererProvider: EntityRendererProvider<E>) {
                event.registerEntityRenderer(entityType, rendererProvider)
            }
        })
    }

    fun layers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        ModelLayers.initialize { layer, factory ->
            event.registerLayerDefinition(layer) { factory.create() }
        }
    }

    fun register(event: RegisterEvent) {
        class NeoForgeRegistration<T> : RegistryRepository.Impl<T> {
            override fun register(
                registry: Registry<T>,
                name: ResourceLocation,
                value: Lazy<T>
            ) {
                event.register<T>(registry.key(), name) { value.value }
            }
        }

        Blocks.initialize(NeoForgeRegistration())
        Entities.initialize(NeoForgeRegistration())
        Items.initialize(NeoForgeRegistration())
    }
}
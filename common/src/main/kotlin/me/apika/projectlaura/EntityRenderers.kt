package me.apika.projectlaura

import me.apika.projectlaura.client.render.SuperGolemRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType

class EntityRenderers(val impl: Impl) {
    private var registered = setOf<EntityType<*>>()

    init {
        register(Entities.EXAMPLE_ENTITY, ::SuperGolemRenderer)
    }

    private fun <E : Entity> register(entry: EntityType<E>, definition: EntityRendererProvider<E>) {
        check(!registered.contains(entry)) { "Duplicate registration for $entry" }

        impl.register(entry, definition)
    }

    interface Impl {
        fun <E : Entity> register(entityType: EntityType<E>, rendererProvider: EntityRendererProvider<E>)
    }
}

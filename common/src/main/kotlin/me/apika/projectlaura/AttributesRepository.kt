package me.apika.projectlaura

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.animal.coppergolem.CopperGolem

class AttributesRepository(val impl: Impl) {
    private var registered = setOf<EntityType<*>>()

    init {
        register(
            Entities.EXAMPLE_ENTITY,
            CopperGolem.createAttributes()
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.FOLLOW_RANGE, 20.0)
                .add(Attributes.MAX_HEALTH, 100.0)
                .build()
        )
    }

    private fun register(entry: EntityType<out LivingEntity>, definition: AttributeSupplier) {
        check(!registered.contains(entry)) { "Duplicate registration for $entry" }

        impl.register(entry, definition)
    }

    fun interface Impl {
        fun register(entityType: EntityType<out LivingEntity>, attributeSupplier: AttributeSupplier)
    }
}

package me.apika.projectlaura

import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.SpawnEggItem
import net.minecraft.world.item.component.TypedEntityData

object Items : RegistryRepository<Item>(BuiltInRegistries.ITEM) {
    val SPAWNER_EGG by registerItem("example_egg", ::SpawnEggItem) {
        it.component<TypedEntityData<EntityType<*>>>(DataComponents.ENTITY_DATA,
            TypedEntityData.of(Entities.EXAMPLE_ENTITY, CompoundTag()))
    }

    val EXAMPLE_BLOCK by registerItem("example_block") { prop ->
        BlockItem(Blocks.EXAMPLE_BLOCK, prop)
    }

    private fun <T : Item> registerItem(name: String, factory: ItemFactory<T>): Lazy<T> {
        return registerItem(name, factory) { it }
    }

    private fun <T : Item> registerItem(
        name: String, factory: ItemFactory<T>, builder: ItemFactory<Item.Properties>
    ): Lazy<T> {
        return register(name) { id ->
            factory.create(builder.create(Item.Properties().setId(id)))
        }
    }

    override fun initializeClient() {}

    fun interface ItemFactory<T> {
        fun create(properties: Item.Properties): T
    }
}
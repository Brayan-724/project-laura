package me.apika.projectlaura

import me.apika.projectlaura.entities.SuperGolem
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.entity.animal.coppergolem.CopperGolem


object Entities : RegistryRepository<EntityType<*>>(BuiltInRegistries.ENTITY_TYPE) {

    val EXAMPLE_ENTITY by registerEntity("example_entity", ::SuperGolem, MobCategory.MISC) {
        it.sized(0.35F, 0.70F).eyeHeight(0.5F).clientTrackingRange(10)
    }

    private fun <T : Entity> registerEntity(
        name: String, factory: EntityType.EntityFactory<T>, category: MobCategory
    ): Lazy<EntityType<T>> {
        return registerEntity(name, factory, category) { it }
    }

    private fun <T : Entity> registerEntity(
        name: String, factory: EntityType.EntityFactory<T>, category: MobCategory,
        config: (EntityType.Builder<T>) -> EntityType.Builder<T>
    ): Lazy<EntityType<T>> {
        return register(name) { id ->
            config(EntityType.Builder.of(factory, category)).build(id)
        }
    }

    override fun initializeClient() {}
}

//private fun <T : Entity> register(
//    name: String, builder: EntityType.Builder<T>
//): Supplier<EntityType<T>> {
//    val id = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name)
//
//    return Registration.register(BuiltInRegistries.ENTITY_TYPE, id) {
//        builder.build(ResourceKey.create(Registries.ENTITY_TYPE, id))
//    }
//}
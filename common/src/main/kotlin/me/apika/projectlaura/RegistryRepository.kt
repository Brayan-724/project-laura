package me.apika.projectlaura

import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import kotlin.jvm.optionals.getOrNull

abstract class RegistryRepository<T : Any>(protected val registry: Registry<T>) {
    val entries = mutableMapOf<String, Lazy<T>>()

    fun get(identifier: ResourceLocation): T? {
        return registry.get(identifier).getOrNull()?.value()
    }

    fun getKey(entry: T): ResourceLocation? {
        return registry.getKey(entry)
    }

    protected open fun <E : T> register(name: String, entry: (ResourceKey<T>) -> E): Lazy<E> {
        check(!entries.containsKey(name)) { "Entry was already registered: $name" }

        val location = name.location()
        val key = ResourceKey.create<T>(registry.key(), location)

        return lazy { entry(key) }.also { lazy ->
            entries[name] = lazy
        }
    }

    fun initialize(impl: Impl<T>) {
        entries.forEach { (path, entry) -> impl.register(registry, path.location(), entry) }
    }

    abstract fun initializeClient()

    fun interface Impl<T> {
        fun register(
            registry: Registry<T>,
            name: ResourceLocation,
            value: Lazy<T>
        );
    }
}
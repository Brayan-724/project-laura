package me.apika.projectlaura

import com.google.common.base.Supplier
import com.mojang.datafixers.util.Either
import net.minecraft.core.Holder
import net.minecraft.core.HolderOwner
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import java.util.*
import java.util.function.Predicate
import java.util.stream.Stream

interface Registration {
    fun registerBlock(name: String, block: (ResourceLocation) -> Block): RegistrationHolder<Block>
    fun registerItem(name: String, item: (ResourceLocation) -> Item): RegistrationHolder<Item>
}

class RegistrationHolder<T>(val resourceKey: ResourceKey<T?>) : Holder<T>, Supplier<T> {
    private var holder: Holder<T>? = null

    companion object {
        fun <T> create(
            registryKey: ResourceKey<Registry<T>>,
            valueName: ResourceLocation
        ): RegistrationHolder<T> {
            return create<T>(ResourceKey.create<T?>(registryKey, valueName))
        }

        fun <T> create(registryName: ResourceLocation, valueName: ResourceLocation): RegistrationHolder<T> {
            return create<T>(ResourceKey.createRegistryKey<T?>(registryName), valueName)
        }

        fun <T> create(key: ResourceKey<T?>): RegistrationHolder<T> {
            return RegistrationHolder<T>(key)
        }
    }

    override fun value(): T & Any {
        bind()
        if (this.holder != null) {
            return this.holder!!.value()!!
        } else {
            throw NullPointerException("Trying to access unbound value: ${this.resourceKey}");
        }
    }

    override fun get(): T {
        return value()
    }

    private fun getRegistry(): Registry<T?>? {
        return BuiltInRegistries.REGISTRY.get(this.resourceKey.registry()) as Registry<T?>?
    }

    private fun bind() {
        if (this.holder == null) {
            val registry: Registry<T?>? = this.getRegistry()
            if (registry != null) {
                this.holder = registry.getHolder(this.resourceKey).orElse(null as Any? as Holder.Reference<T?>?) as Holder<T>?
            } else {
                throw IllegalStateException(
                    "Registry not present for $this : ${this.resourceKey.registry()}"
                )
            }
        }
    }

    override fun isBound(): Boolean {
        this.bind()
        return this.holder != null && this.holder!!.isBound
    }

    override fun `is`(id: ResourceLocation): Boolean {
        return id == this.resourceKey.location()
    }

    override fun `is`(key: ResourceKey<T?>): Boolean {
        return key === this.resourceKey
    }

    override fun `is`(filter: Predicate<ResourceKey<T?>?>): Boolean {
        return filter.test(this.resourceKey)
    }

    override fun `is`(tag: TagKey<T?>): Boolean {
        this.bind()
        return this.holder != null && this.holder!!.`is`(tag)
    }


    @Deprecated("")
    override fun `is`(holder: Holder<T?>): Boolean {
        this.bind()
        return this.holder != null && this.holder!!.`is`(holder)
    }

    override fun tags(): Stream<TagKey<T?>?> {
        this.bind()
        return if (this.holder != null) this.holder!!.tags() else Stream.empty<TagKey<T?>?>()
    }

    override fun unwrap(): Either<ResourceKey<T?>?, T?> {
        return Either.left<ResourceKey<T?>?, T?>(this.resourceKey)
    }

    override fun unwrapKey(): Optional<ResourceKey<T?>?> {
        return Optional.of<ResourceKey<T?>?>(this.resourceKey) as Optional<ResourceKey<T?>?>
    }

    override fun kind(): Holder.Kind {
        return Holder.Kind.REFERENCE
    }

    override fun canSerializeIn(owner: HolderOwner<T?>): Boolean {
        this.bind()
        return this.holder != null && this.holder!!.canSerializeIn(owner)
    }
}
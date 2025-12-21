package me.apika.projectlaura

import me.apika.projectlaura.client.model.SuperGolemModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.builders.LayerDefinition

object ModelLayers {
    private var entries = mutableMapOf<ModelLayerLocation, LayerFactory>()

    val SUPER_GOLEM = register("super_golem", SuperGolemModel::createBodyLayer)

    private fun register(entry: String, definition: LayerFactory): ModelLayerLocation {
        return register(entry, "main", definition)
    }

    private fun register(entry: String, model: String, definition: LayerFactory): ModelLayerLocation {
        val result: ModelLayerLocation = ModelLayerLocation(entry.location(), model)

        check(!entries.containsKey(result)) { "Duplicate registration for $result" }

        entries[result] = definition

        return result
    }

    fun initialize(impl: Impl) {
        entries.forEach { (path, entry) -> impl.register(path, entry) }
    }

    fun interface LayerFactory {
        fun create(): LayerDefinition;
    }

    fun interface Impl {
        fun register(layer: ModelLayerLocation, factory: LayerFactory)
    }
}
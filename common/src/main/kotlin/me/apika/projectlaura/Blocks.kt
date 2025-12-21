package me.apika.projectlaura

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour

object Blocks : RegistryRepository<Block>(BuiltInRegistries.BLOCK) {
    val EXAMPLE_BLOCK by registerBlock("example_block", ::SlabBlock) {
        it
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.GRAVEL)
            .lightLevel { 7 }
    }

    private fun <T : Block> registerBlock(
        name: String, factory: BlockFactory<T>, builder: BlockFactory<BlockBehaviour.Properties>
    ): Lazy<T> {
        return register(name) { id ->
            factory.create(builder.create(BlockBehaviour.Properties.of().setId(id)))
        }
    }

    override fun initializeClient() {}

    fun interface BlockFactory<T> {
        fun create(properties: BlockBehaviour.Properties): T
    }
}
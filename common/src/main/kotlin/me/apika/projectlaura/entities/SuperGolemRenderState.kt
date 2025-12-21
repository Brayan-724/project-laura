package me.apika.projectlaura.entities

import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState
import net.minecraft.world.entity.AnimationState

class SuperGolemRenderState : ArmedEntityRenderState() {
    lateinit var hitState: AnimationState

    companion object {
        val HIT_TICK_COUNT = 15
    }
}
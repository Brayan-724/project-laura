package me.apika.projectlaura.client.render

import me.apika.projectlaura.ModelLayers
import me.apika.projectlaura.client.model.SuperGolemModel
import me.apika.projectlaura.entities.SuperGolem
import me.apika.projectlaura.entities.SuperGolemRenderState
import me.apika.projectlaura.location
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.MobRenderer
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState

class SuperGolemRenderer(
    context: EntityRendererProvider.Context
) : MobRenderer<SuperGolem, SuperGolemRenderState, SuperGolemModel>(context,
    SuperGolemModel(context.bakeLayer(ModelLayers.SUPER_GOLEM)), 0.5f) {
    private val superGolemTexture = "textures/entity/super_golem/super_golem.png".location();

    override fun getTextureLocation(state: SuperGolemRenderState) = superGolemTexture

    override fun createRenderState(): SuperGolemRenderState {
        return SuperGolemRenderState()
    }

    override fun extractRenderState(entity: SuperGolem, state: SuperGolemRenderState, partialTicks: Float) {
        super.extractRenderState(entity, state, partialTicks)
        ArmedEntityRenderState.extractArmedEntityRenderState(entity, state, this.itemModelResolver)
        state.hitState = entity.hitAnimationState
    }
}
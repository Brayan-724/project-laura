// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn

package me.apika.projectlaura.client.model

import com.mojang.blaze3d.vertex.PoseStack
import me.apika.projectlaura.entities.SuperGolemRenderState
import me.apika.projectlaura.location
import net.minecraft.client.model.ArmedModel
import net.minecraft.client.model.EntityModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.world.entity.HumanoidArm

class SuperGolemModel(root: ModelPart) : EntityModel<SuperGolemRenderState>(root), ArmedModel<SuperGolemRenderState> {
    private val golem: ModelPart = root.getChild("golem")
    private val nose: ModelPart = golem.getChild("nose")
    private val feet: ModelPart = golem.getChild("feet")
    private val footR: ModelPart = feet.getChild("footR")
    private val footL: ModelPart = feet.getChild("footL")
    private val arms: ModelPart = golem.getChild("arms")
    private val armR: ModelPart = arms.getChild("armR")
    private val armL: ModelPart = arms.getChild("armL")

    private val walkAnimation = SuperGolemModelAnimation.walk.bake(root)
    private val hitAnimation = SuperGolemModelAnimation.hit.bake(root)

    companion object {
        fun createBodyLayer(): LayerDefinition {
            val modelData = MeshDefinition();
            val modelPartData = modelData.root;

            val golem = modelPartData.addOrReplaceChild("golem",
                CubeListBuilder.create()
                    .texOffs(0, 0)
                    .addBox(-3.0F, -8.0F, -3.0F, 6.0F, 9.0F, 6.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, 21.0F, 0.0F));

            golem.addOrReplaceChild("nose",
                CubeListBuilder.create()
                    .texOffs(19, 0)
                    .addBox(-1.0F, 0.0F, -2.0F, 2.0F, 3.0F, 2.0F, CubeDeformation.NONE),
                PartPose.offset(0.0F, -5.0F, -3.0F));

            val feet = golem.addOrReplaceChild("feet", CubeListBuilder.create(),
                PartPose.offset(-2.0F, 3.0F, 0.0F));
            feet.addOrReplaceChild("footR",
                CubeListBuilder.create()
                    .texOffs(19, 23)
                    .addBox(-1.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F, CubeDeformation(-0.15f)),
                PartPose.offset(0.0F, -3.0F, 0.0F));
            feet.addOrReplaceChild("footL", CubeListBuilder.create()
                .texOffs(10, 16)
                .mirror()
                .addBox(-1.0F, -1.0F, -2.0F, 2.0F, 4.0F, 4.0F, CubeDeformation(-0.15f))
                .mirror(false), PartPose.offset(4.0F, -3.0F, 0.0F));

            val arms = golem.addOrReplaceChild("arms", CubeListBuilder.create(),
                PartPose.offset(0.0F, -3.5F, 0.0F));
            arms.addOrReplaceChild("armR", CubeListBuilder.create()
                .texOffs(1, 24)
                .addBox(-2.0F, -0.5927F, -1.0053F, 2.0F, 5.0F, 2.0F, CubeDeformation.NONE),
                PartPose.offset(-3.0F, 0.0F, 0.0F));
            arms.addOrReplaceChild("armL", CubeListBuilder.create()
                .texOffs(1, 16)
                .mirror()
                .addBox(0.0F, -0.5927F, -1.0053F, 2.0F, 5.0F, 2.0F, CubeDeformation.NONE)
                .mirror(false), PartPose.offset(3.0F, 0.0F, 0.0F));

            return LayerDefinition.create(modelData, 32, 32);
        }
    }

    override fun translateToHand(state: SuperGolemRenderState, arm: HumanoidArm, poseStack: PoseStack) {
        root.translateAndRotate(poseStack)
        golem.translateAndRotate(poseStack)
        arms.translateAndRotate(poseStack)

        val modelPart = if (arm == HumanoidArm.RIGHT) armR else armL
        modelPart.translateAndRotate(poseStack)
    }

    override fun setupAnim(renderState: SuperGolemRenderState) {
        super.setupAnim(renderState)

        walkAnimation.applyWalk(renderState.walkAnimationPos, renderState.walkAnimationSpeed, 10f, 10f)
        hitAnimation.apply(renderState.hitState, renderState.ageInTicks, 3f)
    }
}

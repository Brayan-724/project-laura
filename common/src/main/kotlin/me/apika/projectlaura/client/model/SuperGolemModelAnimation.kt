package me.apika.projectlaura.client.model

import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations

object SuperGolemModelAnimation {
    val walk: AnimationDefinition = AnimationDefinition.Builder.withLength(2.0f).looping()
        .addAnimation("golem", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 7.5f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, -7.5f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("nose", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, -12.5f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 10.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("footL", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, -5.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 5.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("footL", AnimationChannel(AnimationChannel.Targets.POSITION,
            Keyframe(0.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.posVec(-0.6f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("footR", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, -5.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 5.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("footR", AnimationChannel(AnimationChannel.Targets.POSITION,
            Keyframe(0.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.5f, KeyframeAnimations.posVec(0.4f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("armL", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(20.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(-20.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM)
        ))
        .addAnimation("armR", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(0.5f, KeyframeAnimations.degreeVec(-20.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(20.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM)
        ))
        .build()

    val hit: AnimationDefinition = AnimationDefinition.Builder.withLength(2.0f)
        .addAnimation("golem", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.degreeVec(-27.5f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(40.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("golem", AnimationChannel(AnimationChannel.Targets.POSITION,
            Keyframe(0.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.posVec(0.0f, 0.0f, -1.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("feet", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.degreeVec(22.5f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(-22.5f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("feet", AnimationChannel(AnimationChannel.Targets.POSITION,
            Keyframe(0.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.75f, KeyframeAnimations.posVec(0.0f, 0.6574f, 1.9125f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.5f, KeyframeAnimations.posVec(0.0f, 0.4135f, -0.9077f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(2.0f, KeyframeAnimations.posVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("arms", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.0f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(1.5f, KeyframeAnimations.degreeVec(50.5f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.CATMULLROM),
            Keyframe(2.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .addAnimation("nose", AnimationChannel(AnimationChannel.Targets.ROTATION,
            Keyframe(0.0f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(0.8333f, KeyframeAnimations.degreeVec(-30.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR),
            Keyframe(1.125f, KeyframeAnimations.degreeVec(0.0f, 0.0f, 0.0f),
                AnimationChannel.Interpolations.LINEAR)
        ))
        .build()
}
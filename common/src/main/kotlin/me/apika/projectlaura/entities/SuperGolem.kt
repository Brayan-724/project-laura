package me.apika.projectlaura.entities

import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.util.TimeUtil
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.MenuProvider
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
import net.minecraft.world.entity.animal.AbstractGolem
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.EnchantmentMenu
import net.minecraft.world.level.Level
import java.util.*


class SuperGolem(entityType: EntityType<out AbstractGolem>, level: Level) :
    AbstractGolem(entityType, level), NeutralMob {
    companion object {
        val EVENT_DO_HIT = 4.toByte()
    }

    var hitAnimationState = AnimationState()
    var hitAnimationTime = Int.MAX_VALUE
    var willHit = false

    private var persistentAngerTarget: UUID? = null
    private var remainingPersistentAngerTime: Int = 0

    init {
        goalSelector.addGoal(1, SuperGolemMeleeAttackGoal(this))
        goalSelector.addGoal(2, MoveTowardsTargetGoal(this, 1.0, 32.0f))
//        goalSelector.addGoal(5, LookAtPlayerGoal(this, Player::class.java, 6.0f))
//        goalSelector.addGoal(10, RandomLookAroundGoal(this))

        targetSelector.addGoal(
            1,
            NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity::class.java, false, false)
        )
        targetSelector.addGoal(2, ResetUniversalAngerTargetGoal<SuperGolem>(this, false))
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        if (hand == InteractionHand.OFF_HAND) {
            player.displayClientMessage(Component.literal("Don't touch me babe"), true)

            if (player is ServerPlayer) {
                val screen = object : MenuProvider {
                    override fun getDisplayName(): Component = Component.literal("Test Menu")

                    override fun createMenu(
                        containerId: Int, inv: Inventory, player: Player
                    ): AbstractContainerMenu {
//                        EnchantmentMenu
                        return EnchantmentMenu(containerId, inv)
                    }
                }

                player.openMenu(screen)
            }
        }

        return InteractionResult.PASS
    }

    override fun tick() {
        super.tick()

        if (willHit) {
            willHit = false
            if (hitAnimationTime == Int.MAX_VALUE) {
                hitAnimationTime = tickCount + SuperGolemRenderState.HIT_TICK_COUNT
                if (level().isClientSide) {
                    hitAnimationState.start(tickCount)
                }
            }
        }

        if (!isNoAi && hitAnimationTime == tickCount) {
            hitAnimationTime = Int.MAX_VALUE
            if (level().isClientSide) {
                hitAnimationState.stop()
            }
        }
    }

    fun isTimeToAttackAnimated() = tickCount >= hitAnimationTime - 5

    override fun getRemainingPersistentAngerTime(): Int = remainingPersistentAngerTime

    override fun setRemainingPersistentAngerTime(p0: Int) {
        remainingPersistentAngerTime = p0
    }

    override fun getPersistentAngerTarget(): UUID? {
        return this.persistentAngerTarget
    }

    override fun setPersistentAngerTarget(p0: UUID?) {
        persistentAngerTarget = p0
    }

    val persistentAngerTime: UniformInt = TimeUtil.rangeOfSeconds(20, 39);

    override fun startPersistentAngerTimer() {
        this.remainingPersistentAngerTime = persistentAngerTime.sample(this.random)
    }

    private val attackDamage get() = this.getAttributeValue(Attributes.ATTACK_DAMAGE).toFloat();

    override fun doHurtTarget(level: ServerLevel, target: Entity): Boolean {
        val damageSource = damageSources().mobAttack(this)
        val hurt: Boolean = target.hurtServer(level, damageSource, attackDamage)

        // playSound(SoundEvents.COPPER_GOLEM_SPIN, 1.0f, 1.0f)

        return hurt
    }

    override fun handleEntityEvent(id: Byte) {
        when (id) {
            EVENT_DO_HIT -> willHit = true
            else -> super.handleEntityEvent(id)
        }
    }

    private class SuperGolemMeleeAttackGoal(val mob: SuperGolem) : MeleeAttackGoal(mob, 1.0, true) {
        override fun canPerformAttack(entity: LivingEntity): Boolean {
            if (super.canPerformAttack(entity)) {
                mob.run {
                    if (isTimeToAttackAnimated()) {
                        return true
                    }

                    willHit = true
                    level().broadcastEntityEvent(this, EVENT_DO_HIT)
                }
            }

            return false
        }
    }
}
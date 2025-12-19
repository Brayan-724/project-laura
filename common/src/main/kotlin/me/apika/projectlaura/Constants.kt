package me.apika.projectlaura

import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Constants {
    const val MOD_ID = "laura"
    const val MOD_NAME = "Project Laura"
    @JvmStatic // needed so Mixins can access
    val LOG: Logger = LoggerFactory.getLogger(MOD_NAME)
}

package me.apika.projectlaura.platform

import me.apika.projectlaura.Constants
import me.apika.projectlaura.platform.services.PlatformHelper
import java.util.ServiceLoader

object Services {
    val PLATFORM = load(PlatformHelper::class.java)

    fun <T> load(clazz: Class<T>): T {
        val loadedService = ServiceLoader.load(clazz)
            .findFirst()
            .orElseThrow {
                IllegalStateException("Failed to load service for ${clazz.name}")
            }
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz)
        return loadedService
    }
}

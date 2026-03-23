package com.krenalis.analytics.kotlin.core.platform.plugins

import com.krenalis.analytics.kotlin.core.Analytics
import com.krenalis.analytics.kotlin.core.BaseEvent
import com.krenalis.analytics.kotlin.core.DestinationMetadata
import com.krenalis.analytics.kotlin.core.Settings
import com.krenalis.analytics.kotlin.core.platform.DestinationPlugin
import com.krenalis.analytics.kotlin.core.platform.Plugin
import com.krenalis.analytics.kotlin.core.utilities.safeJsonArray
import com.krenalis.analytics.kotlin.core.utilities.safeJsonObject
import kotlinx.serialization.json.JsonPrimitive

/**
 * DestinationMetadataPlugin adds `_metadata` information to payloads that Krenalis uses to
 * determine delivery of events to cloud/device-mode destinations
 */
class DestinationMetadataPlugin : Plugin {
    override val type: Plugin.Type = Plugin.Type.Enrichment
    override lateinit var analytics: Analytics
    private var analyticsSettings: Settings = Settings()

    override fun update(settings: Settings, type: Plugin.UpdateType) {
        super.update(settings, type)
        analyticsSettings = settings
    }

    override fun execute(event: BaseEvent): BaseEvent {
        // Using this over `findAll` for that teensy performance benefit
        val enabledDestinations = analytics.timeline.plugins[Plugin.Type.Destination]?.plugins
            ?.map { it as DestinationPlugin }
            ?.filter { it.enabled && it !is KrenalisDestination }
        val metadata = DestinationMetadata().apply {
            // Mark all loaded destinations as bundled
            val bundled = buildSet { enabledDestinations?.forEach { add(it.key) } }

            // All active integrations, not in `bundled` are put in `unbundled`
            // All unbundledIntegrations not in `bundled` are put in `unbundled`
            val unbundled = buildSet {
                analyticsSettings.integrations.keys.forEach {
                    if (it != "Krenalis" && !bundled.contains(it)) {
                        add(it)
                    }
                }

                analyticsSettings.integrations["Krenalis"]?.safeJsonObject
                    ?.get("unbundledIntegrations")?.safeJsonArray
                    ?.forEach {
                        val content = (it as JsonPrimitive).content
                        if (!bundled.contains(content)) {
                            add(content)
                        }
                    }
            }

            // `bundledIds` for mobile is empty
            this.bundledIds = emptyList()
            this.bundled = bundled.toList()
            this.unbundled = unbundled.toList()
        }

        val payload = event.copy<BaseEvent>().apply {
            this._metadata = metadata
        }

        return payload
    }
}
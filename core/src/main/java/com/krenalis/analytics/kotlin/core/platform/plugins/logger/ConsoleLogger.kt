package com.krenalis.analytics.kotlin.core.platform.plugins.logger

class ConsoleLogger: Logger {
    override fun parseLog(log: LogMessage) {
        println("[Krenalis ${log.kind.toString()} ${log.message}")
    }

}
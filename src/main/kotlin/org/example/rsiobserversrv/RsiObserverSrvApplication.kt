package org.example.rsiobserversrv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class RsiObserverSrvApplication

fun main(args: Array<String>) {
    runApplication<RsiObserverSrvApplication>(*args)
}

package com.gstone.trigramdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.gstone.trigramdemo"]) class TrigramDemoApplication

fun main(args: Array<String>) {
    runApplication<TrigramDemoApplication>(*args)
}

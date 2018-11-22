package com.robcio.SpringStuffUser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class SpringstuffApplication

fun main(args: Array<String>) {
    runApplication<SpringstuffApplication>(*args)
}

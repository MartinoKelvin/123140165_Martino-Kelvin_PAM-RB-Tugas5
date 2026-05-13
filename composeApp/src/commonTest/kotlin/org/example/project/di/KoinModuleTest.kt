package org.example.project.di

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import kotlin.test.Test

class KoinModuleTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun `verify koin modules configuration`() {
        allModules.get(
            index = TODO()
        )
    }

    @Test
    fun `check all modules are resolvable`() {

        checkModules {
            modules(allModules)
        }
    }
}
package com.nomanr.lumo.plugin.actions

import com.nomanr.lumo.exceptions.LumoException
import com.nomanr.lumo.plugin.configs.PropertyLoader
import com.nomanr.lumo.plugin.template.ComponentGenerator
import com.nomanr.lumo.plugin.template.SupportedComponents
import org.gradle.api.Project

class GenerateComponent(project: Project, propertyLoader: PropertyLoader) {

    private val componentGenerator = ComponentGenerator(project.rootDir, propertyLoader.loadProperties())

    fun execute(componentName: String) {
        if (componentName.isEmpty() || !SupportedComponents.values().map { it.name }.contains(componentName)) {
            throw LumoException("Invalid component name") // TODO: NOMAN - you can find the list of suppported comonents here.link
        }

        execute(SupportedComponents.valueOf(componentName))
    }

    fun execute(component: SupportedComponents) {
        componentGenerator.validateAndGenerate(component)
    }
}

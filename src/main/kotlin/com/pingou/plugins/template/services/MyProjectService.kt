package com.pingou.plugins.template.services

import com.intellij.openapi.project.Project
import com.pingou.plugins.template.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}

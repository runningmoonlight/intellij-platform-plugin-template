package jxtemplate.project.bundle

/**
 * Created by liuheng on 2021/6/8.
 */
fun bundle_gitignore()
= """
# Android generated
bin/
gen/
obj/

# Ant
# build.xml
local.properties
#gradle-wrapper.properties
lint.xml

# Maven
target/
pom.xml.*
release.properties

# Eclipse
.externalToolBuilders/
.settings

# IntelliJ
*.iml
*.ipr
*.iws
.idea/
out/

# Mac 
.DS_Store

# svn
.svn

# proguard
proguard

# Gradle
.gradle
build
build/
.idea
gradlew
gradlew.bat

# Vim
*.swp

# Git
#.gitignore
aura.json
deeplink.json
bundleinfo.json
/captures/
""".trimIndent()
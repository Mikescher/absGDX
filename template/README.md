How to use absGDX
=================

 - Generate libGDX projects via setup app
 - Including absGDX lib via file (eg put into a lib folder) or repository
 - add dependency to absGDX into **core** project
 - run `gradlew eclipse` / `gradlew idea` to re-generate the IDE files

Example of `core/build.gradle` (absGDX jar in lib-folder)

```

apply plugin: "java"

sourceCompatibility = 1.6
[compileJava, compileTestJava]*.options*.encoding = 'UTF-8'

sourceSets.main.java.srcDirs = [ "src/" ]

eclipse.project {
    name = appName + "-core"
}

dependencies {
    compile files("../lib/absGDX-framework-1.0.jar")
}

```
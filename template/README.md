How to use absGDX
=================

 - Generate libGDX projects via setup app
 - Including absGDX lib via file (eg put into a lib folder) or repository
 - add dependency to absGDX into **core** project
 - run `gradlew eclipse afterEclipseImport` / `gradlew idea` to re-generate the IDE files

Example of `build.gradle` (absGDX jar in lib-folder) *(only important stuff - see build.gradlew for the whole file)*

```

allprojects {

    repositories {
        mavenCentral()
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
        maven { url "https://oss.sonatype.org/content/repositories/releases/" }
        flatDir {
            dirs '../lib'
        }
    }
}

project(":core") {
    apply plugin: "java"


    dependencies {
        compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
        compile name: 'absGDX-framework-1.1'
    }
}

```
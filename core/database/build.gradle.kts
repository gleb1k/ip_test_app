import ru.glebik.tools.Plugins
import ru.glebik.tools.androidModule
import ru.glebik.tools.coreDesugaring
import ru.glebik.tools.impl
import ru.glebik.tools.kapt

androidModule(
    pkg = "ru.glebik.core.database",
    useCompose = false,
    desugaringEnabled = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt,
    ),
    deps = {
        impl(libs.bundles.room)
        kapt(libs.androidX.roomCompiler)
        impl(libs.gson)
        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)
        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)
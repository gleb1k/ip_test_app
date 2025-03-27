import ru.glebik.tools.Plugins
import ru.glebik.tools.androidModule
import ru.glebik.tools.coreDesugaring
import ru.glebik.tools.impl
import ru.glebik.tools.kapt


androidModule(
    pkg = "ru.glebik.feature.home.impl",
    useCompose = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt,
    ),
    desugaringEnabled = true,
    deps = {
        impl(projects.feature.home.api)
        impl(projects.ui.kit)

        impl(projects.core.ktx)
        impl(projects.core.arch)
        impl(projects.core.database)

        coreDesugaring(libs.androidTools.desugarJdkLibs)
        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)
        impl(libs.bundles.room)
        kapt(libs.androidX.roomCompiler)
    }
)
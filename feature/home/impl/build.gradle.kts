import ru.glebik.tools.Plugins
import ru.glebik.tools.androidModule
import ru.glebik.tools.impl
import ru.glebik.tools.kapt


androidModule(
    pkg = "ru.glebik.feature.home.impl",
    useCompose = true,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt,
    ),
    deps = {
        impl(projects.feature.home.api)

        impl(projects.core.ktx)
        impl(projects.core.arch)
        impl(projects.core.database)
        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)
    }
)
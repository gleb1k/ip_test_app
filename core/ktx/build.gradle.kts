import ru.glebik.tools.Plugins
import ru.glebik.tools.androidModule
import ru.glebik.tools.impl
import ru.glebik.tools.kapt

androidModule(
    pkg = "ru.glebik.core.ktx",
    useCompose = false,
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt,
    ),
    deps = {
        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)
    }
)
import ru.glebik.tools.Plugins
import ru.glebik.tools.androidModule
import ru.glebik.tools.api
import ru.glebik.tools.debugImpl
import ru.glebik.tools.impl
import ru.glebik.tools.kapt

androidModule(
    pkg = "ru.glebik.ui.kit",
    useCompose = true,
    plugins = listOf(
        Plugins.Kapt,
        Plugins.Hilt
    ),
    deps = {
        api(libs.bundles.compose)
        api(libs.jetbrains.kotlinxCollectionsImmutable)
        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)

        impl(platform(libs.androidx.compose.bom))
        debugImpl(libs.androidx.ui.tooling)
    }
)
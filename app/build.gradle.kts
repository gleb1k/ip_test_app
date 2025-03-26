import dagger.hilt.android.plugin.HiltExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import ru.glebik.tools.Plugins
import ru.glebik.tools.androidApp
import ru.glebik.tools.debugImpl
import ru.glebik.tools.impl
import ru.glebik.tools.kapt
import ru.glebik.tools.Variant

androidApp(
    pkg = "ru.glebik.iptestapp",
    code = 1,
    version = "1.0",
    plugins = listOf(
        Plugins.Hilt,
        Plugins.Kapt,
    ),
    variants = listOf(
        Variant.Debug, Variant.Release
    ),
    useCompose = true,
    configure = {
        configure<KaptExtension> {
            correctErrorTypes = true
        }
        configure<HiltExtension> {
            enableAggregatingTask = false
        }
    },
    deps = {
        impl(libs.androidx.core.ktx)
        impl(libs.androidx.lifecycle.runtime.ktx)
        impl(libs.androidx.activity.compose)
        impl(platform(libs.androidx.compose.bom))
        impl(libs.androidx.ui)
        impl(libs.androidx.ui.graphics)
        impl(libs.androidx.ui.tooling.preview)
        impl(libs.androidx.material3)
        debugImpl(libs.androidx.ui.tooling)

        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)
    }
)
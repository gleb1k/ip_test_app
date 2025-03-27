import dagger.hilt.android.plugin.HiltExtension
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import ru.glebik.tools.Plugins
import ru.glebik.tools.Variant
import ru.glebik.tools.androidApp
import ru.glebik.tools.coreDesugaring
import ru.glebik.tools.impl
import ru.glebik.tools.kapt

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
        impl(platform(libs.androidx.compose.bom))
        impl(libs.googleDagger.hiltAndroid)
        kapt(libs.googleDagger.hiltCompiler)
        coreDesugaring(libs.androidTools.desugarJdkLibs)

        impl(projects.core.ktx)
        impl(projects.core.database)
        impl(projects.core.arch)

        impl(projects.ui.kit)

        impl(projects.feature.home.api)
        impl(projects.feature.home.impl)
    }
)
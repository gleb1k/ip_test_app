import ru.glebik.tools.androidModule
import ru.glebik.tools.coreDesugaring
import ru.glebik.tools.impl

androidModule(
    pkg = "ru.glebik.feature.home.api",
    useCompose = false,
    desugaringEnabled = true,
    deps = {
        impl(projects.core.arch)
        impl(libs.androidx.core.ktx)
        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)

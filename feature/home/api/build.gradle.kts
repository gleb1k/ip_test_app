import ru.glebik.tools.androidModule
import ru.glebik.tools.coreDesugaring

androidModule(
    pkg = "ru.glebik.feature.home.api",
    useCompose = false,
    desugaringEnabled = true,
    deps = {
        coreDesugaring(libs.androidTools.desugarJdkLibs)
    }
)

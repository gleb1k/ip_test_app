import ru.glebik.tools.androidModule
import ru.glebik.tools.impl

androidModule(
    pkg = "ru.glebik.core.arch",
    useCompose = false,
    deps = {
        impl(libs.androidx.lifecycle.viewmodel.ktx)
        impl(libs.jetbrains.kotlinxCoroutinesCore)
    }
)
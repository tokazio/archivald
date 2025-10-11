package fr.tokazio.konsistksp.resolver

import com.google.devtools.ksp.symbol.KSFile
import fr.tokazio.konsistksp.api.Annotation
import fr.tokazio.konsistksp.api.File

class KonsistKspFile(
    internal val inner: KSFile,
): File {
    override val packageName: String by lazy {
        inner.packageName.asString()
    }

    override val filePath: String by lazy {
        inner.filePath
    }
    override val fileName: String by lazy {
        inner.fileName
    }

    override val annotations: Sequence<Annotation> by lazy {
        inner.annotations.map {
            KonsistKspAnnotation(it)
        }
    }
}
package fr.tokazio.konsistksp.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import fr.tokazio.konsistksp.internal.model.Location

class KonsistKspLocation(
    inner: FileLocation,
) : Location {
    override val fullFilename: String = inner.filePath

    override val lineNumber: Int = inner.lineNumber
}

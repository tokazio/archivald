package fr.tokazio.archivald.ksp.bridge.model

import com.google.devtools.ksp.symbol.FileLocation
import fr.tokazio.archivald.internal.model.Location

class KonsistKspLocation(
    inner: FileLocation,
) : Location {
    override val fullFilename: String = inner.filePath

    override val lineNumber: Int = inner.lineNumber
}

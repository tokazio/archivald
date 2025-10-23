package fr.tokazio.archivald.jap;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("fr.tokazio.archivald.ArchitectureRule")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
class ArchivaldProcessor extends AbstractProcessor {

    @Override
    public boolean process(
            Set<? extends TypeElement> annotations,
            RoundEnvironment roundEnv
    ) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);

            // …
        }

        return true;
    }

}


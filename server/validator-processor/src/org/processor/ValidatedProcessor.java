package org.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unused")
@SupportedAnnotationTypes("org.validator.annotations.Validated")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class ValidatedProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                if (element.getKind() != ElementKind.RECORD) continue;

                String pkg = processingEnv.getElementUtils().getPackageOf(element).getQualifiedName().toString();
                String recordName = element.getSimpleName().toString();
                String factoryName = recordName + "Factory";

                List<RecordComponentElement> params = ElementFilter.recordComponentsIn(element.getEnclosedElements());

                try {
                    JavaFileObject file = processingEnv.getFiler().createSourceFile(pkg + "." + factoryName);
                    try (Writer writer = file.openWriter()) {

                        writer.write("package " + pkg + ";\n\n");
                        writer.write("public class " + factoryName + " {\n");
                        writer.write("    public static " + recordName + " create(");

                        // Аргументы
                        for (int i = 0; i < params.size(); i++) {
                            RecordComponentElement param = params.get(i);
                            writer.write(param.asType() + " " + param.getSimpleName());
                            if (i < params.size() - 1) writer.write(", ");
                        }
                        writer.write(") {\n");

                        writer.write("        " + recordName + " obj = new " + recordName + "(");

                        for (int i = 0; i < params.size(); i++) {
                            writer.write(params.get(i).getSimpleName().toString());
                            if (i < params.size() - 1) writer.write(", ");
                        }

                        writer.write(");\n");
                        writer.write("        org.validator.validation.ValidationController.validateObject(obj);\n");
                        writer.write("        return obj;\n");
                        writer.write("    }\n");
                        writer.write("}\n");
                    }
                } catch (Exception e) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.getMessage());
                }
            }
        }

        return true;
    }
}
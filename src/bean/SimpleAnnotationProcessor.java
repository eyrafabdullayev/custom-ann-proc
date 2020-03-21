/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.beans.Transient;
import java.lang.reflect.Modifier;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 *
 * @author eyraf
 */
@SupportedAnnotationTypes("bean.Transactional")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class SimpleAnnotationProcessor extends AbstractProcessor{

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(final Element element : roundEnv.getElementsAnnotatedWith(Transactional.class)){
            Element classElement = element.getEnclosingElement();
            boolean isPublic  = element.getModifiers().contains(Modifier.PUBLIC);
            
            if(!isPublic){
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, classElement+"#"+element + " is not public, but @Transactional only works with public fields");
            }
        }
        return true;
    }
    
}

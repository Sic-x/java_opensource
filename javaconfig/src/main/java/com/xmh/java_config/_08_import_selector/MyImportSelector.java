package com.xmh.java_config._08_import_selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector{
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                "com.xmh.java_config._08_import_selector.MyBean",
                "com.xmh.java_config._08_import_selector.OtherBean"
        };
    }
}

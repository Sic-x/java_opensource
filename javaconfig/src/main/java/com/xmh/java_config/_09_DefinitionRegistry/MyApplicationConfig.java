package com.xmh.java_config._09_DefinitionRegistry;



import com.xmh.java_config._08_import_selector.MyImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyDefinitionRegistrar.class)
public class MyApplicationConfig {
}

package com.xmh.java_config._08_import_selector;



import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MyImportSelector.class)
public class MyApplicationConfig {
}

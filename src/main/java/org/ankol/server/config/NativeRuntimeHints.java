package org.ankol.server.config;

import cn.hutool.core.util.ClassUtil;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.Set;

@Configuration
@ImportRuntimeHints(NativeRuntimeHints.class)
public class NativeRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        Set<Class<?>> classSet = ClassUtil.scanPackage("org.ankol.server.api.entity");
        ReflectionHints reflection = hints.reflection();
        for (Class<?> target : classSet) {
            reflection.registerType(target, MemberCategory.values());
        }
    }
}

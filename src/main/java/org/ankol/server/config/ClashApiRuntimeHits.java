package org.ankol.server.config;

import cn.hutool.core.util.ClassUtil;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.Set;

/**
 * ClashApiRuntimeHitsRegistrar
 *
 * @author Administrator
 */
public class ClashApiRuntimeHits implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        for (Class<?> target : this.getClashApiEntityTypeHits()) {
            System.out.println("Register Type hit for " + target.getName());
            hints.reflection()
                    .registerType(target, MemberCategory.values());
        }
    }

    private Set<Class<?>> getClashApiEntityTypeHits() {
        return ClassUtil.scanPackage("com.ankol.api.entity");
    }

    public static void main(String[] args) {
        Set<Class<?>> classSet = ClassUtil.scanPackage("com.ankol.api.entity");
        System.out.println("classSet = " + classSet);
    }
}

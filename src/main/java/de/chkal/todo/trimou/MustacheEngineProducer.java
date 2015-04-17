package de.chkal.todo.trimou;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.trimou.engine.MustacheEngine;
import org.trimou.engine.MustacheEngineBuilder;
import org.trimou.engine.config.EngineConfigurationKey;
import org.trimou.servlet.locator.ServletContextTemplateLocator;
import org.trimou.servlet.resolver.HttpServletRequestResolver;

/**
 * A producer for {@link MustacheEngine}.
 *
 * @author Martin Kouba
 */
public class MustacheEngineProducer {

    @ApplicationScoped
    @Produces
    public MustacheEngine produceMustacheEngine() {
        return MustacheEngineBuilder
                .newBuilder()
                .setProperty(EngineConfigurationKey.PRECOMPILE_ALL_TEMPLATES,
                        true)
                // HttpServletRequestResolver provides HttpServletRequest out of the box
                // But it may degrade performance and so we put the request in the Models instead
                .setProperty(HttpServletRequestResolver.ENABLED_KEY, false)
                .addTemplateLocator(
                        new ServletContextTemplateLocator(1, "/WEB-INF/trimou-views"))
                .build();
    }
}

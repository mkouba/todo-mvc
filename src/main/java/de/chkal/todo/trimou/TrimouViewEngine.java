package de.chkal.todo.trimou;

import java.io.IOException;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.engine.ViewEngine;
import javax.mvc.engine.ViewEngineContext;
import javax.mvc.engine.ViewEngineException;

import org.trimou.engine.MustacheEngine;

/**
 *
 * @author Martin Kouba
 */
public class TrimouViewEngine implements ViewEngine {

    @Inject
    MustacheEngine engine;

    @Override
    public boolean supports(String view) {
        return view.endsWith(".trimou");
    }

    @Override
    public void processView(ViewEngineContext context)
            throws ViewEngineException {
        try {
            Models models = context.getModels();
            // See also comment in MustacheEngineProducer
            models.put("request", context.getRequest());
            engine.getMustache(context.getView()).render(
                    context.getResponse().getWriter(), models);
        } catch (IOException e) {
            throw new ViewEngineException(e);
        }
    }

}

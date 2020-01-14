package de.michaelevelt.toolcollection.html.thymeleaf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public abstract class AbstractThymeleafInitializer {

    protected final String htmlTemplate;

    private TemplateEngine templateEngine;

    /**
     *
     * @param locationPrefix location of the templates relative to resources location. Example: "/templates/"
     * @param htmlTemplate name of the HTML template relative to location prefix and without .html. Expample for file "/templates/irgendwas.html" => "irgendwas"
     */
    public AbstractThymeleafInitializer(String locationPrefix, String htmlTemplate) {
        templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix(locationPrefix);
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML); // HTML5 option was deprecated in 3.0.0
        templateEngine.setTemplateResolver(resolver);
        this.htmlTemplate = htmlTemplate;
    }

    public void renderHTML(String outputFileName) {

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFileName), StandardCharsets.UTF_8)) {
            String html = renderHTML();
            writer.write(html);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public String renderHTML() {
        StringWriter standardTableWriter = new StringWriter();
        getTemplateEngine().process(htmlTemplate, fillContext(), standardTableWriter);
        return standardTableWriter.toString();
    }

    public TemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }

    protected abstract Context fillContext();

}

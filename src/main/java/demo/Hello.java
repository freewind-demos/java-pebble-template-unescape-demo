package demo;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class Hello {

    public static void main(String[] args) throws Exception {
        ClasspathLoader loader = new ClasspathLoader();
        PebbleEngine engine = new PebbleEngine.Builder().loader(loader).strictVariables(true).build();
        PebbleTemplate compiledTemplate = engine.getTemplate("hello.html");


        Map<String, Object> context = new HashMap<>();
        context.put("danger", "<hr>");

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();
        System.out.println(output);
    }

}


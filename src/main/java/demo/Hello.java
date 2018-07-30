package demo;

import com.github.freewind.lostlist.Lists;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ClasspathLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.freewind.lostlist.Lists.list;

public class Hello {

    public static void main(String[] args) throws Exception {
        ClasspathLoader loader = new ClasspathLoader();
        PebbleEngine engine = new PebbleEngine.Builder().loader(loader).strictVariables(true).build();
        PebbleTemplate compiledTemplate = engine.getTemplate("hello.html");

        List<Item> items = list(
                new Item("Item 1", "$19.99", list("good", "item", "new"), list(new Feature("New!"), new Feature("Awesome!"))),
                new Item("Item 2", "$29.99", list("old", "item", "bad"), list(new Feature("Old."), new Feature("Ugly.")))
        );

        Map<String, Object> context = new HashMap<>();
        context.put("items", items);

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        String output = writer.toString();
        System.out.println(output);
    }

}

class Feature {
    public final String description;
    public Feature(String description) {
        this.description = description;
    }
}

class Item {
    public final String name;
    public final String price;
    public final List<String> keywords;
    public final List<Feature> features;
    public Item(String name, String price, List<String> keywords, List<Feature> features) {
        this.name = name;
        this.price = price;
        this.keywords = keywords;
        this.features = features;
    }
}
package com.eden.orchid.impl.generators.collections;

import com.eden.orchid.api.OrchidContext;
import com.eden.orchid.api.generators.GlobalCollection;
import com.eden.orchid.api.theme.pages.OrchidPage;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Stream;

@Singleton
public class FrontMatterCollection extends GlobalCollection<OrchidPage> {

    private final OrchidContext context;

    @Inject
    public FrontMatterCollection(OrchidContext context) {
        super("frontMatter");
        this.context = context;
    }

    @Override
    protected List<OrchidPage> loadItems() {
        return context.getInternalIndex().getAllPages();
    }

    @Override
    protected Stream<OrchidPage> find(String id) {
        // TODO: Make this able to evaluate simple queries including parentheses, & (and), and | (or) operators
        if(id.contains("=")) {
            String key = id.split("=")[0];
            String value = id.split("=")[1];
            return getItems()
                    .stream()
                    .filter(page -> (page.get(key) != null ? page.get(key).toString() : "").equals(value));
        }
        else {
            return null;
        }
    }

}
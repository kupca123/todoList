package cz.homebrew.todolistbackend.utils;

import lombok.NonNull;
import org.apache.commons.collections4.IterableUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;


public class MapperUtils {
    private static final Logger LOGGER = LogManager.getLogger(MapperUtils.class);

    /**
     * <p>
     * Maps list of items to list of another items.
     * </p>
     * <p>
     * Use this method for small lists. It does not use Java stream API to:
     * <ul>
     *     <li>Avoid the overhead of creating Stream objects.</li>
     *     <li>Perform only one pass over the data with direct operations.</li>
     *     <li>Potentially improve performance for small to medium-sized lists.</li>
     *     <li>Keep the code simple and readable.</li>
     * </ul>
     * </p>
     *
     * @param srcList List of items to map.
     * @param mapper  Function to map item.
     * @param <T>     Type of item.
     * @param <U>     Type of mapped item.
     * @return List of mapped items.
     */
    public static <T, U> List<U> mapList(final List<T> srcList, final Function<T, U> mapper) {
        final List<U> resultList = new ArrayList<>();
        mapCollection(srcList, resultList, mapper);
        return resultList;
    }

    private static <T, U> void mapCollection(final Collection<T> srcCollection,
                                             @NonNull final Collection<U> targetCollection,
                                             final Function<T, U> mapper) {
        if (IterableUtils.isEmpty(srcCollection) || mapper == null) {
            LOGGER.debug("[mapCollection] Empty source collection or mapper is null");
            return;
        }
        for (T item : srcCollection) {
            if (item == null) {
                LOGGER.debug("[mapCollection] Null item in source collection");
                continue;
            }
            targetCollection.add(mapper.apply(item));
        }
    }

}

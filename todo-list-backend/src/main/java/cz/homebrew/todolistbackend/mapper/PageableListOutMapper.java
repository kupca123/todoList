package cz.homebrew.todolistbackend.mapper;

import cz.homebrew.todolistbackend.utils.MapperUtils;
import cz.homebrew.todolistbackend.wrapper.PageableListOut;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public class PageableListOutMapper {
    public static <T, U> PageableListOut<U> mapToPageableListOut(final Page<T> page, final Function<T, U> mapper) {
        return new PageableListOut<U>()
                .setPagination(mapToPagination(page))
                .setData(MapperUtils.mapList(page.getContent(), mapper));
    }

    private static <T> PageableListOut.Pagination mapToPagination(final Page<T> page) {
        return new PageableListOut.Pagination()
                .setCurrentPage(page.getNumber())
                .setPageSize(page.getSize())
                .setTotalCount(page.getTotalElements());
    }
}

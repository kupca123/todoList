package cz.homebrew.todolistbackend.api.v1;

import cz.homebrew.todolistbackend.utils.MapperUtils;
import cz.homebrew.todolistbackend.wrapper.PageableListOut;

import java.util.function.Function;

public class PageableListResToMapper {
    public static <T, U> PageableListResTo<U> mapToPageableListResTo(final PageableListOut<T> pageableListOut, final Function<T, U> mapper) {
        return new PageableListResTo<U>()
                .setPagination(mapToPagination(pageableListOut.getPagination()))
                .setData(MapperUtils.mapList(pageableListOut.getData(), mapper));
    }

    private static PageableListResTo.Pagination mapToPagination(final PageableListOut.Pagination pagination) {
        return new PageableListResTo.Pagination()
                .setCurrentPage(pagination.getCurrentPage())
                .setPageSize(pagination.getPageSize())
                .setTotalCount(pagination.getTotalCount());
    }

}

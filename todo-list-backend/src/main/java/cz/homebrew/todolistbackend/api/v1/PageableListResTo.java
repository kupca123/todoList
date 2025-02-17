package cz.homebrew.todolistbackend.api.v1;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageableListResTo<T> {
    private Pagination pagination;
    private List<T> data;

    @Getter
    @Setter
    @Accessors(chain = true)
    public static class Pagination {
        private Integer currentPage;
        private Integer pageSize;
        private Long totalCount;
    }
}

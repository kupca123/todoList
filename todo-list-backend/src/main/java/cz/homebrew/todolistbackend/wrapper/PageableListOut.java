package cz.homebrew.todolistbackend.wrapper;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
@Getter
@Setter
@Accessors(chain = true)
public class PageableListOut<T> {
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

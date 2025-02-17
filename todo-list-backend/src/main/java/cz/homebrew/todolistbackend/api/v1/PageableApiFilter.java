package cz.homebrew.todolistbackend.api.v1;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PageableApiFilter {
    private int page;
    private int pageSize;
    private String order;
    private String orderBy;

    //TODO JAL: přidat načítání dat z konfigurace

    public PageableApiFilter(){
        this.page = 1;
        this.pageSize = 10;
        this.order = "ASC";
        this.orderBy = "id";
    }
    public PageableApiFilter(String orderBy, String order){
        this.page = 1;
        this.pageSize = 10;
        this.order = order;
        this.orderBy = orderBy;
    }

    public Pageable getPageableInstance() {
        return PageRequest.of(this.page - 1, this.pageSize, Sort.Direction.valueOf(this.order), this.orderBy);
    }
}

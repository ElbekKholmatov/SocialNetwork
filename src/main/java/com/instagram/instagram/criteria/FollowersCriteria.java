package com.instagram.instagram.criteria;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ParameterObject
public class FollowersCriteria {
    @Parameter(required = true, example = "username")
    private String username;
    @Parameter(example = "1")
    @Min(value = 0,message = "page can be al least 0")
    private Integer page;
    @Parameter(example = "10")
    @Min(value = 1,message = "page can be al least 0")
    private Integer size;
    @Parameter(required = false,example = "NAME")
    private SortKey sortKey;
    private Order order;
    public enum SortKey{
        DATE,
        NAME,
    }
    public enum Order{
        ASC,DESC
    }


}

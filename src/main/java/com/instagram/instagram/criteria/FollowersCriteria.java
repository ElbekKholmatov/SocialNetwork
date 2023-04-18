package com.instagram.instagram.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FollowersCriteria {
    private String username;
    private Integer page;
    private Integer size;
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

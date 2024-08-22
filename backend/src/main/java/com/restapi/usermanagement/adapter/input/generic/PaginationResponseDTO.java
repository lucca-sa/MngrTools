package com.restapi.usermanagement.adapter.input.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaginationResponseDTO {
    private Integer page;
    private Long totalItems;
    private Integer totalPage;
    private Object data;
}

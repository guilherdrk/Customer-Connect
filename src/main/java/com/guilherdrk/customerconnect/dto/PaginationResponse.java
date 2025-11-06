package com.guilherdrk.customerconnect.dto;

public record PaginationResponse(Integer page,
                                 Integer pageSize,
                                 Long totalElements,
                                 Integer totalPages) {
}

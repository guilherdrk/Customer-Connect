package com.guilherdrk.customerconnect.dto;

import java.util.List;

public record ApiResponse<T>(List<T> content, PaginationResponse paginationResponse) {
}

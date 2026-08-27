package com.techie.microservices.product.dto;

import java.math.BigDecimal;

public record ProductResponse(String id,
                              String name,
                              String description,
                              BigDecimal price
                              ) {

	
    // No additional methods or fields are needed here
}

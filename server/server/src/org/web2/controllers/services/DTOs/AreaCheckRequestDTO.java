package org.web2.controllers.services.DTOs;

import org.validator.annotations.Number;

public record AreaCheckRequestDTO (
    @Number(min = -5, max = 3) String X,
    @Number(min = -2, max = 2, step = 0.5) String Y,
    @Number(min = 1, max = 5, step = 1) String R
) {}


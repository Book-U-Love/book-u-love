package org.bookyoulove.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @Value("${jwt.secret}")
    private String secret;
}

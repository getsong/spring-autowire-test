package com.getsong.mockito.annotation;

import org.springframework.stereotype.Service;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 28/9/2019 3:19 PM
 */
@Service
public class GinService {

    private int quantity;

    boolean hasGin() {
        return true;
    }

    void pourGin() {}
}

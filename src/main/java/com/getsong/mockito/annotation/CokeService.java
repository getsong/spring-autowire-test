package com.getsong.mockito.annotation;

import org.springframework.stereotype.Service;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 28/9/2019 3:18 PM
 */
@Service
public class CokeService {

    private int quantity;

    boolean hasCoke() {
        return true;
    }

    void pourCoke() {}
}

package com.getsong.mockito.annotation;

import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 28/9/2019 3:10 PM
 */
@Service
@Data
public class FooBar {

    private List<String> drinks = new ArrayList<>();
    @NonNull private GinService ginService;
    @NonNull private CokeService cokeService;

    public void makeGin() {
        ginService.pourGin();
        cokeService.pourCoke();
        drinks.add("gin");
    }
}

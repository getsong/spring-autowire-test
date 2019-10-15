package com.getsong.mockito.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 28/9/2019 4:00 PM
 */
@RunWith(MockitoJUnitRunner.class)
public class FooBarTest {

  @Mock private CokeService cokeService;
  @Mock private GinService ginService;

  @InjectMocks private FooBar fooBar;

  @Test
  public void canMakeGin() {
    fooBar.makeGin();
    verify(cokeService).pourCoke();
    verify(ginService).pourGin();
  }
}

package com.getsong.mockito.annotation;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 28/9/2019 3:19 PM
 */
@Service
@Log4j2
public class GinService {

  private int quantity;

  boolean hasGin() {
    return true;
  }

  void pourGin() {
    log.info("pour gin");
  }
}

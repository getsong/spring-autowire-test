package com.getsong.mockito.annotation.observer;

import lombok.extern.log4j.Log4j2;

/**
 * TODO: Purpose
 *
 * @author getsong
 * @since 29/1/2020 4:49 PM
 */
public abstract class Observer {
  Subject subject;

  Observer(Subject subject) {
    this.subject = subject;
    this.subject.attach(this);
  }

  protected abstract void update();
}

@Log4j2
class DecimalObserver extends Observer {
  DecimalObserver(Subject subject) {
    super(subject);
  }

  @Override
  protected void update() {
    log.info("decimal state = {}", subject.getState());
  }
}

@Log4j2
class BinaryObserver extends Observer {
  BinaryObserver(Subject subject) {
    super(subject);
  }

  @Override
  protected void update() {
    log.info("binary state = {}", Integer.toBinaryString(subject.getState()));
  }
}

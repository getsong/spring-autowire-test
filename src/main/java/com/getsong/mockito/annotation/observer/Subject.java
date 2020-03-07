package com.getsong.mockito.annotation.observer;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Purpose
 *
 * @author getsong
 * @since 29/1/2020 4:47 PM
 */
public class Subject {
  @Getter @Setter private int state;
  private List<Observer> observers = new ArrayList<>();

  void attach(Observer observer) {
    observers.add(observer);
  }

  void notifyAllObservers() {
    observers.forEach(Observer::update);
  }

  public static void main(String[] args) {
    Subject subject = new Subject();
    new DecimalObserver(subject);
    new BinaryObserver(subject);
    subject.setState(12);
    subject.notifyAllObservers();
    subject.setState(15);
    subject.notifyAllObservers();
  }
}

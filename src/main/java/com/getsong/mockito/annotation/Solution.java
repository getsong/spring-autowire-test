package com.getsong.mockito.annotation;

import lombok.Value;
import lombok.extern.log4j.Log4j2;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * TODO: Purpose
 *
 * @author TODO: getso
 * @since 8/10/2019 9:14 PM
 */
@Log4j2
public class Solution {

  public static void main(String[] args) {
    Task task = Task.builder().description("nice task").budget(new BigDecimal("12.33")).build();
    log.info("task = {}", task);
  }

  @Value
  public static class Coder {
    String name;
    int age;
  }
}

class Task {
  private String description;
  private LocalDateTime deadline;
  private String name;
  private int manpower;
  private BigDecimal budget;

  Task(Builder builder) {
    this.description = builder.description;
    this.deadline = builder.deadline;
    this.name = builder.name;
    this.manpower = builder.manpower;
    this.budget = builder.budget;
  }

  static Builder builder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "Task{"
        + "description='"
        + description
        + '\''
        + ", deadline="
        + deadline
        + ", name='"
        + name
        + '\''
        + ", manpower="
        + manpower
        + ", budget="
        + budget
        + '}';
  }

  static class Builder {
    private String description;
    private LocalDateTime deadline;
    private String name;
    private int manpower;
    private BigDecimal budget;

    Builder description(String description) {
      this.description = description;
      return this;
    }

    Builder deadline(LocalDateTime deadline) {
      this.deadline = deadline;
      return this;
    }

    Builder name(String name) {
      this.name = name;
      return this;
    }

    Builder manpower(int manpower) {
      this.manpower = manpower;
      return this;
    }

    Builder budget(BigDecimal budget) {
      this.budget = budget;
      return this;
    }

    Task build() {
      return new Task(this);
    }
  }
}

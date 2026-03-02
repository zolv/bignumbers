package net.turtle.math.operation;

import java.util.function.BiFunction;

public interface AddBiFunction<A, B, O> extends BiFunction<A, B, O> {

  default O apply(A a, B b) {
    return add(a, b);
  }

  O add(A a, B b);
}

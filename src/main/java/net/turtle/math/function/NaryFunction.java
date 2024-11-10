package net.turtle.math.function;

public interface NaryFunction<I, O> {

  O apply(I... arguments);
}

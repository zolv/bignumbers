package net.turtle.math.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import net.turtle.math.exception.DifferentDimensionsException;
import net.turtle.math.exception.ParsingException;
import net.turtle.math.vector.BigComplexVector;
import net.turtle.math.vector.BigRationalVector;

public class BigMatrixUtil {

  private BigMatrixUtil() {}

  public static List<BigRationalVector> parseBigRationalMatrix(String matrix) {
    final var matrixPatternString = "^\\[((\\[.*\\])+|)\\]$";

    final var matrixPattern = Pattern.compile(matrixPatternString);
    final var matrixMatcher = matrixPattern.matcher(matrix);
    final List<BigRationalVector> entries;
    if (matrixMatcher.find()) {
      final var matrixContent = matrixMatcher.group(1);

      final var vectorPatternString = "(\\[[^\\[\\]]*\\])";
      final var vectorPattern = Pattern.compile(vectorPatternString);
      final var vectorMatcher = vectorPattern.matcher(matrixContent);

      var dimention = -1;
      final var vectorsTemp = new LinkedList<BigRationalVector>();
      while (vectorMatcher.find()) {
        final var vectorString = vectorMatcher.group(1);
        final var vector = new BigRationalVector(vectorString);
        if (dimention >= 0) {
          if (vector.getDimension() != dimention) {
            throw new DifferentDimensionsException(
                "Row "
                    + vectorsTemp.size()
                    + " has column count "
                    + vector.getDimension()
                    + " but expected "
                    + dimention);
          }
        } else {
          dimention = vector.getDimension();
        }
        vectorsTemp.add(vector);
      }
      entries = new ArrayList<>(vectorsTemp);
    } else {
      throw new ParsingException();
    }
    return entries;
  }

  public static List<BigComplexVector> parseBigComplexMatrix(String matrix) {
    final var matrixPatternString = "^\\[((\\[.*\\])+|)\\]$";

    final var matrixPattern = Pattern.compile(matrixPatternString);
    final var matrixMatcher = matrixPattern.matcher(matrix);
    final List<BigComplexVector> entries;
    if (matrixMatcher.find()) {
      final var matrixContent = matrixMatcher.group(1);

      final var vectorPatternString = "(\\[[^\\[\\]]*\\])";
      final var vectorPattern = Pattern.compile(vectorPatternString);
      final var vectorMatcher = vectorPattern.matcher(matrixContent);

      var dimention = -1;
      final var vectorsTemp = new LinkedList<BigComplexVector>();
      while (vectorMatcher.find()) {
        final var vectorString = vectorMatcher.group(1);
        final var vector = new BigComplexVector(vectorString);
        if (dimention >= 0) {
          if (vector.getDimension() != dimention) {
            throw new DifferentDimensionsException(
                "Row "
                    + vectorsTemp.size()
                    + " has column count "
                    + vector.getDimension()
                    + " but expected "
                    + dimention);
          }
        } else {
          dimention = vector.getDimension();
        }
        vectorsTemp.add(vector);
      }
      entries = new ArrayList<>(vectorsTemp);
    } else {
      throw new ParsingException();
    }
    return entries;
  }
}

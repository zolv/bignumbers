package net.turtle.math.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.util.BigRationalUtil;

public class BigRationalBenchmarkTest {

  /** Note: Converges very slowly. */
  @Test
  public void testPiCalculationSlow() {
    final int iterations = 10000;

    BigRational step = BigRationalValues.ONE;
    BigRational result = BigRationalValues.ZERO;
    for (int n = 1; n <= iterations; n++) {
      if ((n % 2) == 1) {
        result = result.add(step.inverse());
      } else {
        result = result.subtract(step.inverse());
      }
      step = step.add(BigRationalValues.TWO);
    }
    result = result.multiply(BigRationalValues.FOUR).normalize();

    final BigDecimal resultAsBigDecimal = BigRationalUtil.asBigDecimal(result, 1000);
    Assert.assertEquals(
        new BigDecimal(
            "3.1414926535900432384595183833748153787870136427441804605134798054743956706900288508706329431867655157124491802708795952166561383467230532408574251653701454765236670241940248525655253409499879388594072910900843696075335900930113566675364743831962322944161244652129734027375602189028325455989111149217633091234945467251376397944212112597807692707743501661666293501967211185418912760608668236194566519195034280749744218064249716795899958631209765882790525427178406807764625855047822819067593198591800545408231798065252720240459020376331478886531455010812031771231716872805601285826299672909956756616911575902715627777144574883287295233663970545595176277747229942966568263795431780197116939430419408883133506566935401720517389937998567261995432229293704196516829115203259833319998065992858462668944842898140476511663826202369059191069168915302147784283955645343806098264595020206589518133029986140224198589211970445474458773416165780579609315678754590213148607868339287379544702354756380659004862086122461"),
        resultAsBigDecimal);
  }

  @Test
  public void testPiCalculationSpigot() {
    final int iterations = 100;

    final BigRational _16 = new BigRational("16");
    final BigInteger _8 = new BigInteger("8");
    final BigInteger _6 = new BigInteger("6");
    final BigInteger _5 = new BigInteger("5");
    final BigInteger _4 = new BigInteger("4");
    final BigInteger _2 = new BigInteger("2");

    final BigRational result =
        IntStream.range(0, iterations)
            .mapToObj(k -> BigInteger.valueOf(k))
            .map(
                k -> {
                  final BigInteger _8_multiply_k = _8.multiply(k);

                  final BigRational bracket1 =
                      new BigRational(_4, _8_multiply_k.add(BigInteger.ONE));
                  final BigRational bracket2 = new BigRational(_2, _8_multiply_k.add(_4));
                  final BigRational bracket3 =
                      new BigRational(BigInteger.ONE, _8_multiply_k.add(_5));
                  final BigRational bracket4 =
                      new BigRational(BigInteger.ONE, _8_multiply_k.add(_6));
                  final BigRational bracket =
                      bracket1.subtract(bracket2).subtract(bracket3).subtract(bracket4);

                  final BigRational _16Power = _16.pow(k);
                  return (_16Power).inverse().multiply(bracket);
                })
            .reduce(BigRationalValues.ZERO, (a, b) -> a.add(b), (a, b) -> a.add(b));

    final BigDecimal resultAsBigDecimal = BigRationalUtil.asBigDecimal(result.normalize(), 1000);
    Assert.assertEquals(
        new BigDecimal(
            "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938350119627942072033649368930652918677481046443747275745835689496799830568544761736733151267272741486104985027818928325160714591810234040646246442204197188786099229982577997759757351180413852848428385458720429147496452791143006014970346744456216180542684845902788229452018556610201990247249604324160073202780007406672652956784102810625981022620582094608736275907191082727422096992367912769436972087225733962993141058535565453887907799231896235042370187642941792463964198537637248562667681447303820308597460130451144081577375146828551955560411223092871939799961814052636658074275070655967919534101623166825849474197101071491956495443876900920563551701226431595382452869286271655094728764648382287501420414824675691367665732821261353223585026072896819408726672133145235581343392577831741025823830929698428457986240552646342322187685993620231053068623917014139186151922607187982315"),
        resultAsBigDecimal);
  }

  @Test
  public void testSquareRoot2Calculation() {
    final int iterations = 10;

    /*
     * Reducing a stream doesn't perform very well in this case
     */
    BigRational result = BigRationalValues.ONE;
    for (int n = 1; n <= iterations; n++) {
      result = result.divide(BigRationalValues.TWO).add(result.inverse());
    }

    final BigDecimal resultAsBigDecimal = BigRationalUtil.asBigDecimal(result.normalize(), 1000);
    Assert.assertEquals(
        new BigDecimal(
            "1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727350138462309122970249248360558507372126441214970999358314132226659275055927557999505011527820605714701095599716059702745345968620147285174186408891986095523292304843087143214508397626036279952514079896872533965463318088296406206152583523950547457502877599617298355752203375318570113543746034084988471603868999706990048150305440277903164542478230684929369186215805784631115966687130130156185689872372352885092648612494977154218334204285686060146824720771435854874155657069677653720226485447015858801620758474922657226002085584466521458398893944370926591800311388246468157082630100594858704003186480342194897278290641045072636881313739855256117322040245091227700226941127573627280495741473629335318162969205432607709384331632889299584634764418509603415721076775354130839258336964677584235884756341110181393980467477640915158733230608686976354828754443740381634732876540877073470663234107440910393239389"),
        resultAsBigDecimal);
  }
}

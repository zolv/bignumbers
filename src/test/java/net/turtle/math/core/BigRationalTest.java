package net.turtle.math.core;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.context.BigMathContext;
import net.turtle.math.core.BigRational;
import net.turtle.math.exception.CalculationException;

public class BigRationalTest {
	
	@Test
	public void testBigRational() {
		{
			final BigRational br = new BigRational();
			Assert.assertEquals( new BigInteger( "0" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
	}
	
	@Test
	public void testBigRational_String() {
		{
			final BigRational br = new BigRational( "" );
			Assert.assertEquals( new BigInteger( "0" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( "-123/456" );
			Assert.assertEquals( new BigInteger( "-123" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "456" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( "-123/-456" );
			Assert.assertEquals( new BigInteger( "-123" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "-456" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( "-123.456" );
			Assert.assertEquals( new BigInteger( "-123456" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1000" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( "-123456" );
			Assert.assertEquals( new BigInteger( "-123456" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
	}
	
	@Test
	public void testBigRational_BigDecimal() {
		{
			final BigRational br = new BigRational( new BigDecimal( new BigInteger( "2" ), 6 ) );
			Assert.assertEquals( new BigInteger( "2" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1000000" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( new BigDecimal( new BigInteger( "2" ), -6 ) );
			Assert.assertEquals( new BigInteger( "2000000" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( new BigDecimal( new BigInteger( "2" ), 0 ) );
			Assert.assertEquals( new BigInteger( "2" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( new BigDecimal( "-123.456" ) );
			Assert.assertEquals( new BigInteger( "-123456" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1000" ), br.getDenominator() );
		}
	}
	
	@Test
	public void testBigRational_BigInteger() {
		{
			final BigRational br = new BigRational( new BigInteger( "0" ) );
			Assert.assertEquals( new BigInteger( "0" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( new BigInteger( "1" ) );
			Assert.assertEquals( new BigInteger( "1" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( new BigInteger( "-1" ) );
			Assert.assertEquals( new BigInteger( "-1" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
		{
			final Random r = new Random( new Date().getTime() );
			final String randomNumerator = Long.valueOf( r.nextLong() ).toString();
			final BigRational br = new BigRational( new BigInteger( randomNumerator ) );
			Assert.assertEquals( new BigInteger( randomNumerator ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
	}
	
	@Test
	public void testBigRational_BigInteger_BigInteger() {
		{
			final Random r = new Random( new Date().getTime() );
			final String randomNumerator = Long.valueOf( r.nextLong() ).toString();
			final String randomDenominator = Long.valueOf( r.nextLong() ).toString();
			final BigRational br = new BigRational( new BigInteger( randomNumerator ), new BigInteger( randomDenominator ) );
			Assert.assertEquals( new BigInteger( randomNumerator ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( randomDenominator ), br.getDenominator() );
		}
	}
	
	@Test
	public void testBigRational_BigInteger_BigInteger_boolean() {
		{
			final BigRational br = new BigRational( new BigInteger( "4" ), new BigInteger( "2" ), true );
			Assert.assertEquals( new BigInteger( "2" ), br.getNumerator() );
			Assert.assertEquals( new BigInteger( "1" ), br.getDenominator() );
		}
	}
	
	@Test ( expected = ArithmeticException.class )
	public void testBigRational_BigInteger_BigInteger_DivisionByZeroException() {
		final Random r = new Random( new Date().getTime() );
		final String randomNumerator = Long.valueOf( r.nextLong() ).toString();
		new BigRational( new BigInteger( randomNumerator ), new BigInteger( "0" ) );
	}
	
	@Test ( expected = NullPointerException.class )
	public void testBigRational_BigInteger_BigInteger_Null_1() {
		new BigRational( (BigInteger)null, BigInteger.valueOf( 2 ) );
		Assert.fail();
	}
	
	@Test ( expected = NullPointerException.class )
	public void testBigRational_BigInteger_BigInteger_Null_2() {
		new BigRational( BigInteger.valueOf( 2 ), (BigInteger)null );
		Assert.fail();
	}
	
	@Test
	public void testGetNumerator() {
		Assert.assertEquals( new BigInteger( "5" ), new BigRational( new BigInteger( "5" ) ).getNumerator() );
		Assert.assertEquals( new BigInteger( "5" ), new BigRational( new BigInteger( "5" ) ).getDividend() );
	}
	
	@Test
	public void testGetDenominator() {
		Assert.assertEquals( new BigInteger( "5" ), new BigRational( new BigInteger( "1" ), new BigInteger( "5" ) ).getDenominator() );
		Assert.assertEquals( new BigInteger( "5" ), new BigRational( new BigInteger( "1" ), new BigInteger( "5" ) ).getDivisor() );
	}
	
	@Test
	public void testNormalize() {
		{
			final BigRational br1 = new BigRational( "-4", "6" );
			final BigRational r1 = new BigRational( "-2", "3" );
			Assert.assertEquals( r1, br1.normalize() );
		}
		{
			final BigRational br1 = new BigRational( "4", "-6" );
			final BigRational r1 = new BigRational( "-2", "3" );
			Assert.assertEquals( r1, br1.normalize() );
		}
		{
			final BigRational br1 = new BigRational( "4", "6" );
			final BigRational r1 = new BigRational( "2", "3" );
			Assert.assertEquals( r1, br1.normalize() );
		}
		{
			final BigRational br1 = new BigRational( "-4", "-6" );
			final BigRational r1 = new BigRational( "2", "3" );
			Assert.assertEquals( r1, br1.normalize() );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational r1 = new BigRational( "0", "1" );
			Assert.assertEquals( r1, br1.normalize() );
		}
		{
			final BigRational br1 = new BigRational( "12", "60" );
			final BigRational r1 = br1.normalize();
			Assert.assertSame( r1, r1.normalize().normalize().normalize() );
		}
	}
	
	@Test
	public void testNormalizeSignum() {
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational r1 = new BigRational( "-2", "3" );
			Assert.assertEquals( r1, br1.normalizeSignum() );
		}
		{
			final BigRational br1 = new BigRational( "2", "-3" );
			final BigRational r1 = new BigRational( "-2", "3" );
			Assert.assertEquals( r1, br1.normalizeSignum() );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational r1 = new BigRational( "2", "3" );
			Assert.assertEquals( r1, br1.normalizeSignum() );
		}
		{
			final BigRational br1 = new BigRational( "-2", "-3" );
			final BigRational r1 = new BigRational( "2", "3" );
			Assert.assertEquals( r1, br1.normalizeSignum() );
		}
		{
			final BigRational br1 = new BigRational( "0", "-3" );
			final BigRational r1 = new BigRational( "0", "1" );
			Assert.assertEquals( r1, br1.normalizeSignum() );
		}
	}
	
	@Test
	public void testCancel() {
		final String multiplyOfFirst1000PrimesString = "678629608419755514953266004896957820972161078160377361970324401521111792080121479864721936071815069425907219215791646774510151130705671056416094404541167439287735488353736963531288441938981088407654256240451529081607242659988552012480001287133802278572298314458227654950008738955663072953766341488209509227159933381319371567666804963833249523370831655778314080604712246344649628072459805028063160913071005795183295590443375991860551286230065601580359306757988823124262933259305966372664091680948986620887898883461227980556352852601733860114246410887151983493540958775872577571329277597701163671587052591794386970584444752423596023268793021595936555282977008138833858707329536639661377014042325817639809356799596347944462538427778375525904007169834445567450156949173690701738594584875536885957881452438269676946038980597530032671949818526703398270502591574889228837327819994695664173214894557366363343168494592437205324652573516528943874382178600874878024643322031797588414862315122048846223291257900756812820806739795819803783834366449110996030165071920678407750230118672657378102915524688059208755108467225277065866103666795739208709483959119145497860116133180335757702319385020561042517429031288526721801002679092058170909635701703382390753126302005323612316630558515594616479515096004453718500060291836932140612551722161051067379805065002788004096547708243964735215852734827632098700684466036892770059458754742495711074949314613079781545359495019757827538184361308856825999513366660884541936335491466045305322353749545362962683762333460252556042583248154845846566948014188971651057314058851019340282646752239847232045463969939303431371658607220786663205842510175297602195433569758123945251755043878718459161595137019904240640962465899496512410906852088532419874383895656303779315512987369934711061777117329635461569528504994783413643047392160871963795694958724055597996525917454740621526108635321204763824742430011606570436994644169759611263012712375861911682673548369764923418748711813157811279361700331599397588282864147719911156923709896847720603482450047076226728760035577410722701184878333100234780537897462936378382079055966277885316116887834607362114802378706815302650083359076798475953780285866955566883261644281750278358349579977889429105626865087038835977930842352223971442123281019745568694318200865586150762549114357677130353514342849892002965601064686292493671204318349298134598116662388818407027989992498970986262856712232401426575229549744739851333516937170071337085705197690437625282926914858257689908846227286051735284322402597283976180484905838486513162987381659809287870592690902387482033879184700359561190209417618607868793293476867624464497838299321267571049753373623085351455438610076341961842557148160442782839736179329056237366708383637405663196770746783100179128651460773512143616414356080816160456447832856222804164147618891013658880373227849181446498052320436905124576367614898030410445386643656246089772967461562154147355201124738052009172637452710027640262529821855681129322547617443299372089380860873141895162966481252930360380537684913059090577224188204179681342669502124011214018434733385892140553307905100266308832521127607403573729242486985024795253305646999864066282626291530104297235324933472771821035277094700384260778312268190937365143307612108901729316774669077441981239149913617114331308200242717771235228048768133852203532299832810943137983635951570";
		final BigInteger multiplyOfFirst1000PrimesBigInteger = new BigInteger( multiplyOfFirst1000PrimesString );
		final BigRational multiplyOfFirst1000Primes = new BigRational( multiplyOfFirst1000PrimesBigInteger, multiplyOfFirst1000PrimesBigInteger );
		
		final long tripledPrimeDivisors = 2 * 2 * 2 * 3 * 3 * 3 * 5 * 5 * 5 * 7 * 7 * 7;
		{
			final BigInteger zero = BigInteger.ZERO;
			final BigInteger doubledPrimeDivisors2 = new BigInteger( Long.valueOf( tripledPrimeDivisors ).toString() );
			final BigRational br = new BigRational( zero, doubledPrimeDivisors2 ).cancel();
			Assert.assertEquals( zero, br.getNumerator() );
			Assert.assertEquals( BigInteger.ONE, br.getDenominator() );
		}
		{
			final BigRational br = multiplyOfFirst1000Primes.cancel();
			Assert.assertEquals( BigInteger.ONE, br.getNumerator() );
			Assert.assertEquals( BigInteger.ONE, br.getDenominator() );
		}
		{
			final BigRational br = new BigRational( multiplyOfFirst1000PrimesBigInteger.add( BigInteger.ONE ) );
			/*
			 * No new BigRational object is created.
			 */
			Assert.assertSame( br, br.cancel() );
		}
	}
	
	@Test
	public void testAdd() {
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			final BigRational r1 = new BigRational( "22", "15" );
			Assert.assertEquals( r1, br1.add( br2 ) );
			Assert.assertEquals( r1, br2.add( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			final BigRational r1 = new BigRational( "-2", "15" );
			Assert.assertEquals( r1, br1.add( br2 ) );
			Assert.assertEquals( r1, br2.add( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "5" );
			final BigRational r1 = new BigRational( "0", "15" );
			Assert.assertEquals( r1, br1.add( br2 ) );
			Assert.assertEquals( r1, br2.add( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "2", "1" );
			final BigRational br2 = new BigRational( "3", "1" );
			final BigRational r1 = new BigRational( "5", "1" );
			Assert.assertEquals( r1, br1.add( br2 ) );
			Assert.assertEquals( r1, br2.add( br1 ) );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testAdd_Null() {
		final BigRational br1 = new BigRational( "2", "3" );
		br1.add( (BigRational)null );
		Assert.fail();
	}
	
	@Test
	public void testSubtract() {
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			final BigRational r1 = new BigRational( "-2", "15" );
			Assert.assertEquals( r1, br1.subtract( br2 ) );
			final BigRational r2 = new BigRational( "2", "15" );
			Assert.assertEquals( r2, br2.subtract( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			final BigRational r1 = new BigRational( "22", "15" );
			Assert.assertEquals( r1, br1.subtract( br2 ) );
			final BigRational r2 = new BigRational( "-22", "15" );
			Assert.assertEquals( r2, br2.subtract( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "5" );
			final BigRational r1 = new BigRational( "0", "15" );
			Assert.assertEquals( r1, br1.subtract( br2 ) );
			final BigRational r2 = new BigRational( "0", "15" );
			Assert.assertEquals( r2, br2.subtract( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "2", "1" );
			final BigRational br2 = new BigRational( "3", "1" );
			final BigRational r1 = new BigRational( "-1", "1" );
			Assert.assertEquals( r1, br1.subtract( br2 ) );
			final BigRational r2 = new BigRational( "1", "1" );
			Assert.assertEquals( r2, br2.subtract( br1 ) );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testSubstract_Null() {
		final BigRational br1 = new BigRational( "2", "3" );
		br1.subtract( (BigRational)null );
		Assert.fail();
	}
	
	@Test
	public void testMultiply() {
		{
			/*
			 * Multiply by 0
			 */
			final BigRational br1 = new BigRational( "0", "2" );
			final BigRational br2 = new BigRational( "4", "5" );
			final BigRational r1 = new BigRational( "0", "1" );
			Assert.assertEquals( r1, br1.multiply( br2 ) );
			Assert.assertEquals( r1, br2.multiply( br1 ) );
		}
		{
			/*
			 * Multiply by 1
			 */
			final BigRational br1 = new BigRational( "5", "5" );
			final BigRational br2 = new BigRational( "4", "5" );
			final BigRational r1 = new BigRational( "4", "5" );
			Assert.assertEquals( r1, br1.multiply( br2 ) );
			Assert.assertSame( br2, br1.multiply( br2 ) );
			Assert.assertEquals( r1, br2.multiply( br1 ) );
			Assert.assertEquals( br2, br2.multiply( br1 ) );
		}
		{
			/*
			 * + and +
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			final BigRational r1 = new BigRational( "8", "15" );
			Assert.assertEquals( r1, br1.multiply( br2 ) );
			Assert.assertEquals( r1, br2.multiply( br1 ) );
		}
		{
			/*
			 * + and -
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			final BigRational r1 = new BigRational( "-8", "15" );
			Assert.assertEquals( r1, br1.multiply( br2 ) );
			Assert.assertEquals( r1, br2.multiply( br1 ) );
		}
		{
			/*
			 * - and -
			 */
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			final BigRational r1 = new BigRational( "8", "15" );
			Assert.assertEquals( r1, br1.multiply( br2 ) );
			Assert.assertEquals( r1, br2.multiply( br1 ) );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testMultiply_Null() {
		final BigRational br1 = new BigRational( "2", "3" );
		br1.multiply( (BigRational)null );
		Assert.fail();
	}
	
	@Test
	public void testDivide() throws ArithmeticException, NullPointerException {
		{
			/*
			 * + and +
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			final BigRational r1 = new BigRational( "10", "12" );
			Assert.assertEquals( r1, br1.divide( br2 ) );
			final BigRational r2 = new BigRational( "12", "10" );
			Assert.assertEquals( r2, br2.divide( br1 ) );
		}
		{
			/*
			 * + and -
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			final BigRational r1 = new BigRational( "-10", "12" );
			Assert.assertEquals( r1, br1.divide( br2 ) );
			final BigRational r2 = new BigRational( "-12", "10" );
			Assert.assertEquals( r2, br2.divide( br1 ) );
		}
		{
			/*
			 * - and -
			 */
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			final BigRational r1 = new BigRational( "10", "12" );
			Assert.assertEquals( r1, br1.divide( br2 ) );
			final BigRational r2 = new BigRational( "12", "10" );
			Assert.assertEquals( r2, br2.divide( br1 ) );
		}
	}
	
	@Test ( expected = ArithmeticException.class )
	public void testDivide_Zero() throws CalculationException {
		final BigRational br1 = new BigRational( "2", "3" );
		final BigRational br2 = new BigRational( "0", "5" );
		br1.divide( br2 );
		Assert.fail();
	}
	
	@Test ( expected = NullPointerException.class )
	public void testDivide_Null() throws ArithmeticException, NullPointerException {
		final BigRational br1 = new BigRational( "2", "3" );
		br1.divide( (BigRational)null );
		Assert.fail();
	}
	
	@Test
	public void testPowBigRational() {
		{
			final BigRational br1 = new BigRational( "2/3" );
			final BigRational r1 = new BigRational( "256/6561" );
			Assert.assertEquals( r1, br1.pow( new BigRational( "8" ) ) );
			Assert.assertEquals( r1, br1.pow( new BigRational( "16/2" ).normalize() ) );
		}
		{
			final BigRational br1 = new BigRational( "2/3" );
			final BigRational r1 = new BigRational( "6561/256" );
			Assert.assertEquals( r1, br1.pow( new BigRational( "-8" ) ) );
			Assert.assertEquals( r1, br1.pow( new BigRational( "-24/3" ) ) );
		}
		{
			final BigRational br1 = new BigRational( "10" );
			final BigRational r1 = new BigRational( "0.1" );
			Assert.assertEquals( r1, br1.pow( new BigRational( "-1" ) ) );
		}
		{
			final BigRational br1 = new BigRational( "0" );
			final BigRational r1 = new BigRational( "1" );
			Assert.assertEquals( r1, br1.pow( BigRational.ZERO ) );
		}
		
		{
			final BigRational br1 = new BigRational( "123" );
			final BigRational r1 = new BigRational( "6443858614676334363" );
			Assert.assertEquals( r1, br1.pow( BigInteger.valueOf( 9 ) ) );
		}
		
		{
			final BigRational br1 = new BigRational( "5" );
			final BigRational r1 = new BigRational( "5" );
			final BigRational result = br1.pow( new BigRational( "1" ) );
			Assert.assertEquals( r1, result );
			Assert.assertSame( br1, result );
		}
		{
			final BigRational br1 = new BigRational( "5" );
			final BigRational r1 = new BigRational( "5" );
			final BigRational result = br1.pow( new BigRational( "7/7" ) );
			Assert.assertEquals( r1, result );
			Assert.assertSame( br1, result );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testPowBigRational_Null() throws NullPointerException, ArithmeticException {
		final BigRational br1 = new BigRational( "2", "3" );
		br1.pow( (BigRational)null );
		Assert.fail();
	}
	
	@Test
	public void testPowBigInteger() {
		{
			final BigRational br1 = new BigRational( "2/3" );
			final BigRational r1 = new BigRational( "256/6561" );
			Assert.assertEquals( r1, br1.pow( BigInteger.valueOf( 8 ) ) );
		}
		{
			final BigRational br1 = new BigRational( "2/3" );
			final BigRational r1 = new BigRational( "6561/256" );
			Assert.assertEquals( r1, br1.pow( BigInteger.valueOf( -8 ) ) );
		}
		{
			final BigRational br1 = new BigRational( "10" );
			final BigRational r1 = new BigRational( "0.1" );
			Assert.assertEquals( r1, br1.pow( BigInteger.valueOf( -1 ) ) );
		}
		{
			final BigRational br1 = new BigRational( "0" );
			final BigRational r1 = new BigRational( "1" );
			Assert.assertEquals( r1, br1.pow( BigInteger.valueOf( 0 ) ) );
		}
		
		{
			final BigRational br1 = new BigRational( "123" );
			final BigRational r1 = new BigRational( "6443858614676334363" );
			Assert.assertEquals( r1, br1.pow( BigInteger.valueOf( 9 ) ) );
		}
		
		{
			final BigRational br1 = new BigRational( "5" );
			final BigRational r1 = new BigRational( "5" );
			final BigRational result = br1.pow( BigInteger.valueOf( 1 ) );
			Assert.assertEquals( r1, result );
			Assert.assertSame( br1, result );
		}
	}
	
	@Test ( expected = ArithmeticException.class )
	public void testPowBigInteger_ZeroMinusPow() throws NullPointerException, ArithmeticException {
		final BigRational br1 = new BigRational( "0" );
		br1.pow( BigInteger.valueOf( -2 ) );
		Assert.fail();
	}
	
	@Test ( expected = NullPointerException.class )
	public void testPowBigInteger_Null() throws NullPointerException, ArithmeticException {
		final BigRational br1 = new BigRational( "2", "3" );
		br1.pow( (BigInteger)null );
		Assert.fail();
	}
	
	@Test
	public void testAbs() {
		final BigRational r1 = new BigRational( "2", "3" );
		
		final BigRational br1 = new BigRational( "2", "3" );
		Assert.assertEquals( r1, br1.abs() );
		final BigRational br2 = new BigRational( "2", "-3" );
		Assert.assertEquals( r1, br2.abs() );
		final BigRational br3 = new BigRational( "-2", "3" );
		Assert.assertEquals( r1, br3.abs() );
		final BigRational br4 = new BigRational( "-2", "-3" );
		Assert.assertEquals( r1, br4.abs() );
	}
	
	@Test
	public void testNegate() {
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational r1 = new BigRational( "2", "3" );
			Assert.assertEquals( r1, br1.negate() );
		}
		{
			final BigRational br1 = new BigRational( "2", "-3" );
			final BigRational r1 = new BigRational( "-2", "-3" );
			Assert.assertEquals( r1, br1.negate() );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational r1 = new BigRational( "-2", "3" );
			Assert.assertEquals( r1, br1.negate() );
		}
		{
			final BigRational br1 = new BigRational( "-2", "-3" );
			final BigRational r1 = new BigRational( "2", "-3" );
			Assert.assertEquals( r1, br1.negate() );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational r1 = new BigRational( "0", "3" );
			Assert.assertEquals( r1, br1.negate() );
			Assert.assertEquals( br1, br1.negate().negate() );
			Assert.assertEquals( r1, br1.negate().negate().negate() );
		}
	}
	
	@Test
	public void testInverse() {
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational r1 = new BigRational( "3", "-2" );
			Assert.assertEquals( r1, br1.inverse() );
			Assert.assertEquals( br1, br1.inverse().inverse() );
			Assert.assertEquals( r1, br1.inverse().inverse().inverse() );
		}
		{
			final BigRational br1 = new BigRational( "1", "1" );
			final BigRational r1 = new BigRational( "1", "1" );
			Assert.assertEquals( r1, br1.inverse() );
		}
	}
	
	@Test ( expected = ArithmeticException.class )
	public void testInverse_Zero() {
		new BigRational( "0", "3" ).inverse();
	}
	
	@Test
	public void testSignum() {
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			Assert.assertEquals( -1, br1.signum() );
		}
		{
			final BigRational br1 = new BigRational( "2", "-3" );
			Assert.assertEquals( -1, br1.signum() );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			Assert.assertEquals( 1, br1.signum() );
		}
		{
			final BigRational br1 = new BigRational( "-2", "-3" );
			Assert.assertEquals( 1, br1.signum() );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			Assert.assertEquals( 0, br1.signum() );
		}
	}
	
	@Test
	public void testMin() {
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "2", "5" );
			Assert.assertSame( br1, br1.min( br2 ) );
			Assert.assertSame( br1, br2.min( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "6" );
			Assert.assertSame( br1, br1.min( br2 ) );
			Assert.assertSame( br2, br2.min( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "5" );
			Assert.assertSame( br1, br1.min( br2 ) );
			Assert.assertSame( br2, br2.min( br1 ) );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testMin_Null() {
		final BigRational br1 = new BigRational( "-2", "3" );
		br1.min( null );
		Assert.fail();
	}
	
	@Test
	public void testMax() {
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "2", "5" );
			Assert.assertSame( br2, br1.max( br2 ) );
			Assert.assertSame( br2, br2.max( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "6" );
			Assert.assertSame( br2, br1.max( br2 ) );
			Assert.assertSame( br1, br2.max( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "5" );
			Assert.assertSame( br2, br1.max( br2 ) );
			Assert.assertSame( br1, br2.max( br1 ) );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testMax_Null() {
		final BigRational br1 = new BigRational( "-2", "3" );
		br1.max( null );
		Assert.fail();
	}
	
	@Test
	public void testSquare() {
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational r1 = new BigRational( "4", "9" );
			Assert.assertEquals( r1, br1.square() );
		}
		{
			final BigRational br1 = new BigRational( "2", "-3" );
			final BigRational r1 = new BigRational( "4", "9" );
			Assert.assertEquals( r1, br1.square() );
		}
	}
	
	@Test
	public void testCube() {
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational r1 = new BigRational( "8", "27" );
			Assert.assertEquals( r1, br1.cube() );
		}
		{
			final BigRational br1 = new BigRational( "2", "-3" );
			final BigRational r1 = new BigRational( "8", "-27" );
			Assert.assertEquals( r1, br1.cube() );
		}
	}
	
	@Test
	public void testCompareTo() {
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			Assert.assertEquals( -1, br1.compareTo( br2 ) );
			Assert.assertEquals( 1, br2.compareTo( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			Assert.assertEquals( -1, br1.compareTo( br2 ) );
			Assert.assertEquals( 1, br2.compareTo( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "-4", "5" );
			Assert.assertEquals( 1, br1.compareTo( br2 ) );
			Assert.assertEquals( -1, br2.compareTo( br1 ) );
		}
		
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "6" );
			Assert.assertEquals( 0, br1.compareTo( br2 ) );
			Assert.assertEquals( 0, br2.compareTo( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "-4", "6" );
			Assert.assertEquals( 0, br1.compareTo( br2 ) );
			Assert.assertEquals( 0, br2.compareTo( br1 ) );
		}
		{
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "4", "-5" );
			Assert.assertEquals( 1, br1.compareTo( br2 ) );
			Assert.assertEquals( -1, br2.compareTo( br1 ) );
		}
		
		{
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "5" );
			Assert.assertEquals( 0, br1.compareTo( br2 ) );
			Assert.assertEquals( 0, br2.compareTo( br1 ) );
		}
	}
	
	@Test
	public void equalsByValue() {
		{
			/*
			 * Equals null
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			Assert.assertFalse( br1.equals( null ) );
		}
		{
			/*
			 * Same instance
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			Assert.assertTrue( br1.equals( br1 ) );
		}
		
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			Assert.assertFalse( br1.equals( br2 ) );
			Assert.assertFalse( br2.equals( br1 ) );
		}
		
		{
			/*
			 * Equal by value
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "6" );
			Assert.assertTrue( br1.equals( br2 ) );
			Assert.assertTrue( br2.equals( br1 ) );
		}
		{
			/*
			 * Equal by value
			 */
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "-4", "6" );
			Assert.assertTrue( br1.equals( br2 ) );
			Assert.assertTrue( br2.equals( br1 ) );
		}
		
		{
			/*
			 * Zero
			 */
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "-5" );
			Assert.assertTrue( br1.equals( br2 ) );
			Assert.assertTrue( br2.equals( br1 ) );
		}
	}
	
	@Test
	public void equalsStrict() {
		BigMathContext.get().setStrictEqualsAndHashContract( true );
		{
			/*
			 * Equals null
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			Assert.assertFalse( br1.equals( null ) );
		}
		{
			/*
			 * Same instance
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			Assert.assertTrue( br1.equals( br1 ) );
		}
		
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "5" );
			Assert.assertFalse( br1.equals( br2 ) );
			Assert.assertFalse( br2.equals( br1 ) );
		}
		
		{
			/*
			 * Equal by value
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "4", "6" );
			Assert.assertFalse( br1.equals( br2 ) );
			Assert.assertFalse( br2.equals( br1 ) );
		}
		{
			/*
			 * Equal by value
			 */
			final BigRational br1 = new BigRational( "-2", "3" );
			final BigRational br2 = new BigRational( "-4", "6" );
			Assert.assertFalse( br1.equals( br2 ) );
			Assert.assertFalse( br2.equals( br1 ) );
		}
		
		{
			/*
			 * Zero
			 */
			final BigRational br1 = new BigRational( "0", "3" );
			final BigRational br2 = new BigRational( "0", "-5" );
			Assert.assertFalse( br1.equals( br2 ) );
			Assert.assertFalse( br2.equals( br1 ) );
		}
		BigMathContext.get().setStrictEqualsAndHashContract( false );
	}
	
	@Test
	public void hashCodeByValue() {
		{
			final BigRational br1 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			final BigRational br2 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			Assert.assertTrue( br1.hashCode() == br2.hashCode() );
		}
		{
			final BigRational br1 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			final BigRational br2 = new BigRational( new BigInteger( "4" ), new BigInteger( "6" ) );
			Assert.assertTrue( br1.hashCode() == br2.hashCode() );
		}
	}
	
	@Test
	public void hashCodeStrict() {
		BigMathContext.get().setStrictEqualsAndHashContract( true );
		{
			final BigRational br1 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			final BigRational br2 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			Assert.assertTrue( br1.hashCode() == br2.hashCode() );
		}
		{
			final BigRational br1 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			final BigRational br2 = new BigRational( new BigInteger( "4" ), new BigInteger( "6" ) );
			Assert.assertFalse( br1.hashCode() == br2.hashCode() );
		}
		BigMathContext.get().setStrictEqualsAndHashContract( false );
	}
	
	@Test
	public void equalsHashCodeContractByValue() {
		{
			final Set< BigRational > set = new HashSet< BigRational >();
			set.add( new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) ) );
			final BigRational br2 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			Assert.assertTrue( set.contains( br2 ) );
			Assert.assertTrue( set.contains( new BigRational( "4/6" ) ) );
		}
		{
			final Set< BigRational > set = new HashSet< BigRational >();
			set.add( new BigRational( new BigInteger( "3" ), new BigInteger( "6" ) ).normalize() );
			final BigRational br2 = new BigRational( new BigInteger( "5" ), new BigInteger( "10" ) );
			Assert.assertTrue( set.contains( br2.normalize() ) );
			Assert.assertTrue( set.contains( br2 ) );
		}
	}
	
	@Test
	public void equalsHashCodeContractStrict() {
		BigMathContext.get().setStrictEqualsAndHashContract( true );
		{
			final Set< BigRational > set = new HashSet< BigRational >();
			set.add( new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) ) );
			final BigRational br2 = new BigRational( new BigInteger( "2" ), new BigInteger( "3" ) );
			Assert.assertTrue( set.contains( br2 ) );
			Assert.assertFalse( set.contains( new BigRational( "4/6" ) ) );
		}
		{
			final Set< BigRational > set = new HashSet< BigRational >();
			set.add( new BigRational( new BigInteger( "3" ), new BigInteger( "6" ) ).normalize() );
			final BigRational br2 = new BigRational( new BigInteger( "5" ), new BigInteger( "10" ) );
			Assert.assertTrue( set.contains( br2.normalize() ) );
			Assert.assertFalse( set.contains( br2 ) );
		}
		BigMathContext.get().setStrictEqualsAndHashContract( false );
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals( "2/1", new BigRational( "2/1" ).toString() );
		Assert.assertEquals( "2/3", new BigRational( "2", "3" ).toString() );
		Assert.assertEquals( "-2/3", new BigRational( "-2", "3" ).toString() );
		Assert.assertEquals( "2/-3", new BigRational( "2", "-3" ).toString() );
		Assert.assertEquals( "-2/-3", new BigRational( "-2", "-3" ).toString() );
	}
	
	@Test
	public void testChain() throws ArithmeticException, NullPointerException {
		{
			final BigRational br1 = new BigRational( "1" );
			final BigRational br2 = new BigRational( "3" );
			final BigRational r1 = new BigRational( "6" );
			Assert.assertEquals( r1, br1.divide( br2 ).multiply( br2 ).subtract( new BigRational( "-2" ) ).add( new BigRational( "3" ) ) );
		}
	}
}

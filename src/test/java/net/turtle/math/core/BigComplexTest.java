package net.turtle.math.core;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

import net.turtle.math.core.BigComplex;
import net.turtle.math.core.BigRational;

public class BigComplexTest {
	
	
	@Test
	public void testBigComplex() {
		Assert.assertEquals( new BigRational( "0", "1" ), new BigComplex().getA() );
		Assert.assertEquals( new BigRational( "0", "1" ), new BigComplex().getB() );
	}
	
	@Test
	public void testBigComplexBigRational() {
		Assert.assertEquals( new BigRational( "2" ), new BigComplex( new BigRational( "2" ) ).getA() );
		Assert.assertEquals( BigRational.ZERO, new BigComplex( new BigRational( "2" ) ).getB() );
	}
	
	@Test
	public void testBigComplexBigRationalBigRational() {
		{
			final BigComplex c1 = new BigComplex( new BigRational( "2" ), new BigRational( "3" ) );
			Assert.assertEquals( new BigRational( "2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "3" ), c1.getB() );
		}
	}
	
	// @Test
	// public void testBigComplexString() {
	// {
	// final BigComplex c1 = new BigComplex("2");
	// Assert.assertEquals(new BigRational("2"), c1.getA());
	// Assert.assertEquals(BigRational.ZERO, c1.getB());
	// }
	// {
	// final BigComplex c1 = new BigComplex("3i");
	// Assert.assertEquals(BigRational.ZERO, c1.getA());
	// Assert.assertEquals(new BigRational("3"), c1.getB());
	// }
	// {
	// final BigComplex c1 = new BigComplex("2");
	// Assert.assertEquals(new BigRational("2"), c1.getA());
	// Assert.assertEquals(BigRational.ZERO, c1.getB());
	// }
	// {
	// final BigComplex c1 = new BigComplex("2+3i");
	// Assert.assertEquals(new BigRational("2"), c1.getA());
	// Assert.assertEquals(new BigRational("3"), c1.getB());
	// }
	// {
	// final BigComplex c1 = new BigComplex("-2-3i");
	// Assert.assertEquals(new BigRational("-2"), c1.getA());
	// Assert.assertEquals(new BigRational("-3"), c1.getB());
	// }
	// {
	// final BigComplex c1 = new BigComplex("-2/3-5/7i");
	// Assert.assertEquals(new BigRational("-2/3"), c1.getA());
	// Assert.assertEquals(new BigRational("-5/7"), c1.getB());
	// }
	// }
	
	@Test
	public void testBigComplexString() {
		/*
		 * a
		 */
		{
			final BigComplex c1 = new BigComplex( "2" );
			Assert.assertEquals( new BigRational( "2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2" );
			Assert.assertEquals( new BigRational( "-2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2" );
			Assert.assertEquals( new BigRational( "2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "2.3" );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2.3" );
			Assert.assertEquals( new BigRational( "-2.3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2.3" );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "2/3" );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2/3" );
			Assert.assertEquals( new BigRational( "-2/3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2/3" );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "0" ), c1.getB() );
		}
		
		/*
		 * a+bi
		 */
		{
			final BigComplex c1 = new BigComplex( "2+3i" );
			Assert.assertEquals( new BigRational( "2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2-3i" );
			Assert.assertEquals( new BigRational( "-2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2+3i" );
			Assert.assertEquals( new BigRational( "2" ), c1.getA() );
			Assert.assertEquals( new BigRational( "3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "2.3+2.3i" );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2.3-2.3i" );
			Assert.assertEquals( new BigRational( "-2.3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-2.3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2.3+2.3i" );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "2/3+2/3i" );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2/3-2/3i" );
			Assert.assertEquals( new BigRational( "-2/3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-2/3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2/3+2/3i" );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getB() );
		}
		
		/*
		 * bi
		 */
		{
			final BigComplex c1 = new BigComplex( "2i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-2" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "2.3i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2.3i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-2.3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2.3i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2.3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "2/3i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-2/3i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-2/3" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "+2/3i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "2/3" ), c1.getB() );
		}
		
		/*
		 * i
		 */
		{
			final BigComplex c1 = new BigComplex( "i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "1" ), c1.getB() );
		}
		{
			final BigComplex c1 = new BigComplex( "-i" );
			Assert.assertEquals( new BigRational( "0" ), c1.getA() );
			Assert.assertEquals( new BigRational( "-1" ), c1.getB() );
		}
	}
	
	@Test ( expected = NullPointerException.class )
	public void testBigComplexBigRationalBigRational_nullA() {
		new BigComplex( null, new BigRational( "3" ) );
		Assert.fail();
	}
	
	@Test ( expected = NullPointerException.class )
	public void testBigComplexBigRationalBigRational_nullB() {
		new BigComplex( new BigRational( "3" ), null );
		Assert.fail();
	}
	
	@Test
	public void testGetA() {
		Assert.assertEquals( new BigRational( "2" ), new BigComplex( new BigRational( "2" ), new BigRational( "3" ) ).getA() );
		Assert.assertEquals( new BigRational( "2" ), new BigComplex( new BigRational( "2" ), new BigRational( "3" ) ).getReal() );
	}
	
	@Test ( expected = NullPointerException.class )
	public void testGetA_null() {
		new BigComplex( null, new BigRational( "3" ) ).getA();
		Assert.fail();
	}
	
	@Test
	public void testGetB() {
		Assert.assertEquals( new BigRational( "3" ), new BigComplex( new BigRational( "2" ), new BigRational( "3" ) ).getB() );
		Assert.assertEquals( new BigRational( "3" ), new BigComplex( new BigRational( "2" ), new BigRational( "3" ) ).getImaginary() );
	}
	
	@Test ( expected = NullPointerException.class )
	public void testGetB_null() {
		new BigComplex( new BigRational( "2" ), null ).getB();
		Assert.fail();
	}
	
	@Test
	public void testNormalize() {
		{
			final BigRational a1 = new BigRational( "4", "-6" );
			final BigRational b1 = new BigRational( "-12", "-20" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigComplex reduced = c1.normalize();
			Assert.assertEquals( BigInteger.valueOf( -2 ), reduced.getA().getNumerator() );
			Assert.assertEquals( BigInteger.valueOf( 3 ), reduced.getA().getDenominator() );
			Assert.assertEquals( BigInteger.valueOf( 3 ), reduced.getB().getNumerator() );
			Assert.assertEquals( BigInteger.valueOf( 5 ), reduced.getB().getDenominator() );
			Assert.assertNotSame( c1, reduced );
		}
		
		{
			final BigRational a1 = new BigRational( "-2", "3" );
			final BigRational b1 = new BigRational( "-3", "5" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigComplex reduced = c1.normalize();
			Assert.assertSame( c1, reduced );
		}
		
	}
	
	@Test
	public void testNormalizeSignum() {
		{
			final BigRational a1 = new BigRational( "2", "-3" );
			final BigRational b1 = new BigRational( "-3", "-5" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigComplex reduced = c1.normalizeSignum();
			Assert.assertEquals( BigInteger.valueOf( -2 ), reduced.getA().getNumerator() );
			Assert.assertEquals( BigInteger.valueOf( 3 ), reduced.getA().getDenominator() );
			Assert.assertEquals( BigInteger.valueOf( 3 ), reduced.getB().getNumerator() );
			Assert.assertEquals( BigInteger.valueOf( 5 ), reduced.getB().getDenominator() );
			Assert.assertNotSame( c1, reduced );
		}
		
		{
			final BigRational a1 = new BigRational( "-2", "3" );
			final BigRational b1 = new BigRational( "-3", "5" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigComplex reduced = c1.normalizeSignum();
			Assert.assertSame( c1, reduced );
		}
		
		{
			final BigRational a1 = new BigRational( "-2", "3" );
			final BigRational b1 = new BigRational( "3", "-5" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigComplex reduced = c1.normalizeSignum();
			Assert.assertEquals( BigInteger.valueOf( -2 ), reduced.getA().getNumerator() );
			Assert.assertEquals( BigInteger.valueOf( 3 ), reduced.getA().getDenominator() );
			Assert.assertEquals( BigInteger.valueOf( -3 ), reduced.getB().getNumerator() );
			Assert.assertEquals( BigInteger.valueOf( 5 ), reduced.getB().getDenominator() );
			Assert.assertNotSame( c1, reduced );
		}
		
	}
	
	@Test
	public void testCancelBigComplex() {
		{
			final String multiplyOfFirst1000PrimesString = "678629608419755514953266004896957820972161078160377361970324401521111792080121479864721936071815069425907219215791646774510151130705671056416094404541167439287735488353736963531288441938981088407654256240451529081607242659988552012480001287133802278572298314458227654950008738955663072953766341488209509227159933381319371567666804963833249523370831655778314080604712246344649628072459805028063160913071005795183295590443375991860551286230065601580359306757988823124262933259305966372664091680948986620887898883461227980556352852601733860114246410887151983493540958775872577571329277597701163671587052591794386970584444752423596023268793021595936555282977008138833858707329536639661377014042325817639809356799596347944462538427778375525904007169834445567450156949173690701738594584875536885957881452438269676946038980597530032671949818526703398270502591574889228837327819994695664173214894557366363343168494592437205324652573516528943874382178600874878024643322031797588414862315122048846223291257900756812820806739795819803783834366449110996030165071920678407750230118672657378102915524688059208755108467225277065866103666795739208709483959119145497860116133180335757702319385020561042517429031288526721801002679092058170909635701703382390753126302005323612316630558515594616479515096004453718500060291836932140612551722161051067379805065002788004096547708243964735215852734827632098700684466036892770059458754742495711074949314613079781545359495019757827538184361308856825999513366660884541936335491466045305322353749545362962683762333460252556042583248154845846566948014188971651057314058851019340282646752239847232045463969939303431371658607220786663205842510175297602195433569758123945251755043878718459161595137019904240640962465899496512410906852088532419874383895656303779315512987369934711061777117329635461569528504994783413643047392160871963795694958724055597996525917454740621526108635321204763824742430011606570436994644169759611263012712375861911682673548369764923418748711813157811279361700331599397588282864147719911156923709896847720603482450047076226728760035577410722701184878333100234780537897462936378382079055966277885316116887834607362114802378706815302650083359076798475953780285866955566883261644281750278358349579977889429105626865087038835977930842352223971442123281019745568694318200865586150762549114357677130353514342849892002965601064686292493671204318349298134598116662388818407027989992498970986262856712232401426575229549744739851333516937170071337085705197690437625282926914858257689908846227286051735284322402597283976180484905838486513162987381659809287870592690902387482033879184700359561190209417618607868793293476867624464497838299321267571049753373623085351455438610076341961842557148160442782839736179329056237366708383637405663196770746783100179128651460773512143616414356080816160456447832856222804164147618891013658880373227849181446498052320436905124576367614898030410445386643656246089772967461562154147355201124738052009172637452710027640262529821855681129322547617443299372089380860873141895162966481252930360380537684913059090577224188204179681342669502124011214018434733385892140553307905100266308832521127607403573729242486985024795253305646999864066282626291530104297235324933472771821035277094700384260778312268190937365143307612108901729316774669077441981239149913617114331308200242717771235228048768133852203532299832810943137983635951570";
			final BigRational multiplyOfFirst1000Primes = new BigRational( multiplyOfFirst1000PrimesString + "/" + multiplyOfFirst1000PrimesString );
			final BigComplex bc = new BigComplex( multiplyOfFirst1000Primes, multiplyOfFirst1000Primes );
			
			final BigComplex reduced = bc.cancel();
			Assert.assertEquals( BigInteger.ONE, reduced.getA().getNumerator() );
			Assert.assertEquals( BigInteger.ONE, reduced.getA().getDenominator() );
			Assert.assertEquals( BigInteger.ONE, reduced.getB().getNumerator() );
			Assert.assertEquals( BigInteger.ONE, reduced.getB().getDenominator() );
		}
		{
			final BigComplex bc = new BigComplex( new BigRational( "2", "3" ), new BigRational( "5", "7" ) );
			Assert.assertSame( bc, bc.cancel() );
		}
		{
			final BigComplex bc = new BigComplex( new BigRational( "2", "3" ), new BigRational( "5", "10" ) );
			Assert.assertNotSame( bc, bc.cancel() );
		}
	}
	
	@Test
	public void testAdd() {
		{
			final BigComplex c1 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex c2 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex cr = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			Assert.assertEquals( cr, c1.add( c2 ) );
		}
		{
			final BigRational a1 = new BigRational( "2" );
			final BigRational b1 = new BigRational( "3" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigRational a2 = new BigRational( "4" );
			final BigRational b2 = new BigRational( "5" );
			final BigComplex c2 = new BigComplex( a2, b2 );
			
			final BigRational ar = new BigRational( "6" );
			final BigRational br = new BigRational( "8" );
			final BigComplex cr = new BigComplex( ar, br );
			
			Assert.assertEquals( cr, c1.add( c2 ) );
		}
	}
	
	@Test
	public void testSubtract() {
		{
			final BigComplex c1 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex c2 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex cr = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			Assert.assertEquals( cr, c1.subtract( c2 ) );
		}
		{
			final BigRational a1 = new BigRational( "2" );
			final BigRational b1 = new BigRational( "3" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigRational a2 = new BigRational( "4" );
			final BigRational b2 = new BigRational( "6" );
			final BigComplex c2 = new BigComplex( a2, b2 );
			
			final BigRational ar = new BigRational( "-2" );
			final BigRational br = new BigRational( "-3" );
			final BigComplex cr = new BigComplex( ar, br );
			
			Assert.assertEquals( cr, c1.subtract( c2 ) );
		}
	}
	
	@Test
	public void testMultiply() {
		{
			final BigComplex c1 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex c2 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex cr = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			Assert.assertEquals( cr, c1.multiply( c2 ) );
		}
		{
			final BigRational a1 = new BigRational( "2" );
			final BigRational b1 = new BigRational( "3" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigRational a2 = new BigRational( "5" );
			final BigRational b2 = new BigRational( "7" );
			final BigComplex c2 = new BigComplex( a2, b2 );
			
			final BigRational ar = new BigRational( "-11" );
			final BigRational br = new BigRational( "29" );
			final BigComplex cr = new BigComplex( ar, br );
			
			Assert.assertEquals( cr, c1.multiply( c2 ) );
		}
	}
	
	@Test
	public void testDivide() throws ArithmeticException, NullPointerException {
		{
			final BigComplex c1 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex c2 = new BigComplex( BigRational.ZERO, BigRational.ONE );
			final BigComplex cr = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			Assert.assertEquals( cr, c1.divide( c2 ) );
		}
		{
			final BigRational a1 = new BigRational( "2" );
			final BigRational b1 = new BigRational( "3" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigRational a2 = new BigRational( "5" );
			final BigRational b2 = new BigRational( "7" );
			final BigComplex c2 = new BigComplex( a2, b2 );
			
			final BigRational ar = new BigRational( "31/74" );
			final BigRational br = new BigRational( "1/74" );
			final BigComplex cr = new BigComplex( ar, br );
			
			Assert.assertEquals( cr, c1.divide( c2 ) );
		}
	}
	
	@Test
	public void testModuleSquared() {
		{
			final BigComplex c1 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigRational r = BigRational.ZERO;
			Assert.assertEquals( r, c1.absSquared() );
		}
		{
			final BigComplex c1 = new BigComplex( new BigRational( "-2" ), new BigRational( "-3" ) );
			final BigRational r = new BigRational( "13" );
			Assert.assertEquals( r, c1.absSquared() );
		}
	}
	
	@Test
	public void testNegate() {
		{
			final BigComplex c1 = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			final BigComplex cr = new BigComplex( BigRational.ZERO, BigRational.ZERO );
			Assert.assertEquals( cr, c1.negate() );
		}
		{
			final BigRational a1 = new BigRational( "2" );
			final BigRational b1 = new BigRational( "3" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			
			final BigRational ar = new BigRational( "-2" );
			final BigRational br = new BigRational( "-3" );
			final BigComplex cr = new BigComplex( ar, br );
			
			Assert.assertEquals( cr, c1.negate() );
		}
	}
	
	@Test
	public void testInverse() {
		{
			final BigComplex c1 = new BigComplex( new BigRational( "2" ), new BigRational( "3" ) );
			final BigComplex cr = new BigComplex( new BigRational( "2/13" ), new BigRational( "-3/13" ) );
			Assert.assertEquals( cr, c1.inverse() );
			Assert.assertEquals( c1, c1.inverse().inverse() );
			Assert.assertEquals( cr, c1.inverse().inverse().inverse() );
			
			Assert.assertEquals( BigComplex.ONE, c1.inverse().multiply( c1 ) );
		}
	}
	
	@Test ( expected = ArithmeticException.class )
	public void testInverse_ZeroDivision() {
		{
			final BigComplex c1 = new BigComplex( new BigRational( "0" ), new BigRational( "0" ) );
			c1.inverse();
			Assert.fail();
		}
	}
	
	@Test
	public void testConjugate() {
		{
			final BigComplex c1 = new BigComplex( new BigRational( "2" ), new BigRational( "-3" ) );
			final BigComplex cr = new BigComplex( new BigRational( "2" ), new BigRational( "3" ) );
			Assert.assertEquals( cr, c1.conjugate() );
		}
		{
			final BigComplex c1 = new BigComplex( new BigRational( "0" ), new BigRational( "0" ) );
			final BigComplex cr = new BigComplex( new BigRational( "0" ), new BigRational( "0" ) );
			Assert.assertEquals( cr, c1.conjugate() );
		}
	}
	
	@Test
	public void testCompareTo() {
		{
			final BigRational br11 = new BigRational( "6", "2" );
			final BigRational br12 = new BigRational( "6", "3" );
			final BigComplex bc1 = new BigComplex( br11, br12 );
			
			final BigRational br21 = new BigRational( "6", "3" );
			final BigRational br22 = new BigRational( "10", "5" );
			final BigComplex bc2 = new BigComplex( br21, br22 );
			
			Assert.assertEquals( 1, bc1.compareTo( bc2 ) );
			Assert.assertEquals( -1, bc2.compareTo( bc1 ) );
		}
		
		{
			final BigRational br11 = new BigRational( "1", "2" );
			final BigRational br12 = new BigRational( "3", "5" );
			final BigComplex bc1 = new BigComplex( br11, br12 );
			
			final BigRational br21 = new BigRational( "5", "10" );
			final BigRational br22 = new BigRational( "6", "10" );
			final BigComplex bc2 = new BigComplex( br21, br22 );
			
			Assert.assertEquals( 0, bc1.compareTo( bc2 ) );
			Assert.assertEquals( 0, bc2.compareTo( bc1 ) );
		}
		
	}
	
	@Test
	public void testEqualsObject() {
		{
			/*
			 * Equals null
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "3", "5" );
			final BigComplex bc1 = new BigComplex( br1, br2 );
			Assert.assertFalse( bc1.equals( null ) );
		}
		{
			/*
			 * Same instance
			 */
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "3", "5" );
			final BigComplex bc1 = new BigComplex( br1, br2 );
			Assert.assertTrue( bc1.equals( bc1 ) );
		}
		
		{
			/*
			 * Equal by value
			 */
			final BigRational br11 = new BigRational( "2", "3" );
			final BigRational br12 = new BigRational( "3", "5" );
			final BigComplex bc1 = new BigComplex( br11, br12 );
			
			final BigRational br21 = new BigRational( "4", "6" );
			final BigRational br22 = new BigRational( "9", "15" );
			final BigComplex bc2 = new BigComplex( br21, br22 );
			
			final BigRational br31 = new BigRational( "4", "6" );
			final BigRational br32 = new BigRational( "10", "15" );
			final BigComplex bc3 = new BigComplex( br31, br32 );
			
			final BigRational br41 = new BigRational( "5", "6" );
			final BigRational br42 = new BigRational( "10", "15" );
			final BigComplex bc4 = new BigComplex( br41, br42 );
			
			Assert.assertTrue( bc1.equals( bc2 ) );
			Assert.assertTrue( bc2.equals( bc1 ) );
			Assert.assertFalse( bc2.equals( bc3 ) );
			Assert.assertFalse( bc2.equals( bc4 ) );
		}
		{
			/*
			 * Zero
			 */
			final BigRational br11 = new BigRational( "0", "3" );
			final BigRational br12 = new BigRational( "0", "-5" );
			final BigComplex bc1 = new BigComplex( br11, br12 );
			
			final BigRational br21 = new BigRational( "0", "-6" );
			final BigRational br22 = new BigRational( "0", "15" );
			final BigComplex bc2 = new BigComplex( br21, br22 );
			
			Assert.assertTrue( bc1.equals( bc2 ) );
			Assert.assertTrue( bc2.equals( bc1 ) );
		}
		
		{
			final BigRational br1 = new BigRational( "2", "3" );
			final BigRational br2 = new BigRational( "3", "5" );
			final BigComplex bc1 = new BigComplex( br1, br2 );
			Assert.assertFalse( bc1.equals( new Object() ) );
		}
		
	}
	
	@Test
	public void testHashCode() {
		{
			final BigRational a1 = new BigRational( "2/3" );
			final BigRational b1 = new BigRational( "4/5" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			final BigRational a2 = new BigRational( "2/3" );
			final BigRational b2 = new BigRational( "4/5" );
			final BigComplex c2 = new BigComplex( a2, b2 );
			Assert.assertTrue( c1.hashCode() == c2.hashCode() );
		}
		{
			final BigRational a1 = new BigRational( "2/3" );
			final BigRational b1 = new BigRational( "4/5" );
			final BigComplex c1 = new BigComplex( a1, b1 );
			final BigRational a2 = new BigRational( "4/6" );
			final BigRational b2 = new BigRational( "8/10" );
			final BigComplex c2 = new BigComplex( a2, b2 );
			Assert.assertFalse( c1.hashCode() == c2.hashCode() );
		}
	}
	
	@Test
	public void testToString() {
		Assert.assertEquals( "2/1+3/1i", new BigComplex( new BigRational( "2" ), new BigRational( "3" ) ).toString() );
		Assert.assertEquals( "-2/1+3/1i", new BigComplex( new BigRational( "-2" ), new BigRational( "3" ) ).toString() );
		Assert.assertEquals( "2/1-3/1i", new BigComplex( new BigRational( "2" ), new BigRational( "-3" ) ).toString() );
		Assert.assertEquals( "-2/1-3/1i", new BigComplex( new BigRational( "-2" ), new BigRational( "-3" ) ).toString() );
		Assert.assertEquals( "0/1+3/1i", new BigComplex( new BigRational( "0" ), new BigRational( "3" ) ).toString() );
		Assert.assertEquals( "0/1+0/1i", new BigComplex( new BigRational( "0" ), new BigRational( "0" ) ).toString() );
	}
	
}

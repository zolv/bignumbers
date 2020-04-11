package net.turtle.math.core;

import java.math.BigInteger;

import net.turtle.math.core.BigRational;

public class BigRationalValues {

  public static final BigRational ZERO = BigRational.ZERO;

  public static final BigRational ONE = BigRational.ONE;

  public static final BigRational TWO = new BigRational(BigInteger.valueOf(2));

  public static final BigRational THREE = new BigRational(BigInteger.valueOf(3));

  public static final BigRational FOUR = new BigRational(BigInteger.valueOf(4));

  public static final BigRational FIVE = new BigRational(BigInteger.valueOf(5));

  public static final BigRational SIX = new BigRational(BigInteger.valueOf(6));

  public static final BigRational SEVEN = new BigRational(BigInteger.valueOf(7));

  public static final BigRational EIGHT = new BigRational(BigInteger.valueOf(8));

  public static final BigRational NINE = new BigRational(BigInteger.valueOf(9));

  public static final BigRational TEN = new BigRational(BigInteger.valueOf(10));

  public static final BigRational ELEVEN = new BigRational(BigInteger.valueOf(11));

  public static final BigRational TWELVE = new BigRational(BigInteger.valueOf(12));

  public static final BigRational THIRTEEN = new BigRational(BigInteger.valueOf(13));

  public static final BigRational FOURTEEN = new BigRational(BigInteger.valueOf(14));

  public static final BigRational FIFTEEN = new BigRational(BigInteger.valueOf(15));

  public static final BigRational SIXTEEN = new BigRational(BigInteger.valueOf(16));

  /**
   * <div>Value of Answer to the Ultimate Question of Life, The Universe, and Everything.</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/42_(number)">42 on Wikipedia</a>
   */
  public static final BigRational FORTY_TWO = new BigRational(BigInteger.valueOf(42));

  /** <div>Value of 100</div> <div>Mainly used for percentage calculations.</div> */
  public static final BigRational ONE_HUNDRED = new BigRational(BigInteger.valueOf(100));

  public static final BigRational PI_10 = new BigRational("3.1415926536");

  /**
   * According to <a href="https://www.youtube.com/watch?v=FpyrF_Ci2TQ">NUmberphile</a> (dr James
   * Grime) 39 digits (38 decimal places) of pi is enough to calculate circumference of the
   * observable Universe within a width of 1 hydrogen atom.
   */
  public static final BigRational PI_39 =
      new BigRational("3.14159265358979323846264338327950288420");

  public static final BigRational PI_100 =
      new BigRational(
          "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170680");

  public static final BigRational PI_1000 =
      new BigRational(
          "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081284811174502841027019385211055596446229489549303819644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412737245870066063155881748815209209628292540917153643678925903600113305305488204665213841469519415116094330572703657595919530921861173819326117931051185480744623799627495673518857527248912279381830119491298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051320005681271452635608277857713427577896091736371787214684409012249534301465495853710507922796892589235420199561121290219608640344181598136297747713099605187072113499999983729780499510597317328160963185950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473035982534904287554687311595628638823537875937519577818577805321712268066130019278766111959092164201989");

  /**
   * <div>Value of π with best accuracy currently supported.</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Pi">π on Wikipedia</a>
   */
  public static final BigRational PI = PI_1000;

  public static final BigRational TAU_10 = new BigRational("6.2831853072");

  /** See {link {@link #PI_39}. */
  public static final BigRational TAU_39 =
      new BigRational("6.28318530717958647692528676655900576839");

  public static final BigRational TAU_100 =
      new BigRational(
          "6.2831853071795864769252867665590057683943387987502116419498891846156328125724179972560696506842341360");

  public static final BigRational TAU_1000 =
      new BigRational(
          "6.2831853071795864769252867665590057683943387987502116419498891846156328125724179972560696506842341359642961730265646132941876892191011644634507188162569622349005682054038770422111192892458979098607639288576219513318668922569512964675735663305424038182912971338469206972209086532964267872145204982825474491740132126311763497630418419256585081834307287357851807200226610610976409330427682939038830232188661145407315191839061843722347638652235862102370961489247599254991347037715054497824558763660238982596673467248813132861720427898927904494743814043597218874055410784343525863535047693496369353388102640011362542905271216555715426855155792183472743574429368818024499068602930991707421015845593785178470840399122242580439217280688363196272595495426199210374144226999999967459560999021194634656321926371900489189106938166052850446165066893700705238623763420200062756775057731750664167628412343553382946071965069808575109374623191257277647075751875039155637155610643424536132260038557532223918184328403979");

  /**
   * <div>Value of τ with best accuracy currently supported.</div>
   *
   * @see <a href= "https://en.wikipedia.org/wiki/Turn_(geometry)#Tau_proposal"> τ on Wikipedia</a>
   */
  public static final BigRational TAU = TAU_1000;

  public static final BigRational E_10 = new BigRational("2.7182818285");

  public static final BigRational E_100 =
      new BigRational(
          "2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274");

  public static final BigRational E_1000 =
      new BigRational(
          "2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154249992295763514822082698951936680331825288693984964651058209392398294887933203625094431173012381970684161403970198376793206832823764648042953118023287825098194558153017567173613320698112509961818815930416903515988885193458072738667385894228792284998920868058257492796104841984443634632449684875602336248270419786232090021609902353043699418491463140934317381436405462531520961836908887070167683964243781405927145635490613031072085103837505101157477041718986106873969655212671546889570350354");

  /**
   * <div>Value of e with best accuracy currently supported.</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/E_(mathematical_constant)">e on Wikipedia</a>
   */
  public static final BigRational E = E_1000;

  public static final BigRational SQUARE_ROOT_2_10 = new BigRational("1.4142135624");

  public static final BigRational SQUARE_ROOT_2_100 =
      new BigRational(
          "1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");

  public static final BigRational SQUARE_ROOT_2_1000 =
      new BigRational(
          "1.4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727350138462309122970249248360558507372126441214970999358314132226659275055927557999505011527820605714701095599716059702745345968620147285174186408891986095523292304843087143214508397626036279952514079896872533965463318088296406206152583523950547457502877599617298355752203375318570113543746034084988471603868999706990048150305440277903164542478230684929369186215805784631115966687130130156185689872372352885092648612494977154218334204285686060146824720771435854874155657069677653720226485447015858801620758474922657226002085584466521458398893944370926591800311388246468157082630100594858704003186480342194897278290641045072636881313739855256117322040245091227700226941127573627280495738108967504018369868368450725799364729060762996941380475654823728997180326802474420629269124859052181004459842150591120249441341728531478105803603371077309182869314710171111683916581726889419758716582152128229518488472");

  /**
   * <div>Value of square root of 2 with best accuracy currently supported.</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Square_root_of_2">Square root of 2 on Wikipedia</a>
   */
  public static final BigRational SQUARE_ROOT_2 = SQUARE_ROOT_2_1000;

  public static final BigRational SQUARE_ROOT_3_10 = new BigRational("1.7320508076");

  public static final BigRational SQUARE_ROOT_3_100 =
      new BigRational(
          "1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811461867572485757");

  public static final BigRational SQUARE_ROOT_3_1000 =
      new BigRational(
          "1.7320508075688772935274463415058723669428052538103806280558069794519330169088000370811461867572485756756261414154067030299699450949989524788116555120943736485280932319023055820679748201010846749232650153123432669033228866506722546689218379712270471316603678615880190499865373798593894676503475065760507566183481296061009476021871903250831458295239598329977898245082887144638329173472241639845878553976679580638183536661108431737808943783161020883055249016700235207111442886959909563657970871684980728994932964842830207864086039887386975375823173178313959929830078387028770539133695633121037072640192491067682311992883756411414220167427521023729942708310598984594759876642888977961478379583902288548529035760338528080643819723446610596897228728652641538226646984200211954841552784411812865345070351916500166892944154808460712771439997629268346295774383618951101271486387469765459824517885509753790138806649619119622229571105552429237231921977382625616314688420328537166829386496119170497388363954959381");

  /**
   * <div>Value of square root of 3 with best accuracy currently supported.</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Square_root_of_3">Square root of 3 on Wikipedia</a>
   */
  public static final BigRational SQUARE_ROOT_3 = SQUARE_ROOT_3_1000;

  public static final BigRational SQUARE_ROOT_5_10 = new BigRational("2.2360679775");

  public static final BigRational SQUARE_ROOT_5_100 =
      new BigRational(
          "2.2360679774997896964091736687312762354406183596115257242708972454105209256378048994144144083787822750");

  public static final BigRational SQUARE_ROOT_5_1000 =
      new BigRational(
          "2.2360679774997896964091736687312762354406183596115257242708972454105209256378048994144144083787822749695081761507737835042532677244470738635863601215334527088667781731918791658112766453226398565805357613504175337850034233924140644420864325390972525926272288762995174024406816117759089094984923713907297288984820886415426898940991316935770197486788844250897541329561831769214999774248015304341150359576683325124988151781394080005624208552435422355561063063428202340933319829339597463522712013417496142026359047378855043896870611356600457571399565955669569175645782219525000605392312340050092867648755297220567662536660744858535052623306784946334222423176372770266324076801044433158257335058930981362263431986864719469899701808189524264459620345221411922329125981963258111041704958070481204034559949435068555518555725123886416550102624363125710244496187894246829034044747161154557232017376765904609185295756035779843980541553807790643936397230287560629994822138521773485924535151210463455550407072278724");

  /**
   * <div>Value of square root of 5 with best accuracy currently supported.</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Square_root_of_5">Square root of 5 on Wikipedia</a>
   */
  public static final BigRational SQUARE_ROOT_5 = SQUARE_ROOT_5_1000;

  public static final BigRational GOLDEN_RATIO_10 = new BigRational("1.6180339887");

  public static final BigRational GOLDEN_RATIO_100 =
      new BigRational(
          "1.6180339887498948482045868343656381177203091798057628621354486227052604628189024497072072041893911375");

  public static final BigRational GOLDEN_RATIO_1000 =
      new BigRational(
          "1.6180339887498948482045868343656381177203091798057628621354486227052604628189024497072072041893911374847540880753868917521266338622235369317931800607667263544333890865959395829056383226613199282902678806752087668925017116962070322210432162695486262963136144381497587012203408058879544547492461856953648644492410443207713449470495658467885098743394422125448770664780915884607499887124007652170575179788341662562494075890697040002812104276217711177780531531714101170466659914669798731761356006708748071013179523689427521948435305678300228785699782977834784587822891109762500302696156170025046433824377648610283831268330372429267526311653392473167111211588186385133162038400522216579128667529465490681131715993432359734949850904094762132229810172610705961164562990981629055520852479035240602017279974717534277759277862561943208275051312181562855122248093947123414517022373580577278616008688382952304592647878017889921990270776903895321968198615143780314997411069260886742962267575605231727775203536139362");

  /**
   * <div>Value of golden ratio with best accuracy currently supported.</div> <div>golden ratio = (
   * 1 + {@link #SQUARE_ROOT_5} ) / 2</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Golden_ratio">Golden Ratio on Wikipedia</a>
   */
  public static final BigRational GOLDEN_RATIO = GOLDEN_RATIO_1000;

  public static final BigRational SILVER_RATIO_10 = SQUARE_ROOT_2_10.add(ONE);

  public static final BigRational SILVER_RATIO_100 = SQUARE_ROOT_2_100.add(ONE);

  public static final BigRational SILVER_RATIO_1000 = SQUARE_ROOT_2_1000.add(ONE);

  /**
   * <div>Value of silver ratio with best accuracy currently supported.</div> <div>silver ratio = 1
   * + {@link #SQUARE_ROOT_2}</div>
   *
   * @see <a href="https://en.wikipedia.org/wiki/Silver_ratio">Silver Ratio on Wikipedia</a>
   */
  public static final BigRational SILVER_RATIO = SILVER_RATIO_1000;

  private BigRationalValues() {
    super();
  }
}

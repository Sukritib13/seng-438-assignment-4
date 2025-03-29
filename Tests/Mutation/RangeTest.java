package org.jfree.data;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;
import java.security.InvalidParameterException;

public class RangeTest {
    private Range exampleRange;
    private Range expectedRange;
    private Range CompRange;

    @BeforeClass public static void setUpBeforeClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception { 
        exampleRange = new Range(-1, 1);
    }
    	
    
    //testing contains
    
    @Test
    public void testContainsexactlower() {
    	exampleRange = new Range(5,10);
    	assertTrue(exampleRange.contains(5));
    	assertEquals(exampleRange.getUpperBound(), 10, 0.000000001d);
    	assertEquals(exampleRange.getLowerBound(), 5, 0.000000001d);
    }
    @Test
    public void testContainsexactupper() {
    	exampleRange = new Range(5,10);
    	expectedRange = new Range(5,10);
    	assertTrue(exampleRange.contains(10));
    	assertEquals(exampleRange,expectedRange);
    }
    @Test
    public void testContainsslightlylower() {
    	exampleRange = new Range(5,10);
    	expectedRange = new Range(5,10);
    	assertFalse(exampleRange.contains(4));
    	assertEquals(exampleRange,expectedRange);
    }
    @Test
    public void testContainsslightlyhigher() {
    	exampleRange = new Range(5,10);
    	expectedRange = new Range(5,10);
    	assertFalse(exampleRange.contains(11));
    	assertEquals(exampleRange,expectedRange);
    }
    @Test
    public void testContainsslightlyinsidelower() {
    	exampleRange = new Range(5,10);
    	expectedRange = new Range(5,10);
    	assertTrue(exampleRange.contains(6));
    	assertEquals(exampleRange,expectedRange);
    }
    @Test
    public void testContainsslightlyinsideghigher() {
    	exampleRange = new Range(5,10);
    	expectedRange = new Range(5,10);
    	assertTrue(exampleRange.contains(9));
    	assertEquals(exampleRange,expectedRange);
    }
    
    // constructor tests
    @Test
    public void testConstructormessage() {
        try {
            new Range(5, 1); // This should throw an IllegalArgumentException
            fail("Expected IllegalArgumentException was not thrown.");
        } catch (IllegalArgumentException e) {
            // Test passes if IllegalArgumentException is caught
            assertEquals("Range(double, double): require lower (5.0) <= upper (1.0).", e.getMessage());
        }
    }
    @Test
    public void testConstructor() {
    	exampleRange = new Range(5,9);
    	assertEquals(9, exampleRange.getUpperBound(), .000000001d);
    	assertEquals(5, exampleRange.getLowerBound(), .000000001d);
    }
    //equals() section

    @Test
    public void ShouldEqualtest() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(1, 1);
        assertTrue("Comparing 1,1 to 1,1 should equal",
        exampleRange.equals(CompRange));
        assertEquals(1, exampleRange.getUpperBound(), .000000001d);
    	assertEquals(1, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void LowerBoundSmall() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(0, 1);
    	assertFalse("Comparing 1,1 to 0,1 should not equal", 
    	exampleRange.equals(CompRange));
    }
    
    @Test
    public void LowerBoundBig() {
    	CompRange = new Range(2, 2);
    	exampleRange = new Range(1, 2);
    	assertFalse("Comparing 1,2 to 2,2 should not equal",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void HigherBoundSmall() {
    	exampleRange = new Range(4, 8);
    	CompRange = new Range(4, 7);
    	assertFalse("Comparing 4, 8 to 4, 7 should not equal",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void HigherBoundBig() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(1, 2);
    	assertFalse("Comparing 1,1 to 1,2 should not equal",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void BothNonMatchingHigh() {
    	exampleRange = new Range(1,2);
    	CompRange = new Range(3,4);
    	assertFalse("1,2 should not equal 3,4",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void BothNonMatchingLow() {
    	exampleRange = new Range(4,5);
    	CompRange = new Range(1,2);
    	assertFalse("4,5 should not equal 1,2",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void ComparingNegativeNumbers() {
    	exampleRange = new Range(-2,-1);
    	CompRange = new Range(-2, -1);
    	assertTrue("-2,-1 and -2, -1 should equal",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void ComparingNegativewithPositive() {
    	exampleRange = new Range(-2,3);
    	CompRange = new Range(-2,3);
    	assertTrue("-2, 3 and -2, 3 should equal",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void ComparingMismatchSigns() {
    	exampleRange = new Range(2,3);
    	CompRange = new Range(-2,3);
    	assertFalse("2, 3 and -2, 3 should not equal",
    			exampleRange.equals(CompRange));
    }

    @Test
    public void ComparingToNull() {
    	assertFalse("Comparing -1,1 to Null should not equal",
    			exampleRange.equals(null));
    }
    
    //getLength() section
    
    @Test
    public void all0s() {
    	exampleRange = new Range(0, 0);
    	CompRange = new Range(0,0);
    	assertEquals("0, 0 should have a range of 0",
    			0, exampleRange.getLength(), .000000001d);
    	assertEquals(CompRange,exampleRange);
    }

    @Test
    public void all1s() {
    	exampleRange = new Range(1, 1);
    	CompRange = new Range(1,1);
    	assertEquals("1, 1 should have a range of 0",
    			0, exampleRange.getLength(), .000000001d);
    	assertEquals(CompRange,exampleRange);
    }

    @Test
    public void PositiveLength1() {
    	exampleRange = new Range(1, 2);
    	CompRange = new Range(1,2);
    	assertEquals("1, 2 should have a range of 1",
    			1, exampleRange.getLength(), .000000001d);
    	assertEquals(CompRange,exampleRange);
    }

    @Test
    public void NegativeLength1() {
    	exampleRange = new Range(-2, -1);
    	CompRange = new Range(-2,-1);
    	assertEquals("-2, -1 should have a range of 1",
    			1, exampleRange.getLength(), .000000001d);
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void LengthAcross0() {
    	exampleRange = new Range(-1, 1);
    	CompRange = new Range(-1,1);
    	assertEquals("-1, 1 should have a range of 2",
    			2, exampleRange.getLength(), .000000001d);
    	assertEquals(CompRange,exampleRange);
    }

    //constrian() section
   
    @Test
    public void testConstrainWithinRange() {
        assertEquals("A value within the range should return itself", 0.5, exampleRange.constrain(0.5), 0.000000001d);
        assertEquals("Value afterwards should equal 1", exampleRange.getUpperBound(), 1, 0.000000001d);
    	assertEquals("Value afterwards should equal -1", exampleRange.getLowerBound(), -1, 0.000000001d);
    }
    @Test
    public void testConstrainBelowLowerBound() {
        assertEquals("A value below the lower bound should return the lower bound", -1, exampleRange.constrain(-2), 0.000000001d);
        assertEquals("Value afterwards should equal 1", exampleRange.getUpperBound(), 1, 0.000000001d);
    	assertEquals("Value afterwards should equal -1", exampleRange.getLowerBound(), -1, 0.000000001d);
    }
    @Test
    public void testConstrainAboveUpperBound() {
    	CompRange = new Range(-1,1);
        assertEquals("A value above the upper bound should return the upper bound", 1, exampleRange.constrain(2), 0.000000001d);
        assertEquals(CompRange,exampleRange);
    }
    //Shift() section
     @Test
    public void testPositiveDeltaValueWithNoZeroCrossingAndZeroCrossingFalseForShift() {
    	expectedRange = new Range(0,2);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by 1 with no zero crossing the range -1,1 should become 0,2",expectedRange,Range.shift(exampleRange,1));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testNegativeDeltaValueWithNoZeroCrossingAndZeroCrossingFalseForShift() {
    	expectedRange = new Range(-2,0);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by -1 with no zero crossing the range -1,1 should become -2,0",expectedRange,Range.shift(exampleRange, -1));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testPositiveDeltaValueWithNoZeroCrossingAndZeroCrossingTrueForShift() {
    	expectedRange = new Range(0,2);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by 1 with zero crossing the range -1,1 should become 0,2",expectedRange,Range.shift(exampleRange,1,true));
    }
    
    @Test
    public void testNegativeDeltaValueWithNoZeroCrossingAndZeroCrossingTrueForShift() {
    	expectedRange = new Range(-2,0);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by -1 with zero crossing the range -1,1 should become -2,0",expectedRange,Range.shift(exampleRange, -1,true));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testNegativeDeltaValueWithZeroCrossingFalseForShift() {
    	expectedRange = new Range(-3,0);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by -2 with no zero crossing the range -1,1 should become -3,0",expectedRange,Range.shift(exampleRange, -2));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testPositiveDeltaValueWithZeroCrossingFalseForShift() {
    	expectedRange = new Range(0,3);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by 2 with no zero crossing the range -1,1 should become 0,3",expectedRange,Range.shift(exampleRange, 2));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testPositiveDeltaValueWithZeroCrossingTrueForShift() {
    	expectedRange = new Range(1,3);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by 2 with zero crossing the range -1,1 should become 1,3",expectedRange,Range.shift(exampleRange, 2, true));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testNegativeDeltaValueWithZeroCrossingTrueForShift() {
    	expectedRange = new Range(-3,-1);
    	CompRange = new Range(-1,1);
    	assertEquals("After shifting by -2 with zero crossing the range -1,1 should become -3,-1",expectedRange,Range.shift(exampleRange, -2, true));
    	assertEquals(CompRange,exampleRange);
    }

    @Test
    public void testZeroDeltaValueWithZeroCrossingFalseForShift() {
    	expectedRange = new Range(-1,1);
    	CompRange = new Range(-1,1);
    	assertEquals("After shiftingg by 0 with no zero crossing the range -1,1 should remain 1,1", expectedRange,Range.shift(exampleRange,0));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testZeroDeltaValueWithZeroCrossingTrueForShift() {
    	expectedRange = new Range(-1,1);
    	CompRange = new Range(-1,1);
    	assertEquals("After shiftingg by 0 with zero crossing the range -1,1 should remain 1,1", expectedRange,Range.shift(exampleRange,0));
    	assertEquals(CompRange,exampleRange);
    }
    
//    @Test
//    public void testExceptionWhenNullRangePassedForShift() {
//    	try {
//    		Range.shift(null, 1);
//    	}catch(InvalidParameterException e) {
//    		
//    	}catch(Exception e){
//    		fail("When passed a null shift should throw a InvalidParameterError but instead throws:" + e.getMessage());
//    	}
//    }
    @Test
    public void testShiftingZeroWithZeroCrossingFalse() {
    	expectedRange = new Range(0,0);
    	exampleRange = new Range(0,0);
    	CompRange = new Range(0,0);
    	assertEquals("AfterShifting range (0,0) by 0 result should be (0,0)",expectedRange,Range.shift(exampleRange,0,false));
    	assertEquals(CompRange,exampleRange);
    }

    //Expand Section()
    @Test
    public void testLowerAndUpperBoundWithPercentage() {
    	expectedRange = new Range(-2,2);
    	CompRange = new Range(-1,1);
    	assertEquals("After expanding the range -1,1 by 0.5,0.5 the returned range should be -2,2",expectedRange,Range.expand(exampleRange, 0.5, 0.5));
    	assertEquals(CompRange,exampleRange);
    }
    @Test
    public void testZeroLowerMarginForExpand() {
    	expectedRange = new Range(-1,2);
    	exampleRange = Range.expand(exampleRange, 0, 0.5);
    	assertEquals("After expanding the range -1,1 by 0,0.5 the returned range should be -1,2", -1 , exampleRange.getLowerBound(), .000000001d);
    	assertEquals("After expanding the range -1,1 by 0,0.5 the returned range should be -1,2", 2 , exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testZeroUpperMarginForExpand() {
    	expectedRange = new Range(-2,1);
    	exampleRange = Range.expand(exampleRange, 0.5, 0);
    	assertEquals("After expanding the range -1,1 by 0.5,0 the returned range should be -2,1", -2 ,exampleRange.getLowerBound(), .000000001d);
    	assertEquals("After expanding the range -1,1 by 0.5,0 the returned range should be -2,1", 1 ,exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testZeroLowerMarginAndUpperMarginForExpand() {
    	expectedRange = new Range(-1,1);
    	CompRange = new Range(-1,1);
    	assertEquals("After expanding the range -1,1 by 0,0 the returned range should be -1,1",expectedRange,Range.expand(exampleRange, 0, 0));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testLowerMarginGreaterThanOne() {
    	expectedRange = new Range(-5,2);
    	CompRange = new Range(-1,1);
    	assertEquals("After expanding the range -1,1 by 2,0.5 the returned range should be -5,2",expectedRange,Range.expand(exampleRange, 2, 0.5));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testUpperMarginGreaterThanOne() {
    	expectedRange = new Range(-2,5);
    	CompRange = new Range(-1,1);
    	assertEquals("After expanding the range -1,1 by 0.5,2 the returned range should be -2,5",expectedRange,Range.expand(exampleRange, 0.5, 2));
    	assertEquals(CompRange,exampleRange);
    }
    
    @Test
    public void testLowerAndUpperMarginGreaterThanOne() {
    	expectedRange = new Range(-5,5);
    	CompRange = new Range(-1,1);
    	assertEquals("After expanding the range -1,1 by 2,2 the returned range should be -5,5",expectedRange,Range.expand(exampleRange, 2, 2));
    	assertEquals(CompRange,exampleRange);
    }
    @Test 
    public void testLowerSurpassingUpperBoundWithExpand() {
    	expectedRange = new Range(2,2);
    	assertEquals("After expanding the range -1,1 by -2,0 the returned range should be 2,2",expectedRange,Range.expand(exampleRange, -2,0));
    }
//    @Test
//    public void testExceptionWhenNullRangePassedForExpand() {
//    	try {
//    		Range.expand(null, 1,1);
//    	}catch(InvalidParameterException e) {
//    		
//    	}catch(Exception e){
//    		fail("When passed a null shift should throw a InvalidParameterError but instead throws:" + e.getMessage());
//    	}
//    }
    //to string test
    @Test
    public void testValidRangeToString() {
    	assertEquals("the range(-1,1) should be expressed in string form as Range[-1.0,1.0]","Range[-1.0,1.0]", exampleRange.toString());
    	assertEquals("Should not alter original range",exampleRange.getUpperBound(), 1, 0.000000001d);
    	assertEquals("Should not alter original range",exampleRange.getLowerBound(), -1, 0.000000001d);
    }
    //isNanRange test
    @Test
    public void testNanRangeInIsNanRange() {
    	exampleRange = new Range(Double.NaN,Double.NaN);
    	double value = exampleRange.getUpperBound();
    	double value2 = exampleRange.getLowerBound();
    	assertTrue("the range(Double.NaN,Double.NaN) should be identified as a nan range",exampleRange.isNaNRange());
    	assertEquals("Should not alter original range",exampleRange.getUpperBound(), value, 0.000000001d);
    	assertEquals("Should not alter original range",exampleRange.getLowerBound(), value2, 0.000000001d);
    }
    
    @Test
    public void testValidRangeInIsNanRange() {
    	assertFalse("the range(-1,1) should be identified as not NaN", exampleRange.isNaNRange());
    }
    
    @Test
    public void testLowerHalfValidRangeInIsNanRange() {
    	exampleRange = new Range(0,Double.NaN);
    	assertTrue("the range(0,Double.NaN) should be identified as a nan range",exampleRange.isNaNRange());
    }
    
    @Test
    public void testUpperHalfValidRangeInIsNanRange() {
    	exampleRange = new Range(Double.NaN,0);
    	assertTrue("the range(Double.NaN,0) should be identified as a nan range",exampleRange.isNaNRange());
    }
    //test for scale()
    @Test (expected = IllegalArgumentException.class)
    public void testNullRangeInScale() throws IllegalArgumentException{
    	Range.scale(null, 0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testInvalidScaleInScale() throws IllegalArgumentException{
    	Range.scale(exampleRange, -1);
    }
    
    @Test
    public void testValidScaleInScale() {
    	expectedRange = new Range(-2,2);
    	assertEquals("the range (-1,1) scaled by 2 should be (-2,2)",expectedRange,Range.scale(exampleRange,2));
    }

    //expandToIncludeTests
    @Test
    public void testNullRangeInExpandToInclude() {
    	expectedRange = new Range(1,1);
    	assertEquals("when passed a null range expandToInclude should return a range including only the include value",expectedRange,Range.expandToInclude(null,1));
    }
    
    @Test
    public void testValueBelowRangeForExpandToInclude() {
    	expectedRange = new Range(-2,1);
    	assertEquals("the range -1,1 should be expanded to -2,1 to include -2",expectedRange,Range.expandToInclude(exampleRange, -2));
    }
    
    @Test
    public void testValueAboveRangeForExpandToInclude() {
    	expectedRange = new Range(-1,2);
    	assertEquals("the range -1,1 should be expanded to -1,2 to include 2",expectedRange,Range.expandToInclude(exampleRange, 2));
    }
    
    @Test
    public void testValueWithinRangeForExpandToInclude() {
    	expectedRange = new Range(-1,1);
    	assertEquals("the range -1,1 shoudl reamin -1,1 to include 0",expectedRange,Range.expandToInclude(exampleRange, 0));
    }
    
  //getCentralValue
    @Test
    public void testgetCentralvalue() {
    	expectedRange = new Range(1, 1);
    	assertEquals("Central value should be 1",  expectedRange.getCentralValue(), 1.0, 0.000000001d);
    }
    @Test
    public void testgetCentralvaluewider() {
    	expectedRange = new Range(5, 10);
    	assertEquals("Central value should be 7.5",  expectedRange.getCentralValue(), 7.5, 0.000000001d);
    	assertEquals("Value afterwards should equal 10", expectedRange.getUpperBound(), 10, 0.000000001d);
    	assertEquals("Value afterwards should equal 5", expectedRange.getLowerBound(), 5, 0.000000001d);
    	
    }
    //Intersects
    @Test
    public void testIntersectdoubleMethodlower() {
    	expectedRange = new Range(1, 5);
    	assertTrue("1, 4 should return True", expectedRange.intersects(1.0, 4.0));
    }
    @Test
    public void testIntersectdoubleMethodlowerfalse() {
    	expectedRange = new Range(1, 5);
    	assertFalse("1, -1 should return False", expectedRange.intersects(1, -1));
    }
    @Test
    public void testIntersectdecrementedlowerbound() {
    	expectedRange = new Range(3, 5);
    	assertTrue("1, -1 should return False", expectedRange.intersects(3, 6));
    	assertEquals("Value afterwards should equal 5", expectedRange.getUpperBound(), 5, 0.000000001d);
    	assertEquals("Value afterwards should equal 3", expectedRange.getLowerBound(), 3, 0.000000001d);
    }
    
    @Test
    public void testIntersectiondoubleMethodHigh() {
    	expectedRange = new Range(3, 5);
    	assertTrue("4, 6 should intersect", expectedRange.intersects(4, 4));
    	assertEquals("Value afterwards should equal 5", expectedRange.getUpperBound(), 5, 0.000000001d);
    	assertEquals("Value afterwards should equal 3", expectedRange.getLowerBound(), 3, 0.000000001d);
    }
    @Test
    public void testIntersectiondoubleMethodHighFalse() {
    	expectedRange = new Range(3, 5);
    	assertFalse("4, -6 shouldnt intersect", expectedRange.intersects(4, -6));
    }
    
    @Test
    public void testIntersectionRangeMethod() {
    	exampleRange = new Range(1,5);
    	expectedRange = new Range(2,3);
    	assertTrue("Range 1,3 and 2,3 should intersect", exampleRange.intersects(expectedRange));
    }
    @Test
    public void testIntersectionRangeMethodreverse() {
    	expectedRange = new Range(1,5);
    	exampleRange = new Range(2,3);
    	assertTrue("Range 1,3 and 2,3 should intersect", exampleRange.intersects(expectedRange));
    	assertEquals("Value afterwards should equal 5", expectedRange.getUpperBound(), 5, 0.000000001d);
    	assertEquals("Value afterwards should equal 1", expectedRange.getLowerBound(), 1, 0.000000001d);
    }
    
    // test Combine
    @Test
    public void testCombineleftNull() {
    	exampleRange = new Range(1, 3);
    	CompRange = Range.combine(null, exampleRange);
    	assertTrue("Expected Null return", exampleRange.equals(CompRange));
    }
    @Test
    public void testCombinerightNull() {
    	exampleRange = new Range(2,3);
    	CompRange = Range.combine(exampleRange, null);
    	assertTrue("Expected Null Return", exampleRange.equals(CompRange));
    }
    @Test
    public void testCombine() {
    	expectedRange = new Range(1,1);
    	exampleRange = new Range(0, 2);
    	CompRange = Range.combine(exampleRange, expectedRange);
    	
    	assertTrue("Bounds should equal", exampleRange.equals(CompRange));
    }
    //Test combine ignoring NaN
    @Test
    public void testCombineNaNleftNull() {
    	exampleRange = new Range(Double.NaN,Double.NaN);
    	assertNull("Expected Null return", Range.combineIgnoringNaN(null, exampleRange));
    }
    @Test
    public void testCombineNaNleft() {
    	exampleRange = new Range(1,1);
    	CompRange = Range.combineIgnoringNaN(null, exampleRange);
    	assertTrue("Expected to return 1, l range", exampleRange.equals(CompRange));
    }
    
    @Test
    public void testCombineNaNRightNull() {
    	exampleRange = new Range(Double.NaN,Double.NaN);
    	assertNull("Expected Null return", Range.combineIgnoringNaN(exampleRange, null));
    }
    @Test
    public void testCombineNaNRight() {
    	exampleRange = new Range(1,1);
    	CompRange = Range.combineIgnoringNaN(exampleRange, null);
    	assertTrue("Expected to return 1, l range", exampleRange.equals(CompRange));
    }
    @Test
    public void testCombineNaN() {
    	exampleRange = new Range(1,5);
    	expectedRange = new Range(0, 7);
    	CompRange = Range.combineIgnoringNaN(exampleRange, expectedRange);
    	assertTrue("should return range 0, 7", expectedRange.equals(CompRange));
    }
    @Test
    public void testCombineNaNBoth() {
    	exampleRange = new Range(Double.NaN,Double.NaN);
    	expectedRange = new Range(Double.NaN,Double.NaN);
    	assertNull("should return null", Range.combineIgnoringNaN(exampleRange, expectedRange));
    }
    //Testing illegal argument for constructor
    @Test(expected = IllegalArgumentException.class)
    public void testingIllegalConstructor() {
    	expectedRange = new Range(9, 1);
    }
    
    //testing min and max
    @Test
    public void testingMinwithNaN() {
    	exampleRange = new Range(1, 1);
    	expectedRange = new Range(Double.NaN, Double.NaN);
    	CompRange = Range.combineIgnoringNaN(exampleRange, expectedRange);
    	assertTrue("should return True ", exampleRange.equals(CompRange));
    }
    
    //testing hashcode
    @Test
    public void testHashcode() {
    	assertEquals("The function hashcode should return -31457280 for the range -1,1",-31457280,exampleRange.hashCode());
    }
    //testing contains
    @Test
    public void testContainsNegativeEdge() {
    	assertTrue("Given value -1 should be contained in -1,1",exampleRange.contains(-1));
    }
    
    @Test
    public void testContainsPositiveEdge() {
    	assertTrue("Given value 1 should be contained in -1,1",exampleRange.contains(1));
    }
    
    //testing intersects
    @Test
    public void testBarelyIntersectsNegative() {
    	assertTrue("Given range -2,0 should intersect -1,1",exampleRange.intersects(-2,0));
    }
    
    @Test
    public void testBarelyIntersectsPositive() {
    	assertTrue("Given range 0,2 should intersect -1,1",exampleRange.intersects(0,2));
    }
    
    @Test
    public void testlargerRangeIntersects() {
    	assertTrue("Given range -2,2 should intersect -1,1",exampleRange.intersects(-2,2));
    }
    
    @Test
    public void testnotIntersectingNegative() {
    	assertFalse("Given range -3,-2 should not intersect -1,1",exampleRange.intersects(-3,-2));
    }
    
    @Test
    public void testnotintersectingPositive() {
    	assertFalse("Given range 2,3 should not intersect -1,1",exampleRange.intersects(2,3));
    }
    
    @Test
    public void testBarelyIntersectsNegativeWithRange() {
    	expectedRange = new Range(-2,0);
    	assertTrue("Given range -2,0 should intersect -1,1",exampleRange.intersects(expectedRange));
    }
    
    @Test
    public void testBarelyIntersectsPositiveWithRange() {
    	expectedRange = new Range(0,2);
    	assertTrue("Given range 0,2 should intersect -1,1",exampleRange.intersects(expectedRange));
    }
    
    @Test
    public void testlargerRangeIntersectsWithRange() {
    	expectedRange = new Range(-2,2);
    	assertTrue("Given range -2,2 should intersect -1,1",exampleRange.intersects(expectedRange));
    }
    
    @Test
    public void testnotIntersectingNegativeWithRange() {
    	expectedRange = new Range(-3,-2);
    	assertFalse("Given range -3,-2 should not intersect -1,1",exampleRange.intersects(expectedRange));
    }
    
    @Test
    public void testnotintersectingPositiveWithRange() {
    	expectedRange = new Range(2,3);
    	assertFalse("Given range 2,3 should not intersect -1,1",exampleRange.intersects(expectedRange));
    }
    
    //test constrain
    @Test
    public void doesNotContainOnPositiveSide() {
    	assertTrue("should return 1 when asked to constrain 2 in range -1,1",(1 == exampleRange.constrain(2)));
    }
    
    @Test
    public void doesNotContainOnNegativeSide() {
    	assertTrue("should return -1 when asked to constrain -2 in range -1,1",(-1 == exampleRange.constrain(-2)));
    }
    
    @Test
    public void doesContainOnPositiveSide() {
    	assertTrue("should return 1 when asked to constrain 1 in range -1,1",(1 == exampleRange.constrain(1)));
    }
    
    @Test
    public void doesContainOnNegativeSide() {
    	assertTrue("should return -1 when asked to constrain -1 in range -1,1",(-1 == exampleRange.constrain(-1)));
    }
    
    //testing combine
    @Test
    public void testFirstRangeNullInCombine() {
    	assertEquals("the returned range should be -1,1",exampleRange,Range.combine(null, exampleRange));
    }
    
    @Test
    public void testSecondRangeNullInCombine() {
    	assertEquals("the returned range should be -1,1",exampleRange,Range.combine(exampleRange,null));
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}

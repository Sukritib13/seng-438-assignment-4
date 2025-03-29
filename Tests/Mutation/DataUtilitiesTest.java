package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.KeyedValues;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTest extends DataUtilities {
	
	private double[] validData;
    private double[] emptyData;
    private double[] singleElementData;
    private double[] zeroData;
    private double[] negativeValuesData;
    private double[] mixedValuesData;
	
	@Before
	public void setUp() throws Exception{
		
		validData = new double[]{1.5, 2.5, 3.5};
        emptyData = new double[]{};
        singleElementData = new double[]{7.0};
        zeroData = new double[]{0.0};
        negativeValuesData = new double[]{-1.5, -2.5, -3.5};
        mixedValuesData = new double[]{-2.0, 0.0, 3.5};
	}

	//Valid Case
	 @Test
	 public void calculateColumnTotalForMultipleRows() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
		 mockingContext.checking(new Expectations() {
				{
					one(values).getRowCount();
					will(returnValue(2));
					one(values).getValue(0, 0);
					will(returnValue(7.5));
		            one(values).getValue(1, 0);
		            will(returnValue(2.5));
				}	
			});
	     double result = DataUtilities.calculateColumnTotal(values, 0);
	     // verify
	     assertEquals(10.0, result, .000000001d);
	 }
	 //Valid Case
	 @Test
	    public void calculateColumnTotalForSingleRowSingleColumn() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(1));
	                one(values).getValue(0, 0);
	                will(returnValue(-5.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(-5.0, result, .000000001d);
	    }
	 //Valid Case
	 @Test
	    public void calculateColumnTotalForNegativeValues() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(-2.5));
	                one(values).getValue(1, 0);
	                will(returnValue(-3.5));
	                one(values).getValue(2, 0);
	                will(returnValue(4.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(-2.0, result, .000000001d);
	    }
	 //Edge case, data has null values
	 @Test
	    public void calculateColumnTotalForNullValue() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));
	                one(values).getValue(0, 0);
	                will(returnValue(null));
	                one(values).getValue(1, 0);
	                will(returnValue(2.0));
	                one(values).getValue(2, 0);
	                will(returnValue(3.0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(5.0, result, .000000001d);
	    }
	 //Boundary Case - Empty Table
	 @Test
	    public void calculateColumnTotalForEmptyTable() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals(0.0, result, .000000001d);
	    }
	 //Invalid Case when invalid data object is passed
//	 @Test
//	    public void calculateColumnTotalForNullData() {
//		 try {
//			 DataUtilities.calculateColumnTotal(null, 0);
//		 }catch(InvalidParameterException e) {
//			 
//		 }catch (Exception e) {
//			 fail("Excepted InvalidParameterException. Unexpected exception was thrown: ");
//		 }
//	    }
	 //Invalid Case when column index is out of bounds
	 @Test
	    public void calculateColumnTotalForColumnOutOfBounds() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        
        	double result = DataUtilities.calculateColumnTotal(values, 5);
 	        assertEquals(0.0, result, .000000001d);
	       
	    }
	 //Invalid Case when column index is negative
	 @Test
	    public void calculateColumnTotalForNegativeColumnIndex() {
		 	Mockery mockingContext = new Mockery();
		    final Values2D values = mockingContext.mock(Values2D.class);
	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(0));
	            }
	        });
	        double result = DataUtilities.calculateColumnTotal(values, -1);
	        assertEquals(0.0, result, .000000001d);
	    }
	 
	 //Row Tests
	// Valid Case
	 @Test
	 public void calculateRowTotalForMultipleColumns() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(10.0, result, .000000001d);
	 }

	 // Valid Case
	 @Test
	 public void calculateRowTotalForSingleRowSingleColumn() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(1));
	             one(values).getValue(0, 0);
	             will(returnValue(-5.0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(-5.0, result, .000000001d);
	 }

	 // Valid Case
	 @Test
	 public void calculateRowTotalForNegativeValues() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(3));
	             one(values).getValue(0, 0);
	             will(returnValue(-2.5));
	             one(values).getValue(0, 1);
	             will(returnValue(-3.5));
	             one(values).getValue(0, 2);
	             will(returnValue(4.0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(-2.0, result, .000000001d);
	 }

	 // Edge case, data has null values
	 @Test
	 public void calculateRowTotalForNullValue() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(3));
	             one(values).getValue(0, 0);
	             will(returnValue(null));
	             one(values).getValue(0, 1);
	             will(returnValue(2.0));
	             one(values).getValue(0, 2);
	             will(returnValue(3.0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(5.0, result, .000000001d);
	 }

	 // Boundary Case - Empty Table
	 @Test
	 public void calculateRowTotalForEmptyTable() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(0.0, result, .000000001d);
	 }

	 // Invalid Case when row index is out of bounds
	 @Test
	 public void calculateRowTotalForRowOutOfBounds() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, 5);
	     assertEquals(0.0, result, .000000001d);
	 }

	  //Invalid Case when row index is negative
	 @Test
	 public void calculateRowTotalForNegativeRowIndex() {
		 Mockery mockingContext = new Mockery();
		 final Values2D values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(0));
	         }
	     });

	     double result = DataUtilities.calculateRowTotal(values, -1);
	     assertEquals(0.0, result, .000000001d);
	 }
	 
	 //Array Tests
	// Valid Case: Converting a normal array of double values to Number[]
	    @Test
	    public void createNumberArrayWithValidData() {
	        Number[] result = DataUtilities.createNumberArray(validData);

	        assertNotNull(result[2]);
	        assertEquals(3, result.length);
	        assertEquals(1.5, result[0].doubleValue(), .000000001d);
	        assertEquals(2.5, result[1].doubleValue(), .000000001d);
	        assertEquals(3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing empty array
	    @Test
	    public void createNumberArrayWithEmptyArray() {
	        Number[] result = DataUtilities.createNumberArray(emptyData);

	        assertNotNull(result);
	        assertEquals(0, result.length);
	    }

	    // Edge Case: testing single Element Array
	    @Test
	    public void createNumberArrayWithSingleElement() {
	        Number[] result = DataUtilities.createNumberArray(singleElementData);

	        assertNotNull(result[0]);
	        assertEquals(1, result.length);
	        assertEquals(7.0, result[0].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing array containing zero
	    @Test
	    public void createNumberArrayWithZeroValue() {
	        Number[] result = DataUtilities.createNumberArray(zeroData);

	        assertNotNull(result[0]);
	        assertEquals(1, result.length);
	        assertEquals(0.0, result[0].doubleValue(), .000000001d);
	    }

	    // Edge Case: Testing array containing only negative numbers
	    @Test
	    public void createNumberArrayWithNegativeValues() {
	        Number[] result = DataUtilities.createNumberArray(negativeValuesData);

	        assertNotNull(result[2]);
	        assertEquals(3, result.length);
	        assertEquals(-1.5, result[0].doubleValue(), .000000001d);
	        assertEquals(-2.5, result[1].doubleValue(), .000000001d);
	        assertEquals(-3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Edge Case: testing with both positive and negative values
	    @Test
	    public void createNumberArrayWithMixedValues() {
	        Number[] result = DataUtilities.createNumberArray(mixedValuesData);

	        assertNotNull(result[2]);
	        assertEquals(3, result.length);
	        assertEquals(-2.0, result[0].doubleValue(), .000000001d);
	        assertEquals(0.0, result[1].doubleValue(), .000000001d);
	        assertEquals(3.5, result[2].doubleValue(), .000000001d);
	    }

	    // Invalid Case: Null Input
	    @Test(expected = IllegalArgumentException.class)
	    public void createNumberArrayWithNullInput() {
	        DataUtilities.createNumberArray(null);
	    }
	    
	    @After
	    public void tearDown() {
	        // Reset data after each test
	        validData = null;
	        emptyData = null;
	        singleElementData = null;
	        zeroData = null;
	        negativeValuesData = null;
	        mixedValuesData = null;
	    }
	    
	     //Tests whether a valid 2D array of doubles is correctly converted to a 2D array of Numbers.
	   
	    @Test
	    public void testCreateNumberArray2D_ValidInput() {
	        double[][] input = {{1.1, 2.2}, {3.3, 4.4}};
	        Number[][] expected = {{1.1, 2.2}, {3.3, 4.4}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Valid input should be converted correctly", expected, result);
	    }

	    /**
	     * Tests whether an empty 2D double array correctly returns an empty 2D Number array.
	     */
	    @Test
	    public void testCreateNumberArray2D_EmptyArray() {
	        double[][] input = {};
	        Number[][] expected = {};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Empty array should return an empty Number array", expected, result);
	    }

	    /**
	     * Tests whether passing a null input throws an InvalidParameterException.
	     */
	    @Test
	    public void testCreateNumberArray2D_NullInput() {
	        try {
	            double[][] data = null;
	            DataUtilities.createNumberArray2D(data);
	            fail("Expected IllegalArgumentException but no exception was thrown");
	        } catch (IllegalArgumentException e) {
	            // Expected exception, test passes
	        } catch (Exception e) {
	            fail("Expected IllegalArgumentException. Unexpected exception was thrown: " + e.getMessage());
	        }
	    }

	    /**
	     * Tests conversion when the 2D array contains a single element.
	     */
	    @Test
	    public void testCreateNumberArray2D_SingleElement() {
	        double[][] input = {{5.5}};
	        Number[][] expected = {{5.5}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Single element array should be correctly converted", expected, result);
	    }

	    /**
	     * Tests conversion when the 2D array contains negative values.
	     */
	    @Test
	    public void testCreateNumberArray2D_NegativeValues() {
	        double[][] input = {{-1.1, -2.2}, {-3.3, -4.4}};
	        Number[][] expected = {{-1.1, -2.2}, {-3.3, -4.4}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Negative values should be correctly converted", expected, result);
	    }

	    /**
	     * Tests conversion when the 2D array contains only zeros.
	     */
	    @Test
	    public void testCreateNumberArray2D_ZeroValues() {
	        double[][] input = {{0.0, 0.0}, {0.0, 0.0}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        
	        // Instead of assertArrayEquals, check each element
	        assertEquals("Result should have 2 rows", 2, result.length);
	        assertEquals("First row should have 2 columns", 2, result[0].length);
	        assertEquals("Second row should have 2 columns", 2, result[1].length);
	        assertEquals("Element [0][0] should be 0.0", 0.0, result[0][0].doubleValue(), 0.000000001d);
	        assertEquals("Element [0][1] should be 0.0", 0.0, result[0][1].doubleValue(), 0.000000001d);
	        assertEquals("Element [1][0] should be 0.0", 0.0, result[1][0].doubleValue(), 0.000000001d);
	        assertEquals("Element [1][1] should be 0.0", 0.0, result[1][1].doubleValue(), 0.000000001d);
	    }

	    /**
	     * Tests conversion when the 2D array contains large numerical values.
	     */
	    @Test
	    public void testCreateNumberArray2D_LargeValues() {
	        double[][] input = {{1e10, 2e10}, {3e10, 4e10}};
	        Number[][] expected = {{1e10, 2e10}, {3e10, 4e10}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Large values should be correctly converted", expected, result);
	    }
	    
	    /**
	     * Tests conversion when the 2D array contains mixed numerical values.
	     */
	    @Test
	    public void testCreateNumberArray2D_MixedValues() {
	        double[][] input = {{-1.1, 0.0, 2.2}, {3.3, -4.4, 5.5}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        
	        // Check each element individually
	        assertEquals("Result should have 2 rows", 2, result.length);
	        assertEquals("First row should have 3 columns", 3, result[0].length);
	        assertEquals("Second row should have 3 columns", 3, result[1].length);
	        assertEquals("Element [0][0]", -1.1, result[0][0].doubleValue(), 0.000000001d);
	        assertEquals("Element [0][1]", 0.0, result[0][1].doubleValue(), 0.000000001d);
	        assertEquals("Element [0][2]", 2.2, result[0][2].doubleValue(), 0.000000001d);
	        assertEquals("Element [1][0]", 3.3, result[1][0].doubleValue(), 0.000000001d);
	        assertEquals("Element [1][1]", -4.4, result[1][1].doubleValue(), 0.000000001d);
	        assertEquals("Element [1][2]", 5.5, result[1][2].doubleValue(), 0.000000001d);
	    }

	    /**
	     * Tests conversion when the 2D array has non-uniform sub-arrays.
	     */
	    @Test
	    public void testCreateNumberArray2D_NonUniformSubarrays() {
	        double[][] input = {{1.1}, {2.2, 3.3}};
	        Number[][] expected = {{1.1}, {2.2, 3.3}};
	        Number[][] result = DataUtilities.createNumberArray2D(input);
	        assertArrayEquals("Non-uniform subarrays should be correctly converted", expected, result);
	    }
	    
	    // Test Methods for getCumulativePercentages() method
	    
	    /**
	     * Tests cumulative percentage calculation for an empty dataset.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithEmptyDataset() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);
	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(0));
	        }});
	        KeyedValues result = DataUtilities.getCumulativePercentages(data);
	        assertEquals("Empty dataset should return no values", 0, result.getItemCount());
	    }
	    
	    /**
	     * Tests cumulative percentage calculation when dataset contains fractional values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithFractionalValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(0.2));
	            allowing(data).getValue(1); will(returnValue(0.3));
	            allowing(data).getValue(2); will(returnValue(0.5));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.2", 0.2, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.5", 0.5, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains alternating positive and negative values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithAlternatingPositiveNegativeValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(-10));
	            allowing(data).getValue(1); will(returnValue(20));
	            allowing(data).getValue(2); will(returnValue(-30));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);
	        // double total = -10 + 20 + -30; // -20.0

	        assertEquals("First key should be -10/-20", 0.5, result.getValue(0).doubleValue(), 0.0001);
	        assertEquals("Second key should be (-10+20)/-20", -0.5, result.getValue(1).doubleValue(), 0.0001);
	        assertEquals("Third key should be (-10+20-30)/-20", 1.0, result.getValue(2).doubleValue(), 0.0001);
	    }
	    
	    /**
	     * Tests cumulative percentage calculation when dataset contains small values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithSmallValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3)); 
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(0.0001));
	            allowing(data).getValue(1); will(returnValue(0.0002));
	            allowing(data).getValue(2); will(returnValue(0.0003));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.1667", 0.16666666666666666, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.5", 0.5, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains zero values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithZeroValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(0));
	            allowing(data).getValue(1); will(returnValue(0));
	            allowing(data).getValue(2); will(returnValue(0));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        // When total is 0, implementation returns 0.0 for all percentages
	        assertTrue("First key should be NaN", Double.isNaN(result.getValue(0).doubleValue()));
	        assertTrue("Second key should be NaN", Double.isNaN(result.getValue(1).doubleValue()));
	        assertTrue("Third key should be NaN", Double.isNaN(result.getValue(2).doubleValue()));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains negative values.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithNegativeValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(-5));
	            allowing(data).getValue(1); will(returnValue(10));
	            allowing(data).getValue(2); will(returnValue(15));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);
	        // double total = -5 + 10 + 15; // 20.0

	        assertEquals("First key should be -5/20", -0.25, result.getValue(0).doubleValue(), 0.0001);
	        assertEquals("Second key should be (-5+10)/20", 0.25, result.getValue(1).doubleValue(), 0.0001);
	        assertEquals("Third key should be (-5+10+15)/20", 1.0, result.getValue(2).doubleValue(), 0.0001);
	    }
	    /**
	     * Tests cumulative percentage calculation when dataset contains a single value.
	     */
	    @Test
	    public void testGetCumulativePercentagesWithSingleValue() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(1));
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(5));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the single key should be 1.0", 1.0, result.getValue(0));
	    }

	    /**
	     * Tests cumulative percentage calculation when dataset contains large values.
	     */
	    public void testGetCumulativePercentagesWithLargeValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(with(any(Integer.class)));
	            allowing(data).getValue(0); will(returnValue(1000000)); 
	            allowing(data).getValue(1); will(returnValue(2000000)); 
	            allowing(data).getValue(2); will(returnValue(3000000)); 
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.1667", 0.16666666666666666, result.getValue(0));
	        assertEquals("The cumulative percentage for the second key should be 0.5", 0.5, result.getValue(1));
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2));
	    }

// Tests to improve Coverage:

	    @Test
	    public void calculateColumnTotalWithValidRows() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getRowCount();
	                will(returnValue(3));

	                one(values).getValue(0, 0);
	                will(returnValue(1.0));

	                one(values).getValue(1, 0);
	                will(returnValue(2.0));

	                one(values).getValue(2, 0);
	                will(returnValue(3.0));
	            }
	        });

	        int[] validRows = {0, 1, 2};
	        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);

	        assertEquals("Total should be 6.0", 6.0, result, .000000001d);
	    }
	    
	    @Test
	    public void calculateRowTotalWithValidAndInvalidColumns() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(3));

	                one(values).getValue(0, 0);
	                will(returnValue(1.5));

	                one(values).getValue(0, 1);
	                will(returnValue(null));

	                one(values).getValue(0, 2);
	                will(returnValue(2.5));
	            }
	        });

	        int[] validCols = {0, 1, 2};
	        double result = DataUtilities.calculateRowTotal(values, 0, validCols);

	        assertEquals("Total should be 4.0", 4.0, result, .000000001d);
	    }

	    @Test
	    public void calculateRowTotalFullBranchCoverage() {
	        Mockery mock = new Mockery();
	        final Values2D values = mock.mock(Values2D.class);

	        mock.checking(new Expectations() {
	            {
	                one(values).getColumnCount();
	                will(returnValue(-1));

	                one(values).getColumnCount();
	                will(returnValue(3));

	                one(values).getValue(0, 0);
	                will(returnValue(1.5));

	                one(values).getValue(0, 1);
	                will(returnValue(null));

	                one(values).getValue(0, 2);
	                will(returnValue(2.5));
	            }
	        });

	        int[] validCols = {0, 1, 2};
	        double result1 = DataUtilities.calculateRowTotal(values, 0, validCols);
	        assertEquals("Total should be 0 when colCount < 0", 0.0, result1, .000000001d);
	    }
	    
	    @Test
	    public void testGetCumulativePercentagesWithNullValues() {
	        Mockery mockingContext = new Mockery();
	        final KeyedValues data = mockingContext.mock(KeyedValues.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(data).getItemCount(); will(returnValue(3));
	            allowing(data).getKey(0); will(returnValue(0));
	            allowing(data).getKey(1); will(returnValue(1));
	            allowing(data).getKey(2); will(returnValue(2));
	            allowing(data).getValue(0); will(returnValue(10));
	            allowing(data).getValue(1); will(returnValue(null)); // Null value
	            allowing(data).getValue(2); will(returnValue(20));
	        }});

	        KeyedValues result = DataUtilities.getCumulativePercentages(data);

	        assertEquals("The cumulative percentage for the first key should be 0.3333", 0.3333, result.getValue(0).doubleValue(), 0.0001);
	        assertEquals("The cumulative percentage for the third key should be 1.0", 1.0, result.getValue(2).doubleValue(), 0.0001);
	    }
	    
	    //Test cases for equal()
	    @Test
	    public void testEqualWithBothNull() {
	        assertTrue("Both arrays are null, should return true", DataUtilities.equal(null, null));
	    }

	    @Test
	    public void testEqualWithFirstArrayNull() {
	        double[][] b = {{1.0, 2.0}, {3.0, 4.0}};
	        assertFalse("First array is null, should return false", DataUtilities.equal(null, b));
	    }

	    @Test
	    public void testEqualWithSecondArrayNull() {
	        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
	        assertFalse("Second array is null, should return false", DataUtilities.equal(a, null));
	    }

	    @Test
	    public void testEqualWithDifferentLengths() {
	        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] b = {{1.0, 2.0}};
	        assertFalse("Arrays have different lengths, should return false", DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testEqualWithDifferentValues() {
	        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] b = {{1.0, 2.0}, {3.0, 5.0}}; // Last value differs
	        assertFalse("Arrays have different values, should return false", DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testEqualWithSameValues() {
	        double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] b = {{1.0, 2.0}, {3.0, 4.0}};
	        assertTrue("Arrays have the same values, should return true", DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testEqualWithBothHavingNullRows() {
	        double[][] a = {null, {1.0, 2.0}};
	        double[][] b = {null, {1.0, 2.0}};
	        assertTrue("Arrays have null rows in the same positions, should return true", DataUtilities.equal(a, b));
	    }

	    @Test
	    public void testEqualWithOneHavingNullRow() {
	        double[][] a = {null, {1.0, 2.0}};
	        double[][] b = {{1.0, 2.0}, {1.0, 2.0}};
	        assertFalse("One array has a null row while the other does not, should return false", DataUtilities.equal(a, b));
	    }
	    
	    //Test Cases for clone()
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testCloneWithNullSource() {
	        DataUtilities.clone(null);
	    }

	    @Test
	    public void testCloneWithEmptyArray() {
	        double[][] source = {};
	        double[][] cloned = DataUtilities.clone(source);
	        assertNotNull("Cloned array should not be null", cloned);
	        assertEquals("Cloned array should be empty", 0, cloned.length);
	    }

	    @Test
	    public void testCloneWithValidValues() {
	        double[][] source = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] cloned = DataUtilities.clone(source);

	        assertNotNull("Cloned array should not be null", cloned);
	        assertArrayEquals("Cloned array should match the original", source, cloned);
	    }

	    @Test
	    public void testCloneWithNullRows() {
	        double[][] source = {null, {1.0, 2.0}};
	        double[][] cloned = DataUtilities.clone(source);

	        assertNotNull("Cloned array should not be null", cloned);
	        assertNull("First row in cloned array should be null", cloned[0]);
	        assertArrayEquals("Second row should be copied correctly", source[1], cloned[1], 0.0001);
	    }



//Tests to improve Mutation Testing Score

		@Test
		public void testEqualWithNegativeValues() {
		    double[][] a = {{1.0, -2.0}, {3.0, 4.0}};
		    double[][] b = {{1.0, -2.0}, {3.0, -4.0}};
		    assertFalse("Arrays contain same values but with differing signs, should return false", DataUtilities.equal(a, b));
		}
		
		@Test
		public void testEqualWithOneArrayHavingSmallerValues() {
		    double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
		    double[][] b = {{1.0, 2.0}, {2.9, 4.0}};
		    assertFalse("One array has smaller values, should return false", DataUtilities.equal(a, b));
		}
		
		@Test
		public void testEqualWithOneArrayHavingGreaterValues() {
		    double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
		    double[][] b = {{1.0, 2.1}, {3.0, 4.0}};
		    assertFalse("One array has larger values, should return false", DataUtilities.equal(a, b));
		}
		
		@Test
		public void testEqualWithStrictlyGreaterValues() {
		    double[][] a = {{1.0, 2.0}, {3.0, 4.0}};
		    double[][] b = {{1.0, 3.0}, {3.0, 5.0}};
		    assertFalse("One array has strictly greater values, should return false", DataUtilities.equal(a, b));
		}

	    @Test
	    public void testEqual_NullInputs() {
	        assertTrue("Both arrays null should return true", DataUtilities.equal(null, null));
	        assertFalse("First array null, second not should return false", DataUtilities.equal(null, new double[][]{{1.0, 2.0}}));
	        assertFalse("Second array null, first not should return false", DataUtilities.equal(new double[][]{{1.0, 2.0}}, null));
	    }
	    
	    @Test
	    public void testEqual_EmptyArrays() {
	        double[][] empty1 = new double[0][0];
	        double[][] empty2 = new double[0][0];

	        assertTrue("Two empty arrays should be equal", DataUtilities.equal(empty1, empty2));
	    }
	    
	    @Test
	    public void testEqual_SameLengthButDifferentValues() {
	        double[][] array1 = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] array2 = {{1.0, 2.0}, {3.0, 5.0}};

	        assertFalse("Arrays with same length but different values should return false", DataUtilities.equal(array1, array2));
	    }
	    
	    @Test
	    public void testEqual_DifferentLengths() {
	        double[][] array1 = {{1.0, 2.0}, {3.0, 4.0}};
	        double[][] array2 = {{1.0, 2.0}};
	        assertFalse("Arrays with different lengths should return false", DataUtilities.equal(array1, array2));
	    }
	    
	    @Test
	    public void testEqual_EmptyVsNonEmpty() {
	        double[][] emptyArray = new double[0][0];
	        double[][] nonEmptyArray = {{1.0, 2.0}};

	        assertFalse("Empty and non-empty arrays should return false", DataUtilities.equal(emptyArray, nonEmptyArray));
	    }

	    
	    @Test
	    public void testEqual_SingleRowMismatch() {
	        double[][] array1 = {{1.0, 2.0}};
	        double[][] array2 = {{1.0, 3.0}};

	        assertFalse("Arrays with single row and one different value should return false", DataUtilities.equal(array1, array2));
	    }
		
	    @Test
	    public void testClone_SingleRowArray() {
	        double[][] source = {{1.0, 2.0, 3.0}};
	        
	        double[][] result = DataUtilities.clone(source);

	        assertNotNull("Cloned array should not be null", result);
	        assertEquals("Cloned array should have the same number of rows", source.length, result.length);
	        assertArrayEquals("Cloned row should be identical to the original", source[0], result[0], 0.0000001);
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void testCalculateColumnTotal_NullData_ShouldThrowException() {
	        DataUtilities.calculateColumnTotal(null, 0);
	    }
	    
	    @Test
	    public void testCalculateColumnTotal_SingleRow() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(1));

	            one(values).getValue(0, 0);
	            will(returnValue(2.5));
	        }});

	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals("Total should be 2.5", 2.5, result, .000000001d);
	    }

	    @Test
	    public void testCalculateColumnTotal_EmptyData() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(0));
	        }});

	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals("Total should be 0.0 for empty data", 0.0, result, .000000001d);
	    }
	    
	    @Test
	    public void testCalculateColumnTotal_CorrectSummation() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));

	            one(values).getValue(0, 0);
	            will(returnValue(1.5));

	            one(values).getValue(1, 0);
	            will(returnValue(2.5));

	            one(values).getValue(2, 0);
	            will(returnValue(3.0));
	        }});

	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals("Total should be 7.0", 7.0, result, .000000001d);
	    }

	    @Test
	    public void calculateColumnTotalWithNegativeNumbers() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));

	            one(values).getValue(0, 0);
	            will(returnValue(-1.0));

	            one(values).getValue(1, 0);
	            will(returnValue(2.5));

	            one(values).getValue(2, 0);
	            will(returnValue(-3.5));
	        }});

	        double result = DataUtilities.calculateColumnTotal(values, 0);
	        assertEquals("Total should be -2.0", -2.0, result, 0.000000001d);
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void calculateColumnTotal_NullData_ThrowsException() {
	        DataUtilities.calculateColumnTotal(null, 0, new int[]{0, 1, 2});
	    }
	    
	    @Test
	    public void calculateColumnTotal_ValidRowsOutOfBounds_IgnoresInvalidRows() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));

	            one(values).getValue(0, 0);
	            will(returnValue(1.0));

	            one(values).getValue(2, 0);
	            will(returnValue(3.0));
	        }});

	        int[] validRows = {0, 2, 3};
	        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
	        assertEquals("Total should be 4.0 (ignoring row 3)", 4.0, result, 0.000000001d);
	    }
	    
	    @Test
	    public void calculateColumnTotal_EmptyValidRows_ReturnsZero() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));
	        }});

	        double result = DataUtilities.calculateColumnTotal(values, 0, new int[]{});
	        assertEquals("Total should be 0.0 when validRows is empty", 0.0, result, 0.000000001d);
	    }

	    
	    @Test
	    public void calculateColumnTotal_ValidRowsContainsLastIndex() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3)); // 0, 1, 2 are valid

	            one(values).getValue(2, 0);
	            will(returnValue(3.0));
	        }});

	        int[] validRows = {2}; // Last row in the dataset
	        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
	        assertEquals("Total should be 3.0 (including last row)", 3.0, result, 0.000000001d);
	    }

	    @Test
	    public void calculateColumnTotal_ValidRowsContainsOutOfBoundsIndex() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            one(values).getRowCount();
	            will(returnValue(3));

	            one(values).getValue(1, 0);
	            will(returnValue(2.0));
	        }});

	        int[] validRows = {1, 3};
	        double result = DataUtilities.calculateColumnTotal(values, 0, validRows);
	        assertEquals("Total should be 2.0 (ignoring row 3)", 2.0, result, 0.000000001d);
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void calculateRowTotal_NullData_ThrowsException() {
	        DataUtilities.calculateRowTotal(null, 0, new int[]{0, 1, 2});
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void calculateRowTotal_WithNullData_ShouldThrowException() {
	        Values2D values = null;
	        DataUtilities.calculateRowTotal(values, 0);
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void createNumberArray2D_WithNullData_ShouldThrowException() {
	        double[][] data = null;
	        DataUtilities.createNumberArray2D(data);
	    }
	    
	    @Test(expected = IllegalArgumentException.class)
	    public void getCumulativePercentages_WithNullData_ShouldThrowException() {
	        KeyedValues data = null;
	        DataUtilities.getCumulativePercentages(data);
	    }

	    @Test
	    public void calculateRowTotal_ValidColsExceedColumnCount_ShouldIgnoreExtraCols() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount();
	            will(returnValue(2));
	            
	            allowing(values).getValue(0, 0);
	            will(returnValue(5.0));
	            
	            allowing(values).getValue(0, 1);
	            will(returnValue(10.0));
	        }});

	        int[] validCols = {0, 1, 2};
	        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	        assertEquals("Total should ignore column 2", 15.0, result, 0.000000001d);
	    }
	    
	    @Test
	    public void calculateRowTotal_ContainsNullValues_ShouldSkipNulls() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount();
	            will(returnValue(2));
	            
	            allowing(values).getValue(0, 0);
	            will(returnValue(4.0));
	            
	            allowing(values).getValue(0, 1);
	            will(returnValue(null));
	        }});

	        int[] validCols = {0, 1};
	        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	        assertEquals("Total should be 4.0, ignoring null", 4.0, result, 0.000000001d);
	    }

	    @Test
	    public void calculateRowTotal_ValidColsBoundaryCheck_ShouldSumCorrectly() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount();
	            will(returnValue(3));
	            
	            allowing(values).getValue(0, 0);
	            will(returnValue(2.0));
	            
	            allowing(values).getValue(0, 1);
	            will(returnValue(3.0));
	            
	            allowing(values).getValue(0, 2);
	            will(returnValue(5.0));
	        }});

	        int[] validCols = {0, 1, 2};
	        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	        assertEquals("Total should be 10.0", 10.0, result, 0.000000001d);
	    }

	    @Test
	    public void calculateRowTotal_SingleColumn_ShouldReturnValue() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount();
	            will(returnValue(3));
	            
	            allowing(values).getValue(1, 2);
	            will(returnValue(7.0));
	        }});

	        int[] validCols = {2};
	        double result = DataUtilities.calculateRowTotal(values, 1, validCols);
	        assertEquals("Total should be 7.0 for single valid column", 7.0, result, 0.000000001d);
	    }

	    @Test
	    public void calculateRowTotal_ColumnCountEdgeCase_ShouldIgnoreExtraColumns() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount();
	            will(returnValue(2));
	            
	            allowing(values).getValue(0, 0);
	            will(returnValue(4.0));
	            
	            allowing(values).getValue(0, 1);
	            will(returnValue(6.0));
	        }});

	        int[] validCols = {0, 1, 2};
	        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	        assertEquals("Total should be 10.0, ignoring out-of-bound column", 10.0, result, 0.000000001d);
	    }

	    @Test
	    public void calculateRowTotal_ColumnValuesModified_ShouldBeUnaffected() {
	        Mockery mockingContext = new Mockery();
	        final Values2D values = mockingContext.mock(Values2D.class);

	        mockingContext.checking(new Expectations() {{
	            allowing(values).getColumnCount();
	            will(returnValue(3));
	            
	            allowing(values).getValue(0, 0);
	            will(returnValue(1.0));
	            
	            allowing(values).getValue(0, 1);
	            will(returnValue(2.0));
	            
	            allowing(values).getValue(0, 2);
	            will(returnValue(3.0));
	        }});

	        int[] validCols = {0, 1, 2};
	        double result = DataUtilities.calculateRowTotal(values, 0, validCols);
	        assertEquals("Total should be 6.0 with correct column values", 6.0, result, 0.000000001d);
	    }

}

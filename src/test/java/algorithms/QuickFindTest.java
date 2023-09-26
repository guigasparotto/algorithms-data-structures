package algorithms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Auto-generated with Machinet - needs review
public class QuickFindTest {
    private QuickFind quickFind;

    @BeforeEach
    public void setUp() {
        quickFind = new QuickFind(10);
    }

    // Existing tests...

    @Test
    public void testFindWhenIndexIsValidThenReturnSetId() {
        // Arrange
        quickFind.union(2, 5);
        quickFind.union(5, 7);

        // Act
        int setId = quickFind.find(2);

        // Assert
        assertEquals(quickFind.find(7), setId, "Find method should return the id of the set that the element belongs to");
    }

    @Test
    public void testFindWhenIndexIsInvalidThenThrowIllegalArgumentException() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> quickFind.find(15), "An IllegalArgumentException should be thrown when an invalid index is used");
    }

    @Test
    public void testGetCountAfterInitialization() {
        // Arrange
        // Act
        int count = quickFind.getCount();

        // Assert
        assertEquals(10, count, "Count should be equal to initial size when no union operations have been performed");
    }

    @Test
    public void testGetCountAfterUnion() {
        // Arrange
        quickFind.union(2, 5);

        // Act
        int count = quickFind.getCount();

        // Assert
        assertEquals(9, count, "Count should be equal to initial size minus number of union operations");
    }

    @Test
    public void testUnionWhenParametersNotConnectedThenIdsUpdatedAndCountDecreased() {
        // Arrange
        int initialCount = quickFind.getCount();

        // Act
        quickFind.union(2, 5);

        // Assert
        assertTrue(quickFind.connected(2, 5), "Ids should be updated to be the same");
        assertEquals(initialCount - 1, quickFind.getCount(), "Count should be decreased by 1");
    }

    @Test
    public void testUnionWhenParametersAlreadyConnectedThenIdsAndCountNotChanged() {
        // Arrange
        quickFind.union(2, 5);
        int initialCount = quickFind.getCount();
        int initialId2 = quickFind.find(2);
        int initialId5 = quickFind.find(5);

        // Act
        quickFind.union(2, 5);

        // Assert
        assertEquals(initialId2, quickFind.find(2), "Id of 2 should not be changed");
        assertEquals(initialId5, quickFind.find(5), "Id of 5 should not be changed");
        assertEquals(initialCount, quickFind.getCount(), "Count should not be changed");
    }

    @Test
    public void testUnionWhenParameterNotValidThenIllegalArgumentExceptionThrown() {
        // Arrange & Act & Assert
        assertThrows(IllegalArgumentException.class, () -> quickFind.union(2, 15), "An IllegalArgumentException should be thrown when an invalid point is used");
    }

    @Test
    public void testGetCountAfterUnionOperations() {
        quickFind.union(2, 5);
        quickFind.union(5, 7);
        quickFind.union(1, 3);
        int count = quickFind.getCount();
        assertEquals(7, count, "Count should be equal to initial size minus number of union operations");
    }

    @Test
    public void testGetCountWithoutUnionOperations() {
        int count = quickFind.getCount();
        assertEquals(10, count, "Count should be equal to initial size when no union operations have been performed");
    }

    @Test
    public void testConnectedWhenPointsAreConnectedThenReturnsTrue() {
        quickFind.union(2, 5);
        assertTrue(quickFind.connected(2, 5), "Two connected points should be identified as connected");
    }

    @Test
    public void testConnectedWhenPointsAreNotConnectedThenReturnsFalse() {
        assertFalse(quickFind.connected(2, 5), "Two unconnected points should be identified as not connected");
    }

    @Test
    public void testConnectedWhenPointIsInvalidThenThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> quickFind.connected(2, 15), "An IllegalArgumentException should be thrown when an invalid point is used");
    }
}
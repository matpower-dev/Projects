import unittest
import pizzapackage.src.pizza as pizza

class TestTupleCreation(unittest.TestCase):

    def test_sum(self):
        self.assertEqual(sum([1, 2, 3]), 6, "Should be 6")

    def test_tuplecreation(self):
        userarg = "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
        (gridSize, directionsList) = pizza.extractDirectionsAsTuples(userarg)
        self.assertEqual(len(directionsList), 9)
        self.assertEqual(len(gridSize), 2)

    def test_extractNavStringFromDriverInstructions(self):
        userarg = [(0,0), (2,3), (2,0)]
        navString = pizza.extractNavStringFromDriverInstructions(userarg)
        self.assertEqual(type(navString), str)

    def test_extractDirectionList(self):
        userarg = ["2,3", "6,3"]
        inttuple = pizza.extractDirectionList(userarg)
        self.assertEqual(inttuple, [(2,3), (6,3)])

if __name__ == '__main__':
    unittest.main()

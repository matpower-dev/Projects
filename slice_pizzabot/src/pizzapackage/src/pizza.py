#python pizza.py "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
import sys
from functools import partial
import re
import unittest
print("Python version")
print (sys.version)

#Output looks like "WEEWEWWW", or "NSSSNNS"
def generateDirectionString(magnitude, forwardBackwardCharacters):
      if magnitude<0:
             returnDirectionString = abs(magnitude) * forwardBackwardCharacters['backwards']
      else:
             returnDirectionString = abs(magnitude) * forwardBackwardCharacters['forwards']
      return returnDirectionString

eastWestDirectionString  = partial(generateDirectionString, forwardBackwardCharacters={'forwards': 'E', 'backwards': 'W'})

northSouthDirectionString  = partial(generateDirectionString, forwardBackwardCharacters={'forwards': 'N', 'backwards': 'S'})

#convert character pairs to int pairs, eg (1,2), (6,2)
def tupleToInt(splittuple):
     return tuple([int(i) for i in splittuple])

#(6-2) - (5-2) = (1, 0)
def subtractVectors(vec1,vec2):
     return tuple([i - j for i, j in zip(vec1, vec2)])

#operates on a list where entries look like "1,2". Outputs a list of int pairs like (1,2)
def extractDirectionList(directionsList):
    directionsList = [i.split(",") for i in directionsList]
    directionsListTuple = [tupleToInt(i) for i in directionsList]
    return directionsListTuple

#extract int pair from eg 5x5
def extractGridSize(gridString):
    gridVector = re.split("x",gridString)
    gridVector = tupleToInt(gridVector)
    return gridVector

#operates on the raw string given by the user. High level function. Outputs a list of int pairs like (1,2)
def extractDirectionsAsTuples(instructionString):
    splitstring = re.split("[\((.))]", instructionString)
    gridString = splitstring[0]
    gridSize = extractGridSize(gridString)
    directionsList = splitstring[1::2]
    directionsList = extractDirectionList(directionsList)
    return (gridSize, directionsList)

#operates on a list of int pairs. outputs relative coordinates for driver at each step.
def extractDriverInstructions(directionsList):
   driverInstructionsList =  [None] * len(directionsList )

   for i,iter_tuple in enumerate(directionsList):
         if i==0:
                driverInstructions = subtractVectors(directionsList[i], (0,0))
         else:
                driverInstructions = subtractVectors(directionsList[i], directionsList[i-1])
         driverInstructionsList[i] = driverInstructions
   return driverInstructionsList

#extract string like "DEENNNDEE" from driver instructions [(0,0), (2,3), (2,0)]
def extractNavStringFromDriverInstructions(driverInstructionsList):
    totalDirectionStringChunks = [(eastWestDirectionString(i),northSouthDirectionString(j)) for i, j in driverInstructionsList]
    totalDirectionString = "D".join(["".join([i,j]) for i,j in totalDirectionStringChunks])+ "D"
    return totalDirectionString

#Called if run as a standalone module, or can be called from the pizza module itself.
def run():
    tosolve = sys.argv[1]
    tosolve = tosolve.replace(" ","")
    #Find all tuples, eg "(1,2)(5,2)".
    testuserinput_tuples = re.findall(r'(\([0-9]+,[0-9]+\))', tosolve)
    #Find grid size, eg "5x5".
    testuserinput_grid = re.findall(r'^([0-9]+x[0-9]+)\(', tosolve)
    assertpatternmatching = len(testuserinput_grid[0]) + len(''.join(testuserinput_tuples))
    try:
        assert assertpatternmatching == len(tosolve)
    except AssertionError:
        sys.exit("Bad input to script, unexpected input format. A valid input should contain a grid spacing, followed by numbered tuples: 5x5 (0, 0) (1, 3)")
    (gridSize, directionsList) = extractDirectionsAsTuples(tosolve)
    driverInstructionsList = extractDriverInstructions(directionsList)
    navString = extractNavStringFromDriverInstructions(driverInstructionsList)
    print(navString)


if __name__ == "__main__":
    run()

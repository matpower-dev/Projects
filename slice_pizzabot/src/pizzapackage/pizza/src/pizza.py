
#Uses the system version of python, but feel free to change to what you want.
#!/usr/bin/python
import sys
from functools import partial
import re
import unittest
print("Python version")
print (sys.version)

def generateDirectionString(magnitude, forwardBackwardCharacters):
      if magnitude<0:
             returnDirectionString = abs(magnitude) * forwardBackwardCharacters['backwards']
      else:
             returnDirectionString = abs(magnitude) * forwardBackwardCharacters['forwards']
      return returnDirectionString

eastWestDirectionString  = partial(generateDirectionString, forwardBackwardCharacters={'forwards': 'E', 'backwards': 'W'})

northSouthDirectionString  = partial(generateDirectionString, forwardBackwardCharacters={'forwards': 'N', 'backwards': 'S'})

def tupleToInt(splittuple):
     return tuple([int(i) for i in splittuple])


def subtractVectors(vec1,vec2):
     return tuple([i - j for i, j in zip(vec1, vec2)])

def extractDirectionList(directionsList):
    directionsList = [i.split(",") for i in directionsList]
    directionsListTuple = [tupleToInt(i) for i in directionsList]
    return directionsListTuple

def extractGridSize(gridString):
    gridVector = re.split("x",gridString)
    gridVector = tupleToInt(gridVector)
    return gridVector

def extractInstructionsAsTuples(instructionString):
    splitstring = re.split("[\((.))]", instructionString)
    gridString = splitstring[0]
    gridSize = extractGridSize(gridString)
    directionsList = splitstring[1::2]
    directionsList = extractDirectionList(directionsList)
    return (gridSize, directionsList)

def extractDriverInstructions(directionsList):
   driverInstructionsList =  [None] * len(directionsList )

   for i,iter_tuple in enumerate(directionsList):
         if i==0:
                driverInstructions = subtractVectors(directionsList[i], (0,0))
         else:
                driverInstructions = subtractVectors(directionsList[i], directionsList[i-1])
         driverInstructionsList[i] = driverInstructions
   return driverInstructionsList

def extractNavStringFromDriverInstructions(driverInstructionsList):
    totalDirectionStringChunks = [(eastWestDirectionString(i),northSouthDirectionString(j)) for i, j in driverInstructionsList]
    totalDirectionString = "D".join(["".join([i,j]) for i,j in totalDirectionStringChunks])+ "D"
    return totalDirectionString

def run():
    #tosolve = "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)".replace(" ","")
    tosolve = sys.argv[1]
    tosolve = tosolve.replace(" ","")
    testuserinput_tuples = re.findall(r'(\([0-9]+,[0-9]+\))', tosolve)
    testuserinput_grid = re.findall(r'^([0-9]+x[0-9]+)\(', tosolve)
    assertpatternmatching = len(testuserinput_grid[0]) + len(''.join(testuserinput_tuples))
    try:
        assert assertpatternmatching == len(tosolve)
    except AssertionError:
        sys.exit("Bad input to script, unexpected input format. A valid input should contain a grid spacing, followed by numbered tuples: 5x5 (0, 0) (1, 3)")
    (gridSize, directionsList) = extractInstructionsAsTuples(tosolve)
    driverInstructionsList = extractDriverInstructions(directionsList)
    navString = extractNavStringFromDriverInstructions(driverInstructionsList)
    print(navString)


if __name__ == "__main__":
    #unittest.main()
    print("Running as main")
    run()

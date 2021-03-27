# This is a script to calculate directions for a pizza delivery

#I have added peppercorn as a dependency. This is not actually needed, but for demo purposes.

#Make sure you have latest build script in pip:
#python3 -m pip install --upgrade build

#run this script to create a tarball under dist :
#python3 -m build

#Then extract the tarball:
#pip install pythontest-pkg-matpower-0.0.1.tar.gz
#Right now, I can't get the bash scripts to appear this way. So you will have to make do with with extracting the tarball:

#tar -xf pythontest-pkg-matpower-0.0.1.tar.gz
#cd pythontest-pkg-matpower-0.0.1
#This is the folder to run the bash script from.

#INSTRUCTIONS:

#The unit tests are kept separate from the main script. To run tests, go into dist/src folder and run:
#   ./pizza_test.sh

#To run the main script, go to dist/src and run:
#   ./pizza.sh

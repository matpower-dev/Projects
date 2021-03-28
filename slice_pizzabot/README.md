# This directory contains files to install on users system The purpose is to calculate directions for a pizza delivery

#You can set this up the full way, or the abridged way. 
#The full way will do a proper installation on your system and sort any dependencies. The abridged way just involves extracting contents of a tarball.

#FULL WAY

#I have added peppercorn as a dependency. This is not actually needed, but is for demo purposes.
#Go into the slice_pizzaboy folder, containing setup.py, setup.cfg etc.
#Do the below commands:

#pip install wheel
#python setup.py bdist_wheel
#python setup.py sdist

#This will create a tarball under dist. If you want to, extract using:
#tar -xf dist/pythontest-pkg-matpower-0.0.1.tar.gz
#From there, pick up the "less involved" section below.
#OR:
#Install it on your system using:
#pip install dist/pythontest-pkg-matpower-0.0.1.tar.gz

#You should now have now got the test suite and pizzabot installed.
#run from any folder using:

#pizza_test.sh
#pizza.sh "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"

#The python modules will be located differently for you, but should in standard location pip installs to. For me, I use a shim, so it goes to:
#~/.pyenv/versions/3.8.5/lib/python3.8/site-package/pizzapackage
#The scripts also go whereever pip moves these. For me, this is:
#~/.pyenv/versions/3.8.5/bin


#LESS INVOLVED:

#A tarball is included. If you want you can extract it directly using:

#tar -xf pythontest-pkg-matpower-0.0.1.tar.gz
#This is optimised for my system by the build process, so may be less stable. 

#Move the pizza.sh and pizza_test.sh scripts one level up.
#I did not add an extra level to pizzapackage to avoid unnecessary complication, the scripts are written under the assumption that pizzapackage is installed locally. If this is not the case, the run scripts need to be moved up a level. 

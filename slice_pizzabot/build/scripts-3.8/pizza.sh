#python -m pizza "5x5 (0, 0) (1, 3) (4, 4) (4, 2) (4, 2) (0, 1) (3, 2) (2, 3) (4, 1)"
#export PYTHONPATH="${PYTHONPATH}:${PWD}"
python -m pizzapackage.src.pizza "$1"

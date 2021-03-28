import setuptools

setuptools.setup(
    name='pizzapackage-matpower',
    version='0.0.2',
    install_requires=[
        'peppercorn'
    ],
    packages=['pizzapackage', 'pizzapackage/src', 'pizzapackage'],
    scripts=['src/pizzapackage/pizza_test.sh', 'src/pizzapackage/pizza.sh']
)

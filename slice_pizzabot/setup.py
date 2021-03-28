import setuptools

setuptools.setup(
    name='pythontest-pkg-matpower',
    version='0.0.1',
    install_requires=[
        'peppercorn'
    ],
    packages=['pizzapackage', 'pizzapackage/src', 'pizzapackage'],
    scripts=['src/pizzapackage/pizza_test.sh', 'src/pizzapackage/pizza.sh']
)

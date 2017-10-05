#include <QCoreApplication>
#include <iostream>
#include <cmath>

using namespace std;

double func(double arg){ // y = x^2
    return arg * arg;
}

double func1(double arg){ // y = sin(x)
    return sin(arg);
}

double func2(double arg){ // y = cos(x)
    return cos(arg);
}

double func3(double arg){ // y = abs(x)
    return abs(arg);
}

double func4(double arg){ // y = log(x)
    return log(arg);
}

double func5(double arg){ // y = exp(x)
    return exp(arg);
}

double func6(double arg){ // y = atan(x)
    return atan(arg);
}

double func7(double arg){ // (x - 1)^2 - 1 на интервале [0, 2], продолженная на всю вещественную прямую с периодом 2
    double argMod2 = fmod(arg, 2);
    return (argMod2 - 1) * (argMod2 - 1) - 1;
}

double func8(double arg){ // y = log2(x + 1)
    return log2(arg + 1);
}

double func9(double arg){ // y = 2^x
    return exp2(arg);
}

// Метод деления отрезка пополам
// Поиск минимума
// [a, b] - диапазон значений аргумента (пространство поиска)
// eps - погрешность вычислений
// deltaX - некоторое достаточно малое положительное число, 2 * deltaX < eps (!)
// minLoc - положение минимума
// minVal - значение минимума

void findMinLocByBisection(double(*func)(double), double a, double b, double eps, double deltaX,
                double& minLoc, double& minVal){
    double x1, x2;
    double f_x1, f_x2;

    cout << "iter 0 ... " << "x1 = " << a << ", x2 = " << b << endl;
    int numOfIter = 1;

    while ((b - a) >= eps){
        x1 = (b + a)/2 - deltaX;
        x2 = (b + a)/2 + deltaX;
        cout << "iter " << numOfIter << " ... " << "x1 = " << x1 << ", x2 = " << x2 << endl;

        f_x1 = func(x1);
        f_x2 = func(x2);

        if (f_x1 > f_x2){
            a = x1;
        }
        else{
            b = x2;
        }
        numOfIter++;
    }

    minLoc = (a + b)/2;
    minVal = func(minLoc);
}

// Метод золотого сечения
// Поиск минимума
// [a, b] - диапазон значений аргумента (пространство поиска)
// eps - погрешность вычислений
// minLoc - положение минимума
// minVal - значение минимума

void findMinLocByGoldenRatio(double(*func)(double), double a, double b, double eps,
                             double& minLoc, double& minVal){
    double x1, x2;
    double f_x1, f_x2;

    double F = (1 + sqrt(5))/2;

    cout << "iter 0 ... " << "x1 = " << a << ", x2 = " << b << endl;
    int numOfIter = 1;

    while (b - a > eps){
        x1 = b - (b - a)/F;
        x2 = a + (b - a)/F;

        cout << "iter " << numOfIter << " ... " << "x1 = " << x1 << ", x2 = " << x2 << endl;

        f_x1 = func(x1);
        f_x2 = func(x2);

        if (f_x1 > f_x2){
            a = x1;
        }
        else{
            b = x2;
        }

        numOfIter++;
    }

    minLoc = (a + b)/2;
    minVal = func(minLoc);
}

int main()
{
    double minLoc, minVal;

    cout << "--------------------Bisection-----------------------" << endl;

    double eps = 0.02;
    double deltaX = 0.005;

    cout << "f = x^2, a = 2, b = 4" << endl;
    findMinLocByBisection(func, 2.0, 4.0, eps, deltaX,  minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = sin(x), a = 0, b = 6.28" << endl;
    findMinLocByBisection(func1, 0.0, 6.28, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = cos(x), a = -1.57, b = 3.14" << endl;
    findMinLocByBisection(func2, -1.57, 3.14, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = abs(x), a = -10, b = 10" << endl;
    findMinLocByBisection(func3, -10, 10, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = log(x), a = 1, b = 10" << endl;
    findMinLocByBisection(func4, 1, 10, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = exp(x), a = 0, b = 10" << endl;
    findMinLocByBisection(func5, 0, 10, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = atan(x), a = -1, b = 1" << endl;
    findMinLocByBisection(func6, -1.0, 1.0, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = (x - 1)^2 - 1, x = {0, ..., 2}, T = 2, a = -4, b = 4" << endl;
    findMinLocByBisection(func7, -4, 4, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = log2(x + 1),  a = 0, b = 10" << endl;
    findMinLocByBisection(func8, 0, 10, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = 2^x,  a = 2, b = 8" << endl;
    findMinLocByBisection(func9, 2, 8, eps, deltaX, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "--------------------Golden ratio--------------------" << endl;

    cout << "f = x^2, a = 2, b = 4" << endl;
    findMinLocByGoldenRatio(func, 2.0, 4.0, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = sin(x), a = 0, b = 6.28" << endl;
    findMinLocByGoldenRatio(func1, 0.0, 6.28, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = cos(x), a = -1.57, b = 3.14" << endl;
    findMinLocByGoldenRatio(func2, -1.57, 3.14, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = abs(x), a = -10, b = 10" << endl;
    findMinLocByGoldenRatio(func3, -10, 10, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = log(x), a = 1, b = 10" << endl;
    findMinLocByGoldenRatio(func4, 1, 10, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = exp(x), a = 0, b = 10" << endl;
    findMinLocByGoldenRatio(func5, 0, 10, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = atan(x), a = -1, b = 1" << endl;
    findMinLocByGoldenRatio(func6, -1.0, 1.0, 0.01, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = (x - 1)^2 - 1, x = {0, ..., 2}, T = 2, a = -4, b = 4" << endl;
    findMinLocByGoldenRatio(func7, -4, 4, eps, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = log2(x + 1),  a = 0, b = 10" << endl;
    findMinLocByGoldenRatio(func8, 0, 10, eps, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    cout << "f = 2^x,  a = 2, b = 8" << endl;
    findMinLocByGoldenRatio(func9, 2, 8, eps, minLoc, minVal);
    cout << "minLoc = " << minLoc << ", minVal = " << minVal << endl;

    return 0;
}

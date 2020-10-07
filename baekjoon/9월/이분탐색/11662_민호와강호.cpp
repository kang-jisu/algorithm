#include <iostream>
#include <cmath>
using namespace std;

double dis(double x1, double y1, double x2, double y2)
{
    // cout << x1 << " " << y1 << "\n";
    // cout << x2 << " " << y2 << "\n";
    return sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
}

double one(double des, double ori)
{
    return ori + (des - ori) * 2 / 3;
}
double two(double des, double ori)
{
    return ori + (des - ori) * 1 / 3;
}
int main()
{

    double ax, ay, bx, by, cx, cy, dx, dy;
    cin >> ax >> ay >> bx >> by >> cx >> cy >> dx >> dy;

    double now = dis(ax, ay, cx, cy);
    double des = dis(bx, by, dx, dy);
    cout.precision(10);
    cout << now << "\n";
    cout << des << "\n";

    double oneDis = dis(one(ax, bx), one(ay, by), one(cx, dx), one(cy, dy));
    double twoDis = dis(two(ax, bx), two(ay, by), two(cx, dx), two(cy, dy));

    cout << oneDis << "\n";
    cout << twoDis << "\n";
    // while (1)
    // {
    //     double midVal = (now + des) / 2;
    //     cout << midVal << "\n";
    //     if (midDis == midVal)
    //     {
    //         cout << midVal << "\n";
    //         break;
    //     }
    //     else
    //     {
    //         if (midDis > midVal)
    //         {
    //             cout << "midDis>midVal\n";
    //         }
    //         else
    //             cout << "midDis<midVal\n";
    //         break;
    //     }
    // }
    return 0;
}
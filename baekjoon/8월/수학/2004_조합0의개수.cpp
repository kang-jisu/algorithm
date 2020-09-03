#include <iostream>
using namespace std;

long long divide5(long long num)
{
    long long sum = 0;
    for (long long i = 5; i <= num; i *= 5)
    {
        sum += num / i;
    }
    return sum;
}
long long divide2(long long num)
{
    long long sum = 0;
    for (long long i = 2; i <= num; i *= 2)
    {
        sum += num / i;
    }
    return sum;
}
int main()
{
    long long n, m;
    cin >> n >> m;
    int two = 0;
    int five = 0;
    two += (divide2(n) - divide2(m) - divide2(n - m));
    five += (divide5(n) - divide5(m) - divide5(n - m));
    if (two > five)
        cout << five << "\n";
    else
        cout << two << "\n";
    return 0;
}
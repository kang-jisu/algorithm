#include <iostream>
#include <cmath>
#include <string>
using namespace std;

int main()
{

    int a, b;
    cin >> a >> b;
    int m;
    cin >> m;

    int tmp;
    int idx = m - 1;
    long sum = 0;
    string result = "";

    for (int i = 0; i < m; i++)
    {
        cin >> tmp;
        sum += pow(a, idx--) * tmp;
    }
    if (sum == 0)
        cout << "0\n";
    else
    {
        while (sum > 0)
        {
            result += sum % b;
            sum /= b;
        }
        for (int i = result.length() - 1; i >= 0; i--)
        {
            cout << (int)result[i] << " ";
        }
        cout << "\n";
    }
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;

int main()
{

    string num;
    cin >> num;

    long long sum = 0;
    vector<int> str(num.length());
    bool isZero = false;
    for (int i = 0; i < num.length(); i++)
    {
        if (num[i] == '0')
            isZero = true;
        else
        {
            sum += (long long)(num[i] - '0');
        }
    }
    if (sum % 3 == 0 && isZero)
    {
        for (int i = 0; i < num.length(); i++)
        {
            str[i] = (int)(num[i] - '0');
        }
        sort(str.begin(), str.end());
        for (int i = str.size() - 1; i >= 0; i--)
        {
            cout << str[i];
        }
        cout << "\n";
    }
    else
        cout << -1 << "\n";
    return 0;
}
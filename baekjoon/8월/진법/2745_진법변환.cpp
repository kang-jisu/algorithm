#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int main()
{

    string n;
    int b;
    cin >> n >> b;
    long long result = 0;
    int idx = n.length() - 1;
    for (int i = 0; i < n.length(); i++)
    {
        if (n[i] - '0' >= 0 && n[i] - '0' <= 9)
        {

            result += (int)(n[i] - '0') * pow(b, idx--);
        }
        else
        {
            result += (int)(n[i] - 'A' + 10) * pow(b, idx--);
        }
    }
    cout << result << "\n";
    return 0;
}
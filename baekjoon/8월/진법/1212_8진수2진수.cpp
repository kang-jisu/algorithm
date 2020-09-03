#include <iostream>
#include <string>
using namespace std;

int main()
{

    string oct;
    cin >> oct;

    string result = "";

    for (int i = 0; i < oct.length(); i++)
    {
        string tmp = "";
        int num = (int)oct[i] - '0';
        while (num > 1)
        {
            if (num % 2 == 0)
                tmp += "0";
            else
                tmp += "1";
            num /= 2;
        }
        if (num == 0)
            tmp += "0";
        else
            tmp += '1';
        if (i != 0)
        {
            while (tmp.length() < 3)
            {
                tmp += "0";
            }
        }
        for (int j = tmp.length() - 1; j >= 0; j--)
        {
            result += tmp[j];
        }
    }
    cout << result << "\n";

    return 0;
}
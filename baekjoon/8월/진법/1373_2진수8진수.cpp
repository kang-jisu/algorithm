#include <iostream>
#include <string>
#include <cmath>
using namespace std;

int main()
{
    string bin;
    cin >> bin;

    string res = "";

    int cnt = 0;
    int sum = 0;
    for (int i = bin.length() - 1; i >= 0; i--)
    {
        if (bin[i] == '1')
        {
            sum += pow(2, cnt);
        }
        cnt++;
        if (cnt == 3)
        {
            cnt = 0;
            res += to_string(sum);
            sum = 0;
        }
    }
    if(res==""&&sum==0)cout<<0<<"\n";
    else {
    if (sum != 0)
        res += to_string(sum);
    for (int i = res.length() - 1; i >= 0; i--)
    {
        cout << res[i];
    }
    cout << "\n";
    }

    return 0;
}
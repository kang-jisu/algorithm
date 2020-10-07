#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int main()
{

    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    cin >> n;
    vector<vector<int>> cf(n);
    for (int i = 0; i < n; i++)
    {
        int a, b;
        cin >> a >> b;
        cf[i].push_back(b);
        cf[i].push_back(a);
    }

    sort(cf.begin(), cf.end());

    int tmpEnd = cf[0][0];
    int cnt = 1;
    for (int j = 1; j < n; j++)
    {
        if (cf[j][1] >= tmpEnd)
        {
            cnt++;
            tmpEnd = cf[j][0];
        }
    }
    cout << cnt << "\n";

    return 0;
}
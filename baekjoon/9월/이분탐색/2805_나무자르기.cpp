#include <iostream>
#include <vector>
using namespace std;

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int n;
    long long m;
    cin >> n >> m;

    long long max = 0;
    long long min = 0;
    vector<long long> v;
    v.resize(n + 1);

    for (int i = 0; i < n; i++)
    {
        cin >> v[i];
        if (max < v[i])
            max = v[i];
    }
    long long mid = 0;
    long long result = 0;

    while (min < max)
    {
        long long cnt = 0;
        mid = (min + max) / 2;
        for (int i = 0; i < n; i++)
        {
            if (v[i] >= mid)
                cnt += v[i] - mid;
        }
        if (cnt < m)
        {
            max = mid;
        }
        else
        {
            result = mid;
            min = mid + 1;
        }
    }
    cout << result << "\n";

    return 0;
}
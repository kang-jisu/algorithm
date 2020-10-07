#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

// 생각해봐야할듯 . 답보고함

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n, c;
    cin >> n >> c;

    vector<long long> house(n);
    for (int i = 0; i < n; i++)
    {
        cin >> house[i];
    }
    sort(house.begin(), house.end());
    long long min = 1;
    long long max = house[house.size() - 1];
    long long result;
    while (min < max)
    {
        long long mid = (min + max) / 2;
        if (result == mid)
            break;

        long long cnt = 1;
        long long prev = house[0];
        for (int i = 1; i < house.size(); i++)
        {
            if (house[i] - prev >= mid)
            {
                cnt++;
                prev = house[i];
            }
        }
        if (cnt < c)
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
#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int n, m;
int narr[500001];
vector<pair<int, int>> ncnt;
int marr[500001];
int binarySearch(int a)
{
    int left = 0;
    int right = ncnt.size() - 1;
    int mid;
    while (left < right)
    {
        mid = (left + right) / 2;
        if (ncnt[mid].first == a)
            return ncnt[mid].second;
        if (ncnt[mid].first > a)
        {
            right = mid - 1;
        }
        else
        {
            left = mid + 1;
        }
    }
    if (ncnt[right].first == a)
        return ncnt[right].second;
    return 0;
}
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        cin >> narr[i];
    }
    cin >> m;
    for (int i = 0; i < m; i++)
    {
        cin >> marr[i];
    }
    sort(narr, narr + n);

    int cnt = 0;
    for (int i = 0; i < n; i++)
    {
        cnt++;
        if (i == 0)
            continue;
        if (narr[i - 1] != narr[i])
        {
            ncnt.push_back(make_pair(narr[i - 1], cnt - 1));
            cnt = 1;
        }
        else if (i == n - 1)
        {
            ncnt.push_back(make_pair(narr[i], cnt));
        }
    }

    for (int i = 0; i < m; i++)
    {
        cout << binarySearch(marr[i]) << " ";
    }
    cout << "\n";

    return 0;
}
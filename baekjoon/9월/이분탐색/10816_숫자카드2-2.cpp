#include <iostream>
#include <algorithm>
using namespace std;


// lowerbound, upperbound 구해서 차로 개수 구하는 풀이법
// 
int n, m;
int narr[500001];
int marr[500001];
int binarySearch_lower(int a)
{
    // -10 2 3 6 10
    int left = 0;
    int right = n - 1;
    int mid;
    while (left < right)
    {
        mid = (left + right) / 2;
        if (narr[mid] >= a)
        {
            right = mid;
        }
        else
        {
            left = mid + 1;
        }
    }
    return right;
}

int binarySearch_upper(int a)
{
    // -10 2 3 6 10 10
    int left = 0;
    int right = n - 1;
    int mid;
    while (left < right)
    {
        mid = (left + right) / 2;
        if (narr[mid] > a)
        {
            right = mid;
        }
        else
        {
            left = mid + 1;
        }
    }
    if (narr[right] != a)
        right--;
    return right;
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

    for (int i = 0; i < m; i++)
    {
        int lower = binarySearch_lower(marr[i]);
        int upper = binarySearch_upper(marr[i]);

        if (upper >= lower)
            cout << upper - lower + 1 << " ";
        else
            cout << 0 << " ";
    }
    cout << "\n";

    return 0;
}
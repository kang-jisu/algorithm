#include <iostream>
#include <algorithm>
using namespace std;

int n, m;
int narr[500001];
int marr[500001];
int binarySearch(int a)
{
    // -10 2 3 6 10
    int left = 0;
    int right = n - 1;
    int mid;
    while (left < right)
    {
        mid = (left + right) / 2;
        if (narr[mid] == a)
            return 1;
        if (narr[mid] > a)
        {
            right = mid - 1;
        }
        else
        {
            left = mid + 1;
        }
    }
    if (narr[right] == a)
        return 1;
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

    for (int i = 0; i < m; i++)
    {
        cout << binarySearch(marr[i]) << " ";
    }
    cout << "\n";

    return 0;
}
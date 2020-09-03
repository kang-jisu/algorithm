#include <iostream>
#include <algorithm>
using namespace std;

int main()
{
    int n;
    cin >> n;

    long long arr[100001];
    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    sort(arr, arr + n);
    int count = 1;
    long long value = arr[0];
    int max = -1;
    for (int i = 1; i < n; i++)
    {
        if (arr[i - 1] != arr[i])
        {
            if (max < count)
            {
                max = count;
                value = arr[i - 1];
            }
            count = 1;
        }
        else
            count++;
    }
    if (max < count)
    {
        max = count;
        value = arr[n - 1];
    }
    cout << value << "\n";
    return 0;
}
#include <iostream>
using namespace std;

int main()
{

    int arr[100001] = {
        0,
    };
    int n, k;
    cin >> n >> k;
    for (int i = 1; i <= n; i++)
    {
        arr[i] = 1;
    }
    int count = 0;
    int idx = 0;
    cout << "<";
    while (count < n)
    {
        int i = 0;
        while (i < k)
        {
            idx++;
            if (idx > n)
                idx %= n;
            if (arr[idx] == 1)
            {
                i++;
            }
        }
        count++;
        arr[idx] = 0;
        if (count == n)
            cout << idx << ">";
        else
            cout << idx << ", ";
    }

    return 0;
}
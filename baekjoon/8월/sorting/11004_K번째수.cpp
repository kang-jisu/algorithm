#include <iostream>
#include <algorithm>
using namespace std;

int arr[5000001];
int main()
{
    std::ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, k;
    cin >> n >> k;

    for (int i = 0; i < n; i++)
    {
        cin >> arr[i];
    }
    nth_element(arr, arr + k - 1, arr + n);
    cout << arr[k - 1] << "\n";

    return 0;
}
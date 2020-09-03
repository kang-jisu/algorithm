#include <iostream>
using namespace std;

int arr[1000001];
int main()
{
    std::ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    for (int i = 2; i <= 1000; i++)
    {
        for (int j = 2; j * i <= 1000000; j++)
        {
            arr[i * j] = 1;
        }
    }
    arr[1] = 1;
    int n;
    cin >> n;
    while (n != 0)
    {
        for (int i = n - 1; i >= 2; i--)
        {
            if (arr[i] == 0 && arr[n - i] == 0)
            {
                cout << n << " = " << n - i << " + " << i << "\n";
                break;
            }
        }
        cin >> n;
    }
    return 0;
}
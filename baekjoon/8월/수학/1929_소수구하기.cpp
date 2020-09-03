#include <iostream>
using namespace std;

int arr[1000001] = {0};
int main()
{

    int n, m;
    cin >> n >> m;
    for (int i = 2; i <= 1000000; i++)
    {
        if (arr[i] == 0)
        {
            for (int j = 2; i * j <= 1000000; j++)
            {
                arr[i * j] = 1;
            }
        }
    }
    for (int i = n; i <= m; i++)
    {
        if (i == 1)
            continue;
        if (arr[i] == 0)
            cout << i << "\n";
    }
    return 0;
}
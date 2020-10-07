#include <iostream>

using namespace std;

int arr[200][100];
int main()
{

    int n, l;
    cin >> n >> l;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> arr[i][j];
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            arr[i + n][j] = arr[j][i];
        }
    }

    int ans = 0;

    for (int i = 0; i < n * 2; i++)
    {
        int curlen = 1;
        int j;
        for (j = 0; j < n - 1; j++)
        {
            if (arr[i][j] == arr[i][j + 1])
            {
                curlen++;
            }
            else if (arr[i][j] == arr[i][j + 1] + 1)
            { // 내려가는거
                if (curlen >= 0)
                {
                    curlen = 1 - l;
                }
                else
                    break;
            }
            else if (arr[i][j] + 1 == arr[i][j + 1])
            { // 올라가는거
                if (curlen >= l)
                {
                    curlen = 1;
                }
                else
                    break;
            }
            else
                break;
        }
        if (j == n - 1 && curlen >= 0)
        {
            // cout << "i:" << i << "\n";
            ans++;
        }
    }
    cout << ans << "\n";
    return 0;
}
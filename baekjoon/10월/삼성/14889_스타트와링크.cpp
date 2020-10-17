#include <iostream>
#include <algorithm>
#include <cmath>
#include <vector>
using namespace std;

int n;
int A[21];
int arr[21][21];
vector<int> start;
vector<int> link;
int val = 1000000000;
void cal()
{
    for (int i = 0; i < n; i++)
    {
        if (A[i] == 0)
            start.push_back(i);
        else
            link.push_back(i);
    }
    vector<int> v;
    for (int i = 0; i < n / 2 - 2; i++)
    {
        v.push_back(0);
    }
    v.push_back(1);
    v.push_back(1);

    int ssum = 0;
    int lsum = 0;
    vector<int> idx;
    do
    {
        idx.clear();
        for (int i = 0; i < n / 2; i++)
        {
            if (v[i] == 1)
                idx.push_back(i);
        }
        int i = start[idx[0]];
        int j = start[idx[1]];
        int a = link[idx[0]];
        int b = link[idx[1]];
        ssum += arr[i][j] + arr[j][i];
        lsum += arr[a][b] + arr[b][a];

    } while (next_permutation(v.begin(), v.end()));

    if (abs(ssum - lsum) < val)
        val = abs(ssum - lsum);
}
int main()
{

    cin >> n;

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> arr[i][j];
        }
    }
    for (int i = 0; i < n / 2; i++)
    {
        A[n - i - 1] = 1;
    }
    do
    {
        if (A[0] == 0)
        {
            start.clear();
            link.clear();
            cal();
        }
        else
            break;

    } while (next_permutation(A, A + n));

    cout << val << "\n";
    return 0;
}
#include <iostream>
#include <vector>

using namespace std;
vector<int> v;
int n, m;
void mergeSort()
{
    vector<int> tmp;
    int i = 0;
    int j = n;
    while (i < n && j < n + m)
    {
        if (v[i] < v[j])
        {
            tmp.push_back(v[i++]);
        }
        else
        {
            tmp.push_back(v[j++]);
        }
    }

    while (i < n)
    {
        tmp.push_back(v[i++]);
    }

    while (j < n + m)
    {
        tmp.push_back(v[j++]);
    }
    for (i = 0; i < n + m; i++)
    {
        v[i] = tmp[i];
    }
}
int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n >> m;
    v.resize(n + m + 1);
    for (int i = 0; i < n; i++)
    {
        cin >> v[i];
    }
    for (int i = 0; i < m; i++)
    {
        cin >> v[n + i];
    }
    mergeSort();

    for (int i = 0; i < n + m; i++)
    {
        cout << v[i] << " ";
    }
    return 0;
}

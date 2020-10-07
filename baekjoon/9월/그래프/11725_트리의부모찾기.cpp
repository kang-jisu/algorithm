#include <iostream>
#include <vector>
#include <stack>
using namespace std;

vector<vector<int>> v;
vector<int> parent;
vector<int> visit;

void dfs(int a)
{
    visit[a] = 1;
    for (int i = 0; i < v[a].size(); i++)
    {
        if (visit[v[a][i]] == 0)
        {
            dfs(v[a][i]);
            parent[v[a][i]] = a;
        }
    }
}
int main()
{

    int n;
    cin >> n;
    v.resize(n + 1);
    parent.resize(n + 1);
    visit.resize(n + 1);
    int a, b;
    for (int i = 0; i < n - 1; i++)
    {
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }

    dfs(1);

    for (int i = 2; i <= n; i++)
    {
        cout << parent[i] << "\n";
    }
    return 0;
}
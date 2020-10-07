#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> graph;
int visit[20002];
bool tf = true;

void dfs(int v)
{
    for (int i = 0; i < graph[v].size(); i++)
    {
        if (visit[graph[v][i]] == 0)
        {
            if (visit[v] == 1)
                visit[graph[v][i]] = 2;
            else
                visit[graph[v][i]] = 1;
            dfs(graph[v][i]);
        }
        if (visit[v] == visit[graph[v][i]])
        {
            tf = false;
        }
    }
}
int main()
{
    int t;
    cin >> t;
    while (t--)
    {
        int n, m;
        cin >> n >> m;
        graph.clear();
        graph.resize(n + 1);
        for (int i = 1; i <= n; i++)
        {
            visit[i] = 0;
        }
        tf = true;
        for (int i = 0; i < m; i++)
        {
            int a, b;
            cin >> a >> b;
            graph[a].push_back(b);
            graph[b].push_back(a);
        }
        for (int i = 1; i <= n; i++)
        {
            if (visit[i] == 0)
            {
                visit[i] = 1;
                dfs(i);
            }
        }
        if (tf)
            cout << "YES\n";
        else
            cout << "NO\n";
    }

    return 0;
}

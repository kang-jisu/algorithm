#include <iostream>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
using namespace std;

stack<int> s;
queue<int> q;
int svisit[1001];
int qvisit[1001];
void dfs(vector<vector<int>> &v)
{
    int node = s.top();
    s.pop();
    svisit[node] = 1;
    cout << node << " ";
    for (int i = 0; i < v[node].size(); i++)
    {
        if (svisit[v[node][i]] == 0)
        {
            s.push(v[node][i]);
            dfs(v);
        }
    }
}
void bfs(vector<vector<int>> &v)
{
    if (q.empty())
        return;
    int node = q.front();
    q.pop();
    if (qvisit[node] == 0)
    {
        qvisit[node] = 1;
        cout << node << " ";
    }
    for (int i = 0; i < v[node].size(); i++)
    {
        if (qvisit[v[node][i]] == 0)
        {
            q.push(v[node][i]);
        }
    }
    bfs(v);
}

int main()
{
    int N, M, V;
    cin >> N >> M >> V;

    vector<vector<int>> v;
    v.resize(N + 1);
    for (int i = 0; i < M; i++)
    {
        int a, b;
        cin >> a >> b;
        v[a].push_back(b);
        v[b].push_back(a);
    }
    for (int i = 0; i < v.size(); i++)
    {
        sort(v[i].begin(), v[i].end());
    }
    s.push(V);
    q.push(V);
    dfs(v);
    cout << "\n";
    bfs(v);

    return 0;
}
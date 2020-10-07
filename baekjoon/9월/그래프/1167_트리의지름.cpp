#include <iostream>
#include <vector>
using namespace std;

vector<vector<pair<int, int>>> tree;
vector<int> visit;
int n;
int maxDis = 0;
int idx = -1;
void dfs(int node, int dis)
{
    if (visit[node] != 0)
        return;
    visit[node] = 1;
    if (maxDis < dis)
    {
        maxDis = dis;
        idx = node;
    }
    for (int i = 0; i < tree[node].size(); i++)
    {
        dfs(tree[node][i].first, dis + tree[node][i].second);
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n;
    tree.resize(n + 1);
    visit.resize(n + 1);
    for (int i = 0; i < n - 1; i++)
    {
        int num;
        cin >> num;
        int ver;
        cin >> ver;
        if (ver == -1)
            break;
        int dis;
        cin >> dis;
        tree[num].push_back(make_pair(ver, dis));
        tree[ver].push_back(make_pair(num, dis));
    }
    dfs(1, 0);
    maxDis = 0;
    visit.clear();
    visit.resize(n + 1);
    dfs(idx, 0);
    cout << maxDis << "\n";

    return 0;
}

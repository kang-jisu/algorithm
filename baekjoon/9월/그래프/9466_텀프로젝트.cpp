#include <iostream>
using namespace std;

int stu[100001] = {0};
int visit[100001] = {0};
int finished[100001] = {0}; // 이미 끝나서 더이상 확인할 필요 없는 인덱스 표시 해줌.이게중요
int cnt = 0;

int n;
void dfs(int node)
{
    visit[node] = 1;
    int next = stu[node];
    if (visit[next] == 0)
        dfs(next);
    else
    {
        if (finished[next] == 0)
        {
            for (int i = next; i != node; i = stu[i])
            {
                cnt++;
            }
            cnt++;
        }
    }
    finished[node] = 1;
}
int main()
{

    int t;
    cin >> t;
    while (t--)
    {
        cin >> n;
        cnt = 0;
        for (int i = 1; i <= n; i++)
        {
            visit[i] = 0;
            finished[i] = 0;
        }
        for (int i = 1; i <= n; i++)
        {
            cin >> stu[i];
        }
        for (int i = 1; i <= n; i++)
        {
            if (visit[i] == 0)
                dfs(i);
        }
        cout << n - cnt << "\n";
    }
    return 0;
}
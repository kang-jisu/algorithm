#include <iostream>
#include <algorithm>
using namespace std;

char danzi[26][26];
int visit[26][26];
int num[323];
int n;
void dfs(int i, int j, int cnt)
{
    visit[i][j] = cnt;
    num[cnt]++;
    if (i > 0 && danzi[i - 1][j] == '1' && visit[i - 1][j] == 0)
        dfs(i - 1, j, cnt);
    if (i < n - 1 && danzi[i + 1][j] == '1' && visit[i + 1][j] == 0)
        dfs(i + 1, j, cnt);
    if (j > 0 && danzi[i][j - 1] == '1' && visit[i][j - 1] == 0)
        dfs(i, j - 1, cnt);
    if (j < n - 1 && danzi[i][j + 1] == '1' && visit[i][j + 1] == 0)
        dfs(i, j + 1, cnt);
}
int main()
{
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> danzi[i][j];
        }
    }
    int cnt = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (danzi[i][j] == '1' && visit[i][j] == 0)
            {
                cnt++;
                dfs(i, j, cnt);
            }
        }
    }

    cout << cnt << "\n";
    sort(num, num + 323);
    for (int i = 1; i < 323; i++)
    {
        if (num[i] != 0)
            cout << num[i] << "\n";
    }

    return 0;
}


#include <iostream>
using namespace std;

int land[52][52];
int visit[52][52];
int w, h;

void dfs(int i, int j)
{
    visit[i][j] = 1;

    if (i > 0 && land[i - 1][j] == 1 && visit[i - 1][j] == 0)
        dfs(i - 1, j);
    if (i < h - 1 && land[i + 1][j] == 1 && visit[i + 1][j] == 0)
        dfs(i + 1, j);
    if (j > 0 && land[i][j - 1] == 1 && visit[i][j - 1] == 0)
        dfs(i, j - 1);
    if (j < w - 1 && land[i][j + 1] == 1 && visit[i][j + 1] == 0)
        dfs(i, j + 1);
    if (i > 0 && j > 0 && land[i - 1][j - 1] == 1 && visit[i - 1][j - 1] == 0)
        dfs(i - 1, j - 1);
    if (i > 0 && j < w - 1 && land[i - 1][j + 1] == 1 && visit[i - 1][j + 1] == 0)
        dfs(i - 1, j + 1);
    if (i < h - 1 && j > 0 && land[i + 1][j - 1] == 1 && visit[i + 1][j - 1] == 0)
        dfs(i + 1, j - 1);
    if (i < h - 1 && j < w - 1 && land[i + 1][j + 1] == 1 && visit[i + 1][j + 1] == 0)
        dfs(i + 1, j + 1);
}
int main()
{
    while (1)
    {
        cin >> w >> h;
        if (w + h == 0)
            break;
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                cin >> land[i][j];
            }
        }

        int cnt = 0;
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                if (land[i][j] == 1 && visit[i][j] == 0)
                {
                    cnt++;
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                visit[i][j] = 0;
            }
        }
        cout << cnt << "\n";
    }
    return 0;
}
#include <iostream>
#include <vector>

using namespace std;

// 3 3
// 6 6 6
// 6 6 6
// 0 6 6 같은 반례에서 cctv 아예 없을때 기본적으로 0인 개수를 최소로 예외처리를 해주어야함

int n, m;
int off[9][9];
struct cctv
{
    int x;
    int y;
    int num;
    int max;
    int now;
};

vector<cctv> clist;
int tmpoff[9][9];
int cc[] = {0, 4, 2, 4, 4, 1}; // cctv case;

int zero = 0;
int minBlind = 2100000000;

void tmpcheck(cctv ct, int dir, int ori[][9])
{
    // cout << ct.num << "번 cctv: " << ct.x << "," << ct.y << " 좌표에서" << dir << "방향 감시\n";

    //일단 초기화
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            tmpoff[i][j] = ori[i][j];
        }
    }
    if ((ct.num == 1 && dir == 0) || (ct.num == 2 && dir == 0) || (ct.num == 3 && dir <= 1) || (ct.num == 4 && dir <= 2) || (ct.num == 5))
    {
        //현재 좌표에서 오른쪽으로 쭊
        for (int j = ct.y; j < m; j++)
        {
            if (tmpoff[ct.x][j] == 6)
                break;
            if (tmpoff[ct.x][j] == 0)
                tmpoff[ct.x][j] = 9;
        }
    }
    if ((ct.num == 1 && dir == 2) || (ct.num == 2 && dir == 0) || (ct.num == 3 && dir >= 2) || (ct.num == 4 && dir != 1) || (ct.num == 5))
    {

        //현재 좌표에서 왼쪾으로 쭊
        for (int j = ct.y; j >= 0; j--)
        {
            if (tmpoff[ct.x][j] == 6)
                break;
            if (tmpoff[ct.x][j] == 0)
                tmpoff[ct.x][j] = 9;
        }
    }
    if ((ct.num == 1 && dir == 3) || (ct.num == 2 && dir == 1) || (ct.num == 3 && dir == 0) || (ct.num == 3 && dir == 3) || (ct.num == 4 && dir != 2) || (ct.num == 5))
    {

        //현재 좌표에서 위로 쭉
        for (int i = ct.x; i >= 0; i--)
        {
            if (tmpoff[i][ct.y] == 6)
                break;
            if (tmpoff[i][ct.y] == 0)
                tmpoff[i][ct.y] = 9;
        }
    }
    if ((ct.num == 1 && dir == 1) || (ct.num == 2 && dir == 1) || (ct.num == 3 && dir == 1) || (ct.num == 3 && dir == 2) || (ct.num == 4 && dir != 0) || (ct.num == 5))
    {

        //현재 좌표에서 밑으로 쭉
        for (int i = ct.x; i < n; i++)
        {
            if (tmpoff[i][ct.y] == 6)
                break;
            if (tmpoff[i][ct.y] == 0)
                tmpoff[i][ct.y] = 9;
        }
    }
}

void dfs(int id, int ori[][9])
{
    int copytmp[9][9];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            copytmp[i][j] = ori[i][j];
        }
    }

    if (id < clist.size())
    {
        for (int i = 0; i < clist[id].max; i++)
        {
            tmpcheck(clist[id], i, ori); // tmpoff함수에다가 그거 함
            dfs(id + 1, tmpoff);
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < m; j++)
                {
                    tmpoff[i][j] = copytmp[i][j];
                }
            }
        }
    }
    else
    {
        int blind = 0;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (tmpoff[i][j] == 0)
                    blind++;
            }
        }
        if (minBlind > blind)
            minBlind = blind;
    }
}
int main()
{
    cin >> n >> m;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            cin >> off[i][j];
            if (off[i][j] == 0)
                zero++;
            tmpoff[i][j] = off[i][j];
            if (off[i][j] >= 1 && off[i][j] <= 5)
            {
                cctv ct;
                ct.x = i;
                ct.y = j;
                ct.num = off[i][j];
                ct.max = cc[off[i][j]];
                ct.now = 0;
                clist.push_back(ct);
            }
        }
    }
    // 이 조건 넣으니깐 52퍼에서 틀리던거 맞음
    if (clist.size() == 0)
        minBlind = zero;

    dfs(0, off);

    cout << minBlind << "\n";

    return 0;
}

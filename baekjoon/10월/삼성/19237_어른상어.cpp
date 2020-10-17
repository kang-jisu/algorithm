#include <iostream>
#include <vector>
using namespace std;

// 0으로 가는 경우에 다 일단 옮긴다음(G는 바꾸지않고 shark정보만 바꿔줌)
// 맨 마지막에 이전에 0이었다가 지금 채워져버린게 있으면 걔는 죽는걸로하니깐 됨 먼말인지~

struct shark
{
    int x, y;
    int dir;
    int p[5][5];
    bool die = false;
};
struct info
{
    int s; // shark num
    int k; // k
};
info G[22][22];
vector<shark> slist;
int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};
int n, m, k;

void debug()
{
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cout << "(" << G[i][j].s << "," << G[i][j].k << ") ";
        }
        cout << "\n";
    }
    cout << "\n";
}
void move()
{

    for (int s = 1; s <= m; s++)
    {
        if (!slist[s].die)
        {
            bool any = false;
            int sx = slist[s].x; //이동 전 원래 위치
            int sy = slist[s].y;

            int dir[4]; // 현재 바라보는 방향 우선순위

            // cout << s << "번 상어 우선순위 : ";
            for (int i = 0; i < 4; i++)
            {
                dir[i] = slist[s].p[slist[s].dir][i];
                // cout << dir[i] << " ";
            }
            // cout << "\n";

            int A, B;
            for (int d = 0; d < 4; d++)
            {
                A = sx + dx[dir[d]];
                B = sy + dy[dir[d]];
                if (A >= 0 && A < n && B >= 0 && B < n)
                {
                    if (G[A][B].s == 0)
                    {
                        //0인 방향으로 가서 냄새 옮김.
                        // cout << s << "번 상어(" << slist[s].dir << ")" << dir[d] << "방향으로 감"
                        //      << "\n";
                        slist[s].x = A;
                        slist[s].y = B;
                        slist[s].dir = dir[d];
                        any = true;
                        break;
                    }
                }
            }
            if (any == false)
            { // 0을 찾는 우선순위에서 해결 못한경우
                for (int d = 0; d < 4; d++)
                {
                    A = sx + dx[dir[d]];
                    B = sy + dy[dir[d]];
                    if (A >= 0 && A < n && B >= 0 && B < n)
                    {
                        if (G[A][B].s == s)
                        {
                            //자기냄새인 방향으로 가서 냄새 옮김.
                            G[A][B].s = s;
                            G[A][B].k = k + 1;
                            slist[s].x = A;
                            slist[s].y = B;
                            slist[s].dir = dir[d];
                            any = true;
                            break;
                        }
                    }
                }
            }
        }
    }
    G[slist[1].x][slist[1].y].s = 1;
    G[slist[1].x][slist[1].y].k = k + 1;
    for (int i = 2; i <= m; i++)
    {
        int sx = slist[i].x;
        int sy = slist[i].y;
        if (!slist[i].die)
        {
            if (G[sx][sy].s != 0 && G[sx][sy].s != i)
            {
                // cout << i << "죽음" << G[slist[i].x][slist[i].y].s << "여서\n";
                slist[i].die = true;
            }
            else
            {
                G[sx][sy].s = i;
                G[sx][sy].k = k + 1;
            }
        }
    }

    // 상어 냄새 날라가는중
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (G[i][j].s != 0)
            {
                G[i][j].k--;
                if (G[i][j].k == 0)
                {
                    G[i][j].s = 0;
                }
            }
        }
    }

    // debug();
}
int main()
{
    cin >> n >> m >> k;
    slist.resize(1001);
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            int x;
            cin >> x;
            if (x == 0)
            {
                G[i][j].s = 0;
            }
            else
            {
                G[i][j].s = x;
                G[i][j].k = k;
                slist[x].x = i;
                slist[x].y = j;
            }
        }
    }
    for (int i = 1; i <= m; i++)
    {
        cin >> slist[i].dir;
        slist[i].dir--;
    }
    for (int i = 1; i <= m; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            for (int k = 0; k < 4; k++)
            {
                cin >> slist[i].p[j][k];
                slist[i].p[j][k]--;
            }
        }
    }

    int scnt = 0;
    while (scnt < 1001)
    {
        scnt++;
        move();
        int otherfish = false;
        for (int i = 2; i <= m; i++)
        {
            if (slist[i].die == false)
            {
                otherfish = true;
                break;
            }
        }
        if (otherfish == false)
            break;
    }
    if (scnt > 1000)
        cout << "-1\n";
    else
        cout << scnt << "\n";

    return 0;
}
#include <iostream>
#include <algorithm>
#include <queue>
using namespace std;

// 국경선이라는거를 따로 값으로 두지 않아도 그 때 l, r값 확인해서 열려있는지 보면됨.
// 국경선 초기화해주는시간땜에 시간초과났었음 ㅠ
// 그리고 l,r검색해서 국경선 바뀌는 경우에 그 관련된 도시만 union에 저장해서 확인
// 그거할때 n^2 탐색하는거보다 union만큼만 확인하는게 빠름~

int dx[] = {-1, 1, 0, 0};
int dy[] = {0, 0, -1, 1};

int visit[51][51]; // 인구이동할 때 확인한 나라인지
int pop[51][51];   // 인구수

int n, l, r;

queue<pair<int, int>> change;
queue<pair<int, int>> _union;
int unionsum = 0;
void bfs(int i, int j)
{
    queue<pair<int, int>> q;
    q.push({i, j});
    while (!q.empty())
    {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();
        // cout << x << "," << y << "\n";

        _union.push({x, y});
        unionsum += pop[x][y];
        for (int d = 0; d < 4; d++)
        {
            int A = x + dx[d];
            int B = y + dy[d];
            if (A >= 0 && A < n && B >= 0 && B < n && visit[A][B] == 0)
            {
                int diff = abs(pop[x][y] - pop[A][B]);
                if (diff >= l && diff <= r)
                {
                    visit[A][B] = 1;
                    q.push({A, B});
                }
            }
        }
    }
}
bool move()
{
    int ismove = false;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            visit[i][j] = 0;
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            bool tf = false;
            int A, B;
            for (int dir = 0; dir < 4; dir++)
            {
                A = i + dx[dir];
                B = j + dy[dir];
                if (A >= 0 && A < n && B >= 0 && B < n)
                {
                    int diff = abs(pop[i][j] - pop[A][B]);
                    if (diff >= l && diff <= r)
                    {
                        tf = true;
                        ismove = true;
                    }
                }
            }
            if (tf)
            {
                change.push({i, j});
            }
        }
    }
    //bfs

    while (!change.empty())
    {
        int cx = change.front().first;
        int cy = change.front().second;
        change.pop();
        if (visit[cx][cy] == 0)
        {
            visit[cx][cy] = 1;
            // cout << "----\n";
            unionsum = 0;
            bfs(cx, cy);
            int newpop = unionsum / _union.size();
            while (!_union.empty())
            {
                int x = _union.front().first;
                int y = _union.front().second;
                _union.pop();
                pop[x][y] = newpop;
            }
            //이때 union에 있는거 더해서
        }
    }

    return ismove;
}
int main()
{

    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n >> l >> r;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> pop[i][j];
        }
    }
    int cnt = 0;
    while (1)
    {
        if (!move())
            break;
        cnt++;
    }
    cout << cnt << "\n";
    return 0;
}
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

// 틀린이유 1. list 정렬후 index0번이 아니라 벽에 막혀서 갈수 없는게 아닌거중에 최소를 찾아야함
// 틀린이유 2. ssize 가 9가되면 visit값 상어도 0으로 적용돼서 bfs 거리재는데 오류가있었음. ㅠ visit[shark.x][shark.y]=1해줬음
struct pt
{
    int x, y;
    int sdis = 0;
    pt() {}
    pt(int a, int b) : x(a), y(b) {}
};

vector<pt> list;

pt shark;
int space[21][21];
int visit[21][21];
int n;
int ssize = 2; // sharksize
int cnt = 0;
int stime = 0;

int dx[] = {-1, 1, 0, 0}; // 상하좌우
int dy[] = {0, 0, -1, 1};

// 거리가 가장 작고, 위에있고, 왼쪽에있는 순으로 정렬
bool comp(pt a, pt b)
{
    if (a.sdis == b.sdis)
    {
        if (a.x == b.x)
        {
            return a.y < b.y;
        }
        else
            return a.x < b.x;
    }
    else
        return a.sdis < b.sdis;
}

void bfs(int idx, int a, int b)
{
    int ar[21][21];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            ar[i][j] = visit[i][j];
        }
    }
    queue<pair<int, int>> q;
    q.push({shark.x, shark.y});
    // cout << "bfs:" << i << "," << j << "\n";
    while (!q.empty())
    {
        int x, y, A, B;
        x = q.front().first;
        y = q.front().second;
        q.pop();
        if (x == a && y == b)
        {
            break;
        }
        for (int i = 0; i < 4; i++)
        {
            A = x + dx[i];
            B = y + dy[i];
            if (A >= 0 && A < n && B >= 0 && B < n && ar[A][B] == 0)
            {
                ar[A][B] = ar[x][y] + 1;
                q.push({A, B});
            }
        }
    }
    // bfs로 a,b위치에 갈때까지 걸리는 거리 계산
    list[idx].sdis = ar[a][b] - 1;
}
int main()
{

    cin >> n;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> space[i][j];
            if (space[i][j] >= 1 && space[i][j] <= 6)
            {
                if (space[i][j] < 2)
                    list.push_back(pt(i, j));
            }
            if (space[i][j] == 9)
                shark = pt(i, j);
        }
    }

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            if (space[i][j] <= ssize)
            {
                visit[i][j] = 0; // 갈 수 있음
            }
            else
            {
                visit[i][j] = 1;
            }
        }
    }

    while (list.size() != 0)
    {
        //list 정렬을 위해 거리 계산
        for (int i = 0; i < list.size(); i++)
        {
            bfs(i, list[i].x, list[i].y);
        }

        // list 정렬
        sort(list.begin(), list.end(), comp);

        // 후보 list는 있는데 모두 큰 크기물고기에 막혀서 못갈경우->-1 예외처리해줌
        pt target = pt(-1, -1);
        for (int i = 0; i < list.size(); i++)
        {
            if (list[i].sdis != -1)
            {
                target = list[i];
                break;
            }
        }
        if (target.x == -1)
            break;

        space[shark.x][shark.y] = 0; // 상어 원래 자리 비움
        shark.x = target.x;
        shark.y =
            target.y;
        space[target.x][target.y] = 9; // 잡아먹힌 자리 상어자리로
        stime++;
        cnt += target.sdis;

        if (stime == ssize)
        {
            ssize++;
            stime = 0;
        }

        list.clear();
        // 물고기중에 size 이하이면 갈 수 있으므로 visit =0
        // 아니거나 상어 위치이면 visit=1
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (space[i][j] <= ssize)
                {
                    if (space[i][j] >= 1 && space[i][j] <= 6 && space[i][j] < ssize)
                    {
                        list.push_back(pt(i, j));
                    }
                    visit[i][j] = 0;
                }
                else
                {
                    visit[i][j] = 1;
                }
            }
        }
        visit[shark.x][shark.y] = 1;
    }
    cout << cnt << "\n";

    return 0;
}
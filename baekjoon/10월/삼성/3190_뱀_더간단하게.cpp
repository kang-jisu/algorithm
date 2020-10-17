#include <iostream>
#include <queue>
#include <vector>
using namespace std;

// 사과면 뱀 길이 추가
// 아니면 뱀 길이 추가, 꼬리 짜름 
// 뱀 있는게 map=2인거로 .. 와웅 ㅠㅠ

int n, k, l;
int apple[101][101];
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};
queue<pair<int, int>> snake;
queue<pair<int, int>> q;
int head_x, head_y; // 뱀 머리 현재 위치
int cur_dir;
int tcnt;
int main()
{
    cin >> n >> k;
    for (int i = 0; i < k; i++)
    {
        int a, b;
        cin >> a >> b;
        apple[a][b] = 1;
    }
    cin >> l;
    for (int i = 0; i < l; i++)
    {
        int num;
        char info;
        cin >> num >> info;
        if (info == 'D')
        {
            q.push({num, 1});
        }
        else
        {
            q.push({num, -1});
        }
    }

    snake.push({1, 1});
    head_x = 1;
    head_y = 1;
    apple[1][1] = 2;
    while (tcnt <= 10000)
    {
        pair<int, int> top = q.front();
        if (top.first == tcnt)
        {
            // X초 지난 뒤 방향으로 회전 정보 적용
            cur_dir = (cur_dir + top.second);
            if (cur_dir == 4)
                cur_dir = 0;
            else if (cur_dir == -1)
                cur_dir = 3;
            q.pop();
        }
        tcnt++;

        int next_x = head_x + dx[cur_dir];
        int next_y = head_y + dy[cur_dir];

        if (next_x > 0 && next_x <= n && next_y > 0 && next_y <= n && apple[next_x][next_y] != 2)
        {
            if (apple[next_x][next_y] == 1)
            {
                snake.push({next_x, next_y});
            }
            else // 사과없을때
            {
                snake.push({next_x, next_y});
                int hx = snake.front().first;
                int hy = snake.front().second;
                apple[hx][hy] = 0;
                if (snake.size() > 1)
                    snake.pop();
            }
            apple[next_x][next_y] = 2;
            head_x = next_x;
            head_y = next_y;
        }
        else
        {
            break; // 벽이나 자기한테 닿았음
        }
    }
    cout << tcnt << "\n";
    return 0;
}
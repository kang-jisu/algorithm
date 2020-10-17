#include <iostream>
#include <queue>
#include <vector>
using namespace std;

// 사과면 trace에 next만 추가해주고
// 사과가 아니면 지금꺼 복사,그다음 하나씩 shift해서 복제(길이 제한걸리면 짤리게)해서 옮겨주면됨
// 틀렸던 이유는 cur_dir 0,1,2,3하는데있어서 문제있었음 ㅠ -1%4 = -1 로  된듯.

int n, k, l;
int apple[101][101];
int trace[101][101];
//L : d[(dir-1)%4]
//D : d[(dir+1)%4]
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};
vector<vector<int>> snake(10001);
queue<pair<int, int>> q;
int head_x, head_y; // 뱀 머리 현재 위치
int tail_x, tail_y;
int cur_dir;
int tcnt;
int curlen = 0;
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
    head_x = 1;
    head_y = 1;
    tail_x = 1;
    tail_y = 1;
    trace[1][1] = 1;
    snake[0].push_back(0);
    curlen = 1;
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

        // cout << tcnt << " : " << next_x << "," << next_y << " | " << curlen << " / curdir:" << cur_dir << "\n";
        // for (int i = 1; i <= n; i++)
        // {
        //     for (int j = 1; j <= n; j++)
        //     {
        //         if (apple[i][j] == 1)
        //             cout << "2 ";
        //         else
        //             cout << trace[i][j] << " ";
        //     }
        //     cout << "\n";
        // }

        if (next_x > 0 && next_x <= n && next_y > 0 && next_y <= n && trace[next_x][next_y] == 0)
        {
            if (apple[next_x][next_y] == 1)
            {
                // cout << "사과!\n";
                //사과 있으면 꼬리 증가
                curlen++;
                apple[next_x][next_y] = 0;
                snake[tcnt].push_back(cur_dir);

                for (int i = 0; i < curlen - 1; i++)
                {
                    snake[tcnt].push_back(snake[tcnt - 1][i]);
                }

                // cout << "\ncnt: " << tcnt << " : ";
                // for (int i = 0; i < curlen; i++)
                // {
                //     cout << snake[tcnt][i] << " ";
                // }
                // cout << "\norigintrcace\n";
                // for (int i = 1; i <= n; i++)
                // {
                //     for (int j = 1; j <= n; j++)
                //     {
                //         cout << trace[i][j] << " ";
                //     }
                //     cout << "\n";
                // }
                trace[next_x][next_y] = 1;
                head_x = next_x;
                head_y = next_y;
            }
            else // 사과없을때
            {
                snake[tcnt].push_back(cur_dir);
                for (int i = 0; i < curlen - 1; i++)
                {
                    snake[tcnt].push_back(snake[tcnt - 1][i]);
                }
                // cout << "\ncnt: " << tcnt << " : ";
                // for (int i = 0; i < curlen; i++)
                // {
                //     cout << snake[tcnt][i] << " ";
                // }
                // cout << "\norigintrcace\n";
                for (int i = 1; i <= n; i++)
                {
                    for (int j = 1; j <= n; j++)
                    {
                        // cout << trace[i][j] << " ";
                        trace[i][j] = 0;
                    }
                    // cout << "\n";
                }
                int tmptx = tail_x;
                int tmpty = tail_y;
                for (int i = curlen - 1; i >= 0; i--)
                {
                    tmptx += dx[snake[tcnt][i]];
                    tmpty += dy[snake[tcnt][i]];
                    trace[tmptx][tmpty] = 1;
                    if (i == curlen - 1)
                    {
                        tail_x = tmptx;
                        tail_y = tmpty;
                    }
                }
                head_x = tmptx;
                head_y = tmpty;
                // trace[head_x][head_y] = 1;
            }
        }
        else
        {
            // cout << tcnt << "\n";
            // if (trace[next_x][next_y] == 1)
            //     cout << "나랑 만남\n";
            // else
            //     cout << "벽에 닿음\n";
            break; // 벽이나 자기한테 닿았음
        }
    }
    cout << tcnt << "\n";
    return 0;
}
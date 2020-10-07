#include <iostream>
#include <vector>
using namespace std;

struct FISH
{
    bool alive;
    int x;
    int y;
    int num;
    int dir;
};
int a[4][4];
FISH list[17];
int dx[9] = {0, -1, -1, 0, 1, 1, 1, 0, -1};
int dy[9] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
int maxSum = 0;

void moveFish()
{
    for (int i = 1; i <= 16; i++)
    {
        if (list[i].alive)
        {
            FISH f = list[i];
            int dir = f.dir;
            int t = 8;
            int A, B;
            while (t--)
            {
                A = f.x + dx[dir];
                B = f.y + dy[dir];
                if (A >= 0 && A < 4 && B >= 0 && B < 4)
                {
                    if (a[A][B] == -1)
                    { // 빈칸이면 옮기기만해줌
                        a[A][B] = f.num;
                        list[i].x = A;
                        list[i].y = B;
                        list[i].dir = dir;
                        a[f.x][f.y] = -1;
                        break;
                    }
                    else if (a[A][B] != 0)
                    { // 상어가 아니면 swap
                        int tmp = a[A][B];
                        a[A][B] = a[f.x][f.y];
                        a[f.x][f.y] = tmp;

                        list[i].x = A;
                        list[i].y = B;
                        list[i].dir = dir;
                        list[tmp].x = f.x;
                        list[tmp].y = f.y;

                        break;
                    }
                    else
                    { // 상어
                        dir++;
                        if (dir == 9)
                            dir = 1;
                    }
                }
                else
                {
                    dir++;
                    if (dir == 9)
                        dir = 1;
                }
            }
        }
    }
}
void DFS(int x, int y, int dir, int sum)
{

    if (maxSum < sum)
        maxSum = sum;
    moveFish();
    a[x][y] = -1;
    int copyA[4][4];
    FISH copyList[17];

    // 원본 복사해놓음
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            copyA[i][j] = a[i][j];
        }
    }
    for (int i = 1; i <= 16; i++)
    {
        copyList[i] = list[i];
    }

    for (int t = 1; t <= 3; t++)
    {

        int nx = x + dx[dir] * t;
        int ny = y + dy[dir] * t;
        if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4)
        {
            if (a[nx][ny] > 0)
            { // 잡아먹을 물고기가 있으면

                int fish = a[nx][ny];
                a[nx][ny] = 0; // 현재 상어
                list[fish].alive = false;

                DFS(nx, ny, list[fish].dir, sum + list[fish].num);
                // 다시 원래대로

                for (int i = 0; i < 4; i++)
                {
                    for (int j = 0; j < 4; j++)
                    {
                        a[i][j] = copyA[i][j];
                    }
                }
                for (int i = 1; i <= 16; i++)
                {
                    list[i] = copyList[i];
                }
            }
        }
        else
            break;
    }
}
int main()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            FISH fish;
            fish.alive = true;
            fish.x = i;
            fish.y = j;
            cin >> fish.num >> fish.dir;
            list[fish.num] = fish;
            a[i][j] = fish.num;
        }
    }

    int num = a[0][0];
    int dir = list[num].dir;
    list[num].alive = false;

    a[0][0] = 0; // 잡아먹힘
    DFS(0, 0, dir, num);

    cout << maxSum << "\n";

    return 0;
}
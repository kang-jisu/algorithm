#include <iostream>
#include <vector>
#include <stack>
using namespace std;

#define MAXCNT 1000

// 조건에 맞게 하나씩 짜긴 했는데 다음에 이해할수있을지 모를코드 . ..
// 다시 맞출수 있으려나 ㅠㅠ

stack<int> redstack;
int dx[] = {0, 0, -1, 1};
int dy[] = {1, -1, 0, 0};
int floor_size;
int back[] = {1, 0, 3, 2};
int n, k;
bool finish = false;
struct p
{
    int x, y;
    int dir;
    int top = -1;
    int bot = -1;
};

struct space
{
    int c;
    int top = -1;
};

space board[14][14];
p list[11];
int cnt;
void showP()
{
    cout << "\n";
    for (int i = 1; i <= k; i++)
    {
        cout << i << "(" << list[i].top << "," << list[i].bot << ")\n ";
    }
    cout << "\n";
}
void showBoard()
{
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cout << board[i][j].top << " ";
        }
        cout << "\n";
    }
}

void white(int i, int a, int b, int oa, int ob)
{
    //흰색

    int top = i;
    while (top != -1)
    {
        // cout << top << "번 말 같이 옮겨줘야함\n";
        list[top].x = a;
        list[top].y = b;
        top = list[top].top;
    }
    int tmptop = board[oa][ob].top;
    board[oa][ob].top = list[i].bot; // 옮기지 않은 애로top 바꿔줌
    list[board[oa][ob].top].top = -1;
    list[i].bot = board[a][b].top; // 밑에있다고 지정해줌
    if (board[a][b].top != -1)
    { // 말이 있음
        // cout << board[a][b].top << "말 있음\n";
        list[board[a][b].top].top = i;
    }

    board[a][b].top = tmptop;

    floor_size = 0;
    int tmpbot = tmptop;
    while (tmpbot != -1)
    {
        floor_size++;
        tmpbot = list[tmpbot].bot;
    }
    // cout << "floor_size:" << floor_size << "\n";
}
void red(int i, int a, int b, int oa, int ob)
{
    //빨간색

    int oriTop = board[oa][ob].top; // 원래 top->얘가옮기면 bot으로
    int moveTop =

        board[a][b].top; // 옮기려고 하는 위치의 top -1일수도.

    // 해줘야하는거
    // 옮기기 전에 i 밑에있는애를 top으로 바꿔주고 i밑에 그 top이 top을 -1로 가리키게하기

    list[list[i].bot].top = -1;
    board[oa][ob].top = list[i].bot;
    list[i].bot = -1;

    // 옮기려고하는거 list[i]부터 얘의 top까지를 일단 출력
    int tmp = i;
    while (tmp != -1)
    {
        redstack.push(tmp);
        tmp = list[tmp].top;
    }

    // 순서 반대로 바꿔줌
    while (!redstack.empty())
    {
        tmp = redstack.top();
        int swap = list[tmp].top;
        list[tmp].top = list[tmp].bot;
        list[tmp].bot = swap;
        list[tmp].x = a;
        list[tmp].y = b;
        // cout << tmp << "(" << list[tmp].top << "," << list[tmp].bot << ") ";
        redstack.pop();
    }
    // cout << "\n";

    // top , bot 설정
    list[oriTop].bot = moveTop;
    if (moveTop != -1)
        list[moveTop].top = oriTop;
    board[a][b].top = i;

    floor_size = 0;
    int tmpbot = i;
    while (tmpbot != -1)
    {
        floor_size++;
        tmpbot = list[tmpbot].bot;
    }
    // cout << "floor_size:" << floor_size << "\n";
}
void blue(int i, int oa, int ob)
{
    int bd = back[list[i].dir]; // 방향으로 가볼것
    list[i].dir = bd;
    int a = list[i].x + dx[bd];
    int b = list[i].y + dy[bd];
    if (a >= 1 && a <= n && b >= 1 && b <= n)
    {
        if (board[a][b].c == 2)
            return;
        else if (board[a][b].c == 0)
        {
            white(i, a, b, oa, ob);
        }
        else if (board[a][b].c == 1)
        {
            red(i, a, b, oa, ob);
        }
    }
    else
        return;
}

// 한 턴 플레이
void play()
{
    // maxcnt=1000넘어가면 그냥 종료
    if (cnt > MAXCNT)
    {
        return;
    }
    for (int i = 1; i <= k; i++)
    {
        // cout << i << "번 말의 위치와 방향: " << list[i].x << "," << list[i].y << "  | " << list[i].dir << "\n";
        int a, b;
        a = list[i].x + dx[list[i].dir];
        b = list[i].y + dy[list[i].dir];
        int oa = list[i].x;
        int ob = list[i].y;
        if (a >= 1 && a <= n && b >= 1 && b <= n)
        {
            if (board[a][b].c == 0)
            {
                white(i, a, b, oa, ob);
            }
            else if (board[a][b].c == 1)
            {
                red(i, a, b, oa, ob);
            }
            else
            {
                //파란색이면
                blue(i, oa, ob);
            }
        }
        else
        {
            //벽
            blue(i, oa, ob);
        }
        // showBoard();
        // showP();
        if (floor_size >= 4)
        {
            finish = true;
            break;
        }
    }
}
int main()
{
    cin >> n >> k;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= n; j++)
        {
            cin >> board[i][j].c;
        }
    }
    for (int i = 1; i <= k; i++)
    {
        p P;
        cin >> P.x >> P.y >> P.dir;
        P.dir--;
        list[i] = P;
        board[P.x][P.y].top = i;
    }

    // showBoard();
    while (cnt <= MAXCNT)
    {
        cnt++;
        play();
        if (finish)
            break;
    }

    if (cnt > MAXCNT)
        cnt = -1;
    cout << cnt << "\n";
    return 0;
}
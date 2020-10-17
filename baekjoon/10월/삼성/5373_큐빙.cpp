#include <iostream>
using namespace std;

//일단..포기₩ㅠ
//1:위 2:앞 3:오 4:뒤 5:옆  6:밑
char cube[7][3][3];
char color[] = {'-', 'w', 'r', 'b', 'o', 'g', 'y'};
char backChar[] = {'-', 'U', 'B', 'L', 'F', 'R', 'D'};
void init()
{
    for (int q = 1; q <= 6; q++)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cube[q][i][j] = color[q];
            }
        }
    }
}
int getNum(char a)
{
    int what = 1;
    if (a == 'F')
        what = 2;
    else if (a == 'R')
        what = 3;
    else if (a == 'B')
        what = 4;
    else if (a == 'L')
        what = 5;
    else if (a == 'D')
        what = 6;
    return what;
}
void debug()
{
    cout << "--\n";

    for (int i = 0; i < 3; i++)
    {
        for (int q = 1; q <= 6; q++)
        {
            for (int j = 0; j < 3; j++)
            {
                cout << cube[q][i][j];
            }
            cout << " ";
        }
        cout << "\n";
    }
    cout << "\n";
}
void rotate(char to, char dir)
{
    char tmp[7][3][3];
    for (int q = 1; q <= 6; q++)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                tmp[q][i][j] = cube[q][i][j];
            }
        }
    }
    if (to == 'F')
    {
        for (int i = 0; i < 3; i++)
        {
            cube[1][2][i] = tmp[5][i][2];
            cube[5][i][2] = tmp[6][2][i];
            cube[6][2][i] = tmp[3][i][2];
            cube[3][i][2] = tmp[1][2][i];
        }
    }
    else if (to == 'R')
    {
        for (int i = 0; i < 3; i++)
        {
            cube[1][i][2] = tmp[4][i][2];
            cube[4][i][2] = tmp[6][i][2];
            cube[6][i][2] = tmp[2][i][2];
            cube[2][i][2] = tmp[1][i][2];
        }
    }
    else if (to == 'B')
    {
        for (int i = 0; i < 3; i++)
        {
            cube[1][0][i] = tmp[3][i][0];
            cube[3][i][0] = tmp[6][0][i];
            cube[6][0][i] = tmp[5][i][0];
            cube[5][i][0] = tmp[1][0][i];
        }
    }
    else if (to == 'L')
    {
        for (int i = 0; i < 3; i++)
        {
            cube[2][i][0] = tmp[1][i][0];
            cube[6][i][0] = tmp[2][i][0];
            cube[4][i][0] = tmp[6][i][0];
            cube[1][i][0] = tmp[4][i][0];
        }
    }
    else if (to == 'D')
    {
        for (int i = 0; i < 3; i++)
        {
            cube[2][2][i] = tmp[3][2][i];
            cube[3][2][i] = tmp[4][2][i];
            cube[4][2][i] = tmp[5][2][i];
            cube[5][2][i] = tmp[2][2][i];
        }
    }
    else if (to == 'U')
    {
        for (int i = 0; i < 3; i++)
        {
            cube[2][0][i] = tmp[3][0][i];
            cube[3][0][i] = tmp[4][0][i];
            cube[4][0][i] = tmp[5][0][i];
            cube[5][0][i] = tmp[2][0][i];
        }
    }
}
void copyq(int t, int f, char tmp[][3][3])
{
    // cout << "-t-\n";

    // for (int i = 0; i < 3; i++)
    // {
    //     for (int q = 1; q <= 6; q++)
    //     {
    //         for (int j = 0; j < 3; j++)
    //         {
    //             cout << tmp[q][i][j];
    //         }
    //         cout << " ";
    //     }
    //     cout << "\n";
    // }
    // cout << "\n";
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cube[t][i][j] = tmp[f][i][j];
        }
    }
}

void move(char too, char dir)
{
    char copyt[3][3];
    int to = getNum(too);
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            copyt[i][j] = cube[to][i][j];
        }
    }

    if (dir == '+')
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cube[to][j][2 - i] = copyt[i][j];
            }
        }
    }
    else
    {

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cube[to][2 - j][i] = copyt[i][j];
            }
        }
    }
}
int main()
{
    int n;
    cin >> n;

    for (int N = 0; N < n; N++)
    {
        int t;
        cin >> t;
        char a, b;
        init();
        for (int T = 0; T < t; T++)
        {
            cin >> a >> b;
            move(a, b);
            if (b == '+')
                rotate(a, b);
            else
            {
                rotate(a, b);
                rotate(a, b);
                rotate(a, b);
            }
        }
    }
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            cout << cube[1][i][j];
        }
        cout << "\n";
    }

    return 0;
}
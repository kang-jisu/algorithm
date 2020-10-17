#include <iostream>
using namespace std;

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
void rotate(char dir)
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
    if (dir == '+')
    { //시계방향
        cout << "rotate+\n";
        // 1번면은 시계방향
        // 3->4->5->2
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cube[1][j][2 - i] = tmp[1][i][j];
            }
        }
        for (int q = 2; q <= 4; q++)
        {
            for (int i = 0; i < 3; i++)
            {
                cube[q][0][i] = tmp[q + 1][0][i];
            }
        }
        for (int i = 0; i < 3; i++)
        {
            cube[5][0][i] = tmp[2][0][i];
        }
    }
    else
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cube[1][2 - j][i] = tmp[1][i][j];
            }
        }
        for (int q = 2; q <= 4; q++)
        {
            for (int i = 0; i < 3; i++)
            {
                cube[q + 1][0][i] = tmp[q][0][i];
            }
        }
        for (int i = 0; i < 3; i++)
        {
            cube[2][0][i] = tmp[5][0][i];
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

void move(char to)
{
    char copyt[7][3][3];
    for (int q = 1; q <= 6; q++)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                copyt[q][i][j] = cube[q][i][j];
            }
        }
    }
    if (to == 'U')
        return;
    else if (to == 'F')
    {
        copyq(1, 2, copyt);
        copyq(2, 6, copyt);
        copyq(4, 1, copyt);
        copyq(6, 4, copyt);
    }
    else if (to == 'R')
    {
        copyq(1, 3, copyt);
        copyq(3, 6, copyt);
        copyq(5, 1, copyt);
        copyq(6, 5, copyt);
    }
    else if (to == 'B')
    {
        copyq(1, 4, copyt);
        copyq(2, 1, copyt);
        copyq(4, 6, copyt);
        copyq(6, 2, copyt);
    }
    else if (to == 'L')
    {
        copyq(1, 5, copyt);
        copyq(3, 1, copyt);
        copyq(5, 6, copyt);
        copyq(6, 3, copyt);
    }
    else if (to == 'D')
    {
        copyq(1, 6, copyt);
        copyq(2, 4, copyt);
        copyq(4, 2, copyt);
        copyq(6, 1, copyt);
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
            move(a);
            debug();
            rotate(b);
            debug();
            move(backChar[getNum(a)]);
            debug();
        }

        // debug();
        // int what = 1;
        // if (a == 'F')
        //     what = 2;
        // else if (a == 'R')
        //     what = 3;
        // else if (a == 'B')
        //     what = 4;
        // else if (a == 'L')
        //     what = 5;
        // else if (a == 'D')
        //     what = 6;
        // for (int i = 0; i < 3; i++)
        // {
        //     for (int j = 0; j < 3; j++)
        //     {
        //         cout << cube[what][i][j];
        //     }
        //     cout << "\n";
        // }
    }

    return 0;
}
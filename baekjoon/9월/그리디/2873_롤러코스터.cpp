#include <iostream>

using namespace std;

// 못하겠다..
//아직못품 ㅠ
int main()
{

    int happy[1000][1000];
    int r, c;
    cin >> r >> c;
    for (int i = 0; i < r; i++)
    {
        for (int j = 0; j < c; j++)
        {
            cin >> happy[i][j];
        }
    }

    if (r % 2 == 1)
    {
        for (int i = 0; i < r; i++)
        {
            if (i % 2 == 0)
            {
                int ccnt = c - 1;
                while (ccnt--)
                    cout << "R";
            }
            else
            {
                int ccnt = c - 1;
                while (ccnt--)
                    cout << "L";
            }
            if (i != r - 1)
                cout << "D";
        }
    }
    else if (c % 2 == 1)
    {
        for (int i = 0; i < c; i++)
        {
            if (i % 2 == 0)
            {
                int rcnt = r - 1;
                while (rcnt--)
                    cout << "D";
            }
            else
            {
                int rcnt = r - 1;
                while (rcnt--)
                    cout << "U";
            }
            if (i != c - 1)
                cout << "R";
        }
    }
    else
    {
        int mini, minj;
        int minval = 10000000;
        for (int i = 0; i < r; i++)
        {
            for (int j = 1; j < c; j++)
            {
                if ((i + j) % 2 == 1 && happy[i][j] < minval)
                {
                    minval = happy[i][j];
                    mini = i;
                    minj = j;
                }
            }
        }
        for (int i = 0; i < mini - 2; i++)
        {
            if (i % 2 == 0)
            {
                int ccnt = c - 1;
                while (ccnt--)
                    cout << "R";
            }
            else
            {
                int ccnt = c - 1;
                while (ccnt--)
                    cout << "L";
            }
            cout << "D";
        }
        for (int i = 0; i < minj - 1; i++)
        {
            if (i % 2 == 0)
            {
                cout << "DR";
            }
            else
            {
                cout << "UR";
            }
        }
        for (int i = minj - 1; i <= minj + 1; i++)
        {
            if (i == minj - 1)
                cout << "DR";
            if (i == minj)
                cout << "R";
            if (i == minj + 1)
                cout << "UR";
        }
        for (int i = minj + 2; i < c; i++)
        {
            if (i != c - 1)
            {
                if (i % 2 == 0)
                {
                    cout << "DR";
                }
                else
                {
                    cout << "UR";
                }
            }
            else
            {
                cout << "DD";
            }
        }
        int start = mini % 2 == 0 ? mini + 2 : mini + 1;
        for (int i = start; i < r; i++)
        {
            if (i % 2 == 0)
            {
                int ccnt = c - 1;
                while (ccnt--)
                    cout << "L";
            }
            else
            {
                int ccnt = c - 1;
                while (ccnt--)
                    cout << "R";
            }
            if (i != r - 1)
                cout << "D";
        }
    }

    return 0;
}
#include <string>
#include <iostream>
#include <vector>

    using namespace std;

void rotate(vector<vector<int>> &v)
{
    int len = v.size();
    vector<vector<int>> nv;
    nv.resize(len);
    for (int i = 0; i < len; i++)
    {
        nv[i].resize(len);
    }
    for (int i = 0; i < len; i++)
    {
        for (int j = 0; j < len; j++)
        {
            nv[j][len - i - 1] = v[i][j];
        }
    }

    v = nv;
}

bool check(int x, int y, vector<vector<int>> tar, vector<vector<int>> v, int vlen, int tlen)
{
    for (int i = 0; i < vlen; i++)
    {
        for (int j = 0; j < vlen; j++)
        {
            if (v[i][j] == 1)
            {
                if (tar[x + i][y + j] == 1)
                    return false;
                tar[x + i][y + j] = 1;
            }
        }
    }

    for (int i = 0; i < tlen; i++)
    {
        for (int j = 0; j < tlen; j++)
        {
            if (tar[tlen + i][tlen + j] == 0)
                return false;
        }
    }
    return true;
}
bool solution(vector<vector<int>> key, vector<vector<int>> lock)
{
    bool answer = true;
    vector<vector<int>> extend;
    int len = lock.size();
    extend.resize(len * 3);
    for (int i = 0; i < len * 3; i++)
    {
        extend[i].resize(len * 3);
    }
    for (int i = 0; i < 3 * len; i++)
    {
        for (int j = 0; j < 3 * len; j++)
        {
            extend[i][j] = -1;
        }
    }

    for (int i = 0; i < len; i++)
    {
        for (int j = 0; j < len; j++)
        {
            extend[len + i][len + j] = lock[i][j];
        }
    }

    answer = false;
    for (int i = 0; i < 4; i++)
    {
        if (i != 0)
            rotate(key);
        for (int x = 1; x < 2 * len; x++)
        {
            for (int y = 1; y < 2 * len; y++)
            {
                if (check(x, y, extend, key, key.size(), len))
                {
                    answer = true;
                    break;
                }
            }
            if (answer == true)
            {
                break;
            }
        }
        if (answer == true)
        {
            break;
        }
    }
    return answer;
}

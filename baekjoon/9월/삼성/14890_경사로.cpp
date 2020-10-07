#include <iostream>
using namespace std;

int arr[100][100];
int main()
{

    int n, l;
    cin >> n >> l;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            cin >> arr[i][j];
        }
    }

    int load = 0;
    int curlen = 1;

    //가로
    for (int i = 0; i < n; i++)
    {
        bool able = true;
        curlen = 1;
        for (int j = 0; j < n - 1; j++)
        {
            int cur = arr[i][j];
            int next = arr[i][j + 1];

            // 1칸 이상 차이날경우
            if (cur - next > 1 || next - cur > 1)
            {
                // cout << "가로 1칸이상 차이 i:" << i << "\n";
                able = false;
                break;
            }
            // 같으면 유지
            if (cur == next)
            {
                curlen++;
                continue;
            }
            else if (next - cur == 1)
            {
                if (l <= curlen)
                { // 경사로 놓을 수 있음
                    curlen = 1;
                    continue;
                }
                else
                { // 경사로 놓을 수 없음
                    // cout << curlen << "\n";
                    // cout << "가로 경사로 놓을 수 없음 i:" << i << "\n";
                    able = false;
                    break;
                }
            }
            else if (cur - next == 1)
            {
                // i+1 이 -1 이면
                // L만큼 -1인지 보고 아니면 false
                int cnt = 0;
                if (j + l > n - 1)
                { // 길이 끝나서 경사로 못놓음

                    // cout << "가로 길이 끝남 i:" << i << "\n";
                    able = false;
                    break;
                }

                for (int k = 0; k < l; k++)
                {
                    if (arr[i][j + 1 + k] == next)
                        cnt++;
                    else
                        break;
                }
                if (cnt != l)
                {
                    // cout << "길이안맞음" << i << "\n";
                    able = false;
                    break;
                }
                else
                {
                    // 내려가는 경사로 놓았음
                    //j +l위치에서 검사해봐야함

                    if (j + l == n - 1)
                        break;
                    if (next - arr[i][j + l + 1] > 1 || arr[i][j + l + 1] - next > 1)
                    {

                        // cout << "가로 다음꺼 1칸이상 차이 i:" << i << "\n";
                        able = false;
                        break;
                    }
                    if (arr[i][j + l + 1] == next)
                    {
                        // cout << "dsf\n";
                        curlen = 1;
                        j = j + l;
                        continue;
                    }
                    else if (arr[i][j + l + 1] < next)
                    {
                        // cout << "걸려랴\n";
                        if (arr[i][j + l + 1] + l > n - 1)
                        {
                            // cout << "걸림 i:" << i << "\n";
                            able = false;
                            break;
                        }
                    }
                    else
                    { // 1 더 클때

                        // cout << "가로 다음꺼 더 큼  i:" << i << "\n";
                        able = false;
                        break;
                    }
                }
            }
        }
        if (able)
        {
            // cout << "가로 i:" << i << "\n";
            load++;
        }
    }

    //세로
    for (int j = 0; j < n; j++)
    {
        curlen = 1;
        bool able = true;
        for (int i = 0; i < n - 1; i++)
        {
            int cur = arr[i][j];
            int next = arr[i + 1][j];

            // 1칸 이상 차이날경우
            if (cur - next > 1 || next - cur > 1)
            {
                // cout << "세로 1칸이상 차이 i:" << j << "\n";
                able = false;
                break;
            }
            // 같으면 유지
            if (cur == next)
            {
                curlen++;
                continue;
            }
            else if (next - cur == 1)
            {
                if (l <= curlen)
                { // 경사로 놓을 수 있음
                    curlen = 1;
                    continue;
                }
                else
                { // 경사로 놓을 수 없음
                    // cout << curlen << "\n";
                    // cout << "세로 경사로 놓을 수 없음 i:" << j << "\n";
                    able = false;
                    break;
                }
            }
            else if (cur - next == 1)
            {
                // i+1 이 -1 이면
                // L만큼 -1인지 보고 아니면 false
                int cnt = 0;
                if (i + l > n - 1)
                { // 길이 끝나서 경사로 못놓음

                    // cout << "세로 길이 끝남 i:" << j << "\n";
                    able = false;
                    break;
                }

                for (int k = 0; k < l; k++)
                {
                    if (arr[i + 1 + k][j] == next)
                        cnt++;
                    else
                        break;
                }
                if (cnt != l)
                {
                    // cout << "세로 x " << j << "\n";
                    able = false;
                    break;
                }
                else
                {
                    // 내려가는 경사로 놓았음
                    //j +l위치에서 검사해봐야함
                    if (i + l == n - 1)
                    {
                        // cout << "세로 " << j << "\n";
                        break;
                    }
                    if (next - arr[i + l + 1][j] > 1 || arr[i + l + 1][j] - next > 1)
                    {

                        // cout << "세로 다음꺼 1칸이상 차이 i:" << j << "\n";
                        able = false;
                        break;
                    }
                    if (arr[i + l + 1][j] == next)
                    {
                        curlen = 1;
                        i = i + l;
                        continue;
                    }
                    else if (arr[i + l + 1][j] < next)
                    {
                        if (arr[i + l + 1][j] + l > n - 1)
                        {
                            // cout << "걸림 i:" << i << "\n";
                            able = false;
                            break;
                        }
                    }
                    else
                    { // 1 더 클때

                        // cout << "세로 다음꺼 더 큼  i:" << j << "\n";
                        able = false;
                        break;
                    }
                }
            }
        }
        if (able)
        {
            // cout << "세로 i:" << j << "\n";
            load++;
        }
    }
    cout << load << "\n";
    return 0;
}
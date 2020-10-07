#include <string>
#include <iostream>
#include <vector>

using namespace std;

int solution(string s)
{
    int answer = 999999;
    int len = s.length();
    string arr[1000];

    if (len == 1)
        return 1;

    for (int i = 1; i <= len / 2; i++)
    {
        int cnt = 0;
        string newStr = "";
        int scnt = 1;
        for (int j = 0; j < len; j += i)
        {
            arr[cnt++] = s.substr(j, i);
        }

        for (int j = 1; j < cnt; j++)
        {
            if (arr[j - 1] == arr[j])
            {
                scnt++;
                continue;
            }
            else
            {
                if (scnt == 1)
                    newStr += arr[j - 1];
                else
                {
                    newStr += to_string(scnt) + arr[j - 1];
                    scnt = 1;
                }
            }
        }
        if (scnt == 1)
            newStr += arr[cnt - 1];
        else
        {
            newStr += to_string(scnt) + arr[cnt - 1];
        }
        if (newStr.length() < answer)
            answer = newStr.length();
    }
    return answer;
}

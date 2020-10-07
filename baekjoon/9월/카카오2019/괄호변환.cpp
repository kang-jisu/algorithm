#include <string>
#include <vector>
#include <iostream>
#include <stack>
using namespace std;
bool isRight(string p)
{
    stack<char> st;
    for (int i = 0; i < p.length(); i++)
    {
        if (p[i] == '(')
        {
            st.push('(');
        }
        else
        {
            if (st.empty())
                return false;
            st.pop();
        }
    }
    if (!st.empty())
        return false;
    return true;
}
bool isBalance(string p)
{
    int left = 0;
    int right = 0;
    for (int i = 0; i < p.length(); i++)
    {
        if (p[i] == '(')
            left++;
        else
            right++;
    }
    if (right == left)
        return true;
    else
        return false;
}
bool isAtom(string p)
{
    string u = p;
    string v = "";
    int len = p.length();
    if (len <= 2)
        return true;
    for (int i = 2; i < len - 1; i += 2)
    {
        string front = p.substr(0, i);
        string back = p.substr(i);
        if (isBalance(front) && isBalance(back))
        {
            return false;
        }
    }
    return true;
}

string convert(string p)
{
    if (p == "")
        return ""; //  빈 문자인경우
    if (isRight(p))
        return p; // 이미 균형잡힌 문자열인경우

    string u = p;
    string v = "";
    int len = p.length();
    if (len != 2)
    {
        for (int i = 2; i < len - 1; i += 2)
        {
            string front = p.substr(0, i);
            string back = p.substr(i);
            if (isBalance(front))
            {
                if (isAtom(front))
                {
                    u = front;
                    v = back;
                    break;
                }
                else
                    continue;
            }
        }
    }
    if (isRight(u))
    {
        return u + convert(v);
    }
    else
    {
        int sublen = u.length();
        string subu = "";
        if (sublen != 2)
        {
            for (int i = 1; i < sublen - 1; i++)
            {
                subu += u[i];
            }
            for (int i = 0; i < subu.length(); i++)
            {
                if (subu[i] == '(')
                    subu[i] = ')';
                else
                    subu[i] = '(';
            }
        }
        u = '(' + subu + ')';
        return '(' + convert(v) + ')' + subu;
    }
}

string solution(string p)
{
    string answer = "";
    answer = convert(p);
    cout << answer << "\n";
    return answer;
}
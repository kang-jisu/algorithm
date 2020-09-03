#include <iostream>
#include <string>
using namespace std;

int stack[10001];
int idx = 0;
void push(int num)
{
    stack[idx++] = num;
}
int pop()
{
    if (idx == 0)
        return -1;
    else
        return stack[--idx];
}
int size()
{
    return idx;
}
bool empty()
{
    if (size() == 0)
        return true;
    else
        return false;
}

int top()
{
    if (empty())
        return -1;
    else
        return stack[idx - 1];
}
int main()
{
    int n;
    cin >> n;
    string cmd;
    for (int i = 0; i < n; i++)
    {
        cin >> cmd;
        if (cmd == "push")
        {
            int tmp;
            cin >> tmp;
            push(tmp);
        }
        else if (cmd == "top")
        {
            cout << top() << "\n";
        }
        else if (cmd == "size")
        {

            cout << size() << "\n";
        }
        else if (cmd == "empty")
        {

            cout << empty() << "\n";
            ;
        }
        else if (cmd == "pop")
        {
            cout << pop() << "\n";
        }
    }
    return 0;
}
#include <iostream>
#include <string>
using namespace std;

int queue[10000];
int head = 0;
int tail = 0;
void push_back(int num)
{
    queue[tail] = num;
    tail = (tail + 1) % 10000;
}
void push_front(int num)
{
    if (head == 0)
    {
        head = 9999;
        queue[head] = num;
    }
    else
    {
        head--;
        queue[head] = num;
    }
}
int size()
{
    if (head == tail)
        return 0;
    else
    {
        if (head > tail)
        {
            return 10000 + tail - head;
        }
        else
            return tail - head;
    }
}
int pop_front()
{
    if (size() == 0)
        return -1;
    else
    {
        int tmp = queue[head];

        head = (head + 1) % 10000;
        return tmp;
    }
}
int pop_back()
{
    if (size() == 0)
        return -1;
    else
    {
        if (tail == 0)
        {
            tail = 9999;
            return queue[tail];
        }
        else
        {
            return queue[--tail];
        }
    }
}

bool empty()
{
    if (size() == 0)
        return true;
    else
        return false;
}

int front()
{
    if (empty())
        return -1;
    else
        return queue[head];
}
int back()
{
    if (empty())
        return -1;
    else
    {
        if (tail == 0)
        {
            return queue[9999];
        }
        else
        {
            return queue[tail - 1];
        }
    }
}
int main()
{
    int n;
    cin >> n;
    string cmd;
    for (int i = 0; i < n; i++)
    {
        cin >> cmd;
        if (cmd == "push_back")
        {
            int tmp;
            cin >> tmp;
            push_back(tmp);
        }
        else if (cmd == "push_front")
        {
            int tmp;
            cin >> tmp;
            push_front(tmp);
        }
        else if (cmd == "front")
        {
            cout << front() << "\n";
        }
        else if (cmd == "back")
        {
            cout << back() << "\n";
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
        else if (cmd == "pop_front")
        {
            cout << pop_front() << "\n";
        }
        else if (cmd == "pop_back")
        {
            cout << pop_back() << "\n";
        }
    }
    return 0;
}
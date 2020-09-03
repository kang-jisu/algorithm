#include <iostream>
#include <string>
using namespace std;

int queue[10000];
int head=0;
int tail=0;
void push(int num)
{
   queue[tail]=num;
   tail=(tail+1)%10000;
}
int size()
{
   if(head==tail)return 0;
   else return tail-head;
}
int pop()
{
    if (size()==0)
        return -1;
    else
        return queue[head++];
}

bool empty()
{
   if(size()==0)return true;
   else return false;
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
        return queue[tail-1];
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
        else if (cmd == "pop")
        {
            cout << pop() << "\n";
        }
    }
    return 0;
}
#include <iostream>
#include <stack>
using namespace std;


int main()
{
    
    string str;
    cin >> str;

    stack<char> left;
    stack<char> right;
    for(int i=0; i<str.length(); i++){
        left.push(str[i]);
    }

    int m;
    cin >> m;
    for (int i = 0; i < m; i++)
    {
        char cmd;
        cin >> cmd;
        switch (cmd)
        {
        case 'P':
        {
            char t;
            cin>>t;
            left.push(t);
            break;
        }
        case 'L':
        {
            if(left.empty()) break;
            else {
                char popstr = left.top();
                left.pop();
                right.push(popstr);
            }
            break;
        }
        case 'D':
        {
            if(right.empty()) break;
            else {
                char popstr = right.top();
                right.pop();
                left.push(popstr);
            }
            break;
        }
        case 'B':
        {
            if(left.empty())break;
            else left.pop();
            break;
        }
        default:
            break;
        }
    }
    while(!left.empty()){
        right.push( left.top());
        left.pop();
    }
    while(!right.empty()){
        cout<<right.top();
        right.pop();
    }cout<<"\n";
    return 0;
}
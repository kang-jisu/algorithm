// #include <iostream>
// #include <cmath>
// using namespace std;

// long dp[31];
// int main(){

//     int n;
//     cin>>n;
//     dp[0]=1;
//     dp[2]=3;
//     for(int i=4; i<=n; i+=2){
//         for(int j=2; j<i; j+=2){
//             dp[i]+=dp[j]*dp[i-j];
//         }
//         dp[i]+=2;
//     }
    
//     cout<<dp[n]<<"\n";
//     return 0;
// }

#include <iostream>
using namespace std;

int main() {

	int n;
	cin >> n;
	
	int d[31] = { 0 };
	d[0] = 1;
	d[2] = 3;
	for (int i = 4; i <= n; i += 2) {
		d[i] = d[i - 2] * 3;
		for (int j = i; j >= 4; j -= 2) {
			d[i] += d[i- j] * 2;
		}
	}
	cout << d[n] << "\n";
	return 0;
}

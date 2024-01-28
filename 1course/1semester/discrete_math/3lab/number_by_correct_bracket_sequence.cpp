#include <iostream>
#include <vector>
#define ll long long
using namespace std;
int main() {
    string s;
    cin >> s;
    vector<vector<ll>>dp(s.size() + 10, vector<ll>(s.size() + 10, 0));
    dp[0][0] = 1;
    for (int i = 0; i < s.size() + 2; i++) {
        for (int j = 0; j < s.size() + 2; j++) {
            if (j + 1 <= s.size())
                dp[i + 1][j + 1] += dp[i][j];
            if (j > 0)
                dp[i + 1][j - 1] += dp[i][j];
        }
    }
    ll ans = 0;
    int len = 0;
    for (ll i = 0; i < s.size() - 1; i++) {
        if (s[i] == '(') {
            len++;
        }
        else {
            if (len < s.size() / 2) {
                ans += dp[s.size() - i - 1][len + 1];
            }
            len--;
        }
    }
    cout << ans << endl;
    return 0;
}

#include <iostream>
#include <vector>
using namespace std;
vector<long long>fact(20, 0);
int get_inversions(vector<int>& a, const int& pos) {
    int ans = 0;
    for (int i = pos + 1; i < a.size(); i++) {
        if (a[pos] > a[i]) {
            ans++;
        }
    }
    return ans;
}
long long get_num(const int& n, vector<int>& a) {
    long long ans = 0;
    int pos = 0;
    for (int i = n - 1; i >= 0; i--) {
        ans += fact[i] * get_inversions(a, pos);
        pos++;
    }
    return ans;
}
int main() {
    fact[0] = 0;
    fact[1] = 1;
    for (int i = 2; i < 19; i++) {
        fact[i] = fact[i - 1] * i;
    }
    int n;
    cin >> n;
    vector<int>a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    cout << get_num(n, a);
}

#include <iostream>
#include <vector>
using namespace std;
int main() {
    vector<long long>fact(20, 0);
    fact[0] = 1;
    fact[1] = 1;
    for (long long i = 2; i < 20; i++) {
        fact[i] = fact[i - 1] * i;
    }
    long long n, k;
    cin >> n >> k;
    vector<long long>a(n);
    for (long long i = 0; i < n; i++) {
        a[i] = i + 1;
    }
    long long m = k;
    vector<long long> ans;
    while (a.size() > 0) {
        long long q = m / fact[a.size() - 1];
        ans.push_back(a[q]);
        m = m % fact[a.size() - 1];
        a.erase(a.begin() + q);
    }
    for (long long i = 0; i < ans.size(); i++) {
        cout << ans[i] << " ";
    }
    cout << endl;
    return 0;
}

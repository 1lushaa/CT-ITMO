#include <iostream>
#include <vector>
#include <set>
using namespace std;
vector<vector<int>>ans;
set<vector<int>>checker;
int check(const vector<int>& a) {
    vector<int> cp(a);
    cp.erase(cp.begin());
    cp.push_back(1);
    bool flag1 = checker.count(cp);
    if (!flag1) {
        ans.push_back(cp);
        checker.insert(cp);
        return 1;
    }
    cp.pop_back();
    cp.push_back(0);
    bool flag0 = checker.count(cp);
    if (!flag0) {
        ans.push_back(cp);
        checker.insert(cp);
        return 0;
    }
    else {
        return -1;
    }
}
int main() {
    int n;
    cin >> n;
    vector<int>zero(n, 0);
    ans.push_back(zero);
    while (check(ans[ans.size() - 1]) != -1) {
    }
    for (int i = 0; i < ans.size() - 1; i++) {
        for (int j = 0; j < ans[i].size(); j++) {
            cout << ans[i][j];
        }
        cout << endl;
    }
}

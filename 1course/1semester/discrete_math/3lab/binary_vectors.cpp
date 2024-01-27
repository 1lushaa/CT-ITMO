#include<iostream>
#include <vector>
using namespace std;
vector<int>a;
vector<vector<int>>ans;
void gen(const int& n) {
    if (a.size() == n) {
        bool flag = true;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] == 1 && a[i + 1] == 1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            ans.push_back(a);
        }
        return;
    }
    for (int i = 0; i < 2; i++) {
        a.push_back(i);
        gen(n);
        a.pop_back();
    }
}
int main() {
    int n;
    cin >> n;
    gen(n);
    cout << ans.size() << endl;
    for (int i = 0; i < ans.size(); i++) {
        for (int j = 0; j < n; j++) {
            cout << ans[i][j];
        }
        cout << endl;
    }
    return 0;
}

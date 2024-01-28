#include <iostream>
#include <vector>
using namespace std;
void gen(vector<int>& a, const int& n, const int& k) {
    a.push_back(n + 1);
    bool flag = false;
    for (int i = a.size() - 1; i > 0; i--) {
        if (abs(a[i - 1] - a[i]) >= 2) {
            a[i - 1]++;
            flag = true;
            int num = a[i - 1];
            for (int j = i; j < a.size(); j++) {
                a[j] = num + 1;
                num++;
            }
            break;
        }
    }
    if (flag) {
        for (int i = 0; i < k; i++) {
            cout << a[i] << " ";
        }
    }
    else {
        cout << -1;
    }
    cout << endl;
    return;
}
int main() {
    int n, k;
    cin >> n >> k;
    vector<int>a(k);
    for (int i = 0; i < k; i++) {
        cin >> a[i];
    }
    gen(a, n, k);
}

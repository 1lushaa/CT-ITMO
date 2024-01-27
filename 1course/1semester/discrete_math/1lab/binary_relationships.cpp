#include <iostream>
#include <vector>
using namespace std;
 
int ifReflex(vector<vector<int>>& a, int n) {
    for (int i = 0; i < n; i++) {
        if (a[i][i] != 1) {
            return 0;
        }
    }
    return 1;
}
 
int ifAntiReflex(vector<vector<int>>& a, int n) {
    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (a[i][i] == 1) {
            return 0;
        }
    }
    return 1;
}
 
int ifSimmetric(vector<vector<int>>& a, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 0 && a[j][i] == 1 || a[i][j] == 1 && a[j][i] == 0) {
                return 0;
            }
        }
    }
    return 1;
}
 
int ifAntiSimmetric(vector<vector<int>>& a, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == 1 && a[j][i] == 1 && i != j) {
                return 0;
            }
        }
    }
    return 1;
}
 
int ifTransitive(vector<vector<int>>& a, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (a[i][j] == 1 && a[j][k] == 1 && a[i][k] != 1) {
                    return 0;
                }
            }
        }
    }
    return 1;
}
 
void composition(vector<vector<int>>& a, vector<vector<int>>& b, vector<vector<int>>& ans, int n) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (a[i][j] == 1 && b[j][k] == 1) {
                    ans[i][k] = 1;
                }
            }
        }
    }
}
 
int main() {
    int n;
    cin >> n;
    vector<vector<int>>a(n, vector<int>(n));
    vector<vector<int>>b(n, vector<int>(n));
    vector<vector<int>>ans(n, vector<int>(n, 0));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> a[i][j];
        }
    }
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> b[i][j];
        }
    }
    composition(a, b, ans, n);
    cout << ifReflex(a, n) << " " << ifAntiReflex(a, n) << " " << ifSimmetric(a, n) << " " << ifAntiSimmetric(a, n) << " " << ifTransitive(a, n) << endl;
    cout << ifReflex(b, n) << " " << ifAntiReflex(b, n) << " " << ifSimmetric(b, n) << " " << ifAntiSimmetric(b, n) << " " << ifTransitive(b, n) << endl;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cout << ans[i][j] << " ";
        }
        cout << endl;
    }
    return 0;
}

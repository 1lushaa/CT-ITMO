#include <iostream>
#include <vector>
using namespace std;
vector<int>a;
void gen(const int& n, const int& k, const int& curr) {
    if (a.size() == k) {
        for (int i = 0; i < k; i++) {
            cout << a[i] << " ";
        }
        cout << endl;
        return;
    }
    for (int i = curr + 1; i <= n; i++) {
        a.push_back(i);
        gen(n, k, i);
        a.pop_back();
    }
}
int main() {
    int n, k;
    cin >> n >> k;
    gen(n, k, 0);
}

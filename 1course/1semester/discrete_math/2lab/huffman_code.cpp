#include <iostream>
#include <queue>
#include <vector>
using namespace std;
struct node {
    long long val;
    node* left;
    node* right;
};
struct Compare {
    bool operator()(node* a, node* b) {
        return a->val > b->val;
    }
};
long long find(node* root, long long num) {
    if (root == nullptr) {
        return 0;
    }
    if (root->left == nullptr && root->right == nullptr) {
        return num * root->val;
    }
    return find(root->left, num + 1) + find(root->right, num + 1);
}
int main() {
    long long n;
    cin >> n;
    priority_queue<node*, vector<node*>, Compare> a;
    for (long long i = 0; i < n; i++) {
        node* curr = new node;
        cin >> curr->val;
        curr->left = nullptr;
        curr->right = nullptr;
        a.push(curr);
    }
    while (a.size() > 1) {
        node* left = a.top();
        a.pop();
        node* right = a.top();
        a.pop();
        node* curr = new node;
        curr->val = left->val + right->val;
        curr->left = left;
        curr->right = right;
        a.push(curr);
    }
    node* root = a.top();
    cout << find(root, 0);
}

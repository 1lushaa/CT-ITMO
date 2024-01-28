def get_bitwise(dp, i, length, eps):
    if dp[i][length - eps] == 1 and ((i - length + eps) // 2) == 0:
        return 1
    if ((i - length + eps) // 2) == -1:
        return 0
    return dp[i][length - eps] << ((i - length + eps) // 2)
 
def init(dp):
    dp[0][0] = 1
    for i in range(len(dp) - 1):
        for j in range(len(dp[i]) - 1):
            if j < len(dp[i]) - 2:
                dp[i + 1][j + 1] = dp[i + 1][j + 1] + dp[i][j]
            if j > 0:
                dp[i + 1][j - 1] = dp[i + 1][j - 1] + dp[i][j]
 
def gen(ans, n, k, dp):
    permut = k
    curr = [0] * (n * 2 + 1)
    curr_len = 0
    length = 0
    i = len(dp) - 3
    while i >= 0:
        num = 0
        if length < n:
            num = get_bitwise(dp, i, length, -1)
        else:
            num = 0
        if num >= permut:
            curr[curr_len] = '('
            ans.append('(')
            curr_len += 1
            length += 1
            i -= 1
            continue
        permut -= num
        if length > 0 and curr_len > 0 and curr[curr_len - 1] == '(':
            num = get_bitwise(dp, i, length, 1)
        else:
            num = 0
        if num >= permut:
            ans.append(')')
            curr_len -= 1
            length -= 1
            i -= 1
            continue
        permut -= num
        if length < n:
            num = get_bitwise(dp, i, length, -1)
        else:
            num = 0
        if num >= permut:
            ans.append('[')
            curr[curr_len] = '['
            curr_len += 1
            length += 1
            i -= 1
            continue
        permut -= num
        ans.append(']')
        curr_len -= 1
        length -= 1
        i -= 1
 
n, k = map(int, input().split())
k += 1
dp = []
for i in range(n*2+2):
    dp.append([0] * (n + 2))
init(dp)
ans = []
gen(ans, n, k, dp)
print(*ans, sep="")

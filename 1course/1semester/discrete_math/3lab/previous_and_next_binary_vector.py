def gen(s):
    n = int(s, 2)
    cnt = 0
    for i in s:
        if i == '1':
            cnt += 1
    if cnt == len(s):
        print(bin(n - 1)[2:])
        print('-')
        return
    if n == 0:
        print('-')
        print((len(s) - 1)*"0" + "1")
        return
    ans1 = bin(n - 1)[2:]
    ans2 = bin(n + 1)[2:]
    if len(ans1) < len(s):
        while len(ans1) < len(s):
            ans1 = "0" + ans1
    if len(ans2) < len(s):
        while len(ans2) < len(s):
            ans2 = "0" + ans2
    print(ans1)
    print(ans2)
gen(input())

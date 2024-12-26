import re

f = open('day13.in')
ss = f.readlines()

br = re.compile(r'\+(\d+),.*?\+(\d+)')
pr = re.compile(r'X=(\d+), Y=(\d+)')

# Part 1
n = []
i = 0
while i < len(ss):
    am = br.search(ss[i])
    a = (int(am.group(1)), int(am.group(2)))
    i += 1
    bm = br.search(ss[i])
    b = (int(bm.group(1)), int(bm.group(2)))
    i += 1
    cm = pr.search(ss[i])
    c = (int(cm.group(1)), int(cm.group(2)))
    i += 2
    n.append((a[0], b[0], c[0], a[1], b[1], c[1]))

total = 0
for i in n:
    a = (i[4] * i[2] - i[1] * i[5]) / (i[4] * i[0] - i[1] * i[3])
    if not a.is_integer():
        # print('IMPOSSIBLE')
        continue
    b = (i[2] - i[0] * a) / i[1]
    # print(a, b)
    total += a * 3 + b

print(total)

# Part 2
n = []
i = 0
while i < len(ss):
    am = br.search(ss[i])
    a = (int(am.group(1)), int(am.group(2)))
    i += 1
    bm = br.search(ss[i])
    b = (int(bm.group(1)), int(bm.group(2)))
    i += 1
    cm = pr.search(ss[i])
    c = (int(cm.group(1)) + 10000000000000, int(cm.group(2)) + 10000000000000)
    i += 2
    n.append((a[0], b[0], c[0], a[1], b[1], c[1]))

total = 0
for i in n:
    a = (i[4] * i[2] - i[1] * i[5]) / (i[4] * i[0] - i[1] * i[3])
    if not a.is_integer():
        print('IMPOSSIBLE')
        continue
    b = (i[2] - i[0] * a) / i[1]
    if not b.is_integer():
        print('IMPOSSIBLE')
        continue
    print(a, b)
    total += a * 3 + b

print(total)
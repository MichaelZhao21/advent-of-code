from collections import namedtuple
from heapq import heappush, heappop

f = open('day9.in')
n = list(map(lambda x: int(x), f.readline()))

Item = namedtuple('Item', ['ind', 'count', 'num'])

def binseafirst(l, ind):
    low, high = 0, len(l) - 1
    while low <= high:
        mid = (low + high) // 2
        if l[mid][0] < ind:
            low = mid + 1
        elif l[mid][0] > ind:
            high = mid - 1
        else:
            return mid  # Found the target
    return None  # Not found

# Part 2
h = [[] for i in range(10)]
l = []
ind = 0
num = 0
for i, v in enumerate(n):
    if v == 0:
        continue
    if i % 2 == 0:
        l.append(Item(ind, v, num))
        num += 1
    else:
        l.append(Item(ind, v, -1))
        heappush(h[v], ind)
    ind += v

print(l)
print("AAAAAAAAAAA")

# loop from end
i = len(l)-1
while i >= 0:
    # ignore -1 blocks
    if l[i].num == -1:
        i -= 1
        continue

    # look until found
    m = 10e9
    mi = None
    for j in range(l[i].count, 10):
        if len(h[j]) > 0:
            m = min(m, h[j][0])
            mi = j
    if m == 10e9 or m >= l[i].ind:
        i -= 1
        continue
    
    # get index
    ind = binseafirst(l, m)
    print(l[i], m, ind)
    if ind == None:
        print("ERROR THIS INDEX SHOULD NEVER BE NONE TT")
        print(i, m)
        exit(1)
    heappop(h[mi])

    # if same set num; if not same, create new segment
    if l[ind].count == l[i].count:
        l[ind] = Item(l[ind].ind, l[ind].count, l[i].num)
    else:
        diff = l[ind].count - l[i].count
        l[ind] = Item(l[ind].ind, l[i].count, l[i].num)
        ni = l[ind].ind + l[ind].count
        l.insert(ind+1, Item(ni, diff, -1))
        heappush(h[diff], ni)
        i += 1
    l[i] = Item(l[i].ind, l[i].count, -1)
    print(l)
    print("NEXT")

    # decr
    i -= 1

s = []
for i in l:
    s.extend([0 if i.num == -1 else i.num] * i.count)

total = 0
for i, v in enumerate(s):
    total += i * v
print(total)
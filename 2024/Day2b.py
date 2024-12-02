# Read in file
levelslist = []
n = 1000
f = open('day2.in')
for i in range(n):
    s = f.readline()
    ss = s.split(' ')
    curr = []
    for a in ss:
        curr.append(int(a))
    levelslist.append(curr)

# Part 2
# Loop through levelslist and create a new list of all alt lists for levelslist
levelslistlist = []
for levels in levelslist:
    ll = [levels]
    for i in range(len(levels)):
        nw = list(levels)
        del nw[i]
        ll.append(nw)
    levelslistlist.append(ll)

total = 0
for ll in levelslistlist:
    safe = True

    for levels in ll:
        safe = True
        decr = levels[0] > levels[1]

        for (i, l) in enumerate(levels):
            if (i == len(levels) - 1):
                break

            if ((levels[i] > levels[i+1]) != decr):
                safe = False
                break

            diff = abs(levels[i] - levels[i+1])
            if (diff < 1 or diff > 3):
                safe = False
                break
    
        if (safe):
            total += 1
            break

print(total)



import random

num, q, n, m, t = map(int, input().split())
fout = open("testdata/test00" + str(num) + ".txt", "w")
for i in range(q):
    nn = random.randint(1, n)
    mm = random.randint(1, min(m, nn))
    tt = random.randint(1, t)
    fout.write(str(nn) + " " + str(mm) + "\n")
    cur = "m "
    for i in range(tt):
        cur += str(random.randint(1, nn)) + " "
    fout.write(cur + "\n\n")
fout.close()

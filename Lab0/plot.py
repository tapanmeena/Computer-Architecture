import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D


# ======
## data:
mylist=[]
f=open("newData","r")
f1=f.readlines()
for x in f1:
	x=[float(i) for i in x.split()]
	mylist.append(x)

x=[]
y=[]
z=[]
for i in range(len(mylist)):
	x.append(mylist[i][0])
	y.append(mylist[i][1])
	z.append(mylist[i][2])

# ======
## plot:
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')
ax.scatter(x, y, z)
ax.set_xlabel('Width of Border')
ax.set_ylabel('Probability')
ax.set_zlabel('Average Time(in Sec)')

plt.show() # or:
#fig.savefig('3D.png')

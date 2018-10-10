import matplotlib.pylpot as plt
from mpl_toolkits.mplot3d import AxesD
import numpy as np

n_radii=8
n_angles=36

radii=np.linespace(0.125,1.0,n_radii)
angles=np.linespace(0,2*np.pi,n_angles,endpoint=False)

angles=np.repeat(angles[...,np.newaxis], n_radii, axis=1)

x=np.append(0,(radii*np.cos(angles)).flatten())
y=np.append(0,(radii*np.sin(angles)).flatten())

z=np.sin(-x*y)

fig=plt.figure()
ax=gig.gca(projection='3d')

ax.plot_trisurf(x,y,z,linewidth=0.2,antialiased=True)

plt.show()
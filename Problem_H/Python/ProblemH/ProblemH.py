import platform
import os
import numpy as np

'''
Name : Rosdyana Kusuma 
Student ID : 1056035
'''

if platform.system() == "Windows":
    os.system('cls')
else:
    os.system('clear')
print("\n")
print("  ___|                 |  __ __| |                          ")
print(" |      __| _` | __ \  __ \  |   __ \   _ \  _ \   __| |   |")
print(" |   | |   (   | |   | | | | |   | | |  __/ (   | |    |   |")
print("\____|_|  \__,_| .__/ _| |_|_|  _| |_|\___|\___/ _|   \__, |")
print("                _|                                    ____/ ")
print("             rosdyana.kusuma [at] gmail [dot] com")
print("     2016 ACM ICPC Asia Chung-Li Regional - Problem H\n")

inputVertices1 = []
inputVertices2 = []
statPerfectmatching = []

numOfGraphs = int(raw_input('Input number of bipartite graphs : '))

if numOfGraphs <= 10 :
    for BiGraph in range(numOfGraphs) :
        vertexAndEdge = raw_input('Input total vertex and edges: ')
        separateVertexAndEdge = vertexAndEdge.split(' ')
        vertex = int(separateVertexAndEdge[0])
        edge = int(separateVertexAndEdge[1])

        if vertex <= 20 and vertex % 2 == 0:
            minG1 = 1
            maxG1 = vertex/2
            minG2 = (vertex/2)+1
            maxG2 = vertex

            if edge <= 46 :
                print 'Input edges :'
                for numEdge in range(edge) :
                    inputEdges = raw_input('')
                    separateEdges = inputEdges.split(' ')
                    inputVertices1.append(int(separateEdges[0]))
                    inputVertices2.append(int(separateEdges[1]))

                #normalizing vertices
                normV1 = np.array(inputVertices1)
                normV2 = np.array(inputVertices2)
                for x,y in enumerate(normV1) :
                    normV1[x]=y-1
                for x,y in enumerate(normV2) :
                    normV2[x]=y-6

                #find the perfect matching , thanks to numpy
                findMatching = (normV1==normV2).all()
                if findMatching :                    
                    statPerfectmatching.append('0')
                else :
                    statPerfectmatching.append('1')

            else : 
                print 'Maximum number of edges in a bipartite graph is 46.\n'
        else :
            print 'Maximum number of vertices in a bipartite graph is 20 and should be even number.\n'
    final = np.array(statPerfectmatching)
    print 'Output for the Sample Input'
    print '\n'.join(final)
else :
    print 'Maximum number of bipartite graphs is 20.\n'

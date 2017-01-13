import platform
import os
import numpy as np
import argparse
from itertools import*

'''
Copyright 2017 Rosdyana Kusuma.
Licensed under the Apache License, Version 2.0
Name : Rosdyana Kusuma 
Student ID : 1056035
'''
	
def perm_ryser(a): # Ryser's formula, using matrix entries
    maxn = len(a)
    n_list = range(1,maxn+1)
    s_list = chain.from_iterable(combinations(n_list,i) for i in range(maxn+1))
    total = 0
    for st in s_list:
        stotal = (-1)**len(st)
        for i in xrange(maxn):
            stotal *= sum(a[i][j-1] for j in st)
        total += stotal
    return total*((-1)**maxn)

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
print("     python problemH.py -i file_input ")

inputVertices1 = []
inputVertices2 = []
statPerfectmatching = []

parser = argparse.ArgumentParser()
parser.add_argument("-i", help="input file")
args = parser.parse_args()

if args.i is not None:
    with open(args.i) as f:
        content = f.readlines()
    content = [x.strip() for x in content]
    countLine = 0
    for i in content:
        countLine += 1
    #print countLine
    #print content
    numOfGraphs = int(content[0])
    #print numOfGraphs
    vertexAndEdgeIdx = 1
    edgesIdx = 2

    if numOfGraphs <= 20 :
        vertexAndEdge = content[vertexAndEdgeIdx]        
        separateVertexAndEdge = vertexAndEdge.split(' ')
        vertex = int(separateVertexAndEdge[0])
        #print vertex
        edge = int(separateVertexAndEdge[1])
        #print edge
        check = edge+edgesIdx
        adjacencyArr = np.zeros(shape=(vertex/2,vertex/2))
        adjacencyArr.fill(0)
        #print adjacencyArr
        #print check
        for i, item in enumerate(content):
            #print i
            #print item
            #print check
            if i > 1:
                #print i
                #print check
                if i < check:
                    edges = content[i]
                    #print edges
                    separateEdges = edges.split(' ')
                    adjacencyArr.itemset(int(separateEdges[0])-1 , int(separateEdges[1]) - 1 - (vertex/2), 1)
                    #print separateEdges
                if i == check:
                    #print len(content)
                    #print adjacencyArr
                    if perm_ryser(adjacencyArr)%2 == 1:
                        print "0"
                    else:
                        print "1"
                    adjacencyArr.fill(0)
                    if i < len(content):
                        edges = content[i]
                        separateEdges = edges.split(' ')
                        #print separateEdges
                        edge = int(separateEdges[1])
                        check = check + edge + 1
                        #print check
    else :
        print 'Maximum number of bipartite graphs is 20.\n'

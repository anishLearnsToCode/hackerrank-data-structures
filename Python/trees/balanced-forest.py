#!/bin/python3

import math
import os

class TreeNode:
    def __init__(self, value, children):
        self.value = value
        self.children = children
        self.total_sum = None

    def __repr__(self):
        return "TreeNode(%s, %s)" % (self.value, self.total_sum)

def build_tree(tree_values, tree_edges):
    tree_nodes = [TreeNode(v, set()) for v in tree_values]
    for node_from, node_to in tree_edges:
        # The tree input is undirected so I am adding both as children and parent
        # I am later cleaning it up while doing DFS over the tree
        tree_nodes[node_from - 1].children.add(tree_nodes[node_to - 1])
        tree_nodes[node_to - 1].children.add(tree_nodes[node_from - 1])
    return tree_nodes[0]

def is_even_number(value):
    return not value & 1

def populate_tree_sums(root):
    stack = (root, None)
    visited = set()

    while stack:
        selected_node = stack[0]

        if selected_node not in visited:
            visited.add(selected_node)
            for child in selected_node.children:
                #remove non children, this cleans out the "bad" inputs
                #the tree has undirected edges so when we convert it back to a
                #proper tree it's easier to work with...
                child.children.remove(selected_node)
                #populate the stack:
                stack = (child, stack)
        else:
            stack = stack[-1] # pop stack
            #calculate the total sum of the current node before going up the tree
            selected_node.total_sum = sum(
                map(
                    lambda tn: tn.total_sum,
                    selected_node.children
                )
            ) + selected_node.value

def find_best_balanced_forest(root):
    stack = (root, None)
    #visited - visited nodes
    #visited_sums - sums that are currently visited
    #root complement sums complement sums (total_value - parent) on the way to the
    #current node, the cardinality of root complement sums is increased when going
    #down the tree and decreased when going up the tree, it is okay to do that
    #because the sums are always unique in the root_complement_sums
    visited, visited_sums, root_complement_sums = set(), set(), set()
    min_result_value = math.inf

    while stack:
        selected_node = stack[0]

        if selected_node not in visited:
            visited.add(selected_node)

            #populate stack with children all at once:
            for child in selected_node.children:
                stack = (child, stack)

            #this is a complement sum: TOTAL - current_sum
            #I need to calculate it while going down the tree so when I go up
            #I will use those values in the root_complement_sums to check for
            #existance
            selected_sum_comp = root.total_sum - selected_node.total_sum
            root_complement_sums.add(selected_sum_comp)

            # Yes, no bitwise shifts, I present what I want to get accomplished,
            # but I don't care how it is accomplished
            # selected_node.total_sum * 3 >= root.total_sum is checking that
            # that if the cut is made in selected subtree and the visited subtree
            # (in case the comp or sum exists in the visited sums)
            # the remaining subtree sum is equal or less than the sums
            # (which are equal) of the current and the visited subtrees
            # this is just part of the requirement - I can balance the remaining
            # tree only with 0 or positive elements
            if (
                    (selected_node.total_sum * 2) in visited_sums or
                    (root.total_sum - selected_node.total_sum * 2) in visited_sums
            ) and selected_node.total_sum * 3 >= root.total_sum:

                #get the candidate value and update min_result_value if it's less
                candidate_value = selected_node.total_sum * 3 - root.total_sum
                if candidate_value < min_result_value:
                    min_result_value = candidate_value
        else:
            # This is a case where two even halfs are found.
            if (selected_node.total_sum * 2) == root.total_sum:
                candidate_value = selected_node.total_sum
                # In this case a balanced forest is these two halfs + a new node as
                # a separate tree with the same value as the half of the existing
                # tree sum
                if candidate_value < min_result_value:
                    min_result_value = candidate_value

            # check visited sums and root complements
            # root complements are the sums on the way from root to the selected
            # nodes taken from it's parents of if we have a tree
            #          (1)
            #        / |  \
            #      /   |   \
            #     /    |    \
            #   (2)   (3)   (4)
            #   / \    |    /\
            # (5)(6) (7)  (8)(9)
            #
            #
            # If I am at the node 8, I have the {TOTAL - (8).sum, TOTAL - (4).sum }
            # If I am at the node 9, I have the {TOTAL - (9).sum, TOTAL - (4).sum }
            # If I am at the node 2, I have the {TOTAL - (2).sum }
            if (
                    (
                            selected_node.total_sum in visited_sums or
                            selected_node.total_sum in root_complement_sums
                    ) and selected_node.total_sum * 3 >= root.total_sum
            ):
                # candidate split:
                candidate_value = selected_node.total_sum * 3 - root.total_sum
                if candidate_value < min_result_value:
                    min_result_value = candidate_value

            selected_sum_comp = root.total_sum - selected_node.total_sum
            if is_even_number(selected_sum_comp):
                #I am not trying to impress anyone with bitwise shifts here:
                selected_sum_comp_half = selected_sum_comp // 2
                if selected_sum_comp_half > selected_node.total_sum and (
                        selected_sum_comp_half in visited_sums or
                        selected_sum_comp_half in root_complement_sums
                ):
                    #same candidate value
                    candidate_value = selected_sum_comp_half - selected_node.total_sum
                    if candidate_value < min_result_value:
                        min_result_value = candidate_value

            #remove selected complement from root while going up the tree
            root_complement_sums.remove(selected_sum_comp)
            #added to the visited sums while going up the tree
            visited_sums.add(selected_node.total_sum)

            #stack pop:
            stack = stack[-1]

    if min_result_value == math.inf:
        min_result_value = -1
    return min_result_value

# Complete the balancedForest function below.
def balancedForest(tree_values, tree_edges):
    root = build_tree(tree_values, tree_edges)
    populate_tree_sums(root)
    return find_best_balanced_forest(root)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input())

    for q_itr in range(q):
        n = int(input())

        c = list(map(int, input().rstrip().split()))

        edges = []

        for _ in range(n - 1):
            edges.append(list(map(int, input().rstrip().split())))

        result = balancedForest(c, edges)

        fptr.write(str(result) + '\n')

    fptr.close()

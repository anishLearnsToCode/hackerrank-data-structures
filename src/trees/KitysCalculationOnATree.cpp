// Solution Provided by Jesse Sparks
// See GitHub: https://github.com/jsparks125

#include <chrono>
#include <cmath>
#include <cstdio>
#include <map>
#include <queue>
#include <set>
#include <stack>
#include <tuple>
#include <vector>
#include <iostream>
#include <fstream>
#include <algorithm>
#include <unordered_map>
#include <string>

int max_depth = 0;

struct node
{
	int value;
	int depth;
	node* parent;

	node(int v, int d, node* p)
	{
		value = v;
		depth = d;
		parent = p;
	}

	node()
	{
		value = -1;
		depth = -1;
	}
};

struct subtree
{
	int root_node;
	int depth;
	int next_calc_depth = -1;
	unsigned long long next_calc = 0;
	unsigned long long next_sum = 0;

	subtree()
	{}

	subtree(int d)
	{
		depth = d;
	}

	subtree(int cn, int d)
	{
		root_node = cn;
		depth = d;
		next_calc_depth = d;
	}
};

std::unordered_map<int, node> nodes;

int main()
{
	int num_nodes, num_sets;
	std::cin >> num_nodes >> num_sets;
	int child_node, parent_node;

	std::vector<std::pair<int, int>> node_list;
	for (int i = 1; i < num_nodes; i++)
	{
		std::cin >> child_node >> parent_node;
		if (parent_node > child_node)
			node_list.push_back(std::pair<int, int>(child_node, parent_node));
		else
			node_list.push_back(std::pair<int, int>(parent_node, child_node));
	}

	std::pair<int, int> first_node_pair = *(node_list.begin());
	parent_node = first_node_pair.first;
	child_node = first_node_pair.second;
	nodes[parent_node] = node(parent_node, 0, nullptr);
	nodes[child_node] = node(child_node, 1, &nodes[parent_node]);

	for (auto it = std::next(node_list.begin()); it != node_list.end(); ++it)
	{
		parent_node = it->first;
		child_node = it->second;
		node* parent = &nodes[parent_node];
		int parent_depth = parent->depth;
		int current_depth = parent_depth + 1;

		if (current_depth > max_depth)
			max_depth = current_depth;

		nodes[child_node] = node(child_node, current_depth, parent);
	}

	for (int i = 0; i < num_sets; i++)
	{
		int n;
		std::cin >> n;

		if (n > 1)
		{
			int loadTime = 0;

			std::map<int, subtree> subtrees;
			std::map<int, std::map<int, node>> nodes_by_depth;

			unsigned long long int new_kitty_sum = 0;
			int subtree_max_depth = 0;

			//Load nodes from the current set and store them by their depth
			for (int j = 0; j < n; j++)
			{
				int current_node_key;
				std::cin >> current_node_key;
				node current_node = nodes[current_node_key];

				int current_depth = current_node.depth;
				if (current_depth > subtree_max_depth)
					subtree_max_depth = current_depth;

				nodes_by_depth[current_depth][current_node.value] = current_node;
			}

			//Start at the bottom of the tree and work upward, combining nodes as they hit their common ancestor
			for (int j = subtree_max_depth; j > 0; j--)
			{
				auto process_roots = nodes_by_depth.find(j);

				//We can easily shift a node up as long as it is the only node at its current level and there are no nodes directly above it
				//This alleviates quite a bit of processing on very deep trees
				if (process_roots->second.size() == 1)
				{
					auto next_roots_iter = nodes_by_depth.find(j - 1);
					if (next_roots_iter == nodes_by_depth.end())
					{
						int shifted = 0;
						node current_node = process_roots->second.begin()->second;
						int previous_node_value = current_node.value;
						while (next_roots_iter == nodes_by_depth.end() && (j - shifted) > 0)
						{
							shifted++;
							next_roots_iter = nodes_by_depth.find(j - shifted);
							if (next_roots_iter == nodes_by_depth.end())
							{
								previous_node_value = current_node.parent->value;
								current_node.parent = current_node.parent->parent;
							}
						}
						nodes_by_depth[j - (shifted - 1)].insert(std::pair<int, node>(previous_node_value, current_node));
						process_roots = nodes_by_depth.find(j - (shifted - 1));
						j -= (shifted - 1);
					}
				}

				//Check all of the roots at the current level to see if they have a common ancestor or if their parent exists at the next level
				//If so, they can be combined
				for (auto root_iter = process_roots->second.begin(); root_iter != process_roots->second.end(); ++root_iter)
				{
					node current_node = root_iter->second;
					if (current_node.parent == nullptr)
						break;
					node* parent_node = current_node.parent;

					auto parent_iter = nodes_by_depth[j - 1].find(current_node.parent->value);
					if (parent_iter != nodes_by_depth[j - 1].end())
					{
						subtree* next_root_subtree;
						auto next_iter = subtrees.find(parent_iter->second.value);
						if (next_iter != subtrees.end())
							next_root_subtree = &next_iter->second;
						else
						{
							auto nrs_iter = subtrees.insert(std::pair<int, subtree>(parent_iter->first, subtree(parent_iter->first, j - 1))).first;
							next_root_subtree = &(nrs_iter->second);
							next_root_subtree->next_calc = (uint64_t(parent_iter->second.depth - (j - 1)) * uint64_t(parent_iter->second.value)) % 1000000007;
							next_root_subtree->next_sum = parent_iter->second.value;
							nodes_by_depth[j - 1][parent_iter->first].value = parent_iter->first;
						}

						unsigned long long next_calc = next_root_subtree->next_calc + (next_root_subtree->next_sum * (next_root_subtree->next_calc_depth - (j - 1))) % 1000000007;
						unsigned long long next_child_sum = next_root_subtree->next_sum;
						next_root_subtree->next_calc = next_calc;
						next_root_subtree->next_calc_depth = j - 1;

						auto current_iter = subtrees.find(current_node.value);
						if (current_iter != subtrees.end())
						{
							subtree* current_subtree = &current_iter->second;
							int current_index = current_subtree->next_calc_depth - (j - 1);
							unsigned long long new_sum = next_calc * (current_subtree->next_sum);
							unsigned long long current_product = current_subtree->next_calc + (current_subtree->next_sum * uint64_t(current_index)) % 1000000007;
							unsigned long long second_sum = next_child_sum * (current_product % 1000000007);
							new_kitty_sum += (new_sum % 1000000007) + (second_sum % 1000000007);
							next_root_subtree->next_calc += (current_product % 1000000007);
							next_root_subtree->next_calc %= 1000000007;
							next_root_subtree->next_sum += current_subtree->next_sum;
						}
						else
						{
							int current_depth = current_node.depth;
							int current_index = current_depth - (j - 1);
							int current_sum = current_node.value;
							unsigned long long new_sum = next_calc * uint64_t(current_sum);
							unsigned long long current_product = uint64_t(current_sum) * uint64_t(current_index);
							unsigned long long second_sum = next_child_sum * (current_product % 1000000007);
							new_kitty_sum += (new_sum % 1000000007) + (second_sum % 1000000007);
							int new_index = current_depth - next_root_subtree->depth;
							next_root_subtree->next_calc += (current_product % 1000000007);
							next_root_subtree->next_calc %= 1000000007;
							next_root_subtree->next_sum += uint64_t(current_sum);
						}
					}
					else
					{
						int new_root_depth = j - 1;
						current_node.parent = parent_node->parent;
						nodes_by_depth[new_root_depth][parent_node->value] = current_node;
					}
				}
			}

			std::cout << new_kitty_sum % 1000000007 << std::endl;
		}
		else
		{
			std::cin >> n;
			std::cout << 0 << std::endl;
		}
	}

	return 0;
}
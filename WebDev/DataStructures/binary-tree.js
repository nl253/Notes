#!/usr/bin/env node


function mkTree(item = null) {
  if (item === null) {
    return null;
  } else {
    return ({
      data: item,
      left: null,
      right: null,
    });
  }
}

const height = tree => ({
  left: _height(tree.left),
  right: _height(tree.right),
});

const rotateLeft = (tree) => {
  const newRoot = tree.right;
  tree.right = null;
  newRoot.left = merge(newRoot.left, tree);
  return newRoot;
};

const rotateRight = (tree) => {
  const newRoot = tree.left;
  tree.left = null;
  newRoot.right = merge(newRoot.right, tree);
  return newRoot;
};

const flatten = (tree) => {
  if (tree === null) return [];
  // else
  const rest = flatten(tree.left);
  rest.push(tree.data);
  return rest.concat(flatten(tree.right));
};

const _height = (tree) => {
  if (tree === null) return 0;
  else return 1 + Math.max(_height(tree.left), _height(tree.right));
};

const fromList = (xs) => {
  let tree = mkTree();
  while (xs.length > 0) {
    tree = insert(tree, xs.pop());
  }
  return tree;
};

const remove = (tree, value) => {
  if (tree === null) {
    return tree;
  } else if (tree.data === value) {
    return merge(tree.left, tree.right);
  } else if (value < tree.data) {
    tree.left = remove(tree.left, value);
    return tree;
  } else {
    tree.right = remove(tree.right, value);
    return tree;
  }
};

const insert = (tree, value) => {
  if (tree === null) {
    return mkTree(value);
  } else if (value < tree.data) {
    tree.left = insert(tree.left, value);
    return tree;
  } else {
    tree.right = insert(tree.right, value);
    return tree;
  }
};

const merge = (left, right) => {
  if (left === null) return right;
  else if (right === null) return left;

  // else queue-based merge

  const queue = [left, right];
  let root = mkTree();

  while (queue.length > 0) {
    const fst = queue.pop();
    root = insert(root, fst.data);
    if (fst.left !== null) {
      queue.push(fst.left);
    }
    if (fst.right !== null) {
      queue.push(fst.right);
    }
  }
  return root;
};

const balance = (tree) => {
  if (tree === null) return tree;
  // else
  const heights = height(tree);
  const lHeight = height.left;
  const rHeight = height.right;

  if (Math.abs(rHeight - lHeight) <= 1) {
    return tree; // oK, balanced
  } else if (lHeight < rHeight) {
    return balance(rotateLeft(tree));
  } else {
    return balance(rotateRight(tree));
  }
};

module.exports = {
  balance,
  flatten,
  fromList,
  height,
  insert,
  merge,
  mkTree,
  remove,
  rotateLeft,
  rotateRight,
};

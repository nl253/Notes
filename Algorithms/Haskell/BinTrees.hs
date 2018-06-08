#!/usr/bin/env stack
-- stack --install-ghc runghc
{-# LANGUAGE DeriveFunctor #-}

module BinTrees where

import Data.Semigroup

data Tree a
  = Leaf
  | Tree (Tree a)
         a
         (Tree a)
  deriving (Functor, Eq, Show, Read)

class BinTree t where
  fromList :: Ord a => [a] -> t a
  insert :: Ord a => a -> t a
  remove :: Ord a => a -> t a
  merge :: Ord a => t a -> t a -> t a

instance (Ord a) => Semigroup (Tree a) where
  (<>) = merge

instance (Ord a) => Monoid (Tree a) where
  mempty = Leaf
  mappend = (<>)

instance BinTree Tree where
  fromList [] = Leaf
  fromList xs = foldr insert Leaf
  insert a Leaf = Tree Leaf a Leaf
  insert a (Tree t1 b t2)
    | a < b = Tree (insert a t1) b t2
    | otherwise = Tree t1 b $ insert a t2
  remove a Leaf = Leaf
  remove a (Tree t1 b t2)
    | a == b = merge t1 t2
  remove x (Tree left@(Tree t1 b t2) a right@(Tree t3 c t4))
    | x < a = remove x left
    | otherwise = remove x right
  merge Leaf Leaf = Leaf
  merge t Leaf = t
  merge Leaf t = t
  merge left@(Tree t1 a t2) right@(Tree t3 b t4)
    | b >= a = Tree t1 a (merge right t2)
    | otherwise = Tree (merge right t1) a t2

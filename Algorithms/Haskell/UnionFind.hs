{-# LANGUAGE DeriveFoldable #-}
{-# LANGUAGE DeriveFunctor  #-}

module UnionFind
  ( makeSet
  , find
  ) where

import qualified Data.Map   as M
import           Data.Maybe (fromJust)

type UnionFind a = M.Map a a

makeSet :: Ord a => [a] -> UnionFind a
makeSet xs = M.fromList $ fmap initialise xs
  where
    initialise x = (x, x)

setParent :: UnionFind a -> a -> a -> UnionFind a
setParent uf a p = M.insert a p uf

find :: Ord a => UnionFind a -> a -> (UnionFind a, a)
find uf a
  | a == fromJust (M.lookup a uf) = (uf, a)
  | otherwise = find modified parent
  where
    parent :: Ord a => a
    parent = fromJust $ M.lookup a uf
    modified :: Ord a => UnionFind a
    modified = setParent uf a (find uf a)

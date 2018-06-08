#!/usr/bin/env stack
-- stack --install-ghc runghc
module Sorting where

import Data.List (partition)
import System.Time (ctPicosec, getClockTime, toCalendarTime)

quicksort :: Ord a => [a] -> [a]
quicksort xs
  | null xs || null (tail xs) = xs
  | otherwise =
    let pivot = head xs
        (lt, gt') = partition (< pivot) xs
        (eq, gt) = partition (== pivot) gt'
     in concat [quicksort lt, eq, quicksort gt]

quicksort' :: Ord a => [a] -> [a]
quicksort' [] = []
quicksort' [x] = [x]
quicksort' (pivot:xs) = (quicksort lt) ++ eq ++ (quicksort gt)
  where
    lt = filter (< pivot) xs
    eq = filter (== pivot) xs
    gt = filter (> pivot) xs

quicksort'' :: Ord a => [a] -> [a]
quicksort'' [] = []
quicksort'' [x] = [x]
quicksort'' (pivot:xs) = sort pivot xs [] [] []
  where
    sort :: Ord a => a -> [a] -> [a] -> [a] -> [a] -> [a]
    sort _ [] lt eq gt = (quicksort'' lt) ++ eq ++ (quicksort'' gt)
    sort pivot (x:xs) lt eq gt
      | x < pivot = sort pivot xs (x : lt) eq gt
      | x > pivot = sort pivot xs lt eq (x : gt)
      | otherwise = sort pivot xs lt (x : eq) gt

mergesort :: Ord a => [a] -> [a]
mergesort xs
  | null xs || (null . tail) xs = xs
  | otherwise = mergesort fsthalf `merge` mergesort sndhalf
  where
    half = length xs `div` 2
    fsthalf = take half xs
    sndhalf = drop half xs
    merge xs ys
      | null xs = ys
      | null ys = xs
      | x < y = x : merge (tail xs) ys
      | otherwise = y : merge xs (tail ys)
      where
        x = head xs
        y = head ys

insertionsort :: Ord a => [a] -> [a]
insertionsort = sort []
  where
    ins :: Ord a => [a] -> a -> [a]
    ins ys x
      | null ys = [x]
      | x <= head ys = x : ys
      | otherwise = head ys : ins (tail ys) x
    sort :: Ord a => [a] -> [a] -> [a]
    sort sorted xs
      | null xs = sorted
      | otherwise = sort (ins sorted (head xs)) $ tail xs

startAlgo :: Ord a => [a] -> IO ([a], b)
startAlgo xs = do
  putStrLn $ intercalate "\n" [header, line, len_info, time_info]
  start_clock' <- getClockTime
  start_clock <- toCalendarTime start_clock'
  pure start_picosec
  where
    header = "Running Algorithm"
    line = (length header) `replicate` '-'
    len_info = "length: " <> (show (length xs))
    time_info = "starting at: " <> (show start_picosec)
    start_picosec = ctPicosec start_clock

endAlgo :: Integral a => a -> IO ()
endAlgo start_time = do
  end_clock' <- getClockTime
  end_clock <- toCalendarTime end_clock'
  putStrLn "took: " <> (show ((ctPicosec end_clock) - start_time))

verifysort :: (Ord a, Eq a) => [a] -> Bool
verifysort (x:y:xs) = x <= y && verifysort xs
verifysort _ = True

main :: IO ()
main = do
  (xs, start_time) <- startAlgo xs'
  print $ quicksort xs
  endAlgo start_time
  where
    xs' = reverse [1 .. 100]

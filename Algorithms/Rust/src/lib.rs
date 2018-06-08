#![feature(rustc_private)]
extern crate rand;

use std::{iter::ExactSizeIterator, ops::Index};

use rand::prelude::*;

pub fn fact(n: usize) -> usize {
    if n == 0 {
        1
    } else {
        n * fact(n - 1)
    }
}

pub fn perms<'a, T: Clone>(xs: &'a [T]) -> Vec<Vec<T>> { heaps_algo(xs.len(), xs) }

fn heaps_algo<'a, T: Clone>(n: usize, xs: &'a [T]) -> Vec<Vec<T>> {
    if n == 1 {
        return vec![Vec::from(xs)];
    }
    let mut seq = Vec::from(xs);
    let mut perms: Vec<Vec<T>> = Vec::with_capacity(fact(seq.len()) + 1);
    let last_idx = seq.len() - 1;

    for i in 0..n {
        for mut perm in heaps_algo(n - 1, &seq[..last_idx]) {
            let last_elem = &seq[last_idx];
            perm.push(last_elem.clone());
            perms.push(perm);
        }

        seq.swap(if n % 2 == 1 {
                     0
                 } else {
                     i
                 },
                 last_idx);
    }
    perms
}

// Shuffle in-place.
pub fn shuffle_<'a, T>(xs: &'a mut [T]) {
    let n = xs.len();

    for i in 0..n {
        let mut rand_idx: usize = rand::prelude::random();
        rand_idx %= n;
        xs.swap(i, rand_idx);
    }
}

// Pure version of 'shuffle'.
pub fn shuffle<'a, T: Clone>(xs: &'a [T]) -> Vec<T> {
    let mut xs = Vec::from(xs);
    let n = xs.len();

    for i in 0..n {
        let mut rand_idx: usize = rand::prelude::random();
        rand_idx %= n;
        xs.swap(i, rand_idx);
    }
    xs
}

// #[derive(Debug, PartialEq, PartialOrd)]
// struct PermGen<XS: Iterator + Clone + Index<usize>>(XS);

// impl <XS, T> Iterator for PermGen<XS>
// where XS: Iterator + Clone + Index<usize> {
// type Item = T;
// fn next(&mut self) -> T {
// }
// }

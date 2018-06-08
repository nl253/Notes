extern crate Rust;

fn main() {
    // for i in Rust::perms(&[1, 2, 3, 5, 10, 20]) {
        // println!("{:?}", i);
    // }
    let mut xs = [1, 2, 3];
    Rust::shuffle_(&mut xs);
    println!("{:?}", xs);
}

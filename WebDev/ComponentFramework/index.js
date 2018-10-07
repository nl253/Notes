/** @format */

const app = new Tag('div');
const title = new Tag('h1');

app.children.push(title);

const ls = new Tag('ls');

ls.children = [1, 2, 3, 4, 5].map(x => {
  const child = new Tag('li');
  const childChild = new Tag('a');
  childChild.text = x.toString();
  child.children.push(childChild);
  return child;
});

app.children.push(ls);

const manager = new Manager('#root', app);

manager.mount();
